package com.member.homework.controller.category;

import com.member.homework.service.category.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RemoveCategoryController {

    private final RemoveCategoryService removeCategoryService;

    @DeleteMapping("/api/admin/category/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        removeCategoryService.removeCategory(categoryId);
        return ResponseEntity.ok().build();
    }
}
