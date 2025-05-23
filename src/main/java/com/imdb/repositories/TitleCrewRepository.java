package com.imdb.repositories;

import com.imdb.entities.Person;
import com.imdb.entities.TitleCrew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleCrewRepository extends JpaRepository<TitleCrew, String> {
}
