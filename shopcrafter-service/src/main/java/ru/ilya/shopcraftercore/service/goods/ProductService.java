package ru.ilya.shopcraftercore.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.shopcraftercore.dto.goods.product.ProductDto;
import ru.ilya.shopcraftercore.dto.goods.product.PutProductDto;
import ru.ilya.shopcraftercore.entity.goods.Product;
import ru.ilya.shopcraftercore.repository.goods.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        return ((List<Product>) productRepository.findAll())
                .stream().map(ProductDto::fromEntity)
                .toList();
    }

    public ProductDto getProductById(long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return ProductDto.fromEntity(product);
    }

    public ProductDto createProduct(PutProductDto putProductDto) {
        Product product = new Product();
        product.setName(putProductDto.getName());
        product.setDescription(putProductDto.getDescription());
        productRepository.save(product);
        return ProductDto.fromEntity(product);
    }

    public ProductDto updateProduct(long id, PutProductDto putProductDto) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        product.setName(putProductDto.getName());
        product.setDescription(putProductDto.getDescription());
        productRepository.save(product);
        return ProductDto.fromEntity(product);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

}
