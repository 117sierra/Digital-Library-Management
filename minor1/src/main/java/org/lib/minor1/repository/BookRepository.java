package org.lib.minor1.repository;

import org.lib.minor1.models.Book;
import org.lib.minor1.models.BookType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByBookNo(String bookNo);

    List<Book> findByAuthor_Name(String authorName);

    List<Book> findByCost(Integer cost);

    List<Book> findByType(BookType type);

    List<Book> findByCostLessThan(Integer cost);
    List<Book> findByCostGreaterThan(Integer cost);
}
