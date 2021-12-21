package com.hernanrg.appointmentreminder.service;

import com.hernanrg.appointmentreminder.entities.MailContent;
import com.hernanrg.appointmentreminder.utils.MessageUtils;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/*@author Hernan */
@Service
public class EmailService {

    @Value("${app.sendgrid.templateid}")
    private String templateId;
    @Value("${app.sendgrid.key}")
    private String appKey;

    public ArrayList<String> sendEmailPost(MailContent mc) {
        ArrayList al = new ArrayList();
        MessageUtils mu = new MessageUtils();
        String cuerpo = mu.getBody(mc);
        String header = mu.getHeader(mc);

        SendGrid sendGrid = new SendGrid(appKey);
        Mail mail = prepareMail(mc.getEmail(), header, cuerpo);
        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            if (response != null) {
                al.add("OK");
                System.out.println("response code from sendgrid" + response.getHeaders());
            }else{
                al.add("ERROR");
            }
        } catch (IOException ex) {
            System.err.println("Error on sendEmail, " + ex);
            return al;
        }
        al.add(header);
        al.add(cuerpo);

        return al;
    }

    //enviar header y cuerpo
    @Value("${app.sendgrid.email}")
    private String fromEmail;

    public Mail prepareMail(String email, String header, String cuerpo) {

        Mail mail = new Mail();
        Email fromEmail = new Email(this.fromEmail);
        mail.setFrom(fromEmail);
        Email to = new Email();
        to.setEmail(email);
        Personalization personalization = new Personalization();
        personalization.addTo(to);
        personalization.addDynamicTemplateData("header", header);
        personalization.addDynamicTemplateData("cuerpo", cuerpo);
        mail.addPersonalization(personalization);
        mail.setTemplateId(templateId);
        return mail;
    }
}
