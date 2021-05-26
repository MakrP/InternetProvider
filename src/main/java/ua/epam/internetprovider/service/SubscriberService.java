package ua.epam.internetprovider.service;

import ua.epam.internetprovider.db.dao.ServiceDao;
import ua.epam.internetprovider.db.dao.SubscriberDao;
import ua.epam.internetprovider.db.dao.TariffDao;
import ua.epam.internetprovider.db.exception.DaoException;
import ua.epam.internetprovider.db.exception.TransactionException;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.exception.AlreadyHasTariffException;
import ua.epam.internetprovider.service.exception.HasTariffOfSameServiceException;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.exception.SubscriberAmountNotEnoughException;

import java.util.List;

public class SubscriberService extends AbstractService {
    private SubscriberDao subscriberDao;
    private TariffDao tariffDao;
    private ServiceDao serviceDao;

    public void save(Subscriber subscriber) throws ServiceException {
        try {
            getTransaction().begin(subscriberDao);
            subscriberDao.save(subscriber);
            getTransaction().commit();
        } catch (DaoException daoException) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(daoException);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }


    public List<Subscriber> getAll() throws ServiceException {
        try {
            getTransaction().begin(subscriberDao);
            List<Subscriber> allSubscribers = subscriberDao.findAll();
            getTransaction().commit();
            return allSubscribers;
        } catch (DaoException daoException) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(daoException);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }


    public Subscriber getById(long id) throws ServiceException {
        try {
            getTransaction().begin(subscriberDao);
            Subscriber subscriber = subscriberDao.getById(id);
            getTransaction().commit();
            return subscriber;
        } catch (DaoException daoException) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(daoException);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }


    public void subscribeOnTariff(Subscriber subscriber, Tariff tariff, boolean confirmation) throws ServiceException, AlreadyHasTariffException, HasTariffOfSameServiceException, SubscriberAmountNotEnoughException {
        try {
            getTransaction().begin(subscriberDao,tariffDao,serviceDao);
            if (subscriber.getBalance() < tariff.getPrice()) {
                throw new SubscriberAmountNotEnoughException();
            }
            List<Tariff> subscriberTariffs = tariffDao.getSubscriberTariffs(subscriber);
            if (subscriberTariffs.contains(tariff)) {
                throw new AlreadyHasTariffException();
            }
            if (!confirmation) {
                for (Tariff tariffElement : subscriberTariffs) {
                    if (tariffElement.getServiceId() == tariff.getServiceId()) {
                        Service service = serviceDao.getTariffService(tariffElement);
                        throw new HasTariffOfSameServiceException(tariffElement, service);
                    }
                }
            }
            subscriberDao.addTariff(subscriber, tariff);
            getTransaction().commit();
        } catch (DaoException daoException) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(daoException);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }

    public List<Tariff> getActiveTariffs(Subscriber subscriber) throws ServiceException {
        try {
            getTransaction().begin(tariffDao);
            List<Tariff> activeTariffs = tariffDao.getSubscriberTariffs(subscriber);
            getTransaction().commit();
            return activeTariffs;
        } catch (DaoException daoException) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(daoException);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }

    public void increaseBalance(Subscriber subscriber, int total) throws ServiceException {
        try {
            getTransaction().begin(subscriberDao);
            subscriberDao.increaseBalance(subscriber, total);
            getTransaction().commit();
        } catch (DaoException daoException) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(daoException);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }

    public void setSubscriberDao(SubscriberDao subscriberDao) {
        this.subscriberDao = subscriberDao;
    }

    public void setTariffDao(TariffDao tariffDao) {
        this.tariffDao = tariffDao;
    }

    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
}
