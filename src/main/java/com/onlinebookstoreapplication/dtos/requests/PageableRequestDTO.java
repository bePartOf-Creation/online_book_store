package com.onlinebookstoreapplication.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableRequestDTO {
    private int pageNumber;
    private int pageSize;
}
