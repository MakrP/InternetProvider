package ua.epam.internetprovider.db;

import ua.epam.internetprovider.db.dao.BaseDao;
import ua.epam.internetprovider.db.exception.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
    private Connection connection;

    public void begin(BaseDao dao, BaseDao... daos) throws TransactionException {
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            dao.setConnection(connection);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
        for (BaseDao daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    public void commit() throws TransactionException {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    public void rollback() throws TransactionException {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }


}
