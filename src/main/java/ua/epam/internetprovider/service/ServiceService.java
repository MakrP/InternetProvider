package ua.epam.internetprovider.service;

import ua.epam.internetprovider.db.dao.ServiceDao;
import ua.epam.internetprovider.db.daoimpl.MySqlServiceDao;
import ua.epam.internetprovider.entity.Service;

import java.util.List;

public class ServiceService {
    private final ServiceDao dao = new MySqlServiceDao();

    public List<Service> getAll() {
        return dao.findAll();
    }

    public Service getById(long id) {
        return dao.getById(id);
    }

    public Service getByTitle(String title) {
        return dao.getServiceByTitle(title);
    }
}
