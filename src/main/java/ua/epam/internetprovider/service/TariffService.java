package ua.epam.internetprovider.service;

import ua.epam.internetprovider.db.dao.TariffDao;
import ua.epam.internetprovider.db.daofactory.DaoFactory;
import ua.epam.internetprovider.db.daoimpl.mysql.MySqlTariffDao;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;

import java.util.List;

public class TariffService {
    private final TariffDao dao;

    public TariffService(String daoName) {
        dao = DaoFactory.getDaoFactory(daoName).getTariffDao();
    }

    public TariffService() {
        dao = DaoFactory.getDaoFactory().getTariffDao();
    }

    public List<Tariff> getAll() {
        return dao.findAll();
    }
    public List<Tariff> getServiceTariffs(Service service) {
        return dao.getServiceTariffs(service);
    }
    public Tariff getById(long id) {
        return dao.getById(id);
    }

    public Tariff getByTitle(String title) {
        return dao.getTariffByTitle(title);
    }
    public Service getTariffService(Tariff tariff) {
        return dao.getTariffService(tariff);
    }

    public int getTariffsCountOfService(Service service) {
        return dao.getTariffsCountForService(service);
    }
    public List<Tariff> getServiceTariffs(Service service,int offset,int count) {
        return dao.getServiceTariffs(service,offset,count);
    }

    public void save(Tariff tariff) {
        dao.save(tariff);
    }


}
