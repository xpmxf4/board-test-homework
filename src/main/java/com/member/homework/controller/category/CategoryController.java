package com.member.homework.controller.category;

import com.member.homework.domain.*;
import com.member.homework.dto.response.*;
import com.member.homework.service.category.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final LoadCategoryService loadCategoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() {
        List<Category> categories = loadCategoryService.loadAllCategories();

        if (categories.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(ApiResponse.of(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.getReasonPhrase(), categories));

        return ResponseEntity.ok(ApiResponse.ok(categories));
    }
}
