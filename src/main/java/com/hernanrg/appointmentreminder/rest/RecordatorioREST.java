package com.hernanrg.appointmentreminder.rest;
import com.hernanrg.appointmentreminder.entities.Recordatorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hernanrg.appointmentreminder.DAO.RecordatoriosDAO;

/**
 * @author Hernan
 */
@RestController
@RequestMapping("recordatorios")
public class RecordatorioREST {

    @Autowired
    private RecordatoriosDAO recordatoriosDAO;

    @GetMapping
    public ResponseEntity<List<Recordatorio>> getAppointment() {
        List<Recordatorio> turnos = recordatoriosDAO.findAll();
        return ResponseEntity.ok(turnos);
    }

    @RequestMapping(value = "{idturno}")
    public ResponseEntity<Recordatorio> getAppointmentById(@PathVariable("idturno") Integer idturno) {
        Optional<Recordatorio> turno = recordatoriosDAO.findById(idturno);
        if (turno.isPresent()) {
            return ResponseEntity.ok(turno.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Recordatorio> createAppointment(@RequestBody Recordatorio recordatorio) {
        Recordatorio newAppointment = recordatoriosDAO.save(recordatorio);
        return ResponseEntity.ok(newAppointment);
    }

    @DeleteMapping(value = "{idturno}")
    public ResponseEntity<Recordatorio> deleteAppointment(@PathVariable("idturno") Integer idturno) {
        recordatoriosDAO.deleteById(idturno);
        return ResponseEntity.ok(null);
    }
}
