package org.lib.minor1.service;

import org.lib.minor1.models.OperationType;
import org.lib.minor1.models.Student;
import org.lib.minor1.models.StudentFilterType;
import org.lib.minor1.models.User;
import org.lib.minor1.repository.StudentRepository;
import org.lib.minor1.repository.UserRepository;
import org.lib.minor1.request.CreateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Value("${student.authority}")
    private String studentAuthority;

    public List<Student> findStudent(StudentFilterType studentFilterType, String value, OperationType operationType) {

        switch (operationType) {
            case EQUALS:
                switch (studentFilterType) {
                    case EMAIL:
                        return studentRepository.findByEmail(value);
                    case CONTACT:
                        return studentRepository.findByContact(value);
                }
            default:
                return new ArrayList<>();
        }
    }

    public Student create(CreateStudentRequest createStudentRequest) {
        // check if the student already exist or not
        List<Student> students = findStudent(StudentFilterType.CONTACT, createStudentRequest.getContact() , OperationType.EQUALS );
        if(students == null || students.isEmpty()){
            Student student = createStudentRequest.to();
            User user = (User.builder().
                    contact(student.getContact())).
                    password(passwordEncoder.encode(createStudentRequest.getPassword())).
                    authorities(studentAuthority).
                    build();
            user = userRepository.save(user);
            student.setUser(user);
           return studentRepository.save(student);
        }
        return students.get(0);
    }
}
