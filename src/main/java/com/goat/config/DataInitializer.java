package com.goat.config;

import com.goat.model.Event;
import com.goat.model.Position;
import com.goat.repository.EventRepository;
import com.goat.repository.LeagueRepository;
import com.goat.repository.PositionRepository;
import com.goat.service.FootballDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final PositionRepository positionRepository;
    private final FootballDataService footballDataService;
    private final LeagueRepository leagueRepository;
    private final EventRepository eventRepository;

    // Aquí inyectas los repositories que necesites

    public DataInitializer(PositionRepository positionRepository, FootballDataService footballDataService,LeagueRepository leagueRepository, EventRepository eventRepository){
        this.positionRepository = positionRepository;
        this.footballDataService = footballDataService;
        this.leagueRepository = leagueRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... args) throws Exception{


        // POSICIONES (Si no hay crealas)

        if(positionRepository.count() == 0){



        // Creamos los pesos y stats iniciales al arrancar de esa posicion

        // DELANTERO
        Position delantero = new Position();
        delantero.setName("Delantero");
        delantero.setFinishingWeight(0.3);
        delantero.setSpeedWeight(0.2);
        delantero.setTechniqueWeight(0.15);
        delantero.setPhysicalWeight(0.15);
        delantero.setMentalWeight(0.1);
        delantero.setVisionWeight(0.1);
        delantero.setInitialFinishing(40);
        delantero.setInitialSpeed(35);
        delantero.setInitialTechnique(25);
        delantero.setInitialPhysical(30);
        delantero.setInitialMental(25);
        delantero.setInitialVision(20);

        positionRepository.save(delantero);

        // MEDIAPUNTA
        Position mediapunta = new Position();
        mediapunta.setName("Mediapunta");
        mediapunta.setFinishingWeight(0.15);
        mediapunta.setSpeedWeight(0.1);
        mediapunta.setTechniqueWeight(0.25);
        mediapunta.setPhysicalWeight(0.1);
        mediapunta.setMentalWeight(0.15);
        mediapunta.setVisionWeight(0.25);
        mediapunta.setInitialFinishing(25);
        mediapunta.setInitialSpeed(25);
        mediapunta.setInitialTechnique(40);
        mediapunta.setInitialPhysical(20);
        mediapunta.setInitialMental(30);
        mediapunta.setInitialVision(35);

        positionRepository.save(mediapunta);

        // EXTREMO
        Position extremo = new Position();
        extremo.setName("Extremo");
        extremo.setFinishingWeight(0.2);
        extremo.setSpeedWeight(0.3);
        extremo.setTechniqueWeight(0.2);
        extremo.setPhysicalWeight(0.1);
        extremo.setMentalWeight(0.1);
        extremo.setVisionWeight(0.1);
        extremo.setInitialFinishing(30);
        extremo.setInitialSpeed(40);
        extremo.setInitialTechnique(35);
        extremo.setInitialPhysical(25);
        extremo.setInitialMental(20);
        extremo.setInitialVision(25);

        positionRepository.save(extremo);

        }


        // LIGAS API (Si no existen crearlas)

        if(leagueRepository.count() == 0){


// Nivel 3 — élite
        footballDataService.importTeamsFromApi("PL", 3, "Europa"); // Premier League
        Thread.sleep(6000); // espera 6 segundos antes de la siguiente ejecucion
        footballDataService.importTeamsFromApi("PD", 3, "Europa"); // La Liga
        Thread.sleep(6000);
        footballDataService.importTeamsFromApi("SA", 3, "Europa"); // Serie A
        Thread.sleep(6000);
        footballDataService.importTeamsFromApi("BL1", 3, "Europa"); // Bundesliga
        Thread.sleep(6000);
        footballDataService.importTeamsFromApi("FL1", 3, "Europa"); // Ligue 1
        Thread.sleep(6000);

// Nivel 2 — segundas + medias
        footballDataService.importTeamsFromApi("ELC", 2, "Europa"); // Championship
        Thread.sleep(6000);
        footballDataService.importTeamsFromApi("DED", 2, "Europa"); // Eredivisie
        Thread.sleep(6000);
        footballDataService.importTeamsFromApi("PPL", 2, "Europa"); // Liga Portugal
        Thread.sleep(6000);
        footballDataService.importTeamsFromApi("BSA", 2, "Sudamérica");// Brasileirão

        // NATIONAL LEAGUE (Inglaterra) — 24 equipos
        footballDataService.createLeagueManual("National League", "Inglaterra", 1, "Europa", new String[][] {
                {"Aldershot Town", "22"}, {"Altrincham", "20"}, {"Boreham Wood", "19"},
                {"Boston United", "18"}, {"Brackley Town", "17"}, {"Braintree Town", "16"},
                {"Carlisle United", "28"}, {"Eastleigh", "20"}, {"FC Halifax Town", "21"},
                {"Forest Green Rovers", "23"}, {"Gateshead", "19"}, {"Hartlepool United", "24"},
                {"Morecambe", "25"}, {"Rochdale", "27"}, {"Scunthorpe United", "24"},
                {"Solihull Moors", "18"}, {"Southend United", "25"}, {"Sutton United", "20"},
                {"Tamworth", "19"}, {"Truro City", "16"}, {"Wealdstone", "17"},
                {"Woking", "21"}, {"Yeovil Town", "22"}, {"York City", "26"}
        });

        // PRIMERA RFEF (España) — 40 equipos (2 grupos de 20)
        footballDataService.createLeagueManual("Primera RFEF", "España", 1, "Europa", new String[][] {
                // Grupo 1
                {"AD Mérida", "22"}, {"Arenas Club", "18"}, {"Athletic Club B", "25"},
                {"Barakaldo", "19"}, {"Osasuna B", "23"}, {"Arenteiro", "16"},
                {"CD Guadalajara", "17"}, {"CD Lugo", "28"}, {"CD Tenerife", "32"},
                {"CF Talavera", "18"}, {"CP Cacereño", "17"}, {"Ourense CF", "19"},
                {"Pontevedra CF", "26"}, {"Racing de Ferrol", "27"}, {"Celta Fortuna", "22"},
                {"Real Avilés", "18"}, {"Real Madrid Castilla", "30"}, {"SD Ponferradina", "27"},
                {"Unionistas de Salamanca", "21"}, {"Zamora CF", "20"},
                // Grupo 2
                {"AD Alcorcón", "28"}, {"Algeciras CF", "22"}, {"Atlético Madrileño", "25"},
                {"Betis Deportivo", "23"}, {"FC Cartagena", "30"}, {"CD Eldense", "24"},
                {"Gimnàstic de Tarragona", "26"}, {"Hércules CF", "28"}, {"UD Ibiza", "22"},
                {"Marbella FC", "20"}, {"Real Murcia", "30"}, {"CE Sabadell", "25"},
                {"Sevilla Atlético", "24"}, {"Villarreal B", "26"}, {"Antequera CF", "19"},
                {"Atlético Sanluqueño", "18"}, {"Juventud Torremolinos", "17"},
                {"CD Teruel", "18"}
        });

        // SERIE C (Italia) — 20 equipos principales
        footballDataService.createLeagueManual("Serie C", "Italia", 1, "Europa", new String[][] {
                {"Padova", "28"}, {"Vicenza", "27"}, {"Triestina", "22"},
                {"Pro Vercelli", "20"}, {"Reggiana", "25"}, {"Pescara", "30"},
                {"Foggia", "23"}, {"Taranto", "19"}, {"Catania", "30"},
                {"Avellino", "26"}, {"Benevento", "28"}, {"Crotone", "24"},
                {"Lecco", "22"}, {"Rimini", "20"}, {"Perugia", "27"},
                {"Ternana", "25"}, {"Spal", "24"}, {"Arezzo", "21"},
                {"Torres", "19"}, {"Lucchese", "18"}
        });

        // PRIMERA B NACIONAL (Argentina) — 20 equipos
        footballDataService.createLeagueManual("Primera B Nacional", "Argentina", 1, "Sudamérica", new String[][] {
                {"Quilmes", "28"}, {"Chacarita Juniors", "27"}, {"Atlanta", "22"},
                {"Brown de Adrogué", "20"}, {"Almagro", "21"}, {"San Martín de Tucumán", "30"},
                {"Gimnasia de Jujuy", "24"}, {"Estudiantes de Buenos Aires", "23"},
                {"Nueva Chicago", "25"}, {"Deportivo Morón", "22"}, {"Almirante Brown", "21"},
                {"Defensores de Belgrano", "20"}, {"San Telmo", "19"}, {"Temperley", "24"},
                {"Sacachispas", "17"}, {"Comunicaciones", "18"}, {"Talleres de Remedios de Escalada", "19"},
                {"Deportivo Madryn", "20"}, {"Güemes de Santiago del Estero", "21"},
                {"Ferro Carril Oeste", "26"}
        });

        // SÉRIE B (Brasil) — 20 equipos
        footballDataService.createLeagueManual("Série B", "Brasil", 1, "Sudamérica", new String[][] {
                {"Avaí", "28"}, {"Chapecoense", "30"}, {"Guarani", "27"},
                {"Paysandu", "25"}, {"CRB", "23"}, {"Ituano", "22"},
                {"Brusque", "20"}, {"Novorizontino", "26"}, {"Vila Nova", "24"},
                {"Operário", "22"}, {"Goiás", "32"}, {"Coritiba", "30"},
                {"Ponte Preta", "27"}, {"Amazonas", "20"}, {"Sport Recife", "32"},
                {"Ceará", "30"}, {"América Mineiro", "28"}, {"Botafogo-SP", "21"},
                {"ABC", "20"}, {"Ferroviária", "19"}
        });

        // ===== LIGAS HARDCODEADAS — NIVEL 2 (level = 2) =====

        // SEGUNDA DIVISIÓN (España) — 22 equipos
        footballDataService.createLeagueManual("Segunda División", "España", 2, "Europa", new String[][] {
                {"Real Sporting de Gijón", "48"}, {"Real Zaragoza", "50"}, {"RC Deportivo", "52"},
                {"Málaga CF", "46"}, {"Cádiz CF", "50"}, {"Granada CF", "52"},
                {"CD Castellón", "42"}, {"Albacete Balompié", "40"}, {"SD Eibar", "45"},
                {"Real Racing de Santander", "46"}, {"SD Huesca", "42"}, {"Burgos CF", "40"},
                {"CD Leganés", "48"}, {"AD Ceuta FC", "38"}, {"CD Mirandés", "40"},
                {"FC Andorra", "42"}, {"Real Valladolid CF", "52"}, {"UD Almería", "48"},
                {"UD Las Palmas", "50"}, {"CyD Leonesa", "38"}, {"Córdoba CF", "45"},
                {"Real Sociedad B", "44"}
        });

        // SERIE B (Italia) — 20 equipos
        footballDataService.createLeagueManual("Serie B", "Italia", 2, "Europa", new String[][] {
                {"Palermo", "52"}, {"Sampdoria", "55"}, {"Bari", "48"},
                {"Cremonese", "44"}, {"Brescia", "46"}, {"Pisa", "45"},
                {"Catanzaro", "42"}, {"Modena", "40"}, {"Spezia", "46"},
                {"Reggina", "43"}, {"Frosinone", "44"}, {"Salernitana", "48"},
                {"Cosenza", "38"}, {"Südtirol", "36"}, {"Cittadella", "40"},
                {"Juve Stabia", "38"}, {"Carrarese", "36"}, {"Mantova", "35"},
                {"Cesena", "40"}, {"Sassuolo", "52"}
        });

        // LIGUE 2 (Francia) — 20 equipos
        footballDataService.createLeagueManual("Ligue 2", "Francia", 2, "Europa", new String[][] {
                {"FC Metz", "48"}, {"Bordeaux", "52"}, {"Saint-Étienne", "55"},
                {"Caen", "42"}, {"Guingamp", "40"}, {"Rodez", "36"},
                {"Grenoble", "38"}, {"Laval", "38"}, {"Amiens", "40"},
                {"Paris FC", "44"}, {"Pau FC", "36"}, {"Valenciennes", "40"},
                {"Bastia", "38"}, {"Dunkerque", "35"}, {"Quevilly-Rouen", "36"},
                {"Troyes", "42"}, {"Ajaccio", "38"}, {"Niort", "35"},
                {"Concarneau", "34"}, {"Annecy", "35"}
        });

        // 2. BUNDESLIGA (Alemania) — 18 equipos
        footballDataService.createLeagueManual("2. Bundesliga", "Alemania", 2, "Europa", new String[][] {
                {"Hamburger SV", "58"}, {"Schalke 04", "58"}, {"Fortuna Düsseldorf", "50"},
                {"Hannover 96", "48"}, {"1. FC Kaiserslautern", "52"}, {"Hertha BSC", "55"},
                {"1. FC Nürnberg", "48"}, {"Greuther Fürth", "42"}, {"SV Darmstadt 98", "44"},
                {"Eintracht Braunschweig", "40"}, {"SC Paderborn", "42"}, {"1. FC Magdeburg", "40"},
                {"SV Elversberg", "38"}, {"Karlsruher SC", "44"}, {"Preußen Münster", "36"},
                {"SSV Ulm", "35"}, {"Jahn Regensburg", "38"}, {"FC Köln", "55"}
        });

        // LIGA PROFESIONAL (Argentina) — 28 equipos
        footballDataService.createLeagueManual("Liga Profesional", "Argentina", 2, "Sudamérica", new String[][] {
                {"Boca Juniors", "65"}, {"River Plate", "65"}, {"Racing Club", "58"},
                {"San Lorenzo", "55"}, {"Independiente", "58"}, {"Vélez Sarsfield", "50"},
                {"Lanús", "48"}, {"Estudiantes de La Plata", "52"}, {"Newell's Old Boys", "48"},
                {"Rosario Central", "46"}, {"Talleres de Córdoba", "50"}, {"Defensa y Justicia", "45"},
                {"Argentinos Juniors", "44"}, {"Banfield", "44"}, {"Colón", "43"},
                {"Unión de Santa Fe", "42"}, {"Godoy Cruz", "42"}, {"Huracán", "46"},
                {"Tigre", "42"}, {"Platense", "40"}, {"Belgrano", "44"},
                {"Central Córdoba", "38"}, {"Sarmiento", "38"}, {"Barracas Central", "36"},
                {"Instituto", "40"}, {"Gimnasia La Plata", "44"}, {"San Martín de San Juan", "38"},
                {"Atlético Tucumán", "42"}
        });

        // LIGA MX (México) — 18 equipos
        footballDataService.createLeagueManual("Liga MX", "México", 2, "Norteamérica", new String[][] {
                {"Club América", "62"}, {"Guadalajara", "60"}, {"UNAM Pumas", "54"},
                {"Cruz Azul", "58"}, {"Monterrey", "60"}, {"Tigres UANL", "62"},
                {"Santos Laguna", "50"}, {"Toluca", "52"}, {"León", "50"},
                {"Pachuca", "52"}, {"Atlas", "48"}, {"Necaxa", "42"},
                {"Puebla", "44"}, {"Querétaro", "42"}, {"Tijuana", "44"},
                {"Mazatlán", "40"}, {"FC Juárez", "38"}, {"San Luis", "42"}
        });

        // SÜPER LIG (Turquía) — 19 equipos
        footballDataService.createLeagueManual("Süper Lig", "Turquía", 2, "Europa", new String[][] {
                {"Galatasaray", "62"}, {"Fenerbahçe", "60"}, {"Beşiktaş", "58"},
                {"Trabzonspor", "52"}, {"İstanbul Başakşehir", "48"}, {"Adana Demirspor", "42"},
                {"Antalyaspor", "40"}, {"Konyaspor", "40"}, {"Sivasspor", "38"},
                {"Alanyaspor", "40"}, {"Kasımpaşa", "38"}, {"Gaziantep FK", "37"},
                {"Kayserispor", "36"}, {"Hatayspor", "36"}, {"Ankaragücü", "38"},
                {"Rizespor", "36"}, {"Pendikspor", "35"}, {"Samsunspor", "40"},
                {"Eyüpspor", "37"}
        });

        // SUPERLIGA GRIEGA (Grecia) — 14 equipos
        footballDataService.createLeagueManual("Superliga Griega", "Grecia", 2, "Europa", new String[][] {
                {"Olympiacos", "60"}, {"Panathinaikos", "55"}, {"AEK Athens", "54"},
                {"PAOK", "55"}, {"Aris Thessaloniki", "44"}, {"Asteras Tripolis", "38"},
                {"Volos NFC", "36"}, {"OFI Crete", "35"}, {"Atromitos", "37"},
                {"Lamia", "34"}, {"Levadiakos", "33"}, {"Ionikos", "34"},
                {"Panserraikos", "33"}, {"Kallithea", "32"}
        });

        // BELGIAN PRO LEAGUE (Bélgica) — 16 equipos
        footballDataService.createLeagueManual("Belgian Pro League", "Bélgica", 2, "Europa", new String[][] {
                {"Club Brugge", "60"}, {"Anderlecht", "58"}, {"KRC Genk", "55"},
                {"Standard Liège", "50"}, {"Gent", "50"}, {"Antwerp", "48"},
                {"Union Saint-Gilloise", "52"}, {"Cercle Brugge", "40"},
                {"Mechelen", "42"}, {"OH Leuven", "38"}, {"Charleroi", "42"},
                {"Kortrijk", "38"}, {"Westerlo", "40"}, {"STVV", "37"},
                {"RWD Molenbeek", "36"}, {"Eupen", "35"}
        });

        // SCOTTISH PREMIERSHIP (Escocia) — 12 equipos
        footballDataService.createLeagueManual("Scottish Premiership", "Escocia", 2, "Europa", new String[][] {
                {"Celtic", "62"}, {"Rangers", "60"}, {"Aberdeen", "42"},
                {"Hibernian", "40"}, {"Hearts", "42"}, {"Dundee United", "38"},
                {"St. Mirren", "35"}, {"Motherwell", "36"}, {"Kilmarnock", "35"},
                {"Ross County", "33"}, {"Livingston", "33"}, {"St. Johnstone", "34"}
        });

        // MLS (Estados Unidos) — 29 equipos
        footballDataService.createLeagueManual("MLS", "Estados Unidos", 2, "Norteamérica", new String[][] {
                {"LA Galaxy", "54"}, {"Inter Miami", "58"}, {"LAFC", "55"},
                {"New York City FC", "52"}, {"Atlanta United", "50"}, {"Seattle Sounders", "52"},
                {"Portland Timbers", "48"}, {"Nashville SC", "44"}, {"Columbus Crew", "50"},
                {"FC Cincinnati", "46"}, {"New York Red Bulls", "48"}, {"Philadelphia Union", "46"},
                {"New England Revolution", "44"}, {"Orlando City", "44"}, {"Charlotte FC", "42"},
                {"Chicago Fire", "42"}, {"DC United", "44"}, {"CF Montréal", "40"},
                {"Toronto FC", "46"}, {"Minnesota United", "42"}, {"Real Salt Lake", "44"},
                {"Colorado Rapids", "42"}, {"FC Dallas", "44"}, {"Houston Dynamo", "46"},
                {"Sporting Kansas City", "42"}, {"Austin FC", "44"}, {"Vancouver Whitecaps", "40"},
                {"San Jose Earthquakes", "38"}, {"St. Louis City SC", "44"}
        });

        }

        if(eventRepository.count() == 0 ){


// A001 — Primer día en la academia
            Event a001 = footballDataService.createEvent(
                    "Bienvenido al barro",
                    "Primer día en la academia. El vestuario huele a linimento y a historia. El míster entra sin llamar: 'Aquí no hay niños mimados. El que no corra, a casa. El que no compita, a casa. Y el que llegue tarde... que ni se moleste en venir.' Te mira directamente a ti al decir lo último.",
                    "academia", "unico", "common", "academia", 16, 17, 1, 4, null
            );
            footballDataService.createOption(a001,
                    "Salir al campo como si te fuera la vida en ello",
                    "Sprints, entradas, disputas... no dejas una pelota sin pelear. Al acabar, el míster le comenta al segundo: 'Este chaval tiene hambre.' Tú apenas puedes respirar, pero sonríes por dentro.",
                    0, 1, 0, 1, 0, 0, 2, 0, 3, 1, 0, 0, 0, 0);
            footballDataService.createOption(a001,
                    "Mantener perfil bajo y observar cómo funciona todo",
                    "Mientras los demás se matan por cada balón, tú lees el partido desde fuera. No destacas, pero tampoco metes la pata. A veces el que menos ruido hace es el que más dura.",
                    0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0);

// A002 — La novatada
            Event a002 = footballDataService.createEvent(
                    "Bienvenido al vestuario, chaval",
                    "Primer día de concentración. Cuando vuelves de la ducha te encuentras los calzoncillos colgados de la portería del campo de entrenamiento. Todo el vestuario muerto de risa. El capitán te mira con una sonrisa de oreja a oreja: 'Es la tradición, chaval. A todos nos ha pasado.'",
                    "academia", "decision", "common", "academia", 16, 17, 1, 4, null
            );
            footballDataService.createOption(a002,
                    "Reírte con ellos y ir a buscarlos descalzo",
                    "Vas a la portería muerto de risa, levantas los calzoncillos como si fueran una copa y haces una reverencia. El vestuario te adopta. A partir de hoy eres uno más.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 5, 0, 0, 0, 0);
            footballDataService.createOption(a002,
                    "Mosquarte y dejar claro que no tiene gracia",
                    "Les dices que eso es una falta de respeto. Se hace un silencio incómodo. No vuelven a hacerlo, pero te miran raro durante una semana. Puede que tengas razón, pero el vestuario tiene sus propias leyes.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, 0, 0);

// A003 — Tu viejo antes del primer partido
            Event a003 = footballDataService.createEvent(
                    "La charla de tu padre en el coche",
                    "Camino al campo, tu padre baja la radio y te suelta: 'Oye, me da igual si metes tres goles o si no la tocas. Lo que no quiero ver es que vayas con miedo. ¿Estamos?' Silencio. El semáforo se pone en verde.",
                    "academia", "decision", "common", "academia", 16, 17, 1, 4, null
            );
            footballDataService.createOption(a003,
                    "Asentir y saltar al campo sin presión",
                    "Sales al césped con la cabeza limpia. Sin miedo al fallo. Tu padre te ve desde la grada con los brazos cruzados y una media sonrisa.",
                    0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a003,
                    "Ponerte los auriculares y prepararte a tu manera",
                    "Subes el volumen, cierras los ojos y entras en tu burbuja. Cuando pisas el campo ya no estás nervioso. Estás frío.",
                    0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0);

// A004 — Quedarse a entrenar
            Event a004 = footballDataService.createEvent(
                    "¿Te quedas o te piras?",
                    "Son las siete de la tarde. El entrenamiento terminó hace veinte minutos, pero hay cuatro tíos que siguen en el campo tirando centros y rematando de cabeza. Uno de ellos te grita desde la banda: '¡Eh, nuevo! ¿Te vienes o te vas a casa a ver el TikTok?'",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a004,
                    "Dejar la mochila y quedarte una hora más",
                    "Una hora larga de centros, controles orientados y remates. Las piernas lloran, pero la técnica habla. Y lo más importante: ya tienes a cuatro tíos que te consideran uno de los suyos.",
                    0, 2, 1, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0);
            footballDataService.createOption(a004,
                    "Pasar de largo, que mañana hay más",
                    "Levantas la mano, dices que mañana sin falta, y te vas. Nadie te juzga, pero tampoco nadie te espera la próxima vez.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0);

// A005 — Sesión voluntaria a las ocho
            Event a005 = footballDataService.createEvent(
                    "Voluntarios a las ocho de la mañana",
                    "El preparador físico aparece en el vestuario: 'Mañana, sesión voluntaria a las ocho. Voluntaria, ¿eh? Nadie os obliga.' Mira uno por uno. 'Pero yo me acuerdo de quién viene y de quién no.'",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a005,
                    "Estar ahí a las ocho en punto",
                    "El despertador suena a las 6:50 y tu cuerpo te odia. Pero llegas. Sois seis. El preparador os da una palmada en el hombro sin decir nada. Ese gesto vale más que cualquier discurso.",
                    1, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(a005,
                    "Quedarte en la cama, que voluntario es voluntario",
                    "Nueve horas de sueño. Te sientas de lujo en el entrenamiento de la tarde. Pero el preparador no te mira ni una sola vez.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0);

// ────────────────────────────────────────
// BLOQUE 2 — ENTRENADORES Y MEJORA (A006-A010)
// ────────────────────────────────────────

// A006 — Te cambian de posición
            Event a006 = footballDataService.createEvent(
                    "Hoy juegas donde yo te diga",
                    "El míster te corta en el calentamiento: 'Te necesito en otra posición hoy. No me pongas caras. Quiero ver si sirves para algo más que para lo tuyo.' Algunos compañeros se miran.",
                    "academia", "decision", "uncommon", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a006,
                    "Aceptar sin rechistar y dar el callo",
                    "No es tu sitio, no es tu zona de confort, y aun así sacas el partido adelante. El míster lo escribe en su libreta con bolígrafo rojo. Eso es buena señal.",
                    0, 1, 0, 0, 1, 1, 1, 0, 3, 0, 0, 0, 0, 0);
            footballDataService.createOption(a006,
                    "Decirle que rindes mejor en tu posición",
                    "El míster te clava una mirada de esas que duran tres segundos pero pesan tres horas. 'Vale', dice, y se da la vuelta. Juegas en tu sitio, pero algo se ha roto un poco.",
                    0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 0);

// A007 — Preparar un aspecto concreto
            Event a007 = footballDataService.createEvent(
                    "El míster quiere que elijas",
                    "Partido importante el sábado contra el líder. El entrenador te llama aparte: 'Esta semana quiero que te centres en mejorar un aspecto concreto de tu juego. ¿En qué te quieres enfocar?'",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a007,
                    "Practicar definición: remates, disparos, penaltis",
                    "Dos tardes de tiros a puerta. El portero del juvenil te odia, pero tu disparo mejora considerablemente.",
                    0, 0, 2, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(a007,
                    "Trabajar el físico: velocidad, fuerza, resistencia",
                    "Circuitos de velocidad y pesas con el preparador. Llegas al sábado con las piernas cargadas pero más fuerte que nunca.",
                    1, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(a007,
                    "Mejorar la visión de juego: posicionamiento, lectura del partido",
                    "Vídeo con el segundo entrenador y ejercicios tácticos. Ves el campo de otra manera el sábado.",
                    0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0);

// A008 — El míster te elogia y te exige
            Event a008 = footballDataService.createEvent(
                    "Te estás ganando minutos, pero...",
                    "El míster te para en el pasillo después del entrenamiento: 'Mira, te estás ganando minutos, no te lo voy a negar. Pero todavía cometes demasiados errores tontos. Errores que te los perdonan en juveniles pero arriba no te los van a perdonar nadie.'",
                    "academia", "decision", "uncommon", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a008,
                    "Preguntarle en qué mejorar concretamente",
                    "'¿Qué errores, míster? Dime cuáles y los corrijo.' El entrenador asiente. 'Eso me gusta. Un jugador que pregunta es un jugador que aprende.'",
                    0, 0, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 0, 0);
            footballDataService.createOption(a008,
                    "Decirle que estás preparado para jugar más",
                    "'Míster, creo que merezco más minutos.' El entrenador levanta una ceja. 'Eso no lo decides tú, chaval. Pero me gusta que lo digas.' Ambición captada.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a008,
                    "No decir nada y demostrarlo entrenando",
                    "Asientes, te das la vuelta y al día siguiente entrenas como si fuera el último entreno de tu vida. El míster no dice nada. Pero te pone de titular el sábado.",
                    0, 0, 0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0);

// A009 — Las notas han bajado
            Event a009 = footballDataService.createEvent(
                    "El tutor te cita en su despacho",
                    "El tutor del instituto te sienta en la silla y abre la carpeta: 'Mira, chaval, no sé si vas a ser futbolista o fontanero, pero con estas notas no acabas ni el curso. La academia tiene un protocolo y yo tengo un teléfono para llamar a tus padres.'",
                    "academia", "decision", "uncommon", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a009,
                    "Ponerte las pilas con los estudios",
                    "Dos tardes a la semana sin balón. Los apuntes se convierten en tu segundo rival. Pero las notas suben y el tutor deja de mirarte como si fueras un caso perdido.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a009,
                    "Priorizar el fútbol y que los estudios esperen",
                    "Los estudios pueden esperar. El fútbol no. Pero la espada de la sanción académica sigue colgando sobre tu cabeza.",
                    0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// A010 — Mejorar la pierna mala
            Event a010 = footballDataService.createEvent(
                    "La pierna que no quieres usar",
                    "El míster te para después del partido: 'Hoy has tirado cinco veces con la derecha y cero con la izquierda. Hasta el portero ya sabía por dónde ibas a tirar. O aprendes a usar las dos o te van a leer como un libro.'",
                    "academia", "decision", "common", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a010,
                    "Quedarte después a practicar con la pierna mala",
                    "Tres semanas tirando solo con la zurda. Los primeros días los balones van al parking. Pero poco a poco el disparo empieza a tener sentido.",
                    0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(a010,
                    "Centrarte en mejorar lo que ya haces bien",
                    "Si la derecha te funciona, para qué complicarte. Mejor ser buenísimo con una que mediocre con dos. Al menos eso crees.",
                    0, 0, 1, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0);

// ────────────────────────────────────────
// BLOQUE 3 — FAMILIA Y VIDA PERSONAL (A011-A014)
// ────────────────────────────────────────

// A011 — Tu madre te frena
            Event a011 = footballDataService.createEvent(
                    "Tu madre planta el freno en seco",
                    "Tres semanas sin un solo día libre. Tu madre te intercepta en la cocina a las once de la noche: 'Cariño, para. Llevas un mes que no hablas de otra cosa que no sea fútbol. Come algo, duerme ocho horas y mañana ves si te apetece seguir.'",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a011,
                    "Hacerle caso y tomarte un día libre de verdad",
                    "Sofá, tortilla de tu madre y una siesta de dos horas. El lunes vuelves al campo renovado.",
                    0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a011,
                    "Decirle que sí pero escaparte a entrenar igualmente",
                    "Le das un beso, le dices que sí, y a las dos horas estás en el parque tirando a portería. Las madres siempre saben cuándo les mientes.",
                    0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// A012 — Tu madre sobre los amigos
            Event a012 = footballDataService.createEvent(
                    "Llevas semanas sin salir de casa",
                    "Tu madre te pilla mirando el móvil por la noche: 'Cariño, llevas semanas sin quedar con tus amigos. ¿Sabes que existe vida más allá del fútbol, verdad?'",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a012,
                    "Quedar con tus amigos este fin de semana",
                    "Pizza, risas y cero conversaciones de fútbol. Vuelves a sentirte como un chaval normal por unas horas. A veces hace falta.",
                    0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a012,
                    "Seguir centrado, ya saldrás cuando consigas algo",
                    "El sacrificio primero, la diversión después. Eso te repites. Aunque a las tres de la mañana ves las fotos de tus amigos y algo te pica.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0);

// A013 — Tu padre te nota distraído
            Event a013 = footballDataService.createEvent(
                    "Tu padre nota algo raro",
                    "Tu padre te observa durante la cena: 'Te noto más pendiente de los ojeadores que del balón. ¿Tú has venido a jugar al fútbol o a mirar a la grada?' La pregunta duele porque tiene razón.",
                    "academia", "decision", "uncommon", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a013,
                    "Darle la razón y volver a centrarte en jugar",
                    "Tu padre tiene esa habilidad de bajarte los humos con una frase. Bajas la cabeza, asientes, y al día siguiente juegas mirando solo al campo.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a013,
                    "Decirle que es normal pensar en el futuro",
                    "'Papá, es que hay clubes preguntando por mí. ¿Cómo no voy a pensar en ello?' Tu padre suspira. 'Vale, pero que no se te olvide quién eras antes de que preguntaran.'",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// A014 — Pachanga y consola
            Event a014 = footballDataService.createEvent(
                    "Pachanga en el barrio o consola en casa",
                    "Sábado por la tarde. Tu mejor colega te manda un audio: 'Tío, hay pachanga en el parque a las seis. Si no, me quedo en mi casa y echamos unas partidas al FC.' Mañana entrenas temprano.",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a014,
                    "Ir a la pachanga",
                    "Dos horas de fútbol callejero con las chaquetas de porterías. Algún golpe sin importancia y un caño que te va a durar en la memoria un mes.",
                    0, 1, 0, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0);
            footballDataService.createOption(a014,
                    "Consola y dormir pronto",
                    "Tres partidas, una pizza y a la cama a las once. Llegas fresco al entrenamiento del domingo.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0);
            footballDataService.createOption(a014,
                    "Quedarte en casa estudiando (mentira, viendo YouTube)",
                    "Tres horas de vídeos de regates de Neymar. No has estudiado, no has salido y no has descansado. Pero has aprendido una ruleta que mañana probablemente no te salga.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// ────────────────────────────────────────
// BLOQUE 4 — COMPETENCIA Y VESTUARIO (A015-A019)
// ────────────────────────────────────────

// A015 — El rival interno
            Event a015 = footballDataService.createEvent(
                    "El que compite contigo por el puesto",
                    "Otro jugador de tu posición te mira fijo en el calentamiento y te suelta: 'Tranquilo, chaval, que el puesto sigue siendo mío.' Algunos compañeros bajan la mirada. El silencio pesa.",
                    "academia", "decision", "uncommon", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a015,
                    "Ignorarle completamente",
                    "No le das ni una sola mirada. En el campo hablas con las piernas. El entrenador lo ve todo.",
                    0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(a015,
                    "Responderle con calma",
                    "'Ya veremos.' Dos palabras. La tensión queda en el aire. Pero ahora sabe que no te va a achicar.",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, -1, 0, 0, 0, 0);
            footballDataService.createOption(a015,
                    "Demostrarlo en el entrenamiento sin decir nada",
                    "Tres regates, un gol y una asistencia en el partidillo. No dices ni una palabra. No hace falta.",
                    0, 1, 1, 0, 0, 0, 1, 0, 2, 1, 0, 0, 0, 0);

// A016 — El rival sube la tensión
            Event a016 = footballDataService.createEvent(
                    "La competencia se pone seria",
                    "Tu rival interno ha mejorado mucho. Ha marcado tres goles en las últimas dos semanas y el míster le está dando más minutos. Te lo cruza en el vestuario: 'Últimamente juegas tú y yo me quedo en el banquillo. Pero esto no va a quedarse así, ¿eh?'",
                    "academia", "decision", "uncommon", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a016,
                    "Aceptar la competencia limpiamente",
                    "Le tiendes la mano: 'Que gane el mejor.' Él la acepta sorprendido. La competencia se mantiene, pero con respeto.",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 2, 0, 0, 0, 0);
            footballDataService.createOption(a016,
                    "Intentar hacerte su aliado",
                    "'Tío, los dos somos buenos. Si nos ayudamos en vez de matarnos, los dos vamos a salir ganando.' Él lo piensa. Y al final asiente.",
                    0, 0, 0, 0, 0, 1, 0, 0, 1, 4, 0, 0, 0, 0);
            footballDataService.createOption(a016,
                    "Plantarle cara directamente",
                    "'Tú haz lo que quieras, pero el puesto es mío.' La tensión sube. El vestuario se divide. Pero tú juegas con fuego dentro.",
                    0, 0, 0, 0, 0, 0, 1, 0, -1, -2, 0, 0, 0, 0);

// A017 — Penalti y silencio
            Event a017 = footballDataService.createEvent(
                    "Penalti en el 88",
                    "Minuto 88. Penalti a favor. Final del torneo comarcal. El capitán pone el balón en el punto y mira alrededor. Nadie dice nada. El portero rival da saltitos en la línea. El árbitro pita. Alguien tiene que cogerlo.",
                    "academia", "decision", "rare", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a017,
                    "Pedir el balón y asumir la responsabilidad",
                    "Coges el balón con las dos manos. El campo se calla. Carrera corta. Disparo cruzado. Red. GOL. La piña del equipo te deja sin aire y sin camiseta.",
                    0, 0, 3, 0, 3, 0, 5, 0, 2, 3, 2, 3, 0, 0);
            footballDataService.createOption(a017,
                    "Dejárselo al capitán, que para eso lleva el brazalete",
                    "El capitán lo tira al palo largo. Gol. La celebración es suya. Nadie te dice nada, pero en el autobús de vuelta piensas que podrías haberlo tirado tú.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0, 0);

// A018 — Recoger el material
            Event a018 = footballDataService.createEvent(
                    "Recoger los conos o pirar",
                    "El capitán se queda recogiendo los conos y los petos después del entrenamiento. Casi todos se han ido ya a la ducha. Te mira: 'Si quieres me echas una mano, que esto solo es un coñazo.'",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a018,
                    "Ayudarle a recoger todo",
                    "Diez minutos de recoger petos, conos y porterías portátiles. El capitán no te da las gracias con palabras, pero al día siguiente te pasa el primer balón del entrenamiento. Eso significa más.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0);
            footballDataService.createOption(a018,
                    "Irte a la ducha, que cada uno que recoja lo suyo",
                    "Te vas. Nadie te dice nada. Pero el capitán se acuerda.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0);

// A019 — El compañero al que nadie pasa
            Event a019 = footballDataService.createEvent(
                    "El chaval invisible",
                    "Llevas tres partidos viendo la misma escena: hay un chaval nuevo al que no le dan ni una. Le ignoran en los entrenamientos. Hoy le ves sentado solo en el vestuario mirando al suelo.",
                    "academia", "decision", "uncommon", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a019,
                    "Sentarte con él y buscarle en el campo",
                    "Le dices que cuente contigo. En el siguiente partido le filtras un pase que casi acaba en gol. No lo marca, pero levanta la cabeza por primera vez en semanas.",
                    0, 0, 0, 0, 0, 1, 0, 0, 2, 3, 0, 0, 0, 0);
            footballDataService.createOption(a019,
                    "No meterte, que cada uno se busque la vida",
                    "Bastante tienes con lo tuyo. No es tu guerra. Pero cuando le ves recoger solo su bolsa, algo te pica un poco.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// ────────────────────────────────────────
// BLOQUE 5 — OJEADORES E INTERÉS (A020-A023)
// ────────────────────────────────────────

// A020 — Ojeador del {club}
            Event a020 = footballDataService.createEvent(
                    "Hay alguien en la grada con una carpeta",
                    "Diez minutos antes del partido, un compañero entra al vestuario: 'Tíos, hay un tío arriba con un polo del {club} y una carpeta. Está apuntando cosas.' El vestuario explota en murmullos.",
                    "academia", "decision", "rare", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a020,
                    "Intentar hacer cosas que normalmente no haces",
                    "Bicicleta en banda, sombrero a un lateral y un disparo desde fuera del área que va a la grada. El ojeador levanta una ceja. No sabes si es buena o mala señal.",
                    1, 1, 1, 0, -1, 0, 3, 0, -1, -1, 0, 1, 0, 0);
            footballDataService.createOption(a020,
                    "Jugar como si el ojeador no existiera",
                    "Un toque, pared, desmarque, ayuda en defensa. Sin fuegos artificiales. El míster te felicita al acabar. Del ojeador no sabes nada, pero duermes tranquilo.",
                    0, 1, 0, 0, 2, 0, 1, 0, 3, 1, 0, 0, 0, 0);

// A021 — El {club} pregunta
            Event a021 = footballDataService.createEvent(
                    "Rumor en el vestuario: el Atleti",
                    "El rumor corre como la pólvora antes del partido. Un compañero te lo suelta mientras te atas las botas: 'Eh, que dicen que hoy viene un ojeador del {club}.' No sabes si es verdad o mentira. Pero el corazón te late un poco más rápido.",
                    "academia", "decision", "rare", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a021,
                    "No pensar en ello y jugar como siempre",
                    "Te olvidas del rumor y juegas tu partido. Si hay ojeador, que vea al jugador que eres de verdad. Sin disfraces.",
                    0, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a021,
                    "Salir a demostrarlo todo desde el primer minuto",
                    "Saltas al campo enchufado. Cada jugada es una audición. El resultado es impredecible: puedes brillar o quemarte.",
                    1, 0, 1, 0, -1, 0, 2, 0, 0, 0, 0, 2, 0, 0);

// A022 — El {club} quiere verte
            Event a022 = footballDataService.createEvent(
                    "El Sevilla quiere hacerte una prueba",
                    "El director deportivo te espera en la puerta del vestuario: 'Oye, que ha llamado el {club}. Quieren que vayas tres días a entrenar con su cantera. Es solo una toma de contacto, sin presión. ¿Qué le digo?'",
                    "academia", "decision", "rare", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a022,
                    "Ir a la prueba y ver qué ofrecen",
                    "Tres días en la ciudad deportiva del Sevilla. El nivel es otro. Los campos son otros. Todo huele a profesionalismo. Te vas con la sensación de que el fútbol de verdad empieza un poquito más arriba.",
                    0, 1, 0, 0, 1, 0, 3, 0, 0, -1, 0, 0, 1, 0);
            footballDataService.createOption(a022,
                    "Rechazar y quedarte en tu club",
                    "'No, gracias. Estoy bien aquí.' El director asiente sorprendido. En el vestuario la noticia corre. Algunos te respetan más. Otros te llaman loco.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0, 1);

// A023 — Ha llamado el {topCl
            Event a023 = footballDataService.createEvent(
                    "Llamada de un grande",
                    "El director deportivo te llama al despacho. Cierra la puerta, algo que no hace nunca. Se sienta, junta las manos y suelta: 'No quiero que te vuelvas loco con esto... pero el {topClub} ha pedido un informe tuyo.' Silencio. '¿Me has oído?'",
                    "academia", "decision", "epic", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a023,
                    "Mantener los pies en el suelo",
                    "No se lo cuentas ni a tu madre. Sigues entrenando como siempre. Si el {topClub} quiere venir, que venga. Pero tú no vas a mover ni una ceja por ellos.",
                    0, 0, 0, 0, 3, 0, 1, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(a023,
                    "Darlo todo para que la oferta llegue",
                    "La noticia te pone las pilas como no te las ha puesto nada en tu vida. Cada entrenamiento es una final. No duermes bien, pero entrenas como nunca.",
                    0, 1, 1, -1, 0, 0, 3, 0, 0, 0, 0, 0, 1, 0);

// ────────────────────────────────────────
// BLOQUE 6 — TORNEO Y SELECCIÓN (A024-A026)
// ────────────────────────────────────────

// A024 — Torneo nacional juvenil
            Event a024 = footballDataService.createEvent(
                    "El torneo que lo cambia todo",
                    "El director deportivo planta un cartel en el vestuario: 'TORNEO NACIONAL JUVENIL — PRÓXIMA SEMANA.' Debajo, en boli rojo, alguien ha escrito: 'Os van a estar mirando.' Nadie sabe quién lo ha escrito, pero todo el mundo sabe que es verdad.",
                    "academia", "decision", "epic", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a024,
                    "Ir tranquilo, sin inventar nada nuevo",
                    "Haces lo de siempre. Ni más ni menos. Y resulta que lo de siempre es suficientemente bueno para que dos ojeadores se queden esperando hablar con tu entrenador.",
                    0, 0, 0, 0, 2, 0, 3, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(a024,
                    "Prometerte que serás el MVP del torneo",
                    "La promesa te la haces en el espejo del baño. Y durante cuatro partidos juegas como si cada balón fuera el último. A veces funciona. A veces te pasas.",
                    0, 0, 2, 0, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0);
            footballDataService.createOption(a024,
                    "Jugar para que el equipo gane, tú ya saldrás beneficiado",
                    "El míster te señala al final del torneo: 'Ojalá todos pensaran como tú.' No eres el MVP, pero eres el primero al que fichará cualquier club serio.",
                    0, 0, 0, 0, 1, 1, 2, 0, 4, 3, 0, 0, 0, 0);

// A025 — El seleccionador sub-17
            Event a025 = footballDataService.createEvent(
                    "Alguien de la selección anda mirando",
                    "Te enteras por WhatsApp, como todo en la vida: el seleccionador sub-17 ha estado en tu último partido. No dijo nada. No habló con nadie. Solo miró, apuntó, y se fue. La pregunta es: ¿apuntó tu nombre o el de otro?",
                    "academia", "decision", "rare", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a025,
                    "Olvidarte del tema y seguir a lo tuyo",
                    "Borras el grupo de WhatsApp, te pones las botas y entrenas. Si vienes, vienes. Y si no, pues ya nos veremos.",
                    0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a025,
                    "Intentar ser protagonista por si vuelve",
                    "Los siguientes tres entrenos los juegas como si hubiera cámaras. A veces brillas. Otras te estrellas. La línea entre ambición y ansiedad es muy fina.",
                    1, 0, 1, 0, -1, 0, 2, 0, 0, 0, 0, 2, 0, 0);

// A026 — Lesión del amigo
            Event a026 = footballDataService.createEvent(
                    "Tu mejor amigo en la cantera se lesiona",
                    "Rodillazo en un entrenamiento. Tu mejor colega del vestuario se queda en el suelo agarrándose la rodilla. La ambulancia. El silencio. El diagnóstico: rotura de ligamento cruzado. Seis meses fuera.",
                    "academia", "decision", "rare", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a026,
                    "Ir a verle al hospital esa misma noche",
                    "Llegas con un bocadillo y el móvil cargado. Os pasáis dos horas viendo highlights de Messi. Cuando te vas, te dice: 'Gracias, tío. Eres el único que ha venido.'",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 3, 0, 0, 0, 0);
            footballDataService.createOption(a026,
                    "Mandarle un mensaje de ánimo",
                    "Le escribes un audio largo diciéndole que va a volver más fuerte. Él te responde con un emoji. No es lo mismo que ir, y los dos lo sabéis.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0);
            footballDataService.createOption(a026,
                    "No hacer nada especial",
                    "Bastante tienes con lo tuyo. Él lo entenderá. ¿No? Bueno, igual sí. Igual no.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0);

// ────────────────────────────────────────
// BLOQUE 7 — REPRESENTANTE Y PRENSA (A027-A028)
// ────────────────────────────────────────

// A027 — El representante aparece
            Event a027 = footballDataService.createEvent(
                    "Un tío con traje quiere hablar contigo",
                    "Después del torneo, un señor con traje y reloj caro te espera en la puerta: 'Chaval, soy representante de jugadores. He visto cómo juegas y creo que puedo ayudarte. Todavía eres menor, pero puedo empezar a moverme.' Te da una tarjeta.",
                    "academia", "decision", "rare", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a027,
                    "Aceptar y dejarte representar",
                    "Hablas con tus padres, firman los papeles, y de repente tienes un representante. El mundo del fútbol profesional acaba de abrirte una puerta lateral.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 5, 0);
            footballDataService.createOption(a027,
                    "Hablar primero con tu familia antes de decidir",
                    "Le dices que te lo piensas. Tu padre investiga al tío, tu madre desconfía por principio, y al final toman una decisión en familia.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(a027,
                    "Rechazarlo, que aún es pronto",
                    "'Gracias, pero todavía no estoy en ese punto.' El representante asiente y te dice: 'Cuando quieras, aquí estoy.' La tarjeta se queda en tu bolsillo tres meses.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// A028 — El periodista local
            Event a028 = footballDataService.createEvent(
                    "Un periodista quiere entrevistarte",
                    "Un medio local quiere hacerte una entrevista después de tu gran torneo. El periodista te llama: 'Oye, eres la promesa de la cantera y a la gente le interesa saber quién eres. ¿Quedamos veinte minutos y hablamos?'",
                    "academia", "decision", "uncommon", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a028,
                    "Aceptar y responder con naturalidad",
                    "Veinte minutos de entrevista donde hablas de tus sueños, tu familia y tu equipo. Al día siguiente tu madre compra cinco ejemplares del periódico.",
                    0, 0, 0, 0, 0, 0, 3, 0, 0, 1, 1, 3, 0, 0);
            footballDataService.createOption(a028,
                    "Ser prudente y no decir mucho",
                    "Respondes con monosílabos y frases genéricas. La entrevista sale, pero sin chicha. Nadie se acuerda de ella al día siguiente.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0);
            footballDataService.createOption(a028,
                    "Decir que todavía no has conseguido nada",
                    "'Oye, que yo todavía no he hecho nada, ¿eh? Soy un chaval de cantera que intenta hacerse un hueco.' El periodista sonríe. 'Eso es lo que más me gusta oír.' La entrevista sale con ese titular.",
                    0, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0, 2, 0, 0);

// ────────────────────────────────────────
// BLOQUE 8 — DECISIONES CLAVE Y CIERRE (A029-A030)
// ────────────────────────────────────────

// A029 — Te quieren subir de categoría
            Event a029 = footballDataService.createEvent(
                    "El director quiere subirte",
                    "El director deportivo te espera después del entreno: 'Oye, estamos pensando en subirte a entrenar con el filial unas semanas. El nivel es otro, vas a sufrir, pero creo que te vendría bien. ¿Te atreves o prefieres quedarte con tu grupo?'",
                    "academia", "decision", "epic", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a029,
                    "Subir y aceptar el reto",
                    "El primer día con el filial te das cuenta de que todo va el doble de rápido. Las entradas son más fuertes, los pases más tensos, y nadie te espera. Sales del campo agotado y con un moratón en la espinilla. Pero también sales con una sonrisa de las grandes.",
                    1, 1, 0, 1, 2, 1, 3, 0, 3, 0, 0, 0, 0, 2);
            footballDataService.createOption(a029,
                    "Quedarte con tu grupo y seguir creciendo aquí",
                    "Prefieres ser el mejor de tu categoría antes de dar el salto. El director lo respeta, aunque su cara dice que esperaba otra respuesta.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0);

// A030 — Firmar el primer contrato profesional
            Event a030 = footballDataService.createEvent(
                    "El bolígrafo más pesado de tu vida",
                    "Despacho del director deportivo. Corbata. Traje. Un contrato sobre la mesa con tu nombre escrito con una tipografía que parece sacada de un documento de la ONU. El escudo del club en la esquina superior derecha. El director te tiende un bolígrafo de los buenos, de los que pesan: 'Lee todo. Y cuando estés listo... firma.'",
                    "academia", "unico", "legendary", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a030,
                    "Firmar sin que te tiemble el pulso",
                    "Tu firma nunca ha sido tan bonita como hoy. Cuando levantas el bolígrafo, el director te da la mano: 'Bienvenido al fútbol profesional.' Sales del despacho y le pegas una patada al aire en el pasillo. La aventura empieza aquí.",
                    0, 0, 0, 0, 0, 0, 5, 500, 5, 3, 3, 5, 2, 2);
            footballDataService.createOption(a030,
                    "Pedir tiempo para pensarlo con tu familia",
                    "El director frunce el ceño, pero asiente. 'Dos días. Ni uno más.' Sales del despacho y pasas las siguientes 48 horas sin dormir, pesando cada opción como si te fuera la vida.",
                    0, 0, 0, 0, 2, 0, 0, 0, -1, 0, 0, 0, 1, 0);
            footballDataService.createOption(a030,
                    "Preguntar qué plan tiene el club para ti",
                    "'Antes de firmar, quiero saber qué proyecto tenéis para mí. ¿Voy al filial? ¿Voy cedido? ¿Cuántos minutos voy a tener?' El director se recuesta en la silla. 'Vaya, un chaval con las ideas claras. Me gusta.' Y empieza a explicarte.",
                    0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 1, 3);


// ===== EVENTOS DEPORTIVOS (20 eventos) =====
// Categoría: deportivo | Fase: profesional
// Situaciones de partido, rendimiento, récords, rachas

// D001 — Racha goleadora
            Event d001 = footballDataService.createEvent(
                    "Llevas cinco partidos marcando",
                    "Cinco partidos, cinco goles. Los periódicos ya hablan de racha histórica. El míster te llama aparte: 'No cambies nada. Ni la dieta, ni las botas, ni la forma de atarte los cordones. Lo que estés haciendo, sigue.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d001,
                    "Mantener la calma y seguir con tu rutina",
                    "Misma rutina, mismas botas, mismo calentamiento. Marcas en el sexto partido también. La prensa se vuelve loca.",
                    0, 0, 2, 0, 2, 0, 4, 0, 2, 0, 2, 3, 0, 0);
            footballDataService.createOption(d001,
                    "Intentar batir el récord del club",
                    "Vas a por la historia. Sales al campo pensando en goles, no en el equipo. A veces la ambición personal tiene un precio.",
                    0, 0, 3, 0, -1, 0, 3, 0, -1, -1, 1, 2, 0, 0);

// D002 — Mala racha sin marcar
            Event d002 = footballDataService.createEvent(
                    "Ocho partidos sin ver portería",
                    "Ocho partidos. Cero goles. Cero asistencias. La grada ya murmura. El míster te mantiene pero en rueda de prensa le preguntan si va a sentarte. Su respuesta: 'Confío en él. Pero la confianza no es infinita.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d002,
                    "Quedarte a entrenar tiros después de cada sesión",
                    "Tres semanas de disparos extra después de cada entrenamiento. El portero reserva te odia, pero tu confianza vuelve poco a poco.",
                    0, 0, 2, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(d002,
                    "Cambiar tu juego: menos gol, más asociación",
                    "Dejas de obsesionarte con el gol y empiezas a buscar a los compañeros. Dos asistencias en los siguientes tres partidos. El gol llegará.",
                    0, 1, 0, 0, 0, 2, 1, 0, 1, 2, 0, 0, 0, 0);
            footballDataService.createOption(d002,
                    "Hablar con el psicólogo deportivo",
                    "Una sesión de una hora donde no hablas de fútbol ni una vez. Sales extrañamente aliviado. El siguiente sábado marcas un doblete.",
                    0, 0, 1, 0, 3, 0, 1, -100, 1, 0, 0, 0, 0, 0);

// D003 — El derbi
            Event d003 = footballDataService.createEvent(
                    "Día de derbi",
                    "El vestuario vibra. El capitán no necesita dar charla porque la tensión se corta con un cuchillo. Los ultras llevan cantando desde las siete de la mañana. El árbitro pita y el campo ruge. ¿Cómo entras?",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d003,
                    "Enchufado desde el primer minuto, sin dar una por perdida",
                    "Primera jugada, primera entrada. El rival ya sabe que estás ahí. El derbi se juega con las tripas y hoy las tuyas hablan alto.",
                    1, 0, 0, 1, 0, 0, 2, 0, 1, 1, 3, 1, 0, 0);
            footballDataService.createOption(d003,
                    "Jugar con cabeza fría y esperar tu momento",
                    "Mientras todos se calientan, tú juegas como un veterano. Tocas, te desmarques, esperas. En el minuto 70 apareces donde nadie te espera.",
                    0, 1, 1, 0, 2, 1, 2, 0, 2, 0, 2, 1, 0, 0);

// D004 — Expulsado por protestar
            Event d004 = footballDataService.createEvent(
                    "Roja directa",
                    "El árbitro pita una falta absurda en contra tuya. Se te va la boca. Dos palabras de más. Roja directa. El vestuario se queda en silencio cuando entras. El míster ni te mira.",
                    "deportivo", "informativo", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d004,
                    "Pedir disculpas al míster y al equipo",
                    "Al día siguiente, delante de todo el vestuario: 'Lo siento, he perjudicado al equipo.' El capitán asiente. El míster dice: 'Que no se repita.' Y se acabó.",
                    0, 0, 0, 0, 1, 0, -2, 0, 1, 1, 0, 0, 0, 0);
            footballDataService.createOption(d004,
                    "Justificarte diciendo que la falta era inventada",
                    "'Es que el árbitro se la ha inventado, míster.' El entrenador te clava la mirada: 'Me da igual. Un profesional no hace eso.' La relación se enfría.",
                    0, 0, 0, 0, 0, 0, -3, 0, -2, -1, -1, -2, 0, 0);

// D005 — Primer gol con el equipo
            Event d005 = footballDataService.createEvent(
                    "¡GOOOL! El primero con esta camiseta",
                    "Centro desde la banda, el balón te llega solo, enganchas de primeras y la red se mueve. Lo siguiente que recuerdas es a cinco compañeros encima tuyo gritándote al oído. El estadio corea tu nombre por primera vez.",
                    "deportivo", "informativo", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d005,
                    "Celebrarlo con la grada",
                    "Corres hacia la grada, te deslizas de rodillas y señalas al cielo. La afición te adopta en ese instante.",
                    0, 0, 0, 0, 0, 0, 3, 0, 0, 1, 4, 2, 0, 0);
            footballDataService.createOption(d005,
                    "Celebrarlo con tus compañeros",
                    "Corres hacia el banquillo y abrazas a todos, incluido el último suplente. El vestuario te quiere un poco más después de eso.",
                    0, 0, 0, 0, 0, 0, 2, 0, 1, 3, 1, 0, 0, 0);

// D006 — Entrando desde el banquillo
            Event d006 = footballDataService.createEvent(
                    "Hoy empiezas en el banquillo",
                    "El once sale del vestuario y tú no estás. Te sientas en el banquillo con el chándal puesto. En el minuto 60, con el partido 0-0, el míster te mira: 'Calienta. Tienes media hora para convencerme.'",
                    "deportivo", "decision", "common", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d006,
                    "Salir a comerte el mundo en 30 minutos",
                    "Entras enchufado. Primer balón, regate. Segundo balón, disparo. El campo nota que has salido con hambre.",
                    1, 1, 0, 0, 0, 0, 2, 0, 2, 1, 2, 1, 0, 0);
            footballDataService.createOption(d006,
                    "Jugar inteligente, sin prisa",
                    "No hace falta ser el héroe. Tocas bien, te colocas mejor, y ayudas al equipo a ganar. El míster lo valora más de lo que crees.",
                    0, 0, 0, 0, 1, 1, 1, 0, 2, 1, 0, 0, 0, 0);

// D007 — Lesión leve en partido
            Event d007 = footballDataService.createEvent(
                    "Un golpe en la rodilla",
                    "Minuto 35. Choque con un defensa. Te duele la rodilla. El fisio entra al campo, te la palpa y dice: 'Puedes seguir, pero si te la vuelven a dar, podrías estar un mes fuera.' Quedan 55 minutos.",
                    "deportivo", "decision", "common", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d007,
                    "Seguir jugando y arriesgarte",
                    "Aprietas los dientes y sigues. Cada carrera duele, pero acabas el partido. Al día siguiente la rodilla está como una sandía. Una semana de reposo.",
                    0, 0, 0, -1, 1, 0, 1, 0, 2, 1, 1, 0, 0, 0);
            footballDataService.createOption(d007,
                    "Pedir el cambio y ser precavido",
                    "Levantas la mano y pides el cambio. El míster asiente. No es heroico, pero al lunes estás entrenando con normalidad.",
                    0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// D008 — Hat-trick
            Event d008 = footballDataService.createEvent(
                    "Llevas dos goles y queda media hora",
                    "Dos goles en 60 minutos. La grada ya grita tu nombre. El hat-trick está ahí. El míster te mira desde la banda pero no te cambia. El capitán te pasa el balón cada vez que puede.",
                    "deportivo", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d008,
                    "Ir a por el tercero con todo",
                    "Cada balón que te llega, disparas. En el 78, un centro desde la derecha te encuentra solo en el área. Cabezazo. Red. HAT-TRICK. Te llevas el balón a casa.",
                    0, 0, 3, 0, 1, 0, 6, 0, 1, 1, 5, 4, 0, 0);
            footballDataService.createOption(d008,
                    "Jugar para el equipo y que pase lo que pase",
                    "En el 78 tienes la oportunidad, pero ves a un compañero mejor colocado. Le pasas. Él marca. El hat-trick se queda en doblete, pero el equipo gana 4-0.",
                    0, 0, 0, 0, 1, 2, 2, 0, 3, 3, 1, 0, 0, 0);

// D009 — Penalti en partido importante
            Event d009 = footballDataService.createEvent(
                    "Penalti y todo en juego",
                    "Minuto 85. El equipo va 1-2 abajo en un partido clave. Penalti. El lanzador habitual ha sido sustituido. El capitán mira alrededor. El balón está en el punto y alguien tiene que cogerlo.",
                    "deportivo", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d009,
                    "Coger el balón y lanzar",
                    "Lo coges con las dos manos. Silencio. Carrera. Gol. Empate. El estadio explota. Tú te quedas de rodillas mirando al cielo.",
                    0, 0, 3, 0, 3, 0, 5, 0, 2, 2, 4, 3, 0, 0);
            footballDataService.createOption(d009,
                    "Dejar que lo tire otro con más experiencia",
                    "Se lo ofreces al veterano del equipo. Él marca. El estadio canta su nombre. No el tuyo.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0);

// D010 — Asistencia de fantasía
            Event d010 = footballDataService.createEvent(
                    "Una jugada que se te ocurre a ti solo",
                    "Recibes de espaldas, te giras, ves un pasillo que nadie más ve. Un pase de 30 metros entre tres defensas que deja a tu compañero solo ante el portero. Marca. El estadio aplaude el gol. Pero los que entienden de fútbol aplauden tu pase.",
                    "deportivo", "informativo", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d010,
                    "Celebrarlo como un gol propio",
                    "Corres a celebrar con tu compañero. Él te señala reconociendo que el mérito es tuyo. La prensa titula: 'El pase del año.'",
                    0, 1, 0, 0, 0, 3, 3, 0, 1, 2, 2, 3, 0, 0);
            footballDataService.createOption(d010,
                    "Volver a tu posición sin aspavientos",
                    "Un aplauso discreto y a seguir. Los compañeros saben lo que has hecho. No hace falta gritarlo.",
                    0, 0, 0, 0, 1, 1, 1, 0, 2, 2, 0, 0, 0, 0);

// D011 — Te pitan titular por primera vez
            Event d011 = footballDataService.createEvent(
                    "Hoy sales de inicio",
                    "El míster pone el once en la pizarra. Tu nombre está ahí. Primera titularidad oficial. Algunos compañeros te dan una palmada en el hombro. El capitán te mira: 'No te me cagues, ¿eh?' Sonríe al decirlo.",
                    "deportivo", "informativo", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d011,
                    "Salir concentrado y dar tu mejor versión",
                    "90 minutos de ida y vuelta. No marcas, no asistes, pero tampoco pierdes un solo balón. El míster te da la mano al acabar: 'Bien. Muy bien.'",
                    0, 0, 0, 0, 1, 0, 2, 0, 3, 1, 1, 0, 0, 0);
            footballDataService.createOption(d011,
                    "Intentar demostrar que mereces estar siempre ahí",
                    "Intentas hacer de todo: regates, disparos, pases largos. Unas salen, otras no. El míster te mira con esa cara de 'relájate, chaval.'",
                    0, 1, 1, 0, -1, 0, 1, 0, 0, 0, 1, 1, 0, 0);

// D012 — Gol en el descuento
            Event d012 = footballDataService.createEvent(
                    "Minuto 93 y pierdes 0-1",
                    "El cuarto árbitro levanta la pantalla: 4 minutos de descuento. El equipo va 0-1. Saque de esquina. Todo el equipo sube, incluido el portero. El córner viene al segundo palo. Te llega a ti.",
                    "deportivo", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d012,
                    "Cabezazo con todo",
                    "Saltas más alto que nunca. El balón rebota en la red. GOOOL. El estadio se viene abajo. Eres el héroe del minuto 93.",
                    0, 0, 2, 1, 2, 0, 6, 0, 2, 2, 5, 3, 0, 0);
            footballDataService.createOption(d012,
                    "Dejar pasar para el compañero que viene detrás",
                    "Peinas el balón de cabeza. Tu compañero empuja a gol. Empate. Él es el héroe. Tú el asistente invisible.",
                    0, 0, 0, 0, 0, 1, 1, 0, 1, 2, 1, 0, 0, 0);

// D013 — Cambio táctico del entrenador
            Event d013 = footballDataService.createEvent(
                    "El míster cambia el sistema",
                    "Descanso. El equipo va 0-0 y no crea ni media ocasión. El míster borra la pizarra y dibuja una formación nueva: 'Vamos a cambiarlo todo. Tú juegas más adelantado.' Tu posición cambia respecto a lo habitual.",
                    "deportivo", "decision", "common", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d013,
                    "Adaptarte sin rechistar al nuevo puesto",
                    "No es donde sueles jugar, pero funcionas. El equipo mejora. Al final del partido el míster te señala: 'Ves, para eso sirve un jugador inteligente.'",
                    0, 0, 0, 0, 1, 1, 1, 0, 3, 1, 0, 0, 0, 0);
            footballDataService.createOption(d013,
                    "Decirle que rindes más en tu posición natural",
                    "El míster frunce el ceño: 'Mira, hoy necesito que juegues ahí. Si no te gusta, te siento.' Aceptas a regañadientes.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0);

// D014 — Partido contra tu ex-equipo
            Event d014 = footballDataService.createEvent(
                    "Hoy juegas contra tu ex-equipo",
                    "El calendario lo quiso así. Hoy te toca enfrentarte al club que te formó. En la grada hay gente que te vio debutar. Algunos te aplaudirán cuando salgas al campo. Otros no. Depende de cómo te fuiste.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d014,
                    "Saludar a tus ex-compañeros y jugar con respeto",
                    "Abrazos en el calentamiento, buen rollo antes del pitido. Cuando marcas, no celebras. La grada rival te aplaude de pie.",
                    0, 0, 0, 0, 1, 0, 3, 0, 0, 1, 3, 3, 0, 0);
            footballDataService.createOption(d014,
                    "Jugar a muerte como contra cualquier otro",
                    "Entras a competir como si fuera un desconocido. Marcas y celebras. Tu nueva afición te adora. La antigua te silba.",
                    0, 0, 1, 0, 0, 0, 2, 0, 1, 0, 2, -1, 0, 0);

// D015 — Fallo garrafal
            Event d015 = footballDataService.createEvent(
                    "El fallo que nadie olvida",
                    "Solo ante el portero. Estadio de pie. La grada ya celebra. Chut. Al poste. Rebote. Fuera. El silencio dura tres segundos y pesa tres toneladas. El míster se lleva las manos a la cabeza. Los compañeros miran al suelo.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d015,
                    "Apretar los dientes y seguir pidiendo el balón",
                    "Cometes el error de tu vida y diez minutos después vuelves a pedir el balón. El fallo no te define. La reacción, sí.",
                    0, 0, 0, 0, 3, 0, 1, 0, 2, 2, 1, 0, 0, 0);
            footballDataService.createOption(d015,
                    "Te hundes durante el resto del partido",
                    "Dejas de pedir el balón. Te escondes. El míster te sustituye en el 70 con una palmada en la espalda que no consuela.",
                    0, 0, -1, 0, -2, 0, -1, 0, -1, -1, -1, -1, 0, 0);

// D016 — Convocado para la selección
            Event d016 = footballDataService.createEvent(
                    "Lista de convocados: tu nombre está",
                    "Estás viendo las redes sociales cuando salta la notificación: la lista de convocados de la selección absoluta. Tu nombre. Ahí. Entre los 23. Tu teléfono no para de sonar. Tu madre llora. Tu padre se queda callado cinco segundos y luego dice: 'Joder.'",
                    "deportivo", "unico", "epic", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d016,
                    "Ir con los pies en la tierra",
                    "Llegas a la concentración como uno más. Escuchas, entrenas y aprendes. No dices una palabra más alta que otra. El seleccionador lo nota.",
                    0, 0, 0, 0, 2, 0, 5, 0, 0, 2, 3, 3, 0, 0);
            footballDataService.createOption(d016,
                    "Llegar decidido a hacerte un hueco",
                    "No has venido a hacer turismo. Cada entrenamiento es una prueba. Cada rondo, una oportunidad. Quieres que la próxima lista sea obvia.",
                    0, 1, 1, 0, 0, 0, 4, 0, 1, 0, 2, 3, 0, 0);

// D017 — Suplente tres partidos seguidos
            Event d017 = footballDataService.createEvent(
                    "Tres partidos mirando desde el banquillo",
                    "Tercer partido consecutivo sin jugar. El culo ya conoce cada centímetro del banquillo. El míster no te da explicaciones. El que juega en tu puesto no lo está haciendo mal. Pero tampoco brillante.",
                    "deportivo", "decision", "common", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d017,
                    "Hablar con el míster y preguntarle qué necesitas",
                    "'Míster, ¿qué tengo que hacer para jugar?' El entrenador te mira: 'Exactamente lo que estás haciendo. Entrenar bien y esperar tu oportunidad.'",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d017,
                    "Reventar cada entrenamiento para demostrar que mereces jugar",
                    "Si no juegas el sábado, al menos que el míster tenga claro que se equivoca no poniéndote. Entrenas como un animal toda la semana.",
                    0, 1, 0, 1, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0);
            footballDataService.createOption(d017,
                    "Aceptarlo y seguir profesional sin quejarte",
                    "El fútbol es así. Hoy no juegas, mañana sí. Lo importante es estar listo cuando llegue el momento.",
                    0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0);

// D018 — Gol en tu cumpleaños
            Event d018 = footballDataService.createEvent(
                    "Gol el día de tu cumpleaños",
                    "Casualidades del calendario: hoy es tu cumpleaños y hay partido. En el minuto 22, te llega un balón suelto en el área y la mandas dentro. El marcador electrónico pone tu nombre y la grada canta el cumpleaños feliz. No hay guion mejor.",
                    "deportivo", "informativo", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d018,
                    "Hacer el gesto de soplar las velas en la celebración",
                    "Te pones de rodillas y soplas una vela imaginaria. La imagen se hace viral. Tu representante te llama: 'Chaval, esto es oro.'",
                    0, 0, 0, 0, 0, 0, 4, 0, 0, 1, 4, 3, 2, 0);
            footballDataService.createOption(d018,
                    "Dedicárselo a tu familia en la grada",
                    "Señalas a la grada donde están tus padres. Tu madre se tapa la cara. Tu padre levanta el puño. Hay cosas que valen más que cualquier contrato.",
                    0, 0, 0, 0, 1, 0, 3, 0, 0, 1, 3, 1, 0, 0);

// D019 — Jugada polémica a tu favor
            Event d019 = footballDataService.createEvent(
                    "¿Fue fuera de juego o no?",
                    "Gol tuyo. El estadio celebra. Pero las repeticiones muestran que estabas en fuera de juego por un pie. El VAR revisa. Espera. Espera. GOL VÁLIDO. El estadio rival estalla. La prensa te preguntará después del partido.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d019,
                    "Decir en rueda de prensa que no lo has visto claro",
                    "'La verdad es que no sé si estaba en fuera de juego o no. Para eso está el VAR.' Respuesta elegante. La prensa te respeta.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 3, 0, 0);
            footballDataService.createOption(d019,
                    "Decir que era gol legal y punto",
                    "'El VAR ha dicho que sí. No hay más que hablar.' Corto y directo. A algunos les gusta, a otros no.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0);
            footballDataService.createOption(d019,
                    "Reconocer que probablemente era fuera de juego",
                    "'Mira, te soy sincero, creo que estaba fuera. Pero el árbitro ha decidido y yo respeto la decisión.' La honestidad te gana puntos con todo el mundo.",
                    0, 0, 0, 0, 0, 0, 2, 0, 1, 1, 1, 4, 0, 0);

// D020 — Partido de tu vida
            Event d020 = footballDataService.createEvent(
                    "La noche perfecta",
                    "Hay partidos y HAY partidos. Este es de los segundos. Todo te sale. Cada control, cada pase, cada disparo. El campo se mueve a tu ritmo. Dos goles, una asistencia y una ovación del estadio entero cuando el míster te sustituye en el 80. Te levantas del banquillo y todo el campo se pone en pie. Esto es lo que soñabas cuando eras un crío.",
                    "deportivo", "informativo", "epic", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d020,
                    "Disfrutarlo sabiendo que noches así hay pocas",
                    "Te sientas en el banquillo con una sonrisa tonta que no puedes quitarte. El míster te da una palmada: 'Ojalá jugaras así cada semana.' Tú piensas: ojalá.",
                    1, 1, 1, 0, 2, 1, 6, 0, 3, 3, 5, 4, 1, 1);
            footballDataService.createOption(d020,
                    "Tomarlo como el estándar de aquí en adelante",
                    "Esto no es una excepción. Esto es tu nuevo mínimo. A partir de hoy, este es el nivel. No aceptas menos de ti mismo.",
                    0, 0, 0, 0, 0, 0, 4, 0, 1, 0, 3, 2, 0, 0);


            // ===== EVENTOS DEPORTIVOS — SEGUNDA TANDA (D021-D050) =====
// Pegar en DataInitializer junto a los D001-D020

// D021 — Autogol
            Event d021 = footballDataService.createEvent(
                    "La has metido en tu propia portería",
                    "Centro desde la banda. Intentas despejar de cabeza y la mandas al fondo de tu propia red. El estadio se queda helado. Tu portero te mira. El míster se sienta en el banquillo. El marcador cambia. Quedan 40 minutos por delante y un nudo en el estómago que no se va.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d021,
                    "Levantar la mano, disculparte y seguir jugando",
                    "Pides perdón al portero y te pones a correr como si no hubiera pasado. El míster asiente desde la banda. La cagada no te define. Lo que haces después, sí.",
                    0, 0, 0, 0, 2, 0, -1, 0, 1, 1, 0, 0, 0, 0);
            footballDataService.createOption(d021,
                    "Hundirte y empezar a esconderte del balón",
                    "Cada vez que te llega la pelota la sueltas rápido como si quemara. El míster te cambia en el 60. En la ducha te quedas diez minutos mirando la pared.",
                    0, 0, 0, 0, -2, 0, -2, 0, -1, -1, -1, 0, 0, 0);

// D022 — El VAR te anula un golazo
            Event d022 = footballDataService.createEvent(
                    "Gol anulado por el VAR",
                    "Volea desde fuera del área. El balón entra por la escuadra. Corres a celebrarlo. Dos minutos de espera mirando la pantalla. El árbitro se lleva la mano al auricular. Hace el rectángulo con las manos. Gol anulado. Fuera de juego en el inicio de la jugada. Te quedas con la celebración a medias.",
                    "deportivo", "decision", "common", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d022,
                    "Aceptarlo y seguir a lo tuyo",
                    "Respiras hondo, te colocas y esperas el saque. El gol vendrá. El VAR no puede anularlos todos.",
                    0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d022,
                    "Protestar al cuarto árbitro",
                    "Te acercas a la banda gesticulando. El cuarto árbitro ni te mira. Te llevas una amarilla que no necesitabas.",
                    0, 0, 0, 0, -1, 0, -1, 0, -1, 0, 0, -1, 0, 0);

// D023 — Gol de falta directa
            Event d023 = footballDataService.createEvent(
                    "Falta al borde del área",
                    "Falta a 20 metros. El lanzador habitual se acerca al balón, pero tú sientes que hoy es tu día. La barrera se coloca. El portero grita instrucciones. El estadio espera.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d023,
                    "Pedir la falta y lanzar",
                    "La colocas en el ángulo largo. El portero vuela pero no llega. Golazo. Hasta los rivales aplauden. El lanzador habitual no está contento, pero el marcador sí.",
                    0, 0, 3, 0, 1, 0, 4, 0, -1, 0, 3, 3, 0, 0);
            footballDataService.createOption(d023,
                    "Dejar que lance el especialista",
                    "No es tu momento para egos. Le dejas. Lanza... y la mete. Celebráis juntos. A veces lo inteligente es saber cuándo no actuar.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0);

// D024 — Te pisan adrede
            Event d024 = footballDataService.createEvent(
                    "El defensa rival te tiene fichado",
                    "Llevas tres faltas recibidas del mismo defensa. En la cuarta, te pisa el tobillo con los tacos cuando el balón ya no está. No es una falta normal. Es un mensaje. El árbitro pita pero no saca tarjeta. El defensa te susurra: 'La próxima va sin balón.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d024,
                    "No entrar al trapo y ganarle con fútbol",
                    "La mejor venganza es un caño y un gol. Le miras mientras celebras. Él baja la cabeza.",
                    0, 1, 0, 0, 2, 0, 3, 0, 1, 0, 2, 2, 0, 0);
            footballDataService.createOption(d024,
                    "Encararle y decirle que se deje de juego sucio",
                    "Te acercas y le dices cuatro cosas bien dichas. El árbitro os separa. Amarilla para los dos. Pero el mensaje ha quedado claro.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -1, 0, 0);
            footballDataService.createOption(d024,
                    "Decírselo al árbitro y pedir que le vigile",
                    "Te acercas al colegiado y le explicas que te está cazando. El árbitro le avisa. La siguiente falta del defensa es amarilla directa.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0);

// D025 — Tanda de penaltis
            Event d025 = footballDataService.createEvent(
                    "Tanda de penaltis. Eliminatoria.",
                    "Final del partido. Empate. Prórroga agónica. Y ahora todo se decide desde los once metros. El míster escribe la lista de lanzadores en un papel. Tu nombre está. Eres el cuarto. Los tres primeros de tu equipo marcan. Los del rival también. Te toca.",
                    "deportivo", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d025,
                    "Tirar con potencia al centro",
                    "Si el portero se tira, la metes. Si se queda... también. Carrera larga. Cañonazo al centro. Gol. El portero estaba volando hacia la izquierda.",
                    0, 0, 2, 0, 2, 0, 3, 0, 0, 1, 2, 1, 0, 0);
            footballDataService.createOption(d025,
                    "Colocarla con sutileza a un lado",
                    "Carrera corta, toque suave al palo largo. El portero adivina el lado pero no llega. Entra justa. Respiras.",
                    0, 1, 2, 0, 1, 0, 3, 0, 0, 0, 2, 1, 0, 0);

// D026 — Cambio de entrenador
            Event d026 = footballDataService.createEvent(
                    "Han echado al míster",
                    "Llegas al entrenamiento y hay un corro en el vestuario. El míster ha sido destituido. En su lugar hay un tío que no conoces con carpeta nueva y cara de pocos amigos. El primer discurso del nuevo: 'No conozco a nadie. Todo el mundo empieza de cero. El que quiera jugar, que me lo demuestre.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d026,
                    "Adaptarte al nuevo sistema y ganarte su confianza",
                    "Escuchas, obedeces y entrenas como el primer día. El nuevo míster tarda dos semanas en fiarse de ti, pero cuando lo hace, eres titular indiscutible.",
                    0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0);
            footballDataService.createOption(d026,
                    "Echar de menos al anterior y mostrar resistencia al cambio",
                    "Las cosas ya no son como antes. El nuevo sistema no te convence. Se nota en el campo y el nuevo entrenador te lo nota a ti.",
                    0, 0, 0, 0, 0, 0, 0, 0, -2, -1, 0, 0, 0, 0);

// D027 — Perder una final
            Event d027 = footballDataService.createEvent(
                    "Final perdida",
                    "Has llegado a la final. Todo el mundo esperaba que ganaras. Y has perdido. 0-1 con un gol en el descuento. El trofeo se queda en la otra vitrina. Mientras el rival celebra, tú te quedas sentado en el césped mirando al vacío. El vestuario huele a derrota.",
                    "deportivo", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d027,
                    "Felicitar al rival y prometer volver",
                    "Te acercas al capitán rival, le das la mano y le dices: 'Enhorabuena.' Luego entras al vestuario y te juras que no vas a volver a sentir esto.",
                    0, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0, 2, 0, 0);
            footballDataService.createOption(d027,
                    "Irte al vestuario sin hablar con nadie",
                    "No tienes ganas de protocolo. Cabeza baja, ducha y autobús. Mañana será otro día. Hoy no.",
                    0, 0, 0, 0, -1, 0, -1, 0, 0, -1, -1, 0, 0, 0);
            footballDataService.createOption(d027,
                    "Quedarte en el campo hasta que apaguen las luces",
                    "Te sientas en el banquillo vacío mientras el estadio se desaloja. Solo. Con el dolor. A veces hay que tragárselo entero para que no te vuelva a ganar.",
                    0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// D028 — Ganar tu primer título
            Event d028 = footballDataService.createEvent(
                    "¡CAMPEONES!",
                    "Pitido final. 2-0. Has ganado tu primer título. El estadio estalla. Los compañeros te agarran, te levantan, te tiran al aire. El capitán te planta la copa en las manos: 'Levántala tú, te la has ganado.' Tiemblas. La levantas. El flash de cien cámaras te ciega.",
                    "deportivo", "unico", "epic", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d028,
                    "Disfrutarlo como un niño que acaba de cumplir su sueño",
                    "Corres por el campo con la copa como un loco. Te la pones en la cabeza. Te sacas fotos con todo el mundo. Tu madre llora en la grada. Esto es por lo que empezaste a jugar al fútbol.",
                    0, 0, 0, 0, 0, 0, 8, 200, 2, 3, 5, 4, 1, 2);
            footballDataService.createOption(d028,
                    "Celebrarlo con el equipo sin acaparar protagonismo",
                    "Te fundes en un abrazo con el capitán, con el suplente que no ha jugado ni un minuto, con el fisio, con el utillero. Esto no es de uno, es de todos.",
                    0, 0, 0, 0, 1, 0, 6, 200, 3, 5, 3, 3, 0, 2);

// D029 — Victoria remontando
            Event d029 = footballDataService.createEvent(
                    "Remontada épica",
                    "Ibais 0-2 en el descanso. El vestuario era un funeral. El míster ha pegado tres gritos, ha cambiado la táctica y habéis salido a la segunda parte como poseídos. 1-2 en el 60. 2-2 en el 75. Queda un cuarto de hora. ¿Se puede?",
                    "deportivo", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d029,
                    "Pedir el balón y liderar el ataque final",
                    "Te pones al mando. Cada jugada pasa por ti. En el 88, un regateo imposible y un pase al hueco. Gol del compañero. 3-2. El estadio se cae. Literalmente.",
                    0, 1, 0, 0, 2, 1, 5, 0, 2, 3, 4, 3, 0, 0);
            footballDataService.createOption(d029,
                    "Defender el empate y evitar que nos hagan el tercero",
                    "Con 2-2, una derrota sería catastrófica. Te repliegas, ayudas atrás, y aseguras que al menos te llevas un punto. El punto que salva la temporada.",
                    0, 0, 0, 0, 1, 0, 1, 0, 2, 1, 0, 0, 0, 0);

// D030 — Tu compañero te roba un gol cantado
            Event d030 = footballDataService.createEvent(
                    "Te han robado un gol en la línea",
                    "Regateas al portero, el gol está cantado, solo tienes que empujarla a la red vacía... y un compañero aparece por detrás y la mete él. Se lleva la celebración. El gol es suyo. Tú te quedas con las manos en la cabeza y la boca abierta.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d030,
                    "Tragártelo y celebrar con él",
                    "Le das un abrazo. Por fuera sonríes. Por dentro te hierve la sangre. Pero eres profesional. Y el equipo ha marcado.",
                    0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0);
            footballDataService.createOption(d030,
                    "Decirle después del partido que eso no se hace",
                    "En el vestuario le dices: 'Tío, eso era mío y lo sabes.' Él se disculpa a medias. La tensión queda ahí unos días.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0);

// D031 — Partido bajo lluvia torrencial
            Event d031 = footballDataService.createEvent(
                    "Diluvio universal en el campo",
                    "Llueve como si el cielo se hubiera roto. El campo es un barrizal. El balón no bota, los tacos resbalan y la visibilidad es de diez metros. El árbitro decide seguir. Hay que jugar en estas condiciones.",
                    "deportivo", "decision", "common", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d031,
                    "Juego directo, balones largos y físico",
                    "Te adaptas al barro. Olvídate de los regates, hoy es día de correr y pelear. El que más barro tenga en la camiseta al final, gana.",
                    1, 0, 0, 2, 0, 0, 1, 0, 2, 1, 1, 0, 0, 0);
            footballDataService.createOption(d031,
                    "Intentar mantener el toque pese al barro",
                    "Te niegas a jugar a pelotazo. Sigues tocando. El balón se para, se frena, se queda corto... pero cuando llega, llega limpio. El míster te mira con asombro.",
                    0, 2, 0, 0, 1, 0, 2, 0, 1, 0, 0, 0, 0, 0);

// D032 — Debut en Champions
            Event d032 = footballDataService.createEvent(
                    "Noche de Champions",
                    "La música suena. La del himno de Champions. Esa que has oído mil veces por la tele y que ahora suena EN EL CAMPO, CON LAS DOS FILAS DE JUGADORES, y tú estás en una de ellas. Se te pone la piel de gallina. El balón del partido es diferente. Las estrellas en los paneles publicitarios brillan. Esto ya no es fútbol normal.",
                    "deportivo", "unico", "epic", "profesional", 20, 38, 3, 3, null
            );
            footballDataService.createOption(d032,
                    "Disfrutar el momento pero mantener la concentración",
                    "Te permites tres segundos de emoción durante el himno. Después te pones en modo partido. Juegas como si fuera tu liga, pero con un brillo especial en los ojos.",
                    0, 0, 0, 0, 2, 0, 5, 0, 1, 1, 2, 3, 0, 0);
            footballDataService.createOption(d032,
                    "Salir a demostrar que estás a la altura",
                    "Europa tiene que saber tu nombre. Sales enchufado, buscas el balón, te ofreces. La Champions no perdona, pero tú tampoco.",
                    0, 1, 1, 0, 0, 0, 4, 0, 1, 0, 3, 3, 0, 0);

// D033 — Partido contra tu ídolo
            Event d033 = footballDataService.createEvent(
                    "Hoy juegas contra tu ídolo de la infancia",
                    "Tenías su póster en la habitación. Tenías sus botas. Aprendiste a chutar viéndole por la tele. Y ahora está en el túnel de vestuarios a tres metros de ti, poniéndose los guantes. Hoy juegas contra él.",
                    "deportivo", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d033,
                    "Pedirle la camiseta antes del partido",
                    "Te acercas en el túnel: 'Oye, soy muy fan tuyo desde crío. ¿Me cambias la camiseta al final?' Él sonríe: 'Claro, chaval.' Ya has ganado algo hoy pase lo que pase.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0);
            footballDataService.createOption(d033,
                    "Tratarle como un rival más y competir",
                    "El póster ya no está en tu habitación. Hoy es un rival. Y tú has venido a ganar.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0);

// D034 — Te silban en tu propio campo
            Event d034 = footballDataService.createEvent(
                    "Tu propia afición te silba",
                    "Llevas tres partidos sin hacer nada relevante. Hoy, cuando recibes el balón en el minuto 20, se oye un murmullo de silbidos desde la grada. Tu grada. Tu gente. El capitán se acerca: 'Pasa de ellos. Juega.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d034,
                    "Demostrarles que se equivocan en el campo",
                    "Cierras los oídos y abres las piernas. Una jugada, un regate, un centro perfecto. Los silbidos se convierten en aplausos. Nada sabe mejor que callar bocas con fútbol.",
                    0, 1, 0, 0, 2, 0, 2, 0, 0, 0, 2, 1, 0, 0);
            footballDataService.createOption(d034,
                    "Hundirte y pasar desapercibido el resto del partido",
                    "Los silbidos te meten en un agujero del que no sales en todo el partido. El míster te cambia en el 60. La afición aplaude al sustituto. Eso duele más.",
                    0, 0, 0, 0, -2, 0, -2, 0, 0, -1, -2, -1, 0, 0);

// D035 — Gol de chilena
            Event d035 = footballDataService.createEvent(
                    "El centro viene alto y tú de espaldas",
                    "Centro perfecto desde la derecha. Te llega a la altura del pecho pero estás de espaldas a la portería. Tienes dos opciones: bajarla de pecho y girarte, o intentar lo que todo futbolista sueña con hacer alguna vez.",
                    "deportivo", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d035,
                    "Intentar la chilena",
                    "Saltas, te elevas, conectas con el empeine. El balón vuela. El tiempo se para. Red. GOLAZO. El estadio entero se pone de pie. Ese gol va a salir en todas las portadas.",
                    0, 0, 3, 0, 1, 0, 8, 0, 0, 1, 5, 5, 2, 1);
            footballDataService.createOption(d035,
                    "Bajarla y jugar seguro",
                    "Control de pecho, giro y disparo raso. Gol. No es espectacular pero está dentro. A veces lo práctico es lo correcto.",
                    0, 1, 1, 0, 0, 0, 2, 0, 1, 0, 1, 1, 0, 0);

// D036 — Debut con la selección
            Event d036 = footballDataService.createEvent(
                    "Tu primer partido con la selección",
                    "El estadio entero canta el himno. Tú intentas cantar pero la emoción te cierra la garganta. La camiseta de tu país pesa diferente. Miras a los compañeros: jugadores que veías por la tele hace dos años están a tu lado. El árbitro pita. Respira.",
                    "deportivo", "unico", "epic", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d036,
                    "Jugar con humildad, estar al servicio del equipo",
                    "No eres la estrella aquí. Eres el nuevo. Corres, te ofreces, haces lo que el seleccionador te pide. Sales en el 70 con una ovación. El seleccionador te da la mano: 'Volverás.'",
                    0, 0, 0, 0, 2, 0, 5, 0, 0, 2, 2, 3, 0, 0);
            footballDataService.createOption(d036,
                    "Intentar dejar tu sello desde el primer minuto",
                    "Has venido a quedarte. Cada balón que tocas es un mensaje. Un pase de 40 metros, un desmarque al espacio, un disparo que sale rozando el poste. El seleccionador toma nota.",
                    0, 1, 1, 0, 0, 0, 4, 0, 1, 0, 2, 3, 0, 0);

// D037 — Gol con la selección
            Event d037 = footballDataService.createEvent(
                    "Gol con tu selección",
                    "Recibes en el área. Controlas. Recortas al defensa. Disparas. Gol. Has marcado con tu selección. El estadio ruge. Tus compañeros te agarran. Tu padre, en algún sitio, está llorando sin hacer ruido.",
                    "deportivo", "informativo", "epic", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d037,
                    "Señalar al escudo con orgullo",
                    "Te tocas el escudo de la camiseta y lo besas. Ese gol no es tuyo. Es de todo un país.",
                    0, 0, 0, 0, 0, 0, 7, 0, 0, 1, 4, 4, 0, 0);
            footballDataService.createOption(d037,
                    "Dedicárselo a tu familia",
                    "Miras a cámara y dibujas un corazón con las manos. Tu madre, que está viéndolo por televisión a 3000 kilómetros, rompe a llorar.",
                    0, 0, 0, 0, 1, 0, 5, 0, 0, 1, 3, 2, 0, 0);

// D038 — Te señalan tras una derrota
            Event d038 = footballDataService.createEvent(
                    "El equipo pierde y te señalan a ti",
                    "Derrota dura. 0-3 en casa. La prensa necesita un culpable y te ha tocado a ti. Los titulares de mañana dirán tu nombre con letras grandes. El míster intenta protegerte: 'La derrota es de todos.' Pero el daño ya está hecho.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d038,
                    "Asumir tu parte y no buscar excusas",
                    "'He jugado mal. No tengo excusa. La próxima semana será diferente.' La prensa se sorprende de tu honestidad. El vestuario te respeta más.",
                    0, 0, 0, 0, 2, 0, -1, 0, 1, 2, 0, 3, 0, 0);
            footballDataService.createOption(d038,
                    "Defenderte y repartir la culpa",
                    "'No he jugado solo. Esto es de los once y del banquillo.' Técnicamente tienes razón. Pero el vestuario nota que estás esquivando balas.",
                    0, 0, 0, 0, 0, 0, -2, 0, -1, -1, 0, -1, 0, 0);

// D039 — Ovación del campo rival
            Event d039 = footballDataService.createEvent(
                    "El campo rival te aplaude",
                    "Has jugado un partidazo fuera de casa. Has marcado dos goles y has dado una asistencia. Cuando el míster te sustituye en el 80, pasa algo que no esperabas: la afición RIVAL se pone en pie y te aplaude. El estadio entero.",
                    "deportivo", "informativo", "rare", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d039,
                    "Aplaudir de vuelta con respeto",
                    "Levantas las manos y aplauds a la grada. Es un gesto de respeto mutuo. Eso solo pasa cuando has hecho algo que trasciende la rivalidad. Es un momento que se graba a fuego.",
                    0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 5, 5, 0, 0);
            footballDataService.createOption(d039,
                    "Bajar la cabeza con humildad y retirarte",
                    "No necesitas más protagonismo. Te vas al banquillo con la cabeza baja y una sonrisa contenida. El vídeo se hace viral igualmente.",
                    0, 0, 0, 0, 1, 0, 6, 0, 0, 0, 3, 4, 0, 0);

// D040 — Final de copa
            Event d040 = footballDataService.createEvent(
                    "Día de final de copa",
                    "El estadio está lleno. Tu familia está en la grada. Hay cámaras en cada esquina. El presidente del país va a entregar el trofeo. Todo el mundo te mira. Tu madre no ha pegado ojo. Tú tampoco. Pero por diferentes razones.",
                    "deportivo", "decision", "epic", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d040,
                    "Jugar como si fuera un partido más",
                    "Rutina, rutina, rutina. El mismo calentamiento, las mismas botas, la misma música en los auriculares. Cuanto más grande es el partido, más normal tienes que ser.",
                    0, 0, 0, 0, 3, 0, 2, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d040,
                    "Dejarte llevar por la adrenalina del momento",
                    "El ambiente te pone a cien. Sales al campo con el corazón a doscientos. Eso puede ser un arma de doble filo.",
                    1, 0, 1, 0, -1, 0, 2, 0, 0, 0, 2, 1, 0, 0);

// D041 — Récord de goles del club
            Event d041 = footballDataService.createEvent(
                    "A un gol del récord histórico",
                    "Llevas 34 goles en la temporada. El récord del club son 35 de un delantero que marcó esa cifra hace 40 años. Quedan tres partidos. Todo el mundo habla de ello. La leyenda del club te manda un mensaje: 'Rómpelo, chaval. Los récords están para eso.'",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(d041,
                    "Ir a por el récord sin obsesionarte",
                    "Juegas tu fútbol. Si llega, llega. Y en el penúltimo partido, llega. Gol número 35. Empate con la leyenda. En el último partido, 36. Récord. Tu nombre en la historia del club.",
                    0, 0, 2, 0, 1, 0, 6, 0, 0, 1, 4, 3, 0, 2);
            footballDataService.createOption(d041,
                    "Intentar batirlo cuanto antes y quitarte la presión",
                    "Sales al campo pensando solo en marcar. Cada tiro va a portería. A veces la ambición te juega malas pasadas.",
                    0, 0, 1, 0, -1, 0, 2, 0, -1, -1, 1, 1, 0, 0);

// D042 — Partido benéfico
            Event d042 = footballDataService.createEvent(
                    "Te invitan a un partido benéfico",
                    "Un evento solidario organizado por una fundación. Estrellas retiradas, jugadores en activo y famosos que no dan pie con bola. Es por una buena causa. Tu representante te lo comenta: 'Es buen escaparate y haces algo bueno.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d042,
                    "Ir y disfrutar del evento",
                    "Risas, regates de broma y un hat-trick a un famoso que celebra cada gol como si fuera la Champions. La prensa se llena de fotos tuyas sonriendo. Buena imagen.",
                    0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 2, 3, 0, 0);
            footballDataService.createOption(d042,
                    "Declinar educadamente, necesitas descansar",
                    "Tu cuerpo necesita reposo. Le dices a tu representante que no, pero que done dinero en tu nombre. La fundación lo agradece.",
                    0, 0, 0, 1, 0, 0, 0, -100, 0, 0, 0, 1, 0, 0);

// D043 — Te dan el brazalete en un partido
            Event d043 = footballDataService.createEvent(
                    "El capitán se lesiona: te toca a ti",
                    "Minuto 25. El capitán cae lesionado y tiene que salir. El míster grita desde la banda: '¡Eh, tú! ¡Ponte el brazalete!' Te lo lanza el capitán desde la camilla con un gesto de dolor y confianza.",
                    "deportivo", "informativo", "uncommon", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(d043,
                    "Asumir el rol y liderar al equipo",
                    "Te pones el brazalete y algo cambia. Empiezas a hablar, a pedir, a organizar. No sabías que podías ser así. Ni tú ni tus compañeros.",
                    0, 0, 0, 0, 0, 2, 3, 0, 3, 2, 1, 0, 0, 0);
            footballDataService.createOption(d043,
                    "Ponértelo por obligación pero seguir jugando igual",
                    "El brazalete no cambia tu forma de jugar. Eres el mismo. Ni mejor ni peor. Pero el míster esperaba algo más.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0);

// D044 — Prórroga agónica
            Event d044 = footballDataService.createEvent(
                    "Empate al final. Hay prórroga.",
                    "90 minutos. Empate. El árbitro señala que hay 30 minutos más. Las piernas no dan para más. El míster te pregunta: '¿Puedes aguantar o te cambio?'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d044,
                    "Decirle que puedes y quedarte",
                    "Las piernas gritan, pero el corazón grita más. Aguantas los 30 minutos. En el 118 haces una jugada que nadie esperaba, ni tú.",
                    0, 0, 0, -1, 2, 0, 2, 0, 2, 1, 1, 0, 0, 0);
            footballDataService.createOption(d044,
                    "Ser honesto y pedir el cambio",
                    "'Míster, no llego.' El entrenador asiente: 'Gracias por ser sincero.' Te cambia. Desde el banquillo, ves cómo tu sustituto marca el gol de la victoria.",
                    0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0);

// D045 — Asistencia de tacón
            Event d045 = footballDataService.createEvent(
                    "Se te ocurre una locura",
                    "Recibes de espaldas en el área. Hay un compañero desmarcado a tu derecha. Lo normal es girarte y pasarle. Lo loco es hacerlo de tacón, sin mirar, fiándote del instinto.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d045,
                    "Tacón de fantasía",
                    "Toque de tacón, pase perfecto, gol del compañero. El estadio aplaude DOS veces: una por el gol y otra por tu genialidad. Esa jugada va a salir en los highlights de la jornada.",
                    0, 2, 0, 0, 0, 2, 4, 0, 0, 2, 3, 3, 0, 0);
            footballDataService.createOption(d045,
                    "Girarte y hacer el pase normal",
                    "Control, giro, pase. Gol. Nadie recordará tu asistencia mañana, pero el equipo suma tres puntos. A veces lo práctico es lo mejor.",
                    0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0);

// D046 — Romper una sequía del equipo
            Event d046 = footballDataService.createEvent(
                    "El equipo lleva 6 partidos sin ganar",
                    "Seis jornadas. Cero victorias. La moral del vestuario está por los suelos. El míster cada vez grita más y sonríe menos. La afición empieza a perder la paciencia. Hoy, en casa, contra un rival directo, es la última oportunidad de cambiar la dinámica.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d046,
                    "Dar un paso al frente y arengar al vestuario",
                    "Antes de salir al campo, te levantas y dices lo que nadie se atrevía a decir: 'Estamos muertos de miedo. Hoy se acaba. Salimos ahí a ganar o no volvemos al vestuario.' El silencio es total. Y entonces, uno a uno, se levantan todos.",
                    0, 0, 0, 0, 0, 2, 3, 0, 2, 3, 2, 0, 0, 0);
            footballDataService.createOption(d046,
                    "Liderar con el ejemplo en el campo, sin discursos",
                    "No eres de discursos. Pero cuando el balón rueda, eres el primero en cada duelo, el primero en cada carrera, el primero en pedir el balón. El equipo te sigue.",
                    1, 0, 0, 1, 1, 0, 2, 0, 2, 2, 1, 0, 0, 0);

// D047 — Asistencia que da un título
            Event d047 = footballDataService.createEvent(
                    "Último minuto, último partido de liga",
                    "Última jornada de liga. Si ganáis, sois campeones. Si no, se lo lleva el rival. Minuto 89. 0-0. Recibes en la banda. Levantas la cabeza. Ves un hueco que no debería existir. Un pase de 25 metros que tiene que ser perfecto. No bueno. PERFECTO.",
                    "deportivo", "decision", "epic", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d047,
                    "Meter el pase de tu vida",
                    "El balón viaja entre cuatro defensas, bota una vez y llega al pie de tu compañero. Solo, ante el portero. Marca. CAMPEONES. Te caes de rodillas. No has marcado el gol, pero todos saben que el título lo ha ganado tu pase.",
                    0, 1, 0, 0, 2, 3, 8, 300, 2, 3, 5, 5, 1, 2);
            footballDataService.createOption(d047,
                    "Jugar seguro, no arriesgar con un pase largo",
                    "Optas por el pase corto. Posesión. Tiempo. El equipo recicla. Córner en el 92. Cabezazo. Gol. Campeones igualmente. Pero la historia podría haber sido distinta.",
                    0, 0, 0, 0, 0, 0, 4, 300, 1, 1, 3, 2, 0, 1);

// D048 — Compañero lesionado en el campo
            Event d048 = footballDataService.createEvent(
                    "Tu compañero se queda en el suelo",
                    "Jugada normal. Tu compañero cae mal. Se agarra la rodilla. No se levanta. El fisio entra corriendo. El silencio del campo te pone los pelos de punta. Se lo llevan en camilla. Te mira desde la camilla con los ojos vidriosos.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d048,
                    "Acercarte a darle ánimos mientras se lo llevan",
                    "Le agarras la mano un segundo: 'Vas a volver, hermano.' Él asiente sin poder hablar. Vuelves al campo con un nudo en la garganta pero con ganas de ganar ese partido por él.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 1, 1, 0, 0);
            footballDataService.createOption(d048,
                    "Centrarte en el partido, ya le verás después",
                    "No te acercas a la camilla. No por frialdad, sino porque sabes que si le ves así te va a desconcentrar. Le escribirás después.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// D049 — Te premian como MVP de la jornada
            Event d049 = footballDataService.createEvent(
                    "MVP de la jornada",
                    "Después del partido suena tu teléfono. La liga te ha elegido como el mejor jugador de la jornada. Tu foto con la camiseta del partido aparece en la web oficial. Tu representante te felicita. El míster te manda un mensaje: 'Bien. Pero el domingo hay otro partido.'",
                    "deportivo", "informativo", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d049,
                    "Compartirlo en redes con humildad",
                    "Subes la imagen con un 'Gracias al equipo. Sin ellos esto no existe.' Respuesta perfecta. Ni demasiado, ni demasiado poco.",
                    0, 0, 0, 0, 0, 0, 3, 0, 0, 1, 1, 3, 1, 0);
            footballDataService.createOption(d049,
                    "Guardártelo para ti y seguir trabajando",
                    "Ni lo compartes ni lo mencionas. Lo guardas en un cajón de la memoria. Los premios individuales están bien, pero lo que quieres es un título.",
                    0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0);

// D050 — Último partido de la temporada
            Event d050 = footballDataService.createEvent(
                    "Último partido. Fin de temporada.",
                    "90 minutos y se acabó. Da igual el resultado. Lo que importa es el camino: los goles, los fallos, las victorias, las derrotas, las noches de Champions, los domingos grises, las lesiones y las remontadas. El árbitro pita el final. Te quedas en el campo unos segundos más que nadie.",
                    "deportivo", "decision", "common", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d050,
                    "Aplaudir a la afición y despedirte hasta la próxima",
                    "Una vuelta al campo, aplausos a las cuatro gradas, camiseta al público. Sea como sea la temporada, la afición siempre merece ese gesto.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 3, 1, 0, 0);
            footballDataService.createOption(d050,
                    "Irte al vestuario pensando en lo que viene",
                    "Ya estás pensando en la próxima temporada. En qué mejorar, en qué cambiar, en qué mantener. El fútbol no para. Tú tampoco.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);


            // ===== EVENTOS DEPORTIVOS — TERCERA TANDA (D051-D100) =====

// D051 — Lesión grave
            Event d051 = footballDataService.createEvent(
                    "Crujido en la rodilla",
                    "Aceleración en el minuto 30. Sin contacto. Notas un chasquido. Te caes solo. Sabes lo que es antes de que llegue el médico. Rotura de ligamento cruzado. La camilla, el túnel, la resonancia, y la frase que más duele: 'Seis meses.'",
                    "deportivo", "unico", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d051,
                    "Afrontarlo como un reto personal",
                    "Seis meses de gimnasio, piscina y fisio. Es la prueba más dura de tu carrera. Pero cada día que pasa estás más cerca. El día que vuelves a pisar el césped, lloras como un crío.",
                    0, 0, 0, -5, 3, 0, -2, -200, 0, 2, 1, 0, 0, 0);
            footballDataService.createOption(d051,
                    "Hundirte durante semanas antes de reaccionar",
                    "Las primeras semanas son un agujero negro. No quieres ver fútbol, no quieres hablar de fútbol. Hasta que un día, sin saber por qué, te levantas del sofá y vas al gimnasio. La recuperación empieza tarde, pero empieza.",
                    0, 0, 0, -6, -2, 0, -3, -200, 0, -1, -1, 0, 0, 0);

// D052 — Volver de una lesión larga
            Event d052 = footballDataService.createEvent(
                    "El primer entrenamiento después de la lesión",
                    "Seis meses sin tocar un balón. Te atas las botas con manos temblorosas. Sales al campo. El césped te parece más verde que nunca. El primer sprint te da miedo. La rodilla aguanta. El segundo sprint, menos miedo. Al tercero, sonríes.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d052,
                    "Ir poco a poco, sin forzar",
                    "Tres semanas de entrenamiento progresivo. Sin contacto, sin disputas. El míster te dice: 'Cuando estés, estás. Sin prisas.' Vuelves al cien por cien. Sin recaída.",
                    0, 0, 0, 2, 1, 0, 1, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(d052,
                    "Querer volver cuanto antes a competir",
                    "No puedes esperar más. Pides entrar en el grupo a la semana. El fisio protesta. El cuerpo protesta. Pero la cabeza necesita competir. Juegas antes de lo previsto.",
                    0, 0, 0, -1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0);

// D053 — Sustituido en el descanso
            Event d053 = footballDataService.createEvent(
                    "Te cambian en el descanso",
                    "El míster entra al vestuario, mira la pizarra y dice tu número: 'Sales. No me estás dando lo que necesito ahí.' Delante de todos. El sustituto ya se está poniendo las botas. La puerta del vestuario nunca ha pesado tanto.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d053,
                    "Aceptarlo sin montar ningún drama",
                    "Asientes, te sientas y ves la segunda parte desde el banquillo. Por dentro arde. Pero el fútbol es así: hoy te toca tragar.",
                    0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d053,
                    "Pedir explicaciones al míster",
                    "Le paras en el pasillo: '¿Qué he hecho mal?' El míster te mira: 'Hoy no era tu día. No lo conviertas en un problema.' La conversación acaba ahí, pero la herida no.",
                    0, 0, 0, 0, 0, 0, -1, 0, -1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d053,
                    "Reventar el siguiente entrenamiento para demostrar que se equivoca",
                    "El lunes llegas el primero y te vas el último. Cada ejercicio es una venganza. El míster te observa sin decir nada. El sábado vuelves al once.",
                    0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0);

// D054 — El entrenador te echa la bronca delante de todos
            Event d054 = footballDataService.createEvent(
                    "Bronca pública del míster",
                    "En pleno entrenamiento, delante de todo el equipo, el míster para el ejercicio y te señala: '¿Qué estás haciendo? ¿Estás aquí o estás en la luna? Si no quieres entrenar, coge la puerta. Pero no me hagas perder el tiempo a mí ni a tus compañeros.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d054,
                    "Callarte y apretar los dientes",
                    "Tragas saliva y entrenas el doble lo que queda de sesión. No dices una palabra. Los compañeros te miran de reojo. Al final del entreno, el míster se acerca: 'Así me gusta.' Como si nada.",
                    0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d054,
                    "Responderle que tiene razón y pedir disculpas",
                    "'Tienes razón, míster. Perdona.' El vestuario te respeta por no echar más leña. El míster asiente. Tema zanjado.",
                    0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0);
            footballDataService.createOption(d054,
                    "Encararle delante de todos",
                    "'No me grites delante de todos, míster. Si tienes algo que decirme, dímelo en privado.' El vestuario se queda helado. El míster se queda callado tres segundos eternos.",
                    0, 0, 0, 0, 0, 0, 0, 0, -3, 1, 0, 0, 0, 0);

// D055 — Te dejan fuera de la convocatoria
            Event d055 = footballDataService.createEvent(
                    "No estás en la lista",
                    "Viernes por la tarde. Sale la convocatoria para el partido del sábado. Tu nombre no está. No estás lesionado. No estás sancionado. Simplemente no estás. El míster no te ha dicho nada. El WhatsApp del vestuario explota.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d055,
                    "Ir al despacho del míster y preguntarle por qué",
                    "Picas la puerta. 'Míster, ¿puedo saber por qué no estoy?' Él te mira: 'Decisión técnica. Nada personal. Entrena bien esta semana y estarás el próximo.' Es una respuesta que no dice nada, pero es la única que vas a conseguir.",
                    0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d055,
                    "No decir nada y entrenar como un animal la semana siguiente",
                    "Si no hay explicación, te la buscas tú en el campo. La siguiente semana entrenas tan bien que dejar fuera de la lista es imposible de justificar.",
                    0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0);

// D056 — Tu suplente lo hace mejor que tú
            Event d056 = footballDataService.createEvent(
                    "Tu sustituto está en racha",
                    "Te has perdido tres partidos por una molestia muscular. Tu sustituto ha marcado cuatro goles en esos tres partidos. La grada corea su nombre. La prensa habla de él. Cuando vuelves al entrenamiento, el míster no te garantiza el puesto.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d056,
                    "Competir limpiamente por el puesto",
                    "No tienes nada contra él. Es compañero. La competencia sana mejora a los dos. Entrenas a tope y que el míster decida.",
                    0, 0, 0, 0, 1, 0, 0, 0, 2, 2, 0, 0, 0, 0);
            footballDataService.createOption(d056,
                    "Hablar con el míster para recordarle tu trayectoria",
                    "'Míster, llevo aquí dos años rindiendo. No me puedes sentar por tres partidos.' El entrenador te responde: 'Aquí juega el que mejor esté. Demuéstramelo.'",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0);

// D057 — Provocación de un rival famoso
            Event d057 = footballDataService.createEvent(
                    "El rival te busca las cosquillas",
                    "El mediocampista estrella del otro equipo no para de hablar. 'Eh, chaval, ¿tú de dónde has salido? ¿De Segunda B?' Cada vez que pasas cerca te suelta una. El árbitro no le dice nada. Los compañeros te miran: ¿vas a aguantar?",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d057,
                    "Responderle con un caño y una sonrisa",
                    "La mejor respuesta es fútbol. Le haces un caño delante de 50.000 personas. Él se calla. La grada grita 'OLÉ'. No necesitas decir ni una palabra.",
                    0, 2, 0, 0, 2, 0, 4, 0, 0, 0, 3, 2, 0, 0);
            footballDataService.createOption(d057,
                    "Ignorarle completamente durante los 90 minutos",
                    "Ni le miras. Ni le respondes. Juega tu partido como si él no existiera. Al final, él se ha pasado 90 minutos hablando y tú 90 minutos jugando.",
                    0, 0, 0, 0, 3, 0, 1, 0, 1, 0, 0, 1, 0, 0);
            footballDataService.createOption(d057,
                    "Encararle y decirle cuatro cosas",
                    "Le plantas cara: 'A mí me hablas en el campo con el balón, payaso.' El árbitro os separa. Amarilla para los dos. La prensa habla de ello tres días.",
                    0, 0, 0, 0, -1, 0, 1, 0, 0, -1, 1, -1, 0, 0);

// D058 — Te ofrecen el dorsal 10
            Event d058 = footballDataService.createEvent(
                    "El diez está libre",
                    "El jugador que llevaba el 10 se ha marchado del club. El míster te llama: 'Oye, quiero ofrecerte el dorsal. El diez no es un número cualquiera aquí. Tiene historia. ¿Lo quieres o prefieres quedarte con el tuyo?'",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(d058,
                    "Aceptar el 10 con todo lo que conlleva",
                    "Te pones el diez. La grada espera más de ti. La prensa espera más de ti. Tú esperas más de ti. Es un peso y un honor a partes iguales.",
                    0, 0, 0, 0, 0, 0, 4, 0, 2, 0, 2, 2, 0, 1);
            footballDataService.createOption(d058,
                    "Quedarte con tu dorsal, que ya tiene su historia",
                    "'Míster, gracias, pero prefiero el mío.' El entrenador sonríe: 'Me gusta que no necesites un número para sentirte importante.'",
                    0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0);

// D059 — Tarjeta roja a un compañero, jugáis con 10
            Event d059 = footballDataService.createEvent(
                    "Roja a tu compañero. Jugáis con diez.",
                    "Minuto 55. Roja directa a tu compañero. Quedan 35 minutos con uno menos. El equipo se mira. El míster grita desde la banda cambiando la táctica. Ahora hay que correr por dos.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d059,
                    "Dar un paso al frente y correr por dos",
                    "Te dejas la piel cubriendo el hueco. Corres como nunca. Acabas el partido con calambres en las dos piernas, pero con el empate que vale como una victoria.",
                    1, 0, 0, 1, 1, 0, 2, 0, 2, 3, 1, 0, 0, 0);
            footballDataService.createOption(d059,
                    "Jugar inteligente y administrar fuerzas",
                    "Con uno menos no puedes ir a muerte. Tocas, temporizas, enfrias el partido. No es bonito, pero es efectivo. El míster te lo agradece con la mirada.",
                    0, 0, 0, 0, 1, 2, 1, 0, 2, 1, 0, 0, 0, 0);

// D060 — Gol que os salva del descenso
            Event d060 = footballDataService.createEvent(
                    "Si no ganas, bajáis",
                    "Última jornada. Si no ganáis, descenso. El vestuario es una mezcla de miedo y determinación. El míster no da charla. Pone una foto de la afición en el proyector: 'Por ellos.' Minuto 80. 0-0. Te llega un balón en la frontal.",
                    "deportivo", "decision", "epic", "profesional", 18, 38, 1, 2, null
            );
            footballDataService.createOption(d060,
                    "Disparar sin pensarlo",
                    "No hay tiempo para pensar. Disparo raso, esquina, red. Gol. Permanencia. El estadio estalla. Compañeros te aplastan. Es el gol más importante de tu vida y probablemente de la historia reciente del club.",
                    0, 0, 3, 0, 3, 0, 8, 100, 3, 3, 5, 3, 0, 3);
            footballDataService.createOption(d060,
                    "Buscar el pase al compañero mejor colocado",
                    "Ves a tu compañero solo a la derecha. Le pones un pase perfecto. Marca. Permanencia. Tú no saldrás en la portada, pero sabes que sin ese pase no había gol.",
                    0, 0, 0, 0, 1, 2, 4, 100, 2, 3, 3, 2, 0, 2);

// D061 — Ascenso con tu equipo
            Event d061 = footballDataService.createEvent(
                    "¡Habéis subido!",
                    "Pitido final. El equipo ha ascendido. Jugadores llorando, cuerpo técnico abrazándose, la grada cantando sin parar. Has sido parte fundamental del ascenso — tus goles y tu juego han llevado al equipo a la categoría de arriba. El presidente baja al césped con una botella de champán.",
                    "deportivo", "unico", "epic", "profesional", 18, 38, 1, 2, null
            );
            footballDataService.createOption(d061,
                    "Celebrarlo con todo el club y la ciudad",
                    "Autobús descapotable, ayuntamiento, cena de equipo hasta las tantas. Te has ganado esto. El club entero te quiere.",
                    0, 0, 0, 0, 0, 0, 6, 150, 3, 4, 5, 3, 0, 3);
            footballDataService.createOption(d061,
                    "Celebrar pero ya pensar en la siguiente temporada",
                    "Disfrutas la noche, pero al día siguiente ya estás pensando en que la categoría de arriba es otro nivel. No quieres ser el equipo que sube y baja.",
                    0, 0, 0, 0, 2, 0, 4, 150, 1, 2, 2, 1, 0, 1);

// D062 — El equipo desciende
            Event d062 = footballDataService.createEvent(
                    "Habéis bajado",
                    "Pitido final. Descenso confirmado. El estadio se vacía en silencio. Los jugadores se quedan en el césped mirando al vacío. Algunos lloran. Otros se van directos al vestuario sin hablar. Tú te quedas sentado en la banda con la camiseta por encima de la cabeza.",
                    "deportivo", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d062,
                    "Dar la cara ante la afición que se queda",
                    "Te levantas, vas a la grada donde quedan los más fieles, y pides perdón con un gesto. Algunos te insultan. Otros te aplauden por dar la cara. Es lo más difícil que has hecho nunca.",
                    0, 0, 0, 0, 2, 0, -3, 0, 0, 0, 2, 1, 0, 0);
            footballDataService.createOption(d062,
                    "Irte al vestuario sin mirar a nadie",
                    "No puedes. No tienes fuerzas para enfrentarte a la grada. La ducha más larga de tu vida. El teléfono en silencio tres días.",
                    0, 0, 0, 0, -1, 0, -4, 0, 0, -1, -1, 0, 0, 0);

// D063 — Tu primer clásico a nivel top
            Event d063 = footballDataService.createEvent(
                    "El partido del año",
                    "No es un partido cualquiera. Es EL partido. El que paraliza el país. El que ven 500 millones de personas. El que le contarás a tus nietos. El vestuario parece la antesala de una batalla. El míster no dice nada que no se haya dicho ya mil veces. Túnel de vestuarios. Luces. Ruido. Pitido.",
                    "deportivo", "decision", "epic", "profesional", 22, 38, 3, 3, null
            );
            footballDataService.createOption(d063,
                    "Salir a disfrutar del escenario más grande del mundo",
                    "Has soñado con esto desde que eras un crío. No vas a desperdiciar ni un segundo teniéndole miedo. El balón es el mismo, la portería es del mismo tamaño. Solo cambia el volumen.",
                    0, 0, 0, 0, 2, 0, 5, 0, 0, 0, 3, 3, 0, 0);
            footballDataService.createOption(d063,
                    "Centrarte en tu trabajo y olvidar todo lo que rodea al partido",
                    "Auriculares hasta el último momento. No miras a la grada. No lees la prensa. Para ti es un partido más. Al menos eso te repites.",
                    0, 0, 0, 0, 1, 1, 3, 0, 1, 0, 0, 0, 0, 0);

// D064 — Semifinal de Champions
            Event d064 = footballDataService.createEvent(
                    "Semifinal de Champions League",
                    "Has llegado a semis de Champions. Ida en tu campo. El himno suena diferente cuando solo quedan cuatro equipos. El rival es un monstruo lleno de estrellas. Pero tú también tienes las tuyas. Y una de ellas eres tú.",
                    "deportivo", "decision", "epic", "profesional", 22, 38, 3, 3, null
            );
            footballDataService.createOption(d064,
                    "Jugar el partido de tu vida",
                    "Todo lo que has hecho en tu carrera te ha llevado a esta noche. Sales al campo con la determinación de alguien que sabe que este momento no volverá. Y rindes a un nivel que ni tú sabías que tenías.",
                    0, 1, 1, 0, 2, 1, 6, 0, 2, 2, 4, 4, 1, 1);
            footballDataService.createOption(d064,
                    "Respetar al rival y jugar con cabeza",
                    "No es momento de volverse loco. El rival tiene calidad para castigarte si te abres. Juegas inteligente, compacto, esperando tu momento. Cuando llega, no lo desperdicias.",
                    0, 0, 0, 0, 2, 2, 4, 0, 3, 1, 2, 2, 0, 0);

// D065 — Final de Champions
            Event d065 = footballDataService.createEvent(
                    "Final de Champions League",
                    "El día más grande de tu carrera. Final de la Champions League. El estadio está a reventar. Las cámaras del mundo entero apuntan al césped. El trofeo de las orejas grandes brilla bajo los focos al borde del campo. Esta noche, pase lo que pase, tu vida cambia para siempre.",
                    "deportivo", "decision", "legendary", "profesional", 22, 38, 3, 3, null
            );
            footballDataService.createOption(d065,
                    "Dejarte la vida en cada jugada",
                    "90 minutos al límite. Cada sprint es el último. Cada duelo es una final dentro de la final. Si ganas, tu nombre queda grabado para siempre.",
                    0, 1, 1, 0, 3, 0, 8, 500, 2, 3, 5, 5, 2, 3);
            footballDataService.createOption(d065,
                    "Mantener la calma cuando todos pierden la cabeza",
                    "Mientras los demás están a 200 pulsaciones, tú juegas como si estuvieras en el parque. Tocas, filtras, esperas. La calma en la tormenta. Eso te hace diferente.",
                    0, 1, 0, 0, 3, 2, 7, 500, 3, 2, 4, 5, 2, 3);

// D066 — Partido de clasificación mundial
            Event d066 = footballDataService.createEvent(
                    "Eliminatoria del Mundial",
                    "Tu selección se juega la clasificación para el Mundial en un partido. Estadio lleno. Un país entero viendo la tele. El seleccionador te ha puesto de titular. Si ganáis, Mundial. Si perdéis, cuatro años esperando.",
                    "deportivo", "decision", "epic", "profesional", 22, 38, 2, 3, null
            );
            footballDataService.createOption(d066,
                    "Asumir la responsabilidad de liderar al equipo",
                    "No esperas a que pase algo. Tú haces que pase. Pides el balón, generas peligro, arrastras al equipo. Para eso te han convocado.",
                    0, 0, 1, 0, 0, 1, 5, 0, 0, 2, 3, 3, 0, 0);
            footballDataService.createOption(d066,
                    "Jugar disciplinado dentro del plan del seleccionador",
                    "El seleccionador tiene un plan. Tú eres una pieza del plan. No te sales del guion. Ejecutas lo que te piden al milímetro.",
                    0, 0, 0, 0, 1, 0, 3, 0, 2, 1, 1, 2, 0, 0);

// D067 — Te expulsan injustamente
            Event d067 = footballDataService.createEvent(
                    "Roja injusta",
                    "Disputas un balón con el rival. Llegas primero. El contacto es mínimo. El rival se tira al suelo como si le hubieran disparado. El árbitro saca roja directa. No es falta ni de broma. La repetición lo demuestra. Pero ya estás fuera.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d067,
                    "Irte sin montar el número, apelando después",
                    "Levantas las manos, niegas con la cabeza y te vas. Sin gritos, sin empujones. En la sala de prensa, el vídeo habla por ti. La apelación será fácil.",
                    0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 2, 0, 0);
            footballDataService.createOption(d067,
                    "Protestar al árbitro con todo",
                    "Te acercas al colegiado y le dices que es una vergüenza. Los compañeros te separan. La prensa graba todo. La apelación se complica porque te han puesto un informe por protestar.",
                    0, 0, 0, 0, -1, 0, -1, 0, 0, -1, 0, -2, 0, 0);

// D068 — 100 partidos con el club
            Event d068 = footballDataService.createEvent(
                    "Cien partidos con esta camiseta",
                    "Antes del partido, el altavoz del estadio anuncia: 'Con el partido de hoy, nuestro jugador alcanza la cifra de 100 partidos oficiales con el club.' La grada aplaude. Te dan un cuadro conmemorativo. El capitán te abraza. Cien partidos son muchos domingos, muchos viajes en autobús y mucho vestuario compartido.",
                    "deportivo", "informativo", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(d068,
                    "Agradecer a la afición con un gesto sincero",
                    "Te acercas a la grada, te tocas el corazón y aplauds. Cien partidos en un club significan algo. Eso ya no te lo quita nadie.",
                    0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 1, 0, 1);
            footballDataService.createOption(d068,
                    "Centrarte en que el partido 101 sea tan bueno como el primero",
                    "El homenaje está bien, pero hay un partido que ganar. Los números son bonitos. Los tres puntos son necesarios.",
                    0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0);

// D069 — Calientas pero no entras
            Event d069 = footballDataService.createEvent(
                    "Quince minutos calentando para nada",
                    "El míster te manda calentar en el 60. Corres por la banda quince minutos mientras el partido sigue. La grada te aplaude. Vuelves. El míster te mira. '...no, siéntate. Vamos bien así.' Te sientas con el chándal húmedo de sudor y la frustración seca.",
                    "deportivo", "decision", "common", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d069,
                    "Tragártelo y no decir nada",
                    "El fútbol tiene estas cosas. Hoy no ha sido. Mañana será. Te sientas y aceptas.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d069,
                    "Mostrar tu frustración sin ser irrespetuoso",
                    "Te sientas y tu cara lo dice todo. No dices nada, pero tu lenguaje corporal grita. El míster lo nota. No le gusta.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0);

// D070 — Pase de gol que tu compañero falla
            Event d070 = footballDataService.createEvent(
                    "El pase perfecto que no acaba en gol",
                    "Filtras un pase entre líneas que deja a tu compañero solo, completamente solo, ante el portero. Se la tiene que meter. Y la tira fuera. El asistencia que debería haber sido se queda en un 'casi'. Él te mira con cara de disculpa.",
                    "deportivo", "decision", "common", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d070,
                    "Animarle y decirle que la próxima va dentro",
                    "Le chocas la mano: 'Tranquilo, la siguiente la metes.' Sigue pidiendo el balón. Y dos jugadas después, marca. Te busca para celebrar.",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0);
            footballDataService.createOption(d070,
                    "Llevarte las manos a la cabeza y dejar claro tu frustración",
                    "El gesto es involuntario pero tu compañero lo ve. No dices nada, pero tu cara habla. Él baja la cabeza. La relación se enfría un par de grados.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0);

// D071 — El portero rival te provoca
            Event d071 = footballDataService.createEvent(
                    "El portero te tiene calado",
                    "Cada vez que te acercas al área, el portero rival te habla: 'Venga, tira, que te la paro. Siempre tiras al mismo lado.' Lo peor es que tiene razón: te ha parado dos disparos.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d071,
                    "Cambiar tu disparo y pillarle a contrapié",
                    "La tercera vez, fintas el disparo al lado habitual y la colocas al otro palo. Gol. Le miras. Él no dice nada. Tú tampoco. No hace falta.",
                    0, 0, 2, 0, 2, 0, 3, 0, 0, 0, 2, 1, 0, 0);
            footballDataService.createOption(d071,
                    "Ignorarle y seguir con tu juego",
                    "No vas a cambiar tu forma de jugar porque un portero te hable. Sigues tirando como sabes. A la cuarta, entra.",
                    0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0);

// D072 — Estrella ficha por tu equipo
            Event d072 = footballDataService.createEvent(
                    "Ha fichado una estrella para tu posición",
                    "El club anuncia un fichaje bomba: un jugador internacional, tres veces mejor jugador de su liga anterior, que juega en TU posición. La prensa pregunta si eso te convierte en suplente. Tu representante te llama preocupado.",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(d072,
                    "Verlo como una oportunidad de aprender de él",
                    "Es mejor que tú. De momento. Pero tenerlo al lado en cada entrenamiento te hace mejorar. En tres meses has subido de nivel sin darte cuenta.",
                    0, 1, 0, 0, 1, 0, 0, 0, 2, 1, 0, 0, 0, 0);
            footballDataService.createOption(d072,
                    "Competir por el puesto sin complejos",
                    "Le dices a tu representante: 'Me da igual quién venga. El puesto se gana entrenando.' Y sales al campo a demostrar que no sobras.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(d072,
                    "Pedir explicaciones a la directiva",
                    "'¿Para qué ficháis a alguien de mi puesto? ¿No confiáis en mí?' La directiva te dice que es por competencia interna. Tú sabes que es por algo más.",
                    0, 0, 0, 0, 0, 0, -1, 0, -1, 0, 0, 0, 0, -2);

// D073 — Gol número 100 de tu carrera
            Event d073 = footballDataService.createEvent(
                    "Gol número cien",
                    "El narrador de radio lo grita: '¡GOL NÚMERO CIEN EN SU CARRERA PROFESIONAL!' El marcador electrónico lo confirma. Tu centésimo gol. Una cifra redonda que te sitúa entre los elegidos. El estadio aplaude más el número que el gol en sí.",
                    "deportivo", "informativo", "rare", "profesional", 22, 38, 1, 3, null
            );
            footballDataService.createOption(d073,
                    "Señalar al banquillo dedicándolo al cuerpo técnico",
                    "Corres al banquillo y abrazas al míster, al segundo, al preparador. Sin ellos no serías quien eres. El gesto no pasa desapercibido.",
                    0, 0, 0, 0, 0, 0, 4, 0, 3, 2, 2, 2, 0, 0);
            footballDataService.createOption(d073,
                    "Celebrarlo apuntando a los cuatro puntos cardinales del estadio",
                    "Giras sobre ti mismo señalando a las cuatro gradas. Todos te han ayudado a llegar aquí. Los cien son de todos.",
                    0, 0, 0, 0, 0, 0, 5, 0, 0, 1, 4, 3, 0, 1);

// D074 — Te ofrecen ser capitán permanente
            Event d074 = footballDataService.createEvent(
                    "El brazalete, esta vez en serio",
                    "El capitán ha dejado el club. El míster te cita en su despacho: 'He hablado con el vestuario. Quieren que seas tú el capitán. No es temporal. Es el brazalete hasta que tú digas basta.'",
                    "deportivo", "decision", "epic", "profesional", 22, 38, 1, 3, null
            );
            footballDataService.createOption(d074,
                    "Aceptar con orgullo y responsabilidad",
                    "El brazalete ya no es solo un trozo de tela. Es un compromiso. Con el club, con los compañeros, con la afición. A partir de hoy, tu voz pesa más. Y tus actos, también.",
                    0, 0, 0, 0, 0, 2, 5, 0, 3, 3, 3, 2, 0, 2);
            footballDataService.createOption(d074,
                    "Rechazarlo, prefieres liderar sin brazalete",
                    "'Míster, agradezco la confianza pero creo que puedo ayudar más al equipo sin esa presión.' El entrenador lo respeta. Algunos compañeros no lo entienden.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, -1, 0, 0, 0, 0);

// D075 — Partido a puerta cerrada
            Event d075 = footballDataService.createEvent(
                    "Partido sin público",
                    "Sanción al club. Partido a puerta cerrada. El estadio vacío. Cada pelotazo retumba como un eco. No hay grada, no hay cánticos, no hay ambiente. Solo fútbol puro. Y el silencio, que es ensordecedor.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d075,
                    "Jugar con la misma intensidad que siempre",
                    "La grada esté llena o vacía, tú has venido a competir. Corres igual, peleas igual, celebras igual. Aunque nadie te oiga.",
                    0, 0, 0, 0, 2, 0, 1, 0, 1, 1, 0, 0, 0, 0);
            footballDataService.createOption(d075,
                    "Notar que sin público no es lo mismo",
                    "Intentas pero no es igual. Marcas un gol y el silencio posterior es raro. Te falta la adrenalina del público. Juegas correcto, pero sin ese punto extra.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);


            // ===== EVENTOS DEPORTIVOS — ÚLTIMA TANDA (D076-D100) =====

// D076 — El rival celebra en tu cara
            Event d076 = footballDataService.createEvent(
                    "Gol rival y celebración en tu cara",
                    "El delantero rival marca y viene corriendo hacia tu zona a celebrarlo. Se pone de rodillas a dos metros de ti, se señala el nombre de la camiseta y grita mirándote directamente. El árbitro no hace nada. La grada rival enloquece.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d076,
                    "Darle la vuelta y marcar tú después",
                    "Guardas la rabia. La canalizas. Quince minutos después empatas con un gol que celebras mirándole a él. Justicia poética.",
                    0, 0, 1, 0, 2, 0, 3, 0, 0, 0, 3, 2, 0, 0);
            footballDataService.createOption(d076,
                    "Pasar de la provocación completamente",
                    "Le miras un segundo y te das la vuelta. No merece tu energía. Sigues jugando como si no hubiera pasado.",
                    0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 0, 0);

// D077 — Partido de pretemporada contra un gigante
            Event d077 = footballDataService.createEvent(
                    "Pretemporada contra un grande",
                    "Amistoso de pretemporada contra el {topClub}. No vale para nada en la clasificación, pero vale para todo en tu cabeza. Es la primera vez que pisas el campo con esos jugadores. Los que salen por la tele, a tres metros de ti.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 2, null
            );
            footballDataService.createOption(d077,
                    "Jugar sin complejos y demostrar que puedes competir con ellos",
                    "Les plantas cara desde el primer minuto. Uno de sus centrales te dice al oído: 'No está mal, chaval.' Eso vale más que cualquier premio.",
                    0, 0, 0, 0, 1, 0, 3, 0, 0, 0, 0, 2, 0, 0);
            footballDataService.createOption(d077,
                    "Aprender observando cómo se mueven y entrenan",
                    "Más que jugar, estudias. Cómo se desmarcan, cómo pisan el balón, cómo se colocan sin balón. Tres cosas que nunca habías visto de cerca.",
                    0, 1, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0);

// D078 — Gol que clasifica para Champions
            Event d078 = footballDataService.createEvent(
                    "Si marcas, Champions",
                    "Última jornada. Si ganáis, os clasificáis para la Champions League por primera vez en la historia del club. Minuto 70. 0-0. Contraataque. Corres. El balón te llega en el punto de penalti. El portero sale. Tú o él.",
                    "deportivo", "decision", "epic", "profesional", 22, 38, 2, 3, null
            );
            footballDataService.createOption(d078,
                    "Definir con clase, picándola por encima del portero",
                    "Globito suave. El balón vuela por encima del portero en cámara lenta. Entra. CHAMPIONS LEAGUE. El estadio se viene abajo. Has hecho historia.",
                    0, 0, 3, 0, 2, 0, 8, 300, 2, 3, 5, 4, 1, 3);
            footballDataService.createOption(d078,
                    "Disparar fuerte a la esquina sin pensar",
                    "Cañonazo raso al primer palo. Red. Gol. Ni tú sabes cómo ha entrado. Pero ha entrado. Champions.",
                    0, 0, 2, 0, 1, 0, 8, 300, 2, 3, 5, 4, 1, 3);

// D079 — Cesión a otro equipo
            Event d079 = footballDataService.createEvent(
                    "Te ceden a otro club",
                    "El director deportivo te llama: 'Mira, creemos en ti, pero aquí no vas a tener minutos. Hemos llegado a un acuerdo con el {club} para cederte una temporada. Allí jugarás cada semana. Cuando vuelvas serás mejor.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 26, 1, 3, null
            );
            footballDataService.createOption(d079,
                    "Aceptar la cesión y buscar minutos",
                    "Haces la maleta con sensaciones encontradas. Nuevo vestuario, nueva ciudad, nuevo míster. Pero cada sábado juegas. Y eso no tiene precio.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0);
            footballDataService.createOption(d079,
                    "Rechazarla y quedarte a pelear por tu sitio",
                    "Le dices que no. Que prefieres luchar por tu puesto aquí aunque sea desde el banquillo. El director lo respeta, pero te avisa: 'No sé si vas a jugar mucho.'",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1);

// D080 — Triple asistencia en un partido
            Event d080 = footballDataService.createEvent(
                    "Tres asistencias en un partido",
                    "No has marcado, pero tres compañeros han celebrado un gol gracias a ti. Tres pases de gol en un solo partido. Después del último, el comentarista dice por la radio: 'No necesita marcar para ser el mejor del campo.'",
                    "deportivo", "informativo", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d080,
                    "Celebrar cada gol como si fuera tuyo",
                    "Corres a abrazar al goleador las tres veces. El tercer abrazo es el más largo. Eres asistente, pero los tres goles llevan tu firma.",
                    0, 0, 0, 0, 0, 2, 4, 0, 1, 3, 2, 2, 0, 0);
            footballDataService.createOption(d080,
                    "Ir a lo tuyo y buscar tu propio gol",
                    "Tres asistencias están bien, pero quieres el gol. Empiezas a tirar en vez de pasar. No marcas, pero el cuarto pase de gol se queda en la recámara.",
                    0, 0, 1, 0, 0, -1, 1, 0, -1, -1, 0, 0, 0, 0);

// D081 — Entrevista a pie de campo
            Event d081 = footballDataService.createEvent(
                    "Micro en la cara nada más pitar el final",
                    "El partido acaba. Un periodista te intercepta en la banda con un micro y una cámara: 'Gran partido. ¿Cómo te has sentido ahí dentro?' Estás sudando, jadeando y tienes tres segundos para pensar qué decir delante de dos millones de espectadores.",
                    "deportivo", "decision", "common", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d081,
                    "Dar declaraciones completas y hablar bien",
                    "Hablas del equipo, del míster, de la afición. Dices lo correcto. No metes la pata. Tu representante te manda un mensaje: 'Perfecto. Así se habla.'",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0);
            footballDataService.createOption(d081,
                    "Ser espontáneo y decir lo que sientes de verdad",
                    "Sin filtro. Dices lo que piensas. A veces es genial y te hace viral. A veces es polémico y mañana estás en todos los periódicos. El riesgo de la autenticidad.",
                    0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 1, 1, 0, 0);

// D082 — Gol fantasma
            Event d082 = footballDataService.createEvent(
                    "¿Ha entrado o no?",
                    "Tu disparo pega en el larguero, bota en la línea y sale repelido. El linier no señala gol. El VAR revisa. Las imágenes no son concluyentes. Después de tres minutos mirando la pantalla, el árbitro decide: no hay gol. Tú juras que ha entrado.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d082,
                    "Aceptar la decisión y seguir jugando",
                    "Protestas un segundo, respiras y te colocas. El fútbol es injusto a veces. Pero tú sigues.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0);
            footballDataService.createOption(d082,
                    "Protestar enérgicamente",
                    "Te acercas al árbitro con el gesto de 'ha entrado, ha entrado' con las manos. Él te mira aburrido. Amarilla innecesaria.",
                    0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, -1, 0, 0);

// D083 — El utillero veterano te da un consejo
            Event d083 = footballDataService.createEvent(
                    "El utillero que lo ha visto todo",
                    "Después del entrenamiento, el utillero del club —que lleva 30 años ahí y ha visto pasar a tres generaciones de jugadores— te para en el pasillo: 'Chaval, un consejo: no te creas mejor de lo que eres, pero tampoco peor. He visto a muchos quemarse por ambas cosas.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d083,
                    "Agradecerle el consejo y tomártelo en serio",
                    "Le das la mano y le dices gracias. Esa frase se te queda grabada más tiempo del que esperabas. La recuerdas en los momentos difíciles.",
                    0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0);
            footballDataService.createOption(d083,
                    "Escucharlo por educación pero no darle mucha importancia",
                    "Asientes, le sonríes, y sigues tu camino. Es el utillero, no el míster. Aunque a veces los utilleros ven cosas que los míteres no ven.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// D084 — El campo rival te aplaude al salir lesionado
            Event d084 = footballDataService.createEvent(
                    "Sales lesionado y el campo rival te aplaude",
                    "Caes mal tras un salto. Molestia seria en el tobillo. No puedes seguir. Mientras te retiras andando por la banda, la afición RIVAL se pone de pie y aplaude. No te conocen, no les has ganado nada, pero respetan que has dejado todo en el campo.",
                    "deportivo", "informativo", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d084,
                    "Aplaudir de vuelta con emoción contenida",
                    "Levantas la mano y aplauds. Se te humedecen los ojos. Hay momentos en el fútbol que trascienden la rivalidad. Este es uno.",
                    0, 0, 0, -1, 1, 0, 4, 0, 0, 0, 3, 3, 0, 0);
            footballDataService.createOption(d084,
                    "Bajar la cabeza y retirarte con rabia por la lesión",
                    "No puedes disfrutar el aplauso porque la frustración de la lesión es más grande. Te vas al vestuario sin mirar a nadie.",
                    0, 0, 0, -2, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0);

// D085 — Disputas un balón dividido con un amigo del rival
            Event d085 = footballDataService.createEvent(
                    "Balón dividido con tu amigo",
                    "Jugada en el centro del campo. El balón suelto. Tú vas a por él. El que viene por el otro lado es tu mejor amigo de la infancia, que juega en el rival. Os vais a encontrar. Uno de los dos va a llevarse el golpe.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d085,
                    "Ir a por el balón con todo, es tu trabajo",
                    "La amistad es fuera del campo. Dentro, cada uno defiende su camiseta. Llegas primero. Él lo entiende. Al final del partido os abrazáis.",
                    0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d085,
                    "Aflojar un poco en el último momento",
                    "No puedes hacerle daño. Dejas que llegue él. El míster grita desde la banda. Tu amigo te mira un segundo y asiente. Sabe lo que has hecho.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0);

// D086 — Primer partido en un estadio mítico
            Event d086 = footballDataService.createEvent(
                    "Pisas un estadio mítico por primera vez",
                    "Has jugado en muchos campos, pero este es diferente. San Siro, Anfield, el Bernabéu, Maracaná... el nombre da igual, lo que importa es la sensación cuando pisas el césped y miras las gradas. La historia del fútbol está en cada ladrillo.",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d086,
                    "Pararte un segundo a absorber el momento",
                    "Tres segundos mirando las gradas antes del calentamiento. Te prometes a ti mismo recordar esta sensación para siempre.",
                    0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d086,
                    "Tratarlo como un campo más y centrarte en ganar",
                    "Es un campo de fútbol. Con portería, con césped y con un rival que no te va a regalar nada por muy bonito que sea el estadio.",
                    0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0);

// D087 — Marcaje personal asfixiante
            Event d087 = footballDataService.createEvent(
                    "El del 6 te sigue a todas partes",
                    "El defensa rival te ha marcado como un perro guardián. A donde vas, va él. Si te acercas a por agua, casi se viene contigo. Llevas 40 minutos sin tocar un balón porque no te deja ni respirar.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d087,
                    "Moverte más para cansarle y crear espacios para otros",
                    "Si te sigue a ti, no sigue a nadie más. Te conviertes en un señuelo. Cada carrera tuya abre un hueco para un compañero. Inteligencia pura.",
                    0, 0, 0, 0, 0, 2, 1, 0, 2, 1, 0, 0, 0, 0);
            footballDataService.createOption(d087,
                    "Pedir cambio de banda o posición para escapar del marcaje",
                    "Le dices al míster que te ponga en el otro lado. El defensa se queda mirando perdido cinco minutos. Cuando te encuentra, ya has tocado tres balones y dado una asistencia.",
                    0, 1, 0, 0, 0, 1, 2, 0, 1, 0, 0, 0, 0, 0);

// D088 — Gol olímpico
            Event d088 = footballDataService.createEvent(
                    "Saque de esquina y la mandas directa a la red",
                    "Córner tuyo. Te acercas al banderín. Levantas la mano indicando algo al compañero... pero en realidad miras la portería. El portero está adelantado. Hay un hueco. La rosca perfecta podría entrar. ¿Te atreves?",
                    "deportivo", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(d088,
                    "Mandarla directa a portería con rosca",
                    "La pegas con el interior. La rosca es perfecta. El balón entra pegado al primer palo. GOL OLÍMPICO. El estadio no se lo cree. Tú tampoco. Pero está dentro.",
                    0, 2, 3, 0, 1, 0, 7, 0, 0, 1, 5, 5, 1, 0);
            footballDataService.createOption(d088,
                    "Centrar al área como siempre",
                    "No es momento de loterías. Pones un centro al segundo palo. Tu compañero cabezea y la saca el portero. Corner otra vez.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// D089 — Último partido antes de la retirada (futuro lejano)
            Event d089 = footballDataService.createEvent(
                    "El último baile",
                    "Has decidido retirarte. Este es tu último partido como futbolista profesional. El estadio lo sabe. Cuando sales al campo, toda la grada se pone de pie. Aplausos durante dos minutos. El capitán del rival se acerca a darte la mano. Tu familia llora en la grada. Tú intentas no llorar. No lo consigues.",
                    "deportivo", "unico", "legendary", "profesional", 33, 42, 1, 3, null
            );
            footballDataService.createOption(d089,
                    "Jugar los 90 minutos como si fueras inmortal",
                    "No quieres salir. No quieres que acabe. Juegas cada minuto como si fuera el primero. Cuando el árbitro pita el final, te arrodillas en el centro del campo y besas el césped.",
                    0, 0, 0, 0, 3, 0, 10, 0, 3, 3, 5, 5, 2, 3);
            footballDataService.createOption(d089,
                    "Salir en el 80 para recibir la ovación de pie",
                    "Le pides al míster que te cambie en el 80 para poder caminar solo por la banda, aplaudiendo a la grada que te ha acompañado toda tu carrera. El estadio canta tu nombre. Es perfecto.",
                    0, 0, 0, 0, 2, 0, 10, 0, 3, 3, 5, 5, 2, 3);

// D090 — Asistencia en el último segundo que gana un título
            Event d090 = footballDataService.createEvent(
                    "Último segundo. Un pase para la historia.",
                    "Final de copa. Empate. Minuto 93. Tienes el balón en la frontal. Hay tres defensas delante. Ves un hueco imposible. Tu compañero está en offside... no, no lo está. Ahora o nunca.",
                    "deportivo", "decision", "epic", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d090,
                    "Meter el pase imposible",
                    "El balón viaja entre las piernas de dos defensas, supera al tercero por velocidad y llega a tu compañero que define a placer. GOOOL. El estadio explota. Tú caes de rodillas. No has marcado, pero esa asistencia vale un título.",
                    0, 1, 0, 0, 2, 3, 8, 200, 2, 3, 5, 4, 1, 2);
            footballDataService.createOption(d090,
                    "Tirar tú a portería",
                    "No hay tiempo para pensar en pases. Disparo desde la frontal. Rebote en un defensa. Corner. Se acabó el tiempo. Penaltis. Otra historia.",
                    0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0);

// D091 — Ganar el Balón de Oro
            Event d091 = footballDataService.createEvent(
                    "Tu nombre en el Balón de Oro",
                    "Gala en París. Traje, alfombra roja, cámaras de todo el mundo. Leen la lista de finalistas. Tu nombre suena. La sala se queda en silencio. Abren el sobre. Tu nombre otra vez. Has ganado el Balón de Oro. El trofeo dorado brilla bajo los focos mientras subes al escenario con las piernas temblando.",
                    "deportivo", "unico", "legendary", "profesional", 24, 36, 3, 3, null
            );
            footballDataService.createOption(d091,
                    "Dar un discurso emotivo agradeciendo a todos los que te han ayudado",
                    "Nombras a tu padre, a tu madre, al míster de la cantera, al utillero, a tus compañeros. Lloras sin vergüenza. El discurso se hace viral. No solo eres el mejor jugador del mundo. Eres un tío de los buenos.",
                    0, 0, 0, 0, 0, 0, 10, 500, 3, 3, 5, 5, 3, 3);
            footballDataService.createOption(d091,
                    "Ser breve, elegante y dejar que el trofeo hable por ti",
                    "'Gracias a todos. Esto es un sueño.' Tres frases. Una reverencia. Te bajas del escenario con el trofeo y una sonrisa que no puedes controlar.",
                    0, 0, 0, 0, 2, 0, 10, 500, 2, 2, 4, 4, 3, 3);

// D092 — Ganar la Bota de Oro
            Event d092 = footballDataService.createEvent(
                    "Máximo goleador de Europa",
                    "La temporada ha terminado y los números hablan: eres el máximo goleador de todas las ligas europeas. La Bota de Oro es tuya. Los que dudaban de ti cuando empezabas ya no tienen argumentos. Tus goles les han callado la boca uno a uno.",
                    "deportivo", "unico", "epic", "profesional", 22, 36, 2, 3, null
            );
            footballDataService.createOption(d092,
                    "Dedicarlo a los que confiaron cuando nadie lo hacía",
                    "En la rueda de prensa dices: 'Esto es para los que estaban cuando nadie venía a verme jugar.' Tu primer entrenador te manda un mensaje. Solo dice: 'Sabía que llegarías.'",
                    0, 0, 0, 0, 0, 0, 8, 300, 2, 2, 3, 4, 1, 2);
            footballDataService.createOption(d092,
                    "Tomarlo como el primer paso hacia algo más grande",
                    "La Bota está bien. Pero tú quieres el Balón. Y la Champions. Y el Mundial. Esto es el principio, no el final.",
                    0, 0, 0, 0, 1, 0, 6, 300, 1, 0, 2, 2, 1, 1);

// D093 — Partido del Mundial
            Event d093 = footballDataService.createEvent(
                    "Juegas un partido del Mundial",
                    "Has soñado con esto desde que tenías cinco años. Un Mundial. Tu selección. El himno con la mano en el pecho. Miles de compatriotas en la grada. Millones viéndolo por televisión. Todo lo que has hecho en tu carrera te ha llevado a este césped.",
                    "deportivo", "decision", "legendary", "profesional", 22, 38, 2, 3, null
            );
            footballDataService.createOption(d093,
                    "Jugar el partido de tu vida",
                    "Es ahora o nunca. Sales al campo y juegas como nunca has jugado. Cada pase es perfecto. Cada carrera es al espacio correcto. El seleccionador te abraza al sustituirte. 'Eres un crack', te dice al oído.",
                    0, 1, 1, 0, 3, 1, 8, 0, 2, 2, 5, 5, 0, 0);
            footballDataService.createOption(d093,
                    "Controlar los nervios y ejecutar lo que el seleccionador pide",
                    "No es momento de ser el héroe. Es momento de ser el engranaje perfecto. Haces lo que te piden al milímetro. Y eso es exactamente lo que el equipo necesita.",
                    0, 0, 0, 0, 2, 1, 6, 0, 3, 2, 3, 3, 0, 0);

// D094 — Gol que gana un Mundial
            Event d094 = footballDataService.createEvent(
                    "El gol de tu vida. En la final del Mundial.",
                    "Final del Mundial. Empate. Prórroga. Minuto 118. Recibes un pase en la frontal del área. Recortas. Uno. Dos defensas superados. El portero sale. Tú disparas. El balón entra. El mundo se para. Has marcado el gol que le da el Mundial a tu país.",
                    "deportivo", "unico", "legendary", "profesional", 24, 38, 3, 3, null
            );
            footballDataService.createOption(d094,
                    "Correr sin saber a dónde, con los compañeros encima",
                    "No sabes dónde corres. No sabes qué haces. Solo sabes que todo el equipo está encima tuyo y que en algún rincón del mundo, tu padre está llorando como un niño. Acabas de hacer inmortal tu nombre.",
                    0, 0, 0, 0, 0, 0, 10, 1000, 3, 3, 5, 5, 3, 3);
            footballDataService.createOption(d094,
                    "Caer de rodillas y mirar al cielo",
                    "De rodillas. Mirando arriba. Llorando. No hay celebración más honesta. Has llegado a lo más alto que puede llegar un futbolista. Y ha merecido cada segundo del camino.",
                    0, 0, 0, 0, 3, 0, 10, 1000, 3, 3, 5, 5, 3, 3);

            // ===== EVENTOS DE FICHAJE — F001 a F025 =====
// Categoría: fichaje | Fase: profesional

// F001 — Primer interés
            Event f001 = footballDataService.createEvent(
                    "Tu representante te llama",
                    "Suena el teléfono después del entrenamiento. Tu representante va directo al grano: 'Oye, hay un club que ha preguntado por ti. Todavía no hay nada concreto, pero el interés es real. ¿Cómo lo ves?'",
                    "fichaje", "decision", "common", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(f001,
                    "Quiero saber quién es",
                    "Tu representante te da el nombre. No es un grande, pero es un paso adelante. La rueda empieza a girar.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(f001,
                    "De momento prefiero centrarme en mi club",
                    "'Entendido. Les digo que de momento no hay movimiento.' Tu representante cuelga. La oferta queda en el aire, pero tú sigues con la cabeza en lo tuyo.",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0);
            footballDataService.createOption(f001,
                    "Pregunta qué papel tendría allí",
                    "'Antes de decir nada, quiero saber qué pintan para mí.' Tu representante asiente: 'Eso me gusta. Jugador que pregunta antes de saltar.'",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0);

// F002 — Ojeador te sigue
            Event f002 = footballDataService.createEvent(
                    "Alguien te está observando",
                    "Después del partido, un tipo con carpeta y chaqueta de club se acerca a tu representante en la grada. Hablan tres minutos. Tu representante baja y te dice: 'Llevamos varios partidos siguiéndote. Es del {club}.'",
                    "fichaje", "decision", "common", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(f002,
                    "Preguntar qué les parece tu rendimiento",
                    "'¿Y qué dicen? ¿Les gusta lo que ven?' Tu representante sonríe: 'Si no les gustara, no seguirían viniendo.'",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f002,
                    "No darle importancia y seguir a lo tuyo",
                    "Levantas los hombros: 'Que miren lo que quieran.' Sigues entrenando sin cambiar nada. Si les gusta, ya vendrán.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(f002,
                    "Preguntar si están interesados en ficharte",
                    "'¿Van en serio o están mirando escaparates?' Tu representante te frena: 'Tranquilo, estas cosas llevan su tiempo. Déjame a mí.'",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);

// F003 — El interés es real
            Event f003 = footballDataService.createEvent(
                    "La cosa se pone seria",
                    "Tu representante te cita para un café: 'El interés del {club} es real. Todavía no hay oferta, pero quieren seguir viéndote. Me han pedido informes, vídeos, todo. ¿Cómo lo gestionamos?'",
                    "fichaje", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(f003,
                    "Que sigan observándome, no tengo prisa",
                    "Paciencia. Si el interés es real, la oferta llegará. Mientras tanto, sigues rindiendo.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(f003,
                    "Diles que contacten ya con mi club",
                    "'Si quieren, que den el paso.' Tu representante asiente y hace la llamada. Las cosas se aceleran.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(f003,
                    "No me interesa, diles que no",
                    "'Gracias pero no.' Tu representante te mira sorprendido. 'Vale, como quieras.' El interés se enfría.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, -1, 0);

// F004 — Cuánto cuesta sacarte
            Event f004 = footballDataService.createEvent(
                    "Han preguntado tu precio",
                    "Tu representante te llama con voz seria: 'El {club} ha preguntado cuánto costaría sacarte de aquí. Esto ya no es un rumor. Esto es una negociación.'",
                    "fichaje", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(f004,
                    "Escuchar la propuesta",
                    "'A ver, ¿qué ponen sobre la mesa?' La cifra no está mal. Tu cabeza empieza a hacer cálculos.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(f004,
                    "No quiero salir de aquí",
                    "'Diles que no estoy en venta.' Tu representante suspira pero transmite el mensaje. Tu directiva respira aliviada.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 2);
            footballDataService.createOption(f004,
                    "Que hablen con mi club directamente",
                    "'Yo no me meto en cifras. Que los clubes se entiendan entre ellos.' Tu representante gestiona. Tú sigues entrenando.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);

// F005 — Oferta oficial
            Event f005 = footballDataService.createEvent(
                    "Ha llegado una oferta por ti",
                    "El director deportivo te para en el pasillo: 'Oye, ha llegado una oferta oficial del {club}. No te voy a mentir, es una cifra importante. Pero la decisión es tuya. ¿Qué quieres hacer?'",
                    "fichaje", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(f005,
                    "Quiero saber cuánto ofrecen",
                    "El director te dice la cifra. Es más de lo que esperabas. Tu mente se divide entre la ilusión y el miedo a lo desconocido.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 2, 0);
            footballDataService.createOption(f005,
                    "Quiero quedarme",
                    "'Mi sitio es aquí.' El director asiente con una media sonrisa. La directiva descorcha una botella imaginaria.",
                    0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 3);
            footballDataService.createOption(f005,
                    "Estoy dispuesto a escuchar",
                    "'No digo que sí ni que no. Pero quiero saber qué me ofrecen.' El director asiente: 'Te entiendo. Hablaré con ellos.'",
                    0, 0, 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 2, 0);

// F006 — Oferta baja pero con cariño
            Event f006 = footballDataService.createEvent(
                    "La oferta no es para tirar cohetes",
                    "Tu representante se sienta contigo: 'Mira, la oferta no es especialmente alta, pero el {club} te quiere de verdad. El entrenador ha pedido tu fichaje personalmente. ¿Eso cuenta para algo?'",
                    "fichaje", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(f006,
                    "Intentar negociar una mejora",
                    "'Si me quieren de verdad, que lo demuestren con el contrato.' Tu representante vuelve a llamar.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f006,
                    "Aceptarla, el proyecto deportivo importa más",
                    "No todo es dinero. Un entrenador que te quiere vale más que cien mil euros de diferencia. Firmas.",
                    0, 0, 0, 0, 1, 0, 2, 0, 3, 0, 0, 0, 1, 0);
            footballDataService.createOption(f006,
                    "Rechazarla, no me compensa",
                    "'Si no mejoran, no me muevo.' La oferta se cae. Puede que aparezca otra. O puede que no.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0);

// F007 — Rol de rotación
            Event f007 = footballDataService.createEvent(
                    "No serías titular",
                    "Tu representante te lo suelta sin anestesia: 'El {club} te quiere, pero no te garantizan titularidad. Irías como jugador de rotación. Más dinero, menos minutos. ¿Te compensa?'",
                    "fichaje", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(f007,
                    "Me interesa igualmente, ya me ganaré el puesto",
                    "Confías en ti. Llegas como rotación, pero en seis meses eres titular. Al menos ese es el plan.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f007,
                    "Quiero ser titular o no me muevo",
                    "'Si no juego, no voy.' Tu representante lo entiende. La negociación se complica.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(f007,
                    "Pregunta cuántos minutos tendría",
                    "'Antes de decidir, quiero números.' Tu representante negocia garantías. No siempre se consiguen.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0);

// F008 — Papel importante desde el primer día
            Event f008 = footballDataService.createEvent(
                    "Te quieren como pieza clave",
                    "Tu representante te llama con tono diferente: 'El {club} no te quiere para completar plantilla. Te quiere como pieza importante desde el primer día. Titularidad garantizada y un proyecto deportivo serio.'",
                    "fichaje", "decision", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(f008,
                    "Quiero ir, me convence el proyecto",
                    "Un club que te pone en el centro de su plan. Eso no pasa todos los días. Aceptas.",
                    0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 0, 2, 0);
            footballDataService.createOption(f008,
                    "Antes de decidir, quiero saber el salario",
                    "'El proyecto mola, pero ¿cuánto pagan?' Tu representante levanta una ceja. 'Eso sí, se nota que eres profesional.'",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f008,
                    "Quiero conocer más del proyecto antes de moverme",
                    "Pides una reunión con el director deportivo del {club}. Quieres oírlo de su boca, no de un intermediario.",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0);

// F009 — Club de Champions
            Event f009 = footballDataService.createEvent(
                    "Un club de Champions pregunta por ti",
                    "Tu representante te llama y notas que le tiembla la voz: 'Siéntate. Ha preguntado por ti un club que juega la Champions League. No es broma. Van en serio.'",
                    "fichaje", "decision", "epic", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(f009,
                    "Quiero escucharles, esto puede ser el salto",
                    "Champions League. Noches europeas. El himno. Los viajes. Esto es por lo que luchas desde que tenías 16 años.",
                    0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 1, 3, 0);
            footballDataService.createOption(f009,
                    "No quiero moverme todavía, no estoy preparado",
                    "La Champions puede esperar. Mejor ir cuando estés listo que llegar demasiado pronto y quemarte.",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, -1, 0);
            footballDataService.createOption(f009,
                    "Pregunta qué papel tendría yo ahí",
                    "'Champions sí, pero ¿para ir al banquillo? Necesito saber qué me ofrecen.' Prudencia antes que ilusión.",
                    0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 2, 0);

// F010 — Interés de la Premier
            Event f010 = footballDataService.createEvent(
                    "Inglaterra te quiere",
                    "Tu representante suelta la bomba: 'Hay interés de un club de la Premier League. El fútbol inglés. Otra liga, otro ritmo, otro país. Pero el dinero... bueno, ya sabes cómo pagan los ingleses.'",
                    "fichaje", "decision", "epic", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f010,
                    "Quiero conocer la oferta",
                    "La cifra que dice tu representante te hace parpadear dos veces. La Premier no bromea con los salarios.",
                    0, 0, 0, 0, 0, 0, 2, 100, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(f010,
                    "Prefiero seguir en mi liga",
                    "'Estoy bien aquí. No necesito cambiar de país.' Tu representante respeta la decisión.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0);
            footballDataService.createOption(f010,
                    "Me interesa jugar en Inglaterra",
                    "La Premier siempre te ha atraído. El ritmo, los estadios llenos, la intensidad. Esta puede ser tu oportunidad.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 2, 0);

// F011 — Interés de la Bundesliga
            Event f011 = footballDataService.createEvent(
                    "Llamada desde Alemania",
                    "'Un club de la Bundesliga ha preguntado por ti. Alemania, tío. Buen fútbol, estadios a reventar y un proyecto serio.' Tu representante parece entusiasmado.",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f011,
                    "Escuchar qué ofrecen",
                    "Alemania suena bien. Fútbol táctico, público fiel, y clubes que apuestan por jóvenes. Pides más información.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(f011,
                    "No me veo en Alemania",
                    "'Paso. No es mi sitio.' Tu representante no insiste. Cada uno sabe dónde quiere jugar.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// F012 — Interés de Ligue 1
            Event f012 = footballDataService.createEvent(
                    "Francia llama a tu puerta",
                    "'Hay interés de un club de la Ligue 1. No es el PSG, pero es un club serio con proyecto.' Tu representante te lo plantea como una opción real.",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f012,
                    "Escuchar la propuesta",
                    "Francia tiene su encanto. Liga competitiva, buen nivel de vida, y un fútbol que cada vez pesa más en Europa.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(f012,
                    "Quedarme donde estoy",
                    "'Estoy cómodo aquí. Gracias pero no.' El interés se apaga sin drama.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0);

// F013 — Club portugués
            Event f013 = footballDataService.createEvent(
                    "Apuesta de futuro desde Portugal",
                    "'Un club portugués te ve como una apuesta de futuro. No buscan que seas titular ya, pero te ven potencial para ser una pieza importante en dos años.' Tu representante te lo presenta como un movimiento a medio plazo.",
                    "fichaje", "decision", "uncommon", "profesional", 18, 26, 1, 3, null
            );
            footballDataService.createOption(f013,
                    "Aceptar el proyecto, pienso a largo plazo",
                    "Paciencia. Aceptas un rol menor ahora con la promesa de ser protagonista después. Es una apuesta.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f013,
                    "Quiero minutos ya, no promesas",
                    "'Si no juego desde el primer día, no me interesa.' Tu representante transmite el mensaje.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// F014 — Interés de Brasil
            Event f014 = footballDataService.createEvent(
                    "Brasil suena tentador",
                    "'Mira, sé que suena raro, pero un club brasileño está interesado. Buen dinero, estadios de locos y una liga que cada vez exporta más. ¿Te lo piensas al menos?'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f014,
                    "Escuchar, ¿por qué no?",
                    "Brasil. Fútbol, sol y Samba. Hay carreras brillantes que han pasado por allí. ¿Por qué no la tuya?",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f014,
                    "No me interesa salir de Europa",
                    "'Mi carrera está en Europa. Gracias pero no.' La puerta se cierra sin portazo.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// F015 — Interés de Argentina
            Event f015 = footballDataService.createEvent(
                    "Un club argentino te quiere",
                    "'Boca. River. Racing. No, no te voy a decir cuál hasta que me digas que te interesa.' Tu representante disfruta el suspense.",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f015,
                    "Dime quién es, me pica la curiosidad",
                    "El nombre del club te hace levantar una ceja. Argentina tiene equipos con historia para aburrir. Merecen al menos una escucha.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f015,
                    "Argentina queda lejos, prefiero quedarme",
                    "'No me veo cruzando el charco.' Tu representante lo respeta sin insistir.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// F016 — Championship
            Event f016 = footballDataService.createEvent(
                    "El Championship te busca",
                    "'Un club del Championship quiere saber tu situación. No es Premier, pero el Championship es una liga brutal. Estadios llenos, partidos cada tres días y una intensidad que no ves en otras ligas.'",
                    "fichaje", "decision", "common", "profesional", 18, 34, 1, 2, null
            );
            footballDataService.createOption(f016,
                    "Me interesa, es un buen escaparate",
                    "El Championship es la puerta a la Premier. Si rindes ahí, arriba te ven seguro.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f016,
                    "No me interesa, busco algo más arriba",
                    "Apuntas más alto. El Championship no entra en tus planes. Al menos por ahora.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// F017 — En la lista de objetivos
            Event f017 = footballDataService.createEvent(
                    "Estás en la lista",
                    "Tu representante te enseña una captura de pantalla: un medio deportivo italiano filtra la lista de objetivos del {club} para la próxima temporada. Tu nombre está. Tercero de la lista.",
                    "fichaje", "decision", "epic", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f017,
                    "Seguir interesado y ver si pasan a la acción",
                    "Estar en la lista no significa nada. Pero tampoco significa nada malo. Esperas.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 1, 0);
            footballDataService.createOption(f017,
                    "Pedir más información a tu representante",
                    "'¿Tercero? ¿Quiénes son los otros dos?' Tu representante se ríe: 'Lo importante es que estás.'",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0);

// F018 — Dos clubes a la vez
            Event f018 = footballDataService.createEvent(
                    "Dos clubes te quieren",
                    "Tu representante te sienta: 'Tenemos un problema bonito. Hay dos clubes preguntando por ti a la vez. El {club} ofrece más minutos. El otro ofrece más dinero. ¿Qué priorizas?'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f018,
                    "Escuchar a ambos antes de decidir",
                    "No cierras ninguna puerta. Tu representante habla con los dos. La competencia entre clubes puede mejorar las condiciones.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(f018,
                    "Priorizar al que ofrezca más minutos",
                    "El dinero va y viene. Los minutos se juegan ahora. Eliges fútbol por encima de cartera.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f018,
                    "Priorizar al club más grande",
                    "El nombre pesa. Un club grande en el currículum abre puertas que un club pequeño no puede.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 1, 0);

// F019 — Tres clubes interesados
            Event f019 = footballDataService.createEvent(
                    "El mercado se dispara",
                    "'Siéntate que esto es gordo. Ahora mismo hay tres clubes interesados en ti. Tres. Tu valor se ha disparado esta temporada.' Tu representante no cabe en sí de la emoción.",
                    "fichaje", "decision", "epic", "profesional", 22, 38, 1, 3, null
            );
            footballDataService.createOption(f019,
                    "Escuchar todas las ofertas",
                    "Tres opciones sobre la mesa. Cada una con sus pros y sus contras. La decisión más importante de tu carrera hasta ahora.",
                    0, 0, 0, 0, 0, 0, 3, 0, -1, 0, 0, 1, 3, 0);
            footballDataService.createOption(f019,
                    "Elegir el mejor proyecto deportivo",
                    "'Me da igual el dinero. Quiero jugar donde vaya a ser mejor futbolista.' Tu representante suspira pero obedece.",
                    0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f019,
                    "Esperar a final de temporada para decidir",
                    "'No quiero distracciones ahora. En junio hablamos.' Los tres clubes esperan. Puede que alguno se canse.",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0);

// F020 — Club inesperado
            Event f020 = footballDataService.createEvent(
                    "Ha aparecido un club que no esperabas",
                    "Cuando creías que la decisión estaba entre dos opciones, tu representante te llama a las once de la noche: 'Ha entrado un club que no esperábamos. Y es grande. MUY grande. Esto lo cambia todo.'",
                    "fichaje", "decision", "legendary", "profesional", 22, 38, 2, 3, null
            );
            footballDataService.createOption(f020,
                    "Priorizarlo, es una oportunidad irrepetible",
                    "El nombre del {topClub} sobre la mesa cambia todas las ecuaciones. Algunos trenes solo pasan una vez.",
                    0, 0, 0, 0, 0, 0, 4, 0, -2, -1, 0, 2, 3, 0);
            footballDataService.createOption(f020,
                    "Mantener las negociaciones anteriores",
                    "No vas a dejar plantado a un club que lleva semanas negociando por un recién llegado. La lealtad también cuenta en el mercado.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f020,
                    "Pedir tiempo para pensar",
                    "'Dame 48 horas.' Tu representante acepta. Cuarenta y ocho horas de insomnio.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// F021 — Cesión
            Event f021 = footballDataService.createEvent(
                    "El club quiere cederte",
                    "El director deportivo te cita en su despacho: 'Mira, creemos en ti, pero aquí no vas a tener los minutos que necesitas. Hemos hablado con el {club} y están dispuestos a acogerte una temporada. ¿Qué opinas?'",
                    "fichaje", "decision", "uncommon", "profesional", 18, 26, 1, 3, null
            );
            footballDataService.createOption(f021,
                    "Aceptar la cesión, necesito jugar",
                    "Haces la maleta con sensaciones encontradas. Nuevo vestuario, nueva ciudad. Pero cada sábado juegas. Y eso no tiene precio.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 1, 0);
            footballDataService.createOption(f021,
                    "Rechazarla y quedarme a pelear por mi sitio",
                    "'No me voy. Prefiero luchar por mi puesto aquí.' El director lo respeta. 'Vale, pero no sé si vas a jugar mucho.'",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            footballDataService.createOption(f021,
                    "Aceptar pero quiero elegir el destino",
                    "'Me voy, pero quiero saber a dónde. No me mandéis a cualquier sitio.' El director asiente: 'Te damos dos opciones.'",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0);

// F022 — Cesión con opción de compra
            Event f022 = footballDataService.createEvent(
                    "Cesión con truco",
                    "Tu representante te avisa: 'El {club} quiere incluir una opción de compra en la cesión. Eso significa que si rindes bien, pueden ficharte definitivamente sin que tu club pueda impedirlo.'",
                    "fichaje", "decision", "rare", "profesional", 18, 28, 1, 3, null
            );
            footballDataService.createOption(f022,
                    "Aceptar la opción de compra",
                    "Si rindes bien, te quedas. Si no, vuelves. Es un win-win. Al menos eso parece.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f022,
                    "Intentar eliminarla, quiero volver a mi club",
                    "'Quiero ir cedido pero quiero volver. Que quiten la opción.' La negociación se complica.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);

// F023 — Cesión a un rival directo
            Event f023 = footballDataService.createEvent(
                    "Cesión a un rival de tu propia liga",
                    "Tu representante frunce el ceño: 'Hay una opción de cesión, pero es a un club de tu misma competición. Es un rival directo. Tu directiva duda porque si rindes bien, les puedes hacer daño.'",
                    "fichaje", "decision", "uncommon", "profesional", 18, 28, 1, 3, null
            );
            footballDataService.createOption(f023,
                    "Aceptar, me da igual contra quién juegue",
                    "Fútbol es fútbol. Te da igual la camiseta del rival. Necesitas minutos.",
                    0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 1, -1);
            footballDataService.createOption(f023,
                    "Pedir garantías de minutos antes de decidir",
                    "'Si voy a un rival, al menos que sea para jugar.' Tu representante negocia un mínimo de minutos.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0);

// F024 — Cesión al extranjero
            Event f024 = footballDataService.createEvent(
                    "La cesión es fuera del país",
                    "'La única opción de cesión que ha aparecido es en el extranjero. Otro país, otro idioma, otra liga. Pero también otra experiencia que te puede hacer crecer como futbolista y como persona.'",
                    "fichaje", "decision", "rare", "profesional", 18, 28, 1, 3, null
            );
            footballDataService.createOption(f024,
                    "Aceptar la aventura",
                    "Maleta grande, pasaporte y una liga nueva. Los primeros meses son duros. Pero cuando empiezas a jugar, todo encaja.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f024,
                    "Prefiero quedarme en mi país",
                    "'No estoy preparado para irme tan lejos.' Tu representante busca otras opciones más cerca.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// F025 — Vuelta de cesión
            Event f025 = footballDataService.createEvent(
                    "Has vuelto de la cesión. ¿Y ahora qué?",
                    "Un año fuera. Has jugado, has crecido, has cambiado. Vuelves a tu club de origen y el entrenador te para el primer día: 'Has vuelto. Ahora tenemos que decidir qué hacemos contigo. ¿Tú qué quieres?'",
                    "fichaje", "decision", "uncommon", "profesional", 19, 28, 1, 3, null
            );
            footballDataService.createOption(f025,
                    "Quedarme y competir por un puesto",
                    "'Estoy listo. He ido a curtirme y he vuelto mejor.' El entrenador asiente: 'Eso espero. Demuéstralo.'",
                    0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(f025,
                    "Buscar otra cesión, necesito más minutos",
                    "'Todavía no estoy preparado para ser titular aquí. Prefiero seguir jugando fuera.' Otro año de aprendizaje.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 1, 0);
            footballDataService.createOption(f025,
                    "Pedir salir definitivamente",
                    "'No quiero seguir cedido. O me quedo de verdad o me voy para siempre.' El entrenador levanta las cejas. Decisión valiente.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 2, -1);
            

// F026 — Renovación básica
            Event f026 = footballDataService.createEvent(
                    "El club quiere que te quedes",
                    "El director deportivo te para después del entrenamiento: 'Tu contrato entra en su última fase. Queremos que sigas con nosotros. ¿Hablamos?'",
                    "fichaje", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(f026,
                    "Renovar, estoy bien aquí",
                    "Firmas sin pensarlo mucho. Es tu casa. La estabilidad tiene un valor que no se mide en euros.",
                    0, 0, 0, 0, 0, 0, 1, 50, 2, 2, 2, 0, 0, 3);
            footballDataService.createOption(f026,
                    "Esperar a ver si llegan ofertas",
                    "'De momento no firmo nada. Quiero ver qué pasa en el mercado.' La directiva frunce el ceño. El ambiente se enfría.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -1, 0, 1, -2);
            footballDataService.createOption(f026,
                    "Renovar pero pidiendo mejores condiciones",
                    "'Quiero quedarme, pero mi rendimiento merece un contrato mejor.' Empieza la negociación.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0);

// F027 — Renovación con mejora salarial
            Event f027 = footballDataService.createEvent(
                    "Te suben el sueldo",
                    "Tu representante llega con una sonrisa de las grandes: 'El club quiere renovarte y mejorar tu salario. Bastante. Parece que por fin reconocen lo que vales.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f027,
                    "Aceptar la renovación con aumento",
                    "Firmas con una sonrisa. Más dinero, misma casa. La combinación perfecta.",
                    0, 0, 0, 0, 0, 0, 2, 150, 2, 1, 1, 0, 1, 3);
            footballDataService.createOption(f027,
                    "Negociar aún más",
                    "'Está bien, pero creo que puedo sacar más.' Tu representante levanta una ceja: '¿Seguro?' La avaricia rompe el saco a veces.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 2, -1);
            footballDataService.createOption(f027,
                    "Esperar a ver si hay ofertas mejores de fuera",
                    "'Antes de firmar, quiero saber si hay algo mejor ahí fuera.' El club no se lo toma bien.",
                    0, 0, 0, 0, 0, 0, 0, 0, -2, 0, -1, 0, 1, -2);

// F028 — Renovación con garantías deportivas
            Event f028 = footballDataService.createEvent(
                    "Dinero no, pero minutos sí",
                    "Tu representante te lo plantea claro: 'No te ofrecen mucho más dinero, pero te garantizan ser pieza importante del proyecto. Titularidad y un plan para ti. ¿El dinero lo es todo?'",
                    "fichaje", "decision", "uncommon", "profesional", 18, 34, 1, 3, null
            );
            footballDataService.createOption(f028,
                    "Aceptar, el proyecto deportivo importa más",
                    "No todo se mide en cifras. Un entrenador que confía en ti y minutos garantizados valen más que un aumento.",
                    0, 0, 0, 0, 1, 0, 1, 0, 3, 1, 0, 0, 0, 2);
            footballDataService.createOption(f028,
                    "Pedir más dinero además de las garantías",
                    "'Las garantías están bien, pero el bolsillo también importa.' La negociación se alarga.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 1, -1);
            footballDataService.createOption(f028,
                    "Rechazar, busco algo más ambicioso",
                    "'Agradezco la confianza, pero creo que puedo aspirar a más.' Decisión arriesgada.",
                    0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 1, -2);

// F029 — Renovación complicada
            Event f029 = footballDataService.createEvent(
                    "Las negociaciones se atascan",
                    "Tu representante te llama con tono serio: 'La renovación se está complicando. Estamos lejos de lo que pides y lo que ofrecen. Si no cedemos algo, esto se rompe.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f029,
                    "Ceder en algunas condiciones para cerrar",
                    "'Vale, que no se rompa por mi culpa. Cedo en el bonus y firmamos.' Tu representante cierra el acuerdo. No es perfecto, pero es un acuerdo.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2);
            footballDataService.createOption(f029,
                    "Mantener tu postura, no ceder",
                    "'Si no llegan a lo que pido, no firmo.' Las negociaciones siguen estancadas. La prensa empieza a rumorear.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -1, -1, 1, -1);
            footballDataService.createOption(f029,
                    "Romper las negociaciones",
                    "'Se acabó. No voy a renovar.' El vestuario se entera. La directiva se enfada. La afición empieza a murmurar.",
                    0, 0, 0, 0, 0, 0, -1, 0, -3, -1, -3, -2, 1, -3);

// F030 — Contrato en su último año
            Event f030 = footballDataService.createEvent(
                    "Último año de contrato",
                    "Tu representante te recuerda lo que ya sabes: 'Te queda un año de contrato. Si no renuevas, en seis meses puedes negociar libremente con quien quieras. Tu club lo sabe. Y están nerviosos.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f030,
                    "Renovar y acabar con la incertidumbre",
                    "Firmas y todo el mundo respira. La estabilidad tiene un valor.",
                    0, 0, 0, 0, 0, 0, 1, 50, 2, 1, 1, 0, 0, 3);
            footballDataService.createOption(f030,
                    "Esperar a ser libre y elegir sin presiones",
                    "La libertad total en seis meses. Puedes negociar con quien quieras sin que tu club vea un euro. Es poder puro.",
                    0, 0, 0, 0, 0, 0, 2, 0, -2, -1, -2, 1, 3, -2);
            footballDataService.createOption(f030,
                    "Escuchar ofertas mientras decides",
                    "No cierras ninguna puerta. Tu club negocia la renovación mientras tu representante tantea el mercado. Juego peligroso.",
                    0, 0, 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 2, -1);

// F031 — Cláusula de rescisión
            Event f031 = footballDataService.createEvent(
                    "Quieren subir tu cláusula",
                    "En la renovación, el club pide aumentar tu cláusula de rescisión. Tu representante traduce: 'Quieren blindarte. Si tu cláusula sube, es más difícil que alguien te saque de aquí.'",
                    "fichaje", "decision", "uncommon", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f031,
                    "Aceptar una cláusula alta, estoy comprometido",
                    "Cláusula alta = confianza del club en ti. Firmas. Eres intocable a menos que alguien pague una barbaridad.",
                    0, 0, 0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 3);
            footballDataService.createOption(f031,
                    "Pedir una cláusula más baja para tener libertad",
                    "'No quiero cerrarme puertas.' El club no está contento. Tu representante lo ve como una jugada inteligente.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 2, -1);
            footballDataService.createOption(f031,
                    "Aceptar la cláusula a cambio de mejor salario",
                    "'Subo la cláusula si subís el sueldo.' Negociación clásica. Los dos ceden algo.",
                    0, 0, 0, 0, 0, 0, 0, 80, 0, 0, 0, 0, 1, 1);

// F032 — El club rechaza una oferta por ti
            Event f032 = footballDataService.createEvent(
                    "Tu club dice que no",
                    "Tu representante te lo cuenta frustrado: 'La oferta del {club} era buena. Tú estabas dispuesto. Pero tu club la ha rechazado. Dicen que no es suficiente.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f032,
                    "Presionar para salir",
                    "'Quiero irme y ellos lo saben.' La relación con la directiva se tensa. Pero a veces hay que forzar las cosas.",
                    0, 0, 0, 0, 0, 0, 0, 0, -2, -1, -2, -1, 1, -3);
            footballDataService.createOption(f032,
                    "Respetar la decisión del club",
                    "'Si no hay acuerdo, no hay acuerdo. Sigo aquí.' La directiva te lo agradece. Tu representante menos.",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, -1, 2);
            footballDataService.createOption(f032,
                    "Pedir que negocien de nuevo",
                    "'Que vuelvan a hablar. Seguro que hay un punto medio.' Tu representante vuelve a la carga.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0);

// F033 — Tú rechazas una oferta
            Event f033 = footballDataService.createEvent(
                    "No te convence",
                    "Tu representante te presenta la oferta al detalle y tú niegas con la cabeza: 'No. No me veo ahí.' Él insiste: '¿Estás seguro? El dinero es bueno.' 'Seguro.'",
                    "fichaje", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(f033,
                    "Confirmar el rechazo",
                    "No es tu sitio y punto. Mejor quedarte donde estés contento que irte donde no lo vas a estar.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(f033,
                    "Cambiar de opinión y pedir que vuelvan a hablar",
                    "Dos horas después la duda te come. '¿Y si era una buena oportunidad?' Le dices a tu representante que vuelva a llamar.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);

// F034 — Oferta mejorada
            Event f034 = footballDataService.createEvent(
                    "Han subido la oferta",
                    "Tu representante te llama con voz cantarina: 'El {club} no se rinde. Han vuelto con una oferta bastante superior. Esta sí que es seria.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f034,
                    "Ahora sí me interesa",
                    "La nueva cifra te hace cambiar de opinión. El salto empieza a tener sentido.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 2, 0);
            footballDataService.createOption(f034,
                    "Sigo sin verlo claro",
                    "Más dinero no cambia el hecho de que no te convence el proyecto. Te mantienes.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, -1, 0);

// F035 — Última oferta
            Event f035 = footballDataService.createEvent(
                    "Tómalo o déjalo",
                    "Tu representante te lo dice con cara seria: 'Esta es su última oferta. No van a subir más. O la aceptas ahora o se acabó. Tienen otro jugador esperando.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f035,
                    "Aceptar antes de que se arrepientan",
                    "No dejas pasar el tren. Firmas. A veces la decisión perfecta no existe. Existe la decisión a tiempo.",
                    0, 0, 0, 0, 0, 0, 2, 80, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(f035,
                    "Rechazar y mantener tu postura",
                    "Si no es lo que quieres, no es lo que quieres. Aunque el tren se vaya. Ya vendrá otro.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(f035,
                    "Pedir 24 horas más",
                    "'Solo un día más.' El reloj corre. La presión es máxima. Pero a veces un día lo cambia todo.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// F036 — Proyecto deportivo serio
            Event f036 = footballDataService.createEvent(
                    "No vienes a rellenar, vienes a construir",
                    "El director deportivo del {club} te llama directamente: 'No queremos ficharte para completar la plantilla. Queremos construir un proyecto alrededor de jugadores como tú. Esto va en serio.'",
                    "fichaje", "decision", "epic", "profesional", 22, 38, 1, 3, null
            );
            footballDataService.createOption(f036,
                    "Me interesa, quiero ser parte de algo grande",
                    "Un club que construye contigo como pieza central. Eso no pasa todos los días.",
                    0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 0, 2, 2);
            footballDataService.createOption(f036,
                    "Quiero garantías antes de comprometerme",
                    "'Bonitas palabras, pero necesito verlo en el contrato.' No te compran con discursos.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0);

// F037 — Competencia en el nuevo club
            Event f037 = footballDataService.createEvent(
                    "No serás el único bueno ahí",
                    "El entrenador del {club} te habla claro en la videollamada: 'Si vienes, tendrás competencia por tu posición. Hay otro jugador que rinde mucho. No te voy a mentir. ¿Te asusta?'",
                    "fichaje", "decision", "uncommon", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f037,
                    "Me parece normal, la competencia me mejora",
                    "'Si no hay competencia, me acomodo. Tráeme, que me gusta competir.' El entrenador sonríe.",
                    0, 0, 0, 0, 1, 0, 1, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(f037,
                    "Quiero garantías de minutos",
                    "'No me muevo para ir al banquillo.' El entrenador se pone serio: 'Aquí nadie tiene garantías.'",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0);

// F038 — Fichaje estrella en tu puesto
            Event f038 = footballDataService.createEvent(
                    "Han fichado a un crack para tu posición",
                    "Tu representante te llama con mala cara: 'Tu club está a punto de fichar a un jugador internacional para tu posición. Si llega, tus minutos van a bajar. ¿Qué hacemos?'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f038,
                    "Competir, no me voy a rendir",
                    "Que venga quien quiera. El puesto se gana entrenando, no firmando.",
                    0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0);
            footballDataService.createOption(f038,
                    "Pedir salir antes de que me arrinconan",
                    "Mejor irte por la puerta grande que quedarte languideciendo en el banquillo.",
                    0, 0, 0, 0, 0, 0, 0, 0, -2, 0, -1, 0, 2, -1);
            footballDataService.createOption(f038,
                    "Esperar a que llegue y ver qué pasa",
                    "Igual no viene. Igual viene y no rinde. Igual viene y tú eres mejor. Paciencia.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// F039 — Fichaje que te beneficia
            Event f039 = footballDataService.createEvent(
                    "El fichaje perfecto para ti",
                    "El míster te para sonriendo: 'Hemos fichado a un jugador que va a encajar perfectamente contigo. Os vais a complementar de escándalo.' Y tiene razón: el nuevo ficha y os entendéis desde el primer entrenamiento.",
                    "fichaje", "informativo", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(f039,
                    "Darle la bienvenida y conectar desde el primer día",
                    "Os hacéis inseparables en el campo. Él te busca, tú le encuentras. La dupla empieza a funcionar.",
                    0, 1, 0, 0, 0, 1, 2, 0, 1, 3, 0, 0, 0, 0);
            footballDataService.createOption(f039,
                    "Mantener distancia profesional",
                    "Os entendéis bien en el campo pero no hacéis migas fuera. Profesional, pero sin química personal.",
                    0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0);

// F040 — El entrenador te pide personalmente
            Event f040 = footballDataService.createEvent(
                    "El entrenador te quiere",
                    "Tu representante te llama emocionado: 'El entrenador del {club} ha pedido personalmente tu fichaje. No es el director deportivo. No es un ojeador. Es EL MÍSTER el que te quiere. Eso vale oro.'",
                    "fichaje", "decision", "epic", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f040,
                    "Ir donde un entrenador cree en ti",
                    "Un míster que te pide es un míster que te va a dar oportunidades. Aceptas.",
                    0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 0, 1, 0);
            footballDataService.createOption(f040,
                    "Agradecer pero analizar más opciones",
                    "Un entrenador que te quiere está bien. Pero los entrenadores también se van. Necesitas más garantías.",
                    0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 2, 0);

// F041 — El entrenador que te quería se va
            Event f041 = footballDataService.createEvent(
                    "El míster que te quería se va del club",
                    "Tu representante te llama con voz tensa: 'Problema. El entrenador que pidió tu fichaje puede salir del {club}. Si se va, el que venga igual no te quiere. ¿Seguimos con la operación o paramos?'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f041,
                    "Seguir adelante, el club es más que un entrenador",
                    "Los míteres van y vienen. Si el club te quiere y el proyecto es bueno, un cambio de entrenador no cambia nada. ¿Verdad?",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(f041,
                    "Esperar a ver quién viene de entrenador",
                    "'No me muevo hasta saber quién se sienta en el banquillo.' Prudencia. Pero la espera puede hacer que la oferta se enfríe.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(f041,
                    "Cancelar la operación",
                    "'Sin el míster, el proyecto ya no es el que me vendieron.' Tu representante cancela. ¿Habrás acertado?",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0);

// F042 — El nuevo entrenador no te ve
            Event f042 = footballDataService.createEvent(
                    "El nuevo míster tiene otras ideas",
                    "El {club} ha cambiado de entrenador justo antes de que se cierre tu fichaje. Tu representante te lo cuenta: 'El nuevo tiene otras ideas. Sigue queriéndote, pero tu papel ya no está tan claro.'",
                    "fichaje", "decision", "uncommon", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f042,
                    "Seguir adelante y ganarse al nuevo míster",
                    "Los entrenadores se convencen con el trabajo. Llegas, entrenas bien, y que él decida. Confianza en ti mismo.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(f042,
                    "Preguntar qué papel tendría exactamente",
                    "'Antes de moverme, necesito saber si juego o no juego.' La respuesta puede no gustarte.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f042,
                    "Cancelar, el panorama ha cambiado",
                    "Si el entrenador que te quería se fue, el proyecto ya no es el mismo. Mejor quedarte donde sabes lo que hay.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, -1, 0);

// F043 — Último día de mercado
            Event f043 = footballDataService.createEvent(
                    "Deadline day",
                    "Tu representante te llama a las siete de la tarde: 'Quedan cinco horas para que cierre el mercado. Hay una oferta del {club} sobre la mesa. Es ahora o nunca. Literalmente.'",
                    "fichaje", "decision", "epic", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f043,
                    "Aceptar, no hay tiempo para pensar",
                    "Firmas a las once de la noche. Tu foto con la camiseta nueva a las 23:45. Todo en cinco horas.",
                    0, 0, 0, 0, 0, 0, 3, 100, -1, 0, 0, 1, 2, 0);
            footballDataService.createOption(f043,
                    "Rechazar, no me gusta decidir con prisas",
                    "'Si me quieren de verdad, que vuelvan en enero.' Tu representante resopla. La ventana se cierra.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, -2, 0);
            footballDataService.createOption(f043,
                    "Esperar hasta el último momento",
                    "Las horas pasan. Tu teléfono no para. A las 23:30 decides. Treinta minutos para firmar, foto, comunicado y enviar la documentación. Justo. Justísimo.",
                    0, 0, 0, 0, 0, 0, 2, 100, 0, 0, 0, 0, 1, 0);

// F044 — Oferta de última hora
            Event f044 = footballDataService.createEvent(
                    "Sorpresa en el cierre",
                    "A tres horas del cierre del mercado, tu representante te escribe un mensaje de dos líneas: 'Acaba de llegar una oferta que no esperábamos. Es buena. ¿Hablamos?'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f044,
                    "Escuchar, todavía hay tiempo",
                    "Tu representante te lo cuenta en cinco minutos. La oferta es real. Las condiciones son buenas. Pero el reloj corre.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(f044,
                    "Rechazar, a esta hora no se toman decisiones",
                    "'A las once de la noche no se firma un contrato. Si quieren, que vuelvan en enero.' Dormir tranquilo vale más.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, -1, 0);

// F045 — Operación bloqueada
            Event f045 = footballDataService.createEvent(
                    "Se ha atascado todo",
                    "Tu representante suena cansado: 'El acuerdo estaba prácticamente cerrado, pero ha aparecido un problema. Los clubes no se ponen de acuerdo en una condición. Esto puede caerse.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f045,
                    "Esperar a que los clubes se entiendan",
                    "No es tu problema. Que los directivos arreglen sus cosas. Tú sigues entrenando.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(f045,
                    "Presionar para que se cierre",
                    "'Diles que si no se cierra esta semana, me quedo.' La presión funciona. A veces.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 2, -1);
            footballDataService.createOption(f045,
                    "Retirarme de la negociación",
                    "Demasiado drama. No merece la pena. Te retiras y sigues con tu vida.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, -1, 0);

// F046 — Fichaje frustrado
            Event f046 = footballDataService.createEvent(
                    "Se ha caído",
                    "Tu representante te llama con la voz más apagada que le has oído nunca: 'No hay acuerdo. El fichaje se ha caído. Lo siento.' Silencio al otro lado del teléfono.",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f046,
                    "Aceptarlo y mirar hacia delante",
                    "Duele. Pero el fútbol sigue. Mañana hay entrenamiento. Pasado, un partido. La vida no se para por un fichaje roto.",
                    0, 0, 0, 0, 2, 0, -1, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(f046,
                    "Tomártelo como motivación extra",
                    "Si este no ha salido, el siguiente será mejor. Entrenas con una rabia sana que te hace rendir más que nunca.",
                    0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0);

// F047 — Acuerdo entre clubes
            Event f047 = footballDataService.createEvent(
                    "Los clubes tienen acuerdo",
                    "Tu representante te llama con voz firme: 'Está hecho. Los clubes han llegado a un acuerdo por la cifra del traspaso. Ahora falta lo tuyo: las condiciones personales. ¿Qué pides?'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f047,
                    "Priorizar el salario",
                    "'Quiero que me paguen lo que valgo.' Tu representante sonríe. 'Eso es lo que mejor se me da.'",
                    0, 0, 0, 0, 0, 0, 1, 100, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(f047,
                    "Priorizar las garantías deportivas",
                    "'Quiero minutos, quiero un rol y quiero saber qué plan tienen para mí.' Dinero después.",
                    0, 0, 0, 0, 1, 0, 1, 0, 2, 0, 0, 0, 1, 0);
            footballDataService.createOption(f047,
                    "Pedir una duración de contrato larga para tener estabilidad",
                    "'Cuatro o cinco años. No me quiero preocupar por el contrato en dos temporadas.' Estabilidad.",
                    0, 0, 0, 0, 0, 0, 1, 50, 0, 0, 0, 0, 0, 1);

// F048 — Contrato sobre la mesa
            Event f048 = footballDataService.createEvent(
                    "El contrato está delante de ti",
                    "Despacho del director deportivo del {club}. Tu representante a tu lado. El contrato sobre la mesa. Salario, duración, cláusula, bonus, todo negro sobre blanco. El bolígrafo te espera.",
                    "fichaje", "decision", "epic", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f048,
                    "Firmar",
                    "El bolígrafo toca el papel. Nueva ciudad, nueva camiseta, nueva historia. El director te da la mano: 'Bienvenido.'",
                    0, 0, 0, 0, 0, 0, 3, 100, 0, 0, 0, 1, 2, 2);
            footballDataService.createOption(f048,
                    "Negociar un par de cláusulas antes de firmar",
                    "'Todo me parece bien menos esto y esto.' Tu representante saca el boli rojo y marca dos líneas del contrato. Media hora más de negociación.",
                    0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(f048,
                    "Rechazar en el último momento",
                    "Algo no te cuadra. No sabes qué, pero no lo ves. Te levantas: 'Lo siento, pero no.' El silencio del despacho es ensordecedor.",
                    0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, -1, -2, 0);

// F049 — Decisión de carrera
            Event f049 = footballDataService.createEvent(
                    "Dos ofertas, una decisión",
                    "Tu representante te sienta con dos carpetas sobre la mesa. Club A: más dinero, Champions League, competencia brutal por tu puesto. Club B: menos pasta, titularidad asegurada, un proyecto construido para ti. '¿Qué pesa más, el nombre o el protagonismo?'",
                    "fichaje", "decision", "epic", "profesional", 22, 38, 1, 3, null
            );
            footballDataService.createOption(f049,
                    "El dinero y la Champions (Club A)",
                    "Champions, salario alto, pero serás uno más en una plantilla de estrellas. El brillo del escaparate, con el riesgo de ser invisible.",
                    0, 0, 0, 0, 0, 0, 4, 200, -1, 0, 0, 2, 2, 0);
            footballDataService.createOption(f049,
                    "El proyecto deportivo (Club B)",
                    "Menos pasta, pero el equipo se construye alrededor tuyo. Serás la estrella, la referencia, el que tire del carro. Más presión, más protagonismo.",
                    0, 0, 0, 0, 0, 0, 2, 50, 3, 2, 2, 0, 0, 2);
            footballDataService.createOption(f049,
                    "Pedir a los dos que mejoren su oferta",
                    "'Si los dos me queréis, demostradlo.' La guerra de ofertas sube el precio. Pero también el riesgo de que alguno se canse.",
                    0, 0, 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 2, -1);

// F050 — El gran salto
            Event f050 = footballDataService.createEvent(
                    "La oferta que puede cambiar tu vida",
                    "Tu representante te llama temblando: 'Siéntate. El {topClub} ha hecho una oferta oficial por ti. No es un rumor. No es un tanteo. Es una OFERTA. Con números, con contrato, con todo.' Comparativa: tu club actual te da protagonismo, minutos y confianza del míster. El nuevo te ofrece el doble de salario, Champions League, pero serías rotación y los minutos son inciertos.",
                    "fichaje", "unico", "legendary", "profesional", 22, 38, 2, 3, null
            );
            footballDataService.createOption(f050,
                    "Dar el salto, es ahora o nunca",
                    "Algunos trenes solo pasan una vez. Firmas. El mundo se entera. Tu madre llora. Tu padre se queda callado. Tu vida acaba de cambiar.",
                    0, 0, 0, 0, 0, 0, 5, 300, -2, -2, 0, 3, 3, 0);
            footballDataService.createOption(f050,
                    "Quedarte y seguir siendo protagonista donde estás",
                    "'No necesito un club grande para demostrar que soy grande.' Tu directiva te aplaude. Tu representante resopla. La afición te adora un poco más.",
                    0, 0, 0, 0, 1, 0, 0, 0, 3, 2, 4, 0, -1, 3);
            footballDataService.createOption(f050,
                    "Negociar garantías antes de decidir",
                    "'No me voy si no me garantizan minutos.' Tu representante negocia. El {topClub} no garantiza nada. Nunca lo hacen. ¿Te arriesgas?",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 3, 0);
            footballDataService.createOption(f050,
                    "Pedir tiempo para pensarlo",
                    "'Necesito unos días.' El {topClub} te da 72 horas. Setenta y dos horas para tomar la decisión más grande de tu carrera. No duermes ninguna de las tres noches.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }
}

}


/* hay que crearle el atributo con final(constante) siempre del repositorio a usar(siendo el repositorio el que usara las consultas)
 se le da el mismo nombre al atributo que el repositorio, luego metemos los repositrorios como si fuera un constructor,
 luego sobreescribimos lña funcion run() que no devuelve nada y le decimos que si la posicion tiene ya cosas, es decir,
 que si existen posiciones creadas que se pare y no ejecuite y sino las crea */
