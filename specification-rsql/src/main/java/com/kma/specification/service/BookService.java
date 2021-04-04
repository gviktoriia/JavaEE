package com.kma.specification.service;

import static com.kma.specification.specification.BookSpecificationFactory.activeOnly;
import static com.kma.specification.specification.BookSpecificationFactory.authorBooks;
import static com.kma.specification.specification.BookSpecificationFactory.fetchAll;
import static com.kma.specification.specification.BookSpecificationFactory.newestOnly;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.kma.specification.domain.dto.AuthorDto;
import com.kma.specification.domain.dto.BookDto;
import com.kma.specification.domain.dto.CategoryDto;
import com.kma.specification.domain.entities.BookEntity;
import com.kma.specification.repositories.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final DateProvider dateProvider;

    private final EntityManager entityManager;

    public List<BookEntity> searchBooksCriteriaApi() {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<BookEntity> query = cb.createQuery(BookEntity.class);
        final Root<BookEntity> root = query.from(BookEntity.class);

        query.where(
            cb.equal(root.join("authors").get("id"), 2),
            cb.greaterThanOrEqualTo(root.get("createdTimestamp"), dateProvider.getCurrentDateTime().minusDays(7))
        );
        query.distinct(true);
        root.fetch("authors", JoinType.LEFT);
        root.fetch("category");

        return entityManager.createQuery(query).getResultList();
    }

    public List<BookDto> getAllBooks() {
        return repository.findAll().stream()
            .map(BookService::mapEntityToDto)
            .collect(Collectors.toList());
    }

    public List<BookDto> getNewestBook() {
        final LocalDateTime createdAfter = dateProvider.getCurrentDateTime().minusDays(7);
        final Page<BookEntity> bookPage = repository.findAll(
            fetchAll()
                .and(activeOnly())
                .and(newestOnly(createdAfter)),
            PageRequest.of(0, 10)
        );

        return bookPage.stream()
            .map(BookService::mapEntityToDto)
            .collect(Collectors.toList());
    }

    public List<BookDto> getAuthorBooks(final int authorId) {
        final Page<BookEntity> bookPage = repository.findAll(
            fetchAll()
                .and(authorBooks(authorId))
                .and(activeOnly()),
            PageRequest.of(0, 10)
        );

        return bookPage.stream()
            .map(BookService::mapEntityToDto)
            .collect(Collectors.toList());
    }

    private static BookDto mapEntityToDto(BookEntity book) {
        return BookDto.builder()
            .id(book.getId())
            .isbn(book.getIsbn())
            .title(book.getTitle())
            .createdTimestamp(book.getCreatedTimestamp())
            .status(book.getStatus())
            .category(new CategoryDto(book.getCategory().getId(), book.getCategory().getName()))
            .authors(
                book.getAuthors().stream()
                    .map(author -> new AuthorDto(author.getId(), author.getFullName()))
                    .collect(Collectors.toList())
            )
            .build();
    }

}
