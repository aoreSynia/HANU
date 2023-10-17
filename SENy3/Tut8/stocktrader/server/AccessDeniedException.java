package stocktrader.server;


public class AccessDeniedException extends Exception {
    public AccessDeniedException() { 
        super("Access Denied!");
    }
    
    public AccessDeniedException(String message) {
        super(message);
    }
}