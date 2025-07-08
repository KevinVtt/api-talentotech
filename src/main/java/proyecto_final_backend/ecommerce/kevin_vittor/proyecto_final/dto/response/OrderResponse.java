package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderResponse {
    private List<OrderItemResponse> orderItemResponses = new ArrayList<>();
    private String fechaCreacion;

    public void addOrderItemResponse(OrderItemResponse orderItemResponse) {
        this.orderItemResponses.add(orderItemResponse);
    }
}
