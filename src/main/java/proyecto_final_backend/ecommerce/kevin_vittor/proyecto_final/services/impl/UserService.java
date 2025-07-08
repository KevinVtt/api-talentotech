package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.impl;

import org.hibernate.dialect.BooleanDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.NotFoundException;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.CartShop;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.User;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.ICartShop;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.IUser;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.IUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUser userRepository;

    @Autowired
    private ICartShop cartShopRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encontro el usuario con ese id")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User object) {
        try{
            return userRepository.save(object);
        }catch (NotFoundException e){
            throw new NotFoundException("No se ha encontrado el usuario" + e.getCause());
        }catch (RuntimeException e){
            throw new RuntimeException("Hubo un error desconocido " + e.getCause());
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        try{
            userRepository.deleteById(id);
        }catch (NotFoundException e){
            throw new NotFoundException("No se ha encontrado el usuario con id " + id + " causa: " + e.getCause());
        }catch (RuntimeException e){
            throw new RuntimeException("Hubo un error desconocido " + e.getCause());
        }
    }

    @Override
    @Transactional
    public void delete(User object) {
        try{
            userRepository.delete(object);
        }catch (NotFoundException e){
            throw new NotFoundException("No se ha encontrado el usuario" + e.getCause());
        }catch (RuntimeException e){
            throw new RuntimeException("Hubo un error desconocido " + e.getCause());
        }
    }

    @Override
    @Transactional
    public User saveObject(User object) {
        CartShop cartShop = new CartShop();
        cartShop.setOrders(new ArrayList<>());
        object.setCartShop(cartShop);
        return save(object);
    }

    @Override
    @Transactional
    public User updateObject(Integer id, User object) {
        User userDb = userRepository.findById(id).orElseThrow(() -> new NotFoundException("No existe el usuario con el id " + id));
        userDb.setName(object.getName());
        userDb.setEmail(object.getEmail());
        return save(userDb);
    }
}
