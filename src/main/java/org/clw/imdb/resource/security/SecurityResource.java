package org.clw.imdb.resource.security;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.clw.imdb.dto.security.AuthenticationRequest;
import org.clw.imdb.dto.security.AuthenticationResponse;
import org.clw.imdb.services.SecurityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/security")
@Tag(name = "security-resource", description = "Security Service")
@RequiredArgsConstructor
public class SecurityResource {
    private final SecurityService securityService;

    @PreAuthorize(value = "hasAuthority('FULL_ADMIN') or hasAuthority('MEDIA')")
    @PostMapping("/token")
    public AuthenticationResponse getToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return securityService.authenticate(authenticationRequest);
    }
}

