package ua.epam.internetprovider.service.exception;

import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;

public class HasTariffOfSameServiceException extends Exception {
    private Tariff hasTariff;
    private Service service;

    public HasTariffOfSameServiceException(Tariff hasTariff,Service service) {
        this.hasTariff = hasTariff;
        this.service = service;
    }

    public HasTariffOfSameServiceException() {
    }

    public HasTariffOfSameServiceException(String message) {
        super(message);
    }

    public HasTariffOfSameServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public HasTariffOfSameServiceException(Throwable cause) {
        super(cause);
    }

    public Tariff getHasTariff() {
        return hasTariff;
    }

    public Service getService() {
        return service;
    }

}
