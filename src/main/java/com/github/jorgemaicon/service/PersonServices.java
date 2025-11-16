package com.github.jorgemaicon.service;

import com.github.jorgemaicon.controller.PersonController;
import com.github.jorgemaicon.data.dto.PersonDTO;
import com.github.jorgemaicon.exception.RequiredObjectIsNullException;
import com.github.jorgemaicon.exception.ResoucerNotFoundException;
import static com.github.jorgemaicon.mapper.ObjectMapper.parseListObjects;
import static com.github.jorgemaicon.mapper.ObjectMapper.parseObject;

import com.github.jorgemaicon.mapper.custom.PersonMapper;
import com.github.jorgemaicon.model.Person;
import com.github.jorgemaicon.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    public List<PersonDTO> findAll() {
        logger.info("Finding all Person!");

        var persons = parseListObjects(repository.findAll(), PersonDTO.class);

        persons.forEach(this::addLinksHateoas);

        return persons;
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding a Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResoucerNotFoundException("No records found for this ID."));

        var dto = parseObject(entity, PersonDTO.class);
        addLinksHateoas(dto);

        return dto;
    }

    public PersonDTO create(PersonDTO person) {
        if(person == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating a Person!");

        Person entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addLinksHateoas(dto);

        return dto;
    }

        public PersonDTO update(PersonDTO person) {
        if(person == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating a Person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResoucerNotFoundException("No records found for this ID."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addLinksHateoas(dto);

        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting a Person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResoucerNotFoundException("No records found for this ID."));

        repository.delete(entity);
    }

    private void addLinksHateoas(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).listAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}


