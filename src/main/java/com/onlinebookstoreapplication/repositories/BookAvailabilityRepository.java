package com.onlinebookstoreapplication.repositories;

import com.onlinebookstoreapplication.models.BookAvailabilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookAvailabilityRepository extends JpaRepository<BookAvailabilityStatus, Long> {

    @Query("select s from BookAvabv  ilabilityStatus s where s.bookId =: bookId ")
    Optional<BookAvailabilityStatus> findBookAvailabilityByBookId(@Param("bookId") Long bookId);

    @Modifying
    @Query("update BookAvailabilityStatus s SET s.status =: status WHERE  s.bookId =: bookId")
    void updateBookAvailabilityStatus(@Param("status") boolean status, @Param("bookId") Long bookId);


}
