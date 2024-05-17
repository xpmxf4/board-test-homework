package com.member.homework.controller.category;

import com.fasterxml.jackson.databind.*;
import com.member.homework.config.*;
import com.member.homework.domain.*;
import com.member.homework.service.category.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Import(SecurityConfig.class)
@WebMvcTest(CategoryController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CategoryControllerReadTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoadCategoryService loadCategoryService;

    @Test
    void 관리자는_모든_카테고리를_조회할_수_있다() throws Exception {
        // given - 상황 만들기
        Category category1 = createCategory(1);
        Category category2 = createCategory(2);
        List<Category> categories = List.of(category1, category2);
        given(loadCategoryService.loadAllCategories())
                .willReturn(categories);

        // when - 동작
        ResultActions getResult = mockMvc.perform(get("/api/category")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authentication" , "ADMIN")
        );

        // then - 검증
        getResult
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.name()))
                .andExpect(jsonPath("$.data[0].name").value(category1.getName()))
                .andExpect(jsonPath("$.data[0].description").value(category1.getDescription()))
                .andExpect(jsonPath("$.data[1].name").value(category2.getName()))
                .andExpect(jsonPath("$.data[1].description").value(category2.getDescription()))
                .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()));

    }

    @Test
    void 카테고리가_존재하지_않으면_빈_결과물을_보여준다() throws Exception {
        // given - 상황 만들기
        given(loadCategoryService.loadAllCategories())
                .willReturn(List.of());

        // when - 동작
        ResultActions getResult = mockMvc.perform(get("/api/category")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authentication" , "ADMIN")
        );

        // then - 검증
        getResult
                .andExpect(jsonPath("$.code").value(HttpStatus.NO_CONTENT.value()))
                .andExpect(jsonPath("$.status").value(HttpStatus.NO_CONTENT.name()))
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.message").value(HttpStatus.NO_CONTENT.getReasonPhrase()));
    }

    private static Category createCategory(int num) {
        return Category.of("test name" + num, "test desc" + num, null);
    }
}
