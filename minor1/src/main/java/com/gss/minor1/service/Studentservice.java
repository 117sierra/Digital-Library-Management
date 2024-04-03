package com.gss.minor1.service;

import com.gss.minor1.Repository.StudentRepository;
//import com.gss.minor1.Request.CreateStudentRequest;
import com.gss.minor1.Repository.UserRepository;
import com.gss.minor1.Request.CreateStudentRequest;
import com.gss.minor1.models.Operationtype;
import com.gss.minor1.models.Student;
import com.gss.minor1.models.StudentFilterType;
import com.gss.minor1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Studentservice {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${student.authority}")
    String studentauth;

    public List<Student> findstudents(StudentFilterType studentFilterType, String value, Operationtype operationtype) {
        switch (operationtype) {
            case EQUALS:
                switch (studentFilterType) {
                    case EMAIL:
                        return  studentRepository.findByEmail(value);
                    case CONTACT:
                        return studentRepository.findByContact(value);

                }
        }
        return null;
    }

    public Student create (CreateStudentRequest createStudentRequest){
        List<Student> student= findstudents(StudentFilterType.CONTACT, createStudentRequest.getContact(),Operationtype.EQUALS);
        if(student==null || student.isEmpty()){
            Student student1= createStudentRequest.to();
            User user = User.builder().contact(student1.getContact()).password(passwordEncoder.encode(createStudentRequest.getPassword())).authorities(studentauth).build();
            user= userRepository.save(user);
            student1.setUser(user);
            return studentRepository.save(student1);
        }
        return student.get(0);

    }
}

