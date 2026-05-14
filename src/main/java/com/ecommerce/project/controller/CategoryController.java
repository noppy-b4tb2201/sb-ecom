package com.ecommerce.project.controller;

import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//カテゴリー操作にかかわるコントローラー

@RestController
@RequestMapping("/api")
public class CategoryController {

    //CategoryServiceのBeanを自動注入
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name = "message", defaultValue = "Hello") String message) {
        return new ResponseEntity<>("Echoed message: " + message, HttpStatus.OK);
    }

    //カテゴリー取得コントローラー
    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getALLCategories() {
        CategoryResponse categoryResponse = categoryService.getALLCategories();
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    //カテゴリー追加コントローラー
    @PostMapping("/public/categories")
    //@Validでvalidation検証
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    //カテゴリー削除コントローラー
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {

        CategoryDTO deletedCategoryDTO = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(deletedCategoryDTO, HttpStatus.OK);

    }

    //カテゴリー更新コントローラー
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId) {

        CategoryDTO categoryUpdatedDTO = categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(categoryUpdatedDTO, HttpStatus.OK);

    }
}
