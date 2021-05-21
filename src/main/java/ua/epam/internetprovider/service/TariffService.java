package ua.epam.internetprovider.service;

import ua.epam.internetprovider.db.dao.TariffDao;
import ua.epam.internetprovider.db.daofactory.DaoFactory;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.exception.ServiceException;

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

    public void save(Tariff tariff) {
        dao.save(tariff);
    }

    public List<Tariff> getServiceTariffs(Service service, int offset, int count,String orderField, String order) throws ServiceException {
        boolean isAsc = (order == null || order.equals("") ||  order.equals("asc"));
        if(orderField == null || orderField.equals("")) {
            return dao.getServiceTariffs(service,offset,count);
        }
        if(orderField.equals("title")) {
            if(isAsc) {
                return dao.getServiceTariffsSortByTitleAsc(service,offset,count);
            }
            return dao.getServiceTariffsSortByTitleDesc(service,offset,count);
        } else if(orderField.equals("price")) {
            if(isAsc) {
                return dao.getServiceTariffsSortByPriceAsc(service,offset,count);
            }
            return dao.getServiceTariffsSortByPriceDesc(service,offset,count);
        }
        throw new ServiceException("No Such Sort");
    }


}
