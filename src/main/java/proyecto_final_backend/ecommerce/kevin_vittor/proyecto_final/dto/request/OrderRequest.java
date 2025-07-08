package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request;

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
public class OrderRequest {
    private Integer id;
    private List<OrderItemRequest> orderItemRequests = new ArrayList<>();
    private Integer cartId;
    public void addOrderItem(OrderItemRequest orderItemRequest){
        this.orderItemRequests.add(orderItemRequest);
    }
}
