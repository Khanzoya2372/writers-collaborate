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
    private String authorName;

    @Field(value = "age")
    private Integer age;

    @Field(value = "email")
    private String email;

    @Field(value = "books")
    private List<Book> books;

}