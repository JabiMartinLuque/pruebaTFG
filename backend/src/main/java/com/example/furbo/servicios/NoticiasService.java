package com.example.furbo.servicios;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NoticiasService {

    private final RestTemplate restTemplate;

    public NoticiasService() {
        this.restTemplate = new RestTemplate();
    }

    public String getNoticiasEsp() {
        String url = "https://site.api.espn.com/apis/site/v2/sports/soccer/esp.1/news?lang=es&region=es";

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}
