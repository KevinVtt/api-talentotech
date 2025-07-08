package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.InsufficientStockException;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.NotFoundException;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.ProblemForPersists;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.CartShop;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Order;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.OrderItem;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Product;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.ICartShop;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.IOrder;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.IOrderItem;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.IOrderItemService;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.IOrderService;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.IProductService;

@Service
@Log4j2
public class OrderService implements IOrderService{

    @Autowired
    private IOrder orderRepository;

    @Autowired
    private ICartShop cartShopRepository;

    @Autowired
    private IOrderItemService orderItemService;

    @Autowired
    private IProductService productService;

    @Override
    @Transactional
    public void delete(Order object) {
        try{
            orderRepository.delete(object);
        }catch(RuntimeException e){
            throw new ProblemForPersists("No se puede eliminar la orden"); // add customs exceptions
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        try{
            orderRepository.deleteById(id);
        }catch(RuntimeException e){
            throw new ProblemForPersists("No se puede eliminar la orden con esa id"); // add customs exceptions
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        try{
            return orderRepository.findAll();
        }catch(RuntimeException e){
            throw e; // add customs exceptions
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findById(Integer id) {
        try{
           return orderRepository.findById(id);
        }catch(RuntimeException e){
            throw e; // add customs exceptions
        }
    }

    @Override
    @Transactional
    public Order save(Order object) {
        try{
            return orderRepository.save(object);
        }catch(RuntimeException e){
            throw new ProblemForPersists("No se puede actualizar / guardar la orden"); // add customs exceptions
        }
    }

    @Override
    @Transactional
    public Order saveObject(Order object) {
        if (object.getId() != null) {
            throw new IllegalArgumentException("La orden ya existe. Este método es solo para crear nuevas órdenes.");
        }

        log.info("Order: " + object);

        // Validar existencia del carrito
        if (object.getCart() != null && object.getCart().getId() != null) {
            Optional<CartShop> cartShop = cartShopRepository.findById(object.getCart().getId());
            if (cartShop.isPresent()) {
                object.setCart(cartShop.get());
            } else {
                throw new NotFoundException("Carrito con ID " + object.getCart().getId() + " no encontrado.");
            }
        } else {
            throw new IllegalArgumentException("La orden debe estar asociada a un carrito existente.");
        }

        // Validar y vincular los items
        if (object.getItems() == null || object.getItems().isEmpty()) {
            throw new IllegalArgumentException("La orden debe contener al menos un item.");
        }

        object.setOrderDate(LocalDateTime.now());
        object.getItems().forEach(item -> item.setOrder(object));
        setProductoInOrderItem(object.getItems());

        return orderRepository.save(object);
    }


    @Override
    @Transactional
    public Order updateObject(Integer id, Order object) {

        Order orderDb = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("El order con esa id no existe!"));
        if(orderDb.getItems().isEmpty()){
            orderDb.setItems(new ArrayList<>());
        }
        if(object.getCart().getId() != null){
            CartShop cartShopDb = cartShopRepository.findById(object.getCart().getId()).orElseThrow(() -> new NotFoundException("El carrito de compra no existe con esa id"));
            orderDb.setCart(cartShopDb);
        }
        if(object.getItems() != null){
            object.getItems().forEach(item -> item.setOrder(orderDb));
            orderDb.setItems(object.getItems());
        }
        return orderRepository.save(orderDb);
    }

    private void setProductoInOrderItem(List<OrderItem> orderItem){
        for(OrderItem oi: orderItem){
            Product product = productService.findById(oi.getProduct().getId()).orElseThrow();
            oi.setProduct(product);
            decrecentStock(oi,product);
        }
    }

    private void decrecentStock(OrderItem orderItem, Product product){
        if(product.getStock() <= 0){
            throw new InsufficientStockException("No hay suficiente stock");
        }
        product.setStock(product.getStock() - orderItem.getQuantity());
    }
}
