package com.onlinebookstoreapplication.repositories;

import com.onlinebookstoreapplication.models.BookAvailabilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAvailabilityRepository extends JpaRepository<BookAvailabilityStatus, Long> {
}
