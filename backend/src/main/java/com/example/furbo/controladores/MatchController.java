package com.example.furbo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin; // Asegúrate de importar esta clase
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.furbo.servicios.MatchService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/matches")
public class MatchController {
    @Autowired
    private MatchService matchService;

    
    @GetMapping
    public ResponseEntity<String> getMatches() {
        String matches = matchService.getMatches();
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("¡El servidor está funcionando!");
    }
}
