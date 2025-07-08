package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.mapper;

import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.CategoryRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.CategoryResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Category;

import java.util.List;

public class CategoryMapper {
    public static CategoryResponse categoryToResponse(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }

    public static CategoryRequest categoryToRequest(Category category){
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName(category.getName());
        return categoryRequest;
    }

    public static Category categoryResponseToEntity(CategoryResponse categoryResponse){
        Category category = new Category();
        category.setName(categoryResponse.getName());
        return category;
    }

    public static Category categoryRequestToEntity(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setId(categoryRequest.getId());
        category.setName(categoryRequest.getName());
        return category;
    }

    public static List<CategoryResponse> categoryToResponseList(List<Category> categoryList){
        return (categoryList.isEmpty()) ? null : categoryList.stream().map(CategoryMapper::categoryToResponse).toList();
    }

    public static List<CategoryRequest> categoryToRequestList(List<Category> categoryList){
        return (categoryList.isEmpty()) ? null : categoryList.stream().map(CategoryMapper::categoryToRequest).toList();
    }

    public static List<Category> categoryResponeToEntityList(List<CategoryResponse> categoryList){
        return (categoryList.isEmpty()) ? null : categoryList.stream().map(CategoryMapper::categoryResponseToEntity).toList();
    }

    public static List<Category> categoryRequestToEntityList(List<CategoryRequest> categoryList){
        return (categoryList.isEmpty()) ? null : categoryList.stream().map(CategoryMapper::categoryRequestToEntity).toList();
    }
}
