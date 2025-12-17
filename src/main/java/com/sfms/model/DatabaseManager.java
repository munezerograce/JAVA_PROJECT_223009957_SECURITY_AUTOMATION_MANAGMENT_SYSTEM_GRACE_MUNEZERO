package com.sfms.model;

import com.sfms.exception.DatabaseConnectionException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton class for managing database connections.
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() throws DatabaseConnectionException {
        try {
            Properties props = new Properties();
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
                if (input == null) {
                    throw new DatabaseConnectionException("Unable to find db.properties file");
                }
                props.load(input);
            }

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            this.connection = DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException e) {
            throw new DatabaseConnectionException("Failed to establish database connection", e);
        }
    }

    public static synchronized DatabaseManager getInstance() throws DatabaseConnectionException {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() throws DatabaseConnectionException {
        try {
            if (connection == null || connection.isClosed()) {
                // Reconnect if connection is closed
                Properties props = new Properties();
                try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
                    if (input == null) {
                        throw new DatabaseConnectionException("Unable to find db.properties file");
                    }
                    props.load(input);
                }

                String url = props.getProperty("db.url");
                String username = props.getProperty("db.username");
                String password = props.getProperty("db.password");

                this.connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException | IOException e) {
            throw new DatabaseConnectionException("Failed to establish database connection", e);
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
