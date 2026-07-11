package jlp.musica03.cancionMicroservice.service;

import jakarta.transaction.Transactional;
import jlp.musica03.cancionMicroservice.exception.CancionNoEncontradaException;
import jlp.musica03.cancionMicroservice.model.Cancion;
import jlp.musica03.cancionMicroservice.repository.CancionRepository;
import jlp.musica03.cancionMicroservice.webclient.GeneroClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CancionService {

    private static final Logger log = LoggerFactory.getLogger(CancionService.class);

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private GeneroClient generoClient;

    public List<Cancion> findAll(){
        log.info("Se generó lista de canciones");
        return cancionRepository.findAll();
    }

    public Cancion findById(long id){
        System.out.println("Ejecutando CancionService.findById()");
        log.info("Se buscó la canción de id: {}", id);
        if( cancionRepository.existsById(id) ) {
            return cancionRepository.findById(id).get();
        }else{
            log.warn("No se encontró la canción con ID: {}", id);
            throw new CancionNoEncontradaException("Canción con ID "+id+" no encontrada");
        }
    }

    public Cancion save(Cancion nuevaCancion, boolean esNueva){
        Map<String, Object> generoWC = generoClient.obtenerGeneroId(nuevaCancion.getGeneroId());
        if(generoWC == null || generoWC.isEmpty()) {
            log.warn("No hay registro género con tal ID. Ejecución cancelada");
            throw new RuntimeException("No hay registro género con tal ID. Ejecución cancelada");
        }

        Cancion c = cancionRepository.save(nuevaCancion);
        String proposito = esNueva ? "guardada" : "actualizada";
        log.info("Canción con ID {} {} correctamente", c.getId(), proposito);
        return c;
    };

    public void delete(Long id){
        log.info("Eliminando canción con ID: {}", id);
        if( cancionRepository.existsById(id) ) {
            cancionRepository.deleteById(id);
            log.info("Canción con ID {} fue eliminada correctamente",id);
        }else {
            log.warn("No se eliminó la canción. No se encontró ID: {}", id);
            throw new CancionNoEncontradaException("Canción con ID "+id+" no encontrada");
        }

    }

//    public long count(){ return cancionRepository.count(); }

}