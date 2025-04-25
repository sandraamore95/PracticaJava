package exceptions;

public class ResourceNoExiste extends RuntimeException {
    public ResourceNoExiste(String message) {
        super(message);
    }
}
