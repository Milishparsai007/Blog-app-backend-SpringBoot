package com.project.blog_app.controller;

import com.project.blog_app.payload.ApiResponse;
import com.project.blog_app.payload.CategoryDataTransfer;
import com.project.blog_app.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //post
    @PostMapping("/newCategory")
    public ResponseEntity<CategoryDataTransfer> createCategory(@Valid @RequestBody CategoryDataTransfer categoryDataTransfer)
    {
        CategoryDataTransfer createCategory=this.categoryService.createCategory(categoryDataTransfer);
        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    //put
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDataTransfer> updateCategory(@Valid @RequestBody CategoryDataTransfer categoryDataTransfer, @PathVariable Integer categoryId)
    {
        CategoryDataTransfer updateCategory=this.categoryService.updateCategory(categoryDataTransfer,categoryId);
        return new ResponseEntity<CategoryDataTransfer>(updateCategory,HttpStatus.OK);
    }

    //getById
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDataTransfer> getCategoryById(@PathVariable Integer categoryId)
    {
        CategoryDataTransfer category=this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<CategoryDataTransfer>(category,HttpStatus.FOUND);

    }

    //getAllUsers
    @GetMapping()
    public ResponseEntity<List<CategoryDataTransfer>> getAllCategories()
    {
        List<CategoryDataTransfer> allCategories=this.categoryService.getAllCategories();
        return new ResponseEntity<List<CategoryDataTransfer>>(allCategories,HttpStatus.FOUND);
        //Or we can just return
//        return ResponseEntity.ok(allCategories);
    }

    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId)
    {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully",true),HttpStatus.OK);
    }

}
