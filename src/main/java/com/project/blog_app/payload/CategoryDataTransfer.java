package com.project.blog_app.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDataTransfer {
    private Integer categoryId; //isko na bhi lo to bhi chlega because ye auto generated hai.
    @NotEmpty
    @Size(max = 30,message = "Title must be less than 30 letters")
    private String categoryTitle;
    @Size(max = 100,message = "Description must be less than 100 letters")
    @NotEmpty
    private String categoryDesc;
}
