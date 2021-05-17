package ua.epam.internetprovider.db.daoimpl.mysql;

import ua.epam.internetprovider.db.ConnectionPool;
import ua.epam.internetprovider.db.dao.SubscriberDao;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlSubscriberDao implements SubscriberDao {
    private static String FIND_ALL = "select * from subscriber";
    private static String FIND_BY_ACCOUNT_ID = "select * from subscriber where account_id = ?";
    private static String SAVE = "insert into subscriber(account_id,name,surname) values(?,?,?)";
    private static String DELETE_BY_ID = "delete from subscriber where account_id = ?";
    private static String ADD_TARIFF = "insert into subscriber_tariff(subscriber_id,tariff_id) values(?,?)";
    private static String FIND_ACTIVE_SUBSCRIBER_TARIFFS = "select t.id,t.title,t.price from tariff t join subscriber_tariff st on t.id = st.tariff_id " +
            "join subscriber s on s.account_id = st.subscriber_id where s.account_id = ? and st.status = 'ACTIVE'";
    private static String UPDATE_BALANCE = "update subscriber set balance = balance + ? where account_id = ?";

    @Override
    public List<Subscriber> findAll() {
        List<Subscriber> subscribers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            subscribers = getListFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscribers;
    }

    @Override
    public Subscriber getById(Long id) {
        Subscriber subscriber = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ACCOUNT_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                subscriber = getFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriber;
    }

    @Override
    public Subscriber update(Subscriber subscriber) {
        return null;
    }

    @Override
    public void delete(Subscriber subscriber) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void save(Subscriber subscriber) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, subscriber.getAccountId());
            ps.setString(2, subscriber.getName());
            ps.setString(3, subscriber.getSurname());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    subscriber.setAccountId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tariff> getSubscriberTariffs(Subscriber subscriber) {
        List<Tariff> subscriberTariffs = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ACTIVE_SUBSCRIBER_TARIFFS)) {
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
            e.printStackTrace();
        }
        return subscriberTariffs;
    }

    @Override
    public void increaseBalance(Subscriber subscriber, int total) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_BALANCE)) {
            ps.setLong(1, total);
            ps.setLong(2, subscriber.getAccountId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Subscriber getFromResultSet(ResultSet rs) throws SQLException {
        Subscriber subscriber = null;
        if (rs.next()) {
            subscriber = new Subscriber();
            subscriber.setAccountId(rs.getInt("account_id"));
            subscriber.setName(rs.getString("name"));
            subscriber.setSurname(rs.getString("surname"));
            subscriber.setBalance(rs.getInt("balance"));
            subscriber.setStatus(rs.getString("status"));
        }
        return subscriber;
    }

    private List<Subscriber> getListFromResultSet(ResultSet rs) throws SQLException {
        List<Subscriber> subscribers = new ArrayList<>();
        while (rs.next()) {
            Subscriber subscriber = new Subscriber();
            subscriber.setAccountId(rs.getInt("account_id"));
            subscriber.setName(rs.getString("name"));
            subscriber.setSurname(rs.getString("surname"));
            subscriber.setBalance(rs.getInt("balance"));
            subscriber.setStatus(rs.getString("status"));
            subscribers.add(subscriber);
        }
        return subscribers;
    }

    @Override
    public void addTariff(Subscriber subscriber, Tariff tariff) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(ADD_TARIFF)) {
            ps.setLong(1, subscriber.getAccountId());
            ps.setLong(2, tariff.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
