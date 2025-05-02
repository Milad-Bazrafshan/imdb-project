package org.clw.imdb.resource.v1;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.clw.imdb.dto.enums.DataFileType;
import org.clw.imdb.facade.DataSetFacade;
import org.clw.imdb.services.DatasetService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/data")
@Tag(name = "data-resource", description = "Data WebService")
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Authorization Token",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER)
@RequiredArgsConstructor
public class DataResource {
    private final DataSetFacade facade;

    @PostMapping("/initData")
    public void initRating(@Valid @RequestParam(value = "filepath") String filePath, @RequestParam(value = "type") DataFileType type) {
        facade.initData(filePath, type);
    }
}

