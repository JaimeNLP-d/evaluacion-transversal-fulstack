package jlp.musica03.cancionMicroservice.exception;

public class CancionNoEncontradaException extends RuntimeException {
    public CancionNoEncontradaException(String message) {
        super(message);
    }
}
