package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import lombok.*;

@Table(name = "orders")
@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> items = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime orderDate;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "cart_id", nullable = false)
    private CartShop cart;

    public void addOrderItem(OrderItem item) {
        if (item.getId() == null) {
            throw new IllegalStateException("Cannot add unsaved OrderItem to Order");
        }
        item.setOrder(this);
        this.items.add(item);
    }
}