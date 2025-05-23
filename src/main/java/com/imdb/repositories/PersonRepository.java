package com.imdb.repositories;

import com.imdb.entities.Person;
import com.imdb.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}
