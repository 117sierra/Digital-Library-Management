package com.gss.minor1.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Entity
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    private String name;

    @Column(length = 30)
    private String address;

    @Column(nullable = false,unique = true)
    private String contact;

    @Column(length = 30, unique = true)
    private String email;

    @CreationTimestamp
    private Date createdon;

    @UpdateTimestamp
    private Date updatetime;

    @OneToOne
    private User user;

}