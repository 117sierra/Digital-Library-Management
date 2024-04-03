package com.gss.minor1.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class CreateTxnRequest {
     @NotBlank(message = "Student contact should not be blank")
     private String student_contact;
     @NotBlank(message = "Book no should not be blank")
     private String bookno;
     @NotNull(message ="Amount should be positive" )
     private Integer paidamount;
}
