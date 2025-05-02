package org.clw.imdb.dto.movie;

import lombok.Data;
import org.clw.imdb.dto.actor.MovieActorDto;
import org.clw.imdb.model.MovieGenre;

import java.util.List;

@Data
public class MovieDto {
    private String title;
    private String description;
    private String storyline;
    private String taglines;
    private String genreTypeCode;
    private List<MovieActorDto> actors;
    private List<MovieFeatureDto> features;
}
