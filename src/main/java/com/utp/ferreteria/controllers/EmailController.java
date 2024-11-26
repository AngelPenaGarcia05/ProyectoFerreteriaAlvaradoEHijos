package com.utp.ferreteria.controllers;

import java.util.Map;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import com.utp.ferreteria.dto.CorreoRequest;

@RestController
@RequestMapping("/api/enviar-correo")
public class EmailController {
    @PostMapping
    public ResponseEntity<Map<String, String>> enviarCorreo(@RequestBody CorreoRequest request) {
        Resend resend = new Resend("re_SxssKzKQ_3rQ7XrymBirHBwzCc1ygt3mK");

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("estructuramen23.j@gmail.com")
                .subject("Correo desde la página web por: " + request.getEmail())
                .html("<strong> Hola, soy " + request.getNombre() + "</strong><p>" + request.getMensaje() + "</p>")
                .build();

         try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println(data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Correo enviado con exito");
        return ResponseEntity.ok(response);
    }
}
