package com.geekster.studentportal.services;

import com.geekster.studentportal.models.Book;
import com.geekster.studentportal.repositories.IBookDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    IBookDao bookDao;

    public void createBook(Book book) {
        bookDao.save(book);
    }

    public Book getBookById(Long bookId) {
        return bookDao.findBookByBookId(bookId);
    }

    public Iterable<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Transactional
    public void modifyBookPriceById(Long bookId, Double price) {
        bookDao.updatePriceByBookId(bookId,price);
    }

    public String removeBookById(Long bookId) {
        Book book = bookDao.findBookByBookId(bookId);

        if(book == null){
            throw new NullPointerException("Book with id "+bookId+" doesn't exists !!");
        }else {
            bookDao.deleteById(Math.toIntExact(bookId));
            return "Book with id "+bookId+" is deleted successfully !!";
        }

    }
}
