package com.intproject.writerscollaborate.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

@Getter
@Setter
public class Song {
    @Field(value = "song_id")
    @JsonProperty("song_id")
    private Integer songId;

    @Field(value = "song_name")
    @JsonProperty("song_name")
    private String songName;

    @Field(value = "genre")
    @JsonProperty("genre")
    private List<String> genre;

    @Field(value = "lyrics")
    @JsonProperty("lyrics")
    private List<String> lyrics;

    @Field(value = "composer")
    @JsonProperty("composer")
    private List<String> composer;

    @Field(value = "duration")
    @JsonProperty("duration")
    private Integer duration;

    @Field(value = "language")
    @JsonProperty("language")
    private String language;

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public void setLyrics(List<String> lyrics) {
        this.lyrics = lyrics;
    }

    public void setComposer(List<String> composer) {
        this.composer = composer;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
