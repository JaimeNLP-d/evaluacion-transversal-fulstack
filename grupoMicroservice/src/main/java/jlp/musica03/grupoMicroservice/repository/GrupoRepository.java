package jlp.musica03.grupoMicroservice.repository;

import jlp.musica03.grupoMicroservice.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
