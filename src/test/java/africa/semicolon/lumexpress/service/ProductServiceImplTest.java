package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.AddProductRequest;
import africa.semicolon.lumexpress.data.dto.request.GetAllItemsRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateProductRequest;
import africa.semicolon.lumexpress.data.dto.response.AddProductResponse;
import africa.semicolon.lumexpress.data.dto.response.UpdateProductResponse;
import africa.semicolon.lumexpress.data.models.Product;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;
    private AddProductRequest request;

    private AddProductResponse response;
    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths
                .get("/home/semicolon/Pictures/peak.jpg");

        MultipartFile file =
                new MockMultipartFile("peak",
                        Files.readAllBytes(path));
        request = buildAddProductRequest(file);
        response = productService.addProduct(request);
    }



    @Test
    void addProductTest(){
        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isGreaterThan(0L);
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getCode()).isEqualTo(201);
    }

    @Test
    void updateProductDetailsTest() {
        UpdateProductRequest updateRequest = buildUpdateRequest();
        UpdateProductResponse updateResponse =
                productService.updateProductDetails(updateRequest);
        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.getStatusCode())
                .isEqualTo(201);
    }

    @Test
    void getProductByIdTest() {
        Product foundProduct =
                productService.getProductById(response.getProductId());
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId())
                .isEqualTo(response.getProductId());
    }

    @Test
    void getAllProductsTest()  {
        var getItemsRequest = buildGetItemsRequest();
        Page<Product> productsPage = productService
                .getAllProducts(getItemsRequest);
        log.info("page contents:: {}", productsPage);
        assertThat(productsPage).isNotNull();
        assertThat(productsPage.getTotalElements())
                .isGreaterThan(0);
    }


    @Test
    void deleteProductTest() {
        assertThat(productService.deleteProduct(response.getProductId()));

    }

    private GetAllItemsRequest buildGetItemsRequest() {
        return GetAllItemsRequest
                .builder()
                .numberOfItemsPerPage(8)
                .pageNumber(1)
                .build();
    }

    private AddProductRequest buildAddProductRequest(MultipartFile file) {
        return AddProductRequest.builder()
                .name("Milk")
                .productCategory("Beverages")
                .price(BigDecimal.valueOf(30.00))
                .quantity(10)
                .image(file)
                .build();
    }

    private UpdateProductRequest buildUpdateRequest() {
        return UpdateProductRequest.builder()
                .price(BigDecimal.valueOf(40.00))
                .productId(1L)
                .description("its just milo")
                .quantity(10)
                .build();
    }
}