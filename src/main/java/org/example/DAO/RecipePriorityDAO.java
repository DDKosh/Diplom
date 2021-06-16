package org.example.DAO;

import org.example.DTO.RecipePriority;

import java.sql.*;

/**
 * The  Recipe dao.
 */
public class RecipePriorityDAO extends AbstractDAO<RecipePriority> {
    @Override
    protected final RecipePriority getObject(final ResultSet resultSet) throws SQLException {
        return RecipePriority.valueOf(resultSet.getString("PRIORITY"));
    }

    @Override
    protected final ResultSet getRsAll(final Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM RecipePriority");
    }

    @Override
    protected ResultSet getRsAll(Statement statement, long id) throws SQLException {
        return null;
    }

    @Override
    protected final PreparedStatement getPsById(final Connection connection, final long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM RecipePriority WHERE ID = ?");
            statement.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected final PreparedStatement getPsAdd(
            final Connection connection, final RecipePriority recipePriority) throws SQLException {
        return null;
    }

    @Override
    protected final PreparedStatement getPsUpdate(final Connection connection,
                                            final RecipePriority recipePriority, final long id) throws SQLException {
        return null;
    }

    @Override
    protected final PreparedStatement getPsDelete(final Connection connection, final Long id) throws SQLException {
        return null;
    }
}
