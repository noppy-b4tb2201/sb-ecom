package com.ecommerce.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//クラスからエンティティへと変換、データベースへ値が保存される
@Entity(name = "categories")
@Data
//JPA使用時にデフォルトコンストラクタ要求を満たすためのデフォルトコンストラクター
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    //categoryIdをプライマリーキーとしてデータベース側で自動採番
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;

}
