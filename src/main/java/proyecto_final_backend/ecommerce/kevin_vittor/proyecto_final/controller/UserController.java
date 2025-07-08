package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.mapper.UserMapper;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.request.UserRequest;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.dto.response.UserResponse;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.User;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.IUserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(UserMapper.userToResponseList(userService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(UserMapper.toResponse(userService.findById(id).orElseThrow()));
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){
        User userCreated = UserMapper.toEntity(userRequest);
        User userSaved = userService.saveObject(userCreated);
        return ResponseEntity.ok(UserMapper.toResponse(userSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest){
        User userRequestBody = UserMapper.toEntity(userRequest);
        User userUpdated = userService.updateObject(id,userRequestBody);
        return ResponseEntity.ok(UserMapper.toResponse(userUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        userService.deleteById(id);
        return ResponseEntity.ok("El usuario con id " + id + " ha sido eliminado");
    }
}
