package ua.epam.internetprovider.db.dao;

import ua.epam.internetprovider.entity.Account;

public interface AccountDao extends BaseDao<Account,Long>{
    Account getByLoginAndPassword(String login, String password);
    Long getRoleIdByTitle(String title);
}
