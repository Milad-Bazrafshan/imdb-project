package org.clw.imdb.dto.security;

import lombok.Builder;
import lombok.Data;
import org.clw.imdb.dto.enums.RoleType;

@Data
@Builder
public class SecurityRegisterRequest {
    private String name;
    private String username;
    private String password;
    private RoleType role;
}
