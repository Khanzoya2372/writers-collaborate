package com.intproject.writerscollaborate.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.*;

@Getter
@Setter
public class Book {

    @Field(value = "book_id")
    private String bookId;

    @Field(value = "book_name")
    private String bookName;

    @Field(value = "genre")
    private String genre;

    @Field(value = "characters")
    private List<String> characters;

    @Field(value = "chapter_names")
    private List<String> chapterNames;

    @Field(value = "new_ideas")
    private List<String> newIdeas;

}

