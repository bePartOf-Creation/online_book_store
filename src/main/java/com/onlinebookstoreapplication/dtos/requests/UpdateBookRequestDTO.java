package com.onlinebookstoreapplication.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateBookRequestDTO {


    @NotBlank(message = "Book Title Cannot Be Empty.")
    private String title;

    @NotBlank(message = "Book Author Cannot Be Empty.")
    private String author;

    @NotNull(message = "Quantity cannot be empty.")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private int quantity;


}
