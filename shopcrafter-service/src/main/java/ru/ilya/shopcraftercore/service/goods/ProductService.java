package ru.ilya.shopcraftercore.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilya.shopcraftercore.dto.goods.product.ProductDto;
import ru.ilya.shopcraftercore.dto.goods.product.PutProductDto;
import ru.ilya.shopcraftercore.entity.goods.Category;
import ru.ilya.shopcraftercore.entity.goods.Product;
import ru.ilya.shopcraftercore.repository.goods.CategoryRepository;
import ru.ilya.shopcraftercore.repository.goods.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDto> getAllProducts(long storeId, long categoryId) {
        Category category = categoryRepository.findByStoreIdAndId(storeId, categoryId);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        List<Product> products = productRepository.findByCategoryId(categoryId);
        if (products == null) {
            return null;
        }
        return products.stream().map(ProductDto::fromEntity).toList();
    }

    public ProductDto getProductById(long storeId, long categoryId, long id) {
        Category category = categoryRepository.findByStoreIdAndId(storeId, categoryId);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        Product product = productRepository.findByCategoryIdAndId(categoryId, id);
        if (product == null) {
            return null;
        }
        return ProductDto.fromEntity(product);
    }

    @Transactional
    public ProductDto createProduct(long storeId, long categoryId, PutProductDto putProductDto) {
        Category category = categoryRepository.findByStoreIdAndId(storeId, categoryId);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        Product product = new Product();
        makeProduct(putProductDto, product, category);
        productRepository.save(product);
        categoryRepository.save(category);
        return ProductDto.fromEntity(product);
    }

    @Transactional
    public ProductDto updateProduct(long storeId, long categoryId, long id, PutProductDto putProductDto) {
        Category category = categoryRepository.findByStoreIdAndId(storeId, categoryId);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        Product product = productRepository.findByCategoryIdAndId(categoryId, id);
        if (product == null) {
            return null;
        }
        makeProduct(putProductDto, product, category);
        productRepository.save(product);
        return ProductDto.fromEntity(product);
    }

    @Transactional
    public void deleteProduct(long StoreId, long CategoryId, long id) {
        Category category = categoryRepository.findByStoreIdAndId(StoreId, CategoryId);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        Product product = productRepository.findByCategoryIdAndId(category.getId(), id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        category.getProducts().remove(product);
        categoryRepository.save(category);
        productRepository.deleteById(id);
    }

    private void makeProduct(PutProductDto putProductDto, Product product, Category category) {
        product.setName(putProductDto.getName());
        product.setCategory(category);
        product.setPrice(putProductDto.getPrice());
        product.setDescription(putProductDto.getDescription());
        product.setStock(putProductDto.getStock());
        category.getProducts().add(product);
        product.setDescription(putProductDto.getDescription());
    }

}
