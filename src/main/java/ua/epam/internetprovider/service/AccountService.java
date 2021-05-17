package ua.epam.internetprovider.service;

import ua.epam.internetprovider.db.dao.AccountDao;
import ua.epam.internetprovider.db.daofactory.DaoFactory;
import ua.epam.internetprovider.db.daoimpl.mysql.MySqlAccountDao;
import ua.epam.internetprovider.entity.Account;

public class AccountService {
    private final AccountDao dao;

    public AccountService(String daoName) {
        dao = DaoFactory.getDaoFactory(daoName).getAccountDao();
    }

    public AccountService() {
        dao = DaoFactory.getDaoFactory().getAccountDao();
    }


    public Account getAccountByTokens(String login,String password) {
        return dao.getByLoginAndPassword(login,password);
    }

    public void saveAccount(Account account) {
        dao.save(account);
    }

}
