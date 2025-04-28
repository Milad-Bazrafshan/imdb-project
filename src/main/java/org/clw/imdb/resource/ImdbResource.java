package org.clw.imdb.resource;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/imdb")
@Tag(name = "Notification", description = "Notification Service")
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Authorization Token",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER)
@PreAuthorize("hasAuthority('READ_PRIVILEGE') and hasRole('ADMIN')")
@SecurityRequirement(name = "Authorization")
public class ImdbResource {
    @PreAuthorize(value = "hasAuthority('FULL_ADMIN') or hasAuthority('MEDIA')")
    @PostMapping("/template/create")
    public void createNotificationTemplate(@Valid @RequestParam(value = "template") String template) {
//        notificationsService.createNotificationTemplate(template, type);
    }
}

