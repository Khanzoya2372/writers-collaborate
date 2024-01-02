package com.intproject.writerscollaborate.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.*;
import java.util.*;

@Document(value = "Author")
@Getter
@Setter
public class AuthorEntity {
    @Id
    @JsonProperty("id")
    private String id;

    @Field(value = "author_name")
    @JsonProperty("author_name")
    private String authorName;

    @Field(value = "age")
    @JsonProperty("age")
    private Integer age;

    @Field(value = "email")
    @JsonProperty("email")
    private String email;

    @Field(value = "books")
    @JsonProperty("books")
    private List<Book> books;


    public void setId(String id) {
        this.id = id;
    }

    public void setAuthorName(String value) {
        this.authorName = value;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}