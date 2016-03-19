/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaronstechcenter.experfymodule9;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.ClusteringColumn;

/**
 *
 * @author aploetz
 */

@Table(keyspace = "experfy_class", name = "books_by_author")
public class Book {
    @PartitionKey
    private String author;
    @ClusteringColumn(0)
    private String title;
    @ClusteringColumn(1)
    private long edition;
    private String isbn;
    private String publisher;
    private long year;

    public Book() {
        
    }
    
    public Book (String _author, String _title, long _edition, String _isbn,
            String _publisher, long _year) {
        author = _author;
        title =_title;
        edition = _edition;
        isbn = _isbn;
        publisher = _publisher;
        year = _year;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getEdition() {
        return edition;
    }

    public void setEdition(long edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }
}
