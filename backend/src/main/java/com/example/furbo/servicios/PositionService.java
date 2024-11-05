package com.example.furbo.servicios;

import com.example.furbo.entidades.Position;
import com.example.furbo.repositorios.PositionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final RestTemplate restTemplate;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
        this.restTemplate = new RestTemplate();
    }

    @PostConstruct
    public void loadAllPositions() {
        if (positionRepository.count() == 0) {
            String baseUrl = "https://sports.core.api.espn.com/v2/sports/soccer/leagues/esp.1/positions?lang=es&region=es";
            List<String> positionUrls = new ArrayList<>();

            // Obtener la lista de posiciones
            JsonNode response = restTemplate.getForObject(baseUrl, JsonNode.class);
            int pageCount = response.get("pageCount").asInt();

            for (int page = 1; page <= pageCount; page++) {
                String url = baseUrl + "&page=" + page;
                JsonNode pageResponse = restTemplate.getForObject(url, JsonNode.class);
                JsonNode items = pageResponse.get("items");

                for (JsonNode item : items) {
                    String positionUrl = item.get("$ref").asText();
                    positionUrls.add(positionUrl);
                }
            }

            // Cargar detalles de cada posición
            for (String positionUrl : positionUrls) {
                loadPositionDetails(positionUrl);
            }

            System.out.println("Carga de posiciones completada.");
        } else {
            System.out.println("Las posiciones ya están cargadas en la base de datos.");
        }
    }

    public void loadPositionDetails(String positionUrl) {
        // Obtener detalles de la posición
        JsonNode positionNode = restTemplate.getForObject(positionUrl, JsonNode.class);
    
        if (positionNode == null) {
            System.out.println("No se pudo obtener datos de la posición en: " + positionUrl);
            return;
        }
    
        Position position = new Position();
    
        // Asignar valores con comprobaciones nulas
        position.setId(positionNode.has("id") ? positionNode.get("id").asText() : null);
        position.setName(positionNode.has("name") ? positionNode.get("name").asText() : null);
        position.setDisplayName(positionNode.has("displayName") ? positionNode.get("displayName").asText() : null);
        position.setAbbreviation(positionNode.has("abbreviation") ? positionNode.get("abbreviation").asText() : null);
        position.setLeaf(positionNode.has("leaf") ? positionNode.get("leaf").asBoolean() : false);
    
        // Manejar posición padre si existe
        if (positionNode.has("parent")) {
            JsonNode parentNode = positionNode.get("parent");
            if (parentNode.has("$ref")) {
                String parentUrl = parentNode.get("$ref").asText();
                String parentId = parentUrl.substring(parentUrl.lastIndexOf('/') + 1);
    
                // Verificar si el padre ya está en la base de datos
                Optional<Position> parentOpt = positionRepository.findById(parentId);
                Position parentPosition;
                if (parentOpt.isPresent()) {
                    parentPosition = parentOpt.get();
                } else {
                    // Si no está, cargar y guardar el padre
                    loadPositionDetails(parentUrl);
                    parentPosition = positionRepository.findById(parentId).orElse(null);
                }
                position.setParent(parentPosition);
            }
        }
    
        // Guardar la posición en la base de datos
        positionRepository.save(position);
    }
}