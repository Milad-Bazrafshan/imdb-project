package org.clw.imdb.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.clw.imdb.dto.filter.TitlePrincipalsFilterDto;
import org.clw.imdb.model.TitlePrincipals;
import org.clw.imdb.model.TitlePrincipals_;
import org.clw.imdb.model.repository.TitlePrincipalsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TitlePrincipalsService {

    private final TitlePrincipalsRepository titlePrincipalsRepository;

    public TitlePrincipals createTitlePrincipals(TitlePrincipals titlePrincipals) {
        return titlePrincipalsRepository.save(titlePrincipals);
    }

    public TitlePrincipals updateTitlePrincipals(TitlePrincipals titlePrincipals) {
        return titlePrincipalsRepository.save(titlePrincipals);
    }

    public TitlePrincipals getTitlePrincipals(Long id) {
        return titlePrincipalsRepository.findById(id).orElse(null);
    }

    public Page<TitlePrincipals> getTitlePrincipals(TitlePrincipalsFilterDto filter) {
        return titlePrincipalsRepository.findAll(Specification.allOf(getTitlePrincipalsSpec(filter)), PageRequest.of(filter.getPageNumber(), filter.getPageSize()));
    }

    private List<Specification<TitlePrincipals>> getTitlePrincipalsSpec(TitlePrincipalsFilterDto filter) {
        List<Specification<TitlePrincipals>> specifications = new ArrayList<>();

        if (!ObjectUtils.isEmpty(filter.getId())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TitlePrincipals_.ID), filter.getId()));
        }

        if (!ObjectUtils.isEmpty(filter.getTconst())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TitlePrincipals_.TCONST), filter.getTconst()));
        }

        if (!ObjectUtils.isEmpty(filter.getNconst())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TitlePrincipals_.NCONST), filter.getNconst()));
        }

        if (!ObjectUtils.isEmpty(filter.getCategory())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TitlePrincipals_.CATEGORY), filter.getCategory()));
        }

        if (!ObjectUtils.isEmpty(filter.getJobName())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TitlePrincipals_.JOB_NAME), filter.getJobName()));
        }

        if (!ObjectUtils.isEmpty(filter.getCharacters())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TitlePrincipals_.CHARACTERS), filter.getCharacters()));
        }

        return specifications;
    }
}
