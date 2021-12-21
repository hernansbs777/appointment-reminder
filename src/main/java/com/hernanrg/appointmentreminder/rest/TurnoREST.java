package com.hernanrg.appointmentreminder.rest;

import com.hernanrg.appointmentreminder.entities.Turno;
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
import com.hernanrg.appointmentreminder.DAO.TurnosDAO;
import com.hernanrg.appointmentreminder.entities.TurnoTexto;

/**
 * @author Hernan
 */
@RestController
@RequestMapping("turnos")
public class TurnoREST {

    @Autowired
    private TurnosDAO turnoDAO;

    @GetMapping
    public ResponseEntity<List<Turno>> getTurnos() {
        List<Turno> turnos = turnoDAO.findAll();
        return ResponseEntity.ok(turnos);
    }

    @RequestMapping(value = "{idturno}")
    public ResponseEntity<Turno> getTurnoById(@PathVariable("idturno") Integer idturno) {
        Optional<Turno> turno = turnoDAO.findById(idturno);
        if (turno.isPresent()) {
            return ResponseEntity.ok(turno.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Turno> createTurno(@RequestBody Turno turno) {
        Turno newTurno = turnoDAO.save(turno);
        return ResponseEntity.ok(newTurno);
    }

    @DeleteMapping(value = "{idturno}")
    public ResponseEntity<Turno> deleteTurno(@PathVariable("idturno") Integer idturno) {
        turnoDAO.deleteById(idturno);
        return ResponseEntity.ok(null);
    }
}
