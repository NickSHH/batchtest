package com.example.batchtest;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "BOOK")
@Data
public class Book {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "PAPERBACK")
    private Integer paperback;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "ISBN_13", length = 13)
    private String isbn13;

    @Column(name = "DESCRIPTION")
    private String description;
}
