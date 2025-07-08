package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.mapper;

import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.CartShopRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.CartShopResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.CartShop;

import java.util.List;

public class CartShopMapper {
    public static CartShopResponse cartShopToResponse(CartShop cartShop){
        CartShopResponse cartShopResponse = new CartShopResponse();
        cartShopResponse.setOrdersDTO(OrderMapper.orderToResponseList(cartShop.getOrders()));
        return cartShopResponse;
    }

    public static CartShopRequest cartShopToRequest(CartShop cartShop){
        CartShopRequest cartShopRequest = new CartShopRequest();
        cartShopRequest.setOrderRequestList(OrderMapper.orderToRequestList(cartShop.getOrders()));
        return cartShopRequest;
    }

    public static CartShop cartShopResponseToEntity(CartShopResponse cartShopResponse){
        CartShop cartShop = new CartShop();
        cartShop.setOrders(OrderMapper.orderResponseToEntityList(cartShopResponse.getOrdersDTO()));
        return cartShop;
    }

    public static CartShop cartShopRequestToEntity(CartShopRequest cartShopRequest){
        CartShop cartShop = new CartShop();
        cartShop.setId(cartShopRequest.getId());
        cartShop.setOrders(OrderMapper.orderRequestToEntityList(cartShopRequest.getOrderRequestList()));
        return cartShop;
    }

    public static List<CartShop> cartShopEntityToResponse(List<CartShopResponse> cartShopResponses){
        return (cartShopResponses.isEmpty()) ? null : cartShopResponses.stream().map(CartShopMapper::cartShopResponseToEntity).toList();
    }

    public static List<CartShop> cartShopEntityToRequest(List<CartShopRequest> cartShopRequest){
        return (cartShopRequest.isEmpty()) ? null : cartShopRequest.stream().map(CartShopMapper::cartShopRequestToEntity).toList();
    }

    public static List<CartShopResponse> cartShopToResponseList(List<CartShop> cartShopList){
        return (cartShopList.isEmpty()) ? null : cartShopList.stream().map(CartShopMapper::cartShopToResponse).toList();
    }

    public static List<CartShopRequest> cartShopToRequestList(List<CartShop> cartShopList){
        return (cartShopList.isEmpty()) ? null : cartShopList.stream().map(CartShopMapper::cartShopToRequest).toList();
    }
}
