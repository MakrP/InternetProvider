package ua.epam.internetprovider.db.dao;

import ua.epam.internetprovider.db.ConnectionPool;
import ua.epam.internetprovider.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDao<T extends Entity,K> {
    List<T> findAll();
    T getById(K id);
    T update(T t);
    void delete(T t);
    void delete(K id);
    void save(T t);

    default void close(Statement statement) {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    default void close(Connection connection) {
        if(connection != null) {
            ConnectionPool.releaseConnection(connection);
        }
    }
}
