package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.mapper;

import lombok.extern.log4j.Log4j2;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.CategoryRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.ProductRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.ProductResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Category;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Product;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class ProductMapper {

    // Entity to Response
    public static ProductResponse productToResponse(Product product) {
        if (product == null) {
            log.warn("Product is null in productToResponse");
            return null;
        }
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setStock(product.getStock());
        productResponse.setPrice(product.getPrice());
        productResponse.setCategory(CategoryMapper.categoryToResponse(product.getCategory()));
        return productResponse;
    }

    // Entity to Request
    public static ProductRequest productToRequest(Product product) {
        if (product == null) {
            log.warn("Product is null in productToRequest");
            return null;
        }
        ProductRequest productRequest = new ProductRequest();
        productRequest.setId(product.getId());
        productRequest.setName(product.getName());
        productRequest.setStock(product.getStock());
        productRequest.setPrice(product.getPrice());
        productRequest.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
        return productRequest;
    }

    // Request to Entity
    public static Product productToEntity(ProductRequest productRequest) {
        if (productRequest == null) {
            log.warn("ProductRequest is null in productToEntity");
            return null;
        }
        Product product = new Product();
        Category category = new Category();
        product.setCategory(category);
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setStock(productRequest.getStock());
        product.setPrice(productRequest.getPrice());
        product.getCategory().setId(productRequest.getCategoryId());
        // La categoría se establecerá en el servicio
        return product;
    }

    // Response to Entity (corregido)
    public static Product productResponseToEntity(ProductResponse productResponse) {
        if (productResponse == null) {
            log.warn("ProductResponse is null in productResponseToEntity");
            return null;
        }
        Product product = new Product();
        product.setName(productResponse.getName());
        product.setStock(productResponse.getStock());
        product.setPrice(productResponse.getPrice());
        return product;
    }

    // List of Entities to List of Responses
    public static List<ProductResponse> productToResponseList(List<Product> productList) {
        if (productList == null || productList.isEmpty()) {
            return Collections.emptyList();
        }
        return productList.stream()
                .map(ProductMapper::productToResponse)
                .filter(response -> response != null)
                .collect(Collectors.toList());
    }

    // List of Entities to List of Requests
    public static List<ProductRequest> productToRequestList(List<Product> productList) {
        if (productList == null || productList.isEmpty()) {
            return Collections.emptyList();
        }
        return productList.stream()
                .map(ProductMapper::productToRequest)
                .filter(request -> request != null)
                .collect(Collectors.toList());
    }

    // List of Requests to List of Entities
    public static List<Product> productRequestToEntityList(List<ProductRequest> productRequestList) {
        if (productRequestList == null || productRequestList.isEmpty()) {
            return Collections.emptyList();
        }
        return productRequestList.stream()
                .map(ProductMapper::productToEntity)
                .filter(product -> product != null)
                .collect(Collectors.toList());
    }

    // List of Responses to List of Entities
    public static List<Product> productResponseToEntityList(List<ProductResponse> productResponseList) {
        if (productResponseList == null || productResponseList.isEmpty()) {
            return Collections.emptyList();
        }
        return productResponseList.stream()
                .map(ProductMapper::productResponseToEntity)
                .filter(product -> product != null)
                .collect(Collectors.toList());
    }
}