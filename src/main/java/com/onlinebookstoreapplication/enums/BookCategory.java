package com.onlinebookstoreapplication.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookCategory {

    CLASSIC("Classic"),BUSINESS("Business"),SCI_FI("Scientific"),
    ROMANCE("Romance"),CRIME("Crime"),TOP_SELLERS("Top Sellers");


    private String name;
}
