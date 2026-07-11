package jlp.musica03.generoMicroservice.repository;

import jlp.musica03.generoMicroservice.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
