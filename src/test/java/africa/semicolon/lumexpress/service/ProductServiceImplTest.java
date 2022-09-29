package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.AddProductRequest;
import africa.semicolon.lumexpress.data.dto.response.AddProductResponse;
import africa.semicolon.lumexpress.data.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;
    private AddProductRequest request;

    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths
                .get("/home/semicolon/Pictures/peak.jpg");

        MultipartFile file =
                new MockMultipartFile("peak",
                        Files.readAllBytes(path));
        request = AddProductRequest.builder()
                .name("Milk")
                .productCategory("Beverages")
                .price(BigDecimal.valueOf(30.00))
                .quantity(10)
                .image(file)
                .build();
    }

    @Test
    void createProductTest() throws IOException {
        AddProductResponse response =
                productService.addProduct(request);
        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isGreaterThan(0L);
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getCode()).isEqualTo(201);
    }

    @Test
    void updateProductDetailsTest() {
    }

    @Test
    void getProductByIdTest() throws IOException {
        AddProductResponse response =
                productService.addProduct(request);
        Product foundProduct =
                productService.getProductById(response.getProductId());
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId())
                .isEqualTo(response.getProductId());
    }

    @Test
    void getAllProductsTest() throws IOException {
        productService.addProduct(request);
        Page<Product> productsPage = productService.getAllProducts();
        assertThat(productsPage).isNotNull();
        assertThat(productsPage.getTotalElements())
                .isGreaterThan(0);
    }

    @Test
    void deleteProductTest() {
    }
}