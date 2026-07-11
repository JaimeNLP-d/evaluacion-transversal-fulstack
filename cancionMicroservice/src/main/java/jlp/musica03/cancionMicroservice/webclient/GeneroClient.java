package jlp.musica03.cancionMicroservice.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class GeneroClient {
    private final WebClient webClient;

    public GeneroClient(@Value("${genero-service.url}") String generoServidor){
        this.webClient = WebClient.builder().baseUrl(generoServidor).build();
    }

    public Map<String, Object> obtenerGeneroId(Long id){
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Genero no encontrado")) )
                .bodyToMono(Map.class)
                .block();

    }

}
