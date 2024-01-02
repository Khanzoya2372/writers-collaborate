package com.intproject.writerscollaborate.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.*;
import java.util.*;


@Document(value = "Songwriter")
@Getter
@Setter
public class SongwriterEntity {
    @Id
    @JsonProperty("id")
    private String id;

    @Field(value = "songwriter_name")
    @JsonProperty("songwriter_name")
    private String songwriterName;

    @Field(value = "age")
    @JsonProperty("age")
    private Integer age;

    @Field(value = "email")
    @JsonProperty("email")
    private String email;

    @Field(value = "songs")
    @JsonProperty("songs")
    private List<Song> songs;

    public void setId(String id) {
        this.id = id;
    }

    public void setSongwriterName(String songwriterName) {
        this.songwriterName = songwriterName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}


