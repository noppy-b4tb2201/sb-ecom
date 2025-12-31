package com.ecommerce.project.service;
import com.ecommerce.project.model.Category;
import java.util.List;

//カテゴリー操作を行うインターフェース

public interface CategoryService {
    List<Category> getALLCategories();
    void createCategory(Category category);

    String deleteCategory(Long categoryId);
}
