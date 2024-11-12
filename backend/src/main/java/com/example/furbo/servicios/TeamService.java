package com.example.furbo.servicios;

import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.furbo.entidades.Team;
import com.example.furbo.repositorios.TeamRepository;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.annotation.PostConstruct;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final RestTemplate restTemplate;

    public TeamService(TeamRepository teamRepository) {
        this.restTemplate = new RestTemplate();
        this.teamRepository = teamRepository;
    }

    public String getTeams() {
        String url = "https://api.football-data.org/v4/teams?limit=100&offset=100";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "ffff1aac6e7147cd9eb59da188eeda08");  // Reemplaza TU_API_KEY con tu clave de la API
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
        

        /* 
        return restTemplate.getForObject(url, String.class);
        */
    }

    public String getTeamById(String id) {
        String url = "http://sports.core.api.espn.com/v2/sports/soccer/teams/" + id + "?lang=es&region=es";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "ffff1aac6e7147cd9eb59da188eeda08");  // Reemplaza TU_API_KEY con tu clave de la API
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
        

        /* 
        return restTemplate.getForObject(url, String.class);
        */
    }

    @PostConstruct
    public void loadAllTeams() {
        if (teamRepository.count() == 0) {
            // La base de datos está vacía, procedemos a cargar los equipos
            int pageCount = 100; // Ajusta según sea necesario
            for (int page = 1; page <= pageCount; page++) {
                String url = "http://sports.core.api.espn.com/v2/sports/soccer/teams?lang=es&region=es&page=" + page;
                ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
                JsonNode items = response.getBody().get("items");

                for (JsonNode item : items) {
                    String teamUrl = item.get("$ref").asText();
                    loadTeamDetails(teamUrl);
                }
            }
            System.out.println("Carga de equipos completada.");
        } else {
            // Los equipos ya están cargados, no hacemos nada
            System.out.println("Los equipos ya están cargados en la base de datos.");
        }
    }

        public void loadTeamDetails(String teamUrl) {
        // Realizar la solicitud GET al endpoint del equipo
        ResponseEntity<Map> response = restTemplate.getForEntity(teamUrl, Map.class);
        
        // Parsear la respuesta JSON y mapearla al objeto Team
        Map<String, Object> teamData = response.getBody();
        Team team = new Team();
        
        team.setId((String) teamData.get("id"));
        team.setGuid((String) teamData.get("guid"));
        team.setLocation((String) teamData.get("location"));
        team.setName((String) teamData.get("name"));
        team.setAbbreviation((String) teamData.get("abbreviation"));
        team.setDisplayName((String) teamData.get("displayName"));
        team.setShortDisplayName((String) teamData.get("shortDisplayName"));
        team.setColor((String) teamData.get("color"));
        team.setAlternateColor((String) teamData.get("alternateColor"));
        team.setActive((Boolean) teamData.get("isActive"));
        team.setForm((String) teamData.get("form"));

        // Extraer y mapear el logo
        List<Map<String, Object>> logos = (List<Map<String, Object>>) teamData.get("logos");
        if (logos != null && !logos.isEmpty()) {
            team.setLogoUrl((String) logos.get(0).get("href"));
        }

        // Extraer y mapear la información del venue
        Map<String, Object> venue = (Map<String, Object>) teamData.get("venue");
        if (venue != null) {
            team.setVenueName((String) venue.get("fullName"));
            Map<String, Object> address = (Map<String, Object>) venue.get("address");
            if (address != null) {
                team.setVenueCity((String) address.get("city"));
                team.setVenueCountry((String) address.get("country"));
            }
        }

        // Extraer enlace de clubhouse
        List<Map<String, Object>> links = (List<Map<String, Object>>) teamData.get("links");
        if (links != null) {
            for (Map<String, Object> link : links) {
                List<String> rels = (List<String>) link.get("rel");
                if (rels.contains("clubhouse")) {
                    team.setClubhouseUrl((String) link.get("href"));
                    break;
                }
            }
        }

        // Guardar el equipo en la base de datos
        teamRepository.save(team);
    }
}