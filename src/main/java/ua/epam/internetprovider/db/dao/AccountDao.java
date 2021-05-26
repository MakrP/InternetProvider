package ua.epam.internetprovider.db.dao;

import ua.epam.internetprovider.db.exception.DaoException;
import ua.epam.internetprovider.entity.Account;

public abstract class AccountDao extends BaseDao<Account,Long>{
    public abstract Account getByLoginAndPassword(String login, String password) throws DaoException;
    public abstract Long getRoleIdByTitle(String title) throws DaoException;
}
