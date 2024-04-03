package com.gss.minor1.controller;

import com.gss.minor1.Request.CreateBookRequest;
import com.gss.minor1.models.Book;
import com.gss.minor1.models.Bookfiltertype;
import com.gss.minor1.models.Operationtype;
import com.gss.minor1.service.BookService;
import jdk.dynalink.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

@GetMapping("/hello")
public String hello(){
    return "hello";
}
    @PostMapping("/create")
    public void create(@RequestBody CreateBookRequest createBookRequest){

        bookService.create(createBookRequest);
    }
    @GetMapping("/find")
    public List<Book> findbooks(@RequestParam("filter") Bookfiltertype bookfiltertype, @RequestParam("value") String value, @RequestParam("operation") Operationtype operationtype){
        return bookService.findbooks(bookfiltertype,value,operationtype);
    }

}
