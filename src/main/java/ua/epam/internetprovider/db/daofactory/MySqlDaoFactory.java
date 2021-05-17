package ua.epam.internetprovider.db.daofactory;

import ua.epam.internetprovider.db.dao.AccountDao;
import ua.epam.internetprovider.db.dao.ServiceDao;
import ua.epam.internetprovider.db.dao.SubscriberDao;
import ua.epam.internetprovider.db.dao.TariffDao;
import ua.epam.internetprovider.db.daoimpl.mysql.MySqlAccountDao;
import ua.epam.internetprovider.db.daoimpl.mysql.MySqlServiceDao;
import ua.epam.internetprovider.db.daoimpl.mysql.MySqlSubscriberDao;
import ua.epam.internetprovider.db.daoimpl.mysql.MySqlTariffDao;

public class MySqlDaoFactory extends DaoFactory {

    private static MySqlDaoFactory mySqlDaoFactory;

    private MySqlDaoFactory() { }

    public static MySqlDaoFactory getInstance() {
            if (mySqlDaoFactory == null) {
                mySqlDaoFactory = new MySqlDaoFactory();
            }
            return mySqlDaoFactory;
    }


    @Override
    public AccountDao getAccountDao() {
        return new MySqlAccountDao();
    }


    @Override
    public ServiceDao getServiceDao() {
        return new MySqlServiceDao();
    }

    @Override
    public TariffDao getTariffDao() {
        return new MySqlTariffDao();
    }


    @Override
    public SubscriberDao getSubscriberDao() {
        return new MySqlSubscriberDao();
    }
}
