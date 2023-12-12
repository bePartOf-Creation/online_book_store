package com.onlinebookstoreapplication.dtos.requests;

import lombok.Builder;


@Builder
public record BookRequestDTO(
        String title,
        String author,
        String category,
        String isbn
) {
}
