package com.project.blog_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    @Column(name = "Title",length = 50,nullable = false)
    private String categoryTitle;
    @Column(name = "Description",length = 100,nullable = false)
    private String categoryDesc;


    //Cascading :- Entity relationships often depend on the existence of another entity,
    // for example the Person–Address relationship. Without the Person, the Address entity doesn’t have any meaning of its own.
    // When we delete the Person entity, our Address entity should also get deleted. Cascading is a way to achieve this.

    //One to many isliye lgai hai kyoki ek category ke andar multiple posts aa skti hai.
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();
}
