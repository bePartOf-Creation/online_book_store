package com.onlinebookstoreapplication.services;

import com.onlinebookstoreapplication.dtos.requests.BookRequestDTO;
import com.onlinebookstoreapplication.dtos.requests.UpdateBookRequestDTO;
import com.onlinebookstoreapplication.dtos.responses.GenericResponse;
import com.onlinebookstoreapplication.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Book createBook(BookRequestDTO bookRequestDTO);
    List<GenericResponse> getAllBooks(int pageNumber, int pageSize);
    Book updateBook(UpdateBookRequestDTO requestDTO);
    void deleteBook(Long id);
}
