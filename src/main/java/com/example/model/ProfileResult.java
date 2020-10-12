package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile_result")
public class ProfileResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
  //  @JsonProperty("id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "field_id", referencedColumnName = "id")

  //  @JsonProperty("field_id")
    @ToString.Exclude    private Field fieldId;

    @Column(name = "date_field")
 //   @JsonProperty("date_field")
    private LocalDate dateField;

    @Column(name = "domain")
    private String domain;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "profile_task_id", referencedColumnName = "id")
    @ToString.Exclude
    private ProfileTask profileTaskId;

    public ProfileResult(Field field,  LocalDate dateField, String domain, String comment, ProfileTask profileTaskId) {
        this.fieldId=field;
        this.dateField=dateField;
        this.comment=comment;
        this.domain=domain;
        this.profileTaskId=profileTaskId;
    }

}
