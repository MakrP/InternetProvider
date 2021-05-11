package ua.epam.internetprovider.db.daoimpl;

import ua.epam.internetprovider.db.ConnectionPool;
import ua.epam.internetprovider.db.dao.TariffDao;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlTariffDao implements TariffDao {
    private static final String FIND_ALL_TARIFFS = "select id, title,price from tariff";
    private static final String FIND_TARIFF_BY_ID = "select id, title,price from tariff where id = ?";
    private static final String FIND_TARIFF_BY_TITLE = "select id, title,price from tariff where title = ?";
    private static final String FIND_TARIFFS_BY_SERVICE = "select id, title,price from tariff where service_id = ?";

    @Override
    public List<Tariff> getServiceTariffs(Service service) {
        List<Tariff> tariffs = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_TARIFFS_BY_SERVICE)) {
            ps.setLong(1, service.getId());
            try (ResultSet rs = ps.executeQuery()) {
                tariffs = getTariffListFromResultSet(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tariffs;
    }

    @Override
    public Tariff getTariffByTitle(String title) {
        Tariff tariff = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_TARIFF_BY_TITLE)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                tariff = getTariffFromResultSet(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tariff;
    }

    @Override
    public List<Tariff> findAll() {
        List<Tariff> tariffs = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_TARIFFS);
             ResultSet rs = ps.executeQuery()) {
            tariffs = getTariffListFromResultSet(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tariffs;
    }

    @Override
    public Tariff getById(Long id) {
        Tariff tariff = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_TARIFF_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                tariff = getTariffFromResultSet(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tariff;
    }

    @Override
    public Tariff update(Tariff tariff) {
        return null;
    }

    @Override
    public void delete(Tariff tariff) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void save(Tariff tariff) {

    }

    private Tariff getTariffFromResultSet(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        if (rs.next()) {
            tariff = new Tariff();
            tariff.setId(rs.getInt("id"));
            tariff.setTitle(rs.getString("title"));
            tariff.setPrice(rs.getInt("price"));
        }
        return tariff;
    }

    private List<Tariff> getTariffListFromResultSet(ResultSet rs) throws SQLException {
        List<Tariff> tariffs = new ArrayList<>();
        while (rs.next()) {
            Tariff tariff = new Tariff();
            tariff.setId(rs.getInt("id"));
            tariff.setTitle(rs.getString("title"));
            tariff.setPrice(rs.getInt("price"));
            tariffs.add(tariff);
        }
        return tariffs;
    }
}
