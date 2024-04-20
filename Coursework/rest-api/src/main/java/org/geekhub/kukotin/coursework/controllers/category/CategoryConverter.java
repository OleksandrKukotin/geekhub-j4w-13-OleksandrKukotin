package org.geekhub.kukotin.coursework.controllers.category;

import org.geekhub.kukotin.coursework.service.category.Category;

import java.util.List;

public class CategoryConverter {

    private CategoryConverter() {
    }

    public static CategoryDTO toDto(Category entity) {
        return new CategoryDTO(entity.categoryId(), entity.categoryName());
    }

    public static List<CategoryDTO> toDtos(List<Category> entities) {
        return entities.stream()
            .map(CategoryConverter::toDto)
            .toList();
    }

    public static Category fromDto(CategoryDTO dto) {
        return new Category(dto.categoryId(), dto.categoryName());
    }
}
