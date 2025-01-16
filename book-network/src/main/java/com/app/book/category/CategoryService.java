package com.app.book.category;


import com.app.book.common.PageResponse;
import com.app.book.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepository categoryRepository;
       private final CategoryMapper categoryMapper;


    public List<Category> findAll() {
            return categoryRepository.findAll();
        }

/*
    public PageResponse<CategoryResponse> findAllCategorys(int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Category> categorys = categoryRepository.findAllByBookId(pageable, connectedUser.getName());
        List<CategoryResponse> categoryResponse = categorys.stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
        return new PageResponse<>(
                categoryResponse,
                categorys.getNumber(),
                categorys.getSize(),
                categorys.getTotalElements(),
                categorys.getTotalPages(),
                categorys.isFirst(),
                categorys.isLast()
        );
    }
*/


    @Transactional
    public PageResponse<CategoryResponse> findAllCategorysByBook(Integer bookId, int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = ((User) connectedUser.getPrincipal());
        Page<Category> categorys = categoryRepository.findAllByBookId(bookId, pageable);
        List<CategoryResponse> categoryResponses = categorys.stream()
                .map(f -> categoryMapper.toCategoryResponse(f, user.getId()))
                .toList();
        return new PageResponse<>(
                categoryResponses,
                categorys.getNumber(),
                categorys.getSize(),
                categorys.getTotalElements(),
                categorys.getTotalPages(),
                categorys.isFirst(),
                categorys.isLast()
        );

    }


    public Integer save(CategoryRequest request, Authentication connectedUser) {
        // User user = ((User) connectedUser.getPrincipal());
        Category category = categoryMapper.toCategory(request);
        // book.setOwner(user);
        return categoryRepository.save(category).getId();
    }


}