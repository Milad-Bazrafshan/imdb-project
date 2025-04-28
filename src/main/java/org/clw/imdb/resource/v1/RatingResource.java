package org.clw.imdb.resource.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import jakarta.websocket.server.PathParam;
import org.clw.imdb.dto.data.RatingDto;
import org.clw.imdb.dto.filter.ImdbRatingFilterDto;
import org.clw.imdb.facade.RatingFacade;
import org.clw.imdb.model.ImdbRating;
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
public class RatingResource {
    private final DatasetService datasetService;
    private final RatingFacade ratingFacade;

    public RatingResource(DatasetService datasetService, RatingFacade ratingFacade) {
        this.datasetService = datasetService;
        this.ratingFacade = ratingFacade;
    }

    @PostMapping("")
    public RatingDto createRating(@Valid @RequestBody RatingDto dto) {
        return ratingFacade.createRating(dto);
    }

    @GetMapping("")
    public List<RatingDto> getRating(@RequestParam int pageNumber,
                                     @RequestParam int pageSize,
                                     @Nullable @RequestParam String tconst,
                                     @Nullable @RequestParam String averageRating,
                                     @Nullable @RequestParam String numVotes) {
        return ratingFacade.getRating(ImdbRatingFilterDto.builder()
                .pageNumber(pageNumber).pageSize(pageSize)
                .averageRating(averageRating).tconst(tconst).numVotes(numVotes).build());
    }

    @GetMapping("/list")
    public List<ImdbRating> getRatingList(@Valid @RequestParam ImdbRatingFilterDto filterDto) {
        return ratingFacade.getRatingList(filterDto);
    }

    @GetMapping("/{id}")
    public RatingDto getRating(@Valid @PathParam(value = "id") Long id) {
        return ratingFacade.getRating(id);
    }
}

