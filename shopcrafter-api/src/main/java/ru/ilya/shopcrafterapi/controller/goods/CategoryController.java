package ru.ilya.shopcrafterapi.controller.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ilya.shopcraftercore.dto.goods.category.CategoryDto;
import ru.ilya.shopcraftercore.dto.goods.category.PutCategoryDto;
import ru.ilya.shopcraftercore.service.goods.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/stores/{storeId}/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public List<CategoryDto> getStoreCategories(@PathVariable long storeId) {
        return categoryService.getAllCategories(storeId);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable long storeId, @PathVariable long id) {
        return categoryService.getCategory(storeId, id);
    }

    @PostMapping
    public CategoryDto createCategory(@PathVariable long storeId, @RequestBody PutCategoryDto categoryDto) {
        return categoryService.createCategory(storeId, categoryDto);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable long storeId, @PathVariable long id, @RequestBody PutCategoryDto categoryDto) {
        return categoryService.updateCategory(storeId, id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable long storeId, @PathVariable long id) {
        categoryService.deleteCategory(storeId, id);
    }
}
