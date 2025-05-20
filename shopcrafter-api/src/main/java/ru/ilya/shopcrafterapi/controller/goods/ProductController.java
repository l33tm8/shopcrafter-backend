package ru.ilya.shopcrafterapi.controller.goods;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ilya.shopcraftercore.dto.goods.product.ProductDto;
import ru.ilya.shopcraftercore.dto.goods.product.PutProductDto;
import ru.ilya.shopcraftercore.service.goods.ProductService;
import ru.ilya.shopcraftercore.service.MinioService;

import java.util.List;

@RestController
@RequestMapping("/stores/{storeId}/categories/{categoryId}/products")
public class ProductController {

    private final ProductService productService;
    private final MinioService minioService;

    @Autowired
    public ProductController(ProductService productService, MinioService minioService) {
        this.productService = productService;
        this.minioService = minioService;
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

    @PostMapping(value = "/{id}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавить картинки товара", description = "Загружает картинки в MinIO и добавляет ссылки в Product")
    public ProductDto addProductImages(@PathVariable long storeId,
                                    @PathVariable long categoryId,
                                    @PathVariable long id,
                                    @Parameter(description = "Файлы изображений", required = true)
                                    @RequestParam("files") MultipartFile[] files) throws Exception {
        ProductDto product = null;
        for (MultipartFile file : files) {
            String imageUrl = minioService.uploadFile(file);
            product = productService.updateProductImage(storeId, categoryId, id, imageUrl);
        }
        return product;
    }

    @DeleteMapping("/{id}/images")
    @Operation(summary = "Удалить картинку товара", description = "Удаляет ссылку на картинку из Product")
    public ProductDto deleteProductImage(@PathVariable long storeId,
                                      @PathVariable long categoryId,
                                      @PathVariable long id,
                                      @Parameter(description = "URL изображения для удаления", required = true)
                                      @RequestParam("imageUrl") String imageUrl) {
        return productService.deleteProductImage(storeId, categoryId, id, imageUrl);
    }
}
