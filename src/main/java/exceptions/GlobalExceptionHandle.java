package exceptions;

public class GlobalExceptionHandle {
    public static void handle(Exception e) {
        if (e instanceof exceptions.ResourceNotFoundException || e instanceof exceptions.EmptyRepositoryException) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }
        if (e instanceof exceptions.DuplicateResourceException) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Erro inesperado!");
    }
}