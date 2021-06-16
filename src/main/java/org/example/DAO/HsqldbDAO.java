package org.example.DAO;

import java.io.File;

public final class HsqldbDAO {

    private static String url;

    private static final String USER = "root";

    private static final String PASSWORD = "";

    private static final String NAME = "database";

    private static HsqldbDAO hsqldb;

    private HsqldbDAO() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getURL() {
        return url;
    }

    public String getName() {
        return NAME;
    }

    public String getUser() {
        return USER;
    }

    public String getPassword() {
        return PASSWORD;
    }

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
