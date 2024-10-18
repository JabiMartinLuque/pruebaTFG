package com.example.furbo.repositorios;

import com.example.furbo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Métodos adicionales si los necesitas, como buscar por email
    Optional<Usuario> findByEmail(String email);
}