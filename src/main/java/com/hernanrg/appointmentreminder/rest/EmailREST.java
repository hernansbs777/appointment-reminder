package com.hernanrg.appointmentreminder.rest;
import com.hernanrg.appointmentreminder.entities.Turno;
import com.hernanrg.appointmentreminder.entities.MailContent;
import com.hernanrg.appointmentreminder.entities.Recordatorio;
import com.hernanrg.appointmentreminder.service.EmailService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    EmailService emailService;

    @GetMapping("/ok")
    public String email() {
        return "OK";
    }

//    @RequestMapping(value = "{email}")
//    public ResponseEntity<String> sendEmail(@PathVariable("email") String email) {
//        String emailEnviado = emailService.sendEmail(email);
//        return ResponseEntity.ok(emailEnviado);
//
//    }
//    @GetMapping("/sendemail/{email}")
//    public String sendEmail(@PathVariable(value = "email", required = true) String email) {
//        return emailService.sendEmail(email);
//    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody MailContent mc) {
        ResponseEntity<Turno> turno = turnoRest.getTurnoById(mc.getIdturno());
        mc = completeMailContent(turno.getBody(), mc);
        ArrayList<String> response = emailService.sendEmailPost(mc);
        if (!response.isEmpty() && response.get(0) == "OK") {
            //grabar en base de datos 
            String mensaje = response.get(3);
            return ResponseEntity.ok("EMail Sent");
        } else {
            //grabar error en base de datos
            
           return ResponseEntity.noContent().build();
        }
        
    }
    
    private MailContent completeMailContent(Turno turno, MailContent mc){
        mc.setInstitucion(turno.getInstitucion());
        mc.setConsultorio(turno.getConsultorio());
        mc.setDireccion(turno.getDireccion());
        mc.setFecha(turno.getFecha());
        mc.setHora(turno.getHora());
        
        return mc;
    }

}
