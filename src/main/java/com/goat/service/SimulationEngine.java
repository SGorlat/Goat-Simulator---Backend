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


        if(career.getCurrentTeam() == null){
            double base = ((career.getFinishing())*0.3+ (career.getSpeed() * 0.2) / 4);
            return (int) base + randomNum.nextInt(3);
        }

        double base = ((career.getFinishing() * 0.4) + (career.getSpeed() * 0.2) + (career.getTechnique() * 0.3)) / 4;
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

        double teamFactor = 0.5 + (career.getCurrentTeam().getPrestige() / 140.0);        int lucky = randomNum.nextInt(100); // de 0 a 99
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

        if (career.getCurrentTeam() == null) {
            double base = (career.getVision() * 0.2 + career.getTechnique() * 0.1) / 6;
            return (int) base + randomNum.nextInt(2);
        }

        double base = (career.getVision() * 0.4 + career.getTechnique() * 0.3 + career.getSpeed() * 0.1) / 4;
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

        double teamFactor = 0.5 + (career.getCurrentTeam().getPrestige() / 140.0); // valor 140 es equipo medio
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

        if (career.getCurrentTeam() == null) {
            return new Random().nextInt(10) + 1;  // posición aleatoria en la cantera
        }

        int prestigeTeam = career.getCurrentTeam().getPrestige();

        int randomFactor = randomNum.nextInt(21) - 10;

        double points = prestigeTeam + (goals * 3) + (assist * 2.5) + randomFactor;

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
                comp.setFinishing(Math.max(1,comp.getFinishing() - (randomNum.nextInt(2) + 1)));
                comp.setSpeed(Math.max(1,comp.getSpeed() - (randomNum.nextInt(2) + 1)));
                comp.setTechnique(Math.max(1,comp.getTechnique() - (randomNum.nextInt(2) + 1)));
                comp.setVision(Math.max(1,comp.getVision() - (randomNum.nextInt(2) + 1)));
                comp.setPhysical(Math.max(1,comp.getPhysical() - (randomNum.nextInt(2) + 1)));
                comp.setMental(Math.max(1,comp.getMental() - (randomNum.nextInt(2) + 1)));
            }

            // Goles/asist que marcan en base a su stats + aleat
            comp.setSeasonGoals((int) (comp.getFinishing() * 0.15 + randomNum.nextInt(5)));
            comp.setSeasonAssists((int) (comp.getVision() * 0.1 + randomNum.nextInt(3)));


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

        List<String> awards = new ArrayList<>();
        List<Competitor> competitors = competitorRepository.findByCareerIdAndActiveTrue(career.getId());

        // BOTA DE ORO
        int bestScorerThanMe = 0;
        String bestScorer = "";
        int maxGoalsComp = 0;

        for (Competitor comp : competitors) {
            if (comp.getSeasonGoals() > maxGoalsComp) {
                maxGoalsComp = comp.getSeasonGoals();
                bestScorer = comp.getName();
            }
            if (comp.getSeasonGoals() > goals) {
                bestScorerThanMe++;
            }
        }

        if (bestScorerThanMe == 0) {
            awards.add("Bota de Oro ¡Eres el máximo goleador!");
        } else if (bestScorerThanMe <= 2) {
            awards.add("Top 3 Bota de Oro (ganó '" + bestScorer + "' con " + maxGoalsComp + " goles)");
        }

        // TROFEO KOPA (<= 21 y mínimo 10 goles+asistencias)
        if (career.getCurrentAge() <= 21 && goals + assists >= 10) {
            int myOvr = (career.getFinishing() + career.getSpeed() + career.getTechnique() + career.getVision() + career.getMental() + career.getPhysical()) / 6;
            String bestYoung = "";
            int betterYoungThanMe = 0;

            for (Competitor comp : competitors) {
                if (comp.getAge() <= 21) {
                    int compOvr = (comp.getTechnique() + comp.getFinishing() + comp.getSpeed() + comp.getMental() + comp.getVision() + comp.getPhysical()) / 6;
                    if (compOvr > myOvr) {
                        bestYoung = comp.getName();
                        betterYoungThanMe++;
                    }
                }
            }

            if (betterYoungThanMe == 0) {
                awards.add("Trofeo KOPA ¡Mejor jugador joven del mundo!");
            } else if (betterYoungThanMe <= 2) {
                awards.add("Top 3 Trofeo KOPA (ganó '" + bestYoung + "')");
            }
        }

        // BALÓN DE ORO — solo liga nivel 3 + reputation > 60
        if (career.getCurrentTeam() != null && career.getCurrentTeam().getLeague().getLevel() == 3 && career.getReputation() > 60) {
            int myScore = ((career.getFinishing() + career.getSpeed() + career.getTechnique() + career.getVision() + career.getMental() + career.getPhysical()) / 6) + goals + career.getReputation();
            int betterThanMe = 0;
            String goldenBallWinner = "";
            int maxScoreComp = 0;

            for (Competitor comp : competitors) {
                int compScore = ((comp.getFinishing() + comp.getSpeed() + comp.getTechnique() + comp.getVision() + comp.getMental() + comp.getPhysical()) / 6) + comp.getSeasonGoals() + comp.getReputation();
                if (compScore > maxScoreComp) {
                    maxScoreComp = compScore;
                    goldenBallWinner = comp.getName();
                }
                if (compScore > myScore) {
                    betterThanMe++;
                }
            }

            if (betterThanMe == 0) {
                awards.add("Balón de Oro ¡Mejor jugador del mundo!");
            } else if (betterThanMe <= 2) {
                awards.add("Top 3 Balón de Oro (ganó '" + goldenBallWinner + "')");
            }
        }

        return awards;
    }

    public CareerSeason simulateSeason(Long careerId) {

        Career career = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        int goals = calculateGoals(career);
        int assists = calculateAssists(career);
        int leaguePosition = calculateLeaguePosition(career, goals, assists);

        // Cargamos temporadas anteriores
        List<CareerSeason> seasons = careerSeasonRepository.findByCareerIdOrderBySeasonNumberAsc(careerId);

        // Si te quedaste en el equipo y rendiste bien, el equipo mejora
        if (!seasons.isEmpty()) {
            CareerSeason lastSeason = seasons.get(seasons.size() - 1);
            if (lastSeason.getLeaguePosition() != null
                    && lastSeason.getTeam() != null
                    && career.getCurrentTeam() != null
                    && lastSeason.getTeam().getId().equals(career.getCurrentTeam().getId())) {
                if (goals >= 10) {
                    leaguePosition = Math.max(1, leaguePosition - 3);
                } else if (goals >= 5) {
                    leaguePosition = Math.max(1, leaguePosition - 1);
                }
            }
        }

        //  actualizamos prestigio con la posición ya ajustada
        Team team = career.getCurrentTeam();
        if (team != null) {
            if (leaguePosition <= 2) {
                team.setPrestige(team.getPrestige() + 5);
            } else if (leaguePosition <= 5) {
                team.setPrestige(team.getPrestige() + 3);
            } else if (leaguePosition <= 10) {
                team.setPrestige(team.getPrestige() + 1);
            } else if (leaguePosition <= 15) {
                team.setPrestige(team.getPrestige() - 1);
            } else if (leaguePosition <= 18) {
                team.setPrestige(team.getPrestige() - 3);
            } else {
                team.setPrestige(team.getPrestige() - 5);
            }

            // Bonus prestigio si mejora respecto a temporada anterior
            if (!seasons.isEmpty()) {
                CareerSeason lastSeason = seasons.get(seasons.size() - 1);
                if (lastSeason.getLeaguePosition() != null) {
                    if (leaguePosition < lastSeason.getLeaguePosition()) {
                        team.setPrestige(team.getPrestige() + 1);
                    } else if (leaguePosition > lastSeason.getLeaguePosition()) {
                        team.setPrestige(team.getPrestige() - 1);
                    }
                }
            }

            if (team.getPrestige() > 99) team.setPrestige(99);
            if (team.getPrestige() < 10) team.setPrestige(10);
            teamRepository.save(team);
        }

            // Evolucionar rivales
            evolveCompetitors(career);

            // Premios
            List<String> awards = evaluateAwards(career, goals, assists);

            if(leaguePosition == 1){
                awards.add("¡Campeón de " + career.getCurrentTeam().getLeague().getName() + "!");
            }

            if(career.getCupResult() != null){
                awards.add(career.getCupResult());
            }

            // Crecimiento automático del jugador según su growthMultiplier
                    int playerGrowth = (int)(3 * career.getGrowthMultiplier());
                    career.setSpeed(career.getSpeed() + playerGrowth);
                    career.setTechnique(career.getTechnique() + playerGrowth);
                    career.setFinishing(career.getFinishing() + playerGrowth);
                    career.setPhysical(career.getPhysical() + playerGrowth);
                    career.setMental(career.getMental() + playerGrowth);
                    career.setVision(career.getVision() + playerGrowth);

            // Degradación de stats si mayor de 30 (igual que competidores)
                    if (career.getCurrentAge() > 30) {
                        Random random = new Random();
                        career.setSpeed(Math.max(1,career.getSpeed() - (random.nextInt(2) + 1)));
                        career.setTechnique(Math.max(1,career.getTechnique() - (random.nextInt(2) + 1)));
                        career.setFinishing(Math.max(1,career.getFinishing() - (random.nextInt(2) + 1)));
                        career.setPhysical(Math.max(1,career.getPhysical() - (random.nextInt(2) + 1)));
                        career.setMental(Math.max(1,career.getMental() - (random.nextInt(2) + 1)));
                        career.setVision(Math.max(1,career.getVision() - (random.nextInt(2) + 1)));
                    }

                if(career.getHasPhysio()){
                    career.setPhysical(career.getPhysical() + 2);
                }

                if(career.getHasNutritionist()){
                    career.setPhysical(career.getPhysical() + 1);
                    career.setSpeed(career.getSpeed() + 1);
                }

                if(career.getHasPsychology()){
                    career.setMental(career.getMental() + 2);
                    career.setVision(career.getVision() + 2);
                }

                if(career.getHasTrainer()){
                    career.setTechnique(career.getTechnique() + 2);
                    career.setFinishing(career.getFinishing() + 1);
                }
                if(career.getHasBootsPremium()){
                    career.setSpeed(career.getSpeed() + 2);
                    career.setFinishing(career.getFinishing() + 1);
                }
                if(career.getHasImageAgent()){
                    career.setReputation(career.getReputation() + 3);
                    career.setPressRelation(career.getPressRelation() + 2);
                }
                if(career.getHasVideoAnalyst()){
                    career.setVision(career.getVision() + 3);
                }
                if(career.getHasPrivateChef()){
                    career.setPhysical(career.getPhysical() + 1);
                    career.setSpeed(career.getSpeed() + 1);
                    career.setMental(career.getMental() + 1);
                }

          // Tras boost reseteamnos items de la tienda para la siguiente temporada
                career.setHasPhysio(false);
                career.setHasNutritionist(false);
                career.setHasPsychology(false);
                career.setHasTrainer(false);
                career.setHasBootsPremium(false);
                career.setHasImageAgent(false);
                career.setHasVideoAnalyst(false);
                career.setHasPrivateChef(false);

            // reseteamos los pasos de pretemporada a 0(0 = vacaciones)
            career.setPreseasonStep(0);
            career.clearFlags(); // reseteamos las flags, marcas de intereses de equipos ocurridas duirante la temporada
            career.setCupRound(0); // reseteamos ronda copa para siguiente temp
            career.setCupEliminated(false); // reseteamos eliminacion
            career.setCupResult(null); // reseteamos texto de resultado a null

            // Tope de stats a 99 si supera ese rango
            career.setSpeed(Math.min(99, career.getSpeed()));
            career.setTechnique(Math.min(99, career.getTechnique()));
            career.setFinishing(Math.min(99, career.getFinishing()));
            career.setPhysical(Math.min(99, career.getPhysical()));
            career.setMental(Math.min(99, career.getMental()));
            career.setVision(Math.min(99, career.getVision()));
            career.setReputation(Math.min(100, career.getReputation()));

            // Tope de relaciones a 100
            career.setFansRelation(Math.min(100, career.getFansRelation()));
            career.setBoardRelation(Math.min(100, career.getBoardRelation()));
            career.setTeamRelation(Math.min(100, career.getTeamRelation()));
            career.setCoachRelation(Math.min(100, career.getCoachRelation()));
            career.setPressRelation(Math.min(100, career.getPressRelation()));
            career.setAgentRelation(Math.min(100, career.getAgentRelation()));

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
        double teamLevelBonus = 0;
        int leagueLevel = career.getCurrentTeam().getLeague().getLevel();
        if (leagueLevel == 1 && goals >= 10) teamLevelBonus = 3.0;
        else if (leagueLevel == 1 && goals >= 5) teamLevelBonus = 1.5;
        else if (leagueLevel == 2 && goals >= 15) teamLevelBonus = 2.0;
        else if (leagueLevel == 2 && goals >= 8) teamLevelBonus = 1.0;

        double rating = Math.min(10.0, (goals * 0.3 + assists * 0.2 + (20 - leaguePosition) * 0.15 + teamLevelBonus)); // 20 es loes equipos totales de la liga
            careerSeason.setSeasonRating(Math.round(rating * 10.0) / 10.0); // redondea a 1 decimal

            careerSeason.setTitlesWon(awards.isEmpty() ? null : String.join(", ", awards)); //String.join...convierte la lista de premios en texto separado por coma

            // Restamos un año de contrato al final de cada temporada
            if(career.getContractYearsLeft() > 0){
                career.setContractYearsLeft(career.getContractYearsLeft()-1);
            }

        // DINERO FINAL

        int salary = career.getSalary(); // sacamos el salario

        // Quitamos impuestos
        int taxes = (int)(salary*0.35);

        // Coste alquiler vivienda segun nivel de liga

        int rent;
        switch (career.getCurrentTeam().getLeague().getLevel()){
            case 1: rent =  30000;
                break;
            case 2: rent =  60000;
                break;
            case 3: rent =  120000;
                break;
            default: rent = 30000;
        }

        // Estilo de vida segun reputación
        int lifeStyle;
        if (career.getReputation() < 35) {
            lifeStyle = (int)(salary * 0.10);
        } else if (career.getReputation() < 70) {
            lifeStyle = (int)(salary * 0.12);
        } else {
            lifeStyle = (int)(salary * 0.15);
        }

        // Coste agente
        int agent = (int)(salary * 0.08);

        // TOTAL
        int total = taxes + rent + lifeStyle + agent;
        int salaryFinal = salary - total;


        career.setSavings(career.getSavings() + salaryFinal);

        careerSeason.setSalaryGross(salary);
        careerSeason.setTaxesPaid(taxes);
        careerSeason.setRentPaid(rent);
        careerSeason.setLifestylePaid(lifeStyle);
        careerSeason.setAgentPaid(agent);
        careerSeason.setSalaryNet(salaryFinal);


        // Guardamos temporada actual
            careerSeasonRepository.save(careerSeason);


            // Guardamos carrera actualizada con 1 temp +
            careerRepository.save(career);

            return careerSeason;

    }
}

