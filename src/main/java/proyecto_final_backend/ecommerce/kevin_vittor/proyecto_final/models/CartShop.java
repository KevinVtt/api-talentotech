package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "cart_shop")
@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class CartShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "cartShop")
    private User user;

    public void addOrder(Order order) {
        order.setCart(this);
        this.orders.add(order);
    }

    public void voidOrders() {
        this.orders.clear();
    }
}