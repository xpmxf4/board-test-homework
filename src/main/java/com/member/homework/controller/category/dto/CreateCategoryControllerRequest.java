package com.member.homework.controller.category.dto;


import jakarta.validation.constraints.*;

public record CreateCategoryControllerRequest(
        @NotBlank(message = "asdflkja;slkdfj;l") String name,
        String description,
        Long parentId
) {
}
