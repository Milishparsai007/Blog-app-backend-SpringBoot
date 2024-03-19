package com.project.blog_app.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id",nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;
    private String email;
    @Column(nullable = false)
    private String password;
    private String about;

    //ek user ki multiple posts ho skti hai isliye One to many relationship lgai hai.
    //mapped by me hume wo object rkhna hai jisse ye relationship link ho rhi hai i.e., Post wali class me jo hmara User ka object
    //hai uska reference rkhna hai yaha par.

    //Cascading :- Entity relationships often depend on the existence of another entity,
    // for example the Person–Address relationship. Without the Person, the Address entity doesn’t have any meaning of its own.
    // When we delete the Person entity, our Address entity should also get deleted. Cascading is a way to achieve this.

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();

    //constructors

    public User() {
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    //getter and settter

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAbout() {
        return about;
    }

    //setter

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
