package org.clw.imdb.dto.movie;

import lombok.Data;
import org.clw.imdb.dto.actor.AddMovieActorDto;

import java.util.List;

@Data
public class MovieDto {
    private String title;
    private String description;
    private String storyline;
    private String taglines;
    private String genreTypeCode;
    private String year;
    private List<AddMovieActorDto> actors;
    private List<MovieFeatureDto> features;
}
