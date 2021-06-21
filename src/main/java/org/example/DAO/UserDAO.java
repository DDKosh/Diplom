package org.example.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class UserDAO extends AbstractDAO{

    public String getByNameAndPass(final String name, final String pass) throws SQLException {
        String userRole = null;
        try (PreparedStatement statement = getRs(getConnection(), name, pass);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            userRole = resultSet.getString("ROLE");
        } catch (SQLException e) {
            System.err.println(e);
        }
        return userRole;
    }

    protected final PreparedStatement getRs(final Connection connection, String name, String pass) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?");
            statement.setString(1, name);
            statement.setString(2, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected Object getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected ResultSet getRsAll(Statement statement) throws SQLException {
        return null;
    }

    @Override
    protected ResultSet getRsAll(Statement statement, long id) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsById(Connection connection, long id) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsUpdate(Connection connection, Object object, long id) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsAdd(Connection connection, Object object) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsDelete(Connection connection, Long id) throws SQLException {
        return null;
    }
}
