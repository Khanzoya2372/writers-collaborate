package com.intproject.writerscollaborate.repository;

import com.intproject.writerscollaborate.entity.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  authorRepository extends MongoRepository<AuthorEntity, String>, PagingAndSortingRepository<AuthorEntity, String> {
    List<AuthorEntity> findByAuthorName(String authorName);

    Page<AuthorEntity> findAll(Pageable pageable);
    // You can define custom query methods here if needed for Author entity
}


