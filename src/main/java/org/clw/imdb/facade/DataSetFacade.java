package org.clw.imdb.facade;

import org.clw.imdb.dto.data.RatingDto;
import org.clw.imdb.dto.enums.DataFileType;
import org.clw.imdb.dto.filter.ImdbRatingFilterDto;
import org.clw.imdb.model.ImdbRating;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface DataSetFacade {
    void initData(String filepath, DataFileType type);
    void createRating(File file);
    void createPrincipals(File file);
}
