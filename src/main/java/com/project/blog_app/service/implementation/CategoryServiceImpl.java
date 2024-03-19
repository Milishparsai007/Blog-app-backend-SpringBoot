package com.project.blog_app.service.implementation;

import com.project.blog_app.entities.Category;
import com.project.blog_app.exception.ResourceNotFoundException;
import com.project.blog_app.payload.CategoryDataTransfer;
import com.project.blog_app.repository.CategoryRepository;
import com.project.blog_app.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDataTransfer createCategory(CategoryDataTransfer categoryDataTransfer) {
        Category category=this.modelMapper.map(categoryDataTransfer, Category.class);
        category.setCategoryTitle(categoryDataTransfer.getCategoryTitle());
        category.setCategoryDesc(categoryDataTransfer.getCategoryDesc());
        Category savedCategory=this.categoryRepository.save(category);
        return this.modelMapper.map(category,CategoryDataTransfer.class);
    }

    @Override
    public CategoryDataTransfer updateCategory(CategoryDataTransfer categoryDataTransfer, Integer categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        category.setCategoryTitle(categoryDataTransfer.getCategoryTitle());
        category.setCategoryDesc(categoryDataTransfer.getCategoryDesc());

        Category updatedCategory=this.categoryRepository.save(category);
        return this.modelMapper.map(category,CategoryDataTransfer.class);
    }

    @Override
    public CategoryDataTransfer getCategoryById(Integer categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        return this.modelMapper.map(category,CategoryDataTransfer.class);
    }

    @Override
    public List<CategoryDataTransfer> getAllCategories() {
        List<Category> categories=this.categoryRepository.findAll();
        List<CategoryDataTransfer> categoryDataTransfers=categories.stream().map((category) -> this.modelMapper.map(category,CategoryDataTransfer.class)).collect(Collectors.toList());
        //ye wali line kya kar rhi hai ki categories wali list se ek ek krke object nikal rahi hai map method ki help se aur un objects ko model mapper
        //ki help se CategoryDataTransfer wali class me change kar rhi hai.
        //Phir unhe ek new list me dal rahi hai jiska type hai CategoryDataTransfer.

        return categoryDataTransfers;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        this.categoryRepository.delete(category);
    }
}
