package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.mapper;

import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.CartShopRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.UserRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.CartShopResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.UserResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.CartShop;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.User;

import java.util.List;

public class UserMapper {

    public static UserResponse toResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setName(user.getName());
        userResponse.setCartShopResponse(CartShopMapper.cartShopToResponse(user.getCartShop()));
        return userResponse;
    }

    public static UserRequest toRequest(User user){
        UserRequest userRequest = new UserRequest();
        userRequest.setId(user.getId());
        userRequest.setName(user.getName());
        userRequest.setCartShopId(user.getCartShop().getId());
        return userRequest;
    }

    public static User toEntity(UserResponse userResponse){
        User user = new User();
        user.setEmail(userResponse.getEmail());
        user.setName(userResponse.getName());
        return user;
    }

    public static User toEntity(UserRequest userRequest){
        User user = new User();
        CartShop cartShop = new CartShop();
        user.setCartShop(cartShop);
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.getCartShop().setId(userRequest.getCartShopId());
        return user;
    }

    public static List<User> userEntityToResponse(List<UserResponse> userResponses){
        return (userResponses.isEmpty()) ? null : userResponses.stream().map(UserMapper::toEntity).toList();
    }

    public static List<User> userEntityToRequest(List<UserRequest> userRequests){
        return (userRequests.isEmpty()) ? null : userRequests.stream().map(UserMapper::toEntity).toList();
    }

    public static List<UserResponse> userToResponseList(List<User> userList){
        return (userList.isEmpty()) ? null : userList.stream().map(UserMapper::toResponse).toList();
    }

    public static List<UserRequest> userToRequestList(List<User> userList){
        return (userList.isEmpty()) ? null : userList.stream().map(UserMapper::toRequest).toList();
    }

}
