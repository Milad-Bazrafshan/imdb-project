package org.clw.imdb.resource.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.clw.imdb.dto.data.TitlePrincipalsDto;
import org.clw.imdb.dto.filter.TitlePrincipalsFilterDto;
import org.clw.imdb.facade.TitlePrincipalsFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/principals")
@Tag(name = "title-principals-resource", description = "Title Principals WebService")
/*@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Authorization Token",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER)*/
//@PreAuthorize("hasAuthority('READ_PRIVILEGE') and hasRole('ADMIN')")
//@SecurityRequirement(name = "Authorization")
@RequiredArgsConstructor
public class TitlePrincipalsResource {
    private final TitlePrincipalsFacade titlePrincipalsFacade;

    @PostMapping("")
    public TitlePrincipalsDto createTitlePrincipals(@Valid @RequestBody TitlePrincipalsDto dto) {
        return titlePrincipalsFacade.createTitlePrincipals(dto);
    }

    @GetMapping("")
    public List<TitlePrincipalsDto> getTitlePrincipals(@RequestParam TitlePrincipalsFilterDto filter) {
        return titlePrincipalsFacade.getTitlePrincipals(filter);
    }

    @GetMapping("/{id}")
    public TitlePrincipalsDto getTitlePrincipals(@Valid @PathParam(value = "id") Long id) {
        return titlePrincipalsFacade.getTitlePrincipals(id);
    }
}

