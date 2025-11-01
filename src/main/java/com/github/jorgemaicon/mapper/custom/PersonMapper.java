package com.github.jorgemaicon.mapper.custom;

import com.github.jorgemaicon.data.dto.PersonDTO;
import com.github.jorgemaicon.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {

    public PersonDTO convertEntityToDTO(Person person) {
        PersonDTO dto = new PersonDTO();

        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());

        return dto;
    }

    public Person convertDTOToEntity(PersonDTO dto) {
        Person entity = new Person();

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());

        return entity;
    }
}
