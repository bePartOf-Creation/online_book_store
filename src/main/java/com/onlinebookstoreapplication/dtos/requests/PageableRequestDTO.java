package com.onlinebookstoreapplication.dtos.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableRequestDTO {
    @NotNull(message = "Page Number cannot be empty.")
    private int pageNumber;


    @NotNull(message = "PageSize cannot be empty.")
    @Min(value = 0, message = "PageSize must be greater than or equal to 0")
    private int pageSize;
}
