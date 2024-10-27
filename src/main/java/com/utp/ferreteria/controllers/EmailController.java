package com.utp.ferreteria.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

@RestController
@RequestMapping("/enviar-correo")
public class EmailController {
    @PostMapping
    public ResponseEntity<?> enviarCorreo() {
        Resend resend = new Resend("re_SxssKzKQ_3rQ7XrymBirHBwzCc1ygt3mK");

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("estructuramen23.j@gmail.com")
                .subject("it works!")
                .html("<strong>hello world</strong>")
                .build();

         try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println(data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("ok");
    }
}
