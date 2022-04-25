package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Override
    List<Book> findAll();

    Book findFirstById(Integer integer);
    @Override
    <S extends Book> S saveAndFlush(S s);

    Book findFirstByNameLike(String name);
    Book findFirstByAuthorLike(String author);
    Book findFirstByIbsnLike(String ibsn);
    @Query("select  b from Book b where b.name like :param or b.ibsn like :param")
    List<Book> findTwo(@Param("param")String param);

}
