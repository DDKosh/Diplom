package org.example.DAO;

import org.example.DTO.*;

import java.sql.*;

public class AppointmentDAO extends AbstractDAO{

    private static final int ID_NAME = 1;

    @Override
    protected Object getObject(ResultSet resultSet) throws SQLException {
        Patient patient =
                new PatientDAO().getById(resultSet.getLong("ID_PATIENT"));
        Recipe recipe =
                new RecipeDAO().getById(resultSet.getLong("ID_RECIPE"));
        Doctor doctor = new DoctorDAO().getById(resultSet.getLong("ID_DOCTOR"));
        return new Appointment(resultSet.getLong("ID"),
                patient,
                doctor,
                resultSet.getDate("VISIT_DATE"),
                resultSet.getString("APPEAL"),
                recipe,
                resultSet.getString("DIAGNOSIS"));
    }

    @Override
    protected ResultSet getRsAll(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM APPOINTMENT");
    }

    protected ResultSet getRsAll(Statement statement, long id) throws SQLException {
        return statement.executeQuery("SELECT * FROM APPOINTMENT WHERE ID_PATIENT=" + id);
    }

    @Override
    protected PreparedStatement getPsById(Connection connection, long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM PATIENT WHERE ID = ?");
            statement.setLong(ID_NAME, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    @Override
    protected PreparedStatement getPsUpdate(Connection connection, Object object, long id) throws SQLException {
        return null;
    }

    protected final PreparedStatement getPsAdd(final Connection connection, final Appointment appointment) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO APPOINTMENT (ID_PATIENT, VISIT_DATE, APPEAL, ID_RECIPE, DIAGNOSIS, ID_DOCTOR) VALUES (?,?,?,?,?,?)");
            setValues(statement, appointment);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    public void setValues(final PreparedStatement statement, final Appointment appointment) throws SQLException {
        statement.setLong(1, appointment.getPatient().getId());
        statement.setDate(2, Date.valueOf(appointment.getVisitDate()));
        statement.setString(3, appointment.getAppeal());
        statement.setLong(4, appointment.getRecipe().getId());
        statement.setString(5, appointment.getDiagnosis());
        statement.setLong(6, appointment.getDoctor().getId());
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
