package org.example.DAO;

import org.example.DTO.Patient;

import java.sql.*;

/**
 * The type Patient dao.
 */
public class PatientDAO extends AbstractDAO<Patient> {
    /**
     * @value name identifier
     */
    private static final int ID_NAME = 1;
    /**
     * @value patronymic identifier
     */
    private static final int ID_PATRONYMIC = 2;
    /**
     * @value surname identifier
     */
    private static final int ID_SURNAME = 3;
    /**
     * @value phone number identifier
     */
    private static final int ID_PHONENUMBER = 4;
    /**
     * @value identifier
     */
    private static final int ID_ID = 5;

    @Override
    protected final Patient getObject(final ResultSet resultSet) throws SQLException {
        return new Patient(resultSet.getLong("ID"),
                resultSet.getString("NAME"),
                resultSet.getString("PATRONYMIC"),
                resultSet.getString("SURNAME"),
                resultSet.getString("PHONENUMBER"));
    }

    @Override
    protected final ResultSet getRsAll(final Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM PATIENT");
    }

    @Override
    protected final PreparedStatement getPsById(final Connection connection, final long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM PATIENT WHERE ID = ?");
            statement.setLong(ID_NAME, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    /**
     * Sets values.
     *
     * @param statement the statement
     * @param patient   the patient
     * @throws SQLException the sql exception
     */
    public void setValues(final PreparedStatement statement, final Patient patient) throws SQLException {
        statement.setString(ID_NAME, patient.getName());
        statement.setString(ID_PATRONYMIC, patient.getPatronymic());
        statement.setString(ID_SURNAME, patient.getSurname());
        statement.setString(ID_PHONENUMBER, patient.getPhoneNumber());
    }

    @Override
    protected final PreparedStatement getPsAdd(final Connection connection, final Patient patient) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO PATIENT (NAME, PATRONYMIC, SURNAME, PHONENUMBER) VALUES (?,?,?,?)");
            setValues(statement, patient);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    @Override
    protected final PreparedStatement getPsUpdate(
            final Connection connection, final Patient patient, final long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE PATIENT SET NAME = ?, PATRONYMIC = ?, SURNAME = ?, PHONENUMBER = ? WHERE ID = ?");
            setValues(statement, patient);
            statement.setLong(ID_ID, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    @Override
    protected final PreparedStatement getPsDelete(final Connection connection, final Long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM PATIENT WHERE ID = ?");
            statement.setLong(ID_NAME, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }
}
