package ua.epam.internetprovider.service.exception;

public class SubscriberAmountNotEnoughException extends Exception{

    public SubscriberAmountNotEnoughException() {
    }

    public SubscriberAmountNotEnoughException(String message) {
        super(message);
    }

    public SubscriberAmountNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriberAmountNotEnoughException(Throwable cause) {
        super(cause);
    }
}
