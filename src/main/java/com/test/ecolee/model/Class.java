package com.test.ecolee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Data
@Table(name = "ec_class")
public class Class extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASS_SEQ")
    @SequenceGenerator(sequenceName = "class_seq", name = "CLASS_SEQ")
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "classe", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Student> students;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_teacher", referencedColumnName = "id")
    private Teacher teacher;

}
