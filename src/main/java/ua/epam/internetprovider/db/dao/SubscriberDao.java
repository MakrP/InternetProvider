package ua.epam.internetprovider.db.dao;

import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;

import java.util.List;

public interface SubscriberDao extends BaseDao<Subscriber,Long> {
    void addTariff(Subscriber subscriber, Tariff tariff);
    List<Tariff> getSubscriberTariffs(Subscriber subscriber);
    void increaseBalance(Subscriber subscriber, int total);
}
