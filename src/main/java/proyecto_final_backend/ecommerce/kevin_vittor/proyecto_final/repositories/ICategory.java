package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Category;

import java.util.Optional;

@Repository
public interface ICategory extends JpaRepository<Category,Integer> {
    Optional<Category> findByName(String name);
}
