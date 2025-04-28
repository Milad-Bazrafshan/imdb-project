package org.clw.imdb.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.clw.imdb.dto.security.AuthenticationRequest;
import org.clw.imdb.dto.security.AuthenticationResponse;
import org.clw.imdb.dto.security.SecurityRegisterRequest;

import java.io.IOException;

public interface SecurityService {

    public AuthenticationResponse register(SecurityRegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    public Boolean checkToken(String token) throws IOException;
}
