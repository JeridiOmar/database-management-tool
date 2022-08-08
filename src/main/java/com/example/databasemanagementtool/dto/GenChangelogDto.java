package com.example.databasemanagementtool.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class GenChangelogDto {

    private String url;

    private String userName;

    private String password;
}
