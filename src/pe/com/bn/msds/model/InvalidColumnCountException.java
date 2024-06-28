package pe.com.bn.msds.model;


public class InvalidColumnCountException extends Exception {
    public InvalidColumnCountException(String message) {
        super(message);
    }
}