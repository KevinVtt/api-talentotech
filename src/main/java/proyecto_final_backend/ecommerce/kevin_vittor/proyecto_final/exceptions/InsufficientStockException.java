package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}
