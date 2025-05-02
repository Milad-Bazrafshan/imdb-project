package org.clw.imdb.facade.impl;

import lombok.RequiredArgsConstructor;
import org.clw.imdb.dto.enums.DataFileType;
import org.clw.imdb.facade.DataSetFacade;
import org.clw.imdb.model.ImdbRating;
import org.clw.imdb.model.TitlePrincipals;
import org.clw.imdb.services.RatingService;
import org.clw.imdb.services.TitlePrincipalsService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSetFacadeImpl implements DataSetFacade {
    private final RatingService ratingService;
    private final TitlePrincipalsService titlePrincipalsService;

    @Override
    public void initData(String filepath, DataFileType type) {
        File file = new File(filepath);
        switch (type) {
            case RATING -> this.createRating(file);
            case PRINCIPALS -> this.createPrincipals(file);
        }
    }

    @Override
    public void createRating(File inputFile) {
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(inputFile))) {
            String line = null;
            while ((line = TSVReader.readLine()) != null) {
                List<String> itemList = Arrays.stream(line.split("\t")).toList();
                ImdbRating rating = new ImdbRating();
                rating.setTconst(itemList.get(0));
                rating.setAverageRating(itemList.get(1));
                rating.setNumVotes(itemList.get(2));
                ratingService.createRating(rating);
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    @Override
    public void createPrincipals(File inputFile) {
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(inputFile))) {
            String line = null;
            while ((line = TSVReader.readLine()) != null) {
                List<String> itemList = Arrays.stream(line.split("\t")).toList();
                TitlePrincipals dto = new TitlePrincipals();
                dto.setTconst(itemList.get(0));
                dto.setOrdering(Integer.valueOf(itemList.get(1)));
                dto.setNconst(itemList.get(2));
                dto.setCategory(itemList.get(3));
                dto.setJobName(itemList.get(4));
                dto.setCharacters(itemList.get(5));
                titlePrincipalsService.createTitlePrincipals(dto);
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
            System.out.println("Something went wrong");
        }
    }
}
