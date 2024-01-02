package com.intproject.writerscollaborate.repository;

import com.intproject.writerscollaborate.entity.ReviewerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerRepository extends MongoRepository<ReviewerEntity, String> {
    // You can define custom query methods here if needed for ReviewerEntity
}
