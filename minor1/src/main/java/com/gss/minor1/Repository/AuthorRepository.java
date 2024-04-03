package com.gss.minor1.Repository;

import com.gss.minor1.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Query(value = "select * from author where email=:email",nativeQuery = true)
    Author getauthoremail(String email);  //Native query handled by MYSQL working is done on table
    @Query("select  a from  Author a where  a.email=:email")
    Author getauthoremailnonative(String email);  // JPQL handled on java classes and by Hibernate
//     JPQL is better everything is handled by hibernate so error warning can be shown through JPQL technique
//    No query Method
    Author findByEmail(String email);
}
