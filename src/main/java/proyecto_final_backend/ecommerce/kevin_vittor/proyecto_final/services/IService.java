package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services;

import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.OrderItem;

import java.util.List;
import java.util.Optional;

public interface IService <T>{
    Optional<T> findById(Integer id);
    List<T> findAll();
    T save(T object);
    void deleteById(Integer id);
    void delete(T object);
    T saveObject(T object);
    T updateObject(Integer id, T object);
}
