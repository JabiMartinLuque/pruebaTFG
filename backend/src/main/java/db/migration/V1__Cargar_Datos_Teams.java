// src/main/java/db/migration/V1__Cargar_Datos_Teams.java

package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class V1__Cargar_Datos_Teams extends BaseJavaMigration {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void migrate(Context context) throws Exception {
        Connection connection = context.getConnection();

        // Comprueba si la tabla 'team' está vacía
        if (isTableEmpty(connection)) {
            int pageCount = 100; // Ajusta según sea necesario
            for (int page = 1; page <= pageCount; page++) {
                String url = "http://sports.core.api.espn.com/v2/sports/soccer/teams?lang=es&region=es&page=" + page;
                ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
                List<Map<String, Object>> items = (List<Map<String, Object>>) response.getBody().get("items");

                if (items == null || items.isEmpty()) {
                    break; // No hay más páginas
                }

                for (Map<String, Object> item : items) {
                    String teamUrl = (String) item.get("$ref");
                    loadTeamDetails(teamUrl, connection);
                }
            }
            System.out.println("Carga de equipos completada.");
        } else {
            System.out.println("Los equipos ya están cargados en la base de datos.");
        }
    }

    private boolean isTableEmpty(Connection connection) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM team";
        try (PreparedStatement ps = connection.prepareStatement(checkQuery)) {
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private void loadTeamDetails(String teamUrl, Connection connection) {
        try {
            // Realizar la solicitud GET al endpoint del equipo
            @SuppressWarnings("rawtypes")
            ResponseEntity<Map> response = restTemplate.getForEntity(teamUrl, Map.class);

            // Parsear la respuesta JSON y mapearla al objeto Team
            Map<String, Object> teamData = response.getBody();

            String id = (String) teamData.get("id");
            String guid = (String) teamData.get("guid");
            String location = (String) teamData.get("location");
            String name = (String) teamData.get("name");
            String abbreviation = (String) teamData.get("abbreviation");
            String displayName = (String) teamData.get("displayName");
            String shortDisplayName = (String) teamData.get("shortDisplayName");
            String color = (String) teamData.get("color");
            String alternateColor = (String) teamData.get("alternateColor");
            Boolean isActive = (Boolean) teamData.get("isActive");
            String form = (String) teamData.get("form");
            String logoUrl = null;
            String venueName = null;
            String venueCity = null;
            String venueCountry = null;
            String clubhouseUrl = null;

            // Extraer y mapear el logo
            List<Map<String, Object>> logos = (List<Map<String, Object>>) teamData.get("logos");
            if (logos != null && !logos.isEmpty()) {
                logoUrl = (String) logos.get(0).get("href");
            }

            // Extraer y mapear la información del venue
            Map<String, Object> venueRef = (Map<String, Object>) teamData.get("venue");
            if (venueRef != null) {
                String venueUrl = (String) venueRef.get("$ref");
                if (venueUrl != null) {
                    ResponseEntity<Map> venueResponse = restTemplate.getForEntity(venueUrl, Map.class);
                    Map<String, Object> venue = venueResponse.getBody();
                    if (venue != null) {
                        venueName = (String) venue.get("fullName");
                        Map<String, Object> address = (Map<String, Object>) venue.get("address");
                        if (address != null) {
                            venueCity = (String) address.get("city");
                            venueCountry = (String) address.get("country");
                        }
                    }
                }
            }

            // Extraer enlace de clubhouse
            List<Map<String, Object>> links = (List<Map<String, Object>>) teamData.get("links");
            if (links != null) {
                for (Map<String, Object> link : links) {
                    List<String> rels = (List<String>) link.get("rel");
                    if (rels.contains("clubhouse")) {
                        clubhouseUrl = (String) link.get("href");
                        break;
                    }
                }
            }

            // Construir y ejecutar la sentencia SQL para insertar los datos
            String sql = "INSERT INTO team (id, guid, location, name, abbreviation, display_name, short_display_name, color, alternate_color, is_active, form, logo_url, venue_name, venue_city, venue_country, clubhouse_url) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.setString(2, guid);
                ps.setString(3, location);
                ps.setString(4, name);
                ps.setString(5, abbreviation);
                ps.setString(6, displayName);
                ps.setString(7, shortDisplayName);
                ps.setString(8, color);
                ps.setString(9, alternateColor);
                ps.setBoolean(10, isActive != null ? isActive : false);
                ps.setString(11, form);
                ps.setString(12, logoUrl);
                ps.setString(13, venueName);
                ps.setString(14, venueCity);
                ps.setString(15, venueCountry);
                ps.setString(16, clubhouseUrl);

                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}