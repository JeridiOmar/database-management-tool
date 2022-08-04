package com.example.databasemanagementtool.controller;

import com.example.databasemanagementtool.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    ManagementService managementService;

}
