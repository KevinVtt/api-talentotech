package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "order_item")
@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Product product;

    private Integer quantity;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
