package com.example.LibraryManagement.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;

    public String bookName;

    public Integer bookCost;

    public String bookDiscription;

    //Perametrized Constructor
    public Library(Integer bookId, String bookName, Integer bookCost, String bookDiscription) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookCost = bookCost;
        this.bookDiscription = bookDiscription;
    }

    public Library(){

    }

}

