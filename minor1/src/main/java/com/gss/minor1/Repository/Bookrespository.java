package com.gss.minor1.Repository;

import com.gss.minor1.models.Book;
import com.gss.minor1.models.BookType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Bookrespository extends JpaRepository<Book,Integer> {
    public List<Book> findByBookNo(String bookno);
    public List<Book> findByAuthor_Name(String authorname);
    public List<Book> findByCost(Integer cost);
    public List<Book> findByBookType(BookType type);
    public List<Book> findByCostLessThan(Integer value);

    public List<Book> findByCostGreaterThan(Integer value);
    public List<Book> findByCostGreaterThanEqual(Integer value);
}
