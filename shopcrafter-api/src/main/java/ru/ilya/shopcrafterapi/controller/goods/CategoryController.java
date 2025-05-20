package ru.ilya.shopcrafterapi.controller.goods;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ilya.shopcraftercore.dto.goods.category.CategoryDto;
import ru.ilya.shopcraftercore.dto.goods.category.PutCategoryDto;
import ru.ilya.shopcraftercore.service.goods.CategoryService;
import ru.ilya.shopcraftercore.service.MinioService;

import java.util.List;

@RestController
@RequestMapping("/stores/{storeId}/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final MinioService minioService;

    @Autowired
    public CategoryController(CategoryService categoryService, MinioService minioService) {
        this.categoryService = categoryService;
        this.minioService = minioService;
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

    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить картинку категории", description = "Загружает картинку в MinIO и сохраняет ссылку в Category")
    public CategoryDto uploadCategoryImage(@PathVariable long storeId,
                                         @PathVariable long id,
                                         @RequestParam("file") MultipartFile file) throws Exception {
        String imageUrl = minioService.uploadFile(file);
        return categoryService.updateCategoryImage(storeId, id, imageUrl);
    }
}
