package com.member.homework.controller.category;

import com.fasterxml.jackson.databind.*;
import com.member.homework.controller.category.dto.*;
import com.member.homework.exception.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import static com.member.homework.exception.ErrorCode.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


@WebMvcTest(CategoryController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CreateCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private CreateCategoryControllerRequest req;

    @BeforeEach
    void setup() {
        req = new CreateCategoryControllerRequest(
                "test name",
                "test desc",
                1L
        );
    }


    @Test
    void 관리자가_새로운_카테고리를_생성할_수_있다() throws Exception {
        // given - 상황 만들기
        CreateCategoryControllerRequest createCategoryRequest = new CreateCategoryControllerRequest(
                "갤러리이름",
                "갤러리설명",
                1L // 마지막은 부모 ID
        );

        // when - 동작
        ResultActions perform = mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCategoryRequest)));

        // then - 검증
        perform
                .andExpect(jsonPath("$.code", is(HttpStatus.CREATED.value())))
                .andExpect(jsonPath("$.status", is(HttpStatus.CREATED)))
                .andExpect(jsonPath("$.data", is(null)))
                .andExpect(jsonPath("$.message", is("카테고리가 성공적으로 생성 되었습니다.")));
    }

    @Test
    void 카테고리의_이름은_비워둘_수_없다() throws Exception {
        // given - 상황 만들기
        CreateCategoryControllerRequest reqWithEmptyName = new CreateCategoryControllerRequest(
                "",
                "갤러리 설명",
                2L
        );

        // when - 동작
        ResultActions perform = mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqWithEmptyName)));

        // then - 검증
        perform
                .andExpect(jsonPath("$.code", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST)))
                .andExpect(jsonPath("$.data.name[0]", is("카테고리 이름은 비어있을 수 없습니다.")))
                .andExpect(jsonPath("$.message", is("잘못된 요청입니다.")));
    }

    @Test
    void 카테고리의_설명은_비워둘_수_없다() throws Exception {
        // given - 상황 만들기
        CreateCategoryControllerRequest reqWithEmptyDescription = new CreateCategoryControllerRequest(
                "갤러리 이름",
                "",
                2L
        );

        // when - 동작
        ResultActions perform = mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqWithEmptyDescription)));


        // then - 검증
        perform
                .andExpect(jsonPath("$.code", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST)))
                .andExpect(jsonPath("$.data.description[0]", is("카테고리 설명이 비어있을 수 없습니다.")))
                .andExpect(jsonPath("$.message", is("잘못된 요청입니다.")));
    }

    @Test
    void 없는_카테고리를_부모_카테고리로_지정할_수_없다() throws Exception {
        // given - 상황 만들기
        CreateCategoryControllerRequest reqWithNonExistingParentId = new CreateCategoryControllerRequest(
                "갤러리 이름",
                "갤러리 설명",
                999L
        );

        // when - 동작
        ResultActions perform = mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqWithNonExistingParentId)));


        // then - 검증
    }


    @Test
    void 관리자의_권한_없이는_카테고리_생성은_불가능하다() {
        // given - 상황 만들기

        // when - 동작

        // then - 검증
    }
}
