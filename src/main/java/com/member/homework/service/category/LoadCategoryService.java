package com.member.homework.service.category;

import com.member.homework.domain.*;
import com.member.homework.repository.category.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoadCategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> loadAllCategories() {
        return null;
    }
}
