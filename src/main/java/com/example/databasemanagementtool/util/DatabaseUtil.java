package com.example.databasemanagementtool.util;

import liquibase.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseUtil {
    private static final String CHANGELOG_FILE_NAME_TEMPLATE = "backup_%s.%s%s";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    public static Connection generateConnection(String url, String username, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
    public static String makeChangeLogFileName(Database database) {
        String fileId = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        String extension = "yaml";
        String databaseType =String.format("%s.", database.getShortName());
        return String.format(CHANGELOG_FILE_NAME_TEMPLATE, fileId, databaseType, extension);
    }
}
