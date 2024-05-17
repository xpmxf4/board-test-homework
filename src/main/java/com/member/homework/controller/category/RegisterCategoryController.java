package com.member.homework.controller.category;

import com.member.homework.service.category.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RegisterCategoryController {

    private final CreateCategoryService createCategoryService;


}
