package com.example.furbo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin; // Asegúrate de importar esta clase
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.furbo.servicios.NoticiasService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/noticias")
public class NoticiasController {
    @Autowired
    private NoticiasService noticiasService;

    @GetMapping("/esp")
    public ResponseEntity<String> getNoticiasEsp() {
        String noticias = noticiasService.getNoticiasEsp();
        return ResponseEntity.ok(noticias);
    }
}
