package com.imdb.repositories;

import com.imdb.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, String> {

    @Query(value = "select t.original_title\n" +
            "from titles t \n" +
            "inner join TITLE_CREW tc on t.TConST = tc.TConST\n" +
            "inner join people pw on tc.writers =pw.NConST\n" +
            "where tc.directors = tc.writers and pw.DEATH_YEAR is null ;"
            ,nativeQuery = true)
    List<String> findAllTitlesWithSameDirectorAndWritersAndTheyAreAlive();




    @Query(
            value =
            "select t.original_title\n"+
            "from titles t\n"+
            "where t.tconst in (\n"+
            "    select tp.tconst\n"+
            "    from title_principals tp\n"+
            "    inner join people p on p.nconst = tp.nconst\n"+
            "    where p.primary_name in (:actor1, :actor2)\n"+
            "      and tp.characters = 'actors'\n"+
            "    group by tp.tconst\n"+
            "    having count(distinct p.primary_name) = 2)\n",
            nativeQuery = true
    )
    List<String> findTitlesByTwoActors(@Param("actor1") String actor1, @Param("actor2") String actor2);



    @Query(value = 
        "select t.original_title \n"+
        "from titles t\n"+
        "join title_ratings r on t.tconst = r.tconst\n"+
        "where (t.start_year, r.average_rating * r.num_votes) in (\n"+
        "    select t2.start_year, max(r2.average_rating * r2.num_votes)\n"+
        "    from titles t2\n"+
        "    inner join title_ratings r2 on t2.tconst = r2.tconst \n"+
        "    where t2.start_year is not null and t2.genres like %:genre% \n"+
        "    group by t2.start_year\n"+
        ")\n"+
        "and t.genres like %:genre%\n"+
        "order by t.start_year\n"
        , nativeQuery = true)
    List<String> findBestTitlesPerYearByGenre(@Param("genre") String genre);
}