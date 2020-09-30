package com.hong.repository.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hong.repository.entity.Category;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-25 14:25
 * @Version : 1.0
 * Description     : 目录dto
 */
@Data
@NoArgsConstructor
public class CategoryDto {

    private String id;

    private String label;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private List<CategoryDto> children = new ArrayList<>();

    private Integer articlesNum;


    public CategoryDto(@NotNull Category category) {
        if (category == null)return;
        this.id = String.valueOf(category.getId());
        this.label = category.getCategoryName();
        this.updateTime = category.getUpdateTime();
        this.createTime = category.getCreateTime();

        if (category.getChildCategories() != null && category.getChildCategories().size() > 0) {
            for (Category cate : category.getChildCategories()) {
                children.add(new CategoryDto(cate));
            }
        }

        this.articlesNum = this.children.size();
    }
}
