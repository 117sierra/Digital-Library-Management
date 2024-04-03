package com.gss.minor1.models;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Entity
public class Txn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String txnid;
    @JoinColumn
    @ManyToOne
    private Student student;
    @JoinColumn
    @ManyToOne
    private Book book;
    private int paidcost;

    @Enumerated(value = EnumType.STRING)
    private TxnStatus status;
    @CreationTimestamp
    private Date createdon;

    @UpdateTimestamp
    private Date updatetime;
}
