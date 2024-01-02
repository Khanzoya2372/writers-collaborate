package com.intproject.writerscollaborate.service;

import com.intproject.writerscollaborate.entity.SongwriterEntity;
import com.intproject.writerscollaborate.repository.SongwriterRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongwriterService {

    private final Logger logger = LogManager.getLogger(SongwriterService.class);

    @Autowired
    private SongwriterRepository songwriterRepository;

    public SongwriterEntity createSongwriter(SongwriterEntity songwriter) {
        return songwriterRepository.save(songwriter);
    }

    public Optional<SongwriterEntity> getSongwriterById(String id) {
        return songwriterRepository.findById(id);
    }

    public SongwriterEntity updateSongwriter(String id, SongwriterEntity updatedSongwriter) {
        Optional<SongwriterEntity> existingSongwriterOptional = songwriterRepository.findById(id);
        if (existingSongwriterOptional.isPresent()) {
            updatedSongwriter.setId(id);
            return songwriterRepository.save(updatedSongwriter);
        }
        return null;
    }

    public boolean deleteSongwriter(String id) {
        if (songwriterRepository.existsById(id)) {
            songwriterRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void createMultipleSongwriters(List<SongwriterEntity> songwriters) {
        songwriterRepository.saveAll(songwriters);
    }
}



