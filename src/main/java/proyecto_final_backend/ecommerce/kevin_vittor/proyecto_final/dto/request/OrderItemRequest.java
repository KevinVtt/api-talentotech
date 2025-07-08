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
public class OrderItemRequest {
    private Integer id;
    @NotNull(message = "El productId es obligatorio")
    @Positive(message = "El productId debe ser mayor que cero")
    private Integer productId;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que cero")
    private Integer quantity;
}