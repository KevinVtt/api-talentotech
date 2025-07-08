package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.mapper.ProductMapper;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.ProductRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.ProductResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.NotFoundException;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Product;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.IProductService;

@RestController
@RequestMapping("/api/producto")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(ProductMapper.productToResponseList(productService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Integer id) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + id));
        return ResponseEntity.ok(ProductMapper.productToResponse(product));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = ProductMapper.productToEntity(productRequest);
        Product savedProduct = productService.saveObject(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.productToResponse(savedProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> editProduct(@PathVariable Integer id, @Valid @RequestBody ProductRequest productRequest) {
        Product updatedProduct = productService.updateObject(id, ProductMapper.productToEntity(productRequest));
        return ResponseEntity.ok(ProductMapper.productToResponse(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.ok("El producto con ID " + id + " se eliminó correctamente");
    }

}
