package com.example.furbo.controladores;

import com.example.furbo.entidades.Usuario;
import com.example.furbo.servicios.UsuarioService;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;

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
    public ResponseEntity<Map<String, String>> iniciarSesion(@RequestBody Usuario usuario) {
        boolean autenticado = usuarioService.autenticarUsuario(usuario);  // Método en tu servicio para autenticar

        Map<String, String> response = new HashMap<>();
        if (autenticado) {
            // Generar un token (aquí puedes usar JWT o cualquier otro método)
            String token = "dummy-token"; // Reemplaza esto con la lógica real para generar un token
            response.put("token", token);
            response.put("message", "Inicio de sesión exitoso");
            return ResponseEntity.ok(response);  // Respuesta exitosa
        } else {
            response.put("message", "Credenciales incorrectas");
            return ResponseEntity.status(401).body(response);  // Respuesta de error
        }
    }
}
