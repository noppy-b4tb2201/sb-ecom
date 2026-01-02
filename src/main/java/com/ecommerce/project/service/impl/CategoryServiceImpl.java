package com.ecommerce.project.service.impl;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

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

    //カテゴリーIDの更新
    @Override
    public Category updateCategory(Category category, Long categoryId) {

        //データベースにアクセスし、categoryIdに一致するカテゴリーを取得、見つからなければ例外を返す
        Category category_updated = categoryRepository.findById(categoryId)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "category not found"));

        //引数として受け渡されたカテゴリーにカテゴリーIDを設定
        category.setCategoryId(categoryId);

        //カテゴリーを上書きし、データベースに保存
        category_updated = categoryRepository.save(category);

        //更新されたカテゴリーを返す
        return categoryRepository.save(category_updated);
    }

    //カテゴリー削除
    @Override
    public String deleteCategory(Long categoryId) {

        //データベースにアクセスし、categoryIdに一致するカテゴリーを取得、見つからなければ例外を返す
        Category category_deleted = categoryRepository.findById(categoryId)
                                                      .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                              "category not found"));

        //見つかれば削除する
        categoryRepository.delete(category_deleted);

        //削除成功メッセージを返す
        return "category with categoryId:" + categoryId + " deleted successfully";
    }
}
