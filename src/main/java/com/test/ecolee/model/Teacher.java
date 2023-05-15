package com.test.ecolee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "ec_teacher")
public class Teacher extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEACHER_SEQ")
    @SequenceGenerator(sequenceName = "teacher_seq", name = "TEACHER_SEQ")
    private Long id;
    private String firstName;
    private String lastName;
}
