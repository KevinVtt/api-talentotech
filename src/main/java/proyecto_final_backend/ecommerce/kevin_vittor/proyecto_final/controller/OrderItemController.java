package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.mapper.OrderItemMapper;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.OrderItemRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.OrderItemResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.OrderItem;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.IOrderItemService;

import java.util.List;

@RestController
@RequestMapping("/api/order-item")
public class OrderItemController {

    @Autowired
    private IOrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemResponse>> findAll(){
        return ResponseEntity.ok(OrderItemMapper.toResponseList(orderItemService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(OrderItemMapper.toResponse(orderItemService.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<OrderItemResponse> addOrderItem(@Valid @RequestBody OrderItemRequest orderItemRequest){
        OrderItem orderItem = OrderItemMapper.requestToEntity(orderItemRequest);
        OrderItem orderItemSave = orderItemService.saveObject(orderItem);
        return ResponseEntity.ok(OrderItemMapper.toResponse(orderItemSave));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderItem(@PathVariable Integer id, @RequestBody OrderItemRequest orderItemRequest){
        OrderItem orderItem = OrderItemMapper.requestToEntity(orderItemRequest);
        return ResponseEntity.ok(orderItemService.updateObject(id,orderItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Integer id){
        orderItemService.deleteById(id);
        return ResponseEntity.ok("El order item con id " + id + " ha sido eliminado");
    }

}
