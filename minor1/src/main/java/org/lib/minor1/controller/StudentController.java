package org.lib.minor1.controller;

import org.lib.minor1.models.*;
import org.lib.minor1.request.CreateStudentRequest;
import org.lib.minor1.service.StudentService;
import org.lib.minor1.models.StudentFilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public Student create(@RequestBody CreateStudentRequest createStudentRequest){
       return studentService.create(createStudentRequest);
    }

    @GetMapping("/find")
    public List<Student> findStudent(@RequestParam("filter") StudentFilterType studentFilterType,
                                     @RequestParam("value") String value,
                                     @RequestParam("operation") OperationType operationType){
        return studentService.findStudent(studentFilterType, value, operationType);
    }

}
