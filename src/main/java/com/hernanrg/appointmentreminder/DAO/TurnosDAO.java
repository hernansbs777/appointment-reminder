package com.hernanrg.appointmentreminder.DAO;
/**
 *
 * @author Hernan
 */
import com.hernanrg.appointmentreminder.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnosDAO extends JpaRepository<Turno, Integer>{
    
}
