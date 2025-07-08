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
public class CartShopRequest {
    private Integer id;
    private List<OrderRequest> orderRequestList;

    public void addOrder(OrderRequest orderRequest){
        if(this.orderRequestList.isEmpty()){
            this.orderRequestList = new ArrayList<>();
        }
        this.orderRequestList.add(orderRequest);
    }
}
