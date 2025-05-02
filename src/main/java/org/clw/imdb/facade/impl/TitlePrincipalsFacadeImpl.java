package org.clw.imdb.facade.impl;

import lombok.RequiredArgsConstructor;
import org.clw.imdb.dto.data.TitlePrincipalsDto;
import org.clw.imdb.dto.filter.TitlePrincipalsFilterDto;
import org.clw.imdb.facade.TitlePrincipalsFacade;
import org.clw.imdb.mapper.TitlePrincipalsMapper;
import org.clw.imdb.model.TitlePrincipals;
import org.clw.imdb.services.TitlePrincipalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TitlePrincipalsFacadeImpl implements TitlePrincipalsFacade {
    private TitlePrincipalsService titlePrincipalsService;
    private TitlePrincipalsMapper titlePrincipalsMapper;

    @Autowired
    public TitlePrincipalsFacadeImpl(TitlePrincipalsService titlePrincipalsService, TitlePrincipalsMapper titlePrincipalsMapper) {
        this.titlePrincipalsService = titlePrincipalsService;
        this.titlePrincipalsMapper = titlePrincipalsMapper;
    }

    @Override
    public TitlePrincipalsDto createTitlePrincipals(TitlePrincipalsDto dto) {
        TitlePrincipals principals = new TitlePrincipals();
        principals.setTconst(principals.getTconst());
        titlePrincipalsService.createTitlePrincipals(principals);
        return dto;
    }

    @Override
    public List<TitlePrincipalsDto> getTitlePrincipals(TitlePrincipalsFilterDto filter) {
        Page<TitlePrincipals> ratingPage = titlePrincipalsService.getTitlePrincipals(filter);
        return ratingPage.getContent().stream().map(titlePrincipalsMapper::convert).toList();
    }

    @Override
    public TitlePrincipalsDto getTitlePrincipals(long id) {
        TitlePrincipals titlePrincipals = titlePrincipalsService.getTitlePrincipals(id);
        return titlePrincipalsMapper.convert(titlePrincipals);
    }
}
