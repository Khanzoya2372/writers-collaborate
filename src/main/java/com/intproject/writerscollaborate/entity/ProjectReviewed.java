package com.intproject.writerscollaborate.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

@Getter
@Setter
public class ProjectReviewed {
    @Field(value = "project_id")
    @JsonProperty("project_id")
    private Integer projectId;

    @Field(value = "project_name")
    @JsonProperty("project_name")
    private String projectName;

    @Field(value = "review")
    @JsonProperty("review")
    private String review;

    @Field(value = "p_Rating")
    @JsonProperty("p_Rating")
    private Integer pRating;

    @Field(value = "language")
    @JsonProperty("language")
    private String language;

    public void setProjectId(int i) {
        this.projectId = i;
    }

    public void setProjectName(String value) {
        this.projectName = value;
    }

    public void setReview(String value) {
        this.review = value;
    }

    public void setPRating(int i) {
        this.pRating = i;
    }

    public void setLanguage(String value) {
        this.language = value;
    }
}

