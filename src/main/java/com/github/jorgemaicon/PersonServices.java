package com.github.jorgemaicon;

import com.github.jorgemaicon.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id) {
        logger.info("Find one Person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("João");
        person.setLastName("Silva");
        person.setAddress("SP-Brasil");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll() {
        logger.info("Find a lot of Person!");
        List<Person> persons = new ArrayList<Person>();

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person) {
        logger.info("Creating a Person!");
        return person;
    }

    public Person update(Person person) {
        logger.info("Update a Person!");
        return person;
    }

    public void delete(String id) {
        logger.info("Delete a Person!");
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("João");
        person.setLastName("Silva ".concat(Integer.toString(i + 1)));
        person.setAddress("SP-Brasil");
        person.setGender("Male");

        return person;
    }
}
