package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoryRequest {
    private Integer id;
    private String name;
}
