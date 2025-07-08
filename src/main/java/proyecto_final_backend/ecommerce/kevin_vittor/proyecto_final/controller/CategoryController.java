package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.CategoryRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.CategoryResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.mapper.CategoryMapper;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Category;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll(){
        return ResponseEntity.ok().body(CategoryMapper.categoryToResponseList(categoryService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Integer id){
        CategoryResponse categoryResponse = categoryToDTO(categoryService.findById(id).orElseThrow());
        return ResponseEntity.ok(categoryResponse);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        Category category = CategoryMapper.categoryRequestToEntity(categoryRequest);
        Category categorySave = categoryService.save(category);
        return ResponseEntity.accepted().body(CategoryMapper.categoryToResponse(categorySave));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> editCategory(@PathVariable Integer id, @RequestBody CategoryRequest categoryRequest){
        Category category = CategoryMapper.categoryRequestToEntity(categoryRequest);
        return ResponseEntity.ok().body(CategoryMapper.categoryToResponse(categoryService.updateObject(id, category)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        categoryService.deleteById(id);
        return ResponseEntity.ok("La categoria con id " + id + " ha sido eliminada");
    }

    private CategoryResponse categoryToDTO(Category category){
        return CategoryMapper.categoryToResponse(category);
    }
}
