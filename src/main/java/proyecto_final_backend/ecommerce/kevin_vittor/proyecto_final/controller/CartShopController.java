package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.mapper.CartShopMapper;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.CartShopRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.CartShopResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.CartShop;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.ICartShopService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartshop")
public class CartShopController {

    @Autowired
    private ICartShopService cartShopService;

    @GetMapping
    public ResponseEntity<List<CartShopResponse>> findAll(){
        return ResponseEntity.ok(CartShopMapper.cartShopToResponseList(cartShopService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartShopResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(CartShopMapper.cartShopToResponse(cartShopService.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<CartShopResponse> addCart(@Valid @RequestBody CartShopRequest cartShopRequest){
        CartShop cartShop = CartShopMapper.cartShopRequestToEntity(cartShopRequest);
        CartShop cartShopSave = cartShopService.saveObject(cartShop);
        return ResponseEntity.ok(CartShopMapper.cartShopToResponse(cartShopSave));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartShopResponse> updateCart(@PathVariable Integer id,@Valid @RequestBody CartShopRequest cartShopRequest){
        CartShop cartShop = cartShopService.updateObject(id,CartShopMapper.cartShopRequestToEntity(cartShopRequest));
        return ResponseEntity.ok(CartShopMapper.cartShopToResponse(cartShop));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable Integer id){
        cartShopService.deleteById(id);
        return ResponseEntity.ok("El carrito de compras con id " + id + " fue eliminado!");
    }

    @PutMapping("/vaciar/{id}")
    public ResponseEntity<String> voidCartShop(@PathVariable Integer id){
        cartShopService.cleanCartShop(id);
        return ResponseEntity.ok("El carrito con id " + id + " ha sido limpiado");
    }

}
