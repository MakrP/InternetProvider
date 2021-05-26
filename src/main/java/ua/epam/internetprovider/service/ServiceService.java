package ua.epam.internetprovider.service;

import ua.epam.internetprovider.db.dao.ServiceDao;
import ua.epam.internetprovider.db.exception.DaoException;
import ua.epam.internetprovider.db.exception.TransactionException;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.service.exception.ServiceException;

import java.util.List;

public class ServiceService extends AbstractService {
    private ServiceDao dao;

    public List<Service> getAll() throws ServiceException {
        try {
            getTransaction().begin(dao);
            List<Service> allServices = dao.findAll();
            getTransaction().commit();
            return allServices;
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

    public Service getById(long id) throws ServiceException {
        try {
            getTransaction().begin(dao);
            Service service = dao.getById(id);
            getTransaction().commit();
            return service;
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

    public Service getByTitle(String title) throws ServiceException {
        try {
            getTransaction().begin(dao);
            Service service = dao.getServiceByTitle(title);
            getTransaction().commit();
            return service;
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

    public void setDao(ServiceDao dao) {
        this.dao = dao;
    }
}
