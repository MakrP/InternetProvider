package ua.epam.internetprovider.service;

import ua.epam.internetprovider.db.dao.TariffDao;
import ua.epam.internetprovider.db.daoimpl.MySqlTariffDao;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;

import java.util.List;

public class TariffService {
    private final TariffDao dao = new MySqlTariffDao();

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
}
