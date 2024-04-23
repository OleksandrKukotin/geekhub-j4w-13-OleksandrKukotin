package org.geekhub.kukotin.coursework.controllers.author;

import org.geekhub.kukotin.coursework.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.geekhub.kukotin.coursework.controllers.author.AuthorConverter.fromDto;
import static org.geekhub.kukotin.coursework.controllers.author.AuthorConverter.toDto;
import static org.geekhub.kukotin.coursework.controllers.author.AuthorConverter.toDtos;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createAuthor(@RequestBody AuthorDTO dto) {
        authorService.createAuthor(fromDto(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable int id) {
        return new ResponseEntity<>(toDto(authorService.getAuthorById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return new ResponseEntity<>(toDtos(authorService.getAllAuthors(1,10)), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public void updateAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.updateAuthor(fromDto(authorDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping()
    public void deleteAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.deleteAuthor(fromDto(authorDTO));
    }
}
