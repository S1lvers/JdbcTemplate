package ru.sberdata.services.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.sberdata.exceptions.AppColumnNotFoundException;
import ru.sberdata.exceptions.AppSchemaNotFoundException;
import ru.sberdata.exceptions.AppTableNotFoundException;
import ru.sberdata.model.DbAppendRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DbAppenderService {

    private final DbSystemService dbSystemService;

    public DbAppenderService (DbSystemService dbSystemService){
        this.dbSystemService = dbSystemService;
    }

    public void updateTable(DbAppendRequest dbAppendRequest) throws SQLException {
        this.validateRequest(dbAppendRequest);
        log.debug("Пытаюсь обновить таблицу {}...", dbAppendRequest.getTableName());
        String schemaTable = dbAppendRequest.getSchemaName() + "." + dbAppendRequest.getTableName();
        JdbcTemplate jdbcTemplate = dbSystemService.getJdbcTemplate();
        //Если будет список параметров, пока обновляем один
        //dbAppendRequest.getData().forEach(x -> {
            this.updateTableOneByOne(jdbcTemplate, schemaTable, dbAppendRequest.getData());
        //});
        log.debug("Обновление таблицы успешно завершено");
    }

    private void updateTableOneByOne(JdbcTemplate jdbcTemplate, String schemaTable, HashMap<String, String> data) {
        //converting to treemap потому что hashmap не горонтирует одинаковую направленность keyset и treeset
        TreeMap<String, String> dataTree = new TreeMap<>(data);
        String columnsString = " (" + String.join(", ", dataTree.keySet()) + ") ";
        String questionString = " (" + dataTree.keySet().stream().map(x -> "?").collect(Collectors.joining(", ")) + ")";
        jdbcTemplate.update("INSERT INTO " + schemaTable + columnsString + "VALUES" + questionString, new ArrayList<>(dataTree.values()).toArray());
    }

    private void validateRequest(DbAppendRequest dbAppendRequest) throws SQLException {
        log.debug("Валидация существования схемы {}...", dbAppendRequest.getSchemaName());
        if (!dbSystemService.schemaExist(dbAppendRequest.getSchemaName())) {
            throw new AppSchemaNotFoundException("Не удалось найти схему с именем " + dbAppendRequest.getSchemaName());
        }
        log.debug("Валидация существования таблицы {}...", dbAppendRequest.getTableName());
        if (!dbSystemService.tableExist(dbAppendRequest.getSchemaName(), dbAppendRequest.getTableName())) {
            throw new AppTableNotFoundException(String.format("Не удалось найти таблицу %s в схеме %s", dbAppendRequest.getSchemaName(), dbAppendRequest.getTableName()));
        }
        log.debug("Валидация данных для обновления...");
        Set<String> tableColumns = dbSystemService.getTableColumnsCatalog(dbAppendRequest.getSchemaName(), dbAppendRequest.getTableName());
        tableColumns = tableColumns.stream().map(String::toUpperCase).collect(Collectors.toSet());
        Set<String> keySet = dbAppendRequest.getData().keySet().stream().map(String::toUpperCase).collect(Collectors.toSet());
        //dbAppendRequest.getData().forEach(x -> {
            if (!tableColumns.containsAll(keySet)) throw new AppColumnNotFoundException(String.format("Указанные столбцы отсутствуют в таблице %s", dbAppendRequest.getTableName()));
        //});
        log.debug("Валидации прошли успешно!");
    }



}
