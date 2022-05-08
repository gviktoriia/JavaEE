package com.example.demo.repository;

import com.example.demo.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookJPARepository extends PagingAndSortingRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
}