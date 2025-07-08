package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Map<String,Object> errors = new HashMap<>();

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String,Object>> notFoundException(NotFoundException e){
        errors.put("message",e.getMessage());
        errors.put("date", LocalDate.now());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ObjectAlreadyExists.class)
    public ResponseEntity<Map<String,Object>> objectAlreadyExists(ObjectAlreadyExists e){
        errors.put("message",e.getMessage());
        errors.put("date", LocalDate.now());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ProblemForPersists.class)
    public ResponseEntity<Map<String,Object>> problemForPersist(ProblemForPersists e){
        errors.put("message",e.getMessage());
        errors.put("date", LocalDate.now());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Map<String,Object>> insufficientStockException(InsufficientStockException e){
        errors.put("message",e.getMessage());
        errors.put("date", LocalDate.now());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,Object>> RuntimeException(RuntimeException e){
        errors.put("message",e.getMessage());
        errors.put("date", LocalDate.now());
        return ResponseEntity.badRequest().body(errors);
    }

}
