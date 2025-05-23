package com.imdb.repositories;

import com.imdb.entities.TitlePrincipal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitlePrincipalRepository extends JpaRepository<TitlePrincipal, String> {
}
