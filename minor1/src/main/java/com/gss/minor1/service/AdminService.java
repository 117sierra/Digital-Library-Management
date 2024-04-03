package com.gss.minor1.service;

import com.gss.minor1.Repository.AdminRepository;
import com.gss.minor1.Repository.UserRepository;
import com.gss.minor1.Request.CreateAdminRequest;
import com.gss.minor1.models.Admin;
import com.gss.minor1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Value("${admin.authority}")
    String adminauth;

    public Admin create(CreateAdminRequest adminRequest) {
        Admin admin = adminRepository.findByContact(adminRequest.getContact());
        if(admin!=null){
            return admin;
        }
        admin=adminRequest.to();

        User user = User.builder().contact(admin.getContact()).password(passwordEncoder.encode(adminRequest.getPassword())).authorities(adminauth).build();
       user= userRepository.save(user);
       admin.setUser(user);
       return adminRepository.save(admin);
    }
}
