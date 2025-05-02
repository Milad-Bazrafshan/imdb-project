package org.clw.imdb.facade;

import org.clw.imdb.dto.data.TitlePrincipalsDto;
import org.clw.imdb.dto.filter.TitlePrincipalsFilterDto;

import java.util.List;

public interface TitlePrincipalsFacade {
    TitlePrincipalsDto createTitlePrincipals(TitlePrincipalsDto rating);
    List<TitlePrincipalsDto> getTitlePrincipals(TitlePrincipalsFilterDto filter);
    TitlePrincipalsDto getTitlePrincipals(long id);
}
