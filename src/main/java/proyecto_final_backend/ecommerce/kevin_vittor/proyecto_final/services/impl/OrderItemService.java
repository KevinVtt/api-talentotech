package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.impl;

import java.util.List;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.InsufficientStockException;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.NotFoundException;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.ObjectAlreadyExists;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.ProblemForPersists;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Order;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.OrderItem;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Product;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.IOrder;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.IOrderItem;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.IProduct;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.IOrderItemService;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.IProductService;
@Log4j2
@Service
public class OrderItemService implements IOrderItemService {

    @Autowired
    private IOrderItem orderItemRepository;

    @Autowired
    private IOrder orderRepository;

    @Autowired
    private IProduct productRepository;

    @Override
    @Transactional
    public void delete(OrderItem object) {
        try{
            orderItemRepository.delete(object);
        }catch(RuntimeException e){
            throw new ProblemForPersists("No se puede eliminar la orden de compra"); // add customs exceptions
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        try{
            orderItemRepository.deleteById(id);
        }catch(RuntimeException e){
            throw new ProblemForPersists("No se puede eliminar la orden de compra con esa id"); // add customs exceptions
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> findAll() {
        try{
            return orderItemRepository.findAll();
        }catch(RuntimeException e){
            throw e; // add customs exceptions
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderItem> findById(Integer id) {
        try{
            return orderItemRepository.findById(id);
        }catch(RuntimeException e){
            throw e; // add customs exceptions
        }
    }

    @Override
    @Transactional
    public OrderItem save(OrderItem object) {
        try{
            return orderItemRepository.save(object);
        }catch(RuntimeException e){
            throw new ProblemForPersists("No se puede actualizar / guardar la orden de compra"); // add customs exceptions
        }
    }

    @Override
    @Transactional
    public OrderItem saveObject(OrderItem object) {
        if(object.getId() == null){
            log.info("OrderItem: " + object);
            Product product = productRepository.findById(object.getProduct().getId()).orElseThrow(() -> new NotFoundException("No existe el producto"));
            object.setProduct(product);
            log.info("OrderItem post set: " + object);
            decrecentStock(object,product);
            return save(object);
        }
        throw new ObjectAlreadyExists("El id ya existe y es: " + object.getId());
    }

    @Transactional
    @Override
    public OrderItem updateObject(Integer id, OrderItem orderItem) {
        OrderItem orderItemDb = findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró el OrderItem con id: " + id));
        if(orderItem.getOrder().getId() != null){
            Order order = orderRepository.findById(orderItem.getOrder().getId()).orElseThrow(() -> new NotFoundException("No existe el order con esa id"));
            orderItem.setOrder(order);
        }
        setOrderItem(orderItem,orderItemDb);
        decrecentStock(orderItem,orderItem.getProduct());
        return orderItemRepository.save(orderItemDb);
    }


    private void setOrderItem(OrderItem newOrderItem, OrderItem orderItemDb){
        if (newOrderItem.getQuantity() != null) {
            orderItemDb.setQuantity(newOrderItem.getQuantity());
        }
        setProductToOrderItem(newOrderItem,orderItemDb);
        setOrderToOrderItem(newOrderItem,orderItemDb);
    }

    private void setProductToOrderItem(OrderItem newOrderItem,OrderItem orderItemDb){
        if (newOrderItem.getProduct() != null && newOrderItem.getProduct().getId() != null) {
            Product product = productRepository.findById(newOrderItem.getProduct().getId())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado con id: " + newOrderItem.getProduct().getId()));
            orderItemDb.setProduct(product);
        }
    }

    private void setOrderToOrderItem(OrderItem newOrderItem,OrderItem orderItemDb){
        if (newOrderItem.getOrder() != null && newOrderItem.getOrder().getId() != null) {
            Order order = orderRepository.findById(newOrderItem.getOrder().getId())
                    .orElseThrow(() -> new NotFoundException("Orden no encontrada con id: " + newOrderItem.getOrder().getId()));
            orderItemDb.setOrder(order);
        }
    }

    private void decrecentStock(OrderItem orderItem, Product product){
        if(product.getStock() <= 0){
            throw new InsufficientStockException("No hay suficiente stock");
        }
        product.setStock(product.getStock() - orderItem.getQuantity());
    }
}
