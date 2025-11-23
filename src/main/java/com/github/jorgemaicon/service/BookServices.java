package com.github.jorgemaicon.service;

import com.github.jorgemaicon.controller.BookController;
import com.github.jorgemaicon.data.dto.BookDTO;
import com.github.jorgemaicon.exception.RequiredObjectIsNullException;
import com.github.jorgemaicon.exception.ResoucerNotFoundException;
import com.github.jorgemaicon.mapper.custom.BookMapper;
import com.github.jorgemaicon.model.Book;
import com.github.jorgemaicon.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.github.jorgemaicon.mapper.ObjectMapper.parseListObjects;
import static com.github.jorgemaicon.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class BookServices {

    @Autowired
    BookRepository repository;

    @Autowired
    BookMapper mapper;

    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    public List<BookDTO> findAll() {
        logger.info("Finding all Books!");

        var books = parseListObjects(repository.findAll(), BookDTO.class);

        books.forEach(this::addLinksHateoas);

        return books;
    }

    public BookDTO findById(long id) {
        logger.info("Finding a Book!");

        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResoucerNotFoundException("No records found for this ID."));

        var dto = parseObject(entity, BookDTO.class);
        addLinksHateoas(dto);

        return dto;
    }

    public BookDTO create(BookDTO book) {
        if(book == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating a Book!");

        Book entity = parseObject(book, Book.class);
        var dto = parseObject(repository.save(entity), BookDTO.class);
        addLinksHateoas(dto);

        return dto;
    }

    public BookDTO update(BookDTO book) {
        if(book == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating a Book!");
        Book entity = repository.findById(book.getID())
                .orElseThrow(() -> new ResoucerNotFoundException("No records found for this ID."));

        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setLaunch_date(book.getLaunch_date());

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addLinksHateoas(dto);

        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting a Book!");
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResoucerNotFoundException("No records found for this ID."));

        repository.delete(entity);
    }

    private void addLinksHateoas(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getID())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).listAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getID())).withRel("delete").withType("DELETE"));
    }
}
