package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.concurrent.Immutable;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Immutable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile_task_view")
public class ProfileTaskView {

    @Id
    @Column(name = "key")
    private Long key;

    @Column(name = "profile_task_id")
    private Long profileTaskId;


    @Column(name = "field_id")
    private Long fieldId;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "domain")
    private String domain;

    @Column(name = "comment")
    private String comment;

}
