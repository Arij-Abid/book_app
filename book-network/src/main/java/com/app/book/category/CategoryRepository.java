package com.app.book.category;

import com.app.book.feedback.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("""
                    SELECT c
                    FROM Category c
                    JOIN c.book b
                    WHERE b.id = :bookId
            """)
    Page<Category> findAllByBookId(@Param("bookId") Integer bookId, Pageable pageable);

}