package com.goat.service;

import com.goat.model.Career;
import com.goat.model.Competitor;
import com.goat.model.Position;
import com.goat.model.Team;
import com.goat.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class CareerService {

    private final CareerRepository careerRepository; // crear/guardar career
    private final PositionRepository positionRepository; // buscar datos posicion creada
    private final CompetitorRepository competitorRepository; // crear/guardar competidores
    private final TeamRepository teamRepository; // asignar equipos
    private final LeagueRepository leagueRepository; // asignar ligas

    public CareerService(CareerRepository careerRepository, PositionRepository positionRepository, CompetitorRepository competitorRepository, TeamRepository teamRepository, LeagueRepository leagueRepository){
        this.careerRepository = careerRepository;
        this.positionRepository = positionRepository;
        this.competitorRepository = competitorRepository;
        this.teamRepository = teamRepository;
        this.leagueRepository = leagueRepository;

    }

    public Career createCareer(String playerName, Long positionId, String nationality) {


        // findById devuelve un Optional (puede existir o no). .orElseThrow() dice "si existe dame el objeto, si no lanza error"
        Position position = positionRepository.findById(positionId).orElseThrow(() -> new RuntimeException("Posición no encontrada"));


        // Creamos objeto career (nueva partida)
        Career career = new Career();

        // Asignar datos dados por el jugador que inicia la partida
        career.setPlayerName(playerName);
        career.setNationality(nationality);
        career.setPosition(position);

        // Asignarle stats inciales segun la posicion seleccionada (Establecemos la stats accediendo a la posicion y obteniendo el stat de esa posic)

        career.setFinishing(position.getInitialFinishing());
        career.setSpeed(position.getInitialSpeed());
        career.setTechnique(position.getInitialTechnique());
        career.setMental(position.getInitialMental());
        career.setPhysical(position.getInitialPhysical());
        career.setVision(position.getInitialVision());

        // Fecha creación partida
        career.setCreatedAt(LocalDateTime.now());

        // Rol prodigio

        int prodigyRole = new Random().nextInt(100);

        if(prodigyRole < 3){
            career.setGrowthMultiplier(1.5);
        }else if(prodigyRole < 12){
            career.setGrowthMultiplier(1.3);
        }else if(prodigyRole < 25){
            career.setGrowthMultiplier(1.1);
        }

        // Guarda la partida creada
        Career savedCareer = careerRepository.save(career);

        // Llamamos al metodo para generar los competidos una vez tenemos nuestro PJ/Partida
        generateCompetitors(savedCareer);


        // Devuelve la career/partida
        return savedCareer;
    }


    // Hacemos el metodo privador porque solo puede llamarse dentro de esta clase cuando se cree career nueva
    private void generateCompetitors(Career career){


        // La clase ranmdom genera nº aleat
        Random randomNum = new Random();

       // Almacenas todos los valores(posiciones) que hay en positionRepository en la variable 'positions'
       // Por debajo ejecuta: SELECT * FROM positions y devuelve: [Delantero, Mediapunta, Extremo]
        List<Position> positions = positionRepository.findAll();

        // Buscamos equipos de level 1 y 2 igual que position

        List<Team> teamsLevel1 = teamRepository.findByLeagueLevel(1); // metodo de TeamRepository que hace: SELECT * FROM teams t JOIN leagues l ON t.league_id = l.id WHERE l.level = ?
        List<Team> teamsLevel2 = teamRepository.findByLeagueLevel(2);
        List<Team> teamsLevel3 = teamRepository.findByLeagueLevel(3);

        // Para veteranos, combinaremos nivel 2 y 3 en una sola lista:
        List<Team> teamsLevel2And3 = new java.util.ArrayList<>(teamsLevel2); // new ArrayList<>(teamsLevel2) crea una copia de la lista de nivel 2.

        teamsLevel2And3.addAll(teamsLevel3); // addAll(teamsLevel3) le añade todos los equipos de nivel 3

        // NACIONALIDADES INICALES
        String[] nationalities = {"Argentina", "Brasil", "España", "Francia", "Inglaterra", "Alemania", "Portugal", "Italia"};

        // NOMBRES COMPETIDORES
        String[] names = {
                // Argentinos / Sudamericanos
                "Lautaro Giménez", "Matías Rodríguez", "Facundo Álvarez", "Thiago Benítez",
                "Julián Sosa", "Valentín Aguirre", "Nicolás Ferreyra", "Santiago Molina",
                "Máximo Ledesma", "Tobías Cabrera", "Bruno Ojeda", "Ignacio Vega",
                // Brasileños
                "Gabriel Silva", "Rafael Santos", "Lucas Oliveira", "Matheus Costa",
                "Vinícius Souza", "Pedro Almeida", "João Ribeiro", "Bruno Ferreira",
                "Caio Barbosa", "Diego Nascimento", "Rodrigo Lima", "Felipe Rocha",
                // Españoles
                "Álvaro Martín", "Sergio Navarro", "Hugo Castro", "Marc Vidal",
                "Diego Romero", "Pablo Serrano", "Iker Fuentes", "Adrián Gallardo",
                "Nico Herrera", "Mateo Rubio", "Gonzalo Prieto", "Óscar Peña",
                // Franceses
                "Théo Lambert", "Enzo Moreau", "Lucas Girard", "Nathan Fontaine",
                "Hugo Mercier", "Jules Rousseau", "Léo Bonnet", "Noah Perrin",
                // Ingleses
                "Harry Sullivan", "Jack Thornton", "Oliver Reed", "Charlie Hunt",
                "George Baxter", "Alfie Marsh", "Ethan Cole", "Mason Wright",
                // Alemanes / Centroeuropeos
                "Leon Wagner", "Finn Müller", "Luca Schmidt", "Noah Fischer",
                "Ben Hoffmann", "Paul Weber", "Jonas Becker", "Tim Richter",
                // Portugueses / Italianos
                "Rúben Dias", "Diogo Fernandes", "Tomás Carvalho", "André Sousa",
                "Marco Rossi", "Lorenzo Conti", "Matteo Greco", "Alessandro Riva",
                // Africanos / otros
                "Amadou Diallo", "Ibrahim Touré", "Youssef El Amrani", "Kwame Mensah",
                "Sadio Camara", "Omar Sylla", "Musa Bakayoko", "Cheikh Ndiaye"
        };

        // CREAR 1ª TANDA DE JÓVENES: 16 A 19 AÑOS (3)

        for (int i=0;i<3;i++){

        // randomNum.nextInt(CUÁNTOS) + DESDE_DÓNDE --> va desde 0 hasta el numero indicado --> nextInt(4) -> Seria 0, 1, 2, o 3

        int age = randomNum.nextInt(4) + 16; //  dame 4 valores empezando desde el 16

        // Generar una posicion aleatoria para el competidor
            // positions.size() --> 3 (hay 3 posiciones: Delantero, Mediapunta, Extremo)
            // random.nextInt(3)--> 0, 1, o 2 (un índice aleatorio)
            // positions.get(0) --> Delantero, get(1) --> Mediapunta ...
            // positions.get(random.nextInt(3)) --> una posición aleatoria de las 3

        Position randomPosition = positions.get(randomNum.nextInt(positions.size()));


            Competitor comp = new Competitor();

            comp.setName(names[randomNum.nextInt(names.length)]);
            comp.setNationality(nationalities[randomNum.nextInt(nationalities.length)]);
            comp.setAge(age);
            comp.setPosition(randomPosition);
            comp.setFinishing(randomNum.nextInt(16)+35); // stat entre 35-50 para jóvenes
            comp.setSpeed(randomNum.nextInt(16)+35);
            comp.setTechnique(randomNum.nextInt(16)+35);
            comp.setMental(randomNum.nextInt(16)+35);
            comp.setPhysical(randomNum.nextInt(16)+35);
            comp.setVision(randomNum.nextInt(16)+35);
            comp.setPotential((randomNum.nextInt(6)+10)/10.0); // Potencial de crecimiento de jovenes sera entre 1.0 - 1.5 /se divide entre 10.0 para ob tener decimal)
            comp.setCurrentTeam(teamsLevel1.get(randomNum.nextInt(teamsLevel1.size())));
            comp.setCareer(career);
            comp.setActive(true);
            comp.setReputation(randomNum.nextInt(11)+5); // Jóvenes reputation baja (5-15)

            competitorRepository.save(comp);



        }

        // CREAR 2ª TANDA DE CONTRASTADOS 20 A 24 AÑOS (4 edad media)

        for(int i = 0;i<4;i++){

            // EDAD
            int age = randomNum.nextInt(5)+20;


            // POSICIÓN
            Position randomPosition = positions.get(randomNum.nextInt(positions.size()));



            // RESTO DATOS

            // Creamos objeto competidor y le establecemops sus valores
            Competitor comp = new Competitor();

            comp.setName(names[randomNum.nextInt(names.length)]);
            comp.setNationality(nationalities[randomNum.nextInt(nationalities.length)]);
            comp.setAge(age);
            comp.setPosition(randomPosition);
            comp.setFinishing(randomNum.nextInt(15)+58); // stat entre 58-72 para medios
            comp.setSpeed(randomNum.nextInt(15)+58);
            comp.setTechnique(randomNum.nextInt(15)+58);
            comp.setMental(randomNum.nextInt(15)+58);
            comp.setPhysical(randomNum.nextInt(15)+58);
            comp.setVision(randomNum.nextInt(15)+58);
            comp.setPotential((randomNum.nextInt(5)+8)/10.0);  // Potencial de crecimiento de medios sera entre 0.8 - 1.2
            comp.setCurrentTeam(teamsLevel2.get(randomNum.nextInt(teamsLevel2.size())));
            comp.setCareer(career);
            comp.setActive(true);
            comp.setReputation(randomNum.nextInt(21)+25); // Contrastados reputation media (25-45)

            competitorRepository.save(comp);
        }

        // CREAR 2ª TANDA DE VETERANOS 25 A 28 AÑOS (3)

        for(int i = 0; i<3; i++){

            // EDAD
            int age = randomNum.nextInt(4)+25;

            // POSICIÓN
            Position randomPosition = positions.get(randomNum.nextInt(positions.size()));

            // RESTO DATOS

            Competitor comp = new Competitor();

            comp.setName(names[randomNum.nextInt(names.length)]);
            comp.setNationality(nationalities[randomNum.nextInt(nationalities.length)]);
            comp.setAge(age);
            comp.setPosition(randomPosition);
            comp.setFinishing(randomNum.nextInt(11)+78); // stat entre 78-88 para veteranos
            comp.setSpeed(randomNum.nextInt(11)+78);
            comp.setTechnique(randomNum.nextInt(11)+78);
            comp.setMental(randomNum.nextInt(11)+78);
            comp.setPhysical(randomNum.nextInt(11)+78);
            comp.setVision(randomNum.nextInt(11)+78);
            comp.setPotential((randomNum.nextInt(4)+5)/10.0); //  Potencial de crecimiento de veteranos sera entre 0.5 - 0.8
            comp.setCurrentTeam(teamsLevel2And3.get(randomNum.nextInt(teamsLevel2And3.size())));
            comp.setCareer(career);
            comp.setActive(true);
            comp.setReputation(randomNum.nextInt(26)+50); // Veteranos reputation alta (50-75)

            competitorRepository.save(comp);
        }




    }





}
