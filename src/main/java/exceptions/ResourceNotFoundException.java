package exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("OU não encontrada.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
