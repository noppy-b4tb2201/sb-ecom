package com.ecommerce.project.service;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;

import java.util.List;

//カテゴリー操作を行うインターフェース

public interface CategoryService {
    CategoryResponse getALLCategories();

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);

    CategoryDTO deleteCategory(Long categoryId);
}
