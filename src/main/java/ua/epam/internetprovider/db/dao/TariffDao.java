package ua.epam.internetprovider.db.dao;

import ua.epam.internetprovider.db.exception.DaoException;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;

import java.util.List;

public abstract class TariffDao extends BaseDao<Tariff, Long> {
    public abstract List<Tariff> getServiceTariffs(Service service) throws DaoException;

    public abstract Tariff getTariffByTitle(String title) throws DaoException;

    public abstract List<Tariff> getSubscriberTariffs(Subscriber subscriber) throws DaoException;

    public abstract int getTariffsCountForService(Service service) throws DaoException;

    public abstract List<Tariff> getServiceTariffs(Service service, int offset, int count) throws DaoException;

    public abstract List<Tariff> getServiceTariffsSortByTitleDesc(Service service, int offset, int count) throws DaoException;

    public abstract List<Tariff> getServiceTariffsSortByTitleAsc(Service service, int offset, int count) throws DaoException;

    public abstract List<Tariff> getServiceTariffsSortByPriceDesc(Service service, int offset, int count) throws DaoException;

    public abstract List<Tariff> getServiceTariffsSortByPriceAsc(Service service, int offset, int count) throws DaoException;

}
