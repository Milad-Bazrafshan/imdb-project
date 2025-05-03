package org.clw.imdb.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.clw.imdb.common.StringStaticUtil;
import org.clw.imdb.dto.actor.ActorsInfoFilterDto;
import org.clw.imdb.dto.filter.MovieActorSpecDto;
import org.clw.imdb.dto.movie.MovieFilterDto;
import org.clw.imdb.model.*;
import org.clw.imdb.model.repository.ActorTypeRepository;
import org.clw.imdb.model.repository.ActorsInfoRepository;
import org.clw.imdb.model.repository.MovieActorRepository;
import org.clw.imdb.model.repository.MovieBasicInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorsService {

    private final ActorsInfoRepository actorsInfoRepository;
    private final MovieActorRepository movieActorRepository;
    private final ActorTypeRepository actorTypeRepository;
    private final MovieBasicInfoRepository movieBasicInfoRepository;

    public ActorsInfo createActorsInfo(ActorsInfo actorsInfo) {
        return actorsInfoRepository.save(actorsInfo);
    }

    public ActorsInfo getActorsInfo(Long id) {
        return actorsInfoRepository.findById(id).orElse(null);
    }

    public Page<ActorsInfo> getActors(ActorsInfoFilterDto filter) {
        return actorsInfoRepository.findAll(Specification.allOf(getImdbRatingSpec(filter)), PageRequest.of(filter.getFrom(), filter.getSize()));
    }

    private List<Specification<ActorsInfo>> getImdbRatingSpec(ActorsInfoFilterDto filter) {
        List<Specification<ActorsInfo>> specifications = new ArrayList<>();

        if (!ObjectUtils.isEmpty(filter.getFirstName())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(ActorsInfo_.FIRST_NAME), filter.getFirstName()));
        }

        if (!ObjectUtils.isEmpty(filter.getLastName())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(ActorsInfo_.LAST_NAME), filter.getLastName()));
        }

        if (!ObjectUtils.isEmpty(filter.getGender())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ActorsInfo_.GENDER), filter.getGender()));
        }

        if (!ObjectUtils.isEmpty(filter.getAlive())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ActorsInfo_.ALIVE), filter.getAlive()));
        }

        if (!ObjectUtils.isEmpty(filter.getFromHeight()) && !ObjectUtils.isEmpty(filter.getToHeight())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(ActorsInfo_.HEIGHT), filter.getFromHeight(), filter.getToHeight()));
        }

        if (!ObjectUtils.isEmpty(filter.getFromAge()) && !ObjectUtils.isEmpty(filter.getToAge())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(ActorsInfo_.AGE), filter.getFromAge(), filter.getToAge()));
        }

        if (!ObjectUtils.isEmpty(filter.getAge())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ActorsInfo_.ALIVE), filter.getAge()));
        }

        return specifications;
    }

    public MovieActor createMovieActor(MovieActor movieActor) {
        return movieActorRepository.save(movieActor);
    }

    public ActorType createActorType(ActorType actorType) {
        return actorTypeRepository.save(actorType);
    }

    public ActorType getActorType(String code) {
        return actorTypeRepository.findByCode(code);
    }

    public List<MovieBasicInfo> getMoviesByActor(MovieFilterDto filter) {
        List<MovieBasicInfo> finalList = new ArrayList<>();
        MovieActorSpecDto specDto = getMoviesByActorSpec(filter);

        List<MovieBasicInfo> movieBasicInfoList = movieBasicInfoRepository.findAll(Specification.allOf(specDto.getMovieSpecs()));

        finalList.addAll(movieBasicInfoList);

        return finalList;
    }

    private MovieActorSpecDto getMoviesByActorSpec(MovieFilterDto filter) {
        List<Specification<ActorsInfo>> actorSpec = new ArrayList<>();
        List<Specification<MovieBasicInfo>> movieSpec = new ArrayList<>();
        List<Specification<MovieActor>> movieActorSpecs = new ArrayList<>();


        if (!ObjectUtils.isEmpty(filter.getActorFilter())) {
            if (!ObjectUtils.isEmpty(filter.getActorFilter().getDirectorFirstName()) || !ObjectUtils.isEmpty(filter.getActorFilter().getWriterFirstName())) {
                movieSpec.add((root, query, criteriaBuilder) ->
                        criteriaBuilder.or(criteriaBuilder.like(root.get(MovieBasicInfo_.ACTORS).get(MovieActor_.ACTOR_INFO).get(ActorsInfo_.FIRST_NAME), "%" + filter.getActorFilter().getDirectorFirstName() + "%"), criteriaBuilder.like(root.get(MovieBasicInfo_.ACTORS).get(MovieActor_.ACTOR_INFO).get(ActorsInfo_.FIRST_NAME), "%" + filter.getActorFilter().getWriterFirstName() + "%")));

                movieSpec.add((root, query, criteriaBuilder) ->
                        criteriaBuilder.or(criteriaBuilder.equal(root.get(MovieBasicInfo_.ACTORS).get(MovieActor_.TYPE).get(ActorType_.CODE), StringStaticUtil.ActorType.Director), criteriaBuilder.equal(root.get(MovieBasicInfo_.ACTORS).get(MovieActor_.TYPE).get(ActorType_.CODE), StringStaticUtil.ActorType.Writer)));
            }
        }

        return MovieActorSpecDto.builder()
                .actorSpecs(actorSpec)
                .movieSpecs(movieSpec)
                .movieActorSpecs(movieActorSpecs)
                .build();
    }
}
