package com.example.furbo.servicios;

import com.example.furbo.entidades.Usuario;
import com.example.furbo.repositorios.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;




@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario registrarUsuario(Usuario usuario) {
        // Asignar un ID único al usuario
        return usuarioRepositorio.save(usuario);

    }

    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarios.stream().filter(usuario -> usuario.getId().equals(id)).findFirst();
    }

    public boolean autenticarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepositorio.findByEmail(usuario.getEmail());  // Método para encontrar por email
        
        System.out.println("Buscando usuario con email: " + usuario.getPassword());
        System.out.println("Resultado de la búsqueda: " + usuarioExistente.get().getPassword());

        if (usuarioExistente.isPresent()) {
            return usuarioExistente.get().getPassword().equals(usuario.getPassword());  // Verifica la contraseña
        }
        return false;  // Usuario no encontrado
    }
}