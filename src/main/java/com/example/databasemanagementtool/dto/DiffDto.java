package com.example.databasemanagementtool.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DiffDto {

    private String url;

    private String userName;

    private String password;

    private String referenceUrl;

    private String referenceUserName;

    private String referencePassword;

}
