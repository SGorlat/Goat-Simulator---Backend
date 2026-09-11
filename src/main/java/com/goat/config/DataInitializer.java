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
                    "Sprints, entradas, disputas... no dejas una pelota sin pelear. El míster asiente. Pero forzar tanto el primer día te deja reventado y un compañero te dice: 'Tranquilo, bro, que esto es un entreno, no una final.'",
                    0, 1, 0, 1, 0, 0, 2, 0, 2, -1, 0, 0, 0, 0);
            footballDataService.createOption(a001,
                    "Mantener perfil bajo y observar cómo funciona todo",
                    "Mientras los demás se matan por cada balón, tú lees el partido desde fuera. No destacas, pero captas cómo funciona el grupo. El míster ni te mira, pero el veterano del equipo te dice: 'Listo, ¿eh? Me gusta.'",
                    0, 0, 0, 0, 1, 1, 0, 0, 0, 2, 0, 0, 0, 0);
            footballDataService.createOption(a001,
                    "Intentar conectar con los compañeros desde el primer momento",
                    "Te presentas a todos, preguntas nombres, buscas complicidad. Caes bien al grupo desde el día uno. Pero el míster te suelta al final: 'Menos bla bla y más balón, chaval.'",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 4, 0, 0, 0, 0);

// A002 — La novatada
            Event a002 = footballDataService.createEvent(
                    "Bienvenido al vestuario, chaval",
                    "Primer día de concentración. Cuando vuelves de la ducha te encuentras los calzoncillos colgados de la portería del campo de entrenamiento. Todo el vestuario muerto de risa. El capitán te mira con una sonrisa de oreja a oreja: 'Es la tradición, chaval. A todos nos ha pasado.'",
                    "academia", "decision", "common", "academia", 16, 17, 1, 4, null
            );
            footballDataService.createOption(a002,
                    "Reírte con ellos y seguirles el rollo",
                    "Vas a la portería muerto de risa, levantas los calzoncillos como si fueran una copa. El vestuario te adopta. Pero el míster te ve y murmura: 'Menos payasadas y más seriedad.'",
                    0, 0, 0, 0, 0, 0, 1, 0, -1, 4, 0, 0, 0, 0);
            footballDataService.createOption(a002,
                    "Tomártelo con calma pero dejar claro que una vez basta",
                    "'Muy buena, muy buena. Pero la siguiente la devuelvo.' Lo dices sonriendo, pero con una mirada que deja claro que no eres tonto. Te respetan, aunque no eres el más popular.",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0);
            footballDataService.createOption(a002,
                    "Ignorarlo completamente y ir a lo tuyo",
                    "Pasas de largo sin decir nada. Coges los calzoncillos y te vistes en silencio. Algunos te miran raro. Otros piensan: 'Este va a lo suyo.' No caes mal, pero tampoco caes bien.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0);

// A003 — Tu padre antes del primer partido
            Event a003 = footballDataService.createEvent(
                    "La charla de tu padre en el coche",
                    "Camino al campo, tu padre baja la radio y te suelta: 'Oye, me da igual si metes tres goles o si no la tocas. Lo que no quiero ver es que vayas con miedo. ¿Estamos?' Silencio. El semáforo se pone en verde.",
                    "academia", "decision", "common", "academia", 16, 17, 1, 4, null
            );
            footballDataService.createOption(a003,
                    "Asentir y saltar al campo sin presión",
                    "Sales al césped con la cabeza limpia. Juegas suelto, sin miedo al fallo. Pero quizás demasiado relajado: el míster te grita dos veces que aprietes más.",
                    0, 0, 0, 0, 2, 0, 1, 0, -1, 0, 0, 0, 0, 0);
            footballDataService.createOption(a003,
                    "Ponerte los auriculares y prepararte a tu manera",
                    "Subes el volumen, cierras los ojos y entras en tu burbuja. Sales frío y concentrado. Pero tu padre se queda con la sensación de que no le has escuchado.",
                    0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(a003,
                    "Decirle que estás nervioso y que eso es normal",
                    "'Papá, estoy cagado de miedo.' Tu padre sonríe: 'Bien. El que no tiene miedo es porque le da igual.' Sales nervioso pero honesto. A veces la verdad libera más que cualquier técnica mental.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1);

// A004 — Quedarse a entrenar
            Event a004 = footballDataService.createEvent(
                    "¿Te quedas o te piras?",
                    "Son las siete de la tarde. El entrenamiento terminó hace veinte minutos, pero hay cuatro tíos que siguen en el campo tirando centros y rematando de cabeza. Uno de ellos te grita desde la banda: '¡Eh, nuevo! ¿Te vienes o te vas a casa a ver el TikTok?'",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a004,
                    "Dejar la mochila y quedarte una hora más",
                    "Una hora larga de centros, controles y remates. Las piernas lloran. Ya tienes a cuatro tíos que te consideran uno de los suyos. Pero mañana llegas cargado al entreno oficial y el míster lo nota.",
                    0, 2, 1, -1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0);
            footballDataService.createOption(a004,
                    "Irte a descansar para rendir mañana al 100%",
                    "Dices que mañana sin falta. Duermes bien, cenas con tu familia. Al día siguiente eres el más fresco del entreno. Pero los cuatro del campo extra ya tienen su grupo y tú no estás en él.",
                    0, 0, 0, 1, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0);
            footballDataService.createOption(a004,
                    "Quedarte solo media hora y luego irte",
                    "Veinte minutos de tiros y te despides: 'Tíos, mañana más.' Equilibrado: no te has matado pero has dado la cara. Nadie te juzga pero nadie te recuerda especialmente.",
                    0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0);

// A005 — Sesión voluntaria a las ocho
            Event a005 = footballDataService.createEvent(
                    "Voluntarios a las ocho de la mañana",
                    "El preparador físico aparece en el vestuario: 'Mañana, sesión voluntaria a las ocho. Voluntaria, ¿eh? Nadie os obliga.' Mira uno por uno. 'Pero yo me acuerdo de quién viene y de quién no.'",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a005,
                    "Estar ahí a las ocho en punto",
                    "Llegas con los ojos pegados pero llegas. Sois seis. El preparador os machaca. Ganas puntos con el staff pero llegas al entreno de la tarde con las piernas de plomo y rindes peor de lo normal.",
                    1, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(a005,
                    "Quedarte en la cama y descansar bien",
                    "Nueve horas de sueño. Llegas fresco al entreno de la tarde y destacas. Pero el preparador te mira y no dice nada. Ese silencio pesa.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0);
            footballDataService.createOption(a005,
                    "Ir pero no forzar demasiado, dosificarte",
                    "Llegas, haces la sesión al 70% y guardas energía para la tarde. El preparador te ve y comenta: 'Este viene pero no se deja la piel.' ¿Es inteligencia o es tibieza? Depende de quién lo juzgue.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// A006 — Te cambian de posición
            Event a006 = footballDataService.createEvent(
                    "Hoy juegas donde yo te diga",
                    "El míster te corta en el calentamiento: 'Te necesito en otra posición hoy. No me pongas caras. Quiero ver si sirves para algo más que para lo tuyo.' Algunos compañeros se miran.",
                    "academia", "decision", "uncommon", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a006,
                    "Aceptar sin rechistar y dar el callo",
                    "No es tu sitio pero lo intentas. El míster ve que eres obediente y versátil. Pero al no jugar en tu posición, tu rendimiento baja y el ojeador que había en la grada solo ve una versión mediocre de ti.",
                    0, 1, 0, 0, 1, 1, -1, 0, 3, 0, 0, -1, 0, 0);
            footballDataService.createOption(a006,
                    "Decirle educadamente que rindes mejor en tu posición",
                    "Se lo dices con respeto: 'Míster, si necesita verme, preferiría que fuera en mi sitio.' Te mira fijo tres segundos. 'Vale.' Juegas en tu posición, rindes bien, pero el míster ya te tiene fichado como uno que no acepta todo.",
                    0, 0, 0, 0, 0, 0, 1, 0, -2, 0, 0, 1, 0, 0);
            footballDataService.createOption(a006,
                    "Aceptar pero pedirle feedback después",
                    "'Vale, míster, la juego. Pero al final del partido me gustaría que me dijera qué tal.' El entrenador asiente sorprendido. Al final te dice: 'No es tu posición, pero tienes cosas.' Te vas con información útil.",
                    0, 0, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0);

// A007 — Preparar un aspecto concreto
            Event a007 = footballDataService.createEvent(
                    "El míster quiere que elijas",
                    "Partido importante el sábado contra el líder. El entrenador te llama aparte: 'Esta semana quiero que te centres en mejorar un aspecto concreto de tu juego. ¿En qué te quieres enfocar?'",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a007,
                    "Practicar definición: remates, disparos, penaltis",
                    "Dos tardes de tiros a puerta. Tu disparo mejora, pero descuidas la parte táctica y el sábado te pillan fuera de sitio dos veces.",
                    0, 0, 2, 0, 0, -1, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(a007,
                    "Trabajar el físico: velocidad, fuerza, resistencia",
                    "Circuitos de velocidad y pesas. Llegas al sábado más fuerte pero con las piernas cargadas. Rindes mejor en la segunda parte que en la primera.",
                    1, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(a007,
                    "Mejorar la visión de juego: posicionamiento, lectura del partido",
                    "Vídeo con el segundo entrenador. Ves el campo de otra manera el sábado: lees jugadas antes de que pasen. Pero tus piernas no han mejorado y un defensa rápido te come en dos jugadas.",
                    0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0);

// A008 — El míster te elogia y te exige
            Event a008 = footballDataService.createEvent(
                    "Te estás ganando minutos, pero...",
                    "El míster te para en el pasillo después del entrenamiento: 'Mira, te estás ganando minutos, no te lo voy a negar. Pero todavía cometes demasiados errores tontos. Errores que te los perdonan en juveniles pero arriba no te los van a perdonar nadie.'",
                    "academia", "decision", "uncommon", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a008,
                    "Preguntarle en qué mejorar concretamente",
                    "'¿Qué errores, míster?' El entrenador se toma 10 minutos en explicarte. Aprendes, pero también te enteras de más defectos de los que esperabas. Duermes peor esa noche.",
                    0, 0, 0, 0, 0, 1, 0, 0, 3, 0, 0, 0, 0, 0);
            footballDataService.createOption(a008,
                    "Decirle que estás preparado para jugar más",
                    "'Míster, creo que merezco más minutos.' El entrenador levanta una ceja. 'Eso no lo decides tú.' Ambición captada, pero el míster no te pone de titular el sábado para recordarte quién manda.",
                    0, 0, 0, 0, 0, 0, 1, 0, -1, 0, 0, 1, 0, 0);
            footballDataService.createOption(a008,
                    "No decir nada y demostrarlo entrenando",
                    "Asientes y al día siguiente entrenas al 200%. El míster no dice nada. Te pone de titular el sábado. Pero como no le preguntaste, sigues cometiendo los mismos errores sin saberlo.",
                    0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0);

// A009 — Las notas han bajado
            Event a009 = footballDataService.createEvent(
                    "El tutor te cita en su despacho",
                    "El tutor del instituto te sienta en la silla y abre la carpeta: 'Mira, chaval, no sé si vas a ser futbolista o fontanero, pero con estas notas no acabas ni el curso. La academia tiene un protocolo y yo tengo un teléfono para llamar a tus padres.'",
                    "academia", "decision", "uncommon", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a009,
                    "Ponerte las pilas con los estudios",
                    "Dos tardes a la semana sin balón. Las notas suben y el tutor te deja en paz. Pero pierdes dos sesiones de entrenamiento extra que tus competidores sí aprovechan.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a009,
                    "Priorizar el fútbol y arriesgarte con los estudios",
                    "Los estudios pueden esperar, el fútbol no. Entrenas el doble y mejoras rápido. Pero la sanción académica llega: una semana sin competir. El míster se entera y te echa una bronca memorable.",
                    0, 1, 0, 1, 0, 0, 0, 0, -2, 0, 0, 0, 0, 0);
            footballDataService.createOption(a009,
                    "Pedir ayuda a un compañero para estudiar más rápido",
                    "Un compañero de equipo que saca buenas notas te echa un cable. Estudias menos tiempo pero más eficiente. Las notas suben lo justo y no pierdes entrenamientos. Eso sí, le debes un favor.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0);

// A010 — Mejorar la pierna mala
            Event a010 = footballDataService.createEvent(
                    "La pierna que no quieres usar",
                    "El míster te para después del partido: 'Hoy has tirado cinco veces con la derecha y cero con la izquierda. Hasta el portero ya sabía por dónde ibas a tirar. O aprendes a usar las dos o te van a leer como un libro.'",
                    "academia", "decision", "common", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a010,
                    "Quedarte después a practicar con la pierna mala",
                    "Tres semanas tirando solo con la zurda. Los primeros días los balones van al parking. Poco a poco mejora. Pero mientras tanto pierdes confianza con tu pierna buena porque la usas menos.",
                    0, 2, -1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(a010,
                    "Centrarte en mejorar lo que ya haces bien",
                    "Si la derecha te funciona, perfecciónala. Mejor ser devastador con una que mediocre con dos. El míster no está de acuerdo, pero tus goles le callan la boca temporalmente.",
                    0, 0, 1, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0);
            footballDataService.createOption(a010,
                    "Practicar ambas por igual durante un mes",
                    "Repartes el trabajo entre ambas piernas. No mejoras espectacularmente con ninguna, pero te vuelves menos predecible. Un progreso lento pero sólido.",
                    0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0);

// A011 — Tu madre te frena
            Event a011 = footballDataService.createEvent(
                    "Tu madre se planta",
                    "Llevas tres semanas sin un solo día libre. Tu madre te para en la cocina a las once de la noche: 'Cariño, para. Llevas un mes que no haces otra cosa que no sea fútbol. Come algo, duerme ocho horas, sal con tus amigos, tómate un día de descanso para recargar pilas.'",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a011,
                    "Hacerle caso y tomarte un día libre de verdad",
                    "Sofá, tortilla de tu madre y una siesta. Vuelves renovado el lunes. Pero tus competidores entrenaron ese día y uno de ellos ha ocupado tu sitio en el partidillo del martes.",
                    0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, -1, 0, 0);
            footballDataService.createOption(a011,
                    "Decirle que no puedes parar ahora, es un momento clave",
                    "'Mamá, ahora no. Hay un torneo la semana que viene.' Tu madre suspira. Entrenas más, pero la fatiga acumulada te pasa factura y tienes un pinchazo muscular en el entreno del jueves.",
                    0, 0, 0, -1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0);
            footballDataService.createOption(a011,
                    "Buscar un punto medio: descansar pero hacer algo suave",
                    "Sales a andar con tu madre, cenas bien, duermes temprano. No descansas del todo pero tampoco te machacas. Al día siguiente estás mejor pero no al 100%.",
                    0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// A012 — Tu madre sobre los amigos
            Event a012 = footballDataService.createEvent(
                    "Llevas semanas sin salir de casa",
                    "Tu madre te pilla mirando el móvil por la noche: 'Cariño, llevas semanas sin quedar con tus amigos. ¿Sabes que existe vida más allá del fútbol, verdad?'",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a012,
                    "Quedar con tus amigos este fin de semana",
                    "Pizza, risas y cero fútbol. Te sientas como un chaval normal por unas horas. El lunes vuelves con la mente fresca. Pero te acuestas tarde y el domingo rindes peor en el entreno.",
                    0, 0, 0, -1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a012,
                    "Seguir centrado, ya saldrás cuando consigas algo",
                    "El sacrificio primero. Entrenas el sábado mientras tus amigos van al cine. Mejoras un poco más cada día. Pero a las tres de la mañana ves las fotos de tus amigos y te sientes solo.",
                    0, 0, 0, 0, -1, 0, 1, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(a012,
                    "Invitar a tus amigos a verte jugar",
                    "Les dices que vengan al partido del sábado. Vienen tres. Te motivas, juegas con más ganas, pero también intentas lucirte demasiado y el míster te regaña por jugar solo.",
                    0, 0, 0, 0, 1, 0, 1, 0, -1, 1, 1, 0, 0, 0);

// A013 — Tu padre te nota distraído
            Event a013 = footballDataService.createEvent(
                    "Tu padre nota algo raro",
                    "Tu padre te observa durante la cena: 'Te noto más pendiente de los ojeadores que del balón. ¿Tú has venido a jugar al fútbol o a mirar a la grada?' La pregunta duele porque tiene razón.",
                    "academia", "decision", "uncommon", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a013,
                    "Darle la razón y volver a centrarte en jugar",
                    "Bajas la cabeza y asientes. Al día siguiente juegas mirando solo al campo. Rindes mejor, pero dejas de preocuparte por impresionar y un ojeador que vino esa semana no se fija en ti.",
                    0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, -1, 0, 0);
            footballDataService.createOption(a013,
                    "Decirle que es normal pensar en el futuro",
                    "'Papá, hay clubes preguntando por mí. ¿Cómo no voy a pensar en ello?' Tu padre suspira. Tienes razón, pero la ansiedad por impresionar te hace jugar nervioso los próximos partidos.",
                    0, 0, 0, 0, -1, 0, 1, 0, 0, 0, 0, 1, 0, 0);
            footballDataService.createOption(a013,
                    "Pedirle consejo sobre cómo gestionar la presión",
                    "'Papá, ¿tú qué harías?' Se abre una conversación honesta que no teníais desde hace meses. No resuelve nada concreto del fútbol pero te quitas un peso de encima.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1);

// A014 — Pachanga y consola
            Event a014 = footballDataService.createEvent(
                    "Pachanga en el barrio o consola en casa",
                    "Sábado por la tarde. Tu mejor colega te manda un audio: 'Tío, hay pachanga en el parque a las seis. Si no, me quedo en mi casa y echamos unas partidas al FC.' Mañana entrenas temprano.",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a014,
                    "Ir a la pachanga",
                    "Dos horas de fútbol callejero. Un caño que te va a durar en la memoria un mes. Pero un pisotón te deja con el pie hinchado y al día siguiente entrenas cojeando. El míster te echa la bronca: 'Eso te pasa por jugar pachangas.'",
                    0, 1, 0, -1, 0, 0, 1, 0, -1, 2, 0, 0, 0, 0);
            footballDataService.createOption(a014,
                    "Consola y dormir pronto",
                    "Tres partidas, pizza y a la cama a las once. Llegas fresco al entrenamiento. Pero tu colega te dice que la pachanga fue brutal y que el del Betis vino a jugar. Oportunidad perdida de hacer contactos.",
                    0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a014,
                    "Ir a ver la pachanga sin jugar",
                    "Vas, animas, charlas con los tíos, pero no juegas para no arriesgarte. Te diviertes a medias. No mejoras físicamente ni descansas del todo. Pero mantienes el contacto social sin riesgo de lesión.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0);

// A015 — El rival interno
            Event a015 = footballDataService.createEvent(
                    "El que compite contigo por el puesto",
                    "Otro jugador de tu posición te mira fijo en el calentamiento y te suelta: 'Tranquilo, chaval, que el puesto sigue siendo mío.' Algunos compañeros bajan la mirada. El silencio pesa.",
                    "academia", "decision", "uncommon", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a015,
                    "Ignorarle y hablar en el campo",
                    "No le das ni una sola mirada. En el entrenamiento rindes bien, pero la tensión entre vosotros divide al vestuario. Unos van contigo, otros con él.",
                    0, 0, 0, 0, 2, 0, 1, 0, 1, -2, 0, 0, 0, 0);
            footballDataService.createOption(a015,
                    "Responderle con calma y buen rollo",
                    "'Ojalá, tío, así los dos mejoraremos.' Él se queda sin saber qué decir. La tensión baja, pero algunos piensan que no tienes carácter. El míster te prefiere tranquilo, pero ¿respeta más al que pelea?",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0);
            footballDataService.createOption(a015,
                    "Plantarle cara directamente",
                    "'El puesto se lo gana el que más trabaja, no el que más habla.' El vestuario se tensa. Pero en el siguiente partidillo juegas con una rabia que te hace rendir al doble. El míster ve chispas.",
                    0, 0, 1, 0, 0, 0, 1, 0, 0, -3, 0, 1, 0, 0);
// A016 — El rival sube la tensión
            Event a016 = footballDataService.createEvent(
                    "La competencia se pone seria",
                    "Tu rival interno ha mejorado mucho. Ha marcado tres goles en las últimas dos semanas y el míster le está dando más minutos. Te lo cruzas en el vestuario: 'Últimamente juegas tú y yo me quedo en el banquillo. Pero esto no va a quedarse así, ¿eh?'",
                    "academia", "decision", "uncommon", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a016,
                    "Aceptar la competencia limpiamente",
                    "Le tiendes la mano: 'Que gane el mejor.' La competencia se mantiene limpia, pero el vestuario te ve demasiado blando. Algunos piensan que no te importa lo suficiente el puesto.",
                    0, 0, 0, 0, 1, 0, -1, 0, 1, 2, 0, 0, 0, 0);
            footballDataService.createOption(a016,
                    "Intentar hacerte su aliado",
                    "'Tío, si nos ayudamos los dos vamos a salir ganando.' Él asiente. Os entrenáis juntos y ambos mejoráis. Pero el míster tiene que elegir a uno y la decisión se complica más.",
                    0, 1, 0, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 0);
            footballDataService.createOption(a016,
                    "Plantarle cara directamente",
                    "'El puesto es mío. Demuestra lo contrario.' La tensión sube, el vestuario se divide. Pero tú juegas con fuego dentro y el míster ve que te creces bajo presión.",
                    0, 0, 1, 0, 0, 0, 1, 0, -1, -3, 0, 1, 0, 0);

// A017 — Penalti y silencio
            Event a017 = footballDataService.createEvent(
                    "Penalti en el 88",
                    "Minuto 88. Penalti a favor. Final del torneo comarcal. El capitán pone el balón en el punto y mira alrededor. Nadie dice nada. El portero rival da saltitos en la línea. Alguien tiene que cogerlo.",
                    "academia", "decision", "rare", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a017,
                    "Pedir el balón y asumir la responsabilidad",
                    "Lo coges con las dos manos. Silencio. Carrera corta. Disparo cruzado. Red. GOL. Eres el héroe. Pero el capitán te mira raro: nadie le ha pedido permiso. La gloria tiene un precio en el vestuario.",
                    0, 0, 3, 0, 3, 0, 5, 0, -1, 0, 3, 3, 0, 0);
            footballDataService.createOption(a017,
                    "Dejárselo al capitán, que para eso lleva el brazalete",
                    "El capitán lo tira al palo largo. Gol. La celebración es suya. El vestuario te respeta por cederlo. Pero en el autobús de vuelta piensas que podrías haberlo tirado tú. Y el ojeador no apuntó tu nombre.",
                    0, 0, 0, 0, 0, 0, -1, 0, 2, 3, 0, -1, 0, 0);
            footballDataService.createOption(a017,
                    "Ofrecerte pero aceptar si otro quiere tirarlo",
                    "'Yo lo tiro si queréis, pero si alguien lo tiene más claro...' El capitán te señala: 'Tíralo tú.' Lo metes. Es gol, pero el mérito se comparte. No eres el héroe absoluto ni el cobarde.",
                    0, 0, 1, 0, 1, 0, 2, 0, 1, 2, 1, 1, 0, 0);

// A018 — Recoger el material
            Event a018 = footballDataService.createEvent(
                    "Recoger los conos o pirar",
                    "El capitán se queda recogiendo los conos y los petos después del entrenamiento. Casi todos se han ido ya a la ducha. Te mira: 'Si quieres me echas una mano, que esto solo es un coñazo.'",
                    "academia", "decision", "common", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a018,
                    "Ayudarle a recoger todo",
                    "Diez minutos recogiendo material. El capitán te adopta. Pero llegas tarde a la ducha y pierdes el autobús. Tu madre te tiene que venir a buscar y no le hace gracia.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0);
            footballDataService.createOption(a018,
                    "Irte a la ducha, que llegas tarde a casa",
                    "Te vas corriendo porque tu madre te espera. El capitán no dice nada. Llegas a tiempo, cenas con tu familia. Pero mañana el capitán pasa de ti en el calentamiento.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0);
            footballDataService.createOption(a018,
                    "Recoger solo una parte y luego irte",
                    "'Te ayudo con las porterías y me piro, que llego tarde.' Cinco minutos rápidos. El capitán asiente. No te adora, pero reconoce el gesto.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0);

// A019 — El compañero al que nadie pasa
            Event a019 = footballDataService.createEvent(
                    "El chaval invisible",
                    "Llevas tres partidos viendo la misma escena: hay un chaval nuevo al que no le dan ni una. Le ignoran en los entrenamientos. Hoy le ves sentado solo en el vestuario mirando al suelo.",
                    "academia", "decision", "uncommon", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a019,
                    "Sentarte con él y buscarle en el campo",
                    "Le dices que cuente contigo. En el siguiente partido le filtras pases y él empieza a levantar la cabeza. Pero tus otros compañeros te dicen que no le pases tanto, que no está a nivel.",
                    0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0);
            footballDataService.createOption(a019,
                    "No meterte, que cada uno se busque la vida",
                    "Bastante tienes con lo tuyo. El chaval se las tiene que apañar solo. Nadie te ayudó a ti cuando llegaste. Así funciona esto. Pero de noche te pica un poco la conciencia.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a019,
                    "Hablar con el míster para que le integre mejor",
                    "Le dices al entrenador que el chaval nuevo está aislado. El míster actúa: al día siguiente hace ejercicios por parejas y le toca contigo. El chaval mejora. Pero algunos compañeros te ven como un chivato.",
                    0, 0, 0, 0, 0, 0, 0, 0, 2, -1, 0, 0, 0, 0);

// A020 — Ojeador del {club}
            Event a020 = footballDataService.createEvent(
                    "Hay alguien en la grada con una carpeta",
                    "Diez minutos antes del partido, un compañero entra al vestuario: 'Tíos, hay un tío arriba con un polo del {club} y una carpeta. Está apuntando cosas.' El vestuario explota en murmullos.",
                    "academia", "decision", "rare", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a020,
                    "Intentar brillar con jugadas espectaculares",
                    "Bicicleta, sombrero y un tiro desde 30 metros. El ojeador te apunta... pero también apunta que perdiste tres balones por intentar florituras. La ambición puede impresionar o hundir.",
                    1, 1, 1, 0, -1, 0, 3, 0, -2, -1, 0, 2, 0, 0);
            footballDataService.createOption(a020,
                    "Jugar como si el ojeador no existiera",
                    "Un toque, pared, desmarque. Sin fuegos artificiales. El míster te felicita. Pero el ojeador buscaba algo que le llamara la atención y tú has sido demasiado discreto.",
                    0, 0, 0, 0, 2, 0, 0, 0, 3, 1, 0, -1, 0, 0);
            footballDataService.createOption(a020,
                    "Hacer tu juego pero subir la intensidad un punto",
                    "Juegas como siempre pero con un extra de concentración. Ni te pasas ni te quedas corto. El ojeador no se levanta del asiento, pero apunta tu nombre dos veces.",
                    0, 1, 0, 0, 1, 0, 2, 0, 1, 0, 0, 1, 0, 0, "interes_equipo:{club}");


// A021 — Rumor de ojeador
            Event a021 = footballDataService.createEvent(
                    "Rumor en el vestuario",
                    "El rumor corre como la pólvora antes del partido. Un compañero te lo suelta mientras te atas las botas: 'Eh, que dicen que hoy viene un ojeador del {club}.' No sabes si es verdad o mentira. Pero el corazón te late un poco más rápido.",
                    "academia", "decision", "rare", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a021,
                    "No pensar en ello y jugar como siempre",
                    "Te olvidas del rumor. Juegas tu partido normal. Si hay ojeador, que vea lo que eres. Si no lo hay, da igual. Pero al final te enteras de que SÍ había y que se fijó en otro.",
                    0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, -1, 0, 0);
            footballDataService.createOption(a021,
                    "Salir a demostrarlo todo desde el primer minuto",
                    "Saltas enchufado. Cada jugada es una audición. Metes un golazo... pero también fallas un pase fácil por querer hacer una virgüería. Resultado impredecible.",
                    1, 0, 1, 0, -1, 0, 2, 0, -1, 0, 0, 2, 0, 0);
            footballDataService.createOption(a021,
                    "Preguntar al míster si es verdad",
                    "'Míster, ¿es verdad que hay ojeador?' El entrenador te corta: 'Tú sal a jugar y olvídate de quién está mirando.' Te deja claro quién manda. Pero al menos sabes que no debes obsesionarte.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// A022 — Prueba en otro club
            Event a022 = footballDataService.createEvent(
                    "Un club quiere hacerte una prueba",
                    "El director deportivo te espera en la puerta del vestuario: 'Oye, que ha llamado el {club}. Quieren que vayas tres días a entrenar con su cantera. Es solo una toma de contacto, sin presión. ¿Qué le digo?'",
                    "academia", "decision", "rare", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a022,
                    "Ir a la prueba y ver qué ofrecen",
                    "Tres días en otra ciudad deportiva. El nivel es otro. Vuelves con las ideas claras de dónde estás. Pero en tu club se enteran y el míster te trata con más distancia: 'Así que quieres irte, ¿eh?'",
                    0, 1, 0, 0, 1, 0, 3, 0, -2, -2, 0, 0, 1, 0, "interes_equipo:{club}");
            footballDataService.createOption(a022,
                    "Rechazar y quedarte en tu club",
                    "'No, gracias. Estoy bien aquí.' El director asiente sorprendido. En el vestuario la noticia corre y te ganas el respeto de todos. Pero te queda la duda: ¿era tu oportunidad y la has dejado escapar?",
                    0, 0, 0, 0, 0, 0, -1, 0, 2, 3, 0, 0, 0, 2);
            footballDataService.createOption(a022,
                    "Pedir consejo a tu familia antes de decidir",
                    "Le dices al director que te dé 24 horas. Tu padre dice que vayas, tu madre que te quedes. No resolvéis nada pero al menos no tomas la decisión solo.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1);

// A023 — Llamada de un grande
            Event a023 = footballDataService.createEvent(
                    "Llamada de un grande",
                    "El director deportivo te llama al despacho. Cierra la puerta. Se sienta, junta las manos y suelta: 'No quiero que te vuelvas loco con esto... pero el {topClub} ha pedido un informe tuyo.' Silencio. '¿Me has oído?'",
                    "academia", "decision", "epic", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a023,
                    "Mantener los pies en el suelo",
                    "No se lo cuentas ni a tu madre. Sigues entrenando como siempre. Si quieren venir, que vengan. Pero por dentro te mueres de ganas y la ansiedad te hace dormir fatal durante una semana.",
                    0, 0, 0, -1, 2, 0, 1, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(a023,
                    "Darlo todo para que la oferta llegue",
                    "La noticia te pone las pilas como nada en tu vida. Cada entreno es una final. Mejoras rápido pero estás tan tenso que en un partidillo le metes un codazo sin querer a un compañero. El míster te sienta.",
                    0, 1, 1, -1, -1, 0, 3, 0, -1, -1, 0, 2, 1, 0,"interes_topclub");
            footballDataService.createOption(a023,
                    "Contárselo a tus padres y gestionarlo en familia",
                    "Tu padre te dice que no cambies nada. Tu madre llora de emoción. La presión se comparte. No la eliminas, pero la llevas mejor.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1);

// A024 — Torneo nacional juvenil
            Event a024 = footballDataService.createEvent(
                    "El torneo que lo cambia todo",
                    "El director deportivo planta un cartel en el vestuario: 'TORNEO NACIONAL JUVENIL — PRÓXIMA SEMANA.' Debajo, en boli rojo, alguien ha escrito: 'Os van a estar mirando.' Nadie sabe quién lo ha escrito, pero todo el mundo sabe que es verdad.",
                    "academia", "decision", "epic", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a024,
                    "Ir tranquilo, sin inventar nada nuevo",
                    "Haces lo de siempre. Dos ojeadores se quedan a hablar con tu entrenador. Pero no eres el MVP ni nadie habla de ti en las redes. Has sido consistente, no brillante.",
                    0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(a024,
                    "Prometerte que serás el MVP del torneo",
                    "Cuatro partidos jugando como si cada balón fuera el último. Metes tres goles y das una asistencia. Pero en la semifinal te pasas de intensidad y pillas una amarilla tonta que te deja fuera de la final.",
                    0, 0, 2, 0, -1, 0, 4, 0, -1, -1, 0, 3, 0, 0);
            footballDataService.createOption(a024,
                    "Jugar para que el equipo gane, tú ya saldrás beneficiado",
                    "El míster te señala al final del torneo: 'Ojalá todos pensaran como tú.' No eres MVP pero el equipo gana el torneo. Los ojeadores anotan: 'Jugador colectivo, inteligente.' ¿Es suficiente para destacar?",
                    0, 0, 0, 0, 1, 1, 1, 0, 4, 3, 0, -1, 0, 0);

// A025 — El seleccionador sub-17
            Event a025 = footballDataService.createEvent(
                    "Alguien de la selección anda mirando",
                    "Te enteras por WhatsApp: el seleccionador sub-17 estuvo en tu último partido. No dijo nada. No habló con nadie. Solo miró, apuntó, y se fue. La pregunta es: ¿apuntó tu nombre o el de otro?",
                    "academia", "decision", "rare", "academia", 16, 18, 1, 4, null
            );
            footballDataService.createOption(a025,
                    "Olvidarte del tema y seguir a lo tuyo",
                    "Borras el grupo de WhatsApp y entrenas. Si vienen, vienen. Pero cuando pasan dos semanas sin noticia, la duda te come: ¿era para ti y no diste suficiente nivel?",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(a025,
                    "Intentar ser protagonista por si vuelve",
                    "Los siguientes tres entrenos los juegas como si hubiera cámaras. Brillas en uno, te estrellas en otro. La línea entre ambición y ansiedad es muy fina.",
                    1, 0, 1, 0, -1, 0, 2, 0, -1, 0, 0, 2, 0, 0);
            footballDataService.createOption(a025,
                    "Preguntar al míster si sabe algo",
                    "'Míster, ¿usted sabe si el seleccionador estaba aquí por alguien en concreto?' El míster te dice: 'Estaba mirando a varios. Tú incluido. No te vuelvas loco.' Información útil pero ahora la presión es real.",
                    0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0);

// A026 — Lesión del amigo
            Event a026 = footballDataService.createEvent(
                    "Tu mejor amigo en la cantera se lesiona",
                    "Rodillazo en un entrenamiento. Tu mejor colega del vestuario se queda en el suelo agarrándose la rodilla. La ambulancia. El silencio. El diagnóstico: rotura de ligamento cruzado. Seis meses fuera.",
                    "academia", "decision", "rare", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a026,
                    "Ir a verle al hospital esa misma noche",
                    "Llegas con un bocadillo y el móvil cargado. Os pasáis dos horas viendo highlights. Te dice: 'Eres el único que ha venido.' Pero te acuestas tardísimo y al día siguiente rindes fatal.",
                    0, 0, 0, -1, 1, 0, 0, 0, 0, 4, 0, 0, 0, 0);
            footballDataService.createOption(a026,
                    "Mandarle un mensaje de ánimo largo",
                    "Le escribes un audio de tres minutos diciéndole que va a volver más fuerte. Él te responde con un emoji. No es lo mismo que ir. Pero has cumplido y no has comprometido tu descanso.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0);
            footballDataService.createOption(a026,
                    "Ir a verle el fin de semana cuando no haya entreno",
                    "Le dices que irás el sábado después del partido. Él espera dos días. Cuando llegas, ya han ido otros dos compañeros. Tú no fuiste el primero, pero fuiste. A veces ser práctico es mejor que ser heroico.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0);

// A027 — El representante aparece
            Event a027 = footballDataService.createEvent(
                    "Un tío con traje quiere hablar contigo",
                    "Después del torneo, un señor con traje y reloj caro te espera en la puerta: 'Chaval, soy representante de jugadores. He visto cómo juegas y creo que puedo ayudarte. Todavía eres menor, pero puedo empezar a moverme.' Te da una tarjeta.",
                    "academia", "decision", "rare", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a027,
                    "Aceptar y dejarte representar",
                    "Hablas con tus padres, firman los papeles, y de repente tienes representante. Puertas se abren. Pero el representante empieza a presionar para que te muevas y tú solo tienes 16 años.",
                    0, 0, 0, 0, -1, 0, 2, 0, 0, 0, 0, 0, 5, 0);
            footballDataService.createOption(a027,
                    "Hablar primero con tu familia antes de decidir",
                    "Le dices que te lo piensas. Tu padre investiga, tu madre desconfía. Tardáis dos semanas en decidir. Cuando llamáis, el representante ya ha fichado a otro chaval de otro equipo.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(a027,
                    "Rechazarlo, todavía es pronto",
                    "'Gracias, pero no.' El representante asiente y te da la tarjeta por si acaso. Tres meses después, el chaval al que fichó debuta en Segunda. Tú sigues en la cantera. ¿Has sido prudente o lento?",
                    0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, 0);

// A028 — El periodista local
            Event a028 = footballDataService.createEvent(
                    "Un periodista quiere entrevistarte",
                    "Un medio local quiere hacerte una entrevista después de tu gran torneo. El periodista te llama: 'Oye, eres la promesa de la cantera y a la gente le interesa saber quién eres. ¿Quedamos veinte minutos y hablamos?'",
                    "academia", "decision", "uncommon", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a028,
                    "Aceptar y hablar con naturalidad",
                    "Hablas de tus sueños, tu familia, tu equipo. Tu madre compra cinco periódicos. Pero un compañero te suelta en el vestuario: 'Vaya, el famoso.' El tono no es amistoso.",
                    0, 0, 0, 0, 0, 0, 3, 0, 0, -1, 1, 3, 0, 0);
            footballDataService.createOption(a028,
                    "Ser prudente y dar respuestas genéricas",
                    "Monosílabos y frases hechas. La entrevista sale sin chicha. No ganas nada, pero tampoco te expones. El periodista te describe como 'tímido'. No es lo peor del mundo.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0);
            footballDataService.createOption(a028,
                    "Destacar al equipo por encima de ti",
                    "'Yo solo no soy nada. Esto es mérito de todo el grupo.' El periodista sonríe. El titular es bonito pero nadie recuerda tu nombre. Tus compañeros te respetan, los ojeadores ni te buscan.",
                    0, 0, 0, 0, 0, 0, 1, 0, 1, 2, 0, -1, 0, 0);

// A029 — Te quieren subir de categoría
            Event a029 = footballDataService.createEvent(
                    "El director quiere subirte",
                    "El director deportivo te espera después del entreno: 'Oye, estamos pensando en subirte a entrenar con el filial unas semanas. El nivel es otro, vas a sufrir, pero creo que te vendría bien. ¿Te atreves o prefieres quedarte con tu grupo?'",
                    "academia", "decision", "epic", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a029,
                    "Subir y aceptar el reto",
                    "El filial va el doble de rápido. Las entradas duelen más. Nadie te espera. Sales agotado y con moratones. Pero la sonrisa dice todo. Eso sí, tus compañeros de abajo empiezan a verte como 'el que se fue'.",
                    1, 1, 0, 1, 1, 1, 3, 0, 2, -2, 0, 1, 0, 1);
            footballDataService.createOption(a029,
                    "Quedarte y seguir siendo el referente de tu grupo",
                    "Prefieres dominar tu categoría antes de dar el salto. El director lo respeta. Pero un compañero que sí sube ocupa tu hueco potencial arriba. Cuando quieras subir, puede que ya no haya sitio.",
                    0, 0, 0, 0, 0, 0, -1, 0, -1, 2, 0, -1, 0, 0);
            footballDataService.createOption(a029,
                    "Pedir subir solo unos días para probar",
                    "'¿Puedo ir tres días y ver cómo me siento?' El director acepta. Vas tres días, te miden, y vuelves. No te comprometes del todo pero ves el nivel. El director anota: 'Inteligente pero indeciso.'",
                    0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1);

// A030 — Firmar el primer contrato profesional
            Event a030 = footballDataService.createEvent(
                    "El bolígrafo más pesado de tu vida",
                    "Despacho del director deportivo. Corbata. Traje. Un contrato sobre la mesa con tu nombre. El escudo del club en la esquina. El director te tiende un bolígrafo de los buenos, de los que pesan: 'Lee todo. Y cuando estés listo... firma.'",
                    "academia", "unico", "legendary", "academia", 16, 19, 1, 4, null
            );
            footballDataService.createOption(a030,
                    "Firmar sin que te tiemble el pulso",
                    "Tu firma nunca ha sido tan bonita. 'Bienvenido al fútbol profesional.' Sales y le pegas una patada al aire en el pasillo. Pero no has leído la letra pequeña y tu representante te dice después que las condiciones no eran las mejores.",
                    0, 0, 0, 0, 0, 0, 5, 300, 4, 2, 3, 4, 1, 1);
            footballDataService.createOption(a030,
                    "Pedir tiempo para leerlo con tu familia",
                    "El director frunce el ceño: 'Dos días. Ni uno más.' Tu padre lee cada cláusula. Descubrís que hay una cláusula de cesión obligatoria. Negociáis mejor. Pero el director te fichó como 'el que duda'.",
                    0, 0, 0, 0, 2, 0, 2, 500, -1, 0, 0, 0, 2, 0);
            footballDataService.createOption(a030,
                    "Preguntar qué plan deportivo tiene el club para ti",
                    "'Antes de firmar, quiero saber: ¿filial, cedido, qué minutos?' El director se recuesta: 'Un chaval con las ideas claras.' Te explica el plan. Firmas con más información pero menos emoción. No le pegas la patada al aire en el pasillo.",
                    0, 0, 0, 0, 0, 0, 3, 400, 2, 0, 0, 1, 1, 3);
// ===== EVENTOS DEPORTIVOS (20 eventos) =====
// Categoría: deportivo | Fase: profesional
// Situaciones de partido, rendimiento, récords, rachas


// D001 — Racha goleadora
            Event d001 = footballDataService.createEvent(
                    "Llevas cinco partidos marcando",
                    "Cinco partidos, cinco goles. Los periódicos hablan de racha histórica. El míster te llama aparte: 'No cambies nada. Ni la dieta, ni las botas, ni la forma de atarte los cordones.'",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d001,
                    "Mantener la calma y seguir con tu rutina",
                    "Misma rutina, mismas botas. Marcas en el sexto. La prensa se vuelve loca. Pero tanto foco en ti empieza a molestar a compañeros que también trabajan duro y nadie les pregunta.",
                    0, 0, 2, 0, 2, 0, 4, 0, 1, -2, 2, 3, 0, 0);
            footballDataService.createOption(d001,
                    "Intentar batir el récord del club",
                    "Vas a por la historia. Sales pensando en goles, no en el equipo. Forzas disparos desde cualquier ángulo. Metes uno más, pero fallas tres ocasiones que antes habrías pasado a un compañero mejor colocado.",
                    0, 0, 3, 0, -1, 0, 3, 0, -1, -2, 1, 2, 0, 0);
            footballDataService.createOption(d001,
                    "Bajar el perfil y dejar que la racha fluya sin presión",
                    "Ni la mencionas. Entrenas normal, juegas normal. El sexto partido no marcas, pero das dos asistencias. La racha se rompe pero nadie te echa nada en cara.",
                    0, 0, -1, 0, 1, 2, 1, 0, 1, 2, 0, 0, 0, 0);

// D002 — Mala racha sin marcar
            Event d002 = footballDataService.createEvent(
                    "Ocho partidos sin ver portería",
                    "Ocho partidos. Cero goles. La grada murmura. El míster te mantiene pero en rueda de prensa le preguntan si va a sentarte: 'Confío en él. Pero la confianza no es infinita.'",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d002,
                    "Quedarte a entrenar tiros después de cada sesión",
                    "Tres semanas de disparos extra. Tu técnica mejora, pero llegas a los partidos con las piernas cargadas. El gol no llega por cansancio, no por falta de práctica.",
                    0, 0, 2, -1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(d002,
                    "Cambiar tu juego: menos gol, más asociación",
                    "Dejas de obsesionarte con marcar y buscas a los compañeros. Dos asistencias en tres partidos. El míster te valora más, pero la prensa titula: 'Ha perdido el olfato goleador.'",
                    0, 1, 0, 0, 0, 2, 1, 0, 1, 2, 0, -1, 0, 0);
            footballDataService.createOption(d002,
                    "Hablar con el psicólogo deportivo",
                    "Una hora donde no hablas de fútbol. Sales aliviado y el sábado marcas. Pero un compañero se entera y comenta: 'Este necesita psicólogo para meter goles.' El vestuario es cruel a veces.",
                    0, 0, 1, 0, 3, 0, 1, -100, 0, -1, 0, 0, 0, 0);

// D003 — El derbi
            Event d003 = footballDataService.createEvent(
                    "Día de derbi",
                    "El vestuario vibra. El capitán no necesita dar charla. Los ultras llevan cantando desde las siete de la mañana. El árbitro pita y el campo ruge. ¿Cómo entras?",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d003,
                    "Enchufado al máximo desde el primer minuto",
                    "Primera jugada, primera entrada. El derbi se juega con las tripas. Pero tanta intensidad te pasa factura: en el 60 estás fundido y el míster te cambia. Te pierdes el final.",
                    1, 0, 1, -1, 0, 0, 2, 0, 1, 1, 3, 1, 0, 0);
            footballDataService.createOption(d003,
                    "Jugar con cabeza fría y esperar tu momento",
                    "Mientras todos se calientan, tú juegas tranquilo. En el 70 apareces donde nadie te espera. Pero la afición te ha gritado 'cobarde' durante una hora porque no entrabas al cuerpo.",
                    0, 1, 1, 0, 2, 1, 2, 0, 2, 0, -1, 1, 0, 0);
            footballDataService.createOption(d003,
                    "Liderar al equipo con la voz y el ejemplo",
                    "Gritas, ordenas, animas. No eres el que más corre pero sí el que más manda. El capitán te mira raro: ese es SU papel. Pero el equipo responde a tu energía.",
                    0, 0, 0, 0, 1, 0, 1, 0, -1, 1, 2, 0, 0, 0);

// D004 — Expulsado por protestar
            Event d004 = footballDataService.createEvent(
                    "Roja directa",
                    "El árbitro pita una falta absurda. Se te va la boca. Dos palabras de más. Roja directa. El vestuario se queda en silencio cuando entras. El míster ni te mira.",
                    "deportivo", "informativo", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d004,
                    "Pedir disculpas al vestuario al día siguiente",
                    "'Lo siento, he perjudicado al equipo.' El capitán asiente. El míster dice: 'Que no se repita.' Pero algunos piensan que te has rebajado demasiado y que la falta ERA absurda.",
                    0, 0, 0, 0, 1, 0, -2, 0, 1, 2, 0, 0, 0, 0);
            footballDataService.createOption(d004,
                    "No disculparte, la falta era inventada",
                    "'La falta no existía, lo vio todo el estadio.' Algunos compañeros asienten. El míster no. 'Me da igual. Un profesional no hace eso.' Pero en el vestuario ganas algo de respeto por no bajar la cabeza.",
                    0, 0, 0, 0, 0, 0, -1, 0, -2, 1, 0, -2, 0, 0);
            footballDataService.createOption(d004,
                    "Hablar con el míster en privado, no delante de todos",
                    "Le esperas después del entrenamiento: 'Míster, me pasé. No va a volver a pasar.' En privado, sin espectáculo. Él asiente: 'Así se hablan las cosas.'",
                    0, 0, 0, 0, 0, 0, -1, 0, 2, 0, 0, 0, 0, 0);

// D005 — Primer gol con el equipo
            Event d005 = footballDataService.createEvent(
                    "¡GOOOL! El primero con esta camiseta",
                    "Centro desde la banda, enganchas de primeras y la red se mueve. Cinco compañeros encima tuyo gritándote al oído. El estadio corea tu nombre por primera vez.",
                    "deportivo", "informativo", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d005,
                    "Celebrarlo con la grada",
                    "Te deslizas de rodillas y señalas al cielo. La afición te adopta. Pero el capitán te dice después: 'Mola, pero primero celebra con nosotros, ¿no?'",
                    0, 0, 0, 0, 0, 0, 3, 0, 0, -1, 4, 2, 0, 0);
            footballDataService.createOption(d005,
                    "Celebrarlo con tus compañeros",
                    "Corres al banquillo y abrazas a todos. El vestuario te quiere más. Pero la celebración no sale en las redes y tu representante te dice: 'Chaval, la marca personal también importa.'",
                    0, 0, 0, 0, 0, 0, 1, 0, 1, 3, 1, 0, -1, 0);
            footballDataService.createOption(d005,
                    "Celebración contenida y volver al centro del campo",
                    "Un puño apretado, mirada al cielo, y a seguir. El capitán asiente: 'Así se hace.' Pero la afición quería más espectáculo. Te ven frío.",
                    0, 0, 0, 0, 1, 0, 1, 0, 2, 1, -1, 0, 0, 0);

// D006 — Entrando desde el banquillo
            Event d006 = footballDataService.createEvent(
                    "Hoy empiezas en el banquillo",
                    "El once sale y tú no estás. En el 60, con 0-0, el míster te mira: 'Calienta. Tienes media hora para convencerme.'",
                    "deportivo", "decision", "common", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d006,
                    "Salir a comerte el mundo en 30 minutos",
                    "Entras enchufado. Regate, disparo, presión. El campo nota tu hambre. Pero tanta intensidad desordena al equipo y el míster grita desde la banda: '¡No vayas solo!'",
                    1, 1, 1, 0, -1, 0, 2, 0, -1, 0, 2, 1, 0, 0);
            footballDataService.createOption(d006,
                    "Jugar inteligente, sin prisa",
                    "Tocas bien, te colocas mejor, ayudas al equipo. El míster lo valora. Pero no haces nada que te diferencie del que estaba antes. ¿Has convencido o has sido uno más?",
                    0, 0, 0, 0, 1, 1, 0, 0, 2, 1, 0, 0, 0, 0);
            footballDataService.createOption(d006,
                    "Centrarte en una jugada decisiva: la calidad sobre la cantidad",
                    "Tocas poco pero cada balón cuenta. En el 82 recibes, te giras y filtras un pase de gol. Tu compañero marca. Una jugada. Un cambio de partido. Eso es lo que hace un revulsivo.",
                    0, 0, 0, 0, 0, 2, 3, 0, 1, 1, 1, 2, 0, 0);

// D007 — Lesión leve en partido
            Event d007 = footballDataService.createEvent(
                    "Un golpe en la rodilla",
                    "Minuto 35. Choque con un defensa. Te duele la rodilla. El fisio dice: 'Puedes seguir, pero si te la vuelven a dar, podrías estar un mes fuera.' Quedan 55 minutos.",
                    "deportivo", "decision", "common", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d007,
                    "Seguir jugando y arriesgarte",
                    "Aprietas los dientes y sigues. Acabas el partido entero. Al día siguiente la rodilla está hinchada: una semana de reposo. Pero el míster te dice: 'Eso es tener huevos.'",
                    0, 0, 0, -1, 1, 0, 1, 0, 2, 1, 1, 0, 0, 0);
            footballDataService.createOption(d007,
                    "Pedir el cambio y ser precavido",
                    "Levantas la mano. No es heroico, pero al lunes estás entrenando. El problema: tu sustituto marca un doblete y el míster le da la titularidad la semana siguiente.",
                    0, 0, 0, 1, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d007,
                    "Seguir pero bajando la intensidad para protegerte",
                    "Juegas los 55 minutos restantes al 60%. No te lesionas más, pero tampoco aportas. El míster lo nota: 'Si no puedes al 100%, mejor sal.'",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0);

// D008 — Hat-trick
            Event d008 = footballDataService.createEvent(
                    "Llevas dos goles y queda media hora",
                    "Dos goles en 60 minutos. La grada grita tu nombre. El hat-trick está ahí. El capitán te busca con cada balón.",
                    "deportivo", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d008,
                    "Ir a por el tercero con todo",
                    "Cada balón que te llega, disparas. En el 78, cabezazo y red. HAT-TRICK. Te llevas el balón a casa. Pero dos compañeros estaban solos y no les has pasado. En el vestuario hay alguna cara larga.",
                    0, 0, 3, 0, 1, 0, 6, 0, -1, -2, 5, 4, 0, 0);
            footballDataService.createOption(d008,
                    "Jugar para el equipo, que el hat-trick venga solo si tiene que venir",
                    "En el 78 tienes la oportunidad pero ves a un compañero solo. Le pasas. Él marca. 4-0. El hat-trick se queda en doblete. Tu compañero te abraza. El vestuario te aplaude. Pero en casa piensas en el tercero que no tiraste.",
                    0, 0, 0, 0, 1, 2, 2, 0, 3, 3, 1, 0, 0, 0);

// D009 — Penalti en partido importante
            Event d009 = footballDataService.createEvent(
                    "Penalti y todo en juego",
                    "Minuto 85. El equipo va 1-2 abajo. Penalti. El lanzador habitual ha sido sustituido. El capitán mira alrededor. Alguien tiene que cogerlo.",
                    "deportivo", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d009,
                    "Coger el balón y lanzar",
                    "Silencio. Carrera. Gol. Empate. Eres el héroe. Pero te has saltado al capitán, que iba a tirarlo. El abrazo que te da después no es del todo sincero.",
                    0, 0, 3, 0, 3, 0, 5, 0, -1, -1, 4, 3, 0, 0);
            footballDataService.createOption(d009,
                    "Ofrecértelo al veterano del equipo",
                    "Se lo ofreces al veterano. Él marca. El estadio canta su nombre. Tú te quedas con la duda. Pero el veterano te busca después: 'La próxima es tuya. Te lo debo.'",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0);
            footballDataService.createOption(d009,
                    "Preguntar al capitán quién debe tirarlo",
                    "'Capi, ¿quién lo tira?' El capitán te señala: 'Tú.' Lo tiras con la bendición del vestuario. Gol. Héroe con respaldo. Pero has tardado 30 segundos que han enfriado el momento.",
                    0, 0, 2, 0, 1, 0, 3, 0, 1, 2, 2, 1, 0, 0);

// D010 — Asistencia de fantasía
            Event d010 = footballDataService.createEvent(
                    "Una jugada que se te ocurre a ti solo",
                    "Recibes de espaldas, te giras, ves un pasillo que nadie más ve. Un pase de 30 metros que deja a tu compañero solo. Marca. El estadio aplaude el gol. Los que entienden, aplauden tu pase.",
                    "deportivo", "informativo", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d010,
                    "Celebrarlo señalándote los ojos: 'Visión'",
                    "Te señalas los ojos mirando a la cámara. La imagen se viraliza. Tu representante feliz. Pero el compañero que marcó comenta: 'Eh, que el gol lo he metido yo.'",
                    0, 0, 0, 0, 0, 2, 3, 0, 0, -1, 2, 3, 1, 0);
            footballDataService.createOption(d010,
                    "Ir a celebrar con el compañero que ha marcado",
                    "Corres hacia él, le abrazas, le señalas. El vestuario ve que no te llevas el mérito solo. Pero la prensa ni menciona tu pase en la crónica.",
                    0, 0, 0, 0, 0, 1, 1, 0, 1, 3, 0, -1, 0, 0);
            footballDataService.createOption(d010,
                    "Volver a tu posición como si fuera un pase más",
                    "Un aplauso discreto y a seguir. Los que saben, saben. No hace falta gritarlo. Pero nadie te pregunta en la zona mixta y el pase pasa desapercibido para el gran público.",
                    0, 0, 0, 0, 1, 0, 0, 0, 2, 1, 0, 0, 0, 0);

// D011 — Titular por primera vez
            Event d011 = footballDataService.createEvent(
                    "Hoy sales de inicio",
                    "El míster pone el once en la pizarra. Tu nombre está ahí. Primera titularidad. El capitán te mira: 'No te me cagues, ¿eh?' Sonríe al decirlo.",
                    "deportivo", "informativo", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d011,
                    "Salir concentrado, hacer las cosas bien sin arriesgar",
                    "90 minutos sólidos. No marcas, no asistes, pero no pierdes un balón. El míster te da la mano. Pero mañana la prensa titula: 'Debut gris.' Nadie recuerda un partido sin errores.",
                    0, 0, 0, 0, 1, 0, 1, 0, 3, 1, 0, -1, 0, 0);
            footballDataService.createOption(d011,
                    "Intentar demostrar que mereces estar siempre ahí",
                    "Regates, disparos, pases largos. Unas salen y otras no. El míster te mira con esa cara de 'relájate'. Pero la grada se levanta en un par de jugadas y tu nombre suena con fuerza.",
                    0, 1, 1, 0, -1, 0, 2, 0, -1, 0, 2, 2, 0, 0);

// D012 — Gol en el descuento
            Event d012 = footballDataService.createEvent(
                    "Minuto 93 y pierdes 0-1",
                    "4 minutos de descuento. 0-1. Saque de esquina. Todo el equipo sube. El córner viene al segundo palo. Te llega a ti.",
                    "deportivo", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d012,
                    "Cabezazo con todo lo que tienes",
                    "Saltas más alto que nunca. Gol. Empate en el 93. Eres el héroe. Pero al caer aterrizas mal y te tuerces el tobillo. Una semana fuera mínimo.",
                    0, 0, 2, -1, 2, 0, 5, 0, 1, 2, 5, 3, 0, 0);
            footballDataService.createOption(d012,
                    "Peinar para el compañero que viene detrás",
                    "Peinas de cabeza. Tu compañero empuja a gol. Empate. Él es el héroe. Tú sabes lo que has hecho, pero el mundo no.",
                    0, 0, 0, 0, 0, 1, 1, 0, 1, 2, 1, 0, 0, 0);
            footballDataService.createOption(d012,
                    "Controlar y buscar una ocasión más clara",
                    "Bajas el balón con el pecho, te la juegas a un regate... y un defensa te la quita. Se acabó el partido. 0-1. El vestuario en silencio. El míster no te mira.",
                    0, 0, 0, 0, 0, 0, -2, 0, -2, -1, -2, -1, 0, 0);

// D013 — Cambio táctico del míster
            Event d013 = footballDataService.createEvent(
                    "El míster cambia el sistema",
                    "Descanso. 0-0. El míster borra la pizarra: 'Vamos a cambiarlo todo. Tú juegas más adelantado.' Tu posición cambia.",
                    "deportivo", "decision", "common", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d013,
                    "Adaptarte sin rechistar",
                    "No es tu sitio pero funcionas. El equipo mejora. El míster te señala: 'Un jugador inteligente.' Pero juegas fuera de posición y tus stats no brillan: ni goles ni asistencias desde esa zona.",
                    0, 0, 0, 0, 1, 1, 0, 0, 3, 1, 0, 0, 0, 0);
            footballDataService.createOption(d013,
                    "Decirle que rindes más en tu posición natural",
                    "El míster frunce el ceño: 'Si no te gusta, te siento.' Juegas donde dice, pero con menos convicción. El equipo no mejora. ¿Ha sido culpa tuya o del sistema?",
                    0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 0);
            footballDataService.createOption(d013,
                    "Aceptar pero proponer una alternativa táctica",
                    "'Míster, ¿y si en vez de ahí juego aquí? Puedo hacer lo mismo pero desde mi zona.' El míster se lo piensa. 'Vale, probemos.' El equipo gana. Pero has cuestionado al entrenador delante de todos.",
                    0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0);

// D014 — Contra tu ex-equipo
            Event d014 = footballDataService.createEvent(
                    "Hoy juegas contra tu ex-equipo",
                    "El calendario lo quiso así. Te toca enfrentarte al club que te formó. En la grada hay gente que te vio debutar. ¿Cómo lo enfocas?",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d014,
                    "Saludar a tus ex-compañeros y jugar con respeto",
                    "Abrazos en el calentamiento. Cuando marcas, no celebras. La grada rival te aplaude. Pero tu nueva afición te grita: '¡Celebra, coño, que ahora eres nuestro!'",
                    0, 0, 0, 0, 1, 0, 2, 0, 0, 1, -1, 3, 0, 0);
            footballDataService.createOption(d014,
                    "Jugar a muerte como contra cualquier otro",
                    "Entras a competir sin sentimentalismos. Marcas y celebras. Tu nueva afición te adora. La antigua te silba el resto del partido. Algunos ex-compañeros ya no te saludan.",
                    0, 0, 1, 0, 0, 0, 2, 0, 1, -1, 3, -2, 0, 0);
            footballDataService.createOption(d014,
                    "Dedicar el gol a ambas aficiones",
                    "Si marcas, celebras discreto y aplaudes a las dos gradas. Un gesto diplomático. Pero ninguna de las dos aficiones siente que seas 100% suyo.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0);

// D015 — Fallo garrafal
            Event d015 = footballDataService.createEvent(
                    "El fallo que nadie olvida",
                    "Solo ante el portero. Estadio de pie. Chut. Al poste. Rebote. Fuera. El silencio dura tres segundos y pesa tres toneladas. El míster se lleva las manos a la cabeza.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d015,
                    "Apretar los dientes y seguir pidiendo el balón",
                    "Diez minutos después vuelves a pedir el balón. El fallo no te define. Pero el defensa que te marca ya te ha calado: sabe que estás nervioso y te provoca.",
                    0, 0, 0, 0, 2, 0, 1, 0, 1, 1, 1, 0, 0, 0);
            footballDataService.createOption(d015,
                    "Cambiar tu juego: bajar la presión y jugar para otros",
                    "Dejas de buscar el gol y te centras en asistir. La presión baja. Un compañero marca con tu pase. No redimes el fallo, pero aportas. El míster asiente.",
                    0, 0, 0, 0, 0, 2, 0, 0, 1, 2, 0, 0, 0, 0);
            footballDataService.createOption(d015,
                    "Pedir al míster que te cambie para despejar la cabeza",
                    "'Míster, cámbiame, no estoy bien.' El entrenador te mira sorprendido. Te sienta. Reconocer tus límites es valiente. Pero también es dejar a tu equipo con uno menos cuando más te necesitan.",
                    0, 0, 0, 0, 1, 0, -1, 0, 0, 0, -1, 0, 0, 0);

// D016 — Convocado para la selección
            Event d016 = footballDataService.createEvent(
                    "Lista de convocados: tu nombre está",
                    "La lista de la selección absoluta aparece en redes sociales. Tu nombre. Ahí. Entre los 23. Tu teléfono no para de sonar. Tu madre llora. Tu padre dice: 'Joder.'",
                    "deportivo", "unico", "epic", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d016,
                    "Ir con los pies en la tierra y aprender",
                    "Llegas como uno más. Escuchas, entrenas, aprendes. El seleccionador lo nota: 'Este chico es humilde.' Pero nadie habla de ti. Vuelves sin haber dejado huella.",
                    0, 0, 0, 0, 2, 0, 3, 0, 0, 2, 1, 1, 0, 0);
            footballDataService.createOption(d016,
                    "Llegar decidido a hacerte un hueco",
                    "No has venido a hacer turismo. Cada entreno es una prueba. Destacas, pero un veterano te dice: 'Tranquilo, machote, que esto no es tu club.' La selección tiene jerarquías.",
                    0, 1, 1, 0, -1, 0, 4, 0, -1, -1, 2, 3, 0, 0);
            footballDataService.createOption(d016,
                    "Centrarte en conectar con los cracks del vestuario",
                    "Te acercas a los referentes, pides consejo, escuchas. Vuelves con contactos y aprendizaje, pero en el campo apenas has tocado balón. La experiencia ha sido más social que deportiva.",
                    0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 1, 0);

// D017 — Suplente tres partidos
            Event d017 = footballDataService.createEvent(
                    "Tres partidos mirando desde el banquillo",
                    "Tercer partido consecutivo sin jugar. El que juega en tu puesto no lo está haciendo mal. Pero tampoco brillante.",
                    "deportivo", "decision", "common", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d017,
                    "Hablar con el míster y preguntarle qué necesitas",
                    "'¿Qué tengo que hacer para jugar?' El míster te mira: 'Entrenar bien y esperar.' Te queda claro que no hay atajos. Pero al menos sabe que te importa.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d017,
                    "Reventar cada entrenamiento para ganarte el puesto",
                    "Si no juegas el sábado, al menos que el míster tenga claro que se equivoca. Entrenas como un animal. Pero tanta intensidad en semana te pasa factura: llegas al sábado con molestias.",
                    0, 1, 0, -1, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0);
            footballDataService.createOption(d017,
                    "Aceptarlo profesionalmente y mantener el nivel sin quejarte",
                    "El fútbol es así. Hoy no juegas, mañana sí. Entrenas bien sin pasarte. Pero por dentro te quema y un día en el entreno sueltas un comentario que el míster no se esperaba.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// D018 — Gol en tu cumpleaños
            Event d018 = footballDataService.createEvent(
                    "Gol el día de tu cumpleaños",
                    "Hoy es tu cumpleaños y hay partido. En el minuto 22, te llega un balón suelto en el área y la mandas dentro. El marcador pone tu nombre y la grada canta el cumpleaños feliz.",
                    "deportivo", "informativo", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d018,
                    "Hacer el gesto de soplar las velas en la celebración",
                    "Te pones de rodillas y soplas una vela imaginaria. La imagen se viraliza. Tu representante encantado. Pero el míster comenta: 'Menos teatro y más fútbol.'",
                    0, 0, 0, 0, 0, 0, 3, 0, -1, 0, 3, 3, 2, 0);
            footballDataService.createOption(d018,
                    "Dedicárselo a tu familia en la grada",
                    "Señalas a la grada donde están tus padres. Tu madre se tapa la cara. La imagen no se viraliza, pero el momento es tuyo. Solo tuyo.",
                    0, 0, 0, 0, 1, 0, 2, 0, 0, 1, 2, 1, 0, 0);
            footballDataService.createOption(d018,
                    "Celebrar con el equipo sin hacer nada especial",
                    "Un abrazo con los compañeros y a seguir. Nadie sabe que es tu cumpleaños excepto los del vestuario. A veces los mejores momentos son los que no se comparten con el mundo.",
                    0, 0, 0, 0, 0, 0, 1, 0, 1, 2, 0, 0, 0, 0);

// D019 — Gol polémico
            Event d019 = footballDataService.createEvent(
                    "¿Fue fuera de juego o no?",
                    "Gol tuyo. El estadio celebra. Pero las repeticiones muestran que estabas en fuera de juego por un pie. El VAR revisa. GOL VÁLIDO. El rival protesta. La prensa te preguntará.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d019,
                    "Decir en rueda de prensa que no lo has visto claro",
                    "'No sé si estaba en fuera de juego. Para eso está el VAR.' Respuesta elegante. La prensa te respeta. Pero el rival publica un vídeo donde se ve clarísimo y tú quedas como esquivo.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0);
            footballDataService.createOption(d019,
                    "Decir que era gol legal y punto",
                    "'El VAR ha dicho que sí.' Corto y directo. Tu afición encantada. La prensa rival te pone a caldo. Te ganas un enemigo mediático.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -2, 0, 0);
            footballDataService.createOption(d019,
                    "Reconocer honestamente que probablemente era fuera de juego",
                    "'Creo que estaba fuera. Pero el árbitro ha decidido y respeto la decisión.' La honestidad te gana puntos con todos. Excepto con tu director deportivo, que te dice: 'No hagas eso nunca más. Nos pueden anular goles futuros.'",
                    0, 0, 0, 0, 0, 0, 1, 0, -1, 1, 1, 4, 0, 0);

// D020 — Partido de tu vida
            Event d020 = footballDataService.createEvent(
                    "La noche perfecta",
                    "Hay partidos y HAY partidos. Todo te sale. Cada control, cada pase, cada disparo. Dos goles, una asistencia y una ovación de pie cuando te sustituyen en el 80. Esto es lo que soñabas de crío.",
                    "deportivo", "informativo", "epic", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d020,
                    "Disfrutarlo sabiendo que noches así hay pocas",
                    "Te sientas en el banquillo con una sonrisa tonta. El míster: 'Ojalá jugaras así cada semana.' Disfrutas el momento. Pero la semana siguiente no llegas ni de cerca a ese nivel y la comparación duele.",
                    1, 1, 1, 0, 1, 1, 5, 0, 2, 2, 4, 3, 1, 1);
            footballDataService.createOption(d020,
                    "Tomarlo como el nuevo estándar",
                    "Esto no es una excepción. Este es tu nuevo mínimo. Entrenas la semana siguiente con una exigencia brutal. Mejoras... pero también te frustras cuando algo no sale perfecto. La autoexigencia es un arma de doble filo.",
                    0, 0, 0, 0, -1, 0, 4, 0, 1, 0, 2, 2, 0, 0);
            footballDataService.createOption(d020,
                    "Compartir el mérito con todo el equipo",
                    "En la zona mixta: 'Ha sido una noche del equipo, no mía.' Los compañeros te lo agradecen. Pero la prensa quería una frase de estrella y te describe como 'discreto pese a un partido sobresaliente.'",
                    0, 0, 0, 0, 0, 0, 2, 0, 2, 3, 1, -1, 0, 0);
            // ===== EVENTOS DEPORTIVOS — SEGUNDA TANDA (D021-D050) =====
// Pegar en DataInitializer junto a los D001-D020

// D021 — Autogol
            Event d021 = footballDataService.createEvent(
                    "La has metido en tu propia portería",
                    "Centro desde la banda. Intentas despejar de cabeza y la mandas al fondo de tu propia red. Tu portero te mira. El míster se sienta. Quedan 40 minutos.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d021,
                    "Levantar la mano, disculparte y seguir",
                    "Pides perdón al portero y corres como si no hubiera pasado. El míster asiente. Pero por dentro te tiembla todo y en la siguiente jugada dudas medio segundo de más.",
                    0, 0, 0, 0, 1, 0, -1, 0, 1, 1, 0, 0, 0, 0);
            footballDataService.createOption(d021,
                    "Canalizar la rabia y jugar con más intensidad",
                    "El autogol te enciende. Sales a matar. Recuperas tres balones, disputas todo. Pero la rabia te hace cometer una falta innecesaria y ves amarilla.",
                    1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0);
            footballDataService.createOption(d021,
                    "Pedir al míster que te cambie para no empeorar las cosas",
                    "Levantas la mano: 'Míster, cámbiame, no estoy bien.' Reconocer tus límites es valiente. Pero el vestuario piensa que te has rajado cuando más te necesitaban.",
                    0, 0, 0, 0, 0, 0, -1, 0, 0, -2, -1, 0, 0, 0);

// D022 — El VAR te anula un golazo
            Event d022 = footballDataService.createEvent(
                    "Gol anulado por el VAR",
                    "Volea desde fuera del área. Escuadra. Corres a celebrarlo. Dos minutos de espera. Gol anulado por fuera de juego en el inicio de la jugada.",
                    "deportivo", "decision", "common", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d022,
                    "Aceptarlo y seguir a lo tuyo",
                    "Respiras hondo. El gol vendrá. Pero ese golazo era especial y no lo puedes repetir. La frustración contenida te pesa en los últimos 20 minutos.",
                    0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 0, 0);
            footballDataService.createOption(d022,
                    "Protestar al cuarto árbitro",
                    "Te acercas a la banda gesticulando. Te llevas una amarilla. Pero el vestuario ve que peleas por cada gol y tu carácter sube enteros en el grupo.",
                    0, 0, 0, 0, -1, 0, -1, 0, -1, 1, 0, -1, 0, 0);
            footballDataService.createOption(d022,
                    "Usar la rabia como combustible para el resto del partido",
                    "El gol anulado te pone a mil. Sales enchufado. Intentas otra volea en el 75... que se va a la grada. A veces la rabia no es el mejor consejero.",
                    0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0);

// D023 — Gol de falta directa
            Event d023 = footballDataService.createEvent(
                    "Falta al borde del área",
                    "Falta a 20 metros. El lanzador habitual se acerca al balón, pero tú sientes que hoy es tu día. La barrera se coloca. El portero grita instrucciones.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d023,
                    "Pedir la falta y lanzar",
                    "La colocas en el ángulo. Golazo. Hasta los rivales aplauden. Pero el lanzador habitual no te habla el resto del partido. Has pisado su territorio.",
                    0, 0, 3, 0, 1, 0, 4, 0, -1, -2, 3, 3, 0, 0);
            footballDataService.createOption(d023,
                    "Dejar que lance el especialista",
                    "Le dejas. Lanza... y la mete. Celebráis juntos. A veces lo inteligente es saber cuándo no actuar. Pero el ojeador que estaba mirando no apunta tu nombre.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, -1, 0, 0);
            footballDataService.createOption(d023,
                    "Proponer tirarla entre los dos: tú amago, él disparo",
                    "Le dices al oído: 'Yo amago, tú tiras.' Él acepta. Confusión en la barrera, gol. La prensa elogia la coordinación. Ni eres el héroe solo ni te quedas fuera.",
                    0, 1, 0, 0, 0, 1, 2, 0, 1, 2, 1, 1, 0, 0);

// D024 — Te pisan adrede
            Event d024 = footballDataService.createEvent(
                    "El defensa rival te tiene fichado",
                    "Llevas tres faltas del mismo defensa. En la cuarta, te pisa el tobillo con los tacos cuando el balón ya no está. El árbitro pita pero no saca tarjeta. El defensa te susurra: 'La próxima va sin balón.'",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d024,
                    "No entrar al trapo y ganarle con fútbol",
                    "Le haces un caño y celebras mirándole. Él se calienta y comete falta: amarilla. Pero la provocación puede volverse en tu contra si el árbitro te ve regodeándote.",
                    0, 1, 0, 0, 1, 0, 2, 0, 0, 0, 1, 1, 0, 0);
            footballDataService.createOption(d024,
                    "Encararle y decirle que se deje de juego sucio",
                    "Te acercas y le dices cuatro cosas. El árbitro os separa. Amarilla para los dos. No es elegante pero el defensa deja de pegarte el resto del partido.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, -1, 0, 0);
            footballDataService.createOption(d024,
                    "Decírselo al árbitro discretamente",
                    "Te acercas al colegiado y le explicas la situación. El árbitro le avisa. La siguiente falta del defensa es amarilla. Pero algunos compañeros te ven como un quejica.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 1, 0, 0);

// D025 — Tanda de penaltis
            Event d025 = footballDataService.createEvent(
                    "Tanda de penaltis. Eliminatoria.",
                    "Empate tras la prórroga. Todo se decide desde los once metros. Tu nombre está en la lista. Eres el cuarto. Los tres primeros de cada equipo marcan. Te toca.",
                    "deportivo", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d025,
                    "Tirar con potencia al centro",
                    "Cañonazo al centro. El portero estaba volando hacia la izquierda. Gol. Pero si hubiera adivinado y se queda, te la para seguro. Ha sido una apuesta.",
                    0, 0, 2, 0, 1, 0, 3, 0, 0, 1, 2, 1, 0, 0);
            footballDataService.createOption(d025,
                    "Colocarla con sutileza a un lado",
                    "Toque suave al palo largo. El portero adivina el lado pero no llega. Entra justa. Has sido preciso, pero la falta de potencia ha estado a punto de costarte.",
                    0, 1, 1, 0, 0, 0, 2, 0, 0, 0, 2, 1, 0, 0);
            footballDataService.createOption(d025,
                    "Esperar a que el portero se tire y picarla por el centro",
                    "Panenka. El portero se tira a la derecha. El balón entra suave por el centro. El estadio estalla. Pero si no se tira... eso habría sido la humillación de tu vida.",
                    0, 0, 2, 0, 2, 0, 5, 0, -1, 0, 4, 4, 0, 0);

// D026 — Cambio de entrenador
            Event d026 = footballDataService.createEvent(
                    "Han echado al míster",
                    "Llegas al entrenamiento y el míster ha sido destituido. El nuevo tiene carpeta nueva y cara de pocos amigos: 'No conozco a nadie. Todo el mundo empieza de cero.'",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d026,
                    "Adaptarte al nuevo sistema cuanto antes",
                    "Escuchas, obedeces, entrenas como el primer día. El nuevo míster tarda dos semanas en fiarse de ti. Pero el anterior era tu aliado y con él tenías más minutos. Con el nuevo, empiezas de cero de verdad.",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d026,
                    "Ir a presentarte y hablar con él directamente",
                    "Le buscas en su despacho: 'Míster, quiero que sepa que estoy a disposición.' Él te mira: 'Bien. Demuéstramelo.' Has dado un paso que pocos dan. Pero no es garantía de nada.",
                    0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(d026,
                    "Mantener perfil bajo y esperar a ver qué pide",
                    "Observas, escuchas y te adaptas sin hacer ruido. El nuevo míster no te tiene en el radar pero tampoco te tiene en la lista negra. A veces la invisibilidad es una estrategia.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0);

// D027 — Perder una final
            Event d027 = footballDataService.createEvent(
                    "Final perdida",
                    "Has llegado a la final. 0-1 con un gol en el descuento. El trofeo se queda en la otra vitrina. El rival celebra. Tú te quedas sentado en el césped.",
                    "deportivo", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d027,
                    "Felicitar al rival y prometer volver",
                    "Te acercas al capitán rival, le das la mano. Elegante. Pero cuando subes al autobús, la rabia contenida se convierte en insomnio. Dos semanas sin dormir bien.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 3, 0, 0);
            footballDataService.createOption(d027,
                    "Irte al vestuario sin hablar con nadie",
                    "Cabeza baja, ducha, autobús. La prensa te critica por no dar la cara. Pero a veces necesitas el silencio para procesar una derrota así.",
                    0, 0, 0, 0, 0, 0, -1, 0, 0, 0, -1, -1, 0, 0);
            footballDataService.createOption(d027,
                    "Quedarte en el campo hasta que apaguen las luces",
                    "Te sientas en el banquillo vacío. Solo. Con el dolor. La imagen se viraliza. Unos dicen que es dramático, otros que es el retrato perfecto de la derrota. Pero a ti te sirve para no olvidar nunca esta sensación.",
                    0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 1, 2, 0, 0);

// D028 — Ganar tu primer título
            Event d028 = footballDataService.createEvent(
                    "¡CAMPEONES!",
                    "Pitido final. 2-0. Tu primer título. El estadio estalla. El capitán te planta la copa en las manos: 'Levántala tú.' Tiemblas. La levantas.",
                    "deportivo", "unico", "epic", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d028,
                    "Disfrutarlo como un niño",
                    "Corres por el campo con la copa como un loco. Tu madre llora en la grada. Esto es por lo que empezaste. Pero la celebración se alarga y al día siguiente tienes resaca y pierdes un entreno.",
                    0, 0, 0, -1, 0, 0, 7, 200, 0, 2, 5, 4, 1, 2);
            footballDataService.createOption(d028,
                    "Celebrarlo con el equipo sin acaparar protagonismo",
                    "Te fundes en un abrazo con todos: el suplente, el fisio, el utillero. Esto no es de uno. Pero tu representante te dice: 'Chaval, levanta más la copa cuando hay cámaras. Eres tú el que tiene que salir en la portada.'",
                    0, 0, 0, 0, 1, 0, 5, 200, 3, 4, 2, 2, -1, 1);

// D029 — Remontada épica
            Event d029 = footballDataService.createEvent(
                    "Remontada épica",
                    "Ibais 0-2 en el descanso. El míster ha cambiado la táctica. 1-2 en el 60. 2-2 en el 75. Queda un cuarto de hora. ¿Se puede?",
                    "deportivo", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d029,
                    "Pedir el balón y liderar el ataque final",
                    "Cada jugada pasa por ti. En el 88, un regateo y un pase al hueco. 3-2. El estadio se cae. Pero has ignorado al capitán en dos jugadas y se lo va a guardar.",
                    0, 1, 0, 0, 1, 1, 5, 0, -1, -1, 4, 3, 0, 0);
            footballDataService.createOption(d029,
                    "Defender el empate y asegurar el punto",
                    "Con 2-2, una derrota sería catastrófica. Te repliegas, ayudas atrás. Aseguras el punto. Pero la grada quería más y te silba al final. El punto sabe a poco.",
                    0, 0, 0, 0, 1, 0, 0, 0, 2, 1, -1, 0, 0, 0);
            footballDataService.createOption(d029,
                    "Jugar equilibrado: atacar sin descuidar atrás",
                    "Ni héroe ni cobarde. Atacas cuando toca, defiendes cuando hay que defender. El partido acaba 2-2. No es la remontada épica pero tampoco el desastre. El míster te dice: 'Inteligente.'",
                    0, 0, 0, 0, 0, 1, 1, 0, 2, 1, 0, 0, 0, 0);

// D030 — Te roban un gol cantado
            Event d030 = footballDataService.createEvent(
                    "Te han robado un gol en la línea",
                    "Regateas al portero, gol cantado, solo tienes que empujarla... y un compañero aparece y la mete él. Se lleva la celebración.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d030,
                    "Tragártelo y celebrar con él",
                    "Le abrazas. Por fuera sonríes, por dentro hierves. Pero eres profesional. Y el equipo ha marcado. ¿Cuánto tiempo puedes guardar esa rabia?",
                    0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 1, 0, 0);
            footballDataService.createOption(d030,
                    "Decirle en el vestuario que eso no se hace",
                    "'Tío, eso era mío.' Él se disculpa a medias. La tensión queda ahí. Pero no vuelve a hacerlo nunca más.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0);
            footballDataService.createOption(d030,
                    "No decir nada pero dejar de buscarle en el campo",
                    "No le dices nada. Pero las siguientes tres semanas, cuando le ves desmarcado, eliges otra opción. Él lo nota. Tú sabes que lo notas. Es una guerra fría sin palabras.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, -3, 0, 0, 0, 0);

// D031 — Partido bajo lluvia
            Event d031 = footballDataService.createEvent(
                    "Diluvio universal en el campo",
                    "Llueve como si el cielo se hubiera roto. El campo es un barrizal. El balón no bota, los tacos resbalan. El árbitro decide seguir.",
                    "deportivo", "decision", "common", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d031,
                    "Juego directo, balones largos y físico",
                    "Te adaptas al barro. Correr y pelear. El que más barro tenga al final, gana. Pero un resbalón en una entrada te deja con dolor de cadera dos días.",
                    1, 0, 0, 2, 0, 0, 1, 0, 2, 1, 1, 0, 0, 0);
            footballDataService.createOption(d031,
                    "Intentar mantener el toque pese al barro",
                    "Te niegas a jugar a pelotazo. Sigues tocando. Algunos pases se quedan cortos en el agua, pero cuando llegan, llegan limpios. El míster te mira con asombro. Los compañeros con desesperación.",
                    0, 2, 0, 0, 0, 0, 2, 0, 0, -1, 0, 1, 0, 0);
            footballDataService.createOption(d031,
                    "Adaptarte minuto a minuto: si toca pelear, peleas; si toca tocar, tocas",
                    "No te casas con un estilo. Lees el juego y cambias. No eres el más brillante pero sí el más inteligente del campo. Solo que nadie lo nota porque no haces nada espectacular.",
                    0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0);

// D032 — Debut en Champions
            Event d032 = footballDataService.createEvent(
                    "Noche de Champions",
                    "La música del himno suena EN EL CAMPO. Las estrellas en los paneles brillan. Se te pone la piel de gallina. Esto ya no es fútbol normal.",
                    "deportivo", "unico", "epic", "profesional", 20, 38, 3, 3, null
            );
            footballDataService.createOption(d032,
                    "Disfrutar el momento pero mantener la concentración",
                    "Tres segundos de emoción durante el himno. Después, modo partido. Juegas bien pero sin brillar. El titular de mañana: 'Correcto en su debut europeo.' Correcto no vende.",
                    0, 0, 0, 0, 2, 0, 4, 0, 1, 1, 1, 1, 0, 0);
            footballDataService.createOption(d032,
                    "Salir a demostrar que estás a la altura",
                    "Europa tiene que saber tu nombre. Buscas el balón, intentas regates. La Champions no perdona: un error táctico y el rival marca. El míster te mira. Pero diez minutos después asistes un gol que redime todo.",
                    0, 1, 1, 0, -1, 0, 5, 0, -1, 0, 3, 3, 0, 0);

// D033 — Contra tu ídolo
            Event d033 = footballDataService.createEvent(
                    "Hoy juegas contra tu ídolo de la infancia",
                    "Tenías su póster en la habitación. Aprendiste a chutar viéndole por la tele. Y ahora está a tres metros de ti en el túnel de vestuarios.",
                    "deportivo", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d033,
                    "Pedirle la camiseta antes del partido",
                    "Te acercas con respeto: 'Perdona, soy muy fan tuyo. ¿Me cambias la camiseta al final?' Él sonríe y acepta. Pero un compañero te dice: 'Tío, eso no se hace, que somos rivales hoy.'",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, -1, 0, 1, 0, 0);
            footballDataService.createOption(d033,
                    "Tratarle como un rival más y competir",
                    "El póster ya no está en tu habitación. Hoy es un rival. Le ganas un duelo, le haces un caño. Él te mira con respeto. Pero después del partido no te atreves a pedirle la camiseta.",
                    0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0);
            footballDataService.createOption(d033,
                    "Competir durante el partido y pedirle la camiseta al final",
                    "90 minutos de competición limpia. Al final, te acercas: 'Gran partido. ¿Me la cambias?' Él te la da. El equilibrio perfecto entre respeto y profesionalidad.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0);

// D034 — Te silban en tu propio campo
            Event d034 = footballDataService.createEvent(
                    "Tu propia afición te silba",
                    "Tres partidos sin hacer nada relevante. Cuando recibes el balón en el 20, se oyen silbidos desde la grada. Tu grada. Tu gente.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d034,
                    "Demostrarles que se equivocan con fútbol",
                    "Cierras los oídos y abres las piernas. Regate, centro perfecto. Los silbidos se convierten en aplausos. Pero la presión de tener que demostrar cada partido desgasta.",
                    0, 1, 0, 0, 1, 0, 2, 0, 0, 0, 2, 1, 0, 0);
            footballDataService.createOption(d034,
                    "Bajar la cabeza y aceptar que no estás en tu mejor momento",
                    "No te escondes pero tampoco fuerzas. Juegas discreto. El míster te cambia en el 60. La afición no te aplaude ni te silba. Te ignora. Y eso duele más.",
                    0, 0, 0, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0);
            footballDataService.createOption(d034,
                    "Hacer un gesto a la grada pidiendo apoyo",
                    "Levantas las manos pidiendo ánimo. Algunos aplauden, otros silban más fuerte. Es una apuesta: o la grada se vuelca contigo o te señalan como blando.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0);

// D035 — Gol de chilena
            Event d035 = footballDataService.createEvent(
                    "El centro viene alto y tú de espaldas",
                    "Centro perfecto desde la derecha. Te llega a la altura del pecho pero estás de espaldas a portería. Tienes dos opciones: bajarla de pecho, o intentar lo que todo futbolista sueña.",
                    "deportivo", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d035,
                    "Intentar la chilena",
                    "Saltas, conectas con el empeine. Gol. Las portadas. Pero al caer aterrizas mal en la espalda. Nada grave, pero tres días sin entrenar.",
                    0, 0, 3, -1, 1, 0, 7, 0, 0, 1, 5, 5, 2, 1);
            footballDataService.createOption(d035,
                    "Bajarla y jugar seguro",
                    "Control de pecho, giro, disparo raso. Gol. No es espectacular, pero está dentro. Nadie recordará el gol mañana, pero los tres puntos se quedan.",
                    0, 1, 1, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0, 0);

// D036 — Debut con la selección
            Event d036 = footballDataService.createEvent(
                    "Tu primer partido con la selección",
                    "El himno suena. La camiseta de tu país pesa diferente. Jugadores que veías por la tele están a tu lado. El árbitro pita.",
                    "deportivo", "unico", "epic", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d036,
                    "Jugar con humildad, al servicio del equipo",
                    "No eres la estrella. Corres, te ofreces, haces lo que piden. Sales en el 70 con ovación. El seleccionador: 'Volverás.' Pero nadie habla de ti en las crónicas.",
                    0, 0, 0, 0, 2, 0, 4, 0, 0, 2, 1, 1, 0, 0);
            footballDataService.createOption(d036,
                    "Intentar dejar tu sello desde el primer minuto",
                    "Pase de 40 metros, desmarque al espacio, disparo al poste. El seleccionador toma nota. Pero un veterano te dice: 'Aquí se llega pisando despacio, crack.'",
                    0, 1, 1, 0, -1, 0, 5, 0, -1, -1, 2, 3, 0, 0);

// D037 — Gol con la selección
            Event d037 = footballDataService.createEvent(
                    "Gol con tu selección",
                    "Controlas, recortas al defensa, disparas. Gol. Has marcado con tu selección. El estadio ruge. Tu padre, en algún sitio, llora sin hacer ruido.",
                    "deportivo", "informativo", "epic", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d037,
                    "Señalar al escudo con orgullo",
                    "Te tocas el escudo y lo besas. Ese gol es de todo un país. Pero un sector de la prensa escribe: 'Demasiado patriota para una eliminatoria clasificatoria.' No se puede contentar a todos.",
                    0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 3, 2, 0, 0);
            footballDataService.createOption(d037,
                    "Dedicárselo a tu familia",
                    "Miras a cámara y dibujas un corazón. Tu madre, a 3000 kilómetros, llora. Es un momento íntimo en un estadio lleno. Pero el seleccionador quería verte celebrar con el equipo.",
                    0, 0, 0, 0, 1, 0, 4, 0, -1, 0, 2, 1, 0, 0);
            footballDataService.createOption(d037,
                    "Celebrar con todo el equipo",
                    "Corres hacia el banquillo y te tiras encima de todos. El seleccionador incluido. Es la celebración del grupo. Nadie se acuerda de tu celebración específica, pero todos se acuerdan del momento.",
                    0, 0, 0, 0, 0, 0, 4, 0, 1, 2, 1, 1, 0, 0);

// D038 — Te señalan tras una derrota
            Event d038 = footballDataService.createEvent(
                    "El equipo pierde y te señalan a ti",
                    "0-3 en casa. La prensa necesita un culpable. Los titulares de mañana dirán tu nombre. El míster intenta protegerte: 'La derrota es de todos.'",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d038,
                    "Asumir tu parte públicamente",
                    "'He jugado mal. La próxima será diferente.' La prensa se sorprende. El vestuario te respeta. Pero la próxima vez que juegues mal, esperarán otra confesión. Creas un precedente peligroso.",
                    0, 0, 0, 0, 1, 0, -1, 0, 1, 2, 0, 3, 0, 0);
            footballDataService.createOption(d038,
                    "No hablar con la prensa y dejar que pase el ruido",
                    "Zona mixta vacía. No das declaraciones. La prensa titula: 'Se esconde.' Pero en dos días nadie se acuerda. A veces el silencio es la mejor respuesta.",
                    0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d038,
                    "Defender al equipo sin esquivar tu responsabilidad",
                    "'No he jugado bien, pero esto es responsabilidad colectiva.' Equilibrado. Ni mártir ni cobarde. Pero no deja titular y nadie lo recuerda mañana.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0);

// D039 — Ovación del campo rival
            Event d039 = footballDataService.createEvent(
                    "El campo rival te aplaude",
                    "Partidazo fuera de casa. Dos goles y una asistencia. Cuando te sustituyen en el 80, la afición RIVAL se pone en pie y te aplaude. El estadio entero.",
                    "deportivo", "informativo", "rare", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d039,
                    "Aplaudir de vuelta con respeto",
                    "Un gesto de respeto mutuo que trasciende la rivalidad. Pero tu propia afición te critica en redes: 'Demasiado amable con el enemigo.'",
                    0, 0, 0, 0, 0, 0, 7, 0, 0, 0, -1, 5, 0, 0);
            footballDataService.createOption(d039,
                    "Bajar la cabeza con humildad y retirarte",
                    "Te vas al banquillo sin mirar a la grada. El vídeo se viraliza igualmente. La humildad habla por ti. Pero algunos dicen que has despreciado el gesto de la grada.",
                    0, 0, 0, 0, 1, 0, 5, 0, 0, 0, 0, 3, 0, 0);

// D040 — Final de copa
            Event d040 = footballDataService.createEvent(
                    "Día de final de copa",
                    "Estadio lleno. Tu familia en la grada. Cámaras en cada esquina. El presidente va a entregar el trofeo. Tu madre no ha pegado ojo. Tú tampoco.",
                    "deportivo", "decision", "epic", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d040,
                    "Jugar como si fuera un partido más",
                    "Rutina, rutina, rutina. Mismo calentamiento, mismas botas. Juegas sólido pero sin chispa. El partido se te pasa sin hacer nada memorable. Ganas 1-0 con gol de un compañero.",
                    0, 0, 0, 0, 3, 0, 2, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d040,
                    "Dejarte llevar por la adrenalina del momento",
                    "Sales a cien. La adrenalina te potencia al principio pero te quema en la segunda parte. Marcas en el 30, te fundas en el 70. El míster te cambia. Ves el final desde el banquillo.",
                    1, 0, 2, -1, -1, 0, 4, 0, 0, 0, 3, 2, 0, 0);
            footballDataService.createOption(d040,
                    "Empezar cauto y crecer con el partido",
                    "Los primeros 30 minutos estudias al rival. En el 40 apareces. En el 60 dominas. En el 80 metes el gol que cierra el partido. La gestión perfecta de una final.",
                    0, 1, 1, 0, 1, 1, 5, 0, 2, 1, 2, 2, 0, 0);

// D041 — Récord de goles
            Event d041 = footballDataService.createEvent(
                    "A un gol del récord histórico",
                    "34 goles en la temporada. El récord del club son 35 de hace 40 años. Quedan tres partidos. La leyenda del club te manda un mensaje: 'Rómpelo, chaval.'",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(d041,
                    "Ir a por el récord sin obsesionarte",
                    "Juegas tu fútbol. En el penúltimo partido, llega. 35. En el último, 36. Récord. Tu nombre en la historia. Pero la temporada acaba y sientes un vacío: ¿ahora qué?",
                    0, 0, 2, 0, 1, 0, 6, 0, 0, 0, 4, 3, 0, 2);
            footballDataService.createOption(d041,
                    "Intentar batirlo cuanto antes",
                    "Sales pensando solo en marcar. Cada tiro va a portería. Tres partidos forzando. Llegas al último con 34 goles. El récord se te escapa por un gol. La temporada acaba con sabor agridulce.",
                    0, 0, 1, 0, -1, 0, 1, 0, -1, -1, 1, 1, 0, 0);
            footballDataService.createOption(d041,
                    "No pensar en el récord y jugar para el equipo",
                    "Le dices a la prensa: 'Me da igual el récord. Quiero que el equipo acabe bien.' Mentira: te importa mucho. Pero juegas para el grupo y al final marcas en el último partido. 35. Empate con la leyenda. Él te llama: 'Bien hecho, chaval.'",
                    0, 0, 0, 0, 0, 1, 4, 0, 1, 2, 2, 2, 0, 1);

// D042 — Partido benéfico
            Event d042 = footballDataService.createEvent(
                    "Te invitan a un partido benéfico",
                    "Evento solidario. Estrellas retiradas, jugadores en activo y famosos. Tu representante: 'Buen escaparate y haces algo bueno.'",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d042,
                    "Ir y disfrutar del evento",
                    "Risas, regates de broma y fotos con famosos. Buena imagen. Pero al volver al club, el míster te dice: 'Oye, que teníamos sesión de vídeo ayer y no viniste.'",
                    0, 0, 0, 0, 0, 0, 3, 0, -1, 0, 2, 3, 1, 0);
            footballDataService.createOption(d042,
                    "Declinar educadamente, necesitas descansar",
                    "Tu cuerpo necesita reposo. Donas dinero en tu nombre. La fundación lo agradece pero la prensa titula: 'No asistió al benéfico.' Siempre hay alguien que te juzga.",
                    0, 0, 0, 1, 0, 0, -1, -100, 0, 0, 0, -1, 0, 0);
            footballDataService.createOption(d042,
                    "Ir pero solo un rato, sin jugar",
                    "Apareces, te haces fotos, saludas y te vas. Cumples sin comprometer tu físico. Pero ni te diviertes del todo ni descansas del todo.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0);

// D043 — Te dan el brazalete
            Event d043 = footballDataService.createEvent(
                    "El capitán se lesiona: te toca a ti",
                    "Minuto 25. El capitán cae y tiene que salir. El míster grita: '¡Ponte el brazalete!' Te lo lanza desde la camilla.",
                    "deportivo", "informativo", "uncommon", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(d043,
                    "Asumir el rol y liderar al equipo",
                    "Empiezas a hablar, a pedir, a organizar. El equipo responde. Pero el segundo capitán te mira raro: ese brazalete le correspondía a él por jerarquía.",
                    0, 0, 0, 0, 0, 1, 2, 0, 2, -1, 1, 0, 0, 0);
            footballDataService.createOption(d043,
                    "Ponértelo pero seguir jugando como siempre",
                    "El brazalete no cambia tu juego. Eres el mismo. El míster esperaba algo más. Pero al final no has roto nada ni has pisado a nadie.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d043,
                    "Ofrecérselo al segundo capitán por respeto",
                    "Le dices al veterano: 'Esto es tuyo, capi.' Él te agradece el gesto y se lo pone. El vestuario ve que respetas la jerarquía. Pero el míster te había elegido a TI y no le gusta que le corrijan.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 3, 0, 0, 0, 0);

// D044 — Prórroga agónica
            Event d044 = footballDataService.createEvent(
                    "Empate al final. Hay prórroga.",
                    "90 minutos. Empate. 30 minutos más. Las piernas no dan para más. El míster te pregunta: '¿Puedes aguantar o te cambio?'",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d044,
                    "Decirle que puedes y quedarte",
                    "Las piernas gritan pero el corazón más. Aguantas los 30 minutos. En el 118 haces una jugada que decide el partido. Pero al día siguiente no puedes andar.",
                    0, 0, 1, -2, 2, 0, 3, 0, 2, 1, 1, 0, 0, 0);
            footballDataService.createOption(d044,
                    "Ser honesto y pedir el cambio",
                    "'No llego.' El míster asiente: 'Gracias por ser sincero.' Te cambia. Desde el banquillo ves cómo tu sustituto marca. La victoria se celebra sin ti en el campo.",
                    0, 0, 0, 1, 0, 0, 0, 0, 1, 0, -1, 0, 0, 0);
            footballDataService.createOption(d044,
                    "Quedarte pero pedir jugar en otra posición menos exigente",
                    "'Míster, me quedo pero ponme más atrás.' El entrenador acepta. Aguantas 30 minutos más a medio gas. No brillas, pero estás. A veces estar es suficiente.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// D045 — Asistencia de tacón
            Event d045 = footballDataService.createEvent(
                    "Se te ocurre una locura",
                    "Recibes de espaldas en el área. Un compañero desmarcado a tu derecha. Lo normal es girarte. Lo loco es hacerlo de tacón sin mirar.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d045,
                    "Tacón de fantasía",
                    "Toque de tacón perfecto. Gol del compañero. El estadio aplaude dos veces. Sale en todos los highlights. Pero el míster te dice después: 'Si eso sale mal, no vuelves a pisar el campo en un mes.'",
                    0, 2, 0, 0, 0, 2, 4, 0, -1, 1, 3, 3, 0, 0);
            footballDataService.createOption(d045,
                    "Girarte y hacer el pase normal",
                    "Control, giro, pase. Gol. Nadie recordará tu asistencia mañana. Pero el equipo suma tres puntos y el míster te guiña un ojo: 'Así se juega.'",
                    0, 0, 0, 0, 1, 0, 1, 0, 2, 1, 0, 0, 0, 0);

// D046 — Romper sequía del equipo
            Event d046 = footballDataService.createEvent(
                    "El equipo lleva 6 partidos sin ganar",
                    "Seis jornadas sin victoria. La moral por los suelos. El míster grita más y sonríe menos. Hoy, en casa contra un rival directo, es la última oportunidad.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d046,
                    "Dar un discurso en el vestuario antes de salir",
                    "Te levantas: 'Estamos muertos de miedo. Hoy se acaba.' Silencio. Uno a uno, se levantan. Pero el capitán te mira: ¿quién te ha dado a ti permiso para dar charlas? A veces el líder no necesita título.",
                    0, 0, 0, 0, 0, 1, 2, 0, 0, -1, 2, 0, 0, 0);
            footballDataService.createOption(d046,
                    "Liderar con el ejemplo en el campo",
                    "Primer duelo, tuyo. Primer sprint, tuyo. Primer disparo, tuyo. No hablas pero tu cuerpo grita. El equipo te sigue. Pero acabas reventado en el 70 y no llegas al final.",
                    1, 0, 1, -1, 0, 0, 2, 0, 1, 2, 1, 0, 0, 0);
            footballDataService.createOption(d046,
                    "Centrarte en lo tuyo y que cada uno haga su trabajo",
                    "No eres capitán ni líder nato. Haces lo que sabes: jugar bien. Si cada uno hace su parte, la victoria llega. Y llega: 1-0 con gol en el 85 de un compañero. No has sido el héroe, pero has puesto tu ladrillo.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0);

// D047 — Penalti fallado
            Event d047 = footballDataService.createEvent(
                    "Has fallado un penalti",
                    "El portero adivina el lado. Tu lanzamiento va flojo y central. Lo para sin despeinarse. El árbitro señala córner que no es. Tu equipo pierde por ese gol. El vestuario está en silencio.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d047,
                    "Pedir tirar el próximo penalti para redimirte",
                    "Le dices al míster que quieres el próximo. Él duda: '¿Seguro?' La confianza en ti mismo después de un fallo dice mucho. Pero si fallas el próximo, la cosa se pone fea de verdad.",
                    0, 0, 0, 0, 2, 0, -1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d047,
                    "Ceder los penaltis a otro durante un tiempo",
                    "Reconoces que no es tu momento. Otro compañero se encarga. La prensa dice que te has rendido. Pero en el fondo sabes que es lo más inteligente mientras recuperas la confianza.",
                    0, 0, 0, 0, 0, 0, -2, 0, 0, 1, 0, -1, 0, 0);
            footballDataService.createOption(d047,
                    "Entrenar penaltis a diario hasta que no pienses al tirar",
                    "Después de cada sesión, treinta penaltis. El portero suplente ya sabe dónde tiras. Pero tu técnica de lanzamiento mejora. El siguiente penalti en partido lo metes sin dudar.",
                    0, 0, 2, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0);

// D048 — Lesión seria
            Event d048 = footballDataService.createEvent(
                    "Te dan los resultados: rotura muscular",
                    "Las pruebas confirman lo que temías: rotura en el bíceps femoral. El médico te da plazos: 'Entre 6 y 10 semanas fuera, dependiendo de cómo respondas.' El calendario se desmorona. Te pierdes al menos 8 partidos.",
                    "deportivo", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d048,
                    "Seguir la recuperación al pie de la letra",
                    "Haces exactamente lo que dice el fisio. Ni un día más ni un día menos. Vuelves en la semana 8 al 90%. El míster te va metiendo poco a poco. Pero tu sustituto lleva 6 semanas jugando bien y no te devuelve el puesto fácil.",
                    0, 0, 0, 2, 1, 0, -2, 0, 0, 0, -2, 0, 0, 0);
            footballDataService.createOption(d048,
                    "Intentar forzar para volver antes",
                    "Doblas sesiones de recuperación. Estás en el campo en la semana 5. El míster te pone. Pero en el calentamiento del segundo partido, el músculo se resiente. Dos semanas más fuera.",
                    0, 0, 0, -1, 0, 0, -1, 0, -1, 0, 0, -1, 0, 0);
            footballDataService.createOption(d048,
                    "Aprovechar la lesión para trabajar la parte mental y técnica",
                    "Sesiones de vídeo, visualización, técnica individual con balón cuando el físico lo permite. Vuelves en la semana 9 diferente: mismo cuerpo pero mejor cabeza. La lesión te ha transformado.",
                    0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0);

// D049 — Te ofrecen ser capitán
            Event d049 = footballDataService.createEvent(
                    "El míster te ofrece ser el nuevo capitán",
                    "Despacho del míster. Cierra la puerta. 'El capitán se retira a final de temporada. He pensado mucho en quién debe llevar el brazalete. Y creo que eres tú. ¿Qué me dices?'",
                    "deportivo", "decision", "rare", "profesional", 24, 38, 1, 3, null
            );
            footballDataService.createOption(d049,
                    "Aceptar sin dudarlo",
                    "Aceptas con orgullo. El brazalete pesa pero te hace mejor jugador. Sin embargo, ahora eres el que da la cara en las derrotas, el que habla con el árbitro, el que gestiona egos. El fútbol ya no es solo jugar.",
                    0, 0, 0, 0, 0, 1, 3, 0, 3, 1, 1, 1, 0, 1);
            footballDataService.createOption(d049,
                    "Pedir tiempo para pensarlo",
                    "'Míster, es un honor pero necesito pensarlo.' Él asiente pero su cara dice que esperaba un sí inmediato. Al final aceptas, pero el impacto del momento se ha perdido.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d049,
                    "Sugerir a otro compañero que crees más preparado",
                    "'Míster, creo que [compañero] sería mejor capitán que yo.' El entrenador se sorprende. Respeta tu honestidad pero pierde confianza en tu ambición. El otro se convierte en capitán y tú te preguntas si has dejado pasar algo importante.",
                    0, 0, 0, 0, 0, 0, -1, 0, -1, 2, 0, 0, 0, 0);

// D050 — Último partido de la temporada
            Event d050 = footballDataService.createEvent(
                    "Último partido de la temporada",
                    "Ya no hay nada en juego. Ni ascenso, ni descenso, ni títulos. El estadio está medio vacío. El míster ha puesto a los suplentes y a los jóvenes. Y a ti. Es el último partido antes de las vacaciones.",
                    "deportivo", "decision", "common", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d050,
                    "Jugar al 100% como si fuera una final",
                    "No hay partidos sin importancia. Sales a competir. Marcas un gol y das una asistencia. Pero te lesionas levemente en el 70. Empiezas las vacaciones cojeando.",
                    0, 0, 1, -1, 0, 0, 2, 0, 2, 1, 1, 1, 0, 0);
            footballDataService.createOption(d050,
                    "Ir tranquilo y disfrutar sin arriesgar",
                    "Toques bonitos, algún regate de fantasía, risas con los compañeros. Es casi un partido amistoso. No destacas pero tampoco te expones a lesión. El verano empieza limpio.",
                    0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0);
            footballDataService.createOption(d050,
                    "Ayudar a los jóvenes que debutan hoy",
                    "Te pasas el partido buscando a los chavales que debutan. Les pasas, les colocas, les animas. El míster se acerca al final: 'Eso que has hecho hoy vale más que un hat-trick.' Los jóvenes te recordarán siempre.",
                    0, 0, 0, 0, 0, 1, 1, 0, 2, 3, 0, 0, 0, 0);

            // ===== EVENTOS DEPORTIVOS — TERCERA TANDA (D051-D100) =====

// D051 — Lesión grave
            Event d051 = footballDataService.createEvent(
                    "Crujido en la rodilla",
                    "Aceleración en el minuto 30. Sin contacto. Notas un chasquido. Te caes solo. Sabes lo que es antes de que llegue el médico. Rotura de ligamento cruzado. La camilla, el túnel, la resonancia: 'Seis meses.'",
                    "deportivo", "unico", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d051,
                    "Afrontarlo como un reto personal desde el día uno",
                    "Al día siguiente ya estás en el gimnasio. Seis meses de piscina, fisio y hierro. Vuelves más fuerte físicamente pero mentalmente agotado: la obsesión por volver te ha consumido y necesitas semanas para volver a disfrutar jugando.",
                    0, 0, 0, -3, 2, 0, -1, -200, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(d051,
                    "Tomarte las primeras semanas para asimilarlo antes de empezar",
                    "Dos semanas sin hacer nada. Sofá, series, tu familia. Cuando empiezas la recuperación, estás mentalmente limpio. Pero has perdido dos semanas de trabajo físico y la vuelta se alarga un mes más.",
                    0, 0, 0, -5, 1, 0, -2, -200, 0, 1, 0, 0, 0, 0);
            footballDataService.createOption(d051,
                    "Aprovechar los seis meses para estudiar el juego desde fuera",
                    "Vídeo, análisis táctico, sentarte con el cuerpo técnico. Sales de la lesión con un conocimiento del juego que no tenías. Pero tu cuerpo ha sufrido y la readaptación física cuesta más de lo esperado.",
                    0, 0, 0, -4, 0, 2, 0, -200, 1, 0, 0, 0, 0, 0);

// D052 — Volver de una lesión larga
            Event d052 = footballDataService.createEvent(
                    "El primer entrenamiento después de la lesión",
                    "Seis meses sin tocar un balón. Te atas las botas con manos temblorosas. Sales al campo. El primer sprint te da miedo. La rodilla aguanta. Al tercero, sonríes.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d052,
                    "Ir poco a poco, sin forzar nada",
                    "Tres semanas progresivas. Sin contacto. El míster: 'Cuando estés, estás.' Vuelves al 100% sin recaída. Pero tu sustituto lleva seis meses jugando bien y el puesto ya no es tuyo automáticamente.",
                    0, 0, 0, 2, 1, 0, 0, 0, 2, 0, -1, 0, 0, 0);
            footballDataService.createOption(d052,
                    "Pedir entrar en el grupo lo antes posible",
                    "Una semana y ya estás con el grupo. El fisio protesta. Juegas antes de lo previsto y marcas en tu primer partido de vuelta. Pero la rodilla se resiente un par de veces y el miedo no se va del todo.",
                    0, 0, 0, -1, -1, 0, 2, 0, 0, 1, 1, 1, 0, 0);
            footballDataService.createOption(d052,
                    "Combinar vuelta progresiva con trabajo mental",
                    "Sesiones con el psicólogo deportivo mientras entrenas suave. Tardas un mes más en volver pero cuando lo haces no tienes miedo. Ni una duda. El primer partido juegas como si no hubiera pasado nada.",
                    0, 0, 0, 1, 3, 0, 1, -100, 0, 0, 0, 0, 0, 0);
// D053 — Sustituido en el descanso
            Event d053 = footballDataService.createEvent(
                    "Te cambian en el descanso",
                    "El míster entra al vestuario, mira la pizarra y dice tu número: 'Sales. No me estás dando lo que necesito ahí.' Delante de todos. El sustituto ya se está poniendo las botas.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d053,
                    "Aceptarlo sin montar drama",
                    "Asientes, te sientas y ves la segunda parte desde el banquillo. Por dentro arde. El míster valora tu profesionalidad, pero ¿y si empieza a pensar que puedes tragarla siempre?",
                    0, 0, 0, 0, 1, 0, -1, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d053,
                    "Pedir explicaciones al míster en privado",
                    "Le esperas en el pasillo: '¿Qué he hecho mal?' Él te dice tres cosas concretas. Aprendes, pero ahora sabes exactamente lo que piensa de ti y no todo es bonito.",
                    0, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d053,
                    "Reventar el siguiente entrenamiento como venganza",
                    "El lunes llegas el primero y te vas el último. Cada ejercicio es personal. El sábado vuelves al once. Pero la motivación por venganza no dura para siempre y cuando se apaga, ¿qué queda?",
                    0, 1, 0, 1, -1, 0, 0, 0, 1, 0, 0, 0, 0, 0);

// D054 — Bronca pública del míster
            Event d054 = footballDataService.createEvent(
                    "Bronca pública del míster",
                    "En pleno entrenamiento, delante de todo el equipo, el míster para el ejercicio y te señala: '¿Qué estás haciendo? ¿Estás aquí o en la luna? Si no quieres entrenar, coge la puerta.'",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d054,
                    "Callarte y apretar los dientes el resto de la sesión",
                    "Tragas saliva y entrenas el doble. Al final, el míster se acerca: 'Así me gusta.' Como si nada. Has ganado su respeto pero tus compañeros te ven como alguien que no se defiende.",
                    0, 0, 0, 0, 1, 0, 0, 0, 2, -1, 0, 0, 0, 0);
            footballDataService.createOption(d054,
                    "Darle la razón y disculparte delante de todos",
                    "'Tienes razón, míster. Perdona.' Tema zanjado. Pero un compañero te dice en la ducha: 'Tío, no tienes por qué humillarte así delante de todos.'",
                    0, 0, 0, 0, 0, 0, -1, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(d054,
                    "Decirle que si tiene algo que decirte, lo haga en privado",
                    "'No me grites delante de todos, míster.' Silencio. Él se queda callado tres segundos eternos. Los compañeros flipan. Algunos te admiran por plantarte. Otros piensan que te acabas de cavar tu propia tumba.",
                    0, 0, 0, 0, 0, 0, 1, 0, -3, 2, 0, 0, 0, 0);
// D055 — Te dejan fuera de la convocatoria
            Event d055 = footballDataService.createEvent(
                    "No estás en la lista",
                    "Viernes por la tarde. Sale la convocatoria para el sábado. Tu nombre no está. No estás lesionado ni sancionado. Simplemente no estás. El míster no te ha dicho nada.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d055,
                    "Ir al despacho del míster y preguntar",
                    "'Míster, ¿puedo saber por qué?' Él: 'Decisión técnica. Entrena bien y estarás el próximo.' No dice nada útil, pero al menos sabe que te importa. El problema es que ahora también sabe que te ha dolido.",
                    0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d055,
                    "No decir nada y entrenar como un animal la semana siguiente",
                    "Si no hay explicación, te la buscas en el campo. Entrenas tan bien que dejarte fuera es injustificable. El sábado siguiente estás en la lista. Pero te queda la espina de no saber por qué te dejó fuera.",
                    0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d055,
                    "Comentarlo con un compañero de confianza",
                    "Le preguntas a un veterano: '¿Tú sabes algo?' Él te dice: 'Creo que el míster quiere ver a otro chaval. No es por ti.' Te queda más claro pero ahora el vestuario sabe que te ha afectado y eso te hace parecer vulnerable.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0);

// D056 — Tu suplente lo hace mejor que tú
            Event d056 = footballDataService.createEvent(
                    "Tu sustituto está en racha",
                    "Te has perdido tres partidos por molestias. Tu sustituto ha marcado cuatro goles. La grada corea su nombre. Cuando vuelves, el míster no te garantiza el puesto.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d056,
                    "Competir limpiamente por el puesto",
                    "No tienes nada contra él. Entrenas a tope y que el míster decida. Pero las semanas pasan y sigues en el banquillo. La competencia sana está bien en la teoría; en la práctica, te come por dentro.",
                    0, 0, 0, 0, 1, 0, -1, 0, 2, 1, 0, 0, 0, 0);
            footballDataService.createOption(d056,
                    "Hablar con el míster para recordarle lo que has hecho",
                    "'Míster, llevo dos años rindiendo. No me puedes sentar por tres partidos.' Él: 'Aquí juega el que mejor esté.' Te ha dejado claro las reglas. Y las reglas ahora no juegan a tu favor.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d056,
                    "Cambiar algo de tu juego para ofrecer lo que el otro no tiene",
                    "Él marca goles. Tú no vas a superarle en eso ahora mismo. Pero puedes ofrecer otra cosa: más defensa, más asociación, más sacrificio. Te reinventas. El míster nota que aportas cosas diferentes. Te pone en otro partido con otro rol.",
                    0, 0, 0, 0, 0, 2, 1, 0, 1, 0, 0, 0, 0, 0);

// D057 — Provocación de un rival famoso
            Event d057 = footballDataService.createEvent(
                    "El rival te busca las cosquillas",
                    "El mediocampista estrella del otro equipo no para de hablar. 'Eh, chaval, ¿tú de dónde has salido?' Cada vez que pasas cerca te suelta una. El árbitro no le dice nada. Los compañeros te miran.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d057,
                    "Responderle con un caño y una sonrisa",
                    "Le haces un caño delante de 50.000 personas. La grada grita 'OLÉ'. Él se calla. Pero en la siguiente jugada te mete una entrada por detrás que te deja el tobillo ardiendo. El árbitro ni la pita.",
                    0, 2, 0, -1, 1, 0, 3, 0, 0, 0, 3, 2, 0, 0);
            footballDataService.createOption(d057,
                    "Ignorarle completamente durante los 90 minutos",
                    "Ni le miras. Juega tu partido como si no existiera. Al final, él ha hablado 90 minutos y tú has jugado 90 minutos. Pero en la zona mixta la prensa te pregunta: '¿No te ha afectado?' Y solo por contestar, ya le has dado protagonismo.",
                    0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d057,
                    "Encararle y decirle cuatro cosas",
                    "Le plantas cara: 'A mí me hablas con el balón.' El árbitro os separa. Amarilla para los dos. Tus compañeros te palmean la espalda: 'Bien dicho.' Pero el míster te echa la bronca: 'Necesito once, no diez.'",
                    0, 0, 0, 0, -1, 0, 1, 0, -1, 2, 1, -1, 0, 0);

// D058 — Te ofrecen el dorsal 10
            Event d058 = footballDataService.createEvent(
                    "El diez está libre",
                    "El jugador que llevaba el 10 se ha marchado. El míster te llama: 'El diez no es un número cualquiera aquí. Tiene historia. ¿Lo quieres o prefieres quedarte con el tuyo?'",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(d058,
                    "Aceptar el 10 con todo lo que conlleva",
                    "Te pones el diez. La grada espera más, la prensa espera más, tú esperas más. Es un peso y un honor. Pero el anterior dueño del 10 era leyenda del club y los aficionados te comparan constantemente. Cada mal partido es 'no es digno del diez.'",
                    0, 0, 0, 0, -1, 0, 3, 0, 1, 0, 1, 1, 0, 1);
            footballDataService.createOption(d058,
                    "Quedarte con tu dorsal, que ya tiene su historia",
                    "'Míster, prefiero el mío.' El entrenador sonríe: 'Me gusta que no necesites un número.' Pero el 10 se lo dan a otro y cuando ese otro brilla con él, te preguntas si debiste cogerlo tú.",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0);
            footballDataService.createOption(d058,
                    "Pedir un par de días para pensarlo",
                    "'Míster, déjame pensarlo.' Él asiente. Consultas con tu familia, tu representante, tu mejor amigo. Al final coges el 10, pero la decisión ha llegado sin la emoción del primer momento. Lo que debía ser un honor se ha convertido en un trámite.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1, 0);
// D059 — Roja a un compañero, jugáis con 10
            Event d059 = footballDataService.createEvent(
                    "Roja a tu compañero. Jugáis con diez.",
                    "Minuto 55. Roja directa a tu compañero. Quedan 35 minutos con uno menos. El míster grita cambiando la táctica. Ahora hay que correr por dos.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d059,
                    "Dar un paso al frente y correr por dos",
                    "Te dejas la piel cubriendo el hueco. Acabas con calambres en las dos piernas y el empate. Pero al día siguiente no puedes entrenar y el míster te descarta para el próximo partido por precaución.",
                    1, 0, 0, -1, 1, 0, 2, 0, 1, 3, 1, 0, 0, 0);
            footballDataService.createOption(d059,
                    "Jugar inteligente y administrar fuerzas",
                    "Tocas, temporizas, enfrías el partido. No es bonito pero es efectivo. El míster te lo agradece. Pero la grada te grita 'corre más' sin entender que estás salvando el partido con la cabeza, no con las piernas.",
                    0, 0, 0, 0, 0, 2, 1, 0, 2, -1, -1, 0, 0, 0);
            footballDataService.createOption(d059,
                    "Intentar resolver el partido tú solo antes de que sea tarde",
                    "Con uno menos, la mejor defensa es atacar. Buscas el gol por tu cuenta. Un disparo al palo, un regate que acaba en falta. En el 88 la metes. Victoria con diez. Pero si no hubiera entrado, el míster te habría matado por no defender.",
                    0, 1, 2, 0, 0, 0, 4, 0, -1, 0, 3, 2, 0, 0);

// D060 — Gol que os salva del descenso
            Event d060 = footballDataService.createEvent(
                    "Si no ganas, bajáis",
                    "Última jornada. Si no ganáis, descenso. El míster pone una foto de la afición en el proyector: 'Por ellos.' Minuto 80. 0-0. Te llega un balón en la frontal.",
                    "deportivo", "decision", "epic", "profesional", 16, 38, 1, 2, null
            );
            footballDataService.createOption(d060,
                    "Disparar sin pensarlo",
                    "No hay tiempo para pensar. Disparo raso, esquina, red. Gol. Permanencia. El estadio estalla. Es el gol más importante de tu vida. Pero la presión de ser 'el salvador' te persigue: la temporada siguiente, cada vez que no marcas, te lo recuerdan.",
                    0, 0, 3, 0, 2, 0, 8, 100, 1, 2, 5, 3, 0, 3);
            footballDataService.createOption(d060,
                    "Buscar el pase al compañero mejor colocado",
                    "Le ves solo a la derecha. Pase perfecto. Marca. Permanencia. No sales en la portada, pero sin ese pase no había gol. El problema: tu compañero se lleva toda la gloria y en verano le fichan por su gol salvador. Tú te quedas.",
                    0, 0, 0, 0, 1, 2, 3, 100, 2, 2, 2, 1, 0, 1);
            footballDataService.createOption(d060,
                    "Aguantar el balón y buscar una jugada más clara",
                    "Controlas, proteges, esperas. Buscas la jugada perfecta. En el 87 filtras un pase al hueco, tu compañero la pierde. El partido acaba 0-0. Descenso. Quisiste ser perfeccionista cuando el momento pedía instinto.",
                    0, 0, 0, 0, 0, 0, -4, 0, -2, -2, -3, -2, 0, -1);

// D061 — Ascenso con tu equipo
            Event d061 = footballDataService.createEvent(
                    "¡Habéis subido!",
                    "Pitido final. El equipo ha ascendido. Jugadores llorando, cuerpo técnico abrazándose, la grada cantando. Has sido parte fundamental. El presidente baja al césped con champán.",
                    "deportivo", "unico", "epic", "profesional", 16, 38, 1, 2, null
            );
            footballDataService.createOption(d061,
                    "Celebrarlo con todo el club y la ciudad",
                    "Autobús descapotable, ayuntamiento, cena hasta las tantas. El club te adora. Pero la celebración dura tres días y llegas a la pretemporada sin haber descansado. El primer test físico es un desastre.",
                    0, 0, 0, -1, 0, 0, 6, 150, 1, 4, 5, 3, 0, 2);
            footballDataService.createOption(d061,
                    "Celebrar pero ya pensar en la siguiente temporada",
                    "Disfrutas la noche y al día siguiente ya piensas en que arriba es otro nivel. Algunos compañeros te dicen: 'Tío, relájate, que acabamos de subir.' Te ven como el que no sabe disfrutar.",
                    0, 0, 0, 0, 2, 0, 4, 150, 1, -1, 2, 1, 0, 1);
            footballDataService.createOption(d061,
                    "Celebrar y aprovechar para pedir una mejora de contrato",
                    "Llamas a tu representante esa misma noche: 'Es el momento de renegociar.' Tiene razón: el club está eufórico y aceptan subirte el sueldo. Pero el presidente lo recuerda: 'Este chaval pensó en dinero el día del ascenso.'",
                    0, 0, 0, 0, 0, 0, 3, 300, -2, 0, 1, 0, 2, 1);

// D062 — El equipo desciende
            Event d062 = footballDataService.createEvent(
                    "Habéis bajado",
                    "Pitido final. Descenso confirmado. El estadio se vacía en silencio. Algunos jugadores lloran. Otros se van al vestuario sin hablar. Tú te quedas sentado en la banda con la camiseta por encima de la cabeza.",
                    "deportivo", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d062,
                    "Dar la cara ante la afición que se queda",
                    "Vas a la grada donde quedan los más fieles y pides perdón con un gesto. Algunos insultan, otros aplauden por dar la cara. La imagen sale en todos los medios. Te conviertes en el símbolo de la derrota, para bien y para mal.",
                    0, 0, 0, 0, 1, 0, -2, 0, 0, 0, 3, 2, 0, 0);
            footballDataService.createOption(d062,
                    "Irte al vestuario y procesar el golpe en privado",
                    "La ducha más larga de tu vida. Teléfono en silencio tres días. La prensa te critica por no dar la cara. Pero necesitabas ese silencio para no romperte. Cuando vuelves, vuelves entero.",
                    0, 0, 0, 0, 0, 0, -3, 0, 0, 0, -2, -1, 0, 0);
            footballDataService.createOption(d062,
                    "Reunir al vestuario antes de irse y hablar",
                    "'Nos vamos a la mierda, pero nos vamos juntos. El año que viene subimos.' Algunos asienten, otros no te pueden ni mirar. Pero has plantado una semilla. Y en la pretemporada, los que estaban esa noche recuerdan tus palabras.",
                    0, 0, 0, 0, 0, 0, -2, 0, 1, 2, 0, 0, 0, 0);
// D063 — Tu primer clásico a nivel top
            Event d063 = footballDataService.createEvent(
                    "El partido del año",
                    "No es un partido cualquiera. Es EL partido. 500 millones de espectadores. El vestuario parece la antesala de una batalla. Túnel de vestuarios. Luces. Ruido. Pitido.",
                    "deportivo", "decision", "epic", "profesional", 22, 38, 3, 3, null
            );
            footballDataService.createOption(d063,
                    "Salir a disfrutar del escenario más grande del mundo",
                    "Has soñado con esto desde crío. Sales enchufado, buscas el balón, intentas brillar. Marcas un golazo que da la vuelta al mundo. Pero también pierdes dos balones que acaban en contraataque y tu míster te echa una bronca al descanso.",
                    0, 0, 2, 0, -1, 0, 5, 0, -1, 0, 4, 4, 0, 0);
            footballDataService.createOption(d063,
                    "Centrarte en tu trabajo y olvidar todo lo que rodea al partido",
                    "Auriculares hasta el último momento. No miras a la grada. Para ti es un partido más. Juegas sólido, sin errores, sin brillar. El míster encantado. Pero la prensa titula: 'Invisible en el partido del año.'",
                    0, 0, 0, 0, 2, 1, 2, 0, 2, 0, 0, -1, 0, 0);
            footballDataService.createOption(d063,
                    "Empezar cauto y subir la intensidad según avanza el partido",
                    "Los primeros 30 minutos estudias el partido. En el 40 apareces. En el 70 dominas. La gestión es perfecta. Pero un penalti en contra en el 88 arruina todo y la derrota borra tu actuación de las crónicas.",
                    0, 1, 0, 0, 1, 1, 3, 0, 1, 0, 1, 1, 0, 0);

// D064 — Semifinal de Champions
            Event d064 = footballDataService.createEvent(
                    "Semifinal de Champions League",
                    "Semis de Champions. Ida en tu campo. El himno suena diferente cuando solo quedan cuatro equipos. El rival es un monstruo. Pero tú también tienes lo tuyo.",
                    "deportivo", "decision", "epic", "profesional", 22, 38, 3, 3, null
            );
            footballDataService.createOption(d064,
                    "Jugar el partido de tu vida con todo",
                    "Sales con la determinación de alguien que sabe que esto no volverá. Rindes a un nivel que ni tú sabías que tenías. Pero tanta intensidad te pasa factura: un tirón muscular en el 75 te deja fuera de la vuelta.",
                    0, 1, 2, -2, 1, 0, 6, 0, 0, 1, 4, 4, 1, 0);
            footballDataService.createOption(d064,
                    "Respetar al rival y jugar con cabeza fría",
                    "Juegas compacto, inteligente, esperando tu momento. Cuando llega, no lo desperdicias. Un pase decisivo y un gol. Pero la prensa dice: 'Le falta ese punto de locura para los grandes escenarios.' Siempre quieren más.",
                    0, 0, 1, 0, 2, 2, 4, 0, 3, 1, 1, 1, 0, 0);
            footballDataService.createOption(d064,
                    "Liderar al equipo desde la voz y el ejemplo",
                    "Gritas, ordenas, animas. En cada córner en contra eres el primero en la barrera. En cada ataque señalas los desmarques. El equipo te sigue. Pero te olvidas de tu propio juego y no tocas un balón decisivo en 90 minutos.",
                    0, 0, 0, 0, 0, 1, 2, 0, 2, 3, 0, 0, 0, 0);

// D065 — Final de Champions
            Event d065 = footballDataService.createEvent(
                    "Final de Champions League",
                    "El día más grande de tu carrera. Final de la Champions. El estadio a reventar. Las cámaras del mundo apuntan al césped. El trofeo de las orejas grandes brilla bajo los focos. Esta noche, pase lo que pase, tu vida cambia.",
                    "deportivo", "decision", "legendary", "profesional", 22, 38, 3, 3, null
            );
            footballDataService.createOption(d065,
                    "Dejarte la vida en cada jugada",
                    "90 minutos al límite. Cada sprint el último, cada duelo una final. Marcas en el 72 con un disparo que sale del alma. Pero te rompes el gemelo celebrando y ves los últimos 18 minutos desde la camilla. Tu equipo aguanta. Campeones. Pero tú levantas la copa cojeando.",
                    0, 1, 2, -2, 2, 0, 8, 500, 0, 2, 5, 5, 2, 3);
            footballDataService.createOption(d065,
                    "Mantener la calma cuando todos pierden la cabeza",
                    "Mientras los demás están a 200 pulsaciones, tú juegas como en el parque. Tocas, filtras, esperas. En el 85 asistes el gol decisivo con un pase que solo alguien tranquilo puede dar. Campeones. Pero la prensa titula el nombre del goleador, no el tuyo.",
                    0, 1, 0, 0, 3, 2, 6, 500, 3, 2, 3, 3, 2, 2);
            footballDataService.createOption(d065,
                    "Liderar al grupo desde el primer minuto hasta el último",
                    "Gritas, ordenas, animas, peleas. Eres el primero en cada acción defensiva y el último en rendirse. No marcas ni asistes pero sin ti el equipo no habría aguantado. Campeones. El míster te abraza: 'Esto ha sido tuyo.' La prensa no lo ve así.",
                    0, 0, 0, 0, 1, 1, 5, 500, 3, 3, 2, 1, 0, 2);

// D066 — Partido de clasificación mundial
            Event d066 = footballDataService.createEvent(
                    "Eliminatoria del Mundial",
                    "Tu selección se juega la clasificación para el Mundial. Estadio lleno. Un país entero viendo la tele. El seleccionador te ha puesto de titular. Si ganáis, Mundial. Si perdéis, cuatro años esperando.",
                    "deportivo", "decision", "epic", "profesional", 22, 38, 2, 3, null
            );
            footballDataService.createOption(d066,
                    "Asumir la responsabilidad de liderar",
                    "Pides el balón, generas peligro, arrastras al equipo. Metes el gol que clasifica al país. Pero el seleccionador te dice después: 'Has jugado solo. Esto es una selección, no tu club.' La gloria tiene un precio en el grupo.",
                    0, 0, 2, 0, 0, 0, 6, 0, -1, -1, 4, 3, 0, 0);
            footballDataService.createOption(d066,
                    "Jugar disciplinado dentro del plan del seleccionador",
                    "Ejecutas al milímetro lo que te piden. El equipo funciona como un reloj. Ganáis 1-0 con gol de un compañero. El seleccionador te felicita. Pero nadie recuerda tu nombre cuando repasan la clasificación. Fuiste perfecto e invisible.",
                    0, 0, 0, 0, 1, 0, 3, 0, 3, 1, 0, 1, 0, 0);
            footballDataService.createOption(d066,
                    "Empezar disciplinado y soltar el freno si el partido lo pide",
                    "Los primeros 60 minutos dentro del plan. En el 65, con 0-0, empiezas a tomar riesgos. Regate, disparo al palo, centro que casi rematan. El seleccionador te grita que vuelvas a tu sitio. No le haces caso. En el 82 filtras el pase del gol. ¿Desobediencia o instinto?",
                    0, 1, 0, 0, 0, 1, 4, 0, -1, 0, 2, 2, 0, 0);

// D067 — Te expulsan injustamente
            Event d067 = footballDataService.createEvent(
                    "Roja injusta",
                    "Disputas un balón. Llegas primero. Contacto mínimo. El rival se tira como si le hubieran disparado. Roja directa. No es falta ni de broma. La repetición lo demuestra. Pero ya estás fuera.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d067,
                    "Irte sin montar el número y apelar después",
                    "Levantas las manos, niegas con la cabeza y te vas. En la sala de prensa, el vídeo habla por ti. La apelación será fácil. Pero tu equipo pierde el partido con diez y algunos te culpan: 'Si te hubieras quejado más, igual el árbitro rectifica.'",
                    0, 0, 0, 0, 2, 0, -1, 0, 0, -1, 0, 2, 0, 0);
            footballDataService.createOption(d067,
                    "Protestar al árbitro con todo lo que tienes",
                    "Te acercas al colegiado y le dices que es una vergüenza. Los compañeros te separan. La prensa graba todo. La apelación se complica por el informe de protesta. Pero el vestuario te ve como alguien que pelea por lo suyo.",
                    0, 0, 0, 0, -1, 0, -1, 0, -1, 2, 0, -2, 0, 0);
            footballDataService.createOption(d067,
                    "Irte pero señalar al VAR pidiendo que lo revisen",
                    "Haces el gesto del rectángulo pidiendo VAR. El árbitro te ignora. Te vas señalando la pantalla gigante donde se ve que no hay falta. La imagen se viraliza. La apelación la ganas pero el partido ya está perdido.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0);

// D068 — 100 partidos con el club
            Event d068 = footballDataService.createEvent(
                    "Cien partidos con esta camiseta",
                    "Antes del partido, el altavoz anuncia tus 100 partidos oficiales con el club. La grada aplaude. Te dan un cuadro conmemorativo. El capitán te abraza. Cien partidos son muchos domingos y mucho vestuario compartido.",
                    "deportivo", "informativo", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(d068,
                    "Agradecer a la afición con un gesto sincero",
                    "Te acercas a la grada, te tocas el corazón y aplauds. Cien partidos significan algo. Pero el rival aprovecha tu emoción y marca en los primeros cinco minutos mientras tú aún tienes la cabeza en el homenaje.",
                    0, 0, 0, 0, -1, 0, 3, 0, 0, 0, 4, 1, 0, 1);
            footballDataService.createOption(d068,
                    "Centrarte en que el partido 101 sea tan bueno como el primero",
                    "El homenaje está bien, pero hay un partido que ganar. Sales enchufado y marcas. Pero tu representante te dice después: 'Chaval, podrías haber disfrutado el momento. No todo es rendir. A veces hay que parar y agradecer.'",
                    0, 0, 1, 0, 1, 0, 1, 0, 1, 0, -1, 0, -1, 0);
            footballDataService.createOption(d068,
                    "Dedicar el partido a los que ya no están en el club",
                    "Antes de saltar al campo, piensas en los compañeros que se fueron, el míster que te dio la primera oportunidad, el fisio que te recuperó de aquella lesión. Juegas con una tranquilidad especial. No brillas, pero disfrutas cada minuto de una forma diferente.",
                    0, 0, 0, 0, 1, 0, 2, 0, 0, 1, 2, 0, 0, 0);

// D069 — Calientas pero no entras
            Event d069 = footballDataService.createEvent(
                    "Quince minutos calentando para nada",
                    "El míster te manda calentar en el 60. Corres por la banda quince minutos. La grada te aplaude. Vuelves. El míster te mira: '...no, siéntate. Vamos bien así.' Te sientas con el chándal húmedo y la frustración seca.",
                    "deportivo", "decision", "common", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d069,
                    "Tragártelo sin decir nada",
                    "Te sientas y aceptas. El míster valora que no montes un número. Pero por dentro se acumula algo que un día va a explotar. ¿Cuántas veces puedes tragártela antes de reventar?",
                    0, 0, 0, 0, 1, 0, -1, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d069,
                    "Preguntarle al míster después del partido qué ha pasado",
                    "'Míster, me has mandado calentar y luego no me has metido. ¿Qué ha cambiado?' Él: 'El partido iba bien y no quería tocarlo.' Una respuesta que tiene sentido pero no consuela. Al menos sabes que no es personal.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d069,
                    "Usar la frustración para reventar el siguiente entrenamiento",
                    "El lunes llegas con la rabia del que calentó para nada. Entrenas como un poseso. El míster lo nota. Pero la frustración te hace ser demasiado agresivo en un ejercicio y le pegas un patadón a un compañero sin querer. Mal gesto.",
                    0, 1, 0, 0, -1, 0, 0, 0, 0, -1, 0, 0, 0, 0);

// D070 — Pase de gol que tu compañero falla
            Event d070 = footballDataService.createEvent(
                    "El pase perfecto que no acaba en gol",
                    "Filtras un pase entre líneas que deja a tu compañero solo ante el portero. Se la tiene que meter. Y la tira fuera. Él te mira con cara de disculpa.",
                    "deportivo", "decision", "common", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d070,
                    "Animarle y decirle que la próxima va dentro",
                    "Le chocas la mano: 'Tranquilo, la siguiente la metes.' Dos jugadas después marca y te busca para celebrar. Pero después del partido piensas: si la hubiera metido la primera, tendrías una asistencia más en las estadísticas. Y las estadísticas cuentan.",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0);
            footballDataService.createOption(d070,
                    "Llevarte las manos a la cabeza sin poder evitarlo",
                    "El gesto es involuntario pero él lo ve. La relación se enfría. Sin embargo, los dos sabéis que la próxima vez te la va a devolver con intereses. A veces la tensión saca lo mejor.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0);
            footballDataService.createOption(d070,
                    "No reaccionar y volver a tu posición en silencio",
                    "Ni le animas ni le echas la bronca. Simplemente vuelves. Él no sabe qué piensas. Eso le incomoda más que un grito. Pero tú tampoco sabes si has hecho lo correcto: ¿era frialdad profesional o indiferencia?",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, -1, 0, 0, 0, 0);

// D071 — El portero rival te provoca
            Event d071 = footballDataService.createEvent(
                    "El portero te tiene calado",
                    "Cada vez que te acercas al área, el portero rival te habla: 'Venga, tira, que te la paro. Siempre tiras al mismo lado.' Lo peor es que tiene razón: te ha parado dos disparos.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d071,
                    "Cambiar tu disparo y pillarle a contrapié",
                    "La tercera vez, fintas y la colocas al otro palo. Gol. Le miras. Él no dice nada. Pero al cambiar tu forma de tirar has perdido potencia y el gol ha entrado justo. Si el portero no se hubiera movido, la paraba.",
                    0, 0, 2, 0, 1, 0, 3, 0, 0, 0, 2, 1, 0, 0);
            footballDataService.createOption(d071,
                    "Ignorarle y seguir con tu juego sin cambiar nada",
                    "No vas a cambiar tu forma de jugar por un portero bocazas. Sigues tirando al mismo lado. A la cuarta, entra por potencia pura. Pero el portero sonríe: 'Te he adivinado todas. Una tenía que entrar.' Y tiene razón.",
                    0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d071,
                    "Dejar de tirar y buscar a los compañeros",
                    "Si te tiene calado, no le des la oportunidad. Te centras en asistir. Dos pases de gol que acaban en la red. El portero no puede parar lo que no tiras. Pero la prensa titula: 'No se atreve a tirar.'",
                    0, 0, -1, 0, 0, 2, 2, 0, 1, 1, 0, -1, 0, 0);

// D072 — Estrella ficha por tu equipo
            Event d072 = footballDataService.createEvent(
                    "Ha fichado una estrella para tu posición",
                    "El club anuncia un fichaje bomba: un jugador internacional, tres veces mejor jugador de su liga, que juega en TU posición. La prensa pregunta si eso te convierte en suplente. Tu representante te llama preocupado.",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(d072,
                    "Verlo como una oportunidad de aprender",
                    "Tenerlo al lado en cada entrenamiento te hace mejorar. En tres meses has subido de nivel. Pero también llevas tres meses en el banquillo porque él es mejor y el míster lo sabe. Aprendes mucho, juegas poco.",
                    0, 1, 0, 0, 1, 1, -1, 0, 2, 1, -1, 0, 0, 0);
            footballDataService.createOption(d072,
                    "Competir por el puesto sin complejos",
                    "Sales a demostrar que no sobras. El míster te ve entrenar con hambre y te da minutos. Pero la estrella se enfada por no jugar siempre y la tensión entre los dos genera bandos en el vestuario.",
                    0, 0, 0, 0, 0, 0, 1, 0, 1, -2, 0, 1, 1, 0);
            footballDataService.createOption(d072,
                    "Hablar con tu representante sobre salir cedido",
                    "'Si no voy a jugar, prefiero irme a un sitio donde sea titular.' Tu representante mueve hilos. Encuentras equipo. Pero el club te ve como alguien que huye de la competencia en vez de afrontarla.",
                    0, 0, 0, 0, 0, 0, -1, 0, -2, 0, 0, 0, 2, 0);

// D073 — Gol número 100 de tu carrera
            Event d073 = footballDataService.createEvent(
                    "Gol número cien",
                    "El narrador lo grita: '¡GOL NÚMERO CIEN EN SU CARRERA!' El marcador lo confirma. Tu centésimo gol. Una cifra que te sitúa entre los elegidos. El estadio aplaude más el número que el gol en sí.",
                    "deportivo", "informativo", "rare", "profesional", 22, 38, 1, 3, null
            );
            footballDataService.createOption(d073,
                    "Señalar al banquillo dedicándolo al cuerpo técnico",
                    "Corres al banquillo y abrazas al míster, al segundo, al preparador. El gesto no pasa desapercibido. Pero tu compañero que te ha dado el pase te mira desde el otro lado del campo esperando un abrazo que no llega.",
                    0, 0, 0, 0, 0, 0, 3, 0, 3, -1, 1, 2, 0, 0);
            footballDataService.createOption(d073,
                    "Celebrarlo señalando a las cuatro gradas",
                    "Giras sobre ti mismo señalando a todo el estadio. Los cien son de todos. La imagen es icónica. Pero un periodista escribe: 'Celebración ensayada. Se nota que lo tenía preparado.' No puedes ganar con todo el mundo.",
                    0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 3, 1, 0, 1);
            footballDataService.createOption(d073,
                    "Celebración contenida, como si fuera uno más",
                    "Puño apretado, mirada al cielo, tres segundos. Y a seguir. El centenario es un número, no un destino. Pero tu representante te llama después: 'Chaval, era el momento de hacer algo grande. Has desperdiciado una oportunidad de marca.'",
                    0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, -1, 0);

// D074 — Te ofrecen ser capitán permanente
            Event d074 = footballDataService.createEvent(
                    "El brazalete, esta vez en serio",
                    "El capitán ha dejado el club. El míster te cita: 'He hablado con el vestuario. Quieren que seas tú el capitán. No es temporal. Es el brazalete hasta que tú digas basta.'",
                    "deportivo", "decision", "epic", "profesional", 22, 38, 1, 3, null
            );
            footballDataService.createOption(d074,
                    "Aceptar con orgullo y responsabilidad",
                    "A partir de hoy tu voz pesa más. Y tus actos también. Pero el peso del brazalete cambia tu forma de jugar: piensas más en el equipo que en ti y tus números individuales bajan. Eres mejor líder pero peor jugador en las estadísticas.",
                    0, 0, -1, 0, 0, 2, 4, 0, 3, 3, 2, 1, 0, 2);
            footballDataService.createOption(d074,
                    "Rechazarlo, prefieres liderar sin brazalete",
                    "'Míster, puedo ayudar más sin esa presión.' Él lo respeta. Pero el vestuario te votó y has rechazado su confianza. El que se lo ponga al final te mirará raro cada vez que levantes la voz en el vestuario: '¿No era que no querías mandar?'",
                    0, 0, 0, 0, 1, 0, -1, 0, -1, -2, 0, 0, 0, 0);
            footballDataService.createOption(d074,
                    "Aceptarlo pero dejar claro que será un liderazgo compartido",
                    "'Lo acepto, pero quiero que haya tres o cuatro voces en el vestuario, no solo la mía.' El míster asiente. Creas un grupo de capitanes. Funciona, pero las decisiones se ralentizan y nadie sabe quién manda de verdad cuando hay que decidir rápido.",
                    0, 0, 0, 0, 0, 1, 2, 0, 1, 1, 0, 0, 0, 0);

// D075 — Partido a puerta cerrada
            Event d075 = footballDataService.createEvent(
                    "Partido sin público",
                    "Sanción al club. Partido a puerta cerrada. El estadio vacío. Cada pelotazo retumba como un eco. No hay grada, no hay cánticos. Solo fútbol puro y un silencio ensordecedor.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d075,
                    "Jugar con la misma intensidad de siempre",
                    "La grada esté llena o vacía, tú compites. Corres, peleas, celebras. Aunque nadie te oiga. El míster te señala al acabar: 'Esto es profesionalidad.' Pero tanta intensidad sin público te deja vacío: el fútbol sin emoción cansa más.",
                    0, 0, 0, -1, 2, 0, 1, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(d075,
                    "Aprovechar el silencio para trabajar la comunicación",
                    "Sin ruido de grada, tus compañeros te oyen. Pasas el partido hablando, organizando, pidiendo. El equipo funciona como una orquesta. Pero tanta voz te agota y en la segunda parte tu juego baja porque has gastado energía mandando.",
                    0, 0, 0, 0, 0, 2, 0, 0, 1, 2, 0, 0, 0, 0);
            footballDataService.createOption(d075,
                    "Reconocer que sin público no es lo mismo",
                    "Marcas un gol y el silencio posterior es raro. Te falta la adrenalina. Juegas correcto pero sin chispa. Después del partido le dices a un compañero: 'Esto no es fútbol, es un entrenamiento con árbitro.' Un periodista lo escucha y lo publica. El club te regaña.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, -2, 0, 0);

            // D076 — El rival celebra en tu cara
            Event d076 = footballDataService.createEvent(
                    "Gol rival y celebración en tu cara",
                    "El delantero rival marca y viene corriendo hacia ti. Se pone de rodillas a dos metros, se señala el nombre y grita mirándote. El árbitro no hace nada. La grada rival enloquece.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d076,
                    "Guardar la rabia y marcar tú después",
                    "Canalizas la rabia. Quince minutos después empatas celebrando mirándole a él. Justicia poética. Pero la imagen de los dos provocándose sale en todos los medios y la liga os sanciona a ambos con una multa.",
                    0, 0, 1, 0, 1, 0, 3, -50, 0, 0, 2, -1, 0, 0);
            footballDataService.createOption(d076,
                    "Pasar de la provocación completamente",
                    "Le miras un segundo y te das la vuelta. No merece tu energía. La prensa te elogia por tu clase. Pero tus compañeros esperaban que reaccionaras y alguno murmura: '¿Ni eso le enfada? Parece que le da igual.'",
                    0, 0, 0, 0, 2, 0, 0, 0, 1, -1, 0, 2, 0, 0);
            footballDataService.createOption(d076,
                    "Acercarte y susurrarle algo al oído sin que las cámaras lo capten",
                    "Te acercas con calma y le dices algo que solo él escucha. Su sonrisa desaparece. Nadie sabe qué le has dicho. Él no vuelve a mirarte en todo el partido. Pero si algún día lo cuenta, tú lo negarás.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0);

// D077 — Partido de pretemporada contra un grande
            Event d077 = footballDataService.createEvent(
                    "Pretemporada contra un grande",
                    "Amistoso de pretemporada contra el {topClub}. No vale para nada en la clasificación, pero vale para todo en tu cabeza. Es la primera vez que pisas el campo con esos jugadores. Los que salen por la tele, a tres metros de ti.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 2, null
            );
            footballDataService.createOption(d077,
                    "Jugar sin complejos y plantarles cara",
                    "Les plantas cara desde el minuto uno. Uno de sus centrales te dice: 'No está mal, chaval.' Pero forzar tanto en un amistoso te deja con una sobrecarga que te hace perderte el primer partido oficial de liga.",
                    0, 0, 1, -1, 1, 0, 3, 0, 0, 0, 0, 2, 0, 0);
            footballDataService.createOption(d077,
                    "Aprender observando cómo se mueven y entrenan",
                    "Más que jugar, estudias. Cómo se desmarcan, cómo pisan el balón. Tres cosas que nunca habías visto de cerca. Pero el míster te dice después: 'Has estado tímido. ¿Te ha dado miedo la camiseta?'",
                    0, 1, 0, 0, 0, 2, 0, 0, -1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d077,
                    "Buscar al crack del equipo rival y pedirle cambiar camisetas",
                    "Al acabar te acercas a su estrella: '¿Me cambias la camiseta?' Él asiente. La cuelgas en tu habitación. Motivación pura cada mañana. Pero en el vestuario un compañero comenta: 'Parece un fan, no un rival.'",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, -1, 0, 0, 0, 0);

// D078 — Gol que clasifica para Champions
            Event d078 = footballDataService.createEvent(
                    "Si marcas, Champions",
                    "Última jornada. Si ganáis, Champions por primera vez en la historia del club. Minuto 70. 0-0. Contraataque. El balón te llega en el punto de penalti. El portero sale. Tú o él.",
                    "deportivo", "decision", "epic", "profesional", 22, 38, 2, 3, null
            );
            footballDataService.createOption(d078,
                    "Definir con clase, picándola por encima del portero",
                    "Globito suave. El balón vuela en cámara lenta. Entra. CHAMPIONS. Has hecho historia. Pero la picadita ha sido tan arriesgada que si el portero no se tira, la para con la cara. Has tenido una suerte que no mereces. O sí.",
                    0, 0, 3, 0, 1, 0, 8, 300, 0, 2, 5, 4, 1, 3);
            footballDataService.createOption(d078,
                    "Disparar fuerte a la esquina sin pensar",
                    "Cañonazo raso al primer palo. Red. Ni tú sabes cómo ha entrado. Champions. Pero en la repetición se ve que el disparo iba al centro y el portero la ha desviado a su propia red. Gol en propia meta, no tuyo. Las estadísticas no te lo dan. La historia sí.",
                    0, 0, 1, 0, 0, 0, 6, 300, 0, 1, 4, 3, 0, 2);
            footballDataService.createOption(d078,
                    "Regatear al portero y poner el balón en la red vacía",
                    "Encaras al portero, le dejas sentado con un recorte y empujas a puerta vacía. Champions. El gol es limpio, indiscutible, tuyo. Pero el recorte ha tardado un segundo extra y un defensa casi te la quita en la línea. Te ha rozado la bota. Un centímetro más y se acaba el sueño.",
                    0, 1, 2, 0, 2, 0, 7, 300, 1, 2, 5, 5, 1, 3);

// D079 — Cesión a otro equipo
            Event d079 = footballDataService.createEvent(
                    "Te ceden a otro club",
                    "El director deportivo te llama: 'Creemos en ti, pero aquí no vas a tener minutos. Hemos llegado a un acuerdo con el {club} para cederte una temporada. Allí jugarás cada semana. Cuando vuelvas serás mejor.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 26, 1, 3, null
            );
            footballDataService.createOption(d079,
                    "Aceptar la cesión y buscar minutos",
                    "Nuevo vestuario, nueva ciudad, nuevo míster. Cada sábado juegas. Y eso no tiene precio. Pero a mitad de temporada el club de cesión quiere ficharte y tu club de origen no quiere venderte. Te quedas en tierra de nadie.",
                    0, 1, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 1, 0, "interes_cesion");
            footballDataService.createOption(d079,
                    "Rechazarla y quedarte a pelear por tu sitio",
                    "Le dices que no. Prefieres luchar desde el banquillo. El director lo respeta pero te avisa: 'No sé si vas a jugar mucho.' En noviembre llevas tres partidos jugados. La decisión era valiente. ¿Era inteligente?",
                    0, 0, 0, 0, 1, 0, -1, 0, 0, 1, 0, 0, 0, 1);
            footballDataService.createOption(d079,
                    "Aceptar pero poner condiciones: quieres un plan de desarrollo",
                    "'Voy, pero quiero saber qué rol voy a tener y cómo me van a desarrollar.' El club cedente acepta y te da un plan. Pero el míster del equipo receptor se molesta: 'Aquí no vienen a mandar los cedidos.'",
                    0, 0, 0, 0, 0, 1, 0, 0, -1, 0, 0, 0, 1, 0);


// D080 — Triple asistencia en un partido
            Event d080 = footballDataService.createEvent(
                    "Tres asistencias en un partido",
                    "No has marcado, pero tres compañeros han celebrado un gol gracias a ti. Tres pases de gol en un solo partido. El comentarista dice: 'No necesita marcar para ser el mejor del campo.'",
                    "deportivo", "informativo", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d080,
                    "Celebrar cada gol como si fuera tuyo",
                    "Corres a abrazar al goleador las tres veces. Los tres goles llevan tu firma. Pero al día siguiente la prensa titula los nombres de los goleadores, no el tuyo. Las asistencias no venden portadas.",
                    0, 0, 0, 0, 0, 2, 3, 0, 1, 3, 1, 0, 0, 0);
            footballDataService.createOption(d080,
                    "Buscar tu propio gol para completar la noche perfecta",
                    "Tres asistencias están bien, pero quieres el gol. Empiezas a tirar en vez de pasar. No marcas y un compañero que estaba solo te grita: '¡Pásala!' La noche perfecta se queda a medias por la avaricia.",
                    0, 0, 1, 0, 0, -1, 1, 0, -1, -2, 0, 0, 0, 0);
            footballDataService.createOption(d080,
                    "Seguir asistiendo sin cambiar nada hasta el final",
                    "Si el juego pide pasar, pasas. Si pide tirar, tiras. No fuerzas nada. Acaba el partido con tres asistencias y sin gol. Tu representante te dice: 'Tres asistencias no te suben el precio. Tres goles sí.' ¿Tiene razón o es un mercenario?",
                    0, 0, 0, 0, 1, 1, 2, 0, 0, 2, 0, 1, -1, 0);

// D081 — Entrevista a pie de campo
            Event d081 = footballDataService.createEvent(
                    "Micro en la cara nada más pitar el final",
                    "El partido acaba. Un periodista te intercepta con un micro: 'Gran partido. ¿Cómo te has sentido?' Estás sudando, jadeando y tienes tres segundos para pensar qué decir delante de dos millones de espectadores.",
                    "deportivo", "decision", "common", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d081,
                    "Dar declaraciones correctas y políticamente seguras",
                    "Hablas del equipo, del míster, de la afición. No metes la pata. Tu representante: 'Perfecto.' Pero el clip no se comparte, nadie lo recuerda. Has sido correcto e irrelevante.",
                    0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 2, 0, 0);
            footballDataService.createOption(d081,
                    "Ser espontáneo y decir lo que sientes sin filtro",
                    "Dices lo que piensas. El clip se hace viral. Tu nombre suena en todas las tertulias. Pero una frase sacada de contexto genera polémica y el míster te dice al día siguiente: 'La próxima vez, piensa antes de hablar.'",
                    0, 0, 0, 0, 0, 0, 3, 0, -1, 0, 1, -1, 0, 0);
            footballDataService.createOption(d081,
                    "Esquivar la entrevista y irte al vestuario",
                    "'Perdona, ahora no puedo.' Le dejas con el micro en la mano. En el vestuario respiras tranquilo. Pero la prensa titula: 'Se niega a hablar tras el partido.' Y el gabinete de comunicación del club te echa una bronca: 'Hay obligaciones contractuales con la tele.'",
                    0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, -2, 0, 0);


// D082 — Gol fantasma
            Event d082 = footballDataService.createEvent(
                    "¿Ha entrado o no?",
                    "Tu disparo pega en el larguero, bota en la línea y sale repelido. El linier no señala gol. El VAR revisa. Imágenes no concluyentes. Tres minutos después: no hay gol. Tú juras que ha entrado.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d082,
                    "Aceptar la decisión y seguir jugando",
                    "Respiras y te colocas. El fútbol es injusto a veces. Pero al acabar el partido, que pierdes 0-1, te comes la cabeza: si hubieras protestado más, ¿el árbitro habría dudado? ¿Aceptar fue conformismo?",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0);
            footballDataService.createOption(d082,
                    "Protestar al árbitro con energía pero sin pasarte",
                    "Te acercas al árbitro gesticulando pero sin insultarle. Él no cambia la decisión pero te escucha. Al acabar se acerca: 'He visto las imágenes otra vez. Probablemente tenías razón.' Eso no cambia el resultado, pero al menos sabes que te oyeron.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(d082,
                    "Usar la injusticia como gasolina para el resto del partido",
                    "El gol fantasma te enciende. Sales a matar. En el 80 metes un golazo tan claro que ni el VAR puede discutirlo. Justicia por tu cuenta. Pero has jugado tan al límite que has visto amarilla por una entrada innecesaria.",
                    0, 0, 2, 0, 0, 0, 2, 0, -1, 0, 1, 0, 0, 0);

// D083 — El utillero veterano te da un consejo
            Event d083 = footballDataService.createEvent(
                    "El utillero que lo ha visto todo",
                    "El utillero del club, 30 años en el cargo y tres generaciones de jugadores vistas, te para en el pasillo: 'Chaval, un consejo: no te creas mejor de lo que eres, pero tampoco peor. He visto a muchos quemarse por ambas cosas.'",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d083,
                    "Agradecerle el consejo y tomártelo en serio",
                    "Le das la mano y le dices gracias. La frase se te graba. La recuerdas en los momentos difíciles. Pero también en los buenos, y a veces te frena cuando deberías soltar el freno y arriesgar más.",
                    0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0);
            footballDataService.createOption(d083,
                    "Escucharlo por educación pero seguir a tu aire",
                    "Asientes, sonríes y sigues. Es el utillero, no el míster. Pero tres meses después, cuando te crees mejor de lo que eres y el míster te sienta, la frase te vuelve como un eco. Tenía razón.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0);
            footballDataService.createOption(d083,
                    "Sentarte con él y preguntarle qué ha visto en los que triunfaron",
                    "'¿Qué tenían los que duraron?' Él se sienta y te cuenta historias de jugadores que ya no recuerda nadie y de otros que se convirtieron en leyendas. Pierdes veinte minutos de recuperación post-entreno pero ganas una perspectiva que no te da ningún vídeo táctico.",
                    0, 0, 0, -1, 1, 1, 0, 0, 0, 2, 0, 0, 0, 0);


// D084 — Sales lesionado y el campo rival te aplaude
            Event d084 = footballDataService.createEvent(
                    "Sales lesionado y el campo rival te aplaude",
                    "Caes mal tras un salto. Molestia seria en el tobillo. No puedes seguir. Mientras te retiras por la banda, la afición RIVAL se pone de pie y aplaude. No te conocen, pero respetan que has dejado todo.",
                    "deportivo", "informativo", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d084,
                    "Aplaudir de vuelta con emoción contenida",
                    "Levantas la mano y aplauds. Se te humedecen los ojos. Un momento que trasciende la rivalidad. Pero cuando ves la repetición de la jugada, te das cuenta de que la lesión ha sido por una imprudencia tuya. El aplauso era por tu entrega, no por tu inteligencia.",
                    0, 0, 0, -1, 1, 0, 4, 0, 0, 0, 2, 3, 0, 0);
            footballDataService.createOption(d084,
                    "Bajar la cabeza y retirarte con rabia por la lesión",
                    "No puedes disfrutar el aplauso porque la frustración es más grande. Te vas al vestuario sin mirar a nadie. Tu representante te llama: 'Chaval, la imagen de irte sin agradecer el aplauso no ha gustado.' A veces el dolor no deja espacio para la diplomacia.",
                    0, 0, 0, -2, 0, 0, 1, 0, 0, 0, -1, -1, 0, 0);
            footballDataService.createOption(d084,
                    "Quitarte la camiseta y lanzarla a la grada rival",
                    "Te quitas la camiseta y la lanzas a un niño de la grada rival. El estadio entero se pone de pie. La imagen da la vuelta al mundo. Pero el árbitro te saca amarilla por quitarte la camiseta. Si acumulas una más, te pierdes el próximo partido. Que ya te pierdes por la lesión, así que da igual. ¿O no?",
                    0, 0, 0, -1, 0, 0, 5, 0, 0, 0, 4, 5, 0, 0);

// D085 — Balón dividido con tu amigo
            Event d085 = footballDataService.createEvent(
                    "Balón dividido con tu amigo",
                    "Balón suelto en el centro del campo. Tú vas a por él. El que viene por el otro lado es tu mejor amigo de la infancia, que juega en el rival. Os vais a encontrar. Uno de los dos se lleva el golpe.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d085,
                    "Ir a por el balón con todo, es tu trabajo",
                    "La amistad es fuera del campo. Llegas primero. Él se lleva el golpe. Al final del partido os abrazáis. Pero tu madre te dice después: 'Le has hecho daño a tu amigo.' Y eso pesa, aunque no debería.",
                    0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d085,
                    "Aflojar un poco en el último momento",
                    "No puedes hacerle daño. Dejas que llegue él. El míster grita desde la banda. Tu amigo te mira un segundo y asiente. Pero el balón que él recupera acaba en un contragolpe y marcan. 0-1. Tu amistad le ha costado un gol a tu equipo.",
                    0, 0, 0, 0, 0, 0, -1, 0, -2, 0, -1, 0, 0, 0);
            footballDataService.createOption(d085,
                    "Ir al balón fuerte pero limpio, que gane el mejor",
                    "Vas con intensidad pero sin mala leche. Chocáis, los dos caéis, y el balón sale fuera. Empate justo. Os levantáis juntos. Él te dice: 'Buen choque, cabrón.' El árbitro pita falta a favor tuyo. Tu amigo protesta. Tú sabes que no ha sido falta.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);



// D086 — Primer partido en un estadio mítico
            Event d086 = footballDataService.createEvent(
                    "Pisas un estadio mítico por primera vez",
                    "San Siro, Anfield, el Bernabéu, Maracaná... el nombre da igual. Lo que importa es la sensación cuando pisas el césped y miras las gradas. La historia del fútbol está en cada ladrillo.",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d086,
                    "Pararte un segundo a absorber el momento",
                    "Tres segundos mirando las gradas antes del calentamiento. Te llenas de motivación. Pero esos tres segundos se convierten en treinta y el míster te grita: '¡Calienta, que esto no es una excursión!' Llegas al partido con el calentamiento a medias.",
                    0, 0, 0, -1, 1, 0, 2, 0, -1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d086,
                    "Tratarlo como un campo más y centrarte en ganar",
                    "Es un campo de fútbol con portería y césped. Sales concentrado y juegas bien. Pero al acabar el partido te das cuenta de que no has disfrutado ni un segundo del lugar donde estabas. Y ya no volverás aquí hasta la próxima temporada. Si vuelves.",
                    0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(d086,
                    "Sacarte una foto en el túnel y subirla a redes antes del partido",
                    "La foto se hace viral: tú sonriendo en el túnel del estadio. Tu marca personal sube. Pero el míster ve la publicación y te dice: 'La cabeza en el partido, no en Instagram.' Algunos compañeros opinan lo mismo.",
                    0, 0, 0, 0, -1, 0, 2, 0, -1, -1, 1, 1, 1, 0);

// D087 — Marcaje personal asfixiante
            Event d087 = footballDataService.createEvent(
                    "El del 6 te sigue a todas partes",
                    "El defensa rival te marca como un perro guardián. A donde vas, va él. Llevas 40 minutos sin tocar un balón porque no te deja ni respirar.",
                    "deportivo", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d087,
                    "Moverte más para cansarle y crear espacios para otros",
                    "Si te sigue a ti, no sigue a nadie más. Cada carrera tuya abre un hueco. Inteligencia pura. Pero tú acabas el partido sin haber tocado un balón decisivo y la prensa titula: 'Desaparecido.' Nadie ve lo invisible.",
                    0, 0, 0, -1, 0, 2, 0, 0, 1, 1, 0, -1, 0, 0);
            footballDataService.createOption(d087,
                    "Pedir cambio de banda para escapar del marcaje",
                    "Le dices al míster que te cambie de lado. El defensa se queda perdido cinco minutos. Tocas tres balones y das una asistencia. Pero al volver a tu banda original, él ya te espera con más rabia. Y ahora la tiene personal.",
                    0, 1, 0, 0, 0, 1, 2, 0, 0, 0, 0, 1, 0, 0);
            footballDataService.createOption(d087,
                    "Encararle directamente y ganarle el duelo personal",
                    "Si quiere marcarte, que te marque. Te plantas cara a cara, le pides el balón y le haces un caño. La grada ruge. Pero él se calienta, te mete una entrada por detrás y acabas cojeando. Ha ganado él. O tú. Depende de cómo lo mires.",
                    0, 0, 1, -1, 0, 0, 2, 0, 0, 0, 2, 1, 0, 0);

            // D088 — Gol olímpico
            Event d088 = footballDataService.createEvent(
                    "Saque de esquina y la mandas directa a la red",
                    "Córner tuyo. Te acercas al banderín. Levantas la mano indicando algo al compañero... pero en realidad miras la portería. El portero está adelantado. Hay un hueco. La rosca perfecta podría entrar. ¿Te atreves?",
                    "deportivo", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(d088,
                    "Mandarla directa a portería con rosca",
                    "La pegas con el interior. Rosca perfecta. GOL OLÍMPICO. El estadio no se lo cree. Tú tampoco. Pero el míster te echa la bronca después: 'Teníamos una jugada ensayada y te la has saltado. Si no entra, te siento.' Ha entrado. Pero ¿y si no?",
                    0, 2, 3, 0, 0, 0, 6, 0, -1, 0, 5, 5, 1, 0);
            footballDataService.createOption(d088,
                    "Centrar al área como siempre",
                    "No es momento de loterías. Pones un centro al segundo palo. Tu compañero cabezea y marca. 1-0. El gol es suyo pero el córner era tuyo. El míster asiente: 'Así se ejecuta la jugada ensayada.' Correcto y olvidable.",
                    0, 0, 0, 0, 1, 0, 1, 0, 2, 1, 0, 0, 0, 0);
            footballDataService.createOption(d088,
                    "Hacer un amago de córner directo y centrar al segundo palo",
                    "Miras la portería un segundo, como si fueras a tirar directo. El portero retrocede asustado. Centras al segundo palo donde tu compañero está solo porque el portero ha dejado hueco. Gol. Inteligencia disfrazada de locura.",
                    0, 1, 0, 0, 0, 2, 2, 0, 1, 1, 1, 1, 0, 0);

// D089 — Último partido antes de la retirada
            Event d089 = footballDataService.createEvent(
                    "El último baile",
                    "Has decidido retirarte. Este es tu último partido. El estadio lo sabe. Cuando sales al campo, toda la grada se pone de pie. Aplausos dos minutos. El capitán del rival se acerca a darte la mano. Tu familia llora en la grada. Tú intentas no llorar. No lo consigues.",
                    "deportivo", "unico", "legendary", "profesional", 33, 42, 1, 3, null
            );
            footballDataService.createOption(d089,
                    "Jugar los 90 minutos como si fueras inmortal",
                    "No quieres que acabe. Cada minuto como si fuera el primero. En el 88 marcas un gol que cierra tu carrera de la forma más bonita posible. Cuando pita el final, besas el césped. Pero te has forzado tanto que mañana te dolerá todo. Y pasado también. Y ya no habrá más partidos para olvidar el dolor.",
                    0, 0, 1, -2, 3, 0, 10, 0, 2, 2, 5, 5, 2, 3);
            footballDataService.createOption(d089,
                    "Salir en el 80 para recibir la ovación caminando solo por la banda",
                    "Le pides al míster que te cambie en el 80. Caminas solo por la banda, aplaudiendo a la grada que te ha acompañado toda tu carrera. El estadio canta tu nombre. Perfecto. Pero desde el banquillo, ves los últimos diez minutos sin poder jugarlos. Y esos diez minutos te persiguen: ¿por qué no seguí hasta el final?",
                    0, 0, 0, 0, 1, 0, 10, 0, 3, 3, 5, 5, 2, 3);
            footballDataService.createOption(d089,
                    "Dejar que el partido fluya sin hacer nada especial",
                    "No buscas el gol ni la jugada para el recuerdo. Juegas como siempre. Tocas, te desplazas, ayudas. Porque así has jugado toda tu vida y no vas a cambiar el último día. El partido acaba 1-1 y nadie recuerda ninguna jugada tuya. Pero tú recuerdas cada segundo.",
                    0, 0, 0, 0, 2, 0, 8, 0, 2, 2, 3, 3, 0, 2);

// D090 — Asistencia en el último segundo que gana un título
            Event d090 = footballDataService.createEvent(
                    "Último segundo. Un pase para la historia.",
                    "Final de copa. Empate. Minuto 93. Tienes el balón en la frontal. Tres defensas delante. Ves un hueco imposible. Tu compañero está en el límite del fuera de juego. Ahora o nunca.",
                    "deportivo", "decision", "epic", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(d090,
                    "Meter el pase imposible",
                    "El balón viaja entre las piernas de dos defensas. Tu compañero define. GOOOOL. Título. Tú caes de rodillas. Esa asistencia vale una copa. Pero la prensa titula su nombre, no el tuyo. Él levanta el trofeo como héroe. Tú sabes la verdad, pero el mundo no.",
                    0, 1, 0, 0, 1, 3, 6, 200, 1, 2, 3, 2, 1, 2);
            footballDataService.createOption(d090,
                    "Tirar tú a portería para ser el héroe",
                    "No hay tiempo para pensar en pases. Disparo. Rebote en un defensa. Corner. Penaltis. Los ganas. Eres campeón, pero siempre te quedará la duda: si hubieras metido ese pase, ¿habría sido más bonito? ¿O más arriesgado?",
                    0, 0, 1, 0, 0, 0, 3, 200, 0, -1, 2, 1, 0, 1);
            footballDataService.createOption(d090,
                    "Proteger el balón y esperar a que alguien se desmarque mejor",
                    "No ves claro ni el pase ni el tiro. Proteges el balón cinco segundos. Pita el final. Penaltis. El equipo los gana. Eres campeón, pero no has hecho nada decisivo. El título sabe bien. La sensación de no haberlo intentado, no tanto.",
                    0, 0, 0, 0, 1, 0, 2, 200, 0, 0, 1, 0, 0, 0);

// D091 — Ganar el Balón de Oro
            Event d091 = footballDataService.createEvent(
                    "Tu nombre en el Balón de Oro",
                    "Gala en París. Traje, alfombra roja, cámaras del mundo entero. Leen los finalistas. Tu nombre suena. Abren el sobre. Tu nombre otra vez. Has ganado el Balón de Oro. El trofeo dorado brilla mientras subes al escenario con las piernas temblando.",
                    "deportivo", "unico", "legendary", "profesional", 24, 36, 3, 3, null
            );
            footballDataService.createOption(d091,
                    "Dar un discurso emotivo agradeciendo a todos",
                    "Nombras a tu padre, a tu madre, al míster de la cantera, al utillero. Lloras sin vergüenza. El discurso se hace viral. Pero un periodista escribe: '¿Será capaz de volver a ganarlo o ha sido un año irrepetible?' La presión del Balón de Oro empieza al día siguiente de ganarlo.",
                    0, 0, 0, 0, -1, 0, 10, 500, 2, 2, 5, 5, 3, 3);
            footballDataService.createOption(d091,
                    "Ser breve, elegante y dejar que el trofeo hable",
                    "'Gracias a todos. Esto es un sueño.' Tres frases. Una reverencia. Te bajas con el trofeo. La prensa dice: 'Frío incluso al ganar el Balón de Oro.' Tú sabes que por dentro estabas temblando. Pero el mundo solo ve lo que muestras.",
                    0, 0, 0, 0, 2, 0, 9, 500, 1, 0, 3, 2, 3, 2);
            footballDataService.createOption(d091,
                    "Dedicar el premio a alguien que no puede estar ahí",
                    "Levantas el trofeo y dices un nombre. Alguien que te apoyó y ya no está. La sala se emociona. Tu imagen se humaniza ante el mundo. Pero has convertido un momento de gloria en un momento de tristeza. Y no sabes si esa persona habría querido que la nombraras ahí.",
                    0, 0, 0, 0, 1, 0, 10, 500, 2, 1, 4, 4, 1, 3);

// D092 — Ganar la Bota de Oro
            Event d092 = footballDataService.createEvent(
                    "Máximo goleador de Europa",
                    "La temporada ha terminado y los números hablan: eres el máximo goleador de todas las ligas europeas. La Bota de Oro es tuya. Tus goles han callado bocas una a una.",
                    "deportivo", "unico", "epic", "profesional", 22, 36, 2, 3, null
            );
            footballDataService.createOption(d092,
                    "Dedicarlo a los que confiaron cuando nadie lo hacía",
                    "En rueda de prensa: 'Esto es para los que estaban cuando nadie venía a verme.' Tu primer entrenador te manda un mensaje: 'Sabía que llegarías.' Pero un compañero que ha dado muchas asistencias este año comenta: 'Los goles no se meten solos.' Y tiene razón.",
                    0, 0, 0, 0, 0, 0, 7, 300, 1, -1, 3, 4, 1, 2);
            footballDataService.createOption(d092,
                    "Tomarlo como el primer paso hacia algo más grande",
                    "La Bota está bien. Pero quieres el Balón, la Champions, el Mundial. Esto es el principio. Tu representante encantado: 'Así se piensa.' Pero tu familia nota que ya no disfrutas los logros. Cada premio es solo un escalón hacia el siguiente. ¿Cuándo paras a mirar atrás?",
                    0, 0, 0, 0, -1, 0, 6, 300, 1, 0, 2, 2, 2, 0);
            footballDataService.createOption(d092,
                    "Compartir el mérito con todo el equipo públicamente",
                    "'Sin mis compañeros no meto ni la mitad.' La frase es bonita y verdad. El vestuario te adora. Pero los clubes que te quieren fichar leen la entrevista y piensan: '¿Es tan bueno o depende del equipo?' Tu precio de mercado no sube tanto como debería.",
                    0, 0, 0, 0, 0, 0, 5, 200, 2, 3, 2, 2, -1, 1);

// D093 — Partido del Mundial
            Event d093 = footballDataService.createEvent(
                    "Juegas un partido del Mundial",
                    "Has soñado con esto desde los cinco años. Un Mundial. Tu selección. El himno con la mano en el pecho. Miles de compatriotas en la grada. Millones por televisión. Todo lo que has hecho te ha llevado a este césped.",
                    "deportivo", "decision", "legendary", "profesional", 22, 38, 2, 3, null
            );
            footballDataService.createOption(d093,
                    "Jugar el partido de tu vida con todo",
                    "Sales y juegas como nunca. Cada pase perfecto, cada carrera al espacio correcto. El seleccionador te abraza al sustituirte: 'Eres un crack.' Pero tanta intensidad en un partido te deja vacío para el siguiente. ¿Podrás repetirlo en cuartos?",
                    0, 1, 1, -1, 2, 0, 7, 0, 1, 1, 4, 4, 0, 0);
            footballDataService.createOption(d093,
                    "Controlar los nervios y ejecutar lo que el seleccionador pide",
                    "No es momento de héroe. Haces lo que piden al milímetro. El equipo funciona. Ganáis. Pero nadie habla de ti en las crónicas. Tu país celebra a los goleadores. Tú has sido perfecto e invisible. Como siempre que juegas bien sin marcar.",
                    0, 0, 0, 0, 2, 1, 4, 0, 3, 2, 1, 1, 0, 0);
            footballDataService.createOption(d093,
                    "Empezar contenido y soltar el freno si el partido lo pide",
                    "Los primeros 30 minutos disciplinado. Cuando el partido se abre, tú te abres con él. En el 55 marcas un gol que le da la vuelta al marcador. El país entero grita tu nombre. Pero el seleccionador te mira: 'Te he dicho que te quedaras atrás.' ¿Desobediencia o genialidad? Depende del resultado.",
                    0, 0, 2, 0, 0, 0, 6, 0, -1, 0, 3, 3, 0, 0);

            // D094 — Gol que gana un Mundial
            Event d094 = footballDataService.createEvent(
                    "El gol de tu vida. En la final del Mundial.",
                    "Final del Mundial. Empate. Prórroga. Minuto 118. Recibes un pase en la frontal. Recortas. Dos defensas superados. El portero sale. Disparas. Entra. El mundo se para. Has marcado el gol que le da el Mundial a tu país.",
                    "deportivo", "unico", "legendary", "profesional", 24, 38, 3, 3, null
            );
            footballDataService.createOption(d094,
                    "Correr sin saber a dónde, con los compañeros encima",
                    "No sabes dónde corres. Todo el equipo encima tuyo. Tu padre llora en algún rincón del mundo. Tu nombre es inmortal. Pero al día siguiente la vida sigue y todo lo que hagas a partir de ahora se comparará con este momento. ¿Cómo superas el mejor gol de la historia?",
                    0, 0, 0, 0, -1, 0, 10, 1000, 2, 3, 5, 5, 3, 3);
            footballDataService.createOption(d094,
                    "Caer de rodillas y mirar al cielo llorando",
                    "De rodillas. Mirando arriba. Llorando. No hay celebración más honesta. Has llegado a lo más alto. Pero la imagen se convierte en un meme, en un póster, en una camiseta. Tu momento más íntimo se convierte en producto. Y tú no puedes hacer nada para evitarlo.",
                    0, 0, 0, 0, 2, 0, 10, 1000, 3, 2, 5, 5, 3, 3);
            footballDataService.createOption(d094,
                    "Ir corriendo a la grada donde está tu familia",
                    "Saltas la valla publicitaria, llegas a la grada y abrazas a tu madre. Ella no puede hablar. Tu padre te agarra la cara con las dos manos. Tus hermanos lloran. El momento es perfecto y real. Pero la FIFA te sanciona con una multa por saltar al público. Y la imagen que da la vuelta al mundo es la del abrazo, no la del gol.",
                    0, 0, 0, 0, 0, 0, 10, 800, 1, 1, 5, 5, 2, 3);

            // ===== EVENTOS DE FICHAJE  =====
            // Categoría: fichaje | Fase: profesional

// F001 — Primer interés
            Event f001 = footballDataService.createEvent(
                    "Tu representante te llama",
                    "Suena el teléfono después del entrenamiento. Tu representante va directo: 'Hay un club que ha preguntado por ti. Todavía nada concreto, pero el interés es real. ¿Cómo lo ves?'",
                    "fichaje", "decision", "common", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(f001,
                    "Quiero saber quién es y qué ofrecen",
                    "Tu representante te da el nombre. No es un grande pero es un paso adelante. La rueda empieza a girar. Pero un compañero te ve hablando por teléfono con cara seria y el rumor se extiende en el vestuario: 'Se quiere ir.'",
                    0, 0, 0, 0, 0, 0, 1, 0, -1, -1, 0, 0, 2, 0);
            footballDataService.createOption(f001,
                    "De momento centrarme en mi club",
                    "'Diles que de momento no hay movimiento.' Cuelgas. La oferta queda en el aire. Pero tu representante te dice semanas después: 'Han fichado a otro. Esa puerta se ha cerrado.' ¿Era la oportunidad que dejaste pasar?",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, -1, 0);
            footballDataService.createOption(f001,
                    "Preguntar qué papel tendría allí antes de decidir nada",
                    "'Antes de decir nada, quiero saber qué pintan para mí.' Tu representante asiente. Tarda una semana en conseguir respuesta. En esa semana los nervios te afectan en el campo y el míster te pregunta: '¿Estás bien?'",
                    0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 2, 0);

// F002 — Ojeador te sigue
            Event f002 = footballDataService.createEvent(
                    "Alguien te está observando",
                    "Después del partido, un tipo con carpeta y chaqueta de club habla con tu representante en la grada. Tres minutos. Tu representante baja: 'Llevamos varios partidos siguiéndote. Es del {club}.'",
                    "fichaje", "decision", "common", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(f002,
                    "Preguntar qué les parece tu rendimiento",
                    "'¿Les gusta lo que ven?' Tu representante: 'Si no les gustara, no seguirían viniendo.' Te motiva, pero el siguiente partido juegas pensando en impresionar al ojeador y te sale el peor partido de la temporada. La ley de Murphy.",
                    0, 0, 0, 0, -1, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f002,
                    "No darle importancia y seguir a lo tuyo",
                    "Levantas los hombros: 'Que miren.' Sigues sin cambiar nada. El ojeador viene tres partidos más y en dos de ellos rindes bien. Pero en el tercero tienes un mal día y el ojeador deja de venir. ¿Deberías haber dado más de ti sabiendo que miraban?",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(f002,
                    "Contárselo al míster para que sepa que hay interés",
                    "Le dices al entrenador: 'Míster, hay un club que me está siguiendo.' Él te mira: 'Bien. Eso significa que estás haciendo las cosas bien. Pero no quiero que te distraigas.' A partir de ahí te exige más en los entrenos. No sabes si es bueno o malo.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0);

// F003 — El interés es real
            Event f003 = footballDataService.createEvent(
                    "La cosa se pone seria",
                    "Tu representante te cita para un café: 'El interés del {club} es real. No hay oferta aún, pero quieren informes, vídeos, todo. ¿Cómo lo gestionamos?'",
                    "fichaje", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(f003,
                    "Que sigan observándome, no tengo prisa",
                    "Paciencia. Si el interés es real, la oferta llegará. Pero tu representante te dice: 'En este negocio, el que espera demasiado a veces se queda sentado. Hay otros jugadores de tu perfil que están dispuestos a moverse ya.'",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(f003,
                    "Diles que contacten con mi club ya",
                    "'Si quieren, que den el paso.' Las cosas se aceleran. Pero tu club se entera de que has movido ficha y el director deportivo te mira diferente en los pasillos. Ya no eres del todo suyo.",
                    0, 0, 0, 0, 0, 0, 1, 0, -2, -1, 0, 0, 2, 0);
            footballDataService.createOption(f003,
                    "Pedir más información antes de decidir nada",
                    "'Necesito saber liga, rol, proyecto.' Tu representante investiga. Tarda dos semanas. En esas dos semanas otro jugador ha dicho que sí y el club ya no tiene hueco. O sí. No lo sabes hasta que te llaman.",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0);

// F004 — Han preguntado tu precio
            Event f004 = footballDataService.createEvent(
                    "Han preguntado tu precio",
                    "Tu representante te llama con voz seria: 'El {club} ha preguntado cuánto costaría sacarte de aquí. Esto ya no es un rumor. Esto es una negociación.'",
                    "fichaje", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(f004,
                    "Escuchar la propuesta y valorarla",
                    "La cifra no está mal. Tu cabeza empieza a hacer cálculos. Pero mientras calculas, entrenas peor. El míster lo nota: 'Tienes la cabeza en otro sitio.' Y tiene razón.",
                    0, 0, 0, 0, -1, 0, 1, 0, -1, 0, 0, 0, 2, 0);
            footballDataService.createOption(f004,
                    "Decir que no estás en venta",
                    "'Diles que no.' Tu representante suspira. Tu directiva respira. Pero tres meses después el club que preguntó ha fichado a un crack y tú sigues en el mismo sitio. ¿Era tu tren y lo dejaste pasar?",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, -2, 1);
            footballDataService.createOption(f004,
                    "Que hablen los clubes entre ellos, tú a lo tuyo",
                    "'Yo no me meto en cifras.' Tu representante gestiona. Tú entrenas. Pero la negociación se alarga y cada semana te preguntas si hoy será el día que te digan 'te vas' o 'te quedas'. La incertidumbre es lo peor.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);

// F005 — Oferta oficial
            Event f005 = footballDataService.createEvent(
                    "Ha llegado una oferta por ti",
                    "El director deportivo te para en el pasillo: 'Ha llegado oferta oficial del {club}. No te voy a mentir, es una cifra importante. La decisión es tuya. ¿Qué quieres hacer?'",
                    "fichaje", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(f005,
                    "Quiero saber los detalles antes de decidir",
                    "El director te dice la cifra. Es más de lo que esperabas. Tu mente se divide entre ilusión y miedo. Pero mientras piensas, tu capitán se entera y te dice: 'Si te vas, que sea porque quieres, no por dinero.' Eso te remueve.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, -1, 0, 1, 2, 0);
            footballDataService.createOption(f005,
                    "Rechazarla, mi sitio es aquí",
                    "'Mi sitio es aquí.' El director asiente. La directiva encantada. Pero tu representante te llama furioso: 'Me has hecho quedar mal. No me consultas, me usas de recadero.' La relación con tu agente se resiente.",
                    0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, -3, 2);
            footballDataService.createOption(f005,
                    "Pedir unos días para pensarlo",
                    "'No digo que sí ni que no. Necesito tiempo.' El director: 'Tienes 48 horas.' Dos días en los que no duermes, no entrenas bien, y tu familia se divide: tu padre dice que te vayas, tu madre que te quedes.",
                    0, 0, 0, 0, -1, 0, 1, 0, -1, 0, 0, 0, 1, 0);

// F006 — Oferta baja pero con cariño
            Event f006 = footballDataService.createEvent(
                    "La oferta no es para tirar cohetes",
                    "Tu representante se sienta contigo: 'La oferta no es especialmente alta, pero el {club} te quiere de verdad. El entrenador ha pedido tu fichaje personalmente. ¿Eso cuenta para algo?'",
                    "fichaje", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(f006,
                    "Intentar negociar una mejora",
                    "'Si me quieren de verdad, que lo demuestren con el contrato.' Tu representante vuelve a llamar. Consigue un 15% más, pero el entrenador que te pidió se entera de que has negociado duro y piensa: 'A este le importa más el dinero que el proyecto.'",
                    0, 0, 0, 0, 0, 0, 0, 50, -1, 0, 0, 0, 1, 0);
            footballDataService.createOption(f006,
                    "Aceptarla tal cual, el proyecto deportivo importa más",
                    "No todo es dinero. Un entrenador que te pide vale más que cien mil euros de diferencia. Firmas. Llegas y el míster te abraza: 'Bienvenido, te he elegido yo.' Pero tres meses después al míster lo echan y el nuevo no te conoce de nada.",
                    0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0);
            footballDataService.createOption(f006,
                    "Rechazarla y esperar algo mejor",
                    "'Si no mejoran, no me muevo.' La oferta se cae. Tu representante te dice: 'Puede que aparezca otra. O puede que la temporada que viene nadie pregunte.' Vives los siguientes meses entre la confianza y la duda.",
                    0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, -1, 0);

// F007 — Rol de rotación
            Event f007 = footballDataService.createEvent(
                    "No serías titular",
                    "Tu representante te lo suelta sin anestesia: 'El {club} te quiere, pero no te garantizan titularidad. Irías como rotación. Más dinero, menos minutos. ¿Te compensa?'",
                    "fichaje", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(f007,
                    "Me interesa, ya me ganaré el puesto",
                    "Confías en ti. Llegas como rotación y en seis meses quieres ser titular. Pero el titular es un internacional con 200 partidos. Ganarle el puesto no es confianza, es una guerra de desgaste que puede durar años.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f007,
                    "Quiero ser titular o no me muevo",
                    "'Si no juego, no voy.' La negociación se rompe. Pero tu club actual, al enterarse de que rechazas ofertas por querer jugar, te valora más. El míster empieza a ponerte más minutos.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, -1, 0);
            footballDataService.createOption(f007,
                    "Pedir garantías de minutos por contrato",
                    "'Quiero un mínimo de partidos por temporada en el contrato.' Tu representante negocia. El club acepta a regañadientes. Pero un contrato con cláusula de minutos te convierte en un jugador incómodo para el míster: 'Tengo que ponerte por obligación, no por mérito.'",
                    0, 0, 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 2, 0);

// F008 — Papel importante desde el primer día
            Event f008 = footballDataService.createEvent(
                    "Te quieren como pieza clave",
                    "Tu representante te llama con tono diferente: 'El {club} no te quiere para completar plantilla. Te quiere como pieza importante. Titularidad garantizada y proyecto deportivo serio.'",
                    "fichaje", "decision", "rare", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(f008,
                    "Quiero ir, me convence el proyecto",
                    "Un club que te pone en el centro. Eso no pasa todos los días. Aceptas. Llegas motivado. Pero 'pieza importante' también significa que si no rindes, la culpa es toda tuya. En tu club actual eras uno más. Aquí eres EL fichaje.",
                    0, 0, 0, 0, -1, 0, 3, 0, 2, 0, 0, 1, 1, 0);
            footballDataService.createOption(f008,
                    "Antes de decidir, quiero saber el salario",
                    "'El proyecto mola, ¿pero cuánto pagan?' El salario es bueno. Firmas. Pero el vestuario se entera de lo que cobras y un veterano que lleva cinco años y cobra menos te mira raro. El dinero compra talento, no vestuario.",
                    0, 0, 0, 0, 0, 0, 1, 100, 0, -2, 0, 0, 1, 0);
            footballDataService.createOption(f008,
                    "Pedir una reunión con el director deportivo para oírlo de su boca",
                    "Quieres verle la cara, no hablar por teléfono. Vuelas a la ciudad, te enseñan las instalaciones, cenas con el director. Te convences. Pero tu club actual se entera de la visita y te aparta del grupo: 'Si quieres irte, no entrenas con nosotros.'",
                    0, 0, 0, 0, 0, 1, 1, 0, -3, -2, 0, 0, 2, 0);

// F009 — Club de Champions
            Event f009 = footballDataService.createEvent(
                    "Un club de Champions pregunta por ti",
                    "Tu representante te llama y notas que le tiembla la voz: 'Siéntate. Ha preguntado por ti un club que juega Champions. No es broma. Van en serio.'",
                    "fichaje", "decision", "epic", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(f009,
                    "Quiero escucharles, esto puede ser el salto",
                    "Champions. Noches europeas. El himno. Es por lo que luchas desde los 16. Pero tu representante te advierte: 'Quieren un suplente de lujo. Jugarías Copa y Liga de vez en cuando, Champions en fase de grupos si va bien.' La Champions sí, pero desde el banquillo.",
                    0, 0, 0, 0, -1, 0, 2, 0, 0, 0, 0, 1, 3, 0);
            footballDataService.createOption(f009,
                    "No quiero moverme todavía, no estoy preparado",
                    "La Champions puede esperar. Mejor ir listo que llegar pronto y quemarte. Tu representante suspira: 'Estas oportunidades no aparecen dos veces.' Tiene razón. Pero tú también la tienes. ¿O no?",
                    0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, -2, 0);
            footballDataService.createOption(f009,
                    "Preguntar qué papel tendría exactamente",
                    "'Champions sí, ¿pero para ir al banquillo?' Tu representante negocia garantías. El club se lo piensa. Mientras esperan, fichan a otro. Tu prudencia te ha costado la oportunidad. O te ha salvado de un banquillo dorado.",
                    0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0);

// F010 — Interés de la Premier
            Event f010 = footballDataService.createEvent(
                    "Inglaterra te quiere",
                    "Tu representante suelta la bomba: 'Hay interés de un club de la Premier League. Otra liga, otro ritmo, otro país. Pero el dinero... ya sabes cómo pagan los ingleses.'",
                    "fichaje", "decision", "epic", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f010,
                    "Quiero conocer la oferta",
                    "La cifra te hace parpadear dos veces. La Premier no bromea con salarios. Pero irte a Inglaterra significa dejar a tu familia, tus amigos, tu idioma. La lluvia, la comida, la soledad de un país donde no conoces a nadie. ¿Compensa el dinero?",
                    0, 0, 0, 0, -1, 0, 2, 200, -1, -1, 0, 0, 2, 0);
            footballDataService.createOption(f010,
                    "Prefiero seguir en mi liga, no quiero cambiar de país",
                    "'Estoy bien aquí.' Tu representante respeta la decisión. Pero dos compañeros tuyos se van a la Premier esa misma ventana y un año después cobran el triple que tú. Las comparaciones son odiosas pero inevitables.",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, -1, 0);
            footballDataService.createOption(f010,
                    "Pedir ir de visita una semana antes de decidir",
                    "Vuelas a Inglaterra. Ves las instalaciones, la ciudad, el estadio. Todo impresiona. Pero cuando estás solo en el hotel por la noche, lejos de todo, te preguntas si podrías vivir así durante años. Vuelves sin haber decidido nada.",
                    0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0);

// F011 — Interés de la Bundesliga
            Event f011 = footballDataService.createEvent(
                    "Llamada desde Alemania",
                    "'Un club de la Bundesliga ha preguntado por ti. Alemania: buen fútbol, estadios a reventar, proyecto serio.' Tu representante parece entusiasmado.",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f011,
                    "Escuchar qué ofrecen",
                    "Alemania suena bien. Fútbol táctico, público fiel, clubes que apuestan por jóvenes. Pides más información. Pero tu familia no se lo toma bien: 'Alemania está muy lejos y hace mucho frío.' Las decisiones profesionales a veces chocan con las personales.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0);
            footballDataService.createOption(f011,
                    "No me veo en Alemania",
                    "'Paso. No es mi sitio.' Tu representante no insiste. Pero un año después ese club alemán llega a semis de Champions y tú piensas: 'Podría haber estado ahí.' O no. Nunca lo sabrás.",
                    0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(f011,
                    "Aceptar pero poner condiciones: contrato corto para probar",
                    "'Voy, pero solo dos años. Si no funciona, vuelvo.' El club acepta. Llegas con red de seguridad. Pero el vestuario sabe que tienes un pie fuera y nunca te adoptan del todo. Un cedido con contrato es una contradicción.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 1, 0);

// F012 — Interés de Ligue 1
            Event f012 = footballDataService.createEvent(
                    "Francia llama a tu puerta",
                    "'Hay interés de un club de la Ligue 1. No es el PSG, pero es un club serio con proyecto.' Tu representante te lo plantea como una opción real.",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f012,
                    "Escuchar la propuesta con interés",
                    "Francia tiene su encanto. Liga competitiva, buen nivel de vida. Pero cuando investigas, descubres que el club lleva tres entrenadores en dos años. 'Proyecto serio' y 'tres místers en dos años' no encajan. ¿Tu representante te está vendiendo la moto?",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, -1);
            footballDataService.createOption(f012,
                    "Quedarte donde estás, no es el momento",
                    "'Estoy cómodo aquí.' El interés se apaga. Pero la Ligue 1 ficha a un jugador de tu perfil que en seis meses triplica su valor y acaba en la Premier. Tú sigues con el mismo sueldo y la misma liga.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, -1, 0);
            footballDataService.createOption(f012,
                    "Pedir que negocien pero sin prisas",
                    "'Que hablen, pero yo no me muevo hasta que haya algo claro.' Tu representante gestiona. La negociación se alarga un mes. Tu rendimiento en el club baja porque cada llamada de tu agente te desconcentra.",
                    0, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, 1, 0);

// F013 — Club portugués
            Event f013 = footballDataService.createEvent(
                    "Apuesta de futuro desde Portugal",
                    "'Un club portugués te ve como apuesta de futuro. No buscan que seas titular ya, pero te ven potencial para ser pieza importante en dos años.' Tu representante te lo presenta como un movimiento a medio plazo.",
                    "fichaje", "decision", "uncommon", "profesional", 18, 26, 1, 3, null
            );
            footballDataService.createOption(f013,
                    "Aceptar el proyecto, pienso a largo plazo",
                    "Paciencia. Rol menor ahora con la promesa de ser protagonista después. Pero a los seis meses el club ficha a tres jugadores más y la 'pieza importante en dos años' se convierte en 'uno más de la plantilla'. Las promesas en el fútbol caducan rápido.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, -1, 0, 0, 0, 0);
            footballDataService.createOption(f013,
                    "Quiero minutos ya, no promesas de futuro",
                    "'Si no juego desde el primer día, no me interesa.' Tu representante transmite el mensaje. El club dice: 'Nos gusta que tenga ambición, pero no podemos garantizarle eso.' Punto muerto. Pero al menos sabes lo que vales para ellos.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(f013,
                    "Ir cedido un año para probar sin compromiso",
                    "'Voy cedido. Si funciona, me quedo. Si no, vuelvo.' El club acepta. Portugal te recibe bien. Juegas, mejoras, creces. Pero cuando vuelves a tu club, descubres que ya no cuentan contigo: 'Te fuiste, ahora hay otros.'",
                    0, 1, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 1, 0);

// F014 — Interés de Brasil
            Event f014 = footballDataService.createEvent(
                    "Brasil suena tentador",
                    "'Sé que suena raro, pero un club brasileño está interesado. Buen dinero, estadios de locos y una liga que cada vez exporta más. ¿Te lo piensas al menos?'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f014,
                    "Escuchar, ¿por qué no?",
                    "Brasil. Fútbol, sol, pasión. Hay carreras brillantes que han pasado por allí. Pero tus amigos te miran raro: '¿Brasil? ¿No estás huyendo?' Y tu familia no se ve cruzando el Atlántico. La aventura es tentadora. La soledad, no tanto.",
                    0, 0, 0, 0, 0, 0, 1, 100, 0, 0, 0, 0, 1, -1);
            footballDataService.createOption(f014,
                    "No me interesa salir de Europa",
                    "'Mi carrera está en Europa.' La puerta se cierra. Pero un año después ese club brasileño gana la Libertadores y su delantero ficha por el Madrid. Tú ves la noticia desde tu sofá y piensas en el camino que no tomaste.",
                    0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(f014,
                    "Ir una temporada como experiencia vital, no solo deportiva",
                    "'Voy un año. Si es una locura, al menos será una locura bonita.' Tu representante organiza todo. Llegas a Brasil y el fútbol es otra cosa: más libre, más loco, más impredecible. Mejoras en creatividad pero pierdes rigor táctico. Vuelves a Europa siendo otro jugador. Mejor o peor depende de a quién le preguntes.",
                    0, 2, 0, 0, 0, -1, 2, 50, -1, -1, 0, 1, 0, 0);

// F015 — Interés de Argentina
            Event f015 = footballDataService.createEvent(
                    "Un club argentino te quiere",
                    "'Boca. River. Racing. No te voy a decir cuál hasta que me digas que te interesa.' Tu representante disfruta el suspense.",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f015,
                    "Dime quién es, me pica la curiosidad",
                    "El nombre te hace levantar una ceja. Argentina tiene historia para aburrir. Pero cuando investigas, descubres que el club está en crisis económica y ha vendido a sus tres mejores jugadores. ¿Quieren que seas la estrella de una plantilla en reconstrucción? Eso puede ser una oportunidad o un desastre.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f015,
                    "Argentina queda lejos, prefiero quedarme",
                    "'No me veo cruzando el charco.' Tu representante lo respeta. Pero un mes después te enteras de que un compañero de tu liga ha aceptado la oferta que tú rechazaste. En seis meses es ídolo allí y vuelve a Europa fichado por un club top.",
                    0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(f015,
                    "Preguntar si pueden esperar al final de temporada para decidir",
                    "'Me interesa pero no quiero moverme a mitad de año.' Tu representante negocia tiempo. El club acepta esperar. Pero en tres meses fichan a otro y cuando llega junio, la oferta ya no existe. La paciencia tiene un límite.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// F016 — Championship
            Event f016 = footballDataService.createEvent(
                    "El Championship te busca",
                    "'Un club del Championship quiere saber tu situación. No es Premier, pero el Championship es brutal. Estadios llenos, partidos cada tres días y una intensidad que no ves en otras ligas.'",
                    "fichaje", "decision", "common", "profesional", 18, 34, 1, 2, null
            );
            footballDataService.createOption(f016,
                    "Me interesa, es un buen escaparate hacia la Premier",
                    "El Championship es la puerta a la Premier. Si rindes ahí, arriba te ven. Pero el ritmo es demoledor: 46 partidos de liga más playoffs. Tu cuerpo nunca ha jugado tantos partidos seguidos. Si aguantas, subes. Si no, te rompes.",
                    0, 0, 0, -1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f016,
                    "No me interesa, busco algo más arriba",
                    "Apuntas más alto. Tu representante entiende. Pero pasan seis meses y nadie más llama. El Championship sigue ahí esperando. ¿Eras demasiado orgulloso o tenías razón en esperar?",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(f016,
                    "Ir cedido medio año para probar el ritmo inglés",
                    "'Voy seis meses. Si funciona, me quedo. Si no, vuelvo.' Llegas en enero. Cinco meses de barro, frío y tres partidos por semana. Vuelves transformado: más fuerte, más rápido, más duro. Pero tu club no te reconoce: 'Has cambiado. Ya no juegas como antes.' ¿Es mejor o peor?",
                    0, 0, 0, 1, 0, 0, 0, 0, -1, 0, 0, 0, 1, 0);

// F017 — En la lista de objetivos
            Event f017 = footballDataService.createEvent(
                    "Estás en la lista",
                    "Tu representante te enseña una captura: un medio deportivo filtra la lista de objetivos del {club} para la próxima temporada. Tu nombre está. Tercero de la lista.",
                    "fichaje", "decision", "epic", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f017,
                    "Seguir rindiendo y esperar a que pasen a la acción",
                    "Estar en la lista no significa nada. Pero te motiva. Los siguientes tres partidos juegas mejor que nunca. El problema: los dos que están por encima de ti en la lista también están rindiendo. Y uno de ellos es más barato.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0);
            footballDataService.createOption(f017,
                    "Pedir a tu representante que fuerce el contacto",
                    "'No quiero ser el tercero. Quiero que sepan que estoy interesado.' Tu representante llama al club. La respuesta: 'Lo tenemos en cuenta.' Eso puede significar todo o nada. Pero tu club actual se entera de la llamada y el director deportivo te cita en su despacho.",
                    0, 0, 0, 0, 0, 0, 1, 0, -2, -1, 0, 0, 2, 0);
            footballDataService.createOption(f017,
                    "No darle importancia, las listas cambian cada semana",
                    "Hoy eres tercero, mañana quinto, pasado no estás. Las listas de fichajes son humo hasta que hay una oferta. Sigues a lo tuyo. Tu cabeza está limpia. Pero tu representante se frustra: 'Si no muestras interés, ni te van a llamar.'",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, -1, -1);

// F018 — Dos clubes a la vez
            Event f018 = footballDataService.createEvent(
                    "Dos clubes te quieren",
                    "Tu representante te sienta: 'Problema bonito. Dos clubes preguntando a la vez. El {club} ofrece más minutos. El otro ofrece más dinero. ¿Qué priorizas?'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f018,
                    "Escuchar a ambos y dejar que compitan",
                    "No cierras ninguna puerta. La competencia mejora las condiciones. Pero mientras negocian, la prensa filtra que estás jugando con dos clubes y tu imagen de 'mercenario' empieza a circular. Ninguno de los dos confía del todo en ti.",
                    0, 0, 0, 0, 0, 0, 2, 50, -1, -1, 0, -1, 2, 0);
            footballDataService.createOption(f018,
                    "Priorizar al que ofrezca más minutos",
                    "El dinero va y viene. Los minutos se juegan ahora. Eliges fútbol. Pero dos años después, el club rico que rechazaste gana la Champions y tú ves el partido desde casa. ¿Era minutos lo que necesitabas o era ambición?",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(f018,
                    "Priorizar al club más grande por nombre",
                    "El nombre pesa en el currículum. Firmas con el grande. Pero al llegar descubres que eres el séptimo fichaje del verano y la competencia interna es feroz. El club pequeño te habría puesto en el centro. El grande te pone en cola.",
                    0, 0, 0, 0, -1, 0, 2, 0, -1, 0, 0, 1, 1, 0);

// F019 — Tres clubes interesados
            Event f019 = footballDataService.createEvent(
                    "El mercado se dispara",
                    "'Siéntate que esto es gordo. Tres clubes interesados en ti. Tres. Tu valor se ha disparado esta temporada.' Tu representante no cabe en sí de la emoción.",
                    "fichaje", "decision", "epic", "profesional", 22, 38, 1, 3, null
            );
            footballDataService.createOption(f019,
                    "Escuchar todas las ofertas antes de decidir",
                    "Tres opciones sobre la mesa. La decisión más importante de tu carrera. Pero mientras comparas, tu rendimiento en el campo baja. Tres semanas de llamadas, reuniones y noches sin dormir. El míster te sienta: 'No sé dónde tienes la cabeza, pero aquí no.'",
                    0, 0, 0, 0, -1, 0, 2, 0, -2, 0, 0, 1, 2, 0);
            footballDataService.createOption(f019,
                    "Elegir el mejor proyecto deportivo, da igual el dinero",
                    "'Quiero jugar donde vaya a ser mejor futbolista.' Tu representante suspira: 'Eso le cuesta a mi comisión un 40%.' Eliges el club con mejor plan. Llegas motivado. Pero el dinero que rechazaste habría resuelto la vida de tu familia durante años.",
                    0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, -1, 0);
            footballDataService.createOption(f019,
                    "Dejar que tu representante decida la mejor opción",
                    "'Tú sabes de esto más que yo. Elige tú.' Tu representante elige el que le da mejor comisión. ¿Coincide con lo mejor para ti? Puede que sí. Puede que no. Nunca lo sabrás porque nunca comparaste las otras dos.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3, 0);

// F020 — Club inesperado
            Event f020 = footballDataService.createEvent(
                    "Ha aparecido un club que no esperabas",
                    "Cuando la decisión parecía clara, tu representante te llama a las once de la noche: 'Ha entrado un club que no esperábamos. Y es grande. MUY grande. Esto lo cambia todo.'",
                    "fichaje", "decision", "legendary", "profesional", 22, 38, 2, 3, null
            );
            footballDataService.createOption(f020,
                    "Priorizarlo, es una oportunidad irrepetible",
                    "El nombre del {topClub} cambia todas las ecuaciones. Algunos trenes solo pasan una vez. Pero el club con el que llevabas semanas negociando se siente traicionado y tu reputación en el mercado queda dañada: 'Este deja plantado a la gente.'",
                    0, 0, 0, 0, 0, 0, 3, 0, -2, -2, 0, -1, 2, -1);
            footballDataService.createOption(f020,
                    "Mantener las negociaciones anteriores por lealtad",
                    "No vas a dejar plantado a un club por un recién llegado. Firmas donde ya habías negociado. Pero cada vez que ves jugar al {topClub} piensas: 'Podrías ser tú ahí.' La lealtad es bonita. La duda, eterna.",
                    0, 0, 0, 0, 1, 0, 0, 0, 2, 1, 0, 1, -1, 0);
            footballDataService.createOption(f020,
                    "Pedir 48 horas para pensarlo todo",
                    "Cuarenta y ocho horas de insomnio. Listas de pros y contras. Llamadas a tu padre a las tres de la mañana. Al final decides... y al segundo de decirlo, te preguntas si has elegido bien. Elijas lo que elijas, la duda no se va nunca.",
                    0, 0, 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 1, 0);

            // F021 — Cesión
            Event f021 = footballDataService.createEvent(
                    "El club quiere cederte",
                    "El director deportivo te cita: 'Creemos en ti, pero aquí no vas a tener minutos. Hemos hablado con el {club} para cederte una temporada. ¿Qué opinas?'",
                    "fichaje", "decision", "uncommon", "profesional", 18, 26, 1, 3, null
            );
            footballDataService.createOption(f021,
                    "Aceptar la cesión, necesito jugar",
                    "Nuevo vestuario, nueva ciudad. Cada sábado juegas. Pero a mitad de temporada descubres que el club de cesión no puede comprarte y tu club de origen no quiere que vuelvas. Estás en tierra de nadie. Los minutos eran reales, el futuro no tanto.",
                    0, 1, 0, 0, 0, 0, 0, 0, -2, -1, 0, 0, 1, 0);
            footballDataService.createOption(f021,
                    "Rechazarla y quedarme a pelear",
                    "'No me voy.' El director lo respeta. Pero en noviembre llevas cuatro partidos jugados y ves a tu competidor titular cada semana. El orgullo de quedarte se transforma en frustración de no jugar. ¿Cuánto tiempo puedes aguantar?",
                    0, 0, 0, 0, 1, 0, -1, 0, 1, 0, 0, 0, -1, 0);
            footballDataService.createOption(f021,
                    "Aceptar pero elegir el destino yo",
                    "'Me voy, pero quiero saber a dónde.' El director te da dos opciones. Una tiene mejor nivel, la otra más minutos garantizados. Eliges la de más minutos. Tres meses después la otra opción asciende a Primera. Elegiste mal. O no. Pero la duda no se va.",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0);

// F022 — Cesión con opción de compra
            Event f022 = footballDataService.createEvent(
                    "Cesión con truco",
                    "Tu representante te avisa: 'El {club} quiere incluir una opción de compra en la cesión. Si rindes bien, te fichan definitivamente sin que tu club pueda impedirlo.'",
                    "fichaje", "decision", "rare", "profesional", 18, 28, 1, 3, null
            );
            footballDataService.createOption(f022,
                    "Aceptar la opción de compra",
                    "Si rindes, te quedas. Si no, vuelves. Parece un win-win. Pero rindes TAN bien que el club ejecuta la opción por una cifra que fijaron cuando no valías tanto. Tu club de origen se queda sin ti y sin el dinero que ahora vales. Y tú te quedas atado a un contrato que ya no refleja tu nivel.",
                    0, 0, 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0);
            footballDataService.createOption(f022,
                    "Intentar ir sin opción de compra para mantener las puertas abiertas",
                    "'Quiero ir cedido pero sin opción.' La negociación se complica. El club de cesión pierde interés: '¿Para qué desarrollamos a un jugador que no podremos fichar?' Al final aceptan, pero te tratan como un préstamo, no como un fichaje. Nunca eres del todo suyo.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 1);
            footballDataService.createOption(f022,
                    "Negociar una opción de compra más alta para que tu club también gane",
                    "'Subid la opción a una cifra justa.' Tu representante negocia duro. Consigue una opción más alta. Si la ejecutan, tu club gana. Pero el club de cesión, al ver la cifra, te da menos minutos: 'No vamos a desarrollar a un jugador que no podemos pagar.'",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);

// F023 — Cesión a un rival directo
            Event f023 = footballDataService.createEvent(
                    "Cesión a un rival de tu propia liga",
                    "Tu representante frunce el ceño: 'La opción de cesión es a un rival directo de tu liga. Tu directiva duda porque si rindes bien, les puedes hacer daño.'",
                    "fichaje", "decision", "uncommon", "profesional", 18, 28, 1, 3, null
            );
            footballDataService.createOption(f023,
                    "Aceptar, necesito jugar donde sea",
                    "Te da igual la camiseta del rival. Necesitas minutos. Pero cuando te toca jugar contra tu club, marcas un gol y la celebración se te congela. No sabes si levantar el puño o bajar la cabeza. La grada de tu ex-club te silba. La de tu club temporal te aplaude. No eres de ningún sitio.",
                    0, 0, 1, 0, -1, 0, 0, 0, -3, 0, 0, 0, 1, -1);
            footballDataService.createOption(f023,
                    "Pedir que busquen otra opción que no sea un rival",
                    "'No quiero jugar contra mi propio club.' Tu representante busca alternativas. Tarda un mes. La única opción que aparece es un equipo peor, con menos minutos garantizados. A veces ser leal tiene un precio deportivo.",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, -1, 0);
            footballDataService.createOption(f023,
                    "Aceptar pero pedir una cláusula de no alineación contra tu club",
                    "'Voy, pero no juego contra mi equipo.' El rival acepta a regañadientes. Juegas cada semana menos un partido. Tus compañeros temporales lo entienden pero el día del partido contra tu club te ven en la grada con ropa de calle y se preguntan de qué lado estás.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 1, 0);

// F024 — Cesión al extranjero
            Event f024 = footballDataService.createEvent(
                    "La cesión es fuera del país",
                    "'La única opción es en el extranjero. Otro país, otro idioma, otra liga. Pero también otra experiencia que te puede hacer crecer como futbolista y como persona.'",
                    "fichaje", "decision", "rare", "profesional", 18, 28, 1, 3, null
            );
            footballDataService.createOption(f024,
                    "Aceptar la aventura sin pensarlo",
                    "Maleta grande, pasaporte y liga nueva. Los primeros meses son duros: idioma, soledad, comida diferente. Pero cuando empiezas a jugar, todo encaja. Vuelves siendo otro jugador. El problema: tu club ya no te reconoce y has perdido tu sitio en el grupo.",
                    0, 1, 0, 0, 1, 0, 1, 0, -2, -2, 0, 0, 1, 0);
            footballDataService.createOption(f024,
                    "Prefiero quedarme en mi país cerca de mi gente",
                    "'No estoy preparado para irme tan lejos.' Tu representante busca opciones nacionales. No aparece ninguna que te convenza. Acabas la temporada sin cesión, sin minutos y con la sensación de que el miedo te ha paralizado.",
                    0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(f024,
                    "Ir pero con condiciones: contrato corto y vuelta garantizada",
                    "'Voy seis meses. Si no funciona, vuelvo en enero.' Llegas con la maleta a medio hacer y un pie en cada país. Juegas, aprendes, pero nunca te integras del todo. Los compañeros saben que te vas pronto y no invierten en conocerte.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0);


// F025 — Vuelta de cesión
            Event f025 = footballDataService.createEvent(
                    "Has vuelto de la cesión. ¿Y ahora qué?",
                    "Un año fuera. Has jugado, has crecido. Vuelves y el entrenador te para: 'Has vuelto. ¿Tú qué quieres?'",
                    "fichaje", "decision", "uncommon", "profesional", 19, 28, 1, 3, null
            );
            footballDataService.createOption(f025,
                    "Quedarme y competir por un puesto",
                    "'He ido a curtirme y he vuelto mejor.' El entrenador: 'Demuéstralo.' Pero el que jugó en tu puesto mientras estabas fuera lleva un año de ventaja en el sistema. Tú eres mejor jugador, él conoce mejor al equipo. La competencia no es solo talento.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, -1, 0, 0, 0, 0);
            footballDataService.createOption(f025,
                    "Buscar otra cesión, necesito más minutos",
                    "'No estoy preparado para ser titular aquí. Prefiero seguir jugando fuera.' Otro año de aprendizaje. Pero llevas dos años cedido y el club empieza a verte como alguien que nunca será de la casa. Las cesiones tienen fecha de caducidad.",
                    0, 1, 0, 0, 0, 0, -1, 0, -2, 0, 0, 0, 0, -1);
            footballDataService.createOption(f025,
                    "Pedir salir definitivamente",
                    "'O me quedo de verdad o me voy para siempre.' El entrenador levanta las cejas. Valiente. Tu club te pone en el mercado. Pero al ponerte a la venta, tu precio baja porque todos saben que quieres irte. Negocias desde la debilidad.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 2, -2);

// F026 — Renovación básica
            Event f026 = footballDataService.createEvent(
                    "El club quiere que te quedes",
                    "El director deportivo te para después del entrenamiento: 'Tu contrato entra en su última fase. Queremos que sigas. ¿Hablamos?'",
                    "fichaje", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(f026,
                    "Renovar sin pensarlo, estoy bien aquí",
                    "Firmas rápido. Estabilidad, casa, confianza. Pero tu representante te llama furioso: 'Has firmado sin negociar. Me has quitado la posibilidad de conseguirte algo mejor. Ni me has consultado.' La relación con tu agente se resiente.",
                    0, 0, 0, 0, 0, 0, 1, 0, 2, 1, 1, 0, -2, 2);
            footballDataService.createOption(f026,
                    "Esperar a ver si llegan ofertas antes de firmar",
                    "'De momento no firmo. Quiero ver qué pasa.' La directiva frunce el ceño. El vestuario se entera de que no has renovado y algunos empiezan a tratarte como alguien con un pie fuera. Pero tu representante trabaja y aparece una oferta un 30% mejor.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, 0, 2, -1);
            footballDataService.createOption(f026,
                    "Renovar pero pidiendo mejores condiciones",
                    "'Quiero quedarme, pero mi rendimiento merece más.' La negociación se alarga tres semanas. Consigues un 20% más. Pero el club te lo recuerda: 'Te hemos subido el sueldo. Ahora necesitamos que rindas como tal.' La presión sube con el salario.",
                    0, 0, 0, 0, -1, 0, 0, 50, 0, 0, 0, 0, 1, 1);

// F027 — Renovación con mejora salarial
            Event f027 = footballDataService.createEvent(
                    "Te suben el sueldo",
                    "Tu representante llega con una sonrisa: 'El club quiere renovarte y mejorar tu salario. Bastante. Parece que por fin reconocen lo que vales.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f027,
                    "Aceptar la renovación con aumento",
                    "Firmas con una sonrisa. Más dinero, misma casa. Pero un compañero que lleva más años y cobra menos se entera de tu sueldo nuevo. A partir de ahí, cada vez que no marcas, su mirada te dice: '¿Para esto cobras más que yo?'",
                    0, 0, 0, 0, 0, 0, 2, 150, 1, -1, 1, 0, 0, 2);
            footballDataService.createOption(f027,
                    "Negociar aún más, creo que puedo sacar más",
                    "'Está bien, pero creo que hay margen.' Tu representante negocia duro. Consigue un poco más. Pero la directiva te ficha como 'difícil' y cuando llegue el momento de vender, no harán esfuerzos por retenerte.",
                    0, 0, 0, 0, 0, 0, 0, 30, -2, 0, 0, 0, 2, -1);
            footballDataService.createOption(f027,
                    "Aceptar pero pedir una cláusula de salida razonable",
                    "'Firmo, pero quiero una cláusula que me permita irme si llega un grande.' El club acepta a regañadientes. Firmas tranquilo sabiendo que tienes salida. Pero la cláusula se filtra y los equipos que te quieren esperan a que baje aún más en vez de pagar tu valor real.",
                    0, 0, 0, 0, 0, 0, 1, 100, 0, 0, 0, 0, 1, 0);

// F028 — Renovación con garantías deportivas
            Event f028 = footballDataService.createEvent(
                    "Dinero no, pero minutos sí",
                    "Tu representante te lo plantea: 'No ofrecen mucho más dinero, pero te garantizan ser pieza importante. Titularidad y un plan para ti. ¿El dinero lo es todo?'",
                    "fichaje", "decision", "uncommon", "profesional", 18, 34, 1, 3, null
            );
            footballDataService.createOption(f028,
                    "Aceptar, el proyecto deportivo importa más",
                    "Un entrenador que confía y minutos garantizados. Firmas motivado. Pero al míster lo echan en febrero y el nuevo no te ha prometido nada. Las garantías deportivas mueren con el míster que te las dio.",
                    0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1);
            footballDataService.createOption(f028,
                    "Pedir más dinero además de las garantías",
                    "'Las garantías están bien, pero el bolsillo importa.' La negociación se alarga. Consigues algo más. Pero la directiva te ve como alguien que nunca se conforma y cuando llega el momento de hacer sitio para un fichaje, tu nombre es el primero en la lista de prescindibles.",
                    0, 0, 0, 0, 0, 0, 0, 50, -1, 0, 0, 0, 1, -1);
            footballDataService.createOption(f028,
                    "Rechazar y buscar algo más ambicioso fuera",
                    "'Puedo aspirar a más.' Arriesgado. Tu representante busca. Aparece una oferta mejor... pero de un club que baja de categoría al año siguiente. Habrías estado mejor donde te querían.",
                    0, 0, 0, 0, 0, 0, -1, 0, -2, -1, 0, 0, 1, -2);

// F029 — Renovación complicada
            Event f029 = footballDataService.createEvent(
                    "Las negociaciones se atascan",
                    "Tu representante con tono serio: 'La renovación se está complicando. Estamos lejos de lo que pides y lo que ofrecen. Si no cedemos algo, esto se rompe.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f029,
                    "Ceder en algunas condiciones para cerrar",
                    "'Que no se rompa por mi culpa.' Cedes en el bonus y firmas. No es perfecto, pero es un acuerdo. Tu representante sin embargo te dice: 'Has cedido demasiado rápido. Ahora saben que cuando aprietan, tú aflojas. La próxima negociación será peor.'",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, -1, 1);
            footballDataService.createOption(f029,
                    "Mantener tu postura sin ceder",
                    "'Si no llegan, no firmo.' Las negociaciones se estancan. La prensa rumorea. Pero al final el club cede porque sabe que perderte gratis es peor. Firmas en tus términos. El problema: la directiva no te lo perdona y en el próximo mercado intentan venderte.",
                    0, 0, 0, 0, 0, 0, 0, 50, -2, 0, -1, -1, 2, -1);
            footballDataService.createOption(f029,
                    "Romper las negociaciones definitivamente",
                    "'Se acabó. No renuevo.' El vestuario se entera. La afición se divide: unos te apoyan, otros te ven como un traidor. La directiva te aparta del grupo una semana. Tu valor de mercado sube porque vas a salir gratis, pero tu día a día en el club se vuelve un infierno.",
                    0, 0, 0, 0, 0, 0, -1, 0, -3, -1, -2, -2, 3, -3);

// F030 — Último año de contrato
            Event f030 = footballDataService.createEvent(
                    "Último año de contrato",
                    "Tu representante: 'Te queda un año. Si no renuevas, en seis meses negocias libre. Tu club lo sabe. Y están nerviosos.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f030,
                    "Renovar y acabar con la incertidumbre",
                    "Firmas y todos respiran. Estabilidad. Pero has perdido la mayor carta de negociación que tendrás nunca: la libertad. Podrías haber esperado y elegido cualquier club del mundo sin costarles un euro.",
                    0, 0, 0, 0, 0, 0, 1, 50, 2, 1, 1, 0, -2, 3);
            footballDataService.createOption(f030,
                    "Esperar a ser libre y elegir sin presiones",
                    "Libertad total en seis meses. Pero tu club, sabiendo que te vas, deja de contar contigo. Te sienta, te aparta de los partidos importantes. Los últimos seis meses de contrato son un cementerio deportivo.",
                    0, 0, 0, 0, 0, 0, 2, 0, -3, -2, -2, 1, 3, -3);
            footballDataService.createOption(f030,
                    "Escuchar ofertas mientras decides si renovar",
                    "Juego peligroso. Tu club negocia la renovación mientras tu agente tantea el mercado. Un periódico filtra que estás negociando con otro club. Tu capitán te dice en la ducha: 'Decide de una vez, esto está afectando al vestuario.'",
                    0, 0, 0, 0, -1, 0, 1, 0, -1, -2, -1, 0, 2, -1);

// F031 — Cláusula de rescisión
            Event f031 = footballDataService.createEvent(
                    "Quieren subir tu cláusula",
                    "En la renovación, el club pide aumentar tu cláusula. Tu representante traduce: 'Quieren blindarte. Si sube, es más difícil que alguien te saque.'",
                    "fichaje", "decision", "uncommon", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f031,
                    "Aceptar una cláusula alta, estoy comprometido",
                    "Eres intocable. Pero cuando un grande llama dos años después, la cláusula les asusta y no pagan. Has renunciado a tu salida por lealtad. La lealtad es bonita hasta que te atrapa.",
                    0, 0, 0, 0, 0, 0, 1, 0, 2, 1, 1, 0, -2, 3);
            footballDataService.createOption(f031,
                    "Pedir una cláusula baja para tener libertad",
                    "'No quiero cerrarme puertas.' La directiva no está contenta. Pero en un año y medio, un club paga tu cláusula y te vas por la puerta grande. El club se queda sin ti y con la sensación de haberlo regalado.",
                    0, 0, 0, 0, 0, 0, 0, 0, -2, -1, -1, 0, 3, -1);
            footballDataService.createOption(f031,
                    "Aceptar cláusula alta a cambio de mejor salario",
                    "Negociación clásica: los dos ceden algo. Firmas con cláusula alta y sueldo bueno. El problema: ahora eres el jugador más caro de vender Y el que más cobra. Si el equipo va mal, eres el símbolo del gasto excesivo.",
                    0, 0, 0, 0, 0, 0, 0, 80, 0, 0, 0, -1, 0, 1);

// F032 — El club rechaza una oferta por ti
            Event f032 = footballDataService.createEvent(
                    "Tu club dice que no",
                    "Tu representante frustrado: 'La oferta del {club} era buena. Tú estabas dispuesto. Pero tu club la ha rechazado. Dicen que no es suficiente.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f032,
                    "Presionar para que te dejen salir",
                    "'Quiero irme y lo saben.' La relación con la directiva se tensa. Pero a veces forzar funciona: una semana después suben la oferta y tu club acepta. Te vas, pero con la etiqueta de 'conflictivo' para siempre.",
                    0, 0, 0, 0, 0, 0, 0, 0, -2, -1, -2, -1, 1, -3);
            footballDataService.createOption(f032,
                    "Respetar la decisión y seguir profesional",
                    "'Si no hay acuerdo, sigo aquí.' La directiva te lo agradece. Juegas bien el resto de temporada. Pero al acabar, la oferta ya no está y el club que te quería ha fichado a otro. El respeto no se come.",
                    0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, -1, 2);
            footballDataService.createOption(f032,
                    "Pedir que vuelvan a negociar con una cifra intermedia",
                    "'Que hablen otra vez.' Tu representante media. Encuentran un punto medio. Pero la negociación se alarga tanto que el mercado cierra y te quedas. Hasta enero.",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 1, 0);

// F033 — Tú rechazas una oferta
            Event f033 = footballDataService.createEvent(
                    "No te convence",
                    "Tu representante presenta la oferta al detalle. Niegas con la cabeza: 'No. No me veo ahí.' Él insiste: '¿Seguro? El dinero es bueno.' 'Seguro.'",
                    "fichaje", "decision", "uncommon", "profesional", 16, 38, 1, 3, null
            );
            footballDataService.createOption(f033,
                    "Confirmar el rechazo sin mirar atrás",
                    "No es tu sitio y punto. Pero tu representante, que ya había negociado comisiones, se enfada. 'Me has hecho trabajar para nada.' La relación se enfría. Y el siguiente club que pregunte por ti puede que no llegue nunca.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, -2, 0);
            footballDataService.createOption(f033,
                    "Cambiar de opinión y pedir que vuelvan a hablar",
                    "Dos horas después la duda te come. Le dices a tu representante que llame. Pero el club ya ha cerrado con otro jugador. Has tardado dos horas de más. En el mercado, dudar es perder.",
                    0, 0, 0, 0, -1, 0, -1, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(f033,
                    "Rechazar pero decirle a tu agente que busque algo parecido",
                    "'Ese club no, pero ese TIPO de club sí. Busca algo similar.' Tu representante entiende lo que quieres. Tarda dos meses pero aparece algo que encaja. A veces saber decir no es el primer paso para encontrar el sí.",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0);

// F034 — Oferta mejorada
            Event f034 = footballDataService.createEvent(
                    "Han subido la oferta",
                    "Tu representante con voz cantarina: 'El {club} no se rinde. Han vuelto con una oferta bastante superior. Esta sí es seria.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f034,
                    "Ahora sí me interesa",
                    "La nueva cifra cambia las cosas. Pero tu club actual se siente insultado: 'Han tenido que subir la oferta para que te intereses. Eso dice mucho de ti.' La directiva ya no te mira igual.",
                    0, 0, 0, 0, 0, 0, 2, 0, -2, -1, 0, 0, 2, -1);
            footballDataService.createOption(f034,
                    "Sigo sin verlo claro, no es cuestión de dinero",
                    "Más dinero no cambia el hecho de que no te convence el proyecto. Te mantienes. Pero tu representante te dice: 'Chaval, hay un momento en que el no se convierte en nunca. Y estás llegando a ese punto.'",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(f034,
                    "Pedir una mejora más antes de aceptar",
                    "'Casi. Un poco más y cerramos.' Tercer round de negociación. El club acepta pero te avisa: 'Es la última vez que subimos.' Firmas sabiendo que has exprimido cada euro. Pero llegas al club con la etiqueta de 'caro y exigente.'",
                    0, 0, 0, 0, 0, 0, 1, 30, -1, -1, 0, 0, 1, 0);

// F035 — Última oferta
            Event f035 = footballDataService.createEvent(
                    "Tómalo o déjalo",
                    "Tu representante serio: 'Última oferta. No van a subir más. Tienen otro jugador esperando. O ahora o nunca.'",
                    "fichaje", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(f035,
                    "Aceptar antes de que se arrepientan",
                    "No dejas pasar el tren. Firmas. La decisión perfecta no existe, existe la decisión a tiempo. Pero al llegar descubres que el 'otro jugador esperando' era un farol. Podrías haber negociado más. Ya es tarde.",
                    0, 0, 0, 0, 0, 0, 2, 80, 0, 0, 0, 0, 1, 0);
            footballDataService.createOption(f035,
                    "Rechazar y mantener tu postura",
                    "Si no es lo que quieres, no es lo que quieres. El club ficha al otro jugador. Tres meses después ese jugador es titular y marca en un derbi. Tú sigues donde estabas preguntándote qué habría pasado.",
                    0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(f035,
                    "Pedir 24 horas más como último intento",
                    "'Solo un día.' El reloj corre. En esas 24 horas aparece una llamada de otro club que nadie esperaba. La oferta es peor económicamente pero el proyecto es mejor. Ahora tienes dos opciones y cero tiempo. La presión máxima saca lo mejor o lo peor de ti.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0);


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
                    "fichaje", "informativo", "uncommon", "profesional", 16, 38, 1, 3, null
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

        // eventos con flags
            
// DF001 — Ojeador de un top club
            Event df001 = footballDataService.createEvent(
                    "Un hombre con acreditación VIP te observa",
                    "Tercer partido consecutivo que ves al mismo tipo en la grada VIP con una carpeta y el escudo de un grande en la solapa. Tu representante confirma: 'Es del {topClub}. Te están siguiendo en serio.'",
                    "deportivo", "decision", "rare", "profesional", 18, 38, 2, 3, null
            );
            footballDataService.createOption(df001,
                    "Jugar tu fútbol sin cambiar nada",
                    "Si le gusta lo que eres, bien. Si no, que busque a otro. No vas a disfrazarte de algo que no eres. Pero el ojeador deja de venir después del cuarto partido. ¿Era bueno o malo que no cambiaras nada?",
                    0, 0, 0, 0, 2, 0, 1, 0, 1, 0, 0, 0, 0, 0);
            footballDataService.createOption(df001,
                    "Subir tu nivel un punto para impresionar",
                    "Cada partido con él en la grada juegas un poco más enchufado. Metes un golazo el tercer partido. El ojeador apunta tu nombre con bolígrafo rojo. Tu representante recibe una llamada esa misma noche.",
                    0, 1, 1, 0, 0, 0, 3, 0, 0, 0, 0, 1, 1, 0,
                    "interes_topclub");
            footballDataService.createOption(df001,
                    "Forzar jugadas espectaculares para lucirte",
                    "Intentas tres bicicletas, un tiro desde 30 metros y un sombrero. Dos salen bien, una sale fatal. El ojeador apunta cosas. No sabes si buenas o malas.",
                    0, 0, 1, 0, -1, 0, 2, 0, -1, 0, 0, 1, 0, 0);

// DF002 — La Premier League pregunta
            Event df002 = footballDataService.createEvent(
                    "Noticias desde Inglaterra",
                    "Tu representante te llama después del entrenamiento: 'Un club de la Premier League ha pedido un informe completo sobre ti. Quieren vídeos de los últimos diez partidos.' La Premier. El fútbol de verdad.",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(df002,
                    "Dejar que tu representante gestione todo",
                    "Le das vía libre. Él manda los vídeos, negocia, tantea. Tú sigues jugando. Pero la espera te come por dentro y entrenas peor durante dos semanas.",
                    0, 0, 0, 0, -1, 0, 1, 0, 0, 0, 0, 0, 1, 0,
                    "interes_liga:Premier League");
            footballDataService.createOption(df002,
                    "Centrarte en rendir para que los vídeos impresionen",
                    "Si van a ver tus últimos diez partidos, que los diez sean de matrícula. Sales enchufado cada semana. El nivel sube. Pero la presión autoimpuesta te agota.",
                    0, 0, 1, -1, 0, 0, 2, 0, 0, 0, 0, 1, 1, 0,
                    "interes_liga:Premier League");
            footballDataService.createOption(df002,
                    "No darle importancia hasta que haya algo concreto",
                    "Informes piden muchos clubes. No significa nada hasta que hay una oferta. Sigues a lo tuyo. Tu representante se frustra: 'Si no muestras interés, no van a dar el paso.'",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, -1, -1);

// DF003 — Interés desde Alemania
            Event df003 = footballDataService.createEvent(
                    "Alemania te tiene en el radar",
                    "Tu representante te enseña un artículo de un medio deportivo alemán: tu nombre aparece en una lista de 'jóvenes a seguir' junto a otros cinco jugadores. Un club de la Bundesliga ha contactado.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 34, 1, 3, null
            );
            footballDataService.createOption(df003,
                    "Pedir más información sobre el club y la liga",
                    "Investigas. La Bundesliga apuesta por jóvenes, los estadios están llenos y el nivel táctico es alto. Te convence. Le dices a tu representante que mueva ficha.",
                    0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0,
                    "interes_liga:Bundesliga");
            footballDataService.createOption(df003,
                    "No me veo fuera de mi país ahora mismo",
                    "Alemania queda lejos. El idioma, la comida, el frío. No estás preparado. Pero un año después el jugador que ficharon en tu lugar debuta en Champions. Tú ves el partido desde casa.",
                    0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(df003,
                    "Estar abierto pero sin mover nada todavía",
                    "Le dices a tu representante que escuche pero no fuerce. Si llega una oferta concreta, la valoras. Si no, sigues donde estás.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// DF004 — Italia llama
            Event df004 = footballDataService.createEvent(
                    "Un club italiano pregunta por ti",
                    "Tu representante te lo suelta en el coche después del entrenamiento: 'Hay un club de la Serie A que pregunta por ti. Italia. Buen fútbol, cultura táctica, y pasta.'",
                    "deportivo", "decision", "uncommon", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(df004,
                    "Italia me atrae, que sigan hablando",
                    "La Serie A tiene algo especial. Defensa, táctica, historia. Le dices a tu representante que mantenga la puerta abierta.",
                    0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0,
                    "interes_liga:Serie A");
            footballDataService.createOption(df004,
                    "Prefiero quedarme donde estoy",
                    "Italia queda lejos de casa. Prefieres seguir creciendo donde estás. Pero el club italiano ficha a un rival tuyo que triunfa en seis meses.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0);
            footballDataService.createOption(df004,
                    "Solo me iría a un club top italiano",
                    "'Si es Juventus, Milan o Inter, hablamos. Si no, paso.' Tu representante: 'Eso reduce mucho las opciones.' Pero al menos sabes lo que quieres.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// DF005 — Francia te busca
            Event df005 = footballDataService.createEvent(
                    "Desde Francia con interés",
                    "Tu representante te manda un mensaje: 'Un club de la Ligue 1 ha contactado. No es el PSG pero es un club serio con proyecto europeo. ¿Hablamos?'",
                    "deportivo", "decision", "uncommon", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(df005,
                    "Que me cuenten el proyecto",
                    "Francia tiene su encanto. Buena liga, buen nivel de vida, y un fútbol que cada vez pesa más en Europa.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0,
                    "interes_liga:Ligue 1");
            footballDataService.createOption(df005,
                    "No me interesa Francia",
                    "No te ves jugando en la Ligue 1. Tu representante respeta la decisión sin insistir.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(df005,
                    "Depende del salario y del rol",
                    "No dices ni sí ni no. Quieres números concretos. Tu representante investiga y te pasa un informe. La oferta está bien pero no es espectacular.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);

// DF006 — Portugal como trampolín
            Event df006 = footballDataService.createEvent(
                    "Portugal como trampolín",
                    "Tu representante te sienta: 'Tengo muy buena relación con un director deportivo de la Liga Portugal. Me ha dicho que buscan un perfil como el tuyo. Portugal es un trampolín — de ahí han salido jugadores que ahora están en el Real Madrid o en el City.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 28, 1, 3, null
            );
            footballDataService.createOption(df006,
                    "Me interesa, Portugal puede ser un buen paso",
                    "La Liga Portugal ha exportado talento como pocos. Si rindes ahí, los grandes te ven.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0,
                    "interes_liga:Liga Portugal");
            footballDataService.createOption(df006,
                    "Prefiero dar el salto a una liga más grande directamente",
                    "Portugal te parece un paso intermedio. Quieres ir directo a las cinco grandes ligas. Pero tu representante te recuerda: 'No siempre puedes elegir el camino.'",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(df006,
                    "Solo si es Benfica, Porto o Sporting",
                    "'Los tres grandes o nada.' Tu representante asiente. Esos tres son los que más exportan. Pero también los más exigentes.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// DF007 — Un equipo concreto te sigue
            Event df007 = footballDataService.createEvent(
                    "Un club te tiene en la agenda",
                    "Tu representante te confirma que el {club} lleva semanas siguiéndote. Han hablado con tu entorno, han pedido informes y han visto tus últimos cinco partidos en persona.",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(df007,
                    "Seguir rindiendo para que den el paso",
                    "Si están interesados, tu trabajo es demostrar que merecen ficharte. Entrenas con más ganas. Los siguientes partidos son de los mejores de la temporada.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 1, 0,
                    "interes_equipo:{club}");
            footballDataService.createOption(df007,
                    "Decirle a tu representante que fuerce el contacto",
                    "'No quiero esperar. Que sepan que estoy interesado.' Tu representante llama. El club: 'Lo tenemos en cuenta.' Pero tu club actual se entera.",
                    0, 0, 0, 0, 0, 0, 1, 0, -2, -1, 0, 0, 2, 0,
                    "interes_equipo:{club}");
            footballDataService.createOption(df007,
                    "No mover nada y dejar que venga solo",
                    "Si quieren venir, que vengan. No vas a suplicar. Pero a veces el que no se mueve se queda donde está.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);

// DF008 — El míster sugiere cesión
            Event df008 = footballDataService.createEvent(
                    "El míster te llama al despacho",
                    "Cierra la puerta. Se sienta. 'Mira, voy a ser honesto contigo. Confío en ti, pero no puedo garantizarte los minutos que necesitas. Quizá te vendría bien salir cedido medio año para jugar cada semana y volver más hecho.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 28, 1, 3, null
            );
            footballDataService.createOption(df008,
                    "Aceptar la sugerencia, necesitas jugar",
                    "El míster tiene razón. Sin minutos no creces. Pides a tu representante que busque una cesión. El míster te lo agradece: 'Vuelve hecho un jugador.'",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                    "interes_cesion");
            footballDataService.createOption(df008,
                    "Rechazarlo y quedarte a pelear",
                    "'No me voy, míster. Voy a ganarme el puesto aquí.' El entrenador asiente pero su cara dice que no lo ve claro. Los siguientes meses son una batalla contra el banquillo.",
                    0, 0, 0, 0, 1, 0, -1, 0, 0, 1, 0, 0, 0, 0);
            footballDataService.createOption(df008,
                    "Pedir que te dé una oportunidad antes de decidir",
                    "'Míster, ponme tres partidos seguidos. Si no rindo, me voy cedido.' Él acepta. Ahora tienes tres partidos para demostrar que mereces quedarte.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0);

// DF009 — Artículo en prensa deportiva
            Event df009 = footballDataService.createEvent(
                    "Sales en la prensa como posible fichaje",
                    "Un periodista deportivo publica un artículo: 'Cinco jugadores que deberían fichar los grandes este verano.' Tu nombre aparece en la lista. El artículo menciona al {topClub} como posible destino.",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(df009,
                    "No darle importancia, la prensa especula mucho",
                    "Un artículo no es una oferta. Sigues a lo tuyo. Pero tres compañeros te mandan el link con emojis de fuego. La presión mediática sube un grado.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0);
            footballDataService.createOption(df009,
                    "Usarlo como motivación para rendir más",
                    "Si los grandes te miran, que te vean en tu mejor versión. Los siguientes partidos subes el nivel. La prensa sigue hablando.",
                    0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 2, 1, 0,
                    "interes_topclub");
            footballDataService.createOption(df009,
                    "Pedirle a tu representante que confirme si hay interés real",
                    "Tu representante hace llamadas. Confirma que sí, hay interés real de un grande. No es solo prensa. Tu corazón late más rápido.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 2, 0,
                    "interes_topclub");

// DF010 — Excompañero habla bien de ti
            Event df010 = footballDataService.createEvent(
                    "Tu excompañero te recomienda",
                    "Un antiguo compañero de equipo que ahora juega en el {club} te llama: 'Tío, le he hablado de ti al director deportivo. Está interesado. ¿Quieres que le pase tu contacto?'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(df010,
                    "Sí, que le pase mi contacto",
                    "Tu excompañero hace de puente. El director deportivo habla con tu representante. Las cosas se ponen en marcha gracias a una llamada entre amigos.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0,
                    "interes_equipo:{club}");
            footballDataService.createOption(df010,
                    "Agradecerle pero no mover nada por ahora",
                    "'Gracias tío, pero estoy bien donde estoy.' Tu amigo lo entiende. Pero la oportunidad queda en el aire.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(df010,
                    "Pedirle que te cuente cómo es el club por dentro",
                    "Antes de dar ningún paso, quieres saber la verdad. Tu amigo te lo cuenta todo: lo bueno y lo malo. La información vale oro.",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,
                    "interes_equipo:{club}");

// DF011 — Gol que sale en todos los resúmenes
            Event df011 = footballDataService.createEvent(
                    "Tu gol da la vuelta al mundo",
                    "Golazo desde fuera del área que sale en todos los resúmenes europeos. Trending topic en redes. Tu nombre suena en tertulias de otros países. Tu representante no para de recibir mensajes.",
                    "deportivo", "informativo", "rare", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(df011,
                    "Disfrutar el momento y seguir trabajando",
                    "Un golazo no te convierte en estrella. Pero abre puertas. Tu representante filtra que hay llamadas de clubes importantes. ¿Será humo o algo real?",
                    0, 0, 0, 0, 1, 0, 3, 0, 0, 0, 1, 3, 1, 0,
                    "interes_topclub");
            footballDataService.createOption(df011,
                    "Subir el gol a redes sociales y potenciar tu imagen",
                    "El vídeo lo subes tú. Millones de reproducciones. Tu marca personal sube. Pero el míster te dice: 'Menos Instagram y más entrenamiento.'",
                    0, 0, 0, 0, 0, 0, 4, 0, -1, 0, 2, 2, 1, 0,
                    "interes_topclub");

// DF012 — Selección sub-21 con ojeadores
            Event df012 = footballDataService.createEvent(
                    "Selección sub-21 con ojeadores en la grada",
                    "Te convocan con la selección sub-21 para un amistoso. Tu representante te avisa: 'Va a haber ojeadores de media Europa. Es un escaparate perfecto.'",
                    "deportivo", "decision", "rare", "profesional", 18, 24, 1, 3, null
            );
            footballDataService.createOption(df012,
                    "Jugar con todo y aprovechar el escaparate",
                    "Sales enchufado. Marcas un gol y das una asistencia. Tres clubes preguntan por ti esa misma noche. Tu representante no duerme atendiendo llamadas.",
                    0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 2, 2, 0,
                    "interes_topclub");
            footballDataService.createOption(df012,
                    "Jugar seguro para no cometer errores",
                    "No te la juegas. Partido correcto sin brillo. Los ojeadores apuntan otros nombres. A veces lo seguro es lo más peligroso.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(df012,
                    "Pedir no jugar por precaución con una molestia",
                    "Tienes una pequeña molestia y prefieres no arriesgar. Te quedas en el banquillo. La molestia se cura. Pero la oportunidad de lucirte ante ojeadores se ha ido.",
                    0, 0, 0, 1, 0, 0, -1, 0, 0, 0, 0, -1, 0, 0);

// DF013 — Director deportivo te llama
            Event df013 = footballDataService.createEvent(
                    "Llamada de un director deportivo",
                    "Tu representante te pasa una llamada directa del director deportivo del {club}: 'Quería hablar contigo personalmente. Nos gustas mucho y queremos que sepas que estamos siguiendo tu evolución de cerca.'",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(df013,
                    "Agradecerle y mostrar interés",
                    "Una llamada directa del director deportivo es algo serio. No pasa todos los días. Le dices que te halaga y que estarías abierto a escuchar.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0,
                    "interes_equipo:{club}");
            footballDataService.createOption(df013,
                    "Ser educado pero no comprometerte a nada",
                    "Le agradeces la llamada pero no das ninguna señal. 'Ya veremos qué pasa.' El director deportivo entiende que no eres fácil.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(df013,
                    "Preguntarle directamente qué rol tendrías",
                    "No te andas con rodeos: '¿Qué papel tendría yo ahí?' El director deportivo te da una respuesta concreta. Ahora tienes información real para decidir.",
                    0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0,
                    "interes_equipo:{club}");

// DF014 — Rumores en redes sociales
            Event df014 = footballDataService.createEvent(
                    "Tu nombre es tendencia",
                    "Abres Twitter y tu nombre es trending topic. Un periodista fiable ha publicado: 'El {topClub} tiene en su agenda a [tu nombre] para la próxima ventana de fichajes.' Miles de comentarios. Tu teléfono no para.",
                    "deportivo", "decision", "epic", "profesional", 20, 38, 2, 3, null
            );
            footballDataService.createOption(df014,
                    "No entrar en redes y dejar que tu representante gestione",
                    "Tu representante te dice que no publiques nada. Silencio es poder. Mientras tanto, él confirma por sus contactos que el interés es real.",
                    0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 2, 2, 0,
                    "interes_topclub");
            footballDataService.createOption(df014,
                    "Publicar un emoji ambiguo que alimente el rumor",
                    "Un simple emoji de ojos o un reloj de arena. Las redes explotan con teorías. Tu club actual se molesta. Pero el {topClub} sabe que estás interesado.",
                    0, 0, 0, 0, 0, 0, 3, 0, -2, -1, 0, 1, 2, 0,
                    "interes_topclub");
            footballDataService.createOption(df014,
                    "Desmentirlo públicamente para no generar problemas",
                    "Publicas: 'Estoy centrado en mi club y contento aquí.' Tu directiva respira. Pero el {topClub} lee tu mensaje y piensa: 'No le interesamos.' Puerta cerrada.",
                    0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, -1, -2, 1);

// DF015 — Amistoso revelador
            Event df015 = footballDataService.createEvent(
                    "Amistoso contra un equipo de otra liga",
                    "Tu club juega un amistoso de pretemporada contra un equipo de otra liga. El rival tiene jugadores de nivel y el partido se televisa. Tu representante te avisa: 'Hay gente mirando.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(df015,
                    "Dar un recital y demostrar que estás para más",
                    "Juegas como si fuera una final. Dos regates, una asistencia y un disparo al palo. El entrenador rival te dice al acabar: 'Buen jugador.' Eso vale más que cualquier estadística.",
                    0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 1, 0,
                    "interes_topclub");
            footballDataService.createOption(df015,
                    "Jugar tranquilo, es solo un amistoso",
                    "No te la juegas en un amistoso de verano. Juegas correcto, sin riesgo. El partido pasa sin pena ni gloria.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(df015,
                    "Buscar al mejor jugador rival y competir con él",
                    "Te mides al central estrella del rival. Le ganas dos duelos, él te gana tres. Pero le has puesto las cosas difíciles y eso se nota.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0);

// DF016 — Tu agente tiene un plan
            Event df016 = footballDataService.createEvent(
                    "Tu representante tiene un plan de carrera",
                    "Tu representante te cita para comer: 'He estado pensando en tu trayectoria. Creo que tu próximo paso debería ser un grande de Europa. ¿Confías en mí?'",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(df016,
                    "Confiar en su plan y darle vía libre",
                    "Tu representante empieza a mover hilos. Contacta con clubes, manda vídeos, habla con intermediarios. En tres semanas hay interés real de un grande.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 1,
                    "interes_topclub");
            footballDataService.createOption(df016,
                    "Quiero decidir yo mi futuro, no mi representante",
                    "'Agradezco el interés, pero las decisiones las tomo yo.' Tu representante acepta pero se enfría un poco. La relación ya no es la misma.",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, -1);
            footballDataService.createOption(df016,
                    "Preguntar qué clubs tiene en mente",
                    "Antes de decidir nada, quieres saber los nombres. Tu representante suelta tres opciones. Una te ilusiona, otra te da miedo, la tercera te deja indiferente.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                    "interes_topclub");

// DF017 — Elogio del rival
            Event df017 = footballDataService.createEvent(
                    "Elogio inesperado del rival",
                    "Después de un partido, el segundo entrenador del equipo rival se acerca a tu míster: 'Oye, el chaval ese vuestro es muy bueno. ¿Cuánto le queda de contrato?' Tu míster no le contesta. Pero te lo cuenta.",
                    "deportivo", "informativo", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(df017,
                    "Tomarlo como un halago y seguir trabajando",
                    "Que un técnico rival pregunte por ti es señal de que estás haciendo bien las cosas. Pero también de que tu club podría perderte si no te cuida.",
                    0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0,
                    "interes_equipo:{club}");
            footballDataService.createOption(df017,
                    "Usarlo para negociar una mejora con tu club",
                    "Le dices a tu representante que use esa información para presionar una renovación. 'Si otros preguntan, mi club debería valorarme más.'",
                    0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 1, 0);

// DF018 — LaLiga te quiere
            Event df018 = footballDataService.createEvent(
                    "LaLiga te quiere",
                    "Tu representante te llama emocionado: 'Un club de LaLiga ha preguntado por ti. España. Sol, buen fútbol y una liga que todo el mundo ve.'",
                    "deportivo", "decision", "rare", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(df018,
                    "España me encanta, que avancen",
                    "LaLiga siempre ha sido tu sueño. El fútbol español es técnico, táctico y mediático. Le dices a tu representante que dé el paso.",
                    0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0,
                    "interes_liga:Primera Division");
            footballDataService.createOption(df018,
                    "Depende de qué club sea",
                    "No es lo mismo ir al Barça que al Celta. Quieres saber nombres antes de ilusionarte.",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,
                    "interes_liga:Primera Division");
            footballDataService.createOption(df018,
                    "No me veo cambiando de liga ahora",
                    "Estás cómodo donde estás. España puede esperar. Pero España no siempre espera.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0);

// DF019 — Se filtra que no has renovado
            Event df019 = footballDataService.createEvent(
                    "Se filtra que no has renovado",
                    "La prensa publica que tu contrato acaba pronto y que las negociaciones están paradas. De repente, tres clubes que nunca habían preguntado por ti contactan con tu representante.",
                    "deportivo", "informativo", "uncommon", "profesional", 20, 38, 1, 3, null
            );
            footballDataService.createOption(df019,
                    "Dejar que compitan por ti, es tu momento de poder",
                    "Cuando eres libre, tú mandas. Los clubes vienen a ti. Tu representante gestiona. Pero tu club actual te aparta del equipo.",
                    0, 0, 0, 0, 0, 0, 2, 0, -2, -1, -1, 1, 3, -2,
                    "interes_topclub");
            footballDataService.createOption(df019,
                    "Intentar renovar antes de que sea tarde",
                    "No quieres irte. Hablas con la directiva. Encuentran un acuerdo. Pero has perdido poder de negociación porque todos saben que quieres quedarte.",
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, -1, 1);
            footballDataService.createOption(df019,
                    "No hacer nada y dejar que el mercado decida",
                    "Ni fuerzas ni frenas. Que lo que tenga que pasar, pase. Es la estrategia del que confía en su talento. O la del que tiene miedo de decidir.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);

// DF020 — Compañero cedido te dice que hay hueco
            Event df020 = footballDataService.createEvent(
                    "Un compañero cedido te llama",
                    "Un compañero que está cedido en otro equipo te llama: 'Tío, aquí necesitan a alguien en tu posición. Se lo he dicho al míster y está interesado. ¿Quieres que hable?'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 28, 1, 3, null
            );
            footballDataService.createOption(df020,
                    "Sí, que hable, necesito minutos",
                    "Tu compañero hace de intermediario. El club cedente se interesa. Tu representante confirma que hay una opción real de cesión.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                    "interes_cesion");
            footballDataService.createOption(df020,
                    "No, prefiero quedarme a competir aquí",
                    "Agradeces pero rechazas. Quieres ganarte el puesto donde estás. Tu compañero: 'Vale, pero piénsatelo, aquí jugarías todos los fines de semana.'",
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0);
            footballDataService.createOption(df020,
                    "Investigar primero qué club es y qué nivel tiene",
                    "Antes de decir sí, quieres saber dónde irías. Tu compañero te cuenta todo. El nivel es correcto, los minutos garantizados.",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,
                    "interes_cesion");

// DF021 — Partido televisado en prime time
            Event df021 = footballDataService.createEvent(
                    "Partido televisado en prime time",
                    "Tu partido se televisa en horario estelar a todo el país. Millones de espectadores. El comentarista repite tu nombre varias veces. Al día siguiente, tu representante tiene el teléfono lleno de mensajes.",
                    "deportivo", "informativo", "uncommon", "profesional", 18, 38, 1, 3, null
            );
            footballDataService.createOption(df021,
                    "Ha sido solo un partido, no me vuelvo loco",
                    "Un buen partido no te convierte en estrella. Pero abre puertas. Tu representante confirma que hay consultas de varios clubes.",
                    0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 2, 1, 0,
                    "interes_topclub");
            footballDataService.createOption(df021,
                    "Aprovechar la visibilidad para pedir una mejora a tu club",
                    "Si otros te valoran, tu club debería valorarte más. Le pides a tu representante que negocie. La directiva se lo piensa.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);

// DF022 — Holanda como trampolín
            Event df022 = footballDataService.createEvent(
                    "Países Bajos: la fábrica de talento",
                    "Tu representante te comenta: 'Hay interés de un club de la Eredivisie. Holanda es una fábrica de talento — de ahí salen jugadores a las cinco grandes ligas cada verano.'",
                    "deportivo", "decision", "uncommon", "profesional", 18, 30, 1, 3, null
            );
            footballDataService.createOption(df022,
                    "Holanda puede ser un gran paso intermedio",
                    "Ajax, PSV, Feyenoord... clubes con historia que desarrollan jugadores. Si rindes ahí, los grandes de Europa te ven.",
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0,
                    "interes_liga:Eredivisie");
            footballDataService.createOption(df022,
                    "Prefiero una liga de las cinco grandes directamente",
                    "Holanda está bien pero quieres ir directamente a Premier, LaLiga, Bundesliga, Serie A o Ligue 1. No quieres pasos intermedios.",
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            footballDataService.createOption(df022,
                    "Investigar qué club es antes de decidir",
                    "No es lo mismo ir al Ajax que a un club pequeño. Quieres nombres antes de dar el paso.",
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,
                    "interes_liga:Eredivisie");

        }
}

}


/* hay que crearle el atributo con final(constante) siempre del repositorio a usar(siendo el repositorio el que usara las consultas)
 se le da el mismo nombre al atributo que el repositorio, luego metemos los repositrorios como si fuera un constructor,
 luego sobreescribimos lña funcion run() que no devuelve nada y le decimos que si la posicion tiene ya cosas, es decir,
 que si existen posiciones creadas que se pare y no ejecuite y sino las crea */
