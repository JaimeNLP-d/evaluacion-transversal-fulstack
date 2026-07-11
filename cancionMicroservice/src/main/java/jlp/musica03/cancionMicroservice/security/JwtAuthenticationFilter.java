package jlp.musica03.cancionMicroservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jlp.musica03.cancionMicroservice.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Ejecutando JwtAuthenticationFilter.doFilterInternal()");
        String authHeader = request.getHeader("Authorization");

//        System.out.println("authHeader: "+authHeader);

        if ( (authHeader==null) || !(authHeader.startsWith("Bearer ")) ) {
//            System.out.println("authHeader == null: "+ (authHeader == null) );
//            if( !(authHeader == null) ) {System.out.println("authHeader.startsWith('Bearer ') : "+ (authHeader.startsWith("Bearer ")));}
            filterChain.doFilter(request, response);
            System.out.println("No autorizado");
            return;
        }
        System.out.println("Si autorizado");

        String token = authHeader.substring(7);

        if (jwtService.tokenValido(token)) {
            String username = jwtService.extraerUsername(token);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_USER"))
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}