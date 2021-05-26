package ua.epam.internetprovider.db.daoimpl.mysql;

import ua.epam.internetprovider.db.dao.SubscriberDao;
import ua.epam.internetprovider.db.exception.DaoException;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlSubscriberDao extends SubscriberDao {
    private static String FIND_ALL = "select * from subscriber";
    private static String FIND_BY_ACCOUNT_ID = "select * from subscriber where account_id = ?";
    private static String SAVE = "insert into subscriber(account_id,name,surname) values(?,?,?)";
    private static String DELETE_BY_ID = "delete from subscriber where account_id = ?";
    private static String ADD_TARIFF = "insert into subscriber_tariff(subscriber_id,tariff_id) values(?,?)";
    private static String UPDATE_BALANCE = "update subscriber set balance = balance + ? where account_id = ?";

    @Override
    public List<Subscriber> findAll() throws DaoException {
        List<Subscriber> subscribers = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            subscribers = getListFromResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException();
        }
        return subscribers;
    }

    @Override
    public Subscriber getById(Long id) throws DaoException {
        Subscriber subscriber = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ACCOUNT_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                subscriber = getFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DaoException();
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
    public void save(Subscriber subscriber) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
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
            throw new DaoException();
        }
    }


    @Override
    public void increaseBalance(Subscriber subscriber, int total) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_BALANCE)) {
            ps.setLong(1, total);
            ps.setLong(2, subscriber.getAccountId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException();
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
    public void addTariff(Subscriber subscriber, Tariff tariff) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(ADD_TARIFF)) {
            ps.setLong(1, subscriber.getAccountId());
            ps.setLong(2, tariff.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }


}
