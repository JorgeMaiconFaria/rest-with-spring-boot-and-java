package com.github.jorgemaicon.repository;

import com.github.jorgemaicon.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
