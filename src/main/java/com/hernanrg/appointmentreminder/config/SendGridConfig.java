/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hernanrg.appointmentreminder.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Hernan
 */
@Configuration
public class SendGridConfig {
    @Value("${app.sendgrid.key}")
    private String appKey;
    @Bean
    public SendGrid getSendGrid(){
        return new SendGrid(appKey);
    }
    
}
