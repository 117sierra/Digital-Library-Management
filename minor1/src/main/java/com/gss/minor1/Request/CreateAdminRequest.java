package com.gss.minor1.Request;


import com.gss.minor1.models.Admin;
import lombok.*;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class CreateAdminRequest {
    private String name;
    private String address;
    @NotBlank
    private String contact;
    private String email;
    private String password;

    public Admin to() {
        return Admin.builder().name(this.name).address(this.address).contact(this.contact).email(this.email).build();
    }
}
