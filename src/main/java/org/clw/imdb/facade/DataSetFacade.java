package org.clw.imdb.facade;

import org.clw.imdb.dto.enums.DataFileType;

import java.io.File;

public interface DataSetFacade {
    void initData(String filepath, DataFileType type);
    void createPrincipals(File file);
}
