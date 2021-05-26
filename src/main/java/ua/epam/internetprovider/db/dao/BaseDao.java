package ua.epam.internetprovider.db.dao;

import ua.epam.internetprovider.db.exception.DaoException;
import ua.epam.internetprovider.entity.Entity;

import java.sql.Connection;
import java.util.List;

public abstract class BaseDao<T extends Entity,K> {
    protected Connection connection;
    public abstract List<T> findAll() throws DaoException;
    public abstract T getById(K id) throws DaoException;
    public abstract T update(T t) throws DaoException;
    public abstract void delete(T t);
    public abstract void delete(K id);
    public abstract void save(T t) throws DaoException;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
