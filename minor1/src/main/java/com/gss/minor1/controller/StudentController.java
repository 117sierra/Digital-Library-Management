package com.gss.minor1.controller;
//import com.gss.minor1.Request.CreateStudentRequest;
import com.gss.minor1.Request.CreateStudentRequest;
import com.gss.minor1.models.*;
//import com.gss.minor1.service.Studentservice;
import com.gss.minor1.service.Studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private Studentservice studentservice;


    @PostMapping("/create")
    public Student create(@RequestBody CreateStudentRequest createBookRequest){

       return studentservice.create(createBookRequest);
    }
    @GetMapping("/find")
    public List<Student> findbooks(@RequestParam("filter")StudentFilterType studentFilterType, @RequestParam("value") String value, @RequestParam("operation") Operationtype operationtype){
        return studentservice.findstudents(studentFilterType,value,operationtype);
    }

}
