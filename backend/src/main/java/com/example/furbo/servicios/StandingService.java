package com.example.furbo.servicios;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StandingService {
    private final RestTemplate restTemplate;

    public StandingService() {
        this.restTemplate = new RestTemplate();
    }

    public String getStandingsEsp() {
        //String url = "https://api.football-data.org/v4/standings";
        String url = "http://sports.core.api.espn.com/v2/sports/soccer/leagues/esp.1/seasons/2024/types/1/groups/1/standings/0?lang=es&region=es";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "ffff1aac6e7147cd9eb59da188eeda08");  // Reemplaza TU_API_KEY con tu clave de la API
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
        

        /* 
        return restTemplate.getForObject(url, String.class);
        */
    }

    public String getStandingsEng() {
        //String url = "https://api.football-data.org/v4/standings";
        String url = "http://sports.core.api.espn.com/v2/sports/soccer/leagues/eng.1/seasons/2024/types/1/groups/1/standings/0?lang=en&region=us";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "ffff1aac6e7147cd9eb59da188eeda08");  // Reemplaza TU_API_KEY con tu clave de la API
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
        

        /* 
        return restTemplate.getForObject(url, String.class);
        */
    }
}
