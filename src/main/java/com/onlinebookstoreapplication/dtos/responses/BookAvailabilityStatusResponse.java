package com.onlinebookstoreapplication.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookAvailabilityStatusResponse {
    private boolean status;
    private int quantity;
}
