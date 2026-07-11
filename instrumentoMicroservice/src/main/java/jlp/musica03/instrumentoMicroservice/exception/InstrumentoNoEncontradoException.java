package jlp.musica03.instrumentoMicroservice.exception;

public class InstrumentoNoEncontradoException extends RuntimeException {
    public InstrumentoNoEncontradoException(String message) {
        super(message);
    }
}
