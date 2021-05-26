package ua.epam.internetprovider.service.exception;

public class AlreadyHasTariffException extends Exception{
    public AlreadyHasTariffException() {
    }

    public AlreadyHasTariffException(String message) {
        super(message);
    }

    public AlreadyHasTariffException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyHasTariffException(Throwable cause) {
        super(cause);
    }
}
