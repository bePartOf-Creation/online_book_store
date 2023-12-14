package com.onlinebookstoreapplication.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookRequestDTO{

    @NotBlank(message = "Book Title Cannot Be Empty.")
    private String title;

    @NotBlank(message = "Book Author Cannot Be Empty.")
    private String author;

    @NotBlank(message = "Quantity of Books must be specified.")
    private int quantity;

    @NotBlank(message = "Select a Book Category ")
    private Long categoryId;

    @NotBlank(message = "Book isbn Cannot Be Empty.")
    private String isbn;
}
