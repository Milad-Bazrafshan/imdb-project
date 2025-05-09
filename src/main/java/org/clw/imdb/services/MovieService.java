package org.clw.imdb.services;

import lombok.RequiredArgsConstructor;
import org.clw.imdb.dto.actor.ActorsInfoFilterDto;
import org.clw.imdb.model.ActorsInfo;
import org.clw.imdb.model.MovieBasicInfo;
import org.clw.imdb.model.MovieFeatureType;
import org.clw.imdb.model.MovieGenre;
import org.clw.imdb.model.repository.MovieBasicInfoRepository;
import org.clw.imdb.model.repository.MovieFeatureTypeRepository;
import org.clw.imdb.model.repository.MovieGenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieGenreRepository movieGenreRepository;
    private final MovieBasicInfoRepository movieBasicInfoRepository;
    private final MovieFeatureTypeRepository movieFeatureTypeRepository;

    public MovieBasicInfo createMovieBasicInfo(MovieBasicInfo movieBasicInfo) {
        return movieBasicInfoRepository.save(movieBasicInfo);
    }

    public MovieBasicInfo updateMovieBasicInfo(MovieBasicInfo movieBasicInfo) {
        return movieBasicInfoRepository.save(movieBasicInfo);
    }

    public MovieFeatureType createMovieFeatureType(MovieFeatureType movieFeatureType) {
        return movieFeatureTypeRepository.save(movieFeatureType);
    }

    public MovieFeatureType updateMovieFeatureType(MovieFeatureType movieFeatureType) {
        return movieFeatureTypeRepository.save(movieFeatureType);
    }

    public MovieFeatureType getMovieFeatureType(Long id) {
        return movieFeatureTypeRepository.findById(id).orElse(null);
    }

    public MovieFeatureType getMovieFeatureType(String code) {
        return movieFeatureTypeRepository.findByCode(code);
    }

    public List<MovieFeatureType> getAllMovieFeatureType() {
        return movieFeatureTypeRepository.findAll();
    }

    public MovieGenre createMovieGenre(MovieGenre movieGenre) {
        return movieGenreRepository.save(movieGenre);
    }

    public MovieGenre getMovieGenre(String code) {
        return movieGenreRepository.findByCode(code);
    }

    public MovieGenre getMovieGenre(Long id) {
        return movieGenreRepository.findById(id).orElse(null);
    }

    public List<MovieGenre> getMovieGenre() {
        return movieGenreRepository.findAll();
    }

    public MovieBasicInfo getMovieBasicInfo(Long id) {
        return movieBasicInfoRepository.findById(id).orElse(null);
    }

    public List<MovieBasicInfo> getTopAverageRateByGenreAndYear(String genreTypeCode, String year) {
        return movieBasicInfoRepository.findAllByGenre_CodeAndMovieYearOrderByAverageRateDesc(genreTypeCode, year);
    }
}
