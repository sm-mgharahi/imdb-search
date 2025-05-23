package com.imdb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "title_principals")
@Getter
@Setter
@NoArgsConstructor
public class TitlePrincipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tconst;
    private Integer ordering;
    private String nconst;
    @Column(length = 1000)
    private String category;
    @Column(length = 1000)
    private String job;
    @Column(length = 1000)
    private String characters;

    public TitlePrincipal(String tconst, Integer ordering, String nconst, String category, String job, String characters) {
        this.tconst = tconst;
        this.ordering = ordering;
        this.nconst = nconst;
        this.category = category;
        this.job = job;
        this.characters = characters;
    }
}
