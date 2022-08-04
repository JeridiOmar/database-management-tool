package com.example.databasemanagementtool.service;

import com.example.databasemanagementtool.dto.DiffDto;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.diff.DiffResult;
import liquibase.diff.compare.CompareControl;
import liquibase.diff.output.DiffOutputControl;
import liquibase.diff.output.changelog.DiffToChangeLog;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;
import org.springframework.stereotype.Service;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;

@Service
public class ManagementService {

    public void diff(DiffDto diffDto) throws LiquibaseException {

        Connection referenceConnection;
        Connection targetConnection;

        Liquibase liquibase = null;

        try {

            Database referenceDatabase = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(referenceConnection));
            Database targetDatabase = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(targetConnection));

            liquibase = new Liquibase("", new FileSystemResourceAccessor(), referenceDatabase);
            DiffResult diffResult = liquibase.diff(referenceDatabase, targetDatabase, new CompareControl());
            new DiffToChangeLog(diffResult, new DiffOutputControl()).print(System.out);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (liquibase != null) {
                liquibase.forceReleaseLocks();
            }
        }
    }
}
