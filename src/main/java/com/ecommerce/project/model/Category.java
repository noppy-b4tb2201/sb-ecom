package com.ecommerce.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//クラスからエンティティへと変換、データベースへ値が保存される
@Entity(name = "categories")
public class Category {
    //categoryIdをプライマリーキーとしてデータベース側で自動採番
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;

    public Category(String categoryName, Long categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    //JPA使用時にデフォルトコンストラクタ要求を満たすためのデフォルトコンストラクター
    public Category() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
