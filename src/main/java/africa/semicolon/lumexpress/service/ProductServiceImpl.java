package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.AddProductRequest;
import africa.semicolon.lumexpress.data.dto.request.GetAllItemsRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateProductRequest;
import africa.semicolon.lumexpress.data.dto.response.AddProductResponse;
import africa.semicolon.lumexpress.data.dto.response.UpdateProductResponse;
import africa.semicolon.lumexpress.data.models.Category;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.data.repositories.ProductRepository;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;
import africa.semicolon.lumexpress.service.cloud.CloudService;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final CloudService cloudService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public AddProductResponse addProduct(AddProductRequest request) throws IOException {
        Product product = mapper.map(request, Product.class);
        product.getCategories().add(
                Category.valueOf(request.getProductCategory().toUpperCase()));
        var imageUrl =
                cloudService.upload(request.getImage()
                        .getBytes(), ObjectUtils.emptyMap());
        log.info("cloudinary image url::{}", imageUrl);
        product.setImageUrl(imageUrl);
        Product savedProduct = productRepository.save(product);
        return buildAddProductResponse(savedProduct);
    }

    private AddProductResponse buildAddProductResponse(Product savedProduct) {
        return AddProductResponse.builder()
                .code(201)
                .productId(savedProduct.getId())
                .message("product added successfully")
                .build();
    }

    @Override
    public UpdateProductResponse updateProductDetails(Long productId, JsonPatch patch)  {
        //find product
        var foundProduct =
                productRepository.findById(productId)
                        .orElseThrow(()-> new ProductNotFoundException(
                                String.format("product with id %d not found",
                                        productId)
                        ));
        Product updatedProduct = applyPatchToProduct(patch, foundProduct);
        //save updated product
        var savedProduct=productRepository.save(updatedProduct);
        return buildUpdateResponse(savedProduct);
    }

    private Product applyPatchToProduct(JsonPatch patch, Product foundProduct)  {
        //convert found product to json node
        var productNode = objectMapper.convertValue(foundProduct, JsonNode.class);
        //apply patch to productNode
        JsonNode patchedProductNode;
        try {
            patchedProductNode = patch.apply(productNode);
            //convert patchedNode to product object
            var updatedProduct =
                    objectMapper.readValue(objectMapper.writeValueAsBytes(patchedProductNode),
                            Product.class);
            return updatedProduct;
        } catch (IOException| JsonPatchException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static UpdateProductResponse buildUpdateResponse(Product savedProduct) {
        return UpdateProductResponse.builder()
                .productName(savedProduct.getName())
                .price(savedProduct.getPrice())
                .message("update successful")
                .statusCode(200)
                .build();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                        ()->new ProductNotFoundException(
                String.format("product with id %d not found", id)));
    }

    @Override
    public Page<Product> getAllProducts(GetAllItemsRequest getAllItemsRequest) {
        Pageable pageSpecs = PageRequest
                .of(getAllItemsRequest.getPageNumber()-1,
                getAllItemsRequest.getNumberOfItemsPerPage());
        Page<Product> products =
                productRepository.findAll(pageSpecs);
        return products;
    }

    @Override
    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "product deleted successfully";
    }
}
