package com.intproject.writerscollaborate.controller;

import com.intproject.writerscollaborate.entity.Song;
import com.intproject.writerscollaborate.service.SongwriterService;
import com.intproject.writerscollaborate.entity.SongwriterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@RestController
@RequestMapping("/songwriters")
public class SongwriterController {

    @Autowired
    private SongwriterService songwriterService;

    @PostMapping("/create")
    public ResponseEntity<SongwriterEntity> createSongwriter(@RequestBody SongwriterEntity songwriter) {
        SongwriterEntity createdSongwriter = songwriterService.createSongwriter(songwriter);
        return new ResponseEntity<>(createdSongwriter, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongwriterEntity> getSongwriterById(@PathVariable String id) {
        Optional<SongwriterEntity> songwriterOptional = songwriterService.getSongwriterById(id);
        return songwriterOptional.map(songwriter -> new ResponseEntity<>(songwriter, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongwriterEntity> updateSongwriter(@PathVariable String id, @RequestBody SongwriterEntity updatedSongwriter) {
        SongwriterEntity updatedEntity = songwriterService.updateSongwriter(id, updatedSongwriter);
        if (updatedEntity != null) {
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSongwriter(@PathVariable String id) {
        boolean deleted = songwriterService.deleteSongwriter(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-multiple")
    public ResponseEntity<Void> createMultipleSongwriters(@RequestBody List<SongwriterEntity> songwriters) {
        songwriterService.createMultipleSongwriters(songwriters);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/upload-songwriters")
    public ResponseEntity<String> uploadSongwriters(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload a CSV file.", HttpStatus.BAD_REQUEST);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<SongwriterEntity> songwriters = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                SongwriterEntity songwriter = new SongwriterEntity();
                songwriter.setSongwriterName(values[0]);
                songwriter.setAge(Integer.parseInt(values[1]));
                songwriter.setEmail(values[2]);

                List<Song> songs = new ArrayList<>();
                for (int i = 3; i + 6 < values.length; i += 7) {
                    Song song = new Song();
                    song.setSongId(Integer.parseInt(values[i]));
                    song.setSongName(values[i + 1]);
                    song.setGenre(Arrays.asList(values[i + 2].split(";")));
                    song.setLyrics(Arrays.asList(values[i + 3].split(";")));
                    song.setComposer(Arrays.asList(values[i + 4].split(";")));
                    song.setDuration(Integer.parseInt(values[i + 5]));
                    song.setLanguage(values[i + 6]);

                    songs.add(song);
                }
                songwriter.setSongs(songs);
                songwriters.add(songwriter);
            }

            songwriterService.createMultipleSongwriters(songwriters);

            return new ResponseEntity<>("Songwriters uploaded successfully.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to process the CSV file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



