package com.ccsw.tutorial1.game.model;

import com.ccsw.tutorial1.author.model.AuthorDto;
import com.ccsw.tutorial1.category.model.CategoryDto;


public class GameDto {

    private Long id;

    private String title;

    private String age;

    private CategoryDto category;

    private AuthorDto author;

    public Long getId() {

        return this.id;
    }


    public void setId(Long id) {

        this.id = id;
    }


    public String getTitle() {

        return this.title;
    }


    public void setTitle(String title) {

        this.title = title;
    }


    public String getAge() {

        return this.age;
    }


    public void setAge(String age) {

        this.age = age;
    }


    public CategoryDto getCategory() {

        return this.category;
    }


    public void setCategory(CategoryDto category) {

        this.category = category;
    }


    public AuthorDto getAuthor() {

        return this.author;
    }


    public void setAuthor(AuthorDto author) {

        this.author = author;
    }

}
