package com.onlinebookstoreapplication.configs;

import com.onlinebookstoreapplication.enums.BookCategory;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Configuration
@Slf4j
public class CategoryConfig {

    HashMap<Long, String> categories = new LinkedHashMap<>();

    @PostConstruct
    public void seedCategories(){
        categories.put(BookCategory.BUSINESS.getId(), BookCategory.BUSINESS.getName());
        categories.put(BookCategory.CLASSIC.getId(), BookCategory.CLASSIC.getName());
        categories.put(BookCategory.CRIME.getId(), BookCategory.CRIME.getName());
        categories.put(BookCategory.ROMANCE.getId(), BookCategory.ROMANCE.getName());
        categories.put(BookCategory.SCI_FI.getId(), BookCategory.SCI_FI.getName());
    }

   public String getCategory(Long id){
        String category = categories.get(id);
        log.info("::: Category Found -> {}", category);
        return category;
   }
}
