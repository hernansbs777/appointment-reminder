package com.hernanrg.appointmentreminder.utils;

import com.hernanrg.appointmentreminder.entities.MailContent;
import java.util.Date;

/**
 *
 * @author Hernan
 */
public class MessageUtils {
    
    public String getHeader(MailContent mc) {
        String header = "Hola " + mc.getNombre() + " " + mc.getApellido();
        return header;
    }

    public String getBody(MailContent mc) {
        String body = "Institucion: " +mc.getInstitucion()+
                "\nConsultorio: " +mc.getConsultorio()+
                "\nDireccion: " + mc.getDireccion()+ 
                "\nFecha: " + mc.getFecha().toString()+ 
                "\nHora: " + mc.getHora().toString();
        return body;
    }
}
