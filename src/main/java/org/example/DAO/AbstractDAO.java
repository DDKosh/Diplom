package org.example.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Abstract dao.
 *
 * @param <E> the type parameter
 */
public abstract class AbstractDAO<E> {
    /**
     * Gets object.
     *
     * @param resultSet the result set
     * @return the object
     * @throws SQLException the sql exception
     */
    protected abstract E getObject(ResultSet resultSet) throws SQLException;

    /**
     * Gets ps all.
     *
     * @param statement the statement
     * @return the rs all
     * @throws SQLException the sql exception
     */
    protected abstract ResultSet getRsAll(Statement statement)
            throws SQLException;

    /**
     * Gets ps by id.
     *
     * @param connection the connection
     * @param id         the id
     * @return the ps by id
     * @throws SQLException the sql exception
     */
    protected abstract PreparedStatement getPsById(
            Connection connection, long id) throws SQLException;

    /**
     * Gets ps update.
     *
     * @param connection the connection
     * @param object     the object
     * @param id         the id
     * @return the ps update
     * @throws SQLException the sql exception
     */
    protected abstract PreparedStatement getPsUpdate(
            Connection connection, E object, long id) throws SQLException;

    /**
     * Gets ps add.
     *
     * @param connection the connection
     * @param object     the object
     * @return the ps add
     * @throws SQLException the sql exception
     */
    protected abstract PreparedStatement getPsAdd(
            Connection connection, E object) throws SQLException;

    /**
     * Gets ps delete.
     *
     * @param connection the connection
     * @param id         the id
     * @return the ps delete
     * @throws SQLException the sql exception
     */
    protected abstract PreparedStatement getPsDelete(
            Connection connection, Long id) throws SQLException;

    /**
     * The Hsqldb.
     */
    private final HsqldbDAO hsqldb = HsqldbDAO.getHsqldb();

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    hsqldb.getURL(), hsqldb.getUser(), hsqldb.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<E> getAll() {
        List<E> list = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = getRsAll(statement)) {
            while (resultSet.next()) {
                list.add(getObject(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return list;
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws SQLException the sql exception
     */
    public E getById(final long id) throws SQLException {
        E object = null;
        try (PreparedStatement statement = getPsById(getConnection(), id);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            object = getObject(resultSet);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return object;
    }

    /**
     * Update.
     *
     * @param object the object
     * @param id     the id
     * @throws SQLException the sql exception
     */
    public void update(final E object, final long id) throws SQLException {
        try {
            PreparedStatement statement =
                    getPsUpdate(getConnection(), object, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete.
     *
     * @param id the id
     * @throws SQLException the sql exception
     */
    public void delete(final long id) throws SQLException {
        try {
            PreparedStatement statement = getPsDelete(getConnection(), id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Add.
     *
     * @param object the object
     * @throws SQLException the sql exception
     */
    public void add(final E object) throws SQLException {
        try {
            PreparedStatement statement = getPsAdd(getConnection(), object);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
