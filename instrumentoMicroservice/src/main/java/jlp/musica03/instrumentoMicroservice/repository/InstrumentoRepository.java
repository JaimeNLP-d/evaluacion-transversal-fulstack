package jlp.musica03.instrumentoMicroservice.repository;

import jlp.musica03.instrumentoMicroservice.model.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentoRepository extends JpaRepository<Instrumento, Long> {
}
