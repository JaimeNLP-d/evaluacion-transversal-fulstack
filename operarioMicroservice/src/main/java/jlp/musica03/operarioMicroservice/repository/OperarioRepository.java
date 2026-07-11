package jlp.musica03.operarioMicroservice.repository;

import jlp.musica03.operarioMicroservice.model.Operario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperarioRepository extends JpaRepository<Operario, String> {
}
