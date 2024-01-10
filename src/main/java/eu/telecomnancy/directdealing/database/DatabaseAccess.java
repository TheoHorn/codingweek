package eu.telecomnancy.directdealing.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

/**
 * DatabaseAccess class is the class responsible for the connection to the database.
 */
public class DatabaseAccess {
    /**
     * The connection to the database.
     */
    public static Connection connection;

    /**
     * The constructor of the class.
     * @param dbPath the path to the database
     * @throws SQLException if an error occurs
     */
    private DatabaseAccess(String dbPath) throws SQLException {
        connectToDatabase(dbPath);
    }

    /**
     * Create a new database.
     * @param file the file where the database will be created
     * @throws SQLException if an error occurs
     */
    public static void createDatabase(File file) throws SQLException {
        String databaseUrl = "jdbc:sqlite:" + file.getAbsolutePath();

        try (Connection conn = DriverManager.getConnection(databaseUrl)) {
            System.out.println("Connected to SQLite database.");

            String schemaFilePath = DatabaseAccess.class.getResource("/eu/telecomnancy/directdealing/database/schema_database.sql").getPath();
            executeSchemaFile(conn, schemaFilePath);

            conn.commit();
            conn.close();
            System.out.println("Database created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Execute the schema file to create the database.
     * @param conn the connection to the database
     * @param schemaFilePath the path to the schema file
     */
    private static void executeSchemaFile(Connection conn, String schemaFilePath) {
        StringBuilder schema = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(schemaFilePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                schema.append(line);
            }

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(schema.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Connect to the database.
     * @param dbPath the path to the database
     * @throws SQLException if an error occurs
     */
    public static void connectToDatabase(String dbPath) throws SQLException {
        // create a database connection
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        connection.setAutoCommit(true);
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
    }


    /**
     * Disconnect from the database.
     * @throws SQLException if an error occurs
     */
    public static void disconnectFromDatabase() throws SQLException {
        if (connection != null){
            connection.close();
        }
    }
}

