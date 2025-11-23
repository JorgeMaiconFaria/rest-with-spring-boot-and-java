package com.github.jorgemaicon.mapper.custom;

import com.github.jorgemaicon.data.dto.BookDTO;
import com.github.jorgemaicon.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public BookDTO convertEntityToDTO(Book book) {
        BookDTO dto = new BookDTO();

        dto.setID(book.getID());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        dto.setLaunch_date(book.getLaunch_date());

        return dto;
    }

    public Book convertDTOToEntity(BookDTO dto) {
        Book entity = new Book();

        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setPrice(dto.getPrice());
        entity.setLaunch_date(dto.getLaunch_date());

        return entity;
    }
}
