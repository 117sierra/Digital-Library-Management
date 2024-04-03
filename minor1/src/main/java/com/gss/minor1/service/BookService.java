package com.gss.minor1.service;

import com.gss.minor1.Repository.AuthorRepository;
import com.gss.minor1.Repository.Bookrespository;
import com.gss.minor1.Repository.StudentRepository;
import com.gss.minor1.Request.CreateBookRequest;
import com.gss.minor1.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

@Service
public class BookService {
    @Autowired
    private Bookrespository bookrespository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String BOOK_PREFIX= "book:";
    public void create(CreateBookRequest createBookRequest){
            Book book= createBookRequest.to();
            Author authorcheck= authorRepository.findByEmail(book.getAuthor().getEmail());
            if(authorcheck==null){
                 authorcheck=authorRepository.save(book.getAuthor());
            }
    //        bookrespository.save(book);
            List<Book> list= new ArrayList<>();
    ////        before saving book info with author reference the data needs to be present in the author table beforehand.
            book.setAuthor(authorcheck);  // didnt get the sense of it
            book= bookrespository.save(book);
////        always keep class-member names as small letters problem for database entry
        pushdataredisbybookno(book);
        list.add(book);
        pushdataredisbyauthorname(list);
        pushdataredisbycost(book);
    }

    private void pushdataredisbycost(Book book) {
        redisTemplate.opsForList().rightPush(BOOK_PREFIX+book.getCost(),book);
        redisTemplate.expire(BOOK_PREFIX+book.getCost(),10,TimeUnit.MINUTES);

    }

    private void pushdataredisbybookno(Book book) {
        redisTemplate.opsForValue().set(BOOK_PREFIX+book.getBookNo(),book,10, TimeUnit.MINUTES);
    }
    private void pushdataredisbyauthorname(List<Book> list) {
        if (!list.isEmpty()) {
            String authorname= list.get(0).getAuthor().getName();
            redisTemplate.opsForList().leftPush(BOOK_PREFIX + authorname, list);
            redisTemplate.expire(BOOK_PREFIX + authorname, 10, TimeUnit.MINUTES);
        }
    }

    public List<Book> findbooks(Bookfiltertype bookfiltertype, String value, Operationtype operationtype) {
        switch (operationtype){
            case EQUALS:
                switch (bookfiltertype){
                    case BOOK_NO:
                       Book bookinredis= (Book) redisTemplate.opsForValue().get(BOOK_PREFIX+value);
                       if(bookinredis!=null){
                           List<Book> list = new ArrayList<>();
                           list.add(bookinredis);
                           return list;
                       }
                       List<Book>list1= bookrespository.findByBookNo(value);
                       // push it in redis
                        pushdataredisbybookno(list1.get(0));
                        return list1;
                    case BOOKTYPE:
                        return bookrespository.findByBookType(BookType.valueOf(value));
                    case AUTHOR_NAME:
                        List<Book> booklist= (List<Book>) redisTemplate.opsForList().range(BOOK_PREFIX+value,0,-1);
                        if(!booklist.isEmpty()){
                            return booklist;
                        }
                        booklist=bookrespository.findByAuthor_Name(value);
                        pushdataredisbyauthorname(!booklist.isEmpty()? booklist : null);
                        return booklist;
                    case COST:
                        return bookrespository.findByCost(Integer.valueOf(value));
                }
            case LESS_THAN:
                switch (bookfiltertype){
                    case COST:
                        return bookrespository.findByCost(Integer.valueOf(value));
                }
            case GREATER_THAN:
                switch (bookfiltertype){
                    case COST:
                        return bookrespository.findByCostGreaterThan(Integer.valueOf(value));
                }
            case GREATER_THAN_EQUALS:
                switch (bookfiltertype){
                    case COST:
                        return bookrespository.findByCostGreaterThanEqual(Integer.valueOf(value));
                }
            default:
                return new ArrayList<>();
        }
    }

    public Book update(Book book) {
        bookrespository.save(book);
        return book;
    }
}
