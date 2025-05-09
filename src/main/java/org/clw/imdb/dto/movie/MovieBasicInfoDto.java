package org.clw.imdb.dto.movie;

import lombok.Data;
import org.clw.imdb.dto.actor.AddMovieActorDto;
import org.clw.imdb.model.MovieGenre;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieBasicInfoDto {
    private String title;
    private String description;
    private String storyline;
    private String taglines;
    private String year;
    private MovieGenre genre;
    private List<MovieActorDto> actors;

    public void addActor(MovieActorDto actor) {
        if (actors == null) {
            actors = new ArrayList<>();
        }
        actors.add(actor);
    }
}
