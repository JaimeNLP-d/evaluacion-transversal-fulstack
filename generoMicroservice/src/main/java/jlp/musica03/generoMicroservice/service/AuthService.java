package jlp.musica03.generoMicroservice.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public boolean credencialesValidas(String username, String password) {
        System.out.println("Ejecutando AuthService.credencialesValidas()");
        System.out.println("Valor return: " + ("admin".equals(username) && "1234".equals(password)));
        return "admin".equals(username) && "1234".equals(password);
    }
}