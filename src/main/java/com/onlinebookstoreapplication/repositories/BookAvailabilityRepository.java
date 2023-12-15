package com.onlinebookstoreapplication.repositories;

import com.onlinebookstoreapplication.models.BookAvailabilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookAvailabilityRepository extends JpaRepository<BookAvailabilityStatus, Long> {

    @Query("select s from BookAvailabilityStatus s where s.bookId =:bookId ")
    Optional<BookAvailabilityStatus> findBookAvailabilityByBookId(@Param("bookId") Long bookId);

    @Transactional
    @Modifying
    @Query(value = "update book_availability_status s SET status =:status WHERE  s.book_id =:bookId", nativeQuery = true)
    void updateBookAvailabilityStatus(@Param("status") boolean status, @Param("bookId") Long bookId);


}
