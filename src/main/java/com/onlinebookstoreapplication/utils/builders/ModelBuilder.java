package com.onlinebookstoreapplication.utils.builders;

import com.onlinebookstoreapplication.dtos.requests.BookRequestDTO;
import com.onlinebookstoreapplication.dtos.requests.UpdateBookRequestDTO;
import com.onlinebookstoreapplication.models.Book;

import java.time.LocalDateTime;

public class ModelBuilder {

    public static Book modelMapper(BookRequestDTO bookRequestDTO, String category) {
        Book newBook = new Book();
        newBook.setIsbn(bookRequestDTO.getIsbn());
        newBook.setAuthor(bookRequestDTO.getAuthor());
        newBook.setCategory(category);
        newBook.setQuantity(bookRequestDTO.getQuantity());
        newBook.setTitle(bookRequestDTO.getTitle());
        newBook.setCreatedAt(LocalDateTime.now());
        return newBook;


    }
    public static Book modelMapper(UpdateBookRequestDTO bookRequestDTO, Book foundBook) {
        foundBook.setAuthor(bookRequestDTO.getAuthor());
        foundBook.setQuantity(Math.max(bookRequestDTO.getQuantity(), 0));
        foundBook.setTitle(bookRequestDTO.getTitle());
        foundBook.setUpdatedAt(LocalDateTime.now());
        return foundBook;


    }
}