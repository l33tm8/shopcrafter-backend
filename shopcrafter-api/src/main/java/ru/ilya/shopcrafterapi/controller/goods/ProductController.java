package ru.ilya.shopcrafterapi.controller.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ilya.shopcraftercore.dto.goods.product.ProductDto;
import ru.ilya.shopcraftercore.dto.goods.product.PutProductDto;
import ru.ilya.shopcraftercore.service.goods.ProductService;

import java.util.List;

@RestController
@RequestMapping("/stores/{storeId}/categories/{categoryId}/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts(@PathVariable long storeId, @PathVariable long categoryId) {
        return productService.getAllProducts(storeId, categoryId);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable long storeId, @PathVariable long categoryId, @PathVariable long id) {
        return productService.getProductById(storeId, categoryId, id);
    }

    @PostMapping
    public ProductDto createProduct(@PathVariable long storeId, @PathVariable long categoryId, @RequestBody PutProductDto productDto) {
        return productService.createProduct(storeId, categoryId, productDto);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable long storeId, @PathVariable long categoryId, @PathVariable long id, @RequestBody PutProductDto productDto) {
        return productService.updateProduct(storeId, categoryId, id, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long storeId, @PathVariable long categoryId, @PathVariable long id) {
        productService.deleteProduct(storeId, categoryId, id);
    }


}
