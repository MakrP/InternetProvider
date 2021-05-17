package ua.epam.internetprovider.db.daofactory;

import ua.epam.internetprovider.db.dao.AccountDao;
import ua.epam.internetprovider.db.dao.ServiceDao;
import ua.epam.internetprovider.db.dao.SubscriberDao;
import ua.epam.internetprovider.db.dao.TariffDao;

public abstract class DaoFactory {
    private static String MySql = "MYSQl";


    public static DaoFactory getDaoFactory() {
        return MySqlDaoFactory.getInstance();
    }

    public static DaoFactory getDaoFactory(String name) {
        if(name.equals(MySql)) {
            return MySqlDaoFactory.getInstance();
        }
        return null;
    }

    public abstract AccountDao getAccountDao();
    public abstract ServiceDao getServiceDao();
    public abstract TariffDao getTariffDao();
    public abstract SubscriberDao getSubscriberDao();



}
