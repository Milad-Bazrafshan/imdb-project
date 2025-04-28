package org.clw.imdb.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.clw.imdb.config.JwtService;
import org.clw.imdb.dto.enums.RoleType;
import org.clw.imdb.dto.security.AuthenticationRequest;
import org.clw.imdb.dto.security.AuthenticationResponse;
import org.clw.imdb.dto.security.SecurityRegisterRequest;
import org.clw.imdb.model.SecurityToken;
import org.clw.imdb.model.SecurityUser;
import org.clw.imdb.model.repository.SecurityTokenRepository;
import org.clw.imdb.model.repository.UserApiRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final UserApiRepository repository;
    private final SecurityTokenRepository securityTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Override
    public AuthenticationResponse register(SecurityRegisterRequest request) {
        SecurityUser securityUser = SecurityUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(RoleType.ADMIN)
                .build();
        var savedUser = repository.save(securityUser);
        var jwtToken = jwtService.generateToken(securityUser);
        var refreshToken = jwtService.generateRefreshToken(securityUser);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .expired((int) (System.currentTimeMillis() + jwtExpiration))
                .build();
    }

    private void saveUserToken(SecurityUser securityUser, String jwtToken) {
        var token = SecurityToken.builder()
                .securityUser(securityUser)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        securityTokenRepository.save(token);
    }

    private void revokeAllUserTokens(SecurityUser securityUser) {
        var validUserTokens = securityTokenRepository.findAllValidTokenByUser(securityUser.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        securityTokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            var user = this.repository.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public Boolean checkToken(String token) {
        final String username = jwtService.extractUsername(token);
        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            var isTokenValid = securityTokenRepository.findByToken(token)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            return jwtService.isTokenValid(token, userDetails) && isTokenValid;
        } else return false;
    }
}
