package com.gss.minor1.Repository;

import com.gss.minor1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByContact(String contact);
}
