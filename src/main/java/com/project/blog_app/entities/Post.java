package com.project.blog_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.util.Date;


@Entity
@Table(name = "Posts")
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name = "Post Title",length = 100,nullable = false)
    private String title;
    @Column(name = "Content",length = 10000)
    private String content;
    private String img_name;
    private Date addedDate;


    //many posts can be under one category
    @ManyToOne
    @JoinColumn(name = "Catrgory_Id") //this is used to change the name of foreign key in the table.
    private Category category;

    //many posts can be posted by one user.
    @ManyToOne
    @JoinColumn(name = "User_Id ")
    private User user;
    //Interesting chiz hai ki hum tb tk ek post ko add nahi kr skte jab tb ki hume uski category nahi pta and user nahi pta that means
    //ki ek post ki category and user dono add krna pdega to add the post.

    //jo reference variable yaha par le rahe ho User aur Category ke liye wahi wala use krna UserDataTransfer ke andar bhi.

}
