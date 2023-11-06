package org.lib.minor1.repository;

import org.lib.minor1.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findByContact(String contact);
}
