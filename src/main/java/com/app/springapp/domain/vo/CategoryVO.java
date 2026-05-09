package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID              NUMBER          CONSTRAINT PK_CATEGORY PRIMARY KEY,
//CATEGORY_NAME   VARCHAR2(255)   NOT NULL

@Component
@Data
public class CategoryVO {
    private Long id;
    private String categoryName;
}
