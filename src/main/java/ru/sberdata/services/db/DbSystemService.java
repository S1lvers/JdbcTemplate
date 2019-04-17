package ru.sberdata.services.db;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.sberdata.exceptions.AppSchemaNotFoundException;
import ru.sberdata.exceptions.AppTableNotFoundException;
import ru.sberdata.utils.DbHelperUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class DbSystemService {

    @Getter
    private final JdbcTemplate jdbcTemplate;

    public DbSystemService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Set<String> getSchemasCatalog() throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            return DbHelperUtils.extractSchemasFromMetaData(conn.getMetaData());
        }
    }

    public Set<String> getTablesCatalog(String schemaName) throws SQLException {
        if (!this.schemaExist(schemaName)) {
            return new HashSet<>();
        }
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            return DbHelperUtils.extractTablesFromMetadata(conn.getMetaData(), schemaName);
        }
    }

    public Set<String> getTableColumnsCatalog(String schemaName, String tableName) throws SQLException {
        if (!this.tableExist(schemaName, tableName)) {
            return new HashSet<>();
        }
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            return DbHelperUtils.extractTableColumnsFromMetadata(conn.getMetaData(), schemaName, tableName);
        }
    }

    public void doSmthing() throws SQLException {
        Set<String> a = this.getSchemasCatalog();
        System.out.println(a);
    }

    public Boolean schemaExist(String schemaName) throws SQLException {
        return this.getSchemasCatalog().contains(schemaName);
    }

    public Boolean tableExist(String schemaName, String tableName) throws SQLException {
        return this.getTablesCatalog(schemaName).contains(tableName);
    }
}
