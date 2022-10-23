package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.AddProductRequest;
import africa.semicolon.lumexpress.data.dto.request.GetAllItemsRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateProductRequest;
import africa.semicolon.lumexpress.data.dto.response.AddProductResponse;
import africa.semicolon.lumexpress.data.dto.response.UpdateProductResponse;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface ProductService {
    AddProductResponse addProduct(AddProductRequest request) throws IOException;
    UpdateProductResponse updateProductDetails(Long productId, JsonPatch patch) throws JsonPatchException, ProductNotFoundException;
    Product getProductById(Long id) throws ProductNotFoundException;
    Page<Product> getAllProducts(GetAllItemsRequest getAllItemsRequest);
    String deleteProduct(Long id);
}
