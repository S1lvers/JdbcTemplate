package ru.sberdata.model;

import lombok.Data;

import java.util.HashMap;

@Data
public class DbAppendRequest {
    private String schemaName;
    private String tableName;
    private HashMap<String, String> data;
}
