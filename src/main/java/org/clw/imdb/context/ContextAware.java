package org.clw.imdb.context;

import org.clw.imdb.dto.data.TitleAkasDto;

import java.util.List;

public interface ContextAware {
    List<TitleAkasDto> getTitleAkasList();
}
