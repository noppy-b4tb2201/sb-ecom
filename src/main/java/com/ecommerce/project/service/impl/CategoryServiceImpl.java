package com.ecommerce.project.service.impl;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//カテゴリー操作を行うメソッド

@Service
public class CategoryServiceImpl implements CategoryService {

    //CategoryRepositoryのBeanを自動注入
    @Autowired
    private CategoryRepository categoryRepository;

    //現在のすべてのカテゴリーを表示
    @Override
    public List<Category> getALLCategories()  {
        return categoryRepository.findAll();
    }

    //カテゴリー追加
    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    //カテゴリーの更新
    @Override
    public Category updateCategory(Category category, Long categoryId) {

        //カテゴリーを全取得し、リスト配列に格納
        List<Category> categories = categoryRepository.findAll();

        //categoryIdに一致するカテゴリーを見つける
        Optional<Category> category_updated = categories.stream()
                                                       .filter(c -> c.getCategoryId().equals(categoryId))
                                                       .findFirst();

        //一致するカテゴリーを見つけたらexisitingCategory変数を仲介して値を更新する
        //見つからなければ例外を返す
        if (category_updated.isPresent()) {
            Category exisitingCategory = category_updated.get();
            exisitingCategory.setCategoryName(category.getCategoryName());

            return categoryRepository.save(exisitingCategory);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "category not found");
        }
    }

    //カテゴリー削除
    @Override
    public String deleteCategory(Long categoryId) {

        //カテゴリーを全取得し、リスト配列に格納
        List<Category> categories = categoryRepository.findAll();

        //categoryIdに一致するカテゴリーを見つける、見つからなければ例外を返す
        Category category_deleted = categories.stream()
                                      .filter(c -> c.getCategoryId().equals(categoryId))
                                      .findFirst()
                                      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                              "category not found"));

        //見つかれば削除する
        categoryRepository.delete(category_deleted);

        //削除成功メッセージを返す
        return "category with categoryId:" + categoryId + " deleted successfully";
    }
}
