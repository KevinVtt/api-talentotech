package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductResponse {
    private String name;
    private Double price;
    private Integer stock;
    private CategoryResponse category;
}