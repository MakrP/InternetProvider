package ua.epam.internetprovider.service.factory;

import ua.epam.internetprovider.db.Transaction;
import ua.epam.internetprovider.db.daofactory.DaoFactory;
import ua.epam.internetprovider.service.AccountService;
import ua.epam.internetprovider.service.ServiceService;
import ua.epam.internetprovider.service.SubscriberService;
import ua.epam.internetprovider.service.TariffService;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static AccountService getAccountService() {
        AccountService accountService = new AccountService();
        accountService.setDao(DaoFactory.getDaoFactory().getAccountDao());
        accountService.setTransaction(new Transaction());
        return accountService;
    }

    public static ServiceService getServiceService() {
        ServiceService serviceService = new ServiceService();
        serviceService.setDao(DaoFactory.getDaoFactory().getServiceDao());
        serviceService.setTransaction(new Transaction());
        return serviceService;
    }

    public static SubscriberService getSubscriberService() {
        SubscriberService subscriberService = new SubscriberService();
        subscriberService.setSubscriberDao(DaoFactory.getDaoFactory().getSubscriberDao());
        subscriberService.setServiceDao(DaoFactory.getDaoFactory().getServiceDao());
        subscriberService.setTariffDao(DaoFactory.getDaoFactory().getTariffDao());
        subscriberService.setTransaction(new Transaction());
        return subscriberService;
    }

    public static TariffService getTariffService() {
        TariffService tariffService = new TariffService();
        tariffService.setTariffDao(DaoFactory.getDaoFactory().getTariffDao());
        tariffService.setServiceDao(DaoFactory.getDaoFactory().getServiceDao());
        tariffService.setTransaction(new Transaction());
        return tariffService;
    }
}
