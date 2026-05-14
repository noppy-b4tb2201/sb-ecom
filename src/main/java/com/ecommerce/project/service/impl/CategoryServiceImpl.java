package com.ecommerce.project.service.impl;

import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//カテゴリー操作を行うメソッド

@Service
public class CategoryServiceImpl implements CategoryService {

    //CategoryRepositoryのBeanを自動注入
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    //現在のすべてのカテゴリーを表示
    @Override
    public CategoryResponse getALLCategories()  {

        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()) {
            throw new APIException("No Category created till now");
        }
        List<CategoryDTO> categoryDTOs =categories.stream()
                .map(category ->  modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOs);
        return categoryResponse;
    }

    //カテゴリー追加
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = modelMapper.map(categoryDTO, Category.class);
        Category existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(existingCategory != null) {
            throw new APIException("Category with the name " + existingCategory.getCategoryName() + " already existed");
        }

        Category savedCategory = categoryRepository.save(category);

        CategoryDTO savedCategoryDTO =modelMapper.map(savedCategory, CategoryDTO.class);

        return savedCategoryDTO;

    }

    //カテゴリーIDの更新
    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        //データベースにアクセスし、categoryIdに一致するカテゴリーを取得、見つからなければ例外を返す
        Category categoryExists = categoryRepository.findById(categoryId)
                .orElseThrow(() ->new ResourceNotFoundException("category", "categoryId", categoryId));

        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setCategoryId(categoryId);

        //カテゴリーを上書きし、データベースに保存
        categoryExists = categoryRepository.save(category);

        CategoryDTO categoryUpdatedDTO = modelMapper.map(categoryExists, CategoryDTO.class);

        //更新されたカテゴリーを返す
        return categoryUpdatedDTO;
    }

    //カテゴリー削除
    @Override
    public CategoryDTO deleteCategory(Long categoryId) {

        //データベースにアクセスし、categoryIdに一致するカテゴリーを取得、見つからなければ例外を返す
        Category category_deleted = categoryRepository.findById(categoryId)
                                                      .orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

        //見つかれば削除する
        categoryRepository.delete(category_deleted);

        CategoryDTO deletedCategoryDTO = modelMapper.map(category_deleted, CategoryDTO.class);

        //削除成功メッセージを返す
        return deletedCategoryDTO;
    }
}
