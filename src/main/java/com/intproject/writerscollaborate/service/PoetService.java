package com.intproject.writerscollaborate.service;

import com.intproject.writerscollaborate.entity.PoetEntity;
import com.intproject.writerscollaborate.repository.PoetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoetService {

    private final Logger logger = LogManager.getLogger(PoetService.class);

    @Autowired
    private PoetRepository poetRepository;

    public PoetEntity createPoet(PoetEntity poet) {
        return poetRepository.save(poet);
    }

    public Optional<PoetEntity> getPoetById(String id) {
        return poetRepository.findById(id);
    }

    public PoetEntity updatePoet(String id, PoetEntity updatedPoet) {
        Optional<PoetEntity> existingPoetOptional = poetRepository.findById(id);
        if (existingPoetOptional.isPresent()) {
            updatedPoet.setId(id);
            return poetRepository.save(updatedPoet);
        }
        return null;
    }

    public boolean deletePoet(String id) {
        if (poetRepository.existsById(id)) {
            poetRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void createMultiplePoets(List<PoetEntity> poets) {
        poetRepository.saveAll(poets);
    }
}

