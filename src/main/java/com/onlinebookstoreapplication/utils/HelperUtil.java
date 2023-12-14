package com.onlinebookstoreapplication.utils;

import com.onlinebookstoreapplication.dtos.requests.BookRequestDTO;
import com.onlinebookstoreapplication.exceptions.BookNotFoundException;
import com.onlinebookstoreapplication.models.Book;
import com.onlinebookstoreapplication.models.BookAvailabilityStatus;
import com.onlinebookstoreapplication.repositories.BookAvailabilityRepository;
import com.onlinebookstoreapplication.repositories.BookRepository;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

public class HelperUtil {
//    public static void validatePageableObject(int pageNumber, int pageSize) {
//        try {
//            if (pageNumber< 0) {
//                throw new IllegalArgumentException("Page Number cannot be less than zero");
//            }
//
//            if (pageSize < 0) {
//                throw new IllegalArgumentException("Page size cannot be les than zero");
//            }
//        }
//        catch (IllegalArgumentException exception){
//
//        }
//    }

    /**
     * This is an Helper method that check data integrity before persisting.
     * @param bookRequestDTO
     * @param isBookExist
     */
    public static void  checkDataIntegrity(BookRequestDTO bookRequestDTO, boolean isBookExist) {
        if(isBookExist){
            throw new DataIntegrityViolationException("Book Already Exist with title: " + bookRequestDTO.getTitle());
        }
    }

    /**
     * This is an helper method that gets a book from database.
     *
     * @param bookRepo
     * @param id
     * @return
     */
    public static Optional<Book> getBook(BookRepository bookRepo,Long id) {
        Optional<Book> foundBook = bookRepo.findById(id);
        if(foundBook.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }
        return foundBook;
    }

    /**
     *  This is helper method that update book availability status determined
     *  by its current quantity during deletion of a book from the book store
     *
     * @param bookAvailabilityRepo
     * @param existingBook
     * @param i
     */
    public static void updateBookAvailabilityStatusForDeduction(BookAvailabilityRepository bookAvailabilityRepo, Book existingBook, int i) {
        existingBook.setQuantity(i);
        boolean availabilityStatus = existingBook.getQuantity() > 0;
        bookAvailabilityRepo.updateBookAvailabilityStatus(availabilityStatus, existingBook.getId());
    }

    /**
     * This is helper method that get book availability status determined by its book's id
     * @param bookAvailabilityRepo
     * @param bookId
     * @return
     */
    public static  BookAvailabilityStatus getAvailabilityStatus(BookAvailabilityRepository bookAvailabilityRepo,Long bookId) {
        Optional<BookAvailabilityStatus> optionalAvailability = bookAvailabilityRepo.findBookAvailabilityByBookId(bookId);
        return optionalAvailability.get();
    }
}
