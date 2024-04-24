package co.taller2.grupo12.grupo12.ApplicationRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.taller2.grupo12.grupo12.entity.Finca;

@Repository

public interface FincaRepository extends CrudRepository<Finca, Long> {
}
