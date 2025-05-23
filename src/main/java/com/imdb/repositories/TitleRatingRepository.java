package com.imdb.repositories;

import com.imdb.entities.Title;
import com.imdb.entities.TitleRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRatingRepository extends JpaRepository<TitleRating, String> {
}
