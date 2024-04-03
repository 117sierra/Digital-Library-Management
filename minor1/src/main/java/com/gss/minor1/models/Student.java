package com.gss.minor1.models;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Entity
public class Student implements Serializable {
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
    @OneToMany(mappedBy = "student")
    private List<Book> bookList;
    @Enumerated
    private StudentType studentType;


    @OneToOne
    @JoinColumn
    private User user;




}

   



