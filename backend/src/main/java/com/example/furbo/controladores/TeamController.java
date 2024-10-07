package com.example.furbo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.furbo.servicios.TeamService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;


    @GetMapping
    public ResponseEntity<String> getTeams() {
        String teams = teamService.getTeams();
        return ResponseEntity.ok(teams);
    }

    
}