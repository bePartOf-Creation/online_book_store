package com.onlinebookstoreapplication.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookCategory {

    CLASSIC("Classic", 1l),
    ROMANCE("Romance", 2l),
    BUSINESS("Business", 3l),
    CRIME("Crime",4l),
    SCI_FI("Scientific", 5l),
    TOP_SELLERS("Top Sellers",6l);


    private String name;
    private Long id;
}
