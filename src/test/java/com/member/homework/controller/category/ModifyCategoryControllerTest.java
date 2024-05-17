package com.member.homework.controller.category;

import com.member.homework.config.*;
import com.member.homework.service.category.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;

@Import(SecurityConfig.class)
@WebMvcTest(ModifyCategoryController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ModifyCategoryControllerTest {

    @MockBean
    private ModifyCategoryService modifyCategoryService;

    @Test
    void 관리자는_카테고리의_이름_설명_부모_카테고리를_수정할_수_있다() {
        // given - 상황 만들기

        // when - 동작

        // then - 검증
    }

    @Test
    void 이름_없이_카테고리_수정은_불가능하다() {
        // given - 상황 만들기


        // when - 동작

        // then - 검증
    }

    @Test
    void 카테고리의_설명없이_수정은_불가능하다() {
        // given - 상황 만들기

        // when - 동작

        // then - 검증
    }

    @Test
    void 존재하지_않는_카테고리는_수정할_수_없다() {
        // given - 상황 만들기

        // when - 동작

        // then - 검증
    }

    @Test
    void 게시글이_존재하는_카테고리의_수정은_불가능하다() {
        // given - 상황 만들기
        // when - 동작

        // then - 검증
    }

    @Test
    void 관리자의_권한_없이_카테고리_수정은_불가능하다() {
        // given - 상황 만들기
        // when - 동작

        // then - 검증
    }
}
