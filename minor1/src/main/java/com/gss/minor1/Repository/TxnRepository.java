package com.gss.minor1.Repository;

import com.gss.minor1.models.Txn;
import com.gss.minor1.models.TxnStatus;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;

public interface TxnRepository extends JpaRepository<Txn,Integer> {
    public Txn findByStudent_ContactAndBook_BookNoAndStatusOrderByCreatedonDesc(String contact, String bookno, TxnStatus status);

    @Transactional
    @Modifying
    @Query(value = "update txn set createdon= \"2024-02-02 12:53:03.456000\" where id =4",nativeQuery = true)
    public  void updatecreateon();



}
