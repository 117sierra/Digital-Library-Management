package com.gss.minor1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    private String name;
    private int cost;
    private String bookNo;
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("bookList")           // to break the cycle of dependency
    private Author author;
    @Enumerated(value = EnumType.STRING)
    private BookType bookType;
    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties("bookList")  // same to break the cycle of dependency
    private Student student;

}
