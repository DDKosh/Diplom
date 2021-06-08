package org.example.DAO;

import org.example.DTO.Doctor;
import org.example.DTO.Patient;
import org.example.DTO.Recipe;
import org.example.DTO.RecipePriority;

import java.sql.*;


/**
 * The type Recipe dao.
 */
public class RecipeDAO extends AbstractDAO<Recipe> {
    /**
     * @value descriprion identifier
     */
    private static final int ID_DESCRIPTION = 1;
    /**
     * @value patient identifier
     */
    private static final int ID_PATIENT = 2;
    /**
     * @value doctor identifier
     */
    private static final int ID_DOCTOR = 3;
    /**
     * @value creation date identifier
     */
    private static final int ID_CREATIONDATE = 4;
    /**
     * @value validity identifier
     */
    private static final int ID_VALIDITY = 5;
    /**
     * @value priority identifier
     */
    private static final int ID_PRIORITY = 6;
    /**
     * @value identifier
     */
    private static final int ID_ID = 7;

    @Override
    protected final Recipe getObject(final ResultSet resultSet) throws SQLException {
        Patient patient =
                new PatientDAO().getById(resultSet.getLong("ID_PATIENT"));
        Doctor doctor =
                new DoctorDAO().getById(resultSet.getLong("ID_DOCTOR"));
        RecipePriority priority =
                new RecipePriorityDAO().getById(resultSet.getLong("ID_PRIORITY"));
        return new Recipe(resultSet.getLong("ID"),
                resultSet.getString("DESCRIPTION"),
                patient, doctor,
                resultSet.getDate("CREATIONDATE"),
                resultSet.getInt("VALIDITY"),
                priority);
    }

    @Override
    protected final ResultSet getRsAll(final Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM RECIPE");
    }

    @Override
    protected final PreparedStatement getPsById(final Connection connection, final long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM RECIPE WHERE ID = ?");
            statement.setLong(ID_DESCRIPTION, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    /**
     * Sets values.
     *
     * @param statement the statement
     * @param recipe    the recipe
     * @throws SQLException the sql exception
     */
    public void setValues(final PreparedStatement statement, final Recipe recipe) throws SQLException {
        statement.setString(ID_DESCRIPTION, recipe.getDescription());
        statement.setLong(ID_PATIENT, recipe.getPatient().getId());
        statement.setLong(ID_DOCTOR, recipe.getDoctor().getId());
        statement.setDate(ID_CREATIONDATE, recipe.getCreationDate());
        statement.setInt(ID_VALIDITY, recipe.getValidity());
        statement.setLong(ID_PRIORITY, recipe.getPriority().getId());
    }

    @Override
    protected final PreparedStatement getPsAdd(final Connection connection, final Recipe recipe) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO RECIPE (DESCRIPTION, ID_PATIENT, ID_DOCTOR, "
                    + "CREATIONDATE, VALIDITY, ID_PRIORITY) VALUES (?,?,?,?,?,?)");
            setValues(statement, recipe);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    @Override
    protected final PreparedStatement getPsUpdate(
            final Connection connection, final Recipe recipe, final long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE RECIPE SET DESCRIPTION= ?, ID_PATIENT = ?, ID_DOCTOR = ?, CREATIONDATE = ?, VALIDITY = ?, "
                            + "ID_PRIORITY = ?  WHERE ID = ?");
            setValues(statement, recipe);
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
            statement = connection.prepareStatement("DELETE FROM RECIPE WHERE ID = ?");
            statement.setLong(ID_DESCRIPTION, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}
