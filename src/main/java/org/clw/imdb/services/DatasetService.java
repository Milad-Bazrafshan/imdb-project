package org.clw.imdb.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.clw.imdb.dto.movie.MovieBasicInfoDto;
import org.clw.imdb.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Component
@RequiredArgsConstructor
@Configuration
public class DatasetService {
    private final MovieService movieService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ActorsService actorsService;
    @Value("classpath:dataImport/movieGenre.json")
    Resource genreJsonFile;
    @Value("classpath:dataImport/actorType.json")
    Resource actorTypeJsonFile;
    @Value("classpath:dataImport/actorInfo.json")
    Resource actorsJsonFile;
    @Value("classpath:dataImport/movies.json")
    Resource moviesJsonFile;

    @Bean
    public String loadData() {
        importGenre();
        importActorType();
        importActors();
//        importMovies();
        return "";
    }

    @SneakyThrows
    private void importActorType() {
        try (InputStream values = new FileInputStream(actorTypeJsonFile.getFile())) {
            List<ActorType> genreList = objectMapper.readValue(values, new TypeReference<>() {
            });
            genreList.forEach(item -> {
                actorsService.createActorType(item);
            });
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @SneakyThrows
    private void importActors() {
        try (InputStream values = new FileInputStream(actorsJsonFile.getFile())) {
            List<ActorsInfo> genreList = objectMapper.readValue(values, new TypeReference<>() {
            });
            genreList.forEach(item -> {
                actorsService.createActorsInfo(item);
            });
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @SneakyThrows
    private void importGenre() {
        try (InputStream values = new FileInputStream(genreJsonFile.getFile())) {
            List<MovieGenre> genreList = objectMapper.readValue(values, new TypeReference<>() {
            });
            genreList.forEach(item -> {
                movieService.createMovieGenre(item);
            });
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    /*@SneakyThrows
    private void importMovies() {
        try (InputStream values = new FileInputStream(moviesJsonFile.getFile())) {
            List<MovieBasicInfoDto> genreList = objectMapper.readValue(values, new TypeReference<>() {
            });
            genreList.forEach(item -> {
                movieService.createMovieBasicInfo(item);
            });
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }*/
}
