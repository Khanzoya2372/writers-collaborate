package com.intproject.writerscollaborate.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.*;
import java.util.*;

@Document(value = "Poet")
@Getter
@Setter
public class PoetEntity {
    @Id
    @JsonProperty("id")
    private String id;

    @Field(value = "poet_name")
    @JsonProperty("poet_name")
    private String poetName;

    @Field(value = "age")
    @JsonProperty("age")
    private Integer age;

    @Field(value = "email")
    @JsonProperty("email")
    private String email;

    @Field(value = "poems")
    @JsonProperty("poems")
    private List<Poem> poems;

    public void setId(String id) {
        this.id = id;
    }

    public void setPoetName(String poetName) {
        this.poetName = poetName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPoems(List<Poem> poems) {
        this.poems = poems;
    }
}
