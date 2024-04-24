package co.taller2.grupo12.grupo12.ApplicationRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.taller2.grupo12.grupo12.entity.Solicitud;
import co.taller2.grupo12.grupo12.entity.Pago;

@Repository
public interface PagoRepository extends CrudRepository<Pago, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}