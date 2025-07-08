package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.OrderItem;

@Repository
public interface IOrderItem extends JpaRepository<OrderItem,Integer>{

}
