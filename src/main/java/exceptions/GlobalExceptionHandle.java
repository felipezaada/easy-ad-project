package exceptions;

public class GlobalExceptionHandle {
    public static void handle(Exception e) {
        if (e instanceof exceptions.ResourceNotFoundException) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        if (e instanceof exceptions.DuplicateResourceException) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        if (e instanceof exceptions.EmptyRepositoryException) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }
        System.out.println("Erro inesperado:");
    }
}