package com.goat.service;

import com.goat.model.Event;
import com.goat.model.EventOption;
import com.goat.model.League;
import com.goat.model.Team;
import com.goat.repository.EventOptionRepository;
import com.goat.repository.EventRepository;
import com.goat.repository.LeagueRepository;
import com.goat.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class FootballDataService {

    // @Value para leer de application.properties
    @Value("${football.api.key}")
    private String apiKey;

    @Value("${football.api.url}")
    private String apiUrl;

    // Creamos atrib RestTemlate, es un objeto que hace llamadas HTTP
    private final RestTemplate restTemplate = new RestTemplate();
    // Indicamos los repository que usaremos(si vamos a trabajr con sus tablas)
    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;
    private final EventRepository eventRepository;
    private final EventOptionRepository eventOptionRepository;


    // Creamos el constructor con los repositorios indicados
    public FootballDataService(LeagueRepository leagueRepository, TeamRepository teamRepository, EventRepository eventRepository, EventOptionRepository eventOptionRepository) {
        this.leagueRepository = leagueRepository;
        this.teamRepository = teamRepository;
        this.eventRepository = eventRepository;
        this.eventOptionRepository = eventOptionRepository;
    }


    // Metodo para traer info de la API (los equipos) - Test Controller
    public String fetchTeamsByCompetition(String competitionCode) {

        // 1. Montamos la URL
        String url = apiUrl + "/competitions/" + competitionCode + "/teams";

        // 2. Creamos los headers HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", apiKey);

        // 3. Empaqueta/almacena los header como objeto que RestTemplate pueda usar (contiene la peticion)
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 4. Hace la llamada (GET en este caso)
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, String.class
        );

        return response.getBody();
    }

    // Trae las ligas y los equipos y los guarda en la BBDD
        /*  - competitionCode es como viene nombrada la liga en API
            - level es la nivelacion de las ligas que vamos a aplicar
            - continent a que continente pertenece la liga (dato propio)
         */

    public void importTeamsFromApi(String competitionCode, int level, String continent) {

        // 1. Montar URL y headers
        String url = apiUrl + "/competitions/" + competitionCode + "/teams";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 2. Llamar a la API — esta vez devuelve un Map (objeto clave-valor), no un String
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        Map<String, Object> body = response.getBody();

        // 3. Extraer info de la competición (liga)
            /* body.get("competition") → saca el valor de la clave "competition"
            y con Map<String, Object> las claves son String ("competition", "teams")
            y los valores son Object, Map<String, Object>) y (String) son casts
            */
        Map<String, Object> competition = (Map<String, Object>) body.get("competition");
        String leagueName = (String) competition.get("name");

        // 4. Crear y guardar la liga
        League league = new League();
        league.setName(leagueName);
        league.setLevel(level);
        league.setContinent(continent);
        League savedLeague = leagueRepository.save(league);

        // 5. Extraer y guardar equipos
            /*
            un array de JSON es un List, y cada objeto dentro es un Map.
            Por eso el tipo es List<Map<String, Object>> --> Lista(Array) de mapas(Objetos)
            */
        List<Map<String, Object>> teams = (List<Map<String, Object>>) body.get("teams");
        for (Map<String, Object> teamData : teams) {
            Team team = new Team();
            team.setName((String) teamData.get("shortName"));
            team.setCrestUrl((String) teamData.get("crest"));
            team.setLeague(savedLeague);
            team.setPrestige(50); // default - ajustar
            teamRepository.save(team);
        }
    }

    // Crear liga + equipos restantes (para ligas que la API no cubre)
    // String[][] teamsData es un array de arrays: cada elemento es {"name", "prestige"}
    public void createLeagueManual(String name, String country, int level, String continent, String[][] teamsData) {
        League league = new League();
        league.setName(name);
        league.setCountry(country);
        league.setLevel(level);
        league.setContinent(continent);
        League saved = leagueRepository.save(league);

        for (String[] t : teamsData) {
            Team team = new Team();
            team.setName(t[0]);                          // nombre del equipo
            team.setPrestige(Integer.parseInt(t[1]));     // prestige como entero
            team.setLeague(saved);
            teamRepository.save(team);
        }
    }

    //  HELPERS DE EVENTOS (Para evitar repetir codigo en eventos) -> reciben como argumentos todos los datos que necesitan, y dentro crean el objeto y lo guardan

        // Helper para la creacion de eventos
    public Event createEvent(String title, String description, String category,
                             String type, String rarity, String phase,
                             int minAge, int maxAge, int minLeagueLevel, int maxLeagueLevel, Long unlocksEventId){

        Event event = new Event();

        event.setTitle(title);
        event.setDescription(description);
        event.setCategory(category);
        event.setRarity(rarity);
        event.setPhase(phase);
        event.setMinAge(minAge);
        event.setMaxAge(maxAge);
        event.setMinLeagueLevel(minLeagueLevel);
        event.setMaxLeagueLevel(maxLeagueLevel);
        event.setUnlocksEventId(unlocksEventId);
        event.setType(type);

        // Guarda y devuelve el evento creado con su id vpara luego asociarlo a opciones
        return eventRepository.save(event);

    }

        // Helper para la creacion de opciones en las decisiones
    public void createOption(Event event, String text, String outcomeText,
                             int speedMod, int techniqueMod, int finishingMod,
                             int physicalMod, int mentalMod, int visionMod,
                             int reputationMod, int savingsMod,
                             int coachRelMod, int teamRelMod, int fansRelMod,
                             int pressRelMod, int agentRelMod, int boardRelMod){

        EventOption eventOption = new EventOption();

        eventOption.setEvent(event);
        eventOption.setText(text);
        eventOption.setOutcomeText(outcomeText);
        eventOption.setSpeedMod(speedMod);
        eventOption.setTechniqueMod(techniqueMod);
        eventOption.setFinishingMod(finishingMod);
        eventOption.setMentalMod(mentalMod);
        eventOption.setPhysicalMod(physicalMod);
        eventOption.setVisionMod(visionMod);
        eventOption.setReputationMod(reputationMod);
        eventOption.setSavingsMod(savingsMod);
        eventOption.setCoachRelationMod(coachRelMod);
        eventOption.setTeamRelationMod(teamRelMod);
        eventOption.setFansRelationMod(fansRelMod);
        eventOption.setPressRelationMod(pressRelMod);
        eventOption.setAgentRelationMod(agentRelMod);
        eventOption.setBoardRelationMod(boardRelMod);


        eventOptionRepository.save(eventOption);



    }


}
