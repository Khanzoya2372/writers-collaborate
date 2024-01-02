package com.intproject.writerscollaborate.repository;

import com.intproject.writerscollaborate.entity.PoetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoetRepository extends MongoRepository<PoetEntity, String> {
    // You can define custom query methods here if needed for PoetEntity
}

