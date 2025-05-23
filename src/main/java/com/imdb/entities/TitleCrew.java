package com.imdb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "title_crew")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TitleCrew {

    @Id
    private String tconst;
    @Column
    @Lob
    private String directors;
    @Column
    @Lob
    private String writers;
}
