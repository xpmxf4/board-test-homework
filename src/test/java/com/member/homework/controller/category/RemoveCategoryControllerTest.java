package com.member.homework.controller.category;

import com.member.homework.config.*;
import com.member.homework.exception.*;
import com.member.homework.service.category.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(RemoveCategoryController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RemoveCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RemoveCategoryService removeCategoryService;

    @Test
    void 관리자는_존재하는_카테고리를_ID로_삭제할_수_있다() throws Exception{
        // given - 상황 만들기
        Long categoryId = 1L;

        // when - 동작
        ResultActions deleteAction = mockMvc.perform(delete("/api/admin/category/{categoryId}" , categoryId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authentication" , "ADMIN")
        );

        // then - 검증
        deleteAction
                .andExpect(status().isOk());
    }

    @Test
    void 삭제하고자_하는_카테고리가_없으면_삭제는_불가능하다() throws Exception{
        // given - 상황 만들기
        Long unexistingId = Long.MAX_VALUE;
        BDDMockito.willThrow(new CustomException(ErrorCode.CATEGORY_NOT_FOUND_BY_ID))
                .given(removeCategoryService).removeCategory(unexistingId);

        // when - 동작
        ResultActions deleteAction = mockMvc.perform(delete("/api/admin/category/{categoryId}" , unexistingId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authentication" , "ADMIN")
        );


        // then - 검증
        deleteAction
                .andExpect(status().isNotFound());
    }

    @Test
    void 일반_사용자는_카테고리를_삭제할_수_없다() throws Exception {
        // given - 상황 만들기
        Long categoryId = 1L;

        // when - 동작
        ResultActions deleteAction = mockMvc.perform(delete("/api/admin/category/{categoryId}" , categoryId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authentication" , "MEMBER")
        );

        // then - 검증
        deleteAction
                .andExpect(status().isForbidden());
    }

    @Test
    void 게시글이_존재하는_카테고리는_삭제가_안된다() {
        // given - 상황 만들기

        // when - 동작

        // then - 검증
    }
}
