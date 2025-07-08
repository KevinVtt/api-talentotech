package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.mapper;

import org.aspectj.weaver.ast.Or;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.OrderRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.OrderResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.CartShop;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Order;

import java.time.LocalDateTime;
import java.util.List;

public class OrderMapper {

    public static OrderResponse orderToResponse(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setFechaCreacion(order.getOrderDate().toString());
        orderResponse.setOrderItemResponses(OrderItemMapper.toResponseList(order.getItems()));
        return orderResponse;
    }

    public static OrderRequest orderToRequest(Order order){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setId(order.getId());
        orderRequest.setOrderItemRequests(OrderItemMapper.toRequestList(order.getItems()));
        return orderRequest;
    }

    public static Order orderRequestToEntity(OrderRequest orderRequest){
        Order order = new Order();
        CartShop cartShop = new CartShop();
        cartShop.setId(orderRequest.getCartId());
        cartShop.addOrder(order);
        order.setId(orderRequest.getId());
        order.setItems(OrderItemMapper.requestToEntityList(orderRequest.getOrderItemRequests()));
        return order;
    }

    public static Order orderResponseToEntity(OrderResponse orderRequest){
        Order order = new Order();
        order.setOrderDate(LocalDateTime.parse(orderRequest.getFechaCreacion()));
        order.setItems(OrderItemMapper.responseToEntityList(orderRequest.getOrderItemResponses()));
        return order;
    }

    public static List<OrderResponse> orderToResponseList(List<Order> orderList){
        return (orderList.isEmpty()) ? null : orderList.stream().map(OrderMapper::orderToResponse).toList();
    }

    public static List<OrderRequest> orderToRequestList(List<Order> orderList){
        return (orderList.isEmpty()) ? null : orderList.stream().map(OrderMapper::orderToRequest).toList();
    }

    public static List<Order> orderResponseToEntityList(List<OrderResponse> orderList){
        return (orderList.isEmpty()) ? null : orderList.stream().map(OrderMapper::orderResponseToEntity).toList();
    }

    public static List<Order> orderRequestToEntityList(List<OrderRequest> orderList){
        return (orderList.isEmpty()) ? null : orderList.stream().map(OrderMapper::orderRequestToEntity).toList();
    }
}
