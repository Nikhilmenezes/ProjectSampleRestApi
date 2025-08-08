package com.example.Items.controller;


import com.example.Items.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    @Autowired
    private  EmailService emailService;

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
        emailService.sendEmail("nikhil.menezes9@gmail.com",
                "this is subject",
                "this is body of email");
    }



}
