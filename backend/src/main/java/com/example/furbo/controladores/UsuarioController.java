package com.example.furbo.controladores;

import com.example.furbo.entidades.Usuario;
import com.example.furbo.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")  // Permitir peticiones desde tu frontend
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/login")  // Endpoint para iniciar sesión
    public ResponseEntity<?> iniciarSesion(@RequestBody Usuario usuario) {
        boolean autenticado = usuarioService.autenticarUsuario(usuario);  // Método en tu servicio para autenticar

        System.out.println(usuario.getId() + " " + usuario.getNombre()
                + " " + usuario.getEmail()
                + " " + usuario.getPassword()
                + " " + autenticado);

        if (autenticado) {
            return ResponseEntity.ok().body("Inicio de sesión exitoso");  // Respuesta exitosa
        } else {
            System.out.println("Credenciales incorrectas aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            return ResponseEntity.status(401).body("Credenciales incorrectas");  // Respuesta de error
        }
    }
}
