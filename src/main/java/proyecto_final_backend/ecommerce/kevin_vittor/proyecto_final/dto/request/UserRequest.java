package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserRequest {
    private Integer id;
    @NotNull
    private String name;
    private String email;
    @NotNull
    @Positive
    private Integer cartShopId;
}
