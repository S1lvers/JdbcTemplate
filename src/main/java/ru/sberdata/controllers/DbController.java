package ru.sberdata.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberdata.exceptions.AppRuntimeException;
import ru.sberdata.model.DbAppendRequest;
import ru.sberdata.model.DbAppendResponse;
import ru.sberdata.services.db.DbAppenderService;
import ru.sberdata.services.db.DbSystemService;

import java.sql.SQLException;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/db")
public class DbController {

    @Autowired
    private DbSystemService dbSystemService;

    @Autowired
    private DbAppenderService dbAppenderService;


    @GetMapping("/schemas")
    public Set<String> getSchemas() throws SQLException {
        return dbSystemService.getSchemasCatalog();
    }

    @GetMapping("/tables/{schemaName}")
    public Set<String> getTables(@PathVariable String schemaName) throws SQLException {
        return dbSystemService.getTablesCatalog(schemaName);
    }

    @PostMapping("/append")
    public DbAppendResponse append(@RequestBody DbAppendRequest dbAppendRequest) {
        try {
            dbAppenderService.updateTable(dbAppendRequest);
        } catch (SQLException e) {
            log.error("Ошибка SQL :{}" + e.getMessage());
            return new DbAppendResponse().setStatus(false).setError("Возникла непредвиденная ошибка SQL");
        } catch (AppRuntimeException e) {
            log.error(e.getMessage());
            return new DbAppendResponse().setStatus(false).setError(e.getMessage());
        }
        return new DbAppendResponse().setStatus(true);
    }
}
