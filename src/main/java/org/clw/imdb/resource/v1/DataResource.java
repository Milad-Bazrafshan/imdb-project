package org.clw.imdb.resource.v1;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.clw.imdb.services.DatasetService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/data")
@Tag(name = "Data Resource", description = "Data WebService")
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Authorization Token",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER)
//@PreAuthorize("hasAuthority('READ_PRIVILEGE') and hasRole('ADMIN')")
//@SecurityRequirement(name = "Authorization")
public class DataResource {
    private final DatasetService datasetService;

    public DataResource(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    //    @PreAuthorize(value = "hasAuthority('FULL_ADMIN') or hasAuthority('MEDIA')")
    @PostMapping("/init")
    public void initWithPath(@Valid @RequestParam(value = "filepath") String filePath) {
        datasetService.loadData(filePath);
    }
}

