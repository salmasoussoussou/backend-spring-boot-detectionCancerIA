package com.MEDCIN.g04.config;

import com.MEDCIN.g04.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
// vérifie à chaque requête si le token JWT est présent et valide, sinon bloque l’accès.
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader(AUTH_HEADER);

            if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
                logger.debug("Aucun token JWT trouvé dans le header Authorization");
                filterChain.doFilter(request, response);
                return;
            }

            final String jwt = authHeader.substring(BEARER_PREFIX.length());
            final String userEmail = jwtService.extractUsername(jwt);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                logger.debug("Validation du token JWT pour l'utilisateur: {}", userEmail);

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.info("Utilisateur authentifié: {}", userEmail);
                } else {
                    logger.warn("Token JWT invalide pour l'utilisateur: {}", userEmail);
                    sendErrorResponse(response, "Token invalide");
                    return;
                }
            }
        } catch (UsernameNotFoundException ex) {
            logger.error("Utilisateur non trouvé: {}", ex.getMessage());
            sendErrorResponse(response, "Utilisateur non trouvé");
            return;
        } catch (Exception ex) {
            logger.error("Erreur d'authentification JWT", ex);
            sendErrorResponse(response, "Erreur d'authentification");
            return;
        }

        filterChain.doFilter(request, response);
    }
//définit les URL publiques où on ne vérifie pas le token.
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Exclure les endpoints publics du filtrage JWT
        return request.getServletPath().startsWith("/auth/")
                || request.getServletPath().startsWith("/swagger-ui/")
                || request.getServletPath().startsWith("/v3/api-docs/");
    }
// envoie une erreur au client si problème avec le token.
    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(
                String.format("{\"error\": \"Unauthorized\", \"message\": \"%s\"}", message)
        );
    }
}