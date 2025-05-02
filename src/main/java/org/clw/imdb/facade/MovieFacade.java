package org.clw.imdb.facade;

import org.clw.imdb.dto.movie.MovieDto;
import org.clw.imdb.dto.movie.MovieFeatureTypeDto;
import org.clw.imdb.dto.movie.MovieGenreDto;

import java.util.List;

public interface MovieFacade {
    void createMovie(MovieDto movieDto);

    void createMovieGenre(MovieGenreDto movieGenre);
    void createGroupMovieGenre(List<MovieGenreDto> list);
    MovieGenreDto getMovieGenre(String code);
    List<MovieGenreDto> getAllMovieGenre();

    void createMovieFeatureType(MovieFeatureTypeDto movieFeatureTypeDto);
    void createGroupMovieFeatureType(List<MovieFeatureTypeDto> list);
    MovieFeatureTypeDto getMovieFeatureType(String code);
    List<MovieFeatureTypeDto> getAllMovieFeatureType();
}
