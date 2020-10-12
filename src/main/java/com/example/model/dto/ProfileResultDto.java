package com.example.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResultDto {

    private Long profileResultId;
    private String sourceName;
    private String ownersName;
    private String tablesName;
    private String fieldName;
    private String nameDomain;
    private String comment;
    private Long profileTaskId;

}
