package com.intproject.writerscollaborate.controller;

import com.intproject.writerscollaborate.service.AuthorService;
import com.intproject.writerscollaborate.entity.Book;
import com.intproject.writerscollaborate.entity.AuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/authors/create")
    public ResponseEntity<AuthorEntity> createAuthor(@RequestBody AuthorEntity author) {
        AuthorEntity createdAuthor = authorService.createAuthor(author);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @PostMapping("/authors/create-with-books")
    public ResponseEntity<AuthorEntity> createAuthorWithBooks(@RequestBody AuthorEntity request) {

        String authorName = request.getAuthorName();
        int age = request.getAge();
        String email = request.getEmail();

        List<Book> bookRequests = request.getBooks();
        List<Book> books = new ArrayList<>();

        for (Book bookRequest : bookRequests) {
            Book book = new Book();
            book.setBookName(bookRequest.getBookName());
            book.setGenre(bookRequest.getGenre());
            book.setCharacters(bookRequest.getCharacters());
            book.setChapterNames(bookRequest.getChapterNames());
            book.setNewIdeas(bookRequest.getNewIdeas());
            books.add(book);
        }

        AuthorEntity author = new AuthorEntity();
        author.setAuthorName(authorName);
        author.setAge(age);
        author.setEmail(email);
        author.setBooks(books);

        AuthorEntity createdAuthor = authorService.createAuthor(author);

        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }


    @GetMapping("/authors/getById/{id}")
    public ResponseEntity<AuthorEntity> getAuthorById(@PathVariable String id) {
        Optional<AuthorEntity> authorOptional = authorService.getAuthorById(id);
        return authorOptional.map(author -> new ResponseEntity<>(author, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/authors/getByName/{authorName}")
    public ResponseEntity<List<AuthorEntity>> getAuthorsByName(@PathVariable String authorName) {
        List<AuthorEntity> authors = authorService.getAuthorsByName(authorName);
        if (authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(authors, HttpStatus.OK);
        }
    }


    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorEntity> updateAuthor(@PathVariable String id, @RequestBody AuthorEntity updatedAuthor) {
        AuthorEntity updatedEntity = authorService.updateAuthor(id, updatedAuthor);
        if (updatedEntity != null) {
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String id) {
        boolean deleted = authorService.deleteAuthor(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/authors/create-multiple")
    public ResponseEntity<Void> createMultipleAuthors(@RequestBody List<AuthorEntity> authors) {
        authorService.createMultipleAuthors(authors);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/authors/upload-authors")
    public ResponseEntity<String> uploadAuthors(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload a CSV file.", HttpStatus.BAD_REQUEST);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<AuthorEntity> authors = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                AuthorEntity author = new AuthorEntity();
                author.setAuthorName(values[0]);
                author.setAge(Integer.parseInt(values[1]));
                author.setEmail(values[2]);

                List<Book> books = new ArrayList<>();
                for (int i = 3; i < values.length; i += 6) {
                    Book book = new Book();
                    book.setBookId(String.valueOf(Integer.parseInt(values[i])));
                    book.setBookName(values[i + 1]);
                    book.setGenre(values[i + 2]);
                    book.setCharacters(Arrays.asList(values[i + 3].split(",")));
                    book.setChapterNames(Arrays.asList(values[i + 4].split(",")));
                    book.setNewIdeas(Arrays.asList(values[i + 5].split(",")));

                    books.add(book);
                }

                author.setBooks(books);
                authors.add(author);
            }

            authorService.createMultipleAuthors(authors);

            return new ResponseEntity<>("Authors uploaded successfully.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to process the CSV file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}