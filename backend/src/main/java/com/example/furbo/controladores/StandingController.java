package com.example.furbo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin; // Asegúrate de importar esta clase
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.furbo.servicios.StandingService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/standing")
public class StandingController {
    @Autowired
    private StandingService standingService;

    @GetMapping("/esp")
    public ResponseEntity<String> getStandingsEsp() {
        String standings = standingService.getStandingsEsp();
        return ResponseEntity.ok(standings);
    }

    @GetMapping("/eng")
    public ResponseEntity<String> getStandingsEng() {
        String standings = standingService.getStandingsEng();
        return ResponseEntity.ok(standings);
    }
}
