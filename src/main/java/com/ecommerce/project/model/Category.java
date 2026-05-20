package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    //validation検証
    @NotBlank
    @Size(min = 5, message = "categoryName must contain at least 5 charrcters")
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

}
