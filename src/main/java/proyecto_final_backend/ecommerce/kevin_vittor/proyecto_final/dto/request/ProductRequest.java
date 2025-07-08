package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductRequest {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    @NotNull
    @Positive
    private Double price;
    @NotNull
    @Positive
    private Integer stock;
    @NotNull
    @Positive
    private Integer categoryId;
}