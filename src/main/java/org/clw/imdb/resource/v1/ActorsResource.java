package org.clw.imdb.resource.v1;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.clw.imdb.dto.actor.ActorsInfoDto;
import org.clw.imdb.dto.actor.ActorsInfoFilterDto;
import org.clw.imdb.dto.actor.MovieActorDto;
import org.clw.imdb.dto.enums.Gender;
import org.clw.imdb.dto.movie.MovieFilterDto;
import org.clw.imdb.dto.movie.TypeDto;
import org.clw.imdb.facade.ActorsFacade;
import org.clw.imdb.facade.MovieFacade;
import org.clw.imdb.model.MovieBasicInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actors")
@Tag(name = "actors-resource", description = "Actors WebService")
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Authorization Token",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER)
@RequiredArgsConstructor
public class ActorsResource {
    private final MovieFacade movieFacade;
    private final ActorsFacade actorsFacade;

    @PostMapping("/create-movie-actor")
    public MovieActorDto createMovieActor(@Valid @RequestBody MovieActorDto dto) {
        return actorsFacade.createMovieActor(dto);
    }

    @PostMapping("/info")
    public ActorsInfoDto createActors(@Valid @RequestBody ActorsInfoDto dto) {
        return actorsFacade.createActorsInfo(dto);
    }

    @PostMapping("/type")
    public TypeDto createActorType(@Valid @RequestBody TypeDto dto) {
        return actorsFacade.createActorType(dto);
    }

    @PostMapping("/info/group")
    public void createGroupActors(@Valid @RequestBody List<ActorsInfoDto> actors) {
        actorsFacade.createGroupActorsInfo(actors);
    }

    @GetMapping("")
    public List<ActorsInfoDto> getActors(@Valid @RequestParam int from,
                                         @Valid @RequestParam int size,
                                         @Nullable @RequestParam String firstName,
                                         @Nullable @RequestParam String lastName,
                                         @Nullable @RequestParam Gender gender,
                                         @Nullable @RequestParam Boolean alive,
                                         @Nullable @RequestParam String fromHeight,
                                         @Nullable @RequestParam String toHeight,
                                         @Nullable @RequestParam Integer age,
                                         @Nullable @RequestParam Integer fromAge,
                                         @Nullable @RequestParam Integer toAge) {
        return actorsFacade.getActors(ActorsInfoFilterDto.builder()
                .from(from).size(size)
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .fromHeight(fromHeight)
                .toHeight(toHeight)
                .fromAge(fromAge)
                .toAge(toAge)
                .alive(alive)
                .age(age).build());
    }

    @PostMapping("/by-actor")
    public List<MovieBasicInfo> getMoviesByActor(@RequestBody MovieFilterDto filterDto) {
        return actorsFacade.getMoviesByActor(filterDto);
    }
}

