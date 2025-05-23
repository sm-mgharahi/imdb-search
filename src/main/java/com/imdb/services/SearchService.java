package com.imdb.services;

import com.imdb.repositories.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final TitleRepository titleRepository;

    public List<String> getDirectorWriterAlive() {
        return titleRepository.findAllTitlesWithSameDirectorAndWritersAndTheyAreAlive();
    }

    public List<String> getCommonTitles(String actor1, String actor2) {
        return titleRepository.findTitlesByTwoActors(actor1, actor2);
    }

    public List<String> getBestByGenre(String genre) {
        return titleRepository.findBestTitlesPerYearByGenre(genre);
    }
}
