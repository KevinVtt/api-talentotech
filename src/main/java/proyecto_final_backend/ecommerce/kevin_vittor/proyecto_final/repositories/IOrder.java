package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Order;

@Repository
public interface IOrder extends JpaRepository<Order,Integer>{

}
