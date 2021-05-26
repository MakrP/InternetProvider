package ua.epam.internetprovider.service;

import ua.epam.internetprovider.db.dao.ServiceDao;
import ua.epam.internetprovider.db.dao.TariffDao;
import ua.epam.internetprovider.db.exception.DaoException;
import ua.epam.internetprovider.db.exception.TransactionException;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.exception.ServiceException;

import java.util.List;

public class TariffService extends AbstractService {
    private TariffDao tariffDao;
    private ServiceDao serviceDao;

    public List<Tariff> getAll() throws ServiceException {
        try {
            getTransaction().begin(tariffDao);
            List<Tariff> allTariffs = tariffDao.findAll();
            getTransaction().commit();
            return allTariffs;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(e);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }

    public Tariff getById(long id) throws ServiceException {
        try {
            getTransaction().begin(tariffDao);
            Tariff tariff = tariffDao.getById(id);
            getTransaction().commit();
            return tariff;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(e);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }

    public Service getTariffService(Tariff tariff) throws ServiceException {
        try {
            getTransaction().begin(serviceDao);
            Service tariffService = serviceDao.getTariffService(tariff);
            getTransaction().commit();
            return tariffService;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(e);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }

    public int getTariffsCountOfService(Service service) throws ServiceException {
        try {
            getTransaction().begin(tariffDao);
            int tariffsServiceCount = tariffDao.getTariffsCountForService(service);
            getTransaction().commit();
            return tariffsServiceCount;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(e);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }

    public void save(Tariff tariff) throws ServiceException {
        try {
            getTransaction().begin(tariffDao);
            tariffDao.save(tariff);
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(e);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }

    public List<Tariff> getServiceTariffs(Service service, int offset, int count, String orderField, String order) throws ServiceException {
        boolean isAsc = (order == null || order.equals("") || order.equals("asc"));
        try {
            getTransaction().begin(tariffDao);
            List<Tariff> orderedTariffs = null;
            if (orderField == null || orderField.equals("")) {
                orderedTariffs = tariffDao.getServiceTariffs(service, offset, count);
                getTransaction().commit();
                return orderedTariffs;
            }
            if (orderField.equals("title")) {
                if (isAsc) {
                    orderedTariffs = tariffDao.getServiceTariffsSortByTitleAsc(service, offset, count);
                }
                return tariffDao.getServiceTariffsSortByTitleDesc(service, offset, count);
            } else if (orderField.equals("price")) {
                if (isAsc) {
                    orderedTariffs = tariffDao.getServiceTariffsSortByPriceAsc(service, offset, count);
                }
                orderedTariffs = tariffDao.getServiceTariffsSortByPriceDesc(service, offset, count);
            } else {
                throw new ServiceException("No Such Sort");
            }
            getTransaction().commit();
            return orderedTariffs;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(e);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }

    public Tariff update(Tariff tariff) throws ServiceException {
        try {
            getTransaction().begin(tariffDao);
            Tariff updatedTariff = tariffDao.update(tariff);
            getTransaction().commit();
            return updatedTariff;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) {
            }
            throw new ServiceException(e);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }

    public void setTariffDao(TariffDao tariffDao) {
        this.tariffDao = tariffDao;
    }

    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
}
