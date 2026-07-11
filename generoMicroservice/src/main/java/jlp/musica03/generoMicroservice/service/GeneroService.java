package jlp.musica03.generoMicroservice.service;

import jakarta.transaction.Transactional;
import jlp.musica03.generoMicroservice.repository.GeneroRepository;
import jlp.musica03.generoMicroservice.exception.GeneroNoEncontradoException;
import jlp.musica03.generoMicroservice.model.Genero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GeneroService {

    private static final Logger log = LoggerFactory.getLogger(GeneroService.class);

    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> findAll(){
        log.info("Se generó lista de generos");
        return generoRepository.findAll();
    }

    public Genero findById(long id){
        log.info("Se buscó el género de id: {}", id);
        if( generoRepository.existsById(id) ) {
            return generoRepository.findById(id).get();
        }else{
            log.warn("No se encontró el género con ID: {}", id);
            throw new GeneroNoEncontradoException("Genero con ID "+id+" no encontrado");
        }
    }

    public Genero save(Genero nuevoGenero, boolean esNuevo){
        Genero g = generoRepository.save(nuevoGenero);
        String proposito = esNuevo ? "guardado" : "actualizado";
        log.info("Registro género con ID {} {} correctamente", g.getId(), proposito);
        return g;
    };

    public void delete(Long id){
        log.info("Eliminando registro género con ID: {}", id);
        if( generoRepository.existsById(id) ) {
            generoRepository.deleteById(id);
            log.info("Registro género con ID {} fue eliminado correctamente",id);
        }else {
            log.warn("No se eliminó el registro género. No se encontró ID: {}", id);
            throw new GeneroNoEncontradoException("Registro género con ID "+id+" no encontrado");
        }

    }

//    public long count(){ return cancionRepository.count(); }

}