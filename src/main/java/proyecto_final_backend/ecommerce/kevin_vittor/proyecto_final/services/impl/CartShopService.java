package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.NotFoundException;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.ProblemForPersists;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.CartShop;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Order;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.OrderItem;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.ICartShop;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.IOrder;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.IOrderItem;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.ICartShopService;

@Service
public class CartShopService implements ICartShopService {

    @Autowired
    private ICartShop cartShopRepository;

    @Autowired
    private IOrder orderRepository;

    @Autowired
    private IOrderItem orderItemRepository;

    @Override
    @Transactional
    public void delete(CartShop object) {
        try {
            cartShopRepository.delete(object);
        } catch (RuntimeException e) {
            throw new ProblemForPersists("No existe un carrito de compra para eliminar");
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        try {
            cartShopRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new ProblemForPersists("No existe el carrito de compra con ese id");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartShop> findAll() {
        return cartShopRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CartShop> findById(Integer id) {
        return cartShopRepository.findById(id);
    }

    @Override
    @Transactional
    public CartShop save(CartShop object) {
        try {
            return cartShopRepository.save(object);
        } catch (RuntimeException e) {
            throw new ProblemForPersists("No se puede actualizar / guardar el carrito de compra");
        }
    }

    @Override
    @Transactional
    public CartShop saveObject(CartShop object) {
        if (object.getId() != null) {
            throw new IllegalArgumentException("El id existe, no se puede persistir");
        }

        // Persistir las órdenes y sus ítems antes de asignarlos
        List<Order> persistedOrders = new ArrayList<>();
        for (Order order : object.getOrders()) {
            // Persistir los ítems de la orden
            List<OrderItem> persistedItems = new ArrayList<>();
            for (OrderItem item : order.getItems()) {
                if (item.getId() == null) {
                    item = orderItemRepository.save(item);
                }
                persistedItems.add(item);
            }
            order.setItems(persistedItems);
            // Persistir la orden
            if (order.getId() == null) {
                order.setCart(object);
                order = orderRepository.save(order);
            }
            persistedOrders.add(order);
        }
        object.setOrders(persistedOrders);

        return cartShopRepository.save(object);
    }

    @Override
    @Transactional
    public CartShop updateObject(Integer id, CartShop object) {
        CartShop cartDb = findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el carrito con id: " + id));

        if (object.getOrders() != null) {
            // Persistir las órdenes y sus ítems antes de asignarlos
            List<Order> persistedOrders = new ArrayList<>();
            for (Order order : object.getOrders()) {
                // Persistir los ítems de la orden
                List<OrderItem> persistedItems = new ArrayList<>();
                for (OrderItem item : order.getItems()) {
                    if (item.getId() == null) {
                        item = orderItemRepository.save(item);
                    }
                    persistedItems.add(item);
                }
                order.setItems(persistedItems);
                // Persistir la orden
                if (order.getId() == null) {
                    order.setCart(cartDb);
                    order = orderRepository.save(order);
                }
                persistedOrders.add(order);
            }
            cartDb.setOrders(persistedOrders);
        }

        return save(cartDb);
    }

    @Override
    @Transactional
    public boolean cleanCartShop(Integer id) {
        CartShop cartDb = findById(id).orElseThrow(() -> new NotFoundException("No existe el carrito con id: " + id));
        cartDb.voidOrders();
        cartShopRepository.save(cartDb);
        return true;
    }
}