package org.example.DAO;

import java.io.File;

/**
 * The type Hsqldb dao.
 */
public final class HsqldbDAO {
    /**
     * @value url
     */
    private static String url;
    /**
     * @value constant user name "root"
     */
    private static final String USER = "root";
    /**
     * @value constant null password
     */
    private static final String PASSWORD = "";
    /**
     * @value constant name "database"
     */
    private static final String NAME = "database";
    /**
     * @value connection
     */
    private static HsqldbDAO hsqldb;

    /**
     * Instantiates a new HsqldbDAO.
     */
    private HsqldbDAO() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getURL() {
        return url;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return NAME;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public String getUser() {
        return USER;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return PASSWORD;
    }

    /**
     * Gets hsqldb.
     *
     * @return the hsqldb
     */
    public static synchronized HsqldbDAO getHsqldb() {
        if (hsqldb == null) {
            hsqldb = new HsqldbDAO();
            String path = new File("").getAbsolutePath()
                    .replaceAll("[\\\\/]", "/");
            url = "jdbc:hsqldb:file:" + path
                    + "/src/main/resources/db/database;hsqldb.lock_file=false";
        }
        return hsqldb;
    }
}
