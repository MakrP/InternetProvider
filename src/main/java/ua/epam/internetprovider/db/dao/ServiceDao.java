package ua.epam.internetprovider.db.dao;

import ua.epam.internetprovider.db.exception.DaoException;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;

public abstract class ServiceDao extends BaseDao<Service,Long> {
    public abstract Service getServiceByTitle(String title) throws DaoException;
    public abstract Service getTariffService(Tariff tariff) throws DaoException;
}
