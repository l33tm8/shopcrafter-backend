package ru.ilya.shopcraftercore.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilya.shopcraftercore.dto.goods.category.CategoryDto;
import ru.ilya.shopcraftercore.dto.goods.category.PutCategoryDto;
import ru.ilya.shopcraftercore.entity.goods.Category;
import ru.ilya.shopcraftercore.entity.goods.Store;
import ru.ilya.shopcraftercore.exception.EntityNotFoundException;
import ru.ilya.shopcraftercore.repository.goods.CategoryRepository;
import ru.ilya.shopcraftercore.repository.goods.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, StoreRepository storeRepository) {
        this.categoryRepository = categoryRepository;
        this.storeRepository = storeRepository;
    }

    public List<CategoryDto> getAllCategories(long storeId) {
        return categoryRepository.findByStoreId(storeId).stream()
                .map(CategoryDto::fromEntity)
                .toList();
    }

    public CategoryDto getCategory(long storeId, long id) {
        Category category = categoryRepository.findByStoreIdAndId(storeId, id);
        if (category == null) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        return CategoryDto.fromEntity(category);
    }

    @Transactional
    public CategoryDto createCategory(long storeId, PutCategoryDto putCategoryDto) {
        Optional<Store> storeOptional = storeRepository.findById(storeId);
        if (storeOptional.isEmpty()) {
            throw new EntityNotFoundException("Store not found");
        }
        Store store = storeOptional.get();
        Category category = new Category();
        category.setStore(store);
        category.setName(putCategoryDto.getName());
        store.addCategory(category);

        categoryRepository.save(category);
        storeRepository.save(store);
        return CategoryDto.fromEntity(category);
    }

    @Transactional
    public CategoryDto updateCategory(long storeId, long id, PutCategoryDto putCategoryDto) {
        Optional<Store> storeOptional = storeRepository.findById(storeId);
        if (storeOptional.isEmpty()) {
            throw new EntityNotFoundException("Store not found");
        }
        Category category = categoryRepository.findByStoreIdAndId(storeId, id);
        if (category != null) {
            category.setName(putCategoryDto.getName());
            categoryRepository.save(category);
            return CategoryDto.fromEntity(category);
        }
        throw new EntityNotFoundException("Category not found");
    }

    @Transactional
    public void deleteCategory(long storeId, long id) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new EntityNotFoundException("Store not found"));
        Category category = categoryRepository.findByStoreIdAndId(storeId, id);
        if (category == null)
            throw new EntityNotFoundException("Category not found");
        store.removeCategory(category);
        storeRepository.save(store);
        categoryRepository.deleteById(id);
    }

}
