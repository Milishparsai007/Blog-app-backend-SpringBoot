package com.project.blog_app.service;

import com.project.blog_app.entities.Post;
import com.project.blog_app.payload.PostDataTransfer;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {
    //create new post
    PostDataTransfer createPost(PostDataTransfer postDataTransfer,Integer userId,Integer categoryId);

    //update existing post
    PostDataTransfer updatePost(PostDataTransfer postDataTransfer,Integer postId);
    PostDataTransfer updatePost(PostDataTransfer postDataTransfer,Integer postId,Integer userId,Integer categoryId);

    //delete existing post
    void deletePost(Integer postId);

    //get all posts
    List<PostDataTransfer> getAllPosts();

    //get post by id
    PostDataTransfer getPostById(Integer postId);

    //get all posts in a single category
    List<PostDataTransfer> getPostsByCategory(Integer categoryId);

    //get all posts by a single user
    List<PostDataTransfer> getPostsByUser(Integer userId);
}
