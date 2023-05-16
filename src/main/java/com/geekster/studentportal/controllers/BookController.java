package com.geekster.studentportal.controllers;


import com.geekster.studentportal.models.Book;
import com.geekster.studentportal.models.Book;
import com.geekster.studentportal.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping(value = "/")
    public void addBook(@RequestBody Book book){
        bookService.createBook(book);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Iterable<Book>> getAllBooks(){
        Iterable<Book> books = bookService.getAllBooks();
        HttpStatus status;
        if(books == null){
            status = HttpStatus.NO_CONTENT;
        }
        else{
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Iterable<Book>>(books,status);
    }

    @GetMapping(value = "/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        HttpStatus status = null;
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Book>(book, status);

    }

    @PutMapping(value = "/update/id/{bookId}/price/{price}")
    public ResponseEntity<String> updateBookPriceById(@PathVariable Long bookId,@PathVariable Double price){

        Book book = bookService.getBookById(bookId);
        String message = null;
        HttpStatus status;

        if(book == null){
            status = HttpStatus.NOT_MODIFIED;
            message = "id "+bookId+" is not valid";
        }
        else{
            status = HttpStatus.OK;
            bookService.modifyBookPriceById(bookId,price);

        }
        return new ResponseEntity<String>(message,status);
    }

    @DeleteMapping("/id/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long bookId){
        String message = null;
        HttpStatus status;

        try{
            message = bookService.removeBookById(bookId);
            status = HttpStatus.OK;
        }catch (Exception ex){
            status = HttpStatus.BAD_REQUEST;

        }
        return new ResponseEntity<String>(message,status);
    }

}
