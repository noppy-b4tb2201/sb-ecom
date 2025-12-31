package com.ecommerce.project.service;
import com.ecommerce.project.model.Category;
import java.util.List;

//カテゴリー操作を行うインターフェース

public interface CategoryService {
    List<Category> getALLCategories();
    void createCategory(Category category);
    Category updateCategory(Category category, Long categoryId);
    String deleteCategory(Long categoryId);
}
