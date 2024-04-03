package com.gss.minor1.Request;

import com.gss.minor1.models.Author;
import com.gss.minor1.models.Book;
import com.gss.minor1.models.BookType;
import com.gss.minor1.models.Student;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class CreateBookRequest {
    private String bookname;
    private String authoremail;
    private String authorname;
    private int cost;
    private String bookno;
    private BookType bookType;

    public Book to() {
        Author authordata= Author.builder().name(this.authorname).email(this.authoremail).build();
        return Book.builder().bookNo(this.bookno).name(this.bookname).bookType(this.bookType).cost(this.cost).build();
    }
}
