package com.gss.minor1.Repository;

import com.gss.minor1.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
     Admin findByContact(String contact);
}
