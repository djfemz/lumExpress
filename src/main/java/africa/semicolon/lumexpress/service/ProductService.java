package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.AddProductRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateProductRequest;
import africa.semicolon.lumexpress.data.dto.response.AddProductResponse;
import africa.semicolon.lumexpress.data.models.Product;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface ProductService {
    AddProductResponse addProduct(AddProductRequest request) throws IOException;
    String updateProductDetails(UpdateProductRequest request);
    Product getProductById(Long id);
    Page<Product> getAllProducts();
    String deleteProduct(Long id);
}
