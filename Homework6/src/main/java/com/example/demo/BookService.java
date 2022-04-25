package com.example.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public Book createUser(String name, String ibsn, String author){
        Book b=new Book(name, ibsn, author);
        bookRepository.saveAndFlush(b);
        return b;
    }
    @Transactional
    public Book createUser(Book b){
        bookRepository.saveAndFlush(b);
        return b;
    }
    public Book getBookById(Integer id){
        return bookRepository.findFirstById(id);
    }

    public Book getByName(String name){
        return bookRepository.findFirstByNameLike(name);
    }
    public Book getByIbsn(String ibsn){
        return bookRepository.findFirstByIbsnLike(ibsn);
    }
    public Book getByAuthor(String author){
        return bookRepository.findFirstByAuthorLike(author);
    }
    public Book getWord(String word){
        return  bookRepository.findTwo(word).get(0);
    }
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public boolean contains(Book b){
        List<Book> list = bookRepository.findTwo(b.getIbsn());
        if(list.size()>0) return true;
        list=bookRepository.findTwo(b.getName());
        if(list.size()>0) return true;
        return false;

    }

}
