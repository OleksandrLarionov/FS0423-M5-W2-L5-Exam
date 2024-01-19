package larionovoleksanr.exam.repositories;

import larionovoleksanr.exam.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteDAO extends JpaRepository<Dipendente, Long> {
}
