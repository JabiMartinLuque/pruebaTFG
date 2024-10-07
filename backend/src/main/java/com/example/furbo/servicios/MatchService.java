package com.example.furbo.servicios;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MatchService {
    private final RestTemplate restTemplate;

    public MatchService() {
        this.restTemplate = new RestTemplate();
    }

    public String getMatches() {
        String url = "https://api.football-data.org/v4/teams";
        
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
