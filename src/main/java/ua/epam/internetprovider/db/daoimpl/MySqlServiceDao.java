package ua.epam.internetprovider.db.daoimpl;

import ua.epam.internetprovider.db.ConnectionPool;
import ua.epam.internetprovider.db.dao.ServiceDao;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlServiceDao implements ServiceDao {
    private static final String FIND_SERVICE_BY_TITLE = "select id,title from service where title = ?";
    private static final String FIND_ALL_SERVICES = "select id,title from service";
    private static final String FIND_SERVICE_BY_ID = "select id,title from service where id = ?";
    private static final String SAVE_SERVICE = "insert into service(title) values(?)";


    @Override
    public Service getServiceByTitle(String title) {
        Service service = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_SERVICE_BY_TITLE);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                service = new Service();
                service.setId(rs.getInt("id"));
                service.setTitle(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return service;
    }

    @Override
    public List<Tariff> getServiceTariffs(Service service) {
        return null;
    }

    @Override
    public List<Service> findAll() {
        List<Service> services = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_SERVICES);
             ResultSet rs = ps.executeQuery()) {
            services = getListFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    @Override
    public Service getById(Long id) {
        Service service = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_SERVICE_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                service = getFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return service;
    }

    @Override
    public Service update(Service service) {
        return null;
    }

    @Override
    public void delete(Service service) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void save(Service service) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SAVE_SERVICE)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Service getFromResultSet(ResultSet rs) throws SQLException {
        Service service = new Service();
        if (rs.next()) {
            service = new Service();
            service.setId(rs.getInt("id"));
            service.setTitle(rs.getString("title"));
        }
        return service;
    }

    private List<Service> getListFromResultSet(ResultSet rs) throws SQLException {
        List<Service> services = new ArrayList<>();
        while (rs.next()) {
            Service service = new Service();
            service.setId(rs.getInt("id"));
            service.setTitle(rs.getString("title"));
            services.add(service);
        }
        return services;
    }
}
