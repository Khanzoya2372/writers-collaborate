package com.intproject.writerscollaborate.controller;

import com.intproject.writerscollaborate.entity.ProjectReviewed;
import com.intproject.writerscollaborate.service.ReviewerService;
import com.intproject.writerscollaborate.entity.ReviewerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviewers")
public class ReviewerController {

    @Autowired
    private ReviewerService reviewerService;

    @PostMapping("/create")
    public ResponseEntity<ReviewerEntity> createReviewer(@RequestBody ReviewerEntity reviewer) {
        ReviewerEntity createdReviewer = reviewerService.createReviewer(reviewer);
        return new ResponseEntity<>(createdReviewer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewerEntity> getReviewerById(@PathVariable String id) {
        Optional<ReviewerEntity> reviewerOptional = reviewerService.getReviewerById(id);
        return reviewerOptional.map(reviewer -> new ResponseEntity<>(reviewer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewerEntity> updateReviewer(@PathVariable String id, @RequestBody ReviewerEntity updatedReviewer) {
        ReviewerEntity updatedEntity = reviewerService.updateReviewer(id, updatedReviewer);
        if (updatedEntity != null) {
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewer(@PathVariable String id) {
        boolean deleted = reviewerService.deleteReviewer(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-multiple")
    public ResponseEntity<Void> createMultipleReviewers(@RequestBody List<ReviewerEntity> reviewers) {
        reviewerService.createMultipleReviewers(reviewers);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/upload-reviewers")
    public ResponseEntity<String> uploadReviewers(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload a CSV file.", HttpStatus.BAD_REQUEST);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<ReviewerEntity> reviewers = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                ReviewerEntity reviewer = new ReviewerEntity();
                reviewer.setRName(values[0]);
                reviewer.setRating(Integer.parseInt(values[1]));
                reviewer.setEmail(values[2]);
                reviewer.setLanguageProficiency(values[3]);

                List<ProjectReviewed> projectsReviewed = new ArrayList<>();
                for (int i = 2; i < values.length; i += 5) {
                    ProjectReviewed projectReviewed = new ProjectReviewed();
                    projectReviewed.setProjectId(Integer.parseInt(values[i]));
                    projectReviewed.setProjectName(values[i + 1]);
                    projectReviewed.setReview(values[i + 2]);
                    projectReviewed.setPRating(Integer.parseInt(values[i + 3]));
                    projectReviewed.setLanguage(values[i + 4]);

                    projectsReviewed.add(projectReviewed);
                }
                reviewer.setProjectsReviewed(projectsReviewed);
                reviewers.add(reviewer);
            }

            reviewerService.createMultipleReviewers(reviewers);

            return new ResponseEntity<>("Reviewers uploaded successfully.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to process the CSV file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



