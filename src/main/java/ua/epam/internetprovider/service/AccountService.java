package ua.epam.internetprovider.service;

import ua.epam.internetprovider.db.dao.AccountDao;
import ua.epam.internetprovider.db.dao.ServiceDao;
import ua.epam.internetprovider.db.daoimpl.MySqlAccountDao;
import ua.epam.internetprovider.db.daoimpl.MySqlServiceDao;
import ua.epam.internetprovider.entity.Account;

public class AccountService {
    private final AccountDao dao = new MySqlAccountDao();
    
}
