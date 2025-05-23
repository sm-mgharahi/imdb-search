package com.imdb.services;

import com.imdb.entities.*;
import com.imdb.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final TitleRepository titleRepository;
    private final PersonRepository personRepository;
    private final TitleCrewRepository crewRepository;
    private final TitlePrincipalRepository principalRepository;
    private final TitleRatingRepository ratingRepository;

    @Override
    public void run(String... args) throws IOException {
        loadData("src/main/resources/dataset/title.basics.tsv", this::saveTitle);
        loadData("src/main/resources/dataset/name.basics.tsv", this::saveName);
        loadData("src/main/resources/dataset/title.crew.tsv", this::saveCrew);
        loadData("src/main/resources/dataset/title.principals.tsv", this::savePrincipal);
        loadData("src/main/resources/dataset/title.ratings.tsv", this::saveRating);

        log.info("Loaded all datasets");
    }

    private Integer parseInt(String v) {
        return "\\N".equals(v) ? null : Integer.parseInt(v);
    }

    private Double parseDouble(String v) {
        return "\\N".equals(v) ? null : Double.parseDouble(v);
    }

    private String nullIfBlank(String v) {
        return "\\N".equals(v) ? null : v;
    }

    private void loadData(String p, Consumer<List<String[]>> consumer) throws IOException {
        log.info("Loading data from {}", p);
        Path path = Paths.get(p);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            List<String[]> batch = new ArrayList<>();
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] fields = line.split("\t");
                batch.add(fields);
                if (counter % 1000 == 0) {
                    consumer.accept(batch);
                    log.info("Loaded {} rows from {}", counter, p);
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                consumer.accept(batch);
            }
        }
        log.info("Finished loading data from {}", p);
    }

    private void saveTitle(List<String[]> rows) {
        List<Title> records = new ArrayList<>();
        for (String[] f : rows) {
            Title t = new Title(f[0], f[1], f[2], f[3], "1".equals(f[4]), parseInt(f[5]), parseInt(f[6]), parseInt(f[7]), f[8]);
            records.add(t);
        }
        titleRepository.saveAll(records);
    }

    private void saveName(List<String[]> rows) {
        List<Person> records = new ArrayList<>();
        for (String[] f : rows) {
            Person n = new Person(f[0], f[1], parseInt(f[2]), parseInt(f[3]), f[4], f[5]);
            records.add(n);
        }
        personRepository.saveAll(records);
    }

    private void saveCrew(List<String[]> rows) {
        List<TitleCrew> records = new ArrayList<>();
        for (String[] f : rows) {
            TitleCrew c = new TitleCrew(f[0], nullIfBlank(f[1]), nullIfBlank(f[2]));
            records.add(c);
        }

        crewRepository.saveAll(records);
    }


    private void savePrincipal(List<String[]> rows) {

        List<TitlePrincipal> records = new ArrayList<>();
        for (String[] f : rows) {
            TitlePrincipal p = new TitlePrincipal(f[0], parseInt(f[1]), f[2], f[3], nullIfBlank(f[4]), nullIfBlank(f[5]));
            records.add(p);
        }
        principalRepository.saveAll(records);
    }

    private void saveRating(List<String[]> rows) {
        List<TitleRating> records = new ArrayList<>();
        for (String[] f : rows) {
            TitleRating r = new TitleRating(f[0], parseDouble(f[1]), parseInt(f[2]));
            records.add(r);
        }
        ratingRepository.saveAll(records);
    }
}
