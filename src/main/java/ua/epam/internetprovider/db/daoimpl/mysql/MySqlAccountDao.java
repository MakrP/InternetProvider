package ua.epam.internetprovider.db.daoimpl.mysql;

import ua.epam.internetprovider.db.dao.AccountDao;
import ua.epam.internetprovider.db.exception.DaoException;
import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlAccountDao extends AccountDao {

    private static final String FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD = "select a.id,a.login,a.password,r.title as role from account a " +
            "join role r on a.role_id = r.id where a.login = ? and a.password = ?";

    private static final String SAVE_ACCOUNT = "insert into account (login,password,role_id) values(?,?,?)";
    private static final String FIND_ROLE_ID_BY_TITLE = "select id from role where title = ?";

    @Override
    public Account getByLoginAndPassword(String login, String password) throws DaoException {
        Account account = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD)) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                account = getFromResultSet(rs);
            }
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return account;
    }

    @Override
    public Long getRoleIdByTitle(String title) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(FIND_ROLE_ID_BY_TITLE)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("id");
                }
            }
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return null;
    }


    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Account getById(Long id) {
        return null;
    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void save(Account account) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(SAVE_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, account.getLogin());
            ps.setString(2, account.getPassword());
            ps.setLong(3, getRoleIdByTitle(account.getRole().name()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    account.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    private Account getFromResultSet(ResultSet rs) throws SQLException {
        Account account = null;
        if (rs.next()) {
            account = new Account();
            account.setId(rs.getInt("id"));
            account.setLogin(rs.getString("login"));
            account.setPassword(rs.getString("password"));
            account.setRole(Role.valueOf(rs.getString("role")));

        }
        return account;
    }

    private List<Account> getListFromResultSet(ResultSet rs) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        while (rs.next()) {
            Account account = new Account();
            account.setId(rs.getInt("id"));
            account.setLogin(rs.getString("login"));
            account.setPassword(rs.getString("password"));
            account.setRole(Role.valueOf(rs.getString("role")));
            accounts.add(account);
        }
        return accounts;
    }
}
