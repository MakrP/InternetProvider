package ua.epam.internetprovider.db.daoimpl.mysql;

import ua.epam.internetprovider.db.ConnectionPool;
import ua.epam.internetprovider.db.dao.TariffDao;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTariffDao implements TariffDao {
    private static final String FIND_ALL = "select id, title,price from tariff";
    private static final String SAVE = "insert into tariff(title,price,service_id) values (?,?,?)";
    private static final String FIND_N_TARIFFS_WITH_OFFSET = "select id, title,price from tariff where service_id = ? limit ?,? ";
    private static final String FIND_BY_ID = "select id, title,price from tariff where id = ?";
    private static final String FIND_BY_TITLE = "select id, title,price from tariff where title = ?";
    private static final String FIND_BY_SERVICE = "select id, title,price from tariff where service_id = ?";
    private static final String FIND_TARIFF_SERVICE = "select s.id, s.title from tariff t join service s " +
            "on t.service_id = s.id where t.id = ?";

    private static final String GET_TARIFFS_COUNT_FOR_SERVICE = "select count(*) as tariff_count from tariff where service_id = ?";
    private static final String GET_N_TARIFFS_WITH_OFFSET_SORT_BY_TITLE_ASC_FOR_SERVICE = "select id,title,price from tariff where service_id = ? order by title asc limit ?,? ";
    private static final String GET_N_TARIFFS_WITH_OFFSET_SORT_BY_TITLE_DESC_FOR_SERVICE = "select id,title,price from tariff where service_id = ? order by title desc limit ?,? ";
    private static final String GET_N_TARIFFS_WITH_OFFSET_SORT_BY_PRICE_ASC_FOR_SERVICE = "select id,title,price from tariff where service_id = ? order by price asc limit ?,?";
    private static final String GET_N_TARIFFS_WITH_OFFSET_SORT_BY_PRICE_DESC_FOR_SERVICE = "select id,title,price from tariff where service_id = ? order by price desc limit ?,?";

    @Override
    public List<Tariff> getServiceTariffs(Service service) {
        List<Tariff> tariffs = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_SERVICE)) {
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
             PreparedStatement ps = connection.prepareStatement(FIND_BY_TITLE)) {
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
    public Service getTariffService(Tariff tariff) {
        Service service = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_TARIFF_SERVICE)) {
            ps.setLong(1, tariff.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    service = new Service();
                    service.setId(rs.getInt("id"));
                    service.setTitle(rs.getString("title"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return service;
    }

    @Override
    public int getTariffsCountForService(Service service) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_TARIFFS_COUNT_FOR_SERVICE)) {
            ps.setLong(1, service.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("tariff_count");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Tariff> getServiceTariffs(Service service, int offset, int count) {
        return getTariffsWithOffset(FIND_N_TARIFFS_WITH_OFFSET,service,offset,count);
    }

    @Override
    public List<Tariff> getServiceTariffsSortByTitleDesc(Service service, int offset, int count) {
        return getTariffsWithOffset(GET_N_TARIFFS_WITH_OFFSET_SORT_BY_TITLE_DESC_FOR_SERVICE,service,offset,count);
    }

    @Override
    public List<Tariff> getServiceTariffsSortByTitleAsc(Service service, int offset, int count) {
        return getTariffsWithOffset(GET_N_TARIFFS_WITH_OFFSET_SORT_BY_TITLE_ASC_FOR_SERVICE,service,offset,count);
    }

    @Override
    public List<Tariff> getServiceTariffsSortByPriceDesc(Service service, int offset, int count) {
        return getTariffsWithOffset(GET_N_TARIFFS_WITH_OFFSET_SORT_BY_TITLE_DESC_FOR_SERVICE,service,offset,count);
    }

    @Override
    public List<Tariff> getServiceTariffsSortByPriceAsc(Service service, int offset, int count) {
        return getTariffsWithOffset(GET_N_TARIFFS_WITH_OFFSET_SORT_BY_PRICE_ASC_FOR_SERVICE,service,offset,count);
    }


    public List<Tariff> getTariffsWithOffset(String query,Service service, int offset, int count) {
        List<Tariff> tariffs = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, service.getId());
            ps.setLong(2, offset);
            ps.setLong(3, count);
            try (ResultSet rs = ps.executeQuery()) {
                tariffs = getTariffListFromResultSet(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tariffs;
    }

    @Override
    public List<Tariff> findAll() {
        List<Tariff> tariffs = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL);
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
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
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
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, tariff.getTitle());
            ps.setInt(2, tariff.getPrice());
            ps.setLong(3, tariff.getServiceId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    tariff.setId(rs.getLong(1));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
