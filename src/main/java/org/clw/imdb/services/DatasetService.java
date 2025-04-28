package org.clw.imdb.services;

import lombok.RequiredArgsConstructor;
import org.clw.imdb.model.ImdbRating;
import org.clw.imdb.model.TitlePrincipals;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

@Service
@Component
@RequiredArgsConstructor
public class DatasetService {
    private final RatingService ratingService;
    private final PrincipalsService principalsService;

    public void loadData(String filePath) {
        File file = new File(filePath);
        createRating(file);
    }

    public void createRating(File inputFile) {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(inputFile))) {
            String line = null;
            Map<String, Object> mapHeaders = new HashMap<>();
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t");
                List<String> itemList = Arrays.stream(line.split("\t")).toList();
                ImdbRating rating = new ImdbRating();
                rating.setTconst(itemList.get(0));
                rating.setAverageRating(itemList.get(1));
                rating.setNumVotes(itemList.get(2));
                ratingService.createRating(rating);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    public void createPrincipals(File inputFile) {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(inputFile))) {
            String line = null;
            Map<String, Object> mapHeaders = new HashMap<>();
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t");
                List<String> itemList = Arrays.stream(line.split("\t")).toList();
                TitlePrincipals rating = new TitlePrincipals();
                rating.setTconst(itemList.get(0));
                rating.setAverageRating(itemList.get(1));
                rating.setNumVotes(itemList.get(2));
                principalsService.createTitlePrincipals(rating);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    public static ArrayList<String[]> tsvrV2(File test2) {
        ArrayList<String[]> Data = new ArrayList<>();
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(test2))) {
            String line = null;
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t");
                Data.add(lineItems);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return Data;
    }
}
