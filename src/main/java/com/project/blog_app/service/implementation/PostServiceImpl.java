package com.project.blog_app.service.implementation;

import com.project.blog_app.entities.Category;
import com.project.blog_app.entities.Post;
import com.project.blog_app.entities.User;
import com.project.blog_app.exception.ResourceNotFoundException;
import com.project.blog_app.payload.PostDataTransfer;
import com.project.blog_app.repository.CategoryRepository;
import com.project.blog_app.repository.PostRepository;
import com.project.blog_app.repository.UserRepository;
import com.project.blog_app.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDataTransfer createPost(PostDataTransfer postDataTransfer,Integer userId,Integer categoryId) {
        //hume yaha par user aur category ko lana pdega aur ye hum unki respective repositories ko fetch krke krenge.
        //user ko userrepository se lakr post ka user set krdiya hai and same for category.

        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));

        Post post = this.modelMapper.map(postDataTransfer,Post.class);

        post.setImg_name("default.png"); //default no need to send in the response request
        post.setAddedDate(new Date());  //auto generated no need to send in response request

        //the below data will be send in the response request
        post.setTitle(postDataTransfer.getTitle());
        post.setContent(postDataTransfer.getContent());
        //
        post.setUser(user);
        post.setCategory(category);

        Post newPost=this.postRepository.save(post);
        return this.modelMapper.map(newPost,PostDataTransfer.class);

    }

    @Override
    public PostDataTransfer updatePost(PostDataTransfer postDataTransfer, Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        post.setTitle(postDataTransfer.getTitle());
        post.setContent(postDataTransfer.getContent());
        Post updatedPost=this.postRepository.save(post);

        return this.modelMapper.map(updatedPost,PostDataTransfer.class);
    }

    @Override
    public PostDataTransfer updatePost(PostDataTransfer postDataTransfer, Integer postId, Integer userId, Integer categoryId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));

        post.setTitle(postDataTransfer.getTitle());
        post.setContent(postDataTransfer.getContent());
        post.setUser(user);
        post.setCategory(category);

        Post updatedPost=this.postRepository.save(post);

        return this.modelMapper.map(updatedPost,PostDataTransfer.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDataTransfer> getAllPosts()
    {
        List<Post> posts=this.postRepository.findAll();
        List<PostDataTransfer> postDataTransfers=posts.stream().map((post)->this.modelMapper.map(post,PostDataTransfer.class)).collect(Collectors.toList());

        return postDataTransfers;
    }

    @Override
    public PostDataTransfer getPostById(Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        return this.modelMapper.map(post,PostDataTransfer.class);
    }

    //get all posts in a category
    @Override
    public List<PostDataTransfer> getPostsByCategory(Integer categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        List<Post> posts=this.postRepository.findByCategory(category); //this is a customFinderMethod in postrepo which automatically generates queries for the method and gives data.

        List<PostDataTransfer> postDataTransfers=posts.stream().map((post) -> this.modelMapper.map(post,PostDataTransfer.class)).collect(Collectors.toList());
        //this will convert List of posts to List of postdatatransfers.
        return postDataTransfers;
    }

    //get all posts by a user
    @Override
    public List<PostDataTransfer> getPostsByUser(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
        List<Post> posts=this.postRepository.findByUser(user);
        List<PostDataTransfer> postDataTransfers=posts.stream().map((post) -> this.modelMapper.map(post,PostDataTransfer.class)).collect(Collectors.toList());
        return postDataTransfers;
    }
}
