package com.intproject.writerscollaborate.service;

import com.intproject.writerscollaborate.entity.AuthorEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private Logger logger = LogManager.getLogger(AuthorService.class);

    @Autowired
    private com.intproject.writerscollaborate.repository.authorRepository authorRepository;

    public AuthorEntity createAuthor(AuthorEntity authors) {
        return authorRepository.save(authors);
    }

    public Optional<AuthorEntity> getAuthorById(String id) {
        return authorRepository.findById(id);
    }

    public AuthorEntity updateAuthor(String id, AuthorEntity updatedAuthor) {
        Optional<AuthorEntity> existingAuthorOptional = authorRepository.findById(id);
        if (existingAuthorOptional.isPresent()) {
            updatedAuthor.setId(id);
            return authorRepository.save(updatedAuthor);
        }
        return null;
    }

    public boolean deleteAuthor(String id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void createMultipleAuthors(List<AuthorEntity> authors) {
        authorRepository.saveAll(authors);
    }

    public List<AuthorEntity> getAuthorsByName(String authorName) {
        return authorRepository.findByAuthorName(authorName);
    }

    public Page<AuthorEntity> getAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

}



