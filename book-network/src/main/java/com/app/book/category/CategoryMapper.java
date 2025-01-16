package com.app.book.category;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service

public class CategoryMapper {
    public Category toCategory(CategoryRequest request) {
        return Category.builder()
                .name(request.name())
          /*      .book(Book.builder()
                        .id(request.bookId())
                        .shareable(false) // Not required and has no impact :: just to satisfy lombok
                        .archived(false) // Not required and has no impact :: just to satisfy lombok
                        .build()
                )
                */
                .build();
    }

    public CategoryResponse toCategoryResponse(Category category, Integer id) {
        return CategoryResponse.builder()
                .name(category.getName())
                .build();
    }
}
