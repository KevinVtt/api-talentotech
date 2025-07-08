package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.mapper.OrderMapper;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.OrderRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.OrderResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Order;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.IOrderService;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(OrderMapper.orderToResponseList(orderService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(OrderMapper.orderToResponse(orderService.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> addOrder(@Valid @RequestBody OrderRequest orderRequest){
        Order order = OrderMapper.orderRequestToEntity(orderRequest);
        Order orderSave = orderService.saveObject(order);
        log.info("Order persistida: " + orderSave);
        return ResponseEntity.ok(OrderMapper.orderToResponse(orderSave));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Integer id, @RequestBody OrderRequest orderRequest){
        Order order = orderService.updateObject(id,OrderMapper.orderRequestToEntity(orderRequest));
        return ResponseEntity.ok(OrderMapper.orderToResponse(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id){
        orderService.deleteById(id);
        return ResponseEntity.ok("La orden con id " + id + " ha sido eliminada con exito");
    }

}
