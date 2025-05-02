package org.clw.imdb.resource.v1;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.clw.imdb.dto.movie.MovieDto;
import org.clw.imdb.dto.movie.MovieFeatureTypeDto;
import org.clw.imdb.dto.movie.MovieGenreDto;
import org.clw.imdb.facade.MovieFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
@Tag(name = "movie-resource", description = "Movie WebService")
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Authorization Token",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER)
@RequiredArgsConstructor
public class MovieResource {
    private final MovieFacade movieFacade;

    @PostMapping("")
    public void createMovie(@Valid @RequestBody MovieDto dto) {
        movieFacade.createMovie(dto);
    }

    @PostMapping("/genre")
    public void createMovieGenre(@Valid @RequestBody MovieGenreDto dto) {
        movieFacade.createMovieGenre(dto);
    }

    @PostMapping("/genre/group")
    public void createGroupMovieGenre(@Valid @RequestBody List<MovieGenreDto> genres) {
        movieFacade.createGroupMovieGenre(genres);
    }

    @GetMapping("/genre")
    public List<MovieGenreDto> getAllMovieGenre() {
        return movieFacade.getAllMovieGenre();
    }

    @GetMapping("/genre/{code}")
    public MovieGenreDto getMovieGenre(@Valid @PathVariable String code) {
        return movieFacade.getMovieGenre(code);
    }


    @PostMapping("/feature/type")
    public void createMovieFeatureType(@Valid @RequestBody MovieFeatureTypeDto dto) {
        movieFacade.createMovieFeatureType(dto);
    }

    @PostMapping("/feature/group")
    public void createGroupMovieFeatureType(@Valid @RequestBody List<MovieFeatureTypeDto> genres) {
        movieFacade.createGroupMovieFeatureType(genres);
    }

    @GetMapping("/feature/type")
    public List<MovieFeatureTypeDto> getAllMovieFeatureType() {
        return movieFacade.getAllMovieFeatureType();
    }

    @GetMapping("/feature/type/{code}")
    public MovieFeatureTypeDto getMovieFeatureType(@Valid @PathVariable String code) {
        return movieFacade.getMovieFeatureType(code);
    }
}

