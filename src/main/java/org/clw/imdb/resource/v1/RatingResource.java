package org.clw.imdb.resource.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.clw.imdb.dto.data.RatingDto;
import org.clw.imdb.dto.filter.ImdbRatingFilterDto;
import org.clw.imdb.dto.rate.MovieRatingDto;
import org.clw.imdb.facade.RatingFacade;
import org.clw.imdb.model.MovieRating;
import org.clw.imdb.services.DatasetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
@Tag(name = "rating-resource", description = "Rating WebService")
/*@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Authorization Token",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER)*/
//@PreAuthorize("hasAuthority('READ_PRIVILEGE') and hasRole('ADMIN')")
//@SecurityRequirement(name = "Authorization")
@RequiredArgsConstructor
public class RatingResource {
    private final DatasetService datasetService;
    private final RatingFacade ratingFacade;

    @PostMapping("")
    public MovieRatingDto createRating(@Valid @RequestBody MovieRatingDto dto) {
        return ratingFacade.createRating(dto);
    }

    @GetMapping("/movie/rate")
    public MovieRatingDto getMovieRate(@Valid @RequestParam Long movieId) {
        return ratingFacade.getMovieRate(movieId);
    }
}

