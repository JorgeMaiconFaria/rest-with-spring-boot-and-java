package com.github.jorgemaicon.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "launch_date", nullable = false)
    private LocalDateTime launch_date;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(LocalDateTime launch_date) {
        this.launch_date = launch_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return getID() == book.getID() && Double.compare(getPrice(), book.getPrice()) == 0 && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getLaunch_date(), book.getLaunch_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getTitle(), getAuthor(), getPrice(), getLaunch_date());
    }
}
