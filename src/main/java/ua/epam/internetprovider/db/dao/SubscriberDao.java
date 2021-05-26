package ua.epam.internetprovider.db.dao;

import ua.epam.internetprovider.db.exception.DaoException;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;

public abstract class SubscriberDao extends BaseDao<Subscriber,Long> {
    public abstract void addTariff(Subscriber subscriber, Tariff tariff) throws DaoException;
    public abstract void increaseBalance(Subscriber subscriber, int total) throws DaoException;
}
