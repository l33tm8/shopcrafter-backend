package ru.ilya.shopcraftergoods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.shopcraftergoods.dto.product.ProductDto;
import ru.ilya.shopcraftergoods.dto.product.PutProductDto;
import ru.ilya.shopcraftergoods.entity.Product;
import ru.ilya.shopcraftergoods.repository.ProductRepository;

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

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

}
