package ru.sberdata.utils;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DbHelperUtils {

    public static Set<String> extractSchemasFromMetaData(DatabaseMetaData meta) throws SQLException {
        Set<String> resultSchemasSet = new HashSet<>();
        ResultSet schemas = meta.getSchemas();
        while (schemas.next()) {
            resultSchemasSet.add(schemas.getString(1));
        }
        return resultSchemasSet;
    }

    public static Set<String> extractTablesFromMetadata(DatabaseMetaData meta, String schemaName) throws SQLException {
        Set<String> resultTablesSet = new HashSet<>();
        ResultSet rs = meta.getTables(null, schemaName, "%", new String[]{"TABLE"});
        while (rs.next()) {
            resultTablesSet.add(rs.getString(3));
        }
        return resultTablesSet;
    }

    public static Set<String> extractTableColumnsFromMetadata(DatabaseMetaData meta, String schemaName, String tableName) throws SQLException {
        Set<String> resultTableColumnsSet = new HashSet<>();
        ResultSet columns = meta.getColumns(null, schemaName, tableName, null);
        while(columns.next()) {
            resultTableColumnsSet.add(columns.getString("COLUMN_NAME"));
        }
        return resultTableColumnsSet;
    }

}
