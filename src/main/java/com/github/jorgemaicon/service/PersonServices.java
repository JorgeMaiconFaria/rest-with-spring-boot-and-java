package com.github.jorgemaicon.service;

import com.github.jorgemaicon.exception.ResoucerNotFoundException;
import com.github.jorgemaicon.model.Person;
import com.github.jorgemaicon.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PersonRepository repository;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResoucerNotFoundException("No records found for this ID."));
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person create(Person person) {
        return repository.save(person);
    }

    public Person update(Person person) {
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResoucerNotFoundException("No records found for this ID."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResoucerNotFoundException("No records found for this ID."));

        repository.delete(entity);
    }
}
