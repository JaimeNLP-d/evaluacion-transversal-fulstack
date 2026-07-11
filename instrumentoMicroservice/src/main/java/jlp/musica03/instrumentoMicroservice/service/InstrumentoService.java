package jlp.musica03.instrumentoMicroservice.service;

import jakarta.transaction.Transactional;
import jlp.musica03.instrumentoMicroservice.exception.InstrumentoNoEncontradoException;
import jlp.musica03.instrumentoMicroservice.model.Instrumento;
import jlp.musica03.instrumentoMicroservice.repository.InstrumentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InstrumentoService {

    private static final Logger log = LoggerFactory.getLogger(InstrumentoService.class);

    @Autowired
    private InstrumentoRepository instrumentoRepository;

    public List<Instrumento> findAll(){
        log.info("Se generó lista de instrumentos");
        return instrumentoRepository.findAll();
    }

    public Instrumento findById(long id){
        log.info("Se buscó el género de id: {}", id);
        if( instrumentoRepository.existsById(id) ) {
            return instrumentoRepository.findById(id).get();
        }else{
            log.warn("No se encontró el género con ID: {}", id);
            throw new InstrumentoNoEncontradoException("Genero con ID "+id+" no encontrado");
        }
    }

    public Instrumento save(Instrumento nuevoInstrumento, boolean esNuevo){
        Instrumento i = instrumentoRepository.save(nuevoInstrumento);
        String proposito = esNuevo ? "guardado" : "actualizado";
        log.info("Registro instrumento con ID {} {} correctamente", i.getId(), proposito);
        return i;
    };

    public void delete(Long id){
        log.info("Eliminando registro instrumento con ID: {}", id);
        if( instrumentoRepository.existsById(id) ) {
            instrumentoRepository.deleteById(id);
            log.info("Registro instrumento con ID {} fue eliminado correctamente",id);
        }else {
            log.warn("No se eliminó el registro instrumento. No se encontró ID: {}", id);
            throw new InstrumentoNoEncontradoException("Registro instrumento con ID "+id+" no encontrado");
        }

    }


}