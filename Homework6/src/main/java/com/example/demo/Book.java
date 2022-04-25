package com.example.demo;

import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

@Entity
@Table(name = "book")
@NamedQueries({
        @NamedQuery(query = "select b from Book  b where b.name= :name", name = Book.Find_By_Name),
        @NamedQuery(query = "select b from Book  b where b.ibsn= :ibsn", name = Book.Find_By_Ibsn),
        @NamedQuery(query = "select b from Book  b where b.author= :author", name = Book.Find_By_Author)
})

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book {

    public static final String Find_By_Name="Book.Find_By_Name";
    public  static final String Find_By_Ibsn="Book.Find_By_Ibsn";
    public  static final String Find_By_Author="Book.Find_By_Author";


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "ibsn")
    private String ibsn;
    @Column(name = "author")
    private String author;

    Book(String name, String ibsn, String author){
        this.name=name;
        this.ibsn=ibsn;
        this.author=author;
    }

    public String getName(){
        return this.name;
    }
    public String getIbsn(){
        return this.ibsn;
    }
    public String getAuthor(){
        return this.author;
    }
    public String toString(){
        return this.name+"  "+this.ibsn+"  "+ this.author+"\n";
    }

}
