package com.example.furbo.servicios;

import com.example.furbo.entidades.Usuario;
import com.example.furbo.repositorios.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;




@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    private List<Usuario> usuarios = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();  // Para generar IDs únicos

    public Usuario registrarUsuario(Usuario usuario) {
        // Asignar un ID único al usuario
        usuario.setId(idCounter.incrementAndGet());
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarios.stream().filter(usuario -> usuario.getId().equals(id)).findFirst();
    }

    public boolean autenticarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepositorio.findByEmail(usuario.getEmail());  // Método para encontrar por email
        
        System.out.println("Buscando usuario con email: " + usuario.getEmail());
        System.out.println("Resultado de la búsqueda: " + usuarioExistente);

        if (usuarioExistente != null) {
            return usuarioExistente.isPresent() && usuarioExistente.get().getPassword().equals(usuario.getPassword());  // Verifica la contraseña
        }
        return false;  // Usuario no encontrado
    }
}