package com.test.ecolee.repository;

import com.test.ecolee.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "select * from ec_student as s left join ec_class as c  on c.id = s.id_classe where c.name  = :name", nativeQuery = true)
    Page<Student> findAllByClasse(Pageable pageable, @Param("name") String name);

    @Query(value = "select * from ec_student as s left join ec_class as c  on c.id = s.id_classe " +
            "left join ec_teacher as t on  c.id= s.id_classe where t.first_name = :firstName and t.last_name = :lastName ", nativeQuery = true)
    Page<Student> findAllByFirstNameAndLastNameTeacher(Pageable pageable, @Param("firstName") String firstName, @Param("lastName") String lastName);

    Optional<Student> findByFirstNameAndLastName(String firstName,String lastName);
}
