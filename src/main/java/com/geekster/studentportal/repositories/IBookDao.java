package com.geekster.studentportal.repositories;

import com.geekster.studentportal.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookDao extends JpaRepository<Book,Integer> {

    Book findBookByBookId(Long bookId);

    @Modifying
    @Query(value = "update books set price =:price where book_id =:bookId",nativeQuery = true)
    void updatePriceByBookId(Long bookId, Double price);
}
