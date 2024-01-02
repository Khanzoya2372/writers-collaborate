package com.intproject.writerscollaborate.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

@Getter
@Setter
public class Poem {
    @Field(value = "poem_id")
    @JsonProperty("poem_id")
    private Integer poemId;

    @Field(value = "title")
    @JsonProperty("title")
    private String title;

    @Field(value = "language")
    @JsonProperty("language")
    private String language;

    @Field(value = "style")
    @JsonProperty("style")
    private String style;

    @Field(value = "analysis")
    @JsonProperty("analysis")
    private String analysis;

    public void setPoemId(Integer poemId) {
        this.poemId = poemId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}

