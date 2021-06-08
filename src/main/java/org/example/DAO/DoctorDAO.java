package org.example.DAO;

import org.example.DTO.Doctor;
import java.sql.*;


/**
 * The type Doctor dao.
 */
public class DoctorDAO extends AbstractDAO<Doctor> {
    private static final int ID_PIC = 1;

    /**
     * @value name identifier
     */
    private static final int ID_NAME = 2;
    /**
     * @value patronymic identifier
     */
    private static final int ID_PATRONYMIC = 3;
    /**
     * @value surname identifier
     */
    private static final int ID_SURNAME = 4;
    /**
     * @value specialization identifier
     */
    private static final int ID_SPECIALIZATION = 5;
    /**
     * @value identifier
     */
    private static final int ID_ID = 7;

    private static final int ID_COURSES = 6;

    @Override
    protected final Doctor getObject(final ResultSet resultSet) throws SQLException {
        return new Doctor(resultSet.getLong("ID"),
                resultSet.getString("NAME"),
                resultSet.getString("PATRONYMIC"),
                resultSet.getString("SURNAME"),
                resultSet.getString("SPECIALIZATION"),
                resultSet.getString("COURSES"));
    }

    @Override
    protected final ResultSet getRsAll(
            final Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM DOCTOR");
    }

    @Override
    protected final PreparedStatement getPsById(
            final Connection connection, final long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM DOCTOR WHERE ID = ?");
            statement.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    /**
     * Sets values.
     *
     * @param statement the statement
     * @param doctor    the doctor
     * @throws SQLException the sql exception
     */
    public void setValues(
            final PreparedStatement statement, final Doctor doctor) throws SQLException {
        statement.setString(ID_NAME, doctor.getName());
        statement.setString(ID_PATRONYMIC, doctor.getPatronymic());
        statement.setString(ID_SURNAME, doctor.getSurname());
        statement.setString(ID_SPECIALIZATION, doctor.getSpecialization());
        statement.setString(ID_COURSES, doctor.getCourses());
    }

    @Override
    protected final PreparedStatement getPsAdd(
            final Connection connection, final Doctor doctor) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO DOCTOR (PIC, NAME, PATRONYMIC, SURNAME, SPECIALIZATION, COURSES) VALUES (?,?,?,?,?,?)");
            setValues(statement, doctor);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    @Override
    protected final PreparedStatement getPsUpdate(
            final Connection connection, final Doctor doctor, final long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE DOCTOR SET PIC = ?, NAME = ?, PATRONYMIC = ?, SURNAME = ?, SPECIALIZATION = ?, COURSES = ? WHERE ID = ?");
            setValues(statement, doctor);
            statement.setLong(ID_ID, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    @Override
    protected final PreparedStatement getPsDelete(
            final Connection connection, final Long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM DOCTOR WHERE ID = ?");
            statement.setLong(ID_NAME, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }
}
