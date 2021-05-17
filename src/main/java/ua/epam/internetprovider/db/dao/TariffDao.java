package ua.epam.internetprovider.db.dao;

import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;

import java.util.List;

public interface TariffDao extends BaseDao<Tariff,Long> {
    List<Tariff> getServiceTariffs(Service service);
    Tariff getTariffByTitle(String title);
    Service getTariffService(Tariff tariff);
    int getTariffsCountForService(Service service);
    List<Tariff> getServiceTariffs(Service service,int offset,int count);
}
