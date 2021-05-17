package ua.epam.internetprovider.service;

import ua.epam.internetprovider.db.dao.SubscriberDao;
import ua.epam.internetprovider.db.daofactory.DaoFactory;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;

import java.util.List;

public class SubscriberService {
    private final SubscriberDao dao;

    public SubscriberService(String daoName) {
        dao = DaoFactory.getDaoFactory(daoName).getSubscriberDao();
    }

    public SubscriberService() {
        dao = DaoFactory.getDaoFactory().getSubscriberDao();
    }

    public void save(Subscriber subscriber) {
        dao.save(subscriber);
    }

    public List<Subscriber> getAll() {
        return dao.findAll();
    }

    public Subscriber getById(long id) {
        return dao.getById(id);
    }

    public void addTariff(Subscriber subscriber, Tariff tariff) {
        dao.addTariff(subscriber, tariff);
    }

    public List<Tariff> getActiveTariffs(Subscriber subscriber) {
        return dao.getSubscriberTariffs(subscriber);
    }

    public void increaseBalance(Subscriber subscriber, int total) {
        dao.increaseBalance(subscriber, total);
    }

}
