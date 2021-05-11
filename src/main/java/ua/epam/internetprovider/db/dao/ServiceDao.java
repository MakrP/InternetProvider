package ua.epam.internetprovider.db.dao;

import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;

import java.util.List;

public interface ServiceDao extends BaseDao<Service,Long> {
    Service getServiceByTitle(String title);
    List<Tariff> getServiceTariffs(Service service);
}
