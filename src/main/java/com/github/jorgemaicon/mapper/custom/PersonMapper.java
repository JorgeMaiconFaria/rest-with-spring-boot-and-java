package com.github.jorgemaicon.mapper.custom;

import com.github.jorgemaicon.data.dto.v2.PersonDTOV2;
import com.github.jorgemaicon.model.Person;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person person) {
        PersonDTOV2 dto = new PersonDTOV2();

        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());
        dto.setBirthDay(LocalDate.now());

        return dto;
    }

    public Person convertDTOToEntity(PersonDTOV2 dto) {
        Person entity = new Person();

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());
//        entity.setBirthDay(LocalDate.now());

        return entity;
    }
}
