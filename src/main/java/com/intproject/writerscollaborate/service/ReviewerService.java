package com.intproject.writerscollaborate.service;

import com.intproject.writerscollaborate.entity.ReviewerEntity;
import com.intproject.writerscollaborate.repository.ReviewerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ReviewerService {

    private final Logger logger = LogManager.getLogger(ReviewerService.class);

    @Autowired
    private ReviewerRepository reviewerRepository;

    public ReviewerEntity createReviewer(ReviewerEntity reviewer) {
        return reviewerRepository.save(reviewer);
    }

    public Optional<ReviewerEntity> getReviewerById(String id) {
        return reviewerRepository.findById(id);
    }

    public ReviewerEntity updateReviewer(String id, ReviewerEntity updatedReviewer) {
        Optional<ReviewerEntity> existingReviewerOptional = reviewerRepository.findById(id);
        if (existingReviewerOptional.isPresent()) {
            updatedReviewer.setId(id);
            return reviewerRepository.save(updatedReviewer);
        }
        return null;
    }

    public boolean deleteReviewer(String id) {
        if (reviewerRepository.existsById(id)) {
            reviewerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void createMultipleReviewers(List<ReviewerEntity> reviewers) {
        reviewerRepository.saveAll(reviewers);
    }
}
