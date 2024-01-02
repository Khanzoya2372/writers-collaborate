package com.intproject.writerscollaborate.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.*;
import java.util.*;

@Document(value = "Reviewer")
@Getter
@Setter
public class ReviewerEntity {
    @Id
    @JsonProperty("id")
    private Integer id;

    @Field(value = "r_name")
    @JsonProperty("r_name")
    private String rName;

    @Field(value = "rating")
    @JsonProperty("rating")
    private Integer rating;

    @Field(value = "projects_Reviewed")
    @JsonProperty("projects_Reviewed")
    private List<ProjectReviewed> projectsReviewed;

    @Field(value = "email")
    @JsonProperty("email")
    private String email;

    @Field(value = "language_Proficiency")
    @JsonProperty("language_Proficiency")
    private String languageProficiency;

    public void setId(String id) {
        this.id = Integer.valueOf(id);
    }

    public void setRName(String value) {
        this.rName = value;
    }

    public void setRating(int i) {
        this.rating = i;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public void setLanguageProficiency(String value) {
        this.languageProficiency = value;
    }

    public void setProjectsReviewed(List<ProjectReviewed> projectsReviewed) {
        this.projectsReviewed = projectsReviewed;
    }
}

