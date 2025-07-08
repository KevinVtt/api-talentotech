package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartShopResponse {
    private List<OrderResponse> ordersDTO;
}
