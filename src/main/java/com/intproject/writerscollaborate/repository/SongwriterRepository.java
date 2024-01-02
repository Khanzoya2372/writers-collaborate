package com.intproject.writerscollaborate.repository;

import com.intproject.writerscollaborate.entity.SongwriterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongwriterRepository extends MongoRepository<SongwriterEntity, String> {
    // You can define custom query methods here if needed for Songwriter
}
