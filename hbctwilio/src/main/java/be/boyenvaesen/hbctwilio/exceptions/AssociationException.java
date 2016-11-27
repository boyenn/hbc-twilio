package be.boyenvaesen.hbctwilio.exceptions;

/**
 * Created by Boyen on 27/11/2016.
 */
public class AssociationException extends Exception {
    public AssociationException() {

    }

    public AssociationException(String message) {
        super(message);
    }

    public AssociationException(Throwable cause) {
        super(cause);
    }

    public AssociationException(String message, Throwable cause) {
        super(message, cause);
    }
}