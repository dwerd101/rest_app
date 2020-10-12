package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.concurrent.Immutable;
import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile_result_view")
@Immutable
public class ProfileResultView {

    @Id
    @Column(name = "id")
    private long profileResultId;

    @Column(name = "sources_name")
    private String sourceName;
    @Column(name = "owners_name")
    private String ownersName;
    @Column(name = "tables_name")
    private String tablesName;
    @Column(name = "field_name")
    private String fieldName;
    @Column(name = "domain")
    private String nameDomain;
    @Column(name = "comment")
    private String comment;
    @Column(name = "profile_task_id")
    private Long profileTaskId;

}
