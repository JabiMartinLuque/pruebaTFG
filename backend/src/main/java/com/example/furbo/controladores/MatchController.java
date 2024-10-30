package com.example.furbo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public ResponseEntity<String> getMatchById(@PathVariable String id) {
        String match = matchService.getMatchById(id);
        return ResponseEntity.ok(match);
    }

    @GetMapping("/esp")
    public ResponseEntity<String> getMatchesEsp() {
        String matches = matchService.getMatchesEsp();
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/eng")
    public ResponseEntity<String> getMatchesEng() {
        String matches = matchService.getMatchesEng();
        return ResponseEntity.ok(matches);
    }
}
