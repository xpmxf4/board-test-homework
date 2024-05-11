package com.member.homework.controller.category;

import com.member.homework.service.category.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;

@WebMvcTest
public class CreateCategoryControllerTest {

    @Autowired
    private CategoryController categoryController;
    @MockBean
    private CreateCategoryService createCategoryService;

}
