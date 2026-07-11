package jlp.musica03.operarioMicroservice.service;

import jakarta.transaction.Transactional;
import jlp.musica03.operarioMicroservice.exception.OperarioNoEncontradoException;
import jlp.musica03.operarioMicroservice.model.Operario;
import jlp.musica03.operarioMicroservice.repository.OperarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OperarioService {

    private static final Logger log = LoggerFactory.getLogger(OperarioService.class);

    @Autowired
    private OperarioRepository operarioRepository;

    public List<Operario> findAll(){
        log.info("Se generó lista de operarios");
        return operarioRepository.findAll();
    }

    public Operario findById(String rut){
        log.info("Se buscó el operario de rut: {}", rut);
        if( operarioRepository.existsById(rut) ) {
            return operarioRepository.findById(rut).get();
        }else{
            log.warn("No se encontró el operario con rut: {}", rut);
            throw new OperarioNoEncontradoException("Operario con rut "+rut+" no encontrado");
        }
    }

    public Operario save(Operario nuevoOperario, boolean esNuevo){
        Operario o = operarioRepository.save(nuevoOperario);
        String proposito = esNuevo ? "guardado" : "actualizado";
        log.info("Registro operario con rut {} {} correctamente", o.getRut(), proposito);
        return o;
    };

    public void delete(String rut){
        log.info("Eliminando registro operario con rut: {}", rut);
        if( operarioRepository.existsById(rut) ) {
            operarioRepository.deleteById(rut);
            log.info("Registro operario con rut {} fue eliminado correctamente",rut);
        }else {
            log.warn("No se eliminó el registro operario. No se encontró rut: {}", rut);
            throw new OperarioNoEncontradoException("Registro operario con rut "+rut+" no encontrado");
        }

    }


}