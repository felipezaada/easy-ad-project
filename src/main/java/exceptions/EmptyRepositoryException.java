package exceptions;

public class EmptyRepositoryException extends RuntimeException {
    public EmptyRepositoryException() {
        super("Repositório vazio.");
    }

    public EmptyRepositoryException(String message) {
        super(message);
    }
}
