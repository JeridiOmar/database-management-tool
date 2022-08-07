package com.example.databasemanagementtool.service;

import com.example.databasemanagementtool.dto.DiffDto;
import com.example.databasemanagementtool.util.DatabaseUtil;
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
import liquibase.serializer.core.yaml.YamlChangeLogSerializer;
import org.springframework.stereotype.Service;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;

@Service
public class ManagementService {

    public void diffLog(DiffDto diffDto) throws LiquibaseException {
//        System.out.println(diffDto.getUrl());referenceConnection
//        System.out.println(diffDto.getReferencePassword());
        Connection targetConnection = DatabaseUtil.generateConnection(diffDto.getUrl(), diffDto.getUserName(), diffDto.getPassword());
        Connection referenceConnection = DatabaseUtil.generateConnection(diffDto.getReferenceUrl(), diffDto.getReferenceUserName(), diffDto.getReferencePassword());

        Liquibase liquibase = null;

        try {

            Database referenceDatabase = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(referenceConnection));
            Database targetDatabase = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(targetConnection));

            liquibase = new Liquibase("test.yaml", new FileSystemResourceAccessor(), referenceDatabase);
            DiffResult diffResult = liquibase.diff(referenceDatabase, targetDatabase, new CompareControl());
            new DiffToChangeLog(diffResult, new DiffOutputControl()).print(System.out, new YamlChangeLogSerializer());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (liquibase != null) {
                liquibase.forceReleaseLocks();
            }
        }
    }
}
