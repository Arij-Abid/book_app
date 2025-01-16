package com.app.book.category;

import com.app.book.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
@Tag(name = "Category")

public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/book/{book-id}")
    public ResponseEntity<PageResponse<CategoryResponse>> findAllCategorysByBook(
            @PathVariable("book-id") Integer bookId,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(categoryService.findAllCategorysByBook(bookId, page, size, connectedUser));
    }

//    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
  /*  @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> getAllCategories(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(categoryService.findAllCategorysByBook(page, size, connectedUser));
    }
*/
    @PostMapping
    public ResponseEntity<Integer> createCategory(
            @Valid @RequestBody CategoryRequest categoryRequest,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(categoryService.save(categoryRequest, connectedUser));


    }

}