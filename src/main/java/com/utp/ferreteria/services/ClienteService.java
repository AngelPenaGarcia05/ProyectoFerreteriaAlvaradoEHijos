package com.utp.ferreteria.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utp.ferreteria.respositories.ClienteRepository;
import com.utp.ferreteria.models.Cliente;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerClientes(){
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente guardarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void actualizarCliente(Long id, Cliente cliente){
        clienteRepository.findById(id).ifPresent(clienteExistente -> {
            BeanUtils.copyProperties(cliente, clienteExistente, "id");
            clienteRepository.save(clienteExistente);
        });
    }

    public void eliminarCliente(Long id){
        clienteRepository.findById(id).ifPresent(cliente -> {
            clienteRepository.delete(cliente);
        });
    }
}
