package ua.epam.internetprovider.service;

import ua.epam.internetprovider.db.dao.AccountDao;
import ua.epam.internetprovider.db.exception.DaoException;
import ua.epam.internetprovider.db.exception.TransactionException;
import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.service.exception.ServiceException;

public class AccountService extends AbstractService {
    private AccountDao dao;

    public Account getAccountByTokens(String login, String password) throws ServiceException {
        try {
            getTransaction().begin(dao);
            Account account = dao.getByLoginAndPassword(login, password);
            getTransaction().commit();
            return account;
        } catch (DaoException daoException) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) { }
            throw new ServiceException(daoException);
        } catch (TransactionException transactionException) {
            throw new ServiceException(transactionException);
        }
    }

    public void saveAccount(Account account) throws ServiceException {
        try {
            getTransaction().begin(dao);
            dao.save(account);
            getTransaction().commit();
        } catch (DaoException daoException) {
            try {
                getTransaction().rollback();
            } catch (TransactionException transactionException) { }
                throw new ServiceException(daoException);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    public void setDao(AccountDao dao) {
        this.dao = dao;
    }
}
