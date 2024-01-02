package com.intproject.writerscollaborate.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

@Getter
@Setter
public class Book {
    @Setter
    @Field(value = "book_id")
    @JsonProperty("book_id")
    private String bookId;

    @Setter
    @Field(value = "book_name")
    @JsonProperty("book_name")
    private String bookName;

    @Setter
    @Field(value = "genre")
    @JsonProperty("genre")
    private String genre;

    @Setter
    @Field(value = "characters")
    @JsonProperty("characters")
    private List<String> characters;

    @Setter
    @Field(value = "chapter_names")
    @JsonProperty("chapter_names")
    private List<String> chapterNames;

    @Setter
    @Field(value = "new_ideas")
    @JsonProperty("new_ideas")
    private List<String> newIdeas;

}

