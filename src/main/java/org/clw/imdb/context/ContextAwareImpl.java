package org.clw.imdb.context;

import org.clw.imdb.dto.data.TitleAkasDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContextAwareImpl implements ContextAware {
    private List<TitleAkasDto> titleAkasList;
    @Override
    public List<TitleAkasDto> getTitleAkasList() {
        return titleAkasList;
    }
}
