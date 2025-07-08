package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.mapper;

import lombok.extern.log4j.Log4j2;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.OrderItemRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.OrderItemResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.OrderItem;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Product;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class OrderItemMapper {

    // Entity to Response
    public static OrderItemResponse toResponse(OrderItem orderItem) {
        OrderItemResponse response = new OrderItemResponse();
        response.setQuantity(orderItem.getQuantity());
        response.setProduct(ProductMapper.productToResponse(orderItem.getProduct()));
        return response;
    }

    // Entity to Request
    public static OrderItemRequest toRequest(OrderItem orderItem) {
        if (orderItem == null) {
            log.warn("OrderItem is null in toRequest");
            return null;
        }
        OrderItemRequest request = new OrderItemRequest();
        request.setId(orderItem.getId());
        request.setProductId(orderItem.getProduct() != null ? orderItem.getProduct().getId() : null);
        request.setQuantity(orderItem.getQuantity());
        return request;
    }

    // Request to Entity
    public static OrderItem requestToEntity(OrderItemRequest request) {
        OrderItem orderItem = new OrderItem();
        if (request.getId() != null) {
            orderItem.setId(request.getId()); // Solo para actualizaciones
        }
        Product product = new Product();
        product.setId(request.getProductId());
        orderItem.setProduct(product);
        orderItem.setQuantity(request.getQuantity());
        return orderItem;
    }

    // Response to Entity
    public static OrderItem responseToEntity(OrderItemResponse response) {
        if (response == null) {
            log.warn("OrderItemResponse is null in responseToEntity");
            return null;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(response.getQuantity());
        // El producto se establecerá en el servicio (puede requerir búsqueda por nombre)
        return orderItem;
    }

    // List of Entities to List of Responses
    public static List<OrderItemResponse> toResponseList(List<OrderItem> orderItems) {
        return orderItems.stream().map(OrderItemMapper::toResponse).toList();
    }

    // List of Entities to List of Requests
    public static List<OrderItemRequest> toRequestList(List<OrderItem> orderItemList) {
        if (orderItemList == null || orderItemList.isEmpty()) {
            return Collections.emptyList();
        }
        return orderItemList.stream()
                .map(OrderItemMapper::toRequest)
                .filter(request -> request != null)
                .collect(Collectors.toList());
    }

    // List of Requests to List of Entities
    public static List<OrderItem> requestToEntityList(List<OrderItemRequest> orderItemList) {
        if (orderItemList == null || orderItemList.isEmpty()) {
            return Collections.emptyList();
        }
        return orderItemList.stream()
                .map(OrderItemMapper::requestToEntity)
                .filter(orderItem -> orderItem != null)
                .collect(Collectors.toList());
    }

    // List of Responses to List of Entities
    public static List<OrderItem> responseToEntityList(List<OrderItemResponse> orderItemList) {
        if (orderItemList == null || orderItemList.isEmpty()) {
            return Collections.emptyList();
        }
        return orderItemList.stream()
                .map(OrderItemMapper::responseToEntity)
                .filter(orderItem -> orderItem != null)
                .collect(Collectors.toList());
    }
}