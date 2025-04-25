package exceptions;

public class UserNoExiste extends RuntimeException {
    public UserNoExiste(String message) {
        super(message);
    }
}
