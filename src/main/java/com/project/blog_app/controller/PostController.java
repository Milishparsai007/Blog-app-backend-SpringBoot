package com.project.blog_app.controller;

import com.project.blog_app.entities.Post;
import com.project.blog_app.entities.User;
import com.project.blog_app.payload.ApiResponse;
import com.project.blog_app.payload.PostDataTransfer;
import com.project.blog_app.service.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/users/{userId}/category/{categoryId}/newPost")
    //ye itna bda url isliye liya because hume specify krna pdega ki kis user ne kis category ke andar post dali hai.
    //eg:- https://localhost:8080/api/users/1/category/2/posts
    //this will be the example of what we have to pass in url in order to add a new post to the api.
    //it states that user with userId 1 has created a post under category with categoryId 2

    public ResponseEntity<PostDataTransfer> createPost(@RequestBody PostDataTransfer postDataTransfer,
                                                       @PathVariable Integer userId, @PathVariable Integer categoryId)
    {
        PostDataTransfer createPost = this.postService.createPost(postDataTransfer,userId,categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    //GET ALL POSTS BY A USER
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDataTransfer>> getPostsByUser (@PathVariable Integer userId)
    {
        List<PostDataTransfer> postDataTransfers=this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(postDataTransfers,HttpStatus.OK);
    }

    //GET ALL POSTS BY A CATEGORY
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDataTransfer>> getPostsByCategory(@PathVariable Integer categoryId)
    {
        List<PostDataTransfer> postDataTransfers=this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postDataTransfers,HttpStatus.OK);
    }

    //GET ALL POSTS
    @GetMapping("/posts")
    public ResponseEntity<List<PostDataTransfer>> getAllPosts()
    {
        List<PostDataTransfer> postDataTransfers=this.postService.getAllPosts();
        return new ResponseEntity<>(postDataTransfers,HttpStatus.OK);
    }

    //GET POST BY ID
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDataTransfer> findPostById(@PathVariable Integer postId)
    {
        PostDataTransfer postDataTransfer=this.postService.getPostById(postId);
        return new ResponseEntity<>(postDataTransfer,HttpStatus.FOUND);
    }

    //DELETE POST
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId)
    {
        this.postService.deletePost(postId);
        return new ApiResponse("Post deleted successfully",true);
    }

    //UPDATE POST
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDataTransfer> updatePost(@RequestBody PostDataTransfer postDataTransfer,@PathVariable Integer postId)
    {
        PostDataTransfer updatedPost=this.postService.updatePost(postDataTransfer,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDataTransfer> updatePost(@RequestBody PostDataTransfer postDataTransfer,
                                                       @PathVariable Integer postId,
                                                       @PathVariable Integer userId,
                                                       @PathVariable Integer categoryId)
    {
        PostDataTransfer updatedPost=this.postService.updatePost(postDataTransfer,postId,userId,categoryId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }
}
