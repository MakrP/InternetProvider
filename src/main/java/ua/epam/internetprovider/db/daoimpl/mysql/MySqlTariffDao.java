package ua.epam.internetprovider.db.daoimpl.mysql;

import ua.epam.internetprovider.db.dao.TariffDao;
import ua.epam.internetprovider.db.exception.DaoException;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlTariffDao extends TariffDao {
    private static final String FIND_ALL = "select id, title,price,service_id from tariff";
    private static final String SAVE = "insert into tariff(title,price,service_id) values (?,?,?)";
    private static final String FIND_N_TARIFFS_WITH_OFFSET = "select id, title,price,service_id from tariff where service_id = ? limit ?,? ";
    private static final String FIND_BY_ID = "select id, title,price,service_id from tariff where id = ?";
    private static final String FIND_BY_TITLE = "select id, title,price,service_id from tariff where title = ?";
    private static final String FIND_BY_SERVICE = "select id, title,price,service_id from tariff where service_id = ?";
    private static final String GET_TARIFFS_COUNT_FOR_SERVICE = "select count(*) as tariff_count from tariff where service_id = ?";
    private static final String GET_N_TARIFFS_WITH_OFFSET_SORT_BY_TITLE_ASC_FOR_SERVICE = "select id, title,price,service_id from tariff where service_id = ? order by title asc limit ?,? ";
    private static final String GET_N_TARIFFS_WITH_OFFSET_SORT_BY_TITLE_DESC_FOR_SERVICE = "select id, title,price,service_id from tariff where service_id = ? order by title desc limit ?,? ";
    private static final String GET_N_TARIFFS_WITH_OFFSET_SORT_BY_PRICE_ASC_FOR_SERVICE = "select id, title,price,service_id from tariff where service_id = ? order by price asc limit ?,?";
    private static final String GET_N_TARIFFS_WITH_OFFSET_SORT_BY_PRICE_DESC_FOR_SERVICE = "select id, title,price,service_id from tariff where service_id = ? order by price desc limit ?,?";
    private static final String UPDATE = "update tariff set title = ?, price = ? where id = ?";
    private static String FIND_ACTIVE_SUBSCRIBER_TARIFFS = "select t.id,t.title,t.price from tariff t join subscriber_tariff st on t.id = st.tariff_id " +
            "join subscriber s on s.account_id = st.subscriber_id where s.account_id = ? and st.status = 'ACTIVE'";


    @Override
    public List<Tariff> getServiceTariffs(Service service) throws DaoException {
        List<Tariff> tariffs = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_SERVICE)) {
            ps.setLong(1, service.getId());
            try (ResultSet rs = ps.executeQuery()) {
                tariffs = getTariffListFromResultSet(rs);
            }
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return tariffs;
    }


    @Override
    public Tariff getTariffByTitle(String title) throws DaoException {
        Tariff tariff = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_TITLE)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                tariff = getTariffFromResultSet(rs);
            }
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return tariff;
    }

    @Override
    public List<Tariff> getSubscriberTariffs(Subscriber subscriber) throws DaoException {
        List<Tariff> subscriberTariffs = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ACTIVE_SUBSCRIBER_TARIFFS)) {
            ps.setLong(1, subscriber.getAccountId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tariff tariff = new Tariff();
                    tariff.setId(rs.getLong("id"));
                    tariff.setTitle(rs.getString("title"));
                    tariff.setPrice(rs.getInt("price"));
                    subscriberTariffs.add(tariff);
                }
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return subscriberTariffs;
    }


    @Override
    public int getTariffsCountForService(Service service) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(GET_TARIFFS_COUNT_FOR_SERVICE)) {
            ps.setLong(1, service.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("tariff_count");
                }
            }
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return 0;
    }

    @Override
    public List<Tariff> getServiceTariffs(Service service, int offset, int count) throws DaoException {
        return getTariffsWithOffset(FIND_N_TARIFFS_WITH_OFFSET, service, offset, count);
    }

    @Override
    public List<Tariff> getServiceTariffsSortByTitleDesc(Service service, int offset, int count) throws DaoException {
        return getTariffsWithOffset(GET_N_TARIFFS_WITH_OFFSET_SORT_BY_TITLE_DESC_FOR_SERVICE, service, offset, count);
    }

    @Override
    public List<Tariff> getServiceTariffsSortByTitleAsc(Service service, int offset, int count) throws DaoException {
        return getTariffsWithOffset(GET_N_TARIFFS_WITH_OFFSET_SORT_BY_TITLE_ASC_FOR_SERVICE, service, offset, count);
    }

    @Override
    public List<Tariff> getServiceTariffsSortByPriceDesc(Service service, int offset, int count) throws DaoException {
        return getTariffsWithOffset(GET_N_TARIFFS_WITH_OFFSET_SORT_BY_TITLE_DESC_FOR_SERVICE, service, offset, count);
    }

    @Override
    public List<Tariff> getServiceTariffsSortByPriceAsc(Service service, int offset, int count) throws DaoException {
        return getTariffsWithOffset(GET_N_TARIFFS_WITH_OFFSET_SORT_BY_PRICE_ASC_FOR_SERVICE, service, offset, count);
    }


    public List<Tariff> getTariffsWithOffset(String query, Service service, int offset, int count) throws DaoException {
        List<Tariff> tariffs = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, service.getId());
            ps.setLong(2, offset);
            ps.setLong(3, count);
            try (ResultSet rs = ps.executeQuery()) {
                tariffs = getTariffListFromResultSet(rs);
            }
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return tariffs;
    }

    @Override
    public List<Tariff> findAll() throws DaoException {
        List<Tariff> tariffs = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            tariffs = getTariffListFromResultSet(rs);
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return tariffs;
    }

    @Override
    public Tariff getById(Long id) throws DaoException {
        Tariff tariff = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                tariff = getTariffFromResultSet(rs);
            }
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return tariff;
    }

    @Override
    public Tariff update(Tariff tariff) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, tariff.getTitle());
            ps.setInt(2, tariff.getPrice());
            ps.setLong(3, tariff.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return tariff;
    }

    @Override
    public void delete(Tariff tariff) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void save(Tariff tariff) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
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
            throw new DaoException();
        }
    }

    private Tariff getTariffFromResultSet(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        if (rs.next()) {
            tariff = new Tariff();
            tariff.setId(rs.getInt("id"));
            tariff.setTitle(rs.getString("title"));
            tariff.setPrice(rs.getInt("price"));
            tariff.setServiceId(rs.getInt("service_id"));
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
            tariff.setServiceId(rs.getInt("service_id"));
            tariffs.add(tariff);
        }
        return tariffs;
    }
}
