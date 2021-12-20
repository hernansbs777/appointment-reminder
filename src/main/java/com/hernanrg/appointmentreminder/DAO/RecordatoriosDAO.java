package com.hernanrg.appointmentreminder.DAO;
import com.hernanrg.appointmentreminder.entities.Recordatorio;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Hernan
 */
public interface RecordatoriosDAO extends JpaRepository<Recordatorio, Integer>{
    
}
