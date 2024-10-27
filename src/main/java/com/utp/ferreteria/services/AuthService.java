package com.utp.ferreteria.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.utp.ferreteria.dto.JwtResponse;
import com.utp.ferreteria.dto.LoginRequest;
import com.utp.ferreteria.dto.RegisterRequest;
import com.utp.ferreteria.dto.Role;
import com.utp.ferreteria.models.Cliente;
import com.utp.ferreteria.respositories.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApiReniecService apiReniecService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getContrasena())
        );
        UserDetails userDetails =  clienteRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return JwtResponse.builder()
                .token(token)
                .build();
    }

    public JwtResponse register(RegisterRequest request) {
        Map<String, Object> apiReniecResponse = apiReniecService.consultarDni(request.getDni());
        String nombres = apiReniecResponse.get("nombres").toString();
        String apellidos = apiReniecResponse.get("apellidoPaterno").toString() + " " + apiReniecResponse.get("apellidoMaterno").toString();
        Cliente cliente = Cliente.builder()
                .dni(request.getDni())
                .nombres(nombres)
                .apellidos(apellidos)
                .direccion(request.getDireccion())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .rol(Role.USER)
                .build();
        clienteRepository.save(cliente);
        return JwtResponse.builder()
                .token(jwtService.getToken(cliente))
                .build();
    }

}
