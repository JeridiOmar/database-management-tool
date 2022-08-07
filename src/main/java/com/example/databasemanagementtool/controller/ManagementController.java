package com.example.databasemanagementtool.controller;

import com.example.databasemanagementtool.dto.DiffDto;
import com.example.databasemanagementtool.service.ManagementService;
import liquibase.exception.LiquibaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    ManagementService managementService;

    @PostMapping("/diff-log")
    public void diff(@RequestBody DiffDto diffDto) throws LiquibaseException {
        this.managementService.diffLog(diffDto);
    }

}
