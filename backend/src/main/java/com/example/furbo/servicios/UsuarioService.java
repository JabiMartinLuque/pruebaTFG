package com.example.furbo.servicios;

import com.example.furbo.entidades.Usuario;
import com.example.furbo.repositorios.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;




@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    public Usuario registrarUsuario(Usuario usuario) {
        // Asignar un ID único al usuario
        return usuarioRepositorio.save(usuario);

    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id);
    }

    public boolean autenticarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepositorio.findByEmail(usuario.getEmail()); 
        
        System.out.println("Buscando usuario con email: " + usuario.getPassword());
        System.out.println("Resultado de la búsqueda: " + usuarioExistente.get().getPassword());

        if (usuarioExistente.isPresent()) {
            return usuarioExistente.get().getPassword().equals(usuario.getPassword());  
        }
        return false;  
    }
}