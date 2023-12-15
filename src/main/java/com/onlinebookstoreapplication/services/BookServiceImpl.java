package com.onlinebookstoreapplication.services;

import com.onlinebookstoreapplication.configs.CategoryConfig;
import com.onlinebookstoreapplication.dtos.requests.BookRequestDTO;
import com.onlinebookstoreapplication.dtos.requests.UpdateBookRequestDTO;
import com.onlinebookstoreapplication.dtos.responses.GenericResponse;
import com.onlinebookstoreapplication.models.Book;
import com.onlinebookstoreapplication.models.BookAvailabilityStatus;
import com.onlinebookstoreapplication.repositories.BookAvailabilityRepository;
import com.onlinebookstoreapplication.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.onlinebookstoreapplication.utils.HelperUtil.*;
import static com.onlinebookstoreapplication.utils.builders.ModelBuilder.modelMapper;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookServiceImpl implements BookService {

    private final CategoryConfig categoryConfig;
    private final BookRepository bookRepo;
    private final BookAvailabilityRepository bookAvailabilityRepo;


    @Override
    public Book createBook(BookRequestDTO bookRequestDTO) {
        boolean isBookExist = this.bookRepo.existByTitle(bookRequestDTO.getTitle());
        checkDataIntegrity(bookRequestDTO, isBookExist);

        Book mappedBook = modelMapper(bookRequestDTO, categoryConfig.getCategory(bookRequestDTO.getCategoryId()));
        Book savedBook = this.bookRepo.save(mappedBook);

        BookAvailabilityStatus availabilityStatus = new BookAvailabilityStatus();
        availabilityStatus.setBookId(savedBook.getId());
        availabilityStatus.setStatus(bookRequestDTO.getQuantity() > 0);
        this.bookAvailabilityRepo.save(availabilityStatus);

        return savedBook;
    }


    @Override
    public List<GenericResponse> getAllBooks(int pageNumber, int pageSize) {
//        validatePageableObject(pageNumber, pageSize);

        Page<Book> books = this.bookRepo.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdAt")));
        return books.stream()
                .map(book -> new GenericResponse(book, getAvailabilityStatus(this.bookAvailabilityRepo, book.getId()).isStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public Book updateBook(Long id,UpdateBookRequestDTO updateBookRequestDTO) {
        Optional<Book> foundBook = getBook(this.bookRepo, id);
        Book mappedBook = modelMapper(updateBookRequestDTO, foundBook.get());
        boolean availabilityStatus = foundBook.get().getQuantity() > 0;
        this.bookAvailabilityRepo.updateBookAvailabilityStatus(availabilityStatus, mappedBook.getId());
        return this.bookRepo.save(mappedBook);
    }


    @Override
    public void deleteBook(Long id) {
        Optional<Book> existingBookOptional = getBook(this.bookRepo, id);
        Book existingBook = existingBookOptional.get();
        if (existingBook.getQuantity() > 0) {
            updateBookAvailabilityStatusForDeduction(this.bookAvailabilityRepo,existingBook, existingBook.getQuantity() - 1);
        }
        bookRepo.save(existingBook);
        bookRepo.deleteById(id);
    }



}
