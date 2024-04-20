package org.geekhub.kukotin.coursework.controllers.category;

import org.geekhub.kukotin.coursework.service.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.geekhub.kukotin.coursework.controllers.category.CategoryConverter.fromDto;
import static org.geekhub.kukotin.coursework.controllers.category.CategoryConverter.toDtos;


@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addCategory(@RequestBody CategoryDTO categoryDTO) {
        service.create(fromDto(categoryDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public void updateCategory(@RequestBody CategoryDTO categoryDTO) {
        service.update(fromDto(categoryDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void removeCategory(@PathVariable int id) {
        CategoryDTO dto = new CategoryDTO(id, "");
        service.remove(fromDto(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(toDtos(service.getAll()));
    }
}
