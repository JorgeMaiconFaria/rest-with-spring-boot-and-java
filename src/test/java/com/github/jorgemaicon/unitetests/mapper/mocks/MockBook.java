package com.github.jorgemaicon.unitetests.mapper.mocks;

import com.github.jorgemaicon.data.dto.BookDTO;
import com.github.jorgemaicon.model.Book;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockBook {

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookDTO> mockDTOList() {
        List<BookDTO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockDTO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setTitle("Title Test " + number);
        book.setAuthor("Author Test " + number);
        book.setPrice(((number % 2) == 0) ? 20.1 : 30.3);
        book.setLaunch_date(LocalDateTime.of(2023, 11, 23, 10, 0, 0, 0));
        book.setID(number.longValue());
        return book;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO book = new BookDTO();
        book.setTitle("Title Test " + number);
        book.setAuthor("Author Test " + number);
        book.setPrice(((number % 2) == 0) ? 20.1 : 30.3);
        book.setLaunch_date(LocalDateTime.of(2023, 11, 23, 10, 0, 0, 0));
        book.setID(number.longValue());
        return book;
    }
}