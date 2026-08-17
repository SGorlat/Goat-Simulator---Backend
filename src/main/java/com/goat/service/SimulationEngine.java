package com.goat.service;

import com.goat.model.Career;
import com.goat.model.CareerSeason;
import com.goat.model.Competitor;
import com.goat.model.Team;
import com.goat.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SimulationEngine {

    private final CareerRepository careerRepository;
    private final CareerSeasonRepository careerSeasonRepository;
    private final CompetitorRepository competitorRepository;
    private final TeamRepository teamRepository;

    public SimulationEngine(CareerRepository careerRepository, CareerSeasonRepository careerSeasonRepository, CompetitorRepository competitorRepository, TeamRepository teamRepository) {
        this.careerRepository = careerRepository;
        this.careerSeasonRepository = careerSeasonRepository;
        this.competitorRepository = competitorRepository;
        this.teamRepository = teamRepository;

    }


    // Calcular goles de la temporada
    private int calculateGoals(Career career) {

        Random randomNum = new Random();

        double base = ((career.getFinishing() * 0.4) + (career.getSpeed() * 0.2) + (career.getTechnique() * 0.3)) / 10;
        double positionFactor = 1.0; // por defecto

        if (career.getPosition().getName().equals("Delantero")) {
            positionFactor = 1.3;
        } else if (career.getPosition().getName().equals("Extremo")) {
            positionFactor = 1.0;
        } else if (career.getPosition().getName().equals("Mediapunta")) {
            positionFactor = 0.8;
        } else {
            throw new RuntimeException("Error de posición");
        }

        double teamFactor = career.getCurrentTeam().getPrestige() / 70.0; // valor 70 es equipo medio
        int lucky = randomNum.nextInt(100); // de 0 a 99
        double luckyFactor;

        if (lucky < 1) {
            luckyFactor = 1.5;        // 1% — temporada mágica
        } else if (lucky < 4) {
            luckyFactor = 1.2;        // 3% — muy buena
        } else if (lucky < 7) {
            luckyFactor = 0.7;        // 3% — horrible
        } else {
            luckyFactor = 1.0;        // 93% — normal
        }

        int goals = (int) (base * positionFactor * teamFactor * luckyFactor) + randomNum.nextInt(7) - 3;
        if (goals < 0) {
            goals = 0;
        }
        return goals;
    }


    // Calcular asistencias de la temporada
    private int calculateAssists(Career career) {

        Random randomNum = new Random();

        double base = (career.getVision() * 0.4 + career.getTechnique() * 0.3 + career.getSpeed() * 0.1) / 10;
        double positionFactor = 1.0; // por defecto

        if (career.getPosition().getName().equals("Mediapunta")) {
            positionFactor = 1.3;
        } else if (career.getPosition().getName().equals("Extremo")) {
            positionFactor = 1.1;
        } else if (career.getPosition().getName().equals("Delantero")) {
            positionFactor = 0.7;
        } else {
            throw new RuntimeException("Error de posición");
        }

        double teamFactor = career.getCurrentTeam().getPrestige() / 70.0; // valor 70 es equipo medio
        int lucky = randomNum.nextInt(100); // de 0 a 99
        double luckyFactor;

        if (lucky < 1) {
            luckyFactor = 1.5;        // 1% — temporada mágica
        } else if (lucky < 4) {
            luckyFactor = 1.2;        // 3% — muy buena
        } else if (lucky < 7) {
            luckyFactor = 0.7;        // 3% — horrible
        } else {
            luckyFactor = 1.0;        // 93% — normal
        }

        int assists = (int) (base * positionFactor * teamFactor * luckyFactor) + randomNum.nextInt(5) - 2;
        if (assists < 0) {
            assists = 0;
        }
        return assists;

    }

    // Calcular puesto en liga en base al equipo y la contribucion goles/asist
    private int calculateLeaguePosition(Career career, int goals, int assist) {
        Random randomNum = new Random();
        int prestigeTeam = career.getCurrentTeam().getPrestige();

        int randomFactor = randomNum.nextInt(21) - 10;

        double points = prestigeTeam + (goals * 2) + (assist * 1.5) + randomFactor;

        int finalPosition;
        if (points > 110) {
            finalPosition = randomNum.nextInt(2) + 1; // candidato a titulo
        } else if (points > 90) {
            finalPosition = randomNum.nextInt(5) + 3; // champison/ascenso
        } else if (points > 70) {
            finalPosition = randomNum.nextInt(5) + 6;
        } else if (points > 50) {
            finalPosition = randomNum.nextInt(5) + 11;
        } else {
            finalPosition = randomNum.nextInt(5) + 16;
        }

        return finalPosition;

    }

    // Crecimiento de los competidores
    private void evolveCompetitors(Career career) {

        Random randomNum = new Random();

        List<Competitor> competitors = competitorRepository.findByCareerIdAndActiveTrue(career.getId());

        for (Competitor comp : competitors) {
            //calculamos crecimiento segun potencial inicial (base = 2: un jugador normal(1.0 potencial) crece 2 puntos de cada stat por temporada)
            int growth = (int) (2 * comp.getPotential());

            // aumento stats y repu
            comp.setFinishing(comp.getFinishing() + growth);
            comp.setSpeed(comp.getSpeed() + growth);
            comp.setTechnique(comp.getTechnique() + growth);
            comp.setVision(comp.getVision() + growth);
            comp.setPhysical(comp.getPhysical() + growth);
            comp.setMental(comp.getMental() + growth);
            comp.setReputation(comp.getReputation() + growth);

            // si la edad es mayor que 30 solo sube 1 o 2
            if (comp.getAge() > 30) {
                comp.setFinishing(comp.getFinishing() - (randomNum.nextInt(2) + 1));
                comp.setSpeed(comp.getSpeed() - (randomNum.nextInt(2) + 1));
                comp.setTechnique(comp.getTechnique() - (randomNum.nextInt(2) + 1));
                comp.setVision(comp.getVision() - (randomNum.nextInt(2) + 1));
                comp.setPhysical(comp.getPhysical() - (randomNum.nextInt(2) + 1));
                comp.setMental(comp.getMental() - (randomNum.nextInt(2) + 1));
            }

            // Goles/asist que marcan en base a su stats + aleat
            comp.setSeasonGoals((int) (comp.getFinishing() * 0.3 + randomNum.nextInt(5)));
            comp.setSeasonAssists((int) (comp.getVision() * 0.2 + randomNum.nextInt(3)));


            // Subir +1 edad
            comp.setAge(comp.getAge() + 1);


            // Retirada si edad > 40
            if (comp.getAge() > 40) {
                comp.setActive(false);
            } else if (comp.getAge() >= 40 && randomNum.nextInt(100) < 70) {
                comp.setActive(false);              // 70%
            } else if (comp.getAge() >= 39 && randomNum.nextInt(100) < 50) {
                comp.setActive(false);              // 50%
            } else if (comp.getAge() >= 38 && randomNum.nextInt(100) < 30) {
                comp.setActive(false);              // 30%
            } else if (comp.getAge() >= 37 && randomNum.nextInt(100) < 10) {
                comp.setActive(false);              // 10%
            }

            competitorRepository.save(comp);

        }

    }


    private List<String> evaluateAwards(Career career, int goals, int assists) {

        // Creamos lista vacia de premios
        List<String> awards = new ArrayList<>();

        // Listado con competidores
        List<Competitor> competitors = competitorRepository.findByCareerIdAndActiveTrue(career.getId());

        // BOTA DE ORO
        int bestScorerThanMe = 0; // Cuantos max goleadores hay, 0 mejor que yo
        String bestScorer = ""; // nombre max goleador
        int maxGoalsComp = 0;

        for (Competitor comp : competitors) {
            // Buscamos el MAX goleador entre los competidores, se va revisando cada iteracion maxGoals con el comp y si es mayor se saca el nombnre como bestScorer
            if (comp.getSeasonGoals() > maxGoalsComp) {
                maxGoalsComp = comp.getSeasonGoals();
                bestScorer = comp.getName();
            }

            // Comparamos con mis goles
            if (comp.getSeasonGoals() > goals) {
                bestScorerThanMe++; // si 1 competidor me supera en goles sumamos 1

            }
        }

        if (bestScorerThanMe == 0) {
            awards.add("Bota de Oro - ¡Eres el máximo goleador!");
        } else if (bestScorerThanMe == 1) {
            awards.add("Has quedado 2º máximo goleador (ganó '" + bestScorer + "' con " + maxGoalsComp + " goles)");
        } else if (bestScorerThanMe == 2) {
            awards.add("Has quedado 3º máximo goleador (ganó '" + bestScorer + "' con " + maxGoalsComp + " goles)");

        }

        // TROFEO KOPA (<21)


        if (career.getCurrentAge() <= 21) {
            // Media de stats
            int myOverallRating = (career.getFinishing() + career.getSpeed() + career.getTechnique() + career.getVision() + career.getMental() + career.getPhysical()) / 6;

            String bestYoung = "";
            int betterYoungThanMe = 0;

            for (Competitor comp : competitors) {
                if (comp.getAge() <= 21) {
                    int overallRatingComp = (comp.getTechnique() + comp.getFinishing() + comp.getSpeed() + comp.getMental() + comp.getVision() + comp.getPhysical()) / 6;
                    if (overallRatingComp > myOverallRating) {
                        bestYoung = comp.getName();
                        betterYoungThanMe++;
                    }
                }
            }

            if (betterYoungThanMe == 0) {
                awards.add("Trofeo KOPA -  ¡Mejor jugador joven del mundo!");
            } else if (betterYoungThanMe == 1) {
                awards.add("Has quedado 2º en el trofeo KOPA (ganó '" + bestYoung + "')");
            } else if (betterYoungThanMe == 2) {
                awards.add("Has quedado 3º en el trofeo KOPA (ganó '" + bestYoung + "')");

            }

        }

        // BALÓN DE ORO — solo liga nivel 3 + reputation > 60

        if (career.getCurrentTeam() != null && career.getCurrentTeam().getLeague().getLevel() == 3 && career.getReputation() > 60) {

            // puntuacion es la media ovr + goles + asist
            int myScore = ((career.getFinishing() + career.getSpeed() + career.getTechnique()
                    + career.getVision() + career.getMental() + career.getPhysical()) / 6)
                    + goals + career.getReputation();

            int betterThanMe = 0;
            String goldenBallWinner = "";
            int maxScoreComp = 0;

            for (Competitor comp : competitors) {
                int compScore = ((comp.getFinishing() + comp.getSpeed() + comp.getTechnique()
                        + comp.getVision() + comp.getMental() + comp.getPhysical()) / 6)
                        + comp.getSeasonGoals() + comp.getReputation();
                if (compScore > maxScoreComp) {
                    maxScoreComp = compScore;
                    goldenBallWinner = comp.getName();
                }
                if (compScore > myScore) {
                    betterThanMe++;
                }
            }


            if (betterThanMe == 0) {
                awards.add("Ganador del Balón de Oro -  ¡Mejor jugador del mundo!");
            } else if (betterThanMe == 1) {
                awards.add("Has quedado 2º en el Balón de Oro (ganó '" + goldenBallWinner + "')");
            } else if (betterThanMe == 2) {
                awards.add("Has quedado 3º en el Balón de Oro (ganó '" + goldenBallWinner + "')");

            }

        }
        return awards;

    }


    public CareerSeason simulateSeason(Long careerId) {

        // Busco la carrera
        Career career = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // Rendimiento
        int goals = calculateGoals(career); // goles de la temp
        int assists = calculateAssists(career); // asist de la temp
        int leaguePosition = calculateLeaguePosition(career, goals, assists); // posic equipo

        // actualizamos prestigio del equipo segun posic

        Team team = career.getCurrentTeam(); //obtenemos el equipo
        if (team != null) {
            if (leaguePosition <= 2) {
                team.setPrestige(team.getPrestige() + 5);       // candidato al título
            } else if (leaguePosition <= 5) {
                team.setPrestige(team.getPrestige() + 3);       // Champions/ascenso
            } else if (leaguePosition <= 10) {
                team.setPrestige(team.getPrestige() + 1);       // mitad alta
            } else if (leaguePosition <= 15) {
                team.setPrestige(team.getPrestige() - 1);       // mitad baja
            } else if (leaguePosition <= 18) {
                team.setPrestige(team.getPrestige() - 3);       // peligro
            } else {
                team.setPrestige(team.getPrestige() - 5);       // descenso
            }

            // BONUS prestige si mejora tempo anterior

            // Busca TODAS las temporadas anteriores de esta carrera, ordenadas de la primera a la última
            List<CareerSeason> seasons = careerSeasonRepository.findByCareerIdOrderBySeasonNumberAsc(careerId);

            // Miramos si es temp 1 (Esta vacia)
            if (!seasons.isEmpty()) {
                CareerSeason lastSeason = seasons.get(seasons.size() - 1); // Coge la última temp de la lista (ultimo item=tamaño -1) -> Si tengo temp 1, 2 y 3 guardadas, size() = 3, 3 - 1 = 2, seasons.get(2) = la temp 3

               // Si tenemos posic temp anterior(no es null - solo 1ºtemp)
                if (lastSeason.getLeaguePosition() != null) {
                        // Si la posic actual es menor que la de la campaña anterior -> +1 extra al prestigio
                    if (leaguePosition < lastSeason.getLeaguePosition()) {
                        team.setPrestige(team.getPrestige() + 1);

                        // Si la posic actual es mayor que la de la campaña anterior -> -1 extra al prestigio
                    } else if (leaguePosition > lastSeason.getLeaguePosition()) {
                        team.setPrestige(team.getPrestige() - 1);
                    }
                }
            }

            // Limitar entre 10 y 99
            if (team.getPrestige() > 99) team.setPrestige(99);
            if (team.getPrestige() < 10) team.setPrestige(10);


            teamRepository.save(team);

        }

            // Evolucionar rivales
            evolveCompetitors(career);

            // Premios
            List<String> awards = evaluateAwards(career, goals, assists);

            // Envejecemos 1 año
            career.setCurrentAge(career.getCurrentAge() + 1);

            // Actualizamos total goles y asist (sumamos goles y asist de la temporada)
            career.setTotalGoals(career.getTotalGoals() + goals);
            career.setTotalAssists(career.getTotalAssists() + assists);

            // Creamos temporada y le actualizamos datos
            CareerSeason careerSeason = new CareerSeason();

            careerSeason.setCareer(career);
            careerSeason.setSeasonNumber(career.getCurrentAge() - 16); // En que temp estamos
            careerSeason.setTeam(career.getCurrentTeam());
            careerSeason.setAge(career.getCurrentAge());
            careerSeason.setGoals(goals);
            careerSeason.setAssists(assists);
            careerSeason.setLeaguePosition(leaguePosition);

            // Rating de 1 a 10 basado en goles + asistencias + posición del equipo

                //Math.min(10.0, ...) — tope máximo de 10. Si la suma pasa de 10, se queda en 10.
            double rating = Math.min(10.0, (goals * 0.3 + assists * 0.2 + (20 - leaguePosition) * 0.15)); // 20 es loes equipos totales de la liga
            careerSeason.setSeasonRating(Math.round(rating * 10.0) / 10.0); // redondea a 1 decimal

            careerSeason.setTitlesWon(awards.isEmpty() ? null : String.join(", ", awards)); //String.join...convierte la lista de premios en texto separado por coma

            // Restamos un año de contrato al final de cada temporada
            if(career.getContractYearsLeft() > 0){
                career.setContractYearsLeft(career.getContractYearsLeft()-1);
            }

            // Guardamos temporada actual
            careerSeasonRepository.save(careerSeason);

            // Guardamos carrera actualizada con 1 temp +
            careerRepository.save(career);

            return careerSeason;

    }
}

