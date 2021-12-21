package com.hernanrg.appointmentreminder.rest;

import com.hernanrg.appointmentreminder.entities.Turno;
import com.hernanrg.appointmentreminder.entities.MailContent;
import com.hernanrg.appointmentreminder.entities.Recordatorio;
import com.hernanrg.appointmentreminder.service.EmailService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hernan
 */
@RestController
@RequestMapping("enviar")
public class EmailREST {

    @Autowired
    private TurnoREST turnoRest;

    @Autowired
    private RecordatorioREST recordatorioRest;
    
    @Autowired
    EmailService emailService;

    @GetMapping("/ok")
    public String email() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody MailContent mc) {
        ResponseEntity<Turno> turno = turnoRest.getTurnoById(mc.getIdturno());
        mc = completeMailContent(turno.getBody(), mc);
        ArrayList<String> emailResponse = emailService.sendEmailPost(mc);
        ResponseEntity<String> response;        
        Recordatorio r = new Recordatorio();
        r.setEmail(mc.getEmail());
        r.setIdturno(mc.getIdturno());
        if (!emailResponse.isEmpty() && emailResponse.get(0) == "OK") {
            r.setEnviado(1);
            r.setCuerpo(emailResponse.get(1)+emailResponse.get(2));
            recordatorioRest.createAppointment(r);
            return ResponseEntity.ok("EMail Sent");
        } else {
            r.setEnviado(2);
            r.setCuerpo(emailResponse.get(1)+emailResponse.get(2));
            recordatorioRest.createAppointment(r);
            return ResponseEntity.noContent().build();
        }
    }

    private MailContent completeMailContent(Turno turno, MailContent mc) {
        mc.setInstitucion(turno.getInstitucion());
        mc.setConsultorio(turno.getConsultorio());
        mc.setDireccion(turno.getDireccion());
        mc.setFecha(turno.getFecha());
        mc.setHora(turno.getHora());

        return mc;
    }

}
