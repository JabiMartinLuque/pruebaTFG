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

public class V2__Cargar_Datos_Country extends BaseJavaMigration {
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void migrate(Context context) throws Exception {
        Connection connection = context.getConnection();

        // Comprueba si la tabla 'country' está vacía
        if (isTableEmpty(connection)) {
            int pageCount = 20; // Ajusta según sea necesario
            for (int page = 1; page <= pageCount; page++) {
                String url = "http://sports.core.api.espn.com/v2/sports/soccer/leagues/esp.1/countries?lang=es&region=es&page=" + page;
                ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
                List<Map<String, Object>> items = (List<Map<String, Object>>) response.getBody().get("items");

                if (items == null || items.isEmpty()) {
                    break; // No hay más páginas
                }

                for (Map<String, Object> item : items) {
                    String countryUrl = (String) item.get("$ref");
                    loadCountryDetails(countryUrl, connection);
                }
            }
            System.out.println("Carga de países completada.");
        } else {
            System.out.println("Los países ya están cargados en la base de datos.");
        }
    }

    private boolean isTableEmpty(Connection connection) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM countries";
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
    private void loadCountryDetails(String countryUrl, Connection connection) {
        // Implementa la lógica para cargar los detalles de un país
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(countryUrl, Map.class);

            Map<String, Object> country = response.getBody();

            String id = (String) country.get("id");
            String slug = (String) country.get("slug");
            String name = (String) country.get("name");
            String abbreviation = (String) country.get("abbreviation");
            Map<String, Object> flag = (Map<String, Object>) country.get("flag");
            String flagHref = (String) flag.get("href");

            flagHref = flagHref != null ? flagHref : "";
            
            String insertQuery = "INSERT INTO COUNTRIES (id, slug, name, abbreviation, flag_href) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {
                ps.setString(1, id);
                ps.setString(2, slug);
                ps.setString(3, name);
                ps.setString(4, abbreviation);
                ps.setString(5, flagHref);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los detalles del país: " + e.getMessage());
        }
    }
}
