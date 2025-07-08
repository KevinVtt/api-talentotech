package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Category;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Product;

import java.util.Optional;

@Repository
public interface IProduct extends JpaRepository<Product,Integer>{
    Optional<Product> findByName(String name);
}
