package ru.sberdata.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DbAppendResponse {
    private String error;
    private Boolean status;
}
