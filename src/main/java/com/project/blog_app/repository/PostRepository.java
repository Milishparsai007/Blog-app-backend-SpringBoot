package com.project.blog_app.repository;

import com.project.blog_app.entities.Category;
import com.project.blog_app.entities.Post;
import com.project.blog_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
//    Spring Data JPA offers Method Name Query Derivation.
//    By adhering to a specific naming convention like findBy<Property>, we can automatically generate queries based on
//    the method name. For example, findByFirstName(String firstName) generates a query to find entities by their first name.

    List<Post> findByUser(User user); //this will return the list of posts by a particular user.
    List<Post> findByCategory(Category category); //this will return list of posts in a particular category.
}
