package jlp.musica03.grupoMicroservice.service;

import jakarta.transaction.Transactional;
import jlp.musica03.grupoMicroservice.exception.GrupoNoEncontradoException;
import jlp.musica03.grupoMicroservice.model.Grupo;
import jlp.musica03.grupoMicroservice.repository.GrupoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GrupoService {

    private static final Logger log = LoggerFactory.getLogger(GrupoService.class);

    @Autowired
    private GrupoRepository grupoRepository;

    public List<Grupo> findAll(){
        log.info("Se generó lista de grupos");
        return grupoRepository.findAll();
    }

    public Grupo findById(long id){
        log.info("Se buscó el grupo de id: {}", id);
        if( grupoRepository.existsById(id) ) {
            return grupoRepository.findById(id).get();
        }else{
            log.warn("No se encontró el grupo con ID: {}", id);
            throw new GrupoNoEncontradoException("Grupo con ID "+id+" no encontrado");
        }
    }

    public Grupo save(Grupo nuevoGrupo, boolean esNuevo){
        Grupo g = grupoRepository.save(nuevoGrupo);
        String proposito = esNuevo ? "guardado" : "actualizado";
        log.info("Registro grupo con ID {} {} correctamente", g.getId(), proposito);
        return g;
    };

    public void delete(Long id){
        log.info("Eliminando registro género con ID: {}", id);
        if( grupoRepository.existsById(id) ) {
            grupoRepository.deleteById(id);
            log.info("Registro grupo con ID {} fue eliminado correctamente",id);
        }else {
            log.warn("No se eliminó el registro grupo. No se encontró ID: {}", id);
            throw new GrupoNoEncontradoException("Registro grupo con ID "+id+" no encontrado");
        }

    }

//    public long count(){ return cancionRepository.count(); }

}