package com.intproject.writerscollaborate.controller;

import com.intproject.writerscollaborate.entity.Poem;
import com.intproject.writerscollaborate.service.PoetService;
import com.intproject.writerscollaborate.entity.PoetEntity;
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
@RequestMapping("/poets")
public class PoetController {

    @Autowired
    private PoetService poetService;

    @PostMapping("/create")
    public ResponseEntity<PoetEntity> createPoet(@RequestBody PoetEntity poet) {
        PoetEntity createdPoet = poetService.createPoet(poet);
        return new ResponseEntity<>(createdPoet, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoetEntity> getPoetById(@PathVariable String id) {
        Optional<PoetEntity> poetOptional = poetService.getPoetById(id);
        return poetOptional.map(poet -> new ResponseEntity<>(poet, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoetEntity> updatePoet(@PathVariable String id, @RequestBody PoetEntity updatedPoet) {
        PoetEntity updatedEntity = poetService.updatePoet(id, updatedPoet);
        if (updatedEntity != null) {
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoet(@PathVariable String id) {
        boolean deleted = poetService.deletePoet(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-multiple")
    public ResponseEntity<Void> createMultiplePoets(@RequestBody List<PoetEntity> poets) {
        poetService.createMultiplePoets(poets);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/upload-poets")
    public ResponseEntity<String> uploadPoets(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload a CSV file.", HttpStatus.BAD_REQUEST);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<PoetEntity> poets = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                PoetEntity poet = new PoetEntity();
                poet.setPoetName(values[0]);
                poet.setAge(Integer.parseInt(values[1]));
                poet.setEmail(values[2]);

                List<Poem> poems = new ArrayList<>();
                for (int i = 3; i < values.length; i += 5) {
                    Poem poem = new Poem();
                    poem.setPoemId(Integer.parseInt(values[i]));
                    poem.setTitle(values[i + 1]);
                    poem.setLanguage(values[i + 2]);
                    poem.setStyle(values[i + 3]);
                    poem.setAnalysis(values[i + 4]);

                    poems.add(poem);
                }
                poet.setPoems(poems);
                poets.add(poet);
            }

            poetService.createMultiplePoets(poets);

            return new ResponseEntity<>("Poets uploaded successfully.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to process the CSV file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

