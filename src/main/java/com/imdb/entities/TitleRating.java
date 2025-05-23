package com.imdb.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "title_ratings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TitleRating {

    @Id
    private String tconst;

    private Double averageRating;
    private Integer numVotes;
}
