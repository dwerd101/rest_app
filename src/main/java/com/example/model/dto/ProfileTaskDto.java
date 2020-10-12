package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileTaskDto {

    private Long key;
    private Long profileTaskId;
    private Long fieldId;
    private String ownerName;
    private String tableName;
    private String fieldName;
    private String domain;
    private String comment;
}
