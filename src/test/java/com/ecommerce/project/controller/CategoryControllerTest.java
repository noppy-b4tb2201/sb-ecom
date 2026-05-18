package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;

//対象のコントローラー層を指定してWeb層のみをテスト
//@WebMvcTest(CategoryController.class)
//class CategoryControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    //サービス層のモック化
//    @MockitoBean
//    private CategoryService categoryService;
//
//    @Test
//    void testGetALLCategories() throws Exception {
//        //Mockデータ準備
//        Category c1 = new Category(1L, "videogame");
//        Category c2 = new Category(2L, "tripplan");
//        List<Category> categories = Arrays.asList(c1, c2);
//
//        //Service層のメソッドが呼ばれたらこのMockデータを返す
//        Mockito.when(categoryService.getALLCategories()).thenReturn(categories);
//
//        //検証開始、コントローラークラスの挙動どおりに進める
//        //Getリクエスト送信
//        mockMvc.perform(get("/api/public/categories"))
//                .andExpect(status().isOk()) //HTTPステータスの確認
//                .andExpect(content().contentType((MediaType.APPLICATION_JSON)))
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].categoryName").value("videogame"));
//    }
//}