package com.github.jorgemaicon.data.dto;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {

    private long ID;
    private String title;
    private String author;
    private double price;
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
        if (!(o instanceof BookDTO bookDTO)) return false;
        if (!super.equals(o)) return false;
        return getID() == bookDTO.getID() && Double.compare(getPrice(), bookDTO.getPrice()) == 0 && Objects.equals(getTitle(), bookDTO.getTitle()) && Objects.equals(getAuthor(), bookDTO.getAuthor()) && Objects.equals(getLaunch_date(), bookDTO.getLaunch_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getID(), getTitle(), getAuthor(), getPrice(), getLaunch_date());
    }
}
