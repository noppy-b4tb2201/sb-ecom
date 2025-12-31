package com.ecommerce.project.service.impl;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//カテゴリー操作を行うメソッド

@Service
public class CategoryServiceImpl implements CategoryService {
    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    //現在のすべてのカテゴリーを表示
    @Override
    public List<Category> getALLCategories() {
        return categories;
    }

    //カテゴリー追加
    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    //カテゴリーの更新
    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> category_updated = categories.stream()
                                                       .filter(c -> c.getCategoryId().equals(categoryId))
                                                       .findFirst();

        if (category_updated.isPresent()) {
            Category exisitingCategory = category_updated.get();
            exisitingCategory.setCategoryName(category.getCategoryName());

            return exisitingCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "category not found");
        }
    }

    //カテゴリー削除
    @Override
    public String deleteCategory(Long categoryId) {
        Category category_deleted = categories.stream()
                                      .filter(c -> c.getCategoryId().equals(categoryId))
                                      .findFirst()
                                      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                              "category not found"));
        categories.remove(category_deleted);
        return "category with categoryId:" + categoryId + " deleted successfully";
    }
}
