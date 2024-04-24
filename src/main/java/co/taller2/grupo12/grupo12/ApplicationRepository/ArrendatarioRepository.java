package co.taller2.grupo12.grupo12.ApplicationRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.taller2.grupo12.grupo12.entity.Arrendatario;
@Repository
public interface ArrendatarioRepository extends CrudRepository<Arrendatario, Long> {
}