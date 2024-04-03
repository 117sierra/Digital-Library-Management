package com.gss.minor1.Request;

import com.gss.minor1.models.Student;
import com.gss.minor1.models.StudentType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class CreateStudentRequest {
    private String name;
    private String address;
    private String contact;
    private String email;
    private String password;
    public Student to(){
        return Student.builder().name(this.name).address(this.address).contact(this.contact).email(this.email).studentType(StudentType.ACTIVE).build();
    }
}
