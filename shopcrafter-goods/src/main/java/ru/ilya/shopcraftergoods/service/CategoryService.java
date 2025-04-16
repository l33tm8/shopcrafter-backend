package ru.ilya.shopcraftergoods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilya.shopcraftergoods.dto.category.CategoryDto;
import ru.ilya.shopcraftergoods.dto.category.PutCategoryDto;
import ru.ilya.shopcraftergoods.entity.Category;
import ru.ilya.shopcraftergoods.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> getAllCategories() {
        return ((List<Category>) categoryRepository.findAll())
                .stream().map(CategoryDto::fromEntity)
                .toList();
    }

    @Transactional
    public CategoryDto createCategory(PutCategoryDto putCategoryDto) {
        Category category = new Category();
        category.setName(putCategoryDto.getName());
        categoryRepository.save(category);
        return CategoryDto.fromEntity(category);
    }

    @Transactional
    public CategoryDto updateCategory(long id, PutCategoryDto putCategoryDto) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setName(putCategoryDto.getName());
            categoryRepository.save(category);
            return CategoryDto.fromEntity(category);
        }
        return null;
    }

    @Transactional
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

}
