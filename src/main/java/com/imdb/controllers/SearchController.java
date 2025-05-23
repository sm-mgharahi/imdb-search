package com.imdb.controllers;

import com.imdb.services.DataLoader;
import com.imdb.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;


    @GetMapping("/director-writer-alive")
    public List<String> getTitlesBySameDirectorAndWriterAlive() {
        return searchService.getDirectorWriterAlive();
    }

    @GetMapping("/common-titles")
    public List<String> getTitlesByTwoActors(
            @RequestParam String actor1,
            @RequestParam String actor2
    ) {
        return searchService.getCommonTitles(actor1, actor2);
    }

    @GetMapping("/best-by-genre")
    public List<String> getBestTitlesByGenre(@RequestParam String genre) {
        return searchService.getBestByGenre(genre);
    }
}