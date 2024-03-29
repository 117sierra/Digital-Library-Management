package org.lib.minor1.repository;

import org.lib.minor1.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByEmail(String email);

    List<Student> findByContact(String contact);

}
