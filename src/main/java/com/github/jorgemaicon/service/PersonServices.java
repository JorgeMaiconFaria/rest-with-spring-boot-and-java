package com.github.jorgemaicon.service;

import com.github.jorgemaicon.data.dto.v1.PersonDTO;
import com.github.jorgemaicon.data.dto.v2.PersonDTOV2;
import com.github.jorgemaicon.exception.ResoucerNotFoundException;
import static com.github.jorgemaicon.mapper.ObjectMapper.parseListObjects;
import static com.github.jorgemaicon.mapper.ObjectMapper.parseObject;

import com.github.jorgemaicon.mapper.custom.PersonMapper;
import com.github.jorgemaicon.model.Person;
import com.github.jorgemaicon.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    public List<PersonDTO> findAll() {
        logger.info("Finding all Person!");

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding a Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResoucerNotFoundException("No records found for this ID."));

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating a Person!");

        Person entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("Creating a Person V2!");

        var entity = converter.convertDTOToEntity(person);

        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating a Person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResoucerNotFoundException("No records found for this ID."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting a Person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResoucerNotFoundException("No records found for this ID."));

        repository.delete(entity);
    }
}
