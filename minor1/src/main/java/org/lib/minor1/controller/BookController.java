package org.lib.minor1.controller;

import org.lib.minor1.exception.TxnServiceException;
import org.lib.minor1.models.Book;
import org.lib.minor1.models.BookFilterType;
import org.lib.minor1.models.OperationType;
import org.lib.minor1.request.CreateBookRequest;
import org.lib.minor1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public void create(@RequestBody CreateBookRequest createBookRequest) throws TxnServiceException {
        bookService.create(createBookRequest);
    }

    @GetMapping("/find")
    public List<Book> findBooks(@RequestParam("filter") BookFilterType bookFiltertype,
                                @RequestParam("value") String value,
                                @RequestParam("operation")OperationType operationType){
     return bookService.findBooks(bookFiltertype, value, operationType);
    }

}
