package com.project.blog_app.service;

import com.project.blog_app.payload.CategoryDataTransfer;

import java.util.List;

public interface CategoryService {
    //create
    CategoryDataTransfer createCategory(CategoryDataTransfer categoryDataTransfer);

    //update
    CategoryDataTransfer updateCategory(CategoryDataTransfer categoryDataTransfer,Integer categoryId);

    //get
    CategoryDataTransfer getCategoryById(Integer categoryId);

    //getAll
    List<CategoryDataTransfer> getAllCategories();

    //delete
    void deleteCategory(Integer categoryId);

}
