package org.example.DAO;

import org.example.DTO.Patient;
import org.example.DTO.RecipePriority;
import org.example.DTO.Specialization;

import java.sql.*;

public class SpecializationDAO extends AbstractDAO<String> {
    @Override
    public final String getObject(final ResultSet resultSet) throws SQLException {
        return resultSet.getString("NAME");
        /*return new Specialization(resultSet.getLong("ID"),
                resultSet.getString("NAME"));*/
    }

    @Override
    public final ResultSet getRsAll(final Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM \"PUBLIC\".\"SPECIALIZATION\"");
    }

    @Override
    public final PreparedStatement getPsById(final Connection connection, final long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM Specialization WHERE ID = ?");
            statement.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsUpdate(Connection connection, String object, long id) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsAdd(Connection connection, String object) throws SQLException {
        return null;
    }


    @Override
    protected PreparedStatement getPsDelete(Connection connection, Long id) throws SQLException {
        return null;
    }
}
