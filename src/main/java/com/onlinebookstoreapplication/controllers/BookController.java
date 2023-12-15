package com.onlinebookstoreapplication.controllers;

import com.onlinebookstoreapplication.dtos.requests.BookRequestDTO;
import com.onlinebookstoreapplication.dtos.requests.PageableRequestDTO;
import com.onlinebookstoreapplication.dtos.requests.UpdateBookRequestDTO;
import com.onlinebookstoreapplication.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {
    private final BookService bookService;


    @PostMapping("book/new")
    public ResponseEntity<?> createBook(@RequestBody @Valid BookRequestDTO  bookRequestDTO){
        return new ResponseEntity<>(this.bookService.createBook(bookRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("book/update/{id}")
    public ResponseEntity<?> updateBook(@RequestBody @Valid UpdateBookRequestDTO updateBookRequestDTO, @PathVariable Long id){
        return new ResponseEntity<>(this.bookService.updateBook(id,updateBookRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("books")
    public ResponseEntity<?> allBooks(@RequestParam("pn") int pageNumber, @RequestParam("ps") int pageSize ){
        PageableRequestDTO  pageableRequestDTO = PageableRequestDTO.builder().pageNumber(pageNumber).pageSize(pageSize).build();
        return new ResponseEntity<>(this.bookService.getAllBooks(pageableRequestDTO.getPageNumber(),pageableRequestDTO.getPageSize()), HttpStatus.OK);
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        this.bookService.deleteBook(id);
        return new ResponseEntity<>("Book was Successfully deleted", HttpStatus.OK);
    }
}
