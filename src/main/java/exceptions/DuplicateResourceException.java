package exceptions;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException() {
        super("OU duplicada.");
    }

    public DuplicateResourceException(String message) {
        super(message);
    }
}
