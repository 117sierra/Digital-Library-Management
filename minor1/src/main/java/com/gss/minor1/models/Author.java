package com.gss.minor1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Entity
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(length = 30, unique = true,nullable = false)
    private String email;
    @CreationTimestamp
    private Date createdon;
    @UpdateTimestamp
    private Date updatetime;
    @OneToMany(mappedBy = "author")
//    @JsonIgnoreProperties("author")
    private List<Book> bookList;

}
