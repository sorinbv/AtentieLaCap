package sorindonosa.cinesunt;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Play extends Activity implements SensorEventListener {

    public static List<String> listaRezultateString = new ArrayList<String>();
    public static List<Integer> listaRezultateInt = new ArrayList<Integer>();
    public static int scor;
    LinearLayout linLayout;
    private TextView cuvant, ceas, tip;
    private RelativeLayout rLayout;
    private MediaPlayer mp, sCorect, sPass;
    private List<String> lista = new ArrayList<String>();
    private List<String> lista2 = new ArrayList<String>();
    private SensorManager sensorMan;
    private Sensor accelerometer;
    private CountDownTimer cT;
    private boolean ev = true;
    CountDownTimer ctWait = new CountDownTimer(1700, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            ev = false;
        }

        @Override
        public void onFinish() {
            ev = true;
        }
    };
    private int culoareDeschisa = Color.parseColor("#c0dfd9");
    CountDownTimer ctCuloare = new CountDownTimer(500, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            rLayout.setBackgroundColor(culoareDeschisa);
            linLayout.setBackgroundColor(culoareDeschisa);
        }
    };
    private InterstitialAd interAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.play);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // ActionBar actionBar = getActionBar();
        // actionBar.hide();
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/cesar_font.otf");
        listaRezultateString.clear();
        listaRezultateInt.clear();
        scor = 0;

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        sensorMan = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMan.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        int culoareAccent = Color.parseColor("#FFC107");
        int culoareFundal = Color.parseColor("#3b3a36");

        cuvant = (TextView) findViewById(R.id.txtCuvant);
        cuvant.setTypeface(typeFace);
        tip = (TextView) findViewById(R.id.txtTip);
        tip.setTypeface(typeFace);
        tip.setVisibility(View.GONE);
        ceas = (TextView) findViewById(R.id.txtCeas);
        ceas.setTypeface(typeFace);
        // cuvant.setTextColor(culoareAccent);
        // ceas.setTextColor(culoareAccent);
        sCorect = MediaPlayer.create(getApplicationContext(), R.raw.corect);
        sPass = MediaPlayer.create(getApplicationContext(), R.raw.pass);

        rLayout = (RelativeLayout) findViewById(R.id.play);

        linLayout = (LinearLayout) findViewById(R.id.linearLayout1);

        linLayout.setBackgroundColor(culoareDeschisa);
        rLayout.setBackgroundColor(culoareDeschisa);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.final_sound);

        interAd = new InterstitialAd(this);
        interAd.setAdUnitId("ca-app-pub-1675138020340294/8224306365");

        interAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent myIntent = new Intent(Play.this, Rezultate.class);
                startActivity(myIntent);
            }
        });
        requestNewInterstitial();


        AdRequest adRequest = new AdRequest.Builder().build();
        interAd.loadAd(adRequest);

        if (MainActivity.getCategorie().equals("Actori")) {
            List<String> listAuxiliara = Arrays.asList("Patrick Swayze", "Kevin Bacon", "Benicio Del Toro",
                    "Harvey Kietel", "Ed Harris", "Willem Dafoe", "Charles Laughton", "Michael Douglas", "James Caan",
                    "Robert Downey Jr.", "Richard Burton", "Alec Guiness", "Geoffrey Rush", "Christopher Lee",
                    "Hugo Weaving", "Viggo Mortensen", "Sylvester Stallone", "Arnold Schwarzenegger", "Tim Robbins",
                    "Javier Bardem", "Steve Martin", "Mark Wahlberg", "Jim Carrey", "Mel Gibson", "Jon Voight",
                    "Woody Allen", "Peter Sellers", "Charlie Chaplin", "Kevin Costner", "Gene Wilder", "Steve McQueen",
                    "Nicolas Cage", "Bill Murray", "Burt Lancaster", "Ian McKellen", "James Cagney", "Peter O’ Toole",
                    "Will Smith", "John Travolta", "Christopher Walken", "Christopher Plummer", "George Clooney",
                    "Joe Pesci", "Matt Damon", "Samuel L. Jackson", "Philip Seymour Hoffman", "Tommy Lee Jones",
                    "Ben Kingsley", "Robert Duvall", "Gary Oldman", "Jeff Bridges", "Harrison Ford", "Montgomery Clift",
                    "Joaquin Phoenix", "Tom Cruise", "Ralph Fiennes", "Robert Redford", "Gregory Peck", "Bruce Willis",
                    "Edward Norton", "Liam Neeson", "Gene Hackman", "Kevin Spacey", "Jack Lemmon", "Sean Connery",
                    "Heath Ledger", "Christoph Waltz", "Robin Williams", "Sean Penn", "Denzel Washington",
                    "Henry Fonda", "Russell Crowe", "Kirk Douglas", "James Dean", "Paul Newman", "Brad Pitt",
                    "John Wayne", "Leonardo DiCaprio", "Anthony Hopkins", "Christian Bale", "Morgan Freeman",
                    "Johnny Depp", "Clint Eastwood", "Dustin Hoffman", "Cary Grant", "Tom Hanks", "James Stewart",
                    "Daniel Day-Lewis", "Jack Nicholson", "Robert De Niro", "Al Pacino", "Marlon Brando", "Zac Efron",
                    "Taylor Lautner", "Josh Hutcherson", "Shia LaBeouf", "Chace Crawford", "Alex Pettyfer",
                    "Jared Padalecki", "Drew Roy", "Kellan Lutz", "Alexander Ludwig", "Liam Hemsworth", "Drake Bell",
                    "Dave Franco", "Daniel Radcliffe", "Kelly Blatz", "Jake Gyllenhaal", "Ellen Page", "Emma Watson",
                    "Jennifer Aniston", "Kristen Stewart", "Winona Ryder", "Keira Knightley", "Octavia Spencer",
                    "Marisa Tomei", "Dianne Wiest", "Renée Zellweger", "Whoopi Goldberg", "Thelma Ritter",
                    "Joan Cusack", "Michelle Williams", "Jessica Chastain", "Catherine Zeta-Jones", "Maggie Smith",
                    "Anne Hathaway", "Cameron Diaz", "Meg Ryan", "Julia Roberts", "Demi Moore", "Amy Adams",
                    "Michelle Pfeiffer", "Uma Thurman", "Julianne Moore", "Rachel Weisz", "Jennifer Hudson",
                    "Audrey Tautou", "Rachel McAdams", "Gwyneth Paltrow", "Jennifer Connelly", "Halle Berry",
                    "Penelope Cruz", "Judi Dench", "Naomi Watts", "Jennifer Lawrence", "Marion Cotillard",
                    "Sandra Bullock", "Nicole Kidman", "Helen Mirren", "Angelina Jolie", "Charlize Theron",
                    "Natalie Portman", "Hilary Swank", "Kate Winslet", "Joan Crawford", "Diane Keaton", "Helen Hunt",
                    "Barbara Streisand", "Deborah Kerr", "Sophia Loren", "Rita Hayworth", "Natalie Wood",
                    "Barbara Stanwyck", "Holly Hunter", "Emma Thompson", "Cate Blanchett", "Jane Wyman", "Glenn Close",
                    "Greta Garbo", "Sissy Spacek", "Jane Fonda", "Jodie Foster", "Viven Leigh", "Grace Kelly",
                    "Marilyn Monroe", "Meryl Streep", "Elizabeth Taylor", "Audrey Hepburn", "Katharine Hepburn",
                    "Miley Cyrus", "Chloë Grace Moretz", "Maia Mitchell", "Miranda Cosgrove", "Debby Ryan",
                    "Abigail Breslin", "Madisen Beaty", "Kathryn Newton", "Willow Shields", "Maia Mitchell",
                    "Selena Gomez", "Bella Thorne", "Ariana Grande", "Stefanie Scott", "Eva Green", "Shailene Woodley",
                    "Rupert Grint", "Sean Bean", "Ian McKellen", "Chris Hemsworth", "Tom Hiddleston", "Matt LeBlanc",
                    "Courteney Cox", "Lisa Kudrow", "Chuck Norris", "Bruce Lee", "Jean-Claude Van Damme", "Jackie Chan",
                    "Ryan Gosling", "Matthew McConaughey", "Vin Diesel", "Ben Stiller", "Dwayne Johnson", "Ben Affleck",
                    "Adam Sandler", "Keanu Reeves", "Antonio Banderas", "Gerard Butler", "Simon Baker",
                    "Robert Pattinson", "Paul Walker", "Bradley Cooper", "Ashton Kutcher", "Mila Kunis");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            String deSters = lista.get(index);
            lista.remove(deSters);
        }

        if (MainActivity.getCategorie().equals("Animale")) {
            List<String> listAuxiliara = Arrays.asList("Cal", "Oaie", "Cimpanzeu", "Câine", "Vulpe", "Gorilă", "Babuin",
                    "Urs", "Focă", "Elefant", "Rădașcă", "Șobolan", "Guzgan", "Egretă", "Arici", "Antilopă", "Cămilă",
                    "Pisică", "Cerb", "Leu", "Lup", "Bursuc", "Hipopotam", "Nevăstuică", "Oposum", "Urs Panda", "Zebră",
                    "Râs", "Raton", "Șacal", "Sconcs", "Leopard", "Liliac", "Tapir", "Tigru", "Jaguar", "Lemur", "Puma",
                    "Cangur", "Cârtiță", "Girafă", "Mistreț", "Rinocer", "Ornitorinc", "Hienă", "Iepure", "Broască",
                    "Balenă", "Broască țestoasă", "Buburuză", "Cameleon", "Capră", "Caracatiță", "Crab", "Crap",
                    "Delfin", "Curcan", "Dihor", "Flamingo", "Furnică", "Găină", "Fluture", "Măgar", "Melc", "Muscă",
                    "Nevăstuică", "Oposum", "Pelican", "Pinguin", "Păun", "Pisică de mare", "Porc", "Ponei", "Rac",
                    "Rechin", "Șarpe", "Șopârlă", "Struț", "Urs polar", "Tigru", "Vacă", "Veveriță", "Varan", "Viespe",
                    "Vultur", "Popândău", "Castor", "Tarantulă", "Cameleon", "Albină", "Cărăbuș", "Lăcustă", "Libelulă",
                    "Țânțar", "Termită", "Molie", "Fluture", "Omidă", "Căpușă", "Rândunică", "Porumbel", "Cioară",
                    "Bufniță", "Papagal", "Rață", "Ciocănitoare", "Cuc", "Fazan", "Lebădă", "Pescăruș", "Șoim", "Stârc",
                    "Uliu", "Corb", "Coțofană", "Graur", "Barză", "Pupăză", "Albatros", "Canar", "Cocor",
                    "Pasărea colibri", "Sitar", "Vrabie", "Pupăză", "Cucuvea", "Ciocârlie", "Potârniche", "Gâscă",
                    "Salamandră", "Antilopă", "Cămilă", "Ghepard", "Panda roșu", "Raton", "Pirania", "Morsă", "Pinguin",
                    "Țipar", "Ton", "Căluț de mare", "Meduză", "Cuc", "Pelican", "Pescăruș", "Vultur", "Pasărea Kiwi",
                    "Bivol", "Arici de mare", "Păianjen", "Păstrăv", "Peruș", "Chinchilă", "Antilopa Gnu", "Marmotă",
                    "Hamster", "Sardină", "Coiot", "Gândac", "Cobra", "Calamar", "Elan", "Ren", "Hârciog", "Licurici",
                    "Dromader", "Taur", "Greier", "Lamă", "Panteră", "Păduche", "Zimbru", "Urangutan", "Mangustă");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            String deSters = lista.get(index);
            lista.remove(deSters);
        }
        if (MainActivity.getCategorie().equals("Meserii")) {
            List<String> listAuxiliara = Arrays.asList("Profesor", "Casier", "Bucătar", "Frizer", "Programator",
                    "Electrician", "Mecanic auto", "Salvamar", "Contabil", "Fermier", "Arbitru", "Arhitect", "Arhivar",
                    "Armurier", "Asistentă medicală", "Astronom", "Atlet", "Bibliotecar", "Biolog", "Brutar", "Bucătar",
                    "Cameristă", "Cântăreț", "Ceasornicar", "Cofetar", "Chelner", "Compozitor", "Consilier", "Contabil",
                    "Coregraf", "Cosmetician", "Critic de film", "Croitor", "Crupier", "Dansator", "Detectiv",
                    "Director", "Dispecer", "Ecolog", "Economist", "Educator", "Etnograf", "Evaluator", "Farmacist",
                    "Fizician", "Fizioterapeut", "Fotograf", "Garderobier", "Geograf", "Ghid turistic", "Geolog",
                    "Grafician", "Grădinar", "Gunoier", "Hidrolog", "Infirmier", "Inginer", "Instalator",
                    "Instructor auto", "Învățător", "Judecător", "Lector", "Liftier", "Logoped", "Macaragiu",
                    "Majordom", "Maistru", "Majordom", "Matematician", "Mecanic auto", "Medic", "Meteorolog", "Moașă",
                    "Notar", "Chimist", "Ortoped", "Paznic", "Pădurar", "Pescar", "Pictor", "Pilot de avion", "Pompier",
                    "Poștaș", "Preot", "Profesor", "Pediatru", "Psihiatru", "Psiholog", "Regizor", "Redactor",
                    "Salvamar", "Scafandru", "Scenarist", "Sculptor", "Sociolog", "Statistician", "Strungar", "Sudor",
                    "Sufleor", "Tâmplar", "Tehnician dentar", "Tehnolog chimist", "Tinichigiu", "Tractorist", "Portar",
                    "Vatman", "Vânzător", "Viticultor", "Zidar", "Agricultor", "Bijutier", "Avocat", "Bibliotecar",
                    "Brutar", "Blănar", "Brancardier", "Cantor", "Cartograf", "Ceasornicar", "Cioban", "Clovn",
                    "Consilier juridic", "Consul", "Coregraf", "Critic literar", "Dansator", "Decan", "Deputat",
                    "Președinte", "Dietetician", "Drujbist", "Gropar", "Gunoier", "Guvernator", "Hingher", "Macaragiu",
                    "Magician", "Măcelar", "Măturător", "Medic veterinar", "Medic chirurg", "Medic orelist",
                    "Medic legist", "Stomatolog", "Meteorolog", "Mitropolit", "Ministru", "Olar", "Parașutist",
                    "Patiser", "Cofetar", "Pescar", "Pilot auto", "Șofer de tir", "Potcovar", "Pompier", "Preot",
                    "Reporter", "Secretară", "Senator", "Stewardesa", "Taximetrist");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }
        if (MainActivity.getCategorie().equals("Plante")) {
            List<String> listAuxiliara = Arrays.asList("Cais", "Măr", "Brad", "Stejar", "Arborele de cafea",
                    "Coada calului", "Brusture", "Vișin", "Roșie", "Mesteacăn", "Plop", "Tei", "Castravete", "Afin",
                    "Aloe vera", "Anason", "Ardei iute", "Busuioc", "Castan", "Cătină", "Capere", "Cartof", "Chimen",
                    "Cicoare", "Cimbru", "Cireș", "Ciulin", "Coacăz roșu", "Coriandru", "Dovleac", "Dud", "Eucalipt",
                    "Fasole", "Fenicul", "Frag", "Frasin", "Gălbenele", "Gutui", "Hamei", "Hrean", "Ienupăr", "In",
                    "Jneapăn", "Levănțică", "Măceș", "Mărar", "Mac", "Merișor", "Mure", "Mușețel", "Nuc", "Păpădie",
                    "Porumb", "Rapiță", "Rodie", "Rostopască", "Salvie", "Roiniță", "Soc", "Sunătoare", "Tătăneasă",
                    "Tei", "Țintură", "Urzică", "Usturoi", "Vâsc", "Volbură", "Zmeur", "Floare de colț", "Avocado",
                    "Cătină", "Clementin", "Coacăz negru", "Cocotier", "Curmal", "Dud", "Fistic", "Frag", "Grepfrut",
                    "Gutui", "Kaky", "Kiwi", "Lămâi", "Limetă", "Mandarin", "Măslin", "Merișor", "Migdal", "Mur", "Nuc",
                    "Nectarin", "Pepene galben", "Pepene verde", "Piersic", "Pomelo", "Portocal", "Păr", "Prun",
                    "Rodie", "Smochin", "Viță de vie", "Zmeur", "Lăptucă", "Lobodă", "Mărar", "Pătrunjel", "Tarhon",
                    "Salată verde", "Spanac", "Țelină", "Varză", "Arahide", "Vânătă", "Bob", "Fasole", "Linte",
                    "Mazăre", "Năut", "Soia", "Ceapă", "Cartof dulce", "Cartof", "Ghimbir", "Gulie", "Praz", "Rubarbă",
                    "Sparanghel", "Hrean", "Morcov", "Ridiche neagră", "Ridiche albă", "Sfeclă", "Floarea soarelui",
                    "Boabe de muștar", "Dafin", "Rozmarin", "Brad", "Molid", "Tuie", "Fag", "Pin", "Salcâm", "Arin",
                    "Stejar", "Cuișoare", "Piper", "Liliac", "Trestie", "Alun", "Mătrăgun", "Tutun", "Ceai verde",
                    "Salcie", "Nufăr", "Lotus", "Mango", "Papaya");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }

        if (MainActivity.getCategorie().equals("Personaje")) {
            List<String> listAuxiliara = Arrays.asList("Homer Simpson", "Bambi", "Bart Simpson", "Grinch", "Batman",
                    "Casper", "Ciocănitoarea Woody", "Daphne\n (Scooby Doo)", "Dick\n (Curse Trăsnite)",
                    "Dino\n (Flintstone)", "Droopy", "Dumbo", "Felix Motanul", "Fred Flintstone",
                    "Fred Jones (Scooby Doo)", "Garfield", "Goofy", "Huckleberry Hound", "Jerry șoarecele",
                    "Johnny Test", "George\n (Familia Jetson)", "Elroy\n (Familia Jetson)", "Jane\n (Familia Jetson)",
                    "Judy Jetson (Familia Jetson)", "Phineas\n (Phineas și Ferb)", "Ferb\n (Phineas și Ferb)",
                    "Candace\n (Phineas și Ferb)", "Perry\n (Phineas și Ferb)", "Dr. Doofenshmirtz\n (Phineas și Ferb)",
                    "Isabella\n (Phineas și Ferb)", "Jeremy\n (Phineas și Ferb)", "Fred Flintstone", "Wilma Flintstone",
                    "Tom motanul", "Mickey Mouse", "Minnie Mouse", "Mowgli", "Mr. Bean", "Măgărușul\n (Shrek)", "Shrek",
                    "Pantera Roz", "Pluto\n (Disney)", "Popeye Marinarul", "Fiona\n (Shrek)", "Scooby Doo", "Superman",
                    "Ursul Yogi", " Wile E. Coyote", "SpongeBob", "Eric Cartman\n (SouthPark)", "Daffy Duck",
                    "Porky Pig", "Beavis și Butthead", "Betty Boop", "Underdog", "Ursul Winnie", "Donald Duck",
                    "Alvin\n (Alvin și veverițele)", "Blossom\n (Fetițele Powerpuff)", "Bubbles\n (Fetițele Powerpuff)",
                    "Buttercup\n (Fetițele Powerpuff)", "Spider-Man", "Finn\n (Adventure time)",
                    "Câinele Jake\n (Adventure time)", "Stewie\n (Family Guy)", "Peter Griffin\n (Family Guy)",
                    "Lois\n (Family Guy)", "Kyle\n (SouthPark)", "Mordecai\n (Adventure time)", "Marge Simpson",
                    "Olive (Popeye)", "Sylvester", "Courage - Câinele fricos", "Baloo", "Bagheera", "Maleficent",
                    "Samurai Jack", "Mojo Jojo\n (Fetițele Powerpuff)", "Him\n (Fetițele Powerpuff)", "Darkwing Duck",
                    "Lisa Simpson", "Timon", "Pumba", "Top Cat", "Manny (Ice Age)", "Woody (Toy Story)",
                    "Sebastian\n (Mica Sirenă)", "Aladdin", "Duhul lui Aladdin", "Jasmine (Aladdin)", "Cenusăreasa",
                    "Cruella de Ville", "Tweety", "Taz", "Ed, Edd si Eddy", "Johnny Bravo", "Super Mario",
                    "Inspector Gadget", "Peter Pan", "Cow and Chicken", "Dexter", "Dee Dee", "Marțianul Marvin",
                    "Daria", "Ariel", "Tarzan", "Rapunzel", "Pocahontas", "Pinocchio", "Nemo",
                    "Jessie\n (Copii de la 402)", "Polly\n (Copii de la 402)", "Penny\n (Copii de la 402)",
                    "Nancy\n (Copii de la 402)", "Vinnie\n (Copii de la 402)", "Freddie\n (Copii de la 402)",
                    "Domnișoara Gracie (Copii de la 402)", "Pisoiul Eek", "Susan și Mary\n (Johnny Test)",
                    "Eugen - Bling Bling Boy", "Andy\n (Ce-i cu Andy)", "Lori\n (Ce-i cu Andy)",
                    "Danny\n (Ce-i cu Andy)", "Ben 10", "Beetlejuice", "Chowder", "Gomez Addams", "Morticia Addams",
                    "Jim Button", "Kim Possible", "Lilo", "Stitch", "Thomas train", "Naruto", "Pucca",
                    "Ash\n (Pokemon)", "Echipa Racheta\n (Pokemon)", "Pikachu\n (Pokemon)", "Iron-Man");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }

/*        if (MainActivity.getCategorie().equals("Celebritati")) {
            tip.setVisibility(View.VISIBLE);
            List<String> listAuxiliara = Arrays.asList("Cătălin Măruță", "Inna", "Alexandra Stan", "Mircea Badea",
                    "Cristi Minculescu", "Lucian Mândruță", "Traian Băsescu", "Maia Morgenstern", "Stela Popescu",
                    "Florin Călinescu", "Puiu Călinescu", "Toma Caragiu", "Mircea Cărtărescu", "Nicolae Ceaușeșcu",
                    "Tudor Chirilă", "Cristian Chivu", "Maria Ciobanu", "Arsenie Boca", "Victor Ciutacu",
                    "Victor Ponta", "Nadia Comăneci", "Jean Constantin", "Nicu Covaci", "Eugen Cristea", "Ion Cristoiu",
                    "Nicolae Dică", "Mircea Diaconu", "Gheorghe Dinică", "Mircea Dinescu", "Leonard Doroftei",
                    "Dida Drăgan", "Andreea Esca", "Felicia Filip", "Mircea Geoană", "Tudor Gheorghe",
                    "Angela Gheorghiu", "Dorin Goian", "Nicolae Guță", "Gheorghe Hagi", "Victor Hănescu", "Ion Iliescu",
                    "Mugur Isărescu", "Sandra Izbașa", "Klauss Iohannis", "Laura Lavric", "Anna Lesko", "Mihai Leu",
                    "Gabriel Liiceanu", "Bogdan Lobonț", "Nicoleta Luciu", "Irina Loghin", "Monica Macovei",
                    "Carla's Dreams", "Horațiu Mălăele", "Mădălina Manole", "Andreea Marin", "Marius Moga",
                    "Andi Moisescu", "Mihai Morar", "Cristian Mungiu", "Mona Muscă", "Adrian Mutu", "Ilie Năstase",
                    "Adrian Năstase", "Sorin Oprescu", "Teodor Paleologu", "Adrian Păunescu", "Andrei Pavel",
                    "Amza Pellea", "Gică Petrescu", "Dan Petrescu", "Florin Piersic", "Andrei Pleșu", "Victor Ponta",
                    "Silviu Prigoană", "Mihaela Rădulescu", "Andreea Raicu", "Dem Rădulescu", "Victor Rebengiuc",
                    "Paula Seling", "Vasile Șeicaru", "Pavel Stratan", "Gabriela Szabo", "Maria Tănase",
                    "Corneliu Vadim", "Robert Turcescu", "Elena Udrea", "Liviu Vasilică", "Mircea Vintilă",
                    "Lucian Viziru", "Gheorghe Zamfir", "Guess Who", "Loredana Groza", "Alina Sorescu", "Cabral Ibacka",
                    "Dan C. Mihăilescu", "Dan Negru", "Gabriela Firea", "Lucian Mîndruță", "Lucian Viziru",
                    "Mihai Gâdea", "Mihai Găinușă", "Oreste Teodorescu", "Șerban Huidu", "Teo Trandafir",
                    "Victor Ciutacu", "Bianca Drăgușanu", "Mircea Radu", "Radu Banciu", "Mihai Morar", "Smiley",
                    "Victor Slav", "Luminița Anghel", "Anda Adam", "Andra", "Andreea Bălan", "Ștefan Bănică",
                    "Ducu Bertzi", "Dan Bittman", "Florin Chilian", "Gil Dobrică", "Adrian Enache", "Felicia Filip",
                    "Tudor Gheorghe", "Ștefan Hrușcă", "Fuego", "Cătălin Josan", "Ovidiu Komornyik", "Sanda Ladoși",
                    "Irina Loghin", "Mădălina Manole", "Mihai Mărgineanu", "Ada Milea", "Horia Moculescu",
                    "Marius Moga", "Florian Pittiș", "Vasile Șeicaru", "Mihai Trăistariu", "Aura Urziceanu", "Randi",
                    "Delia Matache", "Cheloo", "Ombladon", "DOC", "Deliric", "Sava Negrean", "Margareta Clipa",
                    "Aurel Tămaș", "Horia Brenciu", "CRBL", "Adrian Mutu", "Bogdan Stelea", "Marius Tucă",
                    "Lidia Buble", "Liviu Teodorescu", "Feli Donose", "Radu Vâlcan", "Dani Oțil", "Mihai Bendeac",
                    "Andreea Berecleanu", "Andreea Mantea", "Cătălin Scărlătescu", "Cosmin Seleși", "Connect-R",
                    "Daniela Crudu", "Florin Dumitrescu", "Jorge", "Liviu Vârciu", "Mihai Petre", "Ozana Barabancea",
                    "Pepe", "Simona Gherghe", "Florin Busuioc", "Pavel Bartoș", "Bebe Cotimanis", "Adi Hădean",
                    "Mihai Bobonete", "Dani Mocanu", "Monica Columbeanu", "Romeo Fantastik");
            List<String> listAuxiliara2 = Arrays.asList("(prezentator TV)", "(cântăreață)", "(cântăreață)",
                    "(prezentator TV)", "(cântăreț)", "(prezentator TV)", "(politician)", "(actriță)", "(actriță)",
                    "(prezentator TV)", "(actor)", "(actor)", "(scriitor)", "(politician)", "(cântăreț)", "(fotbalist)",
                    "(cântăreață)", "(călugăr)", "(prezentator TV)", "(politician)", "(gimnastă)", "(actor)",
                    "(cântăreț)", "(actor)", "(prezentator TV)", "(fotbalist)", "(actor)", "(actor)", "(poet)",
                    "(boxeur)", "(cântăreață)", "(prezentatoare TV)", "(cântăreață)", "(politician)", "(cântăreț)",
                    "(cântăreață)", "(fotbalist)", "(cântăreț)", "(fotbalist)", "(tenismen)", "(politician)",
                    "(politician)", "(gimnastă)", "(politician)", "(cântăreață)", "(cântăreață)", "(pilot)",
                    "(scriitor)", "(fotbalist)", "(prezentatoare TV)", "(cântăreață)", "(politician)", "(cântăreț)",
                    "(actor)", "(cântăreață)", "(prezentatoare TV)", "(cântăreț)", "(prezentator TV)",
                    "(prezentator TV)", "(regizor)", "(politician)", "(fotbalist)", "(tenismen)", "(politician)",
                    "(politician)", "(politician)", "(poet)", "(tenismen)", "(actor)", "(cântăreț)", "(fotbalist)",
                    "(actor)", "(scriitor)", "(politician)", "(politician)", "(prezentatoare TV)", "(prezentatoare TV)",
                    "(actor)", "(actor)", "(cântăreață)", "(cântăreț)", "(cântăreț)", "(atletă)", "(cântăreață)",
                    "(politician)", "(prezentator TV)", "(politician)", "(cântăreț)", "(cântăreț)", "(actor)",
                    "(cântăreț)", "(cântăreț)", "(cântăreață)", "(cântăreață)", "(prezentator TV)", "(prezentator TV)",
                    "(prezentator TV)", "(politician)", "(prezentator TV)", "(actor)", "(prezentator TV)",
                    "(prezentator TV)", "(prezentator TV)", "(prezentator TV)", "(prezentatoare TV)",
                    "(prezentator TV)", "(prezentatoare TV)", "(prezentator TV)", "(prezentator TV)",
                    "(prezentator TV)", "(cântăreț)", "(prezentator TV)", "(cântăreață)", "(cântăreață)",
                    "(cântăreață)", "(cântăreață)", "(cântăreț)", "(cântăreț)", "(cântăreț)", "(cântăreț)",
                    "(cântăreț)", "(cântăreț)", "(cântăreață)", "(cântăreț)", "(cântăreț)", "(cântăreț)", "(cântăreț)",
                    "(cântăreț)", "(cântăreață)", "(cântăreață)", "(cântăreață)", "(cântăreț)", "(cântăreață)",
                    "(cântăreț)", "(cântăreț)", "(cântăreț)", "(cântăreț)", "(cântăreț)", "(cântăreață)", "(cântăreț)",
                    "(cântăreață)", "(cântăreț)", "(cântăreț)", "(cântăreț)", "(cântăreț)", "(cântăreață)",
                    "(cântăreață)", "(cântăreț)", "(cântăreț)", "(cântăreț)", "(fotbalist)", "(fotbalist)",
                    "(prezentator TV)", "(cântăreață)", "(cântăreț)", "(cântăreață)", "(prezentator TV)",
                    "(prezentator TV)", "(prezentator TV)", "(prezentatoare TV)", "(prezentatoare TV)",
                    "(prezentator TV)", "(prezentator TV)", "(cântăreț)", "(prezentatoare TV)", "(bucătar)",
                    "(cântăreț)", "(prezentator TV)", "(prezentator TV)", "(prezentatoare TV)", "(cântăreț)",
                    "(prezentatoare TV)", "(prezentator TV)", "(prezentator TV)", "(prezentator TV)", "(bucătar)",
                    "(actor)", "(cântăreț)", "(fotomodel)", "(cântăreț)");
            lista.addAll(listAuxiliara);
            lista2.addAll(listAuxiliara2);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            if (index <= lista2.size()) {
                tip.setText(lista2.get(index));
            } else {
                tip.setText("");
            }

            listaRezultateString.add(lista.get(index));
            lista.remove(index);
            lista2.remove(index);
        }*/

        if (MainActivity.getCategorie().equals("Proverbe")) {
            List<String> listAuxiliara = Arrays.asList("A ierta e uşor, a uita e greu", "Amânarea e hoţul timpului",
                    "A ajunge cuțitul la os", "A avea ac de cojocul cuiva", "A băga mâna în foc pentru ceva",
                    "A bate apa-n piuă", "Bate fierul cât e cald", "A bate găina cu lanțul", "A călca pe bec",
                    "A cânta cuiva în strună", "Aceași Mărie cu altă pălărie", "A da bir cu fugiții",
                    "A da perle la porci", "A despica firul în patru", "A duce cu preșul",
                    "Adevărul este întotdeauna la mijloc", "A face ochi dulci cuiva",
                    "A face pe cineva cu ou și cu oțet", "A face umbră pământului degeaba", "A fi cu ochii în patru",
                    "A fi prins cu cioara vopsită", "Ai carte, ai parte", "A dat-o cotită",
                    "A-i fi frică și de umbra sa", "A împăca capra cu varza", "A împușca doi iepuri dintr-un foc",
                    "A turna gaz pe foc", "A pune carul înaintea boilor", "Apa trece, pietrele rămân",
                    "Așchia nu sare departe de trunchi", "Surdul n-aude, dar le potrivește", "Banu-i ochiul dracului",
                    "Bate fierul cât e cald", "Bătrânețe, haine grele", "Bine faci, bine găsești",
                    "Buturuga mică răstoarnă carul mare", "Calul de dar nu se caută la dinți",
                    "Capra sare masa, iada sare casa", "Capul plecat sabia nu-l taie",
                    "Când doi se ceartă al treilea câștigă", "Când pisica nu-i acasă, șoarecii joacă pe masă",
                    "Câinele care latră nu mușcă", "Călătorului îi șade bine cu drumul", "Câte bordeie, atâtea obiceie",
                    "Ce-am avut și ce-am pierdut!", "Ce ție nu-ți place, altuia nu-i face", "Ce-i în gușă și-n căpușă",
                    "Ce-i în mână nu-i minciună", "Cele rele sâ se spele, cele bune să se-adune",
                    "Cine râde la urmă râde mai bine", "Cine nu are un bătrân, să își cumpere",
                    "Cine nu-ncearcă, nici nu câștigă", "Cine sapă groapa altuia, cade singur în ea",
                    "Cine se frige cu ciorba, suflă și în iaurt", "Cine seamănă vânt, culege furtună",
                    "Cine se aseamănă, se adună", "Cine se scoală de dimineață, departe ajunge",
                    "Cine se scuză, se acuză", "Copilul și bețivul spun adevărul", "Corb la corb nu scoate ochii",
                    "Cu o floare nu se face primăvară", "Cui pe cui scoate", "Cu un ochi râde și cu altul plânge",
                    "Cum e turcul și pistolul", "Dacă n-ajunge, mai rămâne", "Dacă tăceai, filosof rămâneai",
                    "Dar din dar se face rai", "Dați cezarului, ce-i al cezarului", "După mine, potopul!",
                    "După război mulți viteji se arată", "Excepția confirmă regula", "Fă bine și așteaptă rău",
                    "Ferește-te de proști, pentru că au mintea odihnită", "Fă-te frate cu dracul până treci puntea",
                    "Ferește-te de câinele mut și de omul tăcut", "Foamea e cel mai bun bucătar",
                    "Frate, frate, dar brânza e pe bani", "Fuga e rușinoasă, dar sănătoasă",
                    "Găina bătrână face supa bună", "Gând la gând cu bucurie", "Graba strică treaba",
                    "Gura bate fundul", "Haina nu face pe om", "Iarba rea nu piere", "Încet, încet, departe ajungi",
                    "În țara orbilor, chiorul este împărat", "Jarul potolit te arde",
                    "La plăcinte înainte, la război înapoi", "La pomul lăudat să nu te duci cu sacul",
                    "În tot răul este și un bine", "La omul sărac, nici boii nu trag",
                    "Lauda de sine nu miroase a bine", "Lenea e cucoană mare", "Lăcomia pierde omenia",
                    "Lăcusta nu are milă de bucate", "Lucrul, odată început, e pe jumătate făcut", "Lupul lup rămâne",
                    "Lupu-și schimbă părul, dar năravul ba", "Mai bine mai târziu decât niciodată",
                    "Mamă soacră, poamă acră", "Măgarul duce vinul și bea apă", "Mărul putred strică și merele bune",
                    "Mătura nouă mătură bine", "Mâța blândă zgârie rău", "Meseria e brățară de aur",
                    "Mi-e milă de tine, dar de mine mi se rupe inima", "Minciuna are picioare scurte",
                    "Mortul de la groapă nu se întoarce", "Nemulțumitului i se ia darul", "Nevoia îl învață pe om",
                    "Nimeni nu e profet în țara lui", "Nu aduce anul ce aduce ora", "Nu e dracul așa de negru",
                    "Nu este pădure fără uscături", "Nu iese fum fără foc", "Nu lăsa pe mâine ce poți face azi",
                    "Nu mor caii când vor câinii", "Nu lăuda ziua înainte de asfințit",
                    "Nu știe stânga ce face dreapta", "Nu toate muștele fac miere", "Nu tot ce zboară se mănâncă",
                    "Nu-ți băga nasul unde nu-ți fierbe oala", "Nu-ți dori, că ți se va întâmpla",
                    "Nu vede pădurea de copaci", "Nu zice hop până n-ai sărit groapa",
                    "Prostul râde de ce își aduce aminte", "Ochii care nu se văd se uită",
                    "Ochii sunt oglinda sufletului", "Ochii verzi niciodată să nu-i crezi",
                    "Omul e măsura tuturor lucrurilor", "Omul sfințește locul", "Paza bună trece primejdia rea",
                    "Pofta vine mâncând", "Prietenul bun e ca o pungă de bani", "Prietenul la nevoie se cunoaște",
                    "Prostul moare de grija altuia", "Pe cine nu lași să moară, nu te lasă să trăiești",
                    "Peștele de la cap se-mpute", "Pică pară mălăiață în gura nătăfleață",
                    "Prea puțin ca să trăiești, prea mult ca să mori", "Prost să fii, noroc să ai",
                    "Puneți frâu la gură și lacăt la inimă", "Prostul nu e prost destul, dacă nu e și fudul",
                    "Răbdarea trece marea", "Răzbunarea e arma prostului", "Râde ciob de oală spartă",
                    "Rușinosul roade osul", "S-a dus bou și a venit vacă", "Sângele apă nu se face",
                    "Scopul scuză mijloacele", "Stăpânul zgârcit învață sluga hoață", "Și tăcerea e un răspuns",
                    "Tăcerea e de aur", "Toamna se numără bobocii", "Toate drumurile duc la Roma",
                    "Țara arde și baba se piaptănă", "Ulciorul nu merge de multe ori la apă", "Unde dai și unde crapă",
                    "Unde se cioplește, sar și așchii", "Unde-s mulți, puterea crește", "Urma scapă turma",
                    "Vorba dulce mult aduce", "Vorba lungă, sărăcia omului", "Vorbești de lup și lupul la ușă",
                    "Ziua bună se cunoaște de dimineață");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            cuvant.setTextSize(48);
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }

        if (MainActivity.getCategorie().equals("Capitale")) {
            List<String> listAuxiliara = Arrays.asList("Austria\n-Viena-", "Anglia\n–Londra-", "Belarus\n–Minsk-",
                    "Belgia\n–Bruxelles-", "Bosnia-Herţegovina\n–Sarajevo-", "Bulgaria\n–Sofia-", "Cehia\n–Praga-",
                    "Cipru\n–Nicosia-", "Croaţia\n–Zagreb-", "Danemarca\n–Copenhaga-", "Elveţia\n–Berna-",
                    "Estonia\n–Tallinn-", "Finlanda\n–Helsinki-", "Franţa\n–Paris-", "Germania\n–Berlin-",
                    "Grecia\n–Atena-", "Irlanda\n–Dublin-", "Islanda\n–Reykjavik-", "Italia\n–Roma-",
                    "Kosovo\n–Priştina-", "Letonia\n–Riga-", "Liechtenstein\n–Vaduz-", "Lituania\n–Vilnius-",
                    "Luxemburg\n–Luxemburg-", "Macedonia\n–Skopje-", "Malta\n–Valletta-", "Moldova\n–Chişinău-",
                    "Monaco\n–Monaco-", "Muntenegru\n–Podgorica-", "Norvegia\n–Oslo-", "Olanda\n–Amsterdam / Haga-",
                    "Polonia\n–Varşovia-", "Portugalia\n–Lisabona-", "România\n–Bucureşti-", "Rusia\n–Moscova-",
                    "San Marino\n–San Marino-", "Serbia\n–Belgrad-", "Slovacia\n–Bratislava-", "Slovenia\n–Ljubljana-",
                    "Spania\n–Madrid-", "Suedia\n–Stockholm-", "Turcia\n–Ankara-", "Ucraina\n–Kiev-",
                    "Ungaria\n–Budapesta-", "Vatican\n–Vatican-", "Albania\n–Tirana-", "Andorra\n–Andorra la Vella-",
                    "Irlanda de Nord\n-Belfast-", "Scotia\n–Edinburgh-", "Tara Galilor\n-Cardiff-",
                    "Liechestein\n–Vaduz-", " Insulele Feroe\n–Tórshavn-", "Coreea de Sud\n-Seul-",
                    "Coreea de Nord\n-Phenian-", "Vietnam\n-Hanoi-", "Laos\n-Vientiane-", "Thailanda\n-Bangkok-",
                    "Cambodgia\n-Phnom Penh-", "Singapore\n-Singapore-", "Bangladesh\n-Dhaka-", "Nepal\n-Katmandu-",
                    "Sri Lanka\n-Colombo-", "Tadjikistan\n-Dusanbe-", "Kirghizstan\n-Biskek-", "Uzbekistan\n-Taskent-",
                    "Azerbaidjan\n-Baku-", "Georgia\n-Tbilisi-", "Armenia\n-Erevan-", "Cipru\n-Nicosia-",
                    "Liban\n-Beirut-", "Israel\n-Tel Aviv-", "Lordania\n-Amman-", "Kuwait\n-Al Kuwait-",
                    "Qatar\n-Doha-", "Bahrain\n-Manama-", "Emiratele Arabe Unite\n-Abu Dhabi-", "Siria\n-Damasc-",
                    "Turkmenistan\n-Ashabad-", "Pakistan\n-Islamabad-", "India\n-New Delhi-",
                    "Arabia Saudita\n-Ar-Riyadh-", "Yemen\n-Sanaa-", "Oman\n-Mascat-", "Turcia\n-Ankara-",
                    "Iran\n-Teheran-", "Afghanistan\n-Kabul-", "Malaysia\n-Kuala Lumpur-", "Indonesia\n-Jakarta-",
                    "Japonia\n-Tokio-", "China\n-Beijing-", "Mongolia\n-Ulan Bator-", "Kazakhstan\n-Astana-",
                    "Argentina\n–Buenos Aires-", "Bolivia\n–La Paz-", "Brazilia\n–Brasil-",
                    "Chile\n–Santiago de Chile-", "Colombia\n–Bogota-", "Ecuador\n–Quito-", "Guiana Franceza\n–Cyanne-",
                    "Paraguay\n–Asuncio-", "Peru\n–Lima-", "Uruguay\n–Montevideo-", "Venezuela\n–Caracas-",
                    "Bahamas\n–Nassau-", "Barbados\n–Bridgetown-", "Canada\n–Ottawa-", "Costa Rica\n–San Jose-",
                    "Cuba\n–Havana-", "Republica Dominicana\n–Santo Domingo-", "El Salvador\n–San Salvador-",
                    "Guatemala\n–Ciudad de Guatemala-", "Haiti\n–Port-au-Prince-", "Honduras\n–Tegucigalpa-",
                    "Jamaica\n–Kingston-", "Mexic\n–Ciudad de Mexico-", "Nicaragua\n–Managua-",
                    "Panama\n–Ciudad de Panama-", "S.U.A.\n–Washington-", "Algeria\n–Alger-", "Angola\n–Luanda-",
                    "Botswana\n–Gaborone-", "Burkina Faso\n–Ouagadougou-", "Camerun\n–Yaounde-",
                    "Republica Congolează\n–Brazzaville-", "Egipt\n–Cairo-", "Etiopia\n-Addisa Ababa",
                    "Gabon\n–Libreville-", "Ghana\n–Accra-", "Kenya\n–Nairobi-", "Lesotho\n–Maseru-",
                    "Libia\n–Tripoli-", "Madagascar\n–Antananarivo-", "Maroc\n–Rabat-", "Mozambic\n–Maputo-",
                    "Nigeria\n–Abuja-", "Rwanda\n–Kigali-", "Senegal\n–Dakar-", "Somalia\n–Mogadishu-",
                    "Tunisia\n–Tunis-", "Australia\n–Canberra-", "Noua Zeelanda\n–Wellington-", "Tuvalu\n–Funafuti-",
                    "Samoa\n–Apia-");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }

        if (MainActivity.getCategorie().equals("Sporturi")) {
            List<String> listAuxiliara = Arrays.asList("Aikido", "Alpinism", "Atletism", "Arte marțiale",
                    "Automobilism", "Badminton", "Baschet", "Baseball", "Biatlon", "Biliard", "Bob", "Box", "Bowling",
                    "Bridge", "Caiac-canoe", "Canotaj", "Carting", "Călărie", "Ciclism", "Cricket", "Culturism",
                    "Curling", "Dans", "Darts", "Echitație", "Fotbal", "Fotbal american", "Fotbal tenis", "Formula 1",
                    "Futsal", "Gimnastică", "Go", "Golf", "Haltere", "Handbal", "Hochei pe gheață", "Hochei pe iarbă",
                    "Înot", "Judo", "Karting", "Karate", "Kick-Box", "Kitesurfing", "Lupte", "Motociclism", "Natație",
                    "Oină", "Planorism", "Parapantism", "Parașutism", "Patinaj artistic", "Patinaj viteză", "Paintball",
                    "Pentatlon", "Pescuit", "Polo", "Popice", "Raliu", "Rugby", "Sanie", "Sărituri cu schiurile",
                    "Scrabble", "Scrimă", "Scufundări", "Skeleton", "Snowboarding", "Snooker", "Speologie", "Sumo",
                    "Șah", "Schi", "Surf", "Tae Bo", "Taekwondo", "Tenis de câmp", "Tenis de masă", "Tir sportiv",
                    "Tir cu arcul", "Triatlon", "Volei", "Vânătoare", "Wrestling", "Yachting", "Squash",
                    "Aruncarea discului‎", "Aruncarea greutății", "Aruncarea suliței", "Bârnă", "Cal cu mânere",
                    "Inele (gimnastică)", "Paralele (gimnastică)", "Sol (gimnastică)", "Sărituri (gimnastică)",
                    "Jujitsu", "Rodeo", "Skateboarding", "Cățărat", "Downhill", "Marș", "Rafting", "Drifting",
                    "Bungee jumping", "Triplu salt", "Săritura cu prăjina", "Săritura în înălțime",
                    "Săritura în lungime");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }

        if (MainActivity.getCategorie().equals("Emotii")) {
            List<String> listAuxiliara = Arrays.asList("Tristeţe", "Descurajare", "Deprimare", "Bucurie", "Plăcere",
                    "Excitare", "Dezgust", "Neîncredere", "Nefericire", "Durere", "Frustrare", "Solitudine", "Frică",
                    "Furie", "Vinovăţie", "Depresie", "Mândrie", "Gelozie", "Auto-compătimire", "Anxietate", "Invidie",
                    "Ruşine", "Apreciere", "Speranţă", "Fericire", "Entuziasm", "Vitalitate", "Încredere",
                    "Recunoştinţă", "Jale", "Răbdare", "Indignare", "Irascibilitate", "Ostilitate", "Mâhnire",
                    "Melancolie", "Îmbufnare", "Singurătate", "Disperare", "Preocupare", "Neînţelegere", "Îngrijorare",
                    "Spaimă", "Uşurare", "Mulţumire", "Binecuvântare", "Acceptare", "Afinitate", "Devotament",
                    "Adoraţie", "Aversiune", "Repulsie", "Remuşcare", "Ură", "Groază", "Fobie", "Panică", "Extaz",
                    "Milă", "Pasiune", "Dorinţă sexuală", "Iritare", "Exasperare", "Vigilență", "Plictiseală",
                    "Meditație", "Uimire", "Aniticipație", "Supunere", "Optimism", "Pesimism", "Dezaprobare",
                    "Tensiune", "Nonșalanță", "Sensibilitate", "Aroganță", "Cinism", "Ipocrizie", "Sadism", "Încântare",
                    "Nerăbdare", "Suspiciune", "Ofensare", "Scepticism", "Scârbă", "Tulburare", "Amărăciune",
                    "Suferinţă", "Grijă", "Insatisfacţie", "Satisfacţie", "Mânie", "Teamă", "Ostilitatea", "Antipatie",
                    "Silă");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }

        if (MainActivity.getCategorie().equals("Casnice")) {
            List<String> listAuxiliara = Arrays.asList("Capsator", "Pistol de lipit", "Ac de cusut", "Capse", "Cutter",
                    "Cuțit", "Fierăstrău", "Bormașină", "Pilă", "Perie de sârmă", "Daltă", "Fulculiță", "Șmirghel",
                    "Menghină", "Clește", "Clemă de păr", "Șurubelniță", "Imbus", "Șlefuitor", "Lingură",
                    "Mașină de spălat", "Mașină de cusut", "Foarfecă", "Metru croitorie", "Ruletă", "Fier de călcat",
                    "Multimetru", "Clește de sertizat", "Pensetă", "Scară", "Roabă", "Mătură", "Făraș", "Găleată",
                    "Mop", "Prelungitor", "Furtun", "Trusă de prim ajutor", "Priză", "Bec", "Tastatură", "Mouse",
                    "Ceas de perete", "Boxe audio", "Căști audio", "Șifonier", "Canapea", "Fotoliu", "Scaun", "Debara",
                    "Borcan", "Damigeană", "Săpun lichid", "Bibelou", "Televizor", "Monitor", "Aspirator", "Pieptene",
                    "Șorț de bucătărie", "Mănușă de bucătărie", "Papuci de casă", "Șosete", "Fustă", "Rochie",
                    "Chiloți", "Draperie", "Jaluzea", "Uscător de rufe", "Masă de călcat", "Tigaie", "Ceainic", "Ibric",
                    "Farfurie", "Castron", "Sac de gunoi", "Coș de gunoi", "Chiuvetă", "Aragaz", "Cuptor cu microunde",
                    "Icoană", "Aparat de AC", "Tablou", "Lustră", "Veioză", "Telefon mobil", "Tabletă", "Cămașă",
                    "Calorifer", "Oglindă", "Bibliotecă", "Oală", "Strecurătoare", "Răzătoare", "Storcător de fructe",
                    "Mixer", "Covor", "Gresie", "Faianță", "Hotă", "Ochelari de soare", "Ochelari de vedere", "Binoclu",
                    "Apă de toaletă", "Brățară", "Noptieră", "Cuier", "Birou", "Pat", "Pian", "Cântar", "Radiator",
                    "Radio", "Pătură", "Mochetă", "Pilotă", "Saltea", "Pernă", "Cearșaf", "Prosop", "Umeraș", "Clanță",
                    "Burete de vase", "Săpun", "Prăjitor de pâine", "Cană", "Pahar", "Tirbușon", "Polonic", "Sită",
                    "Tel", "Uscător de vase", "Grătar", "Față de masă", "Lumânare", "Chibrite", "Lipici", "Brichetă",
                    "Bandă adezivă", "Timbru", "Stilou", "Creion", "Hârtie igienică", "Pastă de dinți",
                    "Balsam de rufe", "Fotografie", "Frigider", "Sobă", "Vază", "Umnbrelă", "Masă", "Cadă", "Cafetieră",
                    "Rucsac", "Uscător de păr", "Placă de intins părul", "Ondulator", "Epilator", "Cărți de joc",
                    "Laptop", "Calendar", "Blender", "Râșniță de cafea", "Fructieră", "Penar", "Ceas", "Cheie",
                    "Telecomandă", "Unghieră", "Parchet", "Ghiveci", "Pantaloni", "Robinet", "Încărcător de telefon",
                    "Platou", "Baldachin", "Lupă", "Creaioane colorate", "Consolă de jocuri", "Pantofi cu toc", "Inel",
                    "Verighetă", "Cercei", "Medalion", "Scobitori", "Aparat de ras", "Mașină de tuns", "Role",
                    "Chitară", "Scrumieră", "Microfon", "Cameră web", "Maiou", "Bătător de covoare", "Acvariu",
                    "Mojar");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }

        if (MainActivity.getCategorie().equals("Rock")) {
            List<String> listAuxiliara = Arrays.asList("Led Zeppelin", "The Beatles", "Pink Floyd", "Queen",
                    "Metallica", "AC/DC", "The Rolling Stones", "Guns N' Roses", "Nirvana", "The Who", "Linkin Park",
                    "Green Day", "Black Sabbath", "Rush", "Iron Maiden", "Red Hot Chili Peppers", "Aerosmith",
                    "Jimi Hendrix", "U2", "The Doors", "Van Halen", "Bon Jovi", "Foo Fighters", "Radiohead",
                    "Pearl Jam", "Muse", "30 Seconds to Mars", "Deep Purple", "System of a Down", "Eagles", "Journey",
                    "Breaking Benjamin", "Alice in Chains", "Blink-182", "Judas Priest", "Dream Theater",
                    "Lynyrd Skynyrd", "Creedence Clearwater Revival", "Kiss", "Megadeth", "Coldplay", "The Offspring",
                    "Bruce Springsteen", "My Chemical Romance", "Disturbed", "Scorpions", "Oasis", "Evanescence",
                    "Def Leppard", "Smashing Pumpkins", "Arctic Monkeys", "Korn", "Soundgarden", "The Verve",
                    "Eric Clapton", "Sting", "Jethro Tull", "Pantera", "Genesis", "Rage Against the Machine",
                    "Dire Straits", "Sum 41", "Alice Cooper", "Marilyn Manson", "The Killers", "Slipknot",
                    "The White Stripes", "Panic! at the Disco", "Rammstein", "Nine Inch Nails", "Ronnie James Dio",
                    "The Rasmus", "R.E.M.", "Incubus", "Imagine Dragons", "Sex Pistols", "Pixies",
                    "Queens of the Stone Age", "Frank Zappa", "Duran Duran", "ZZ Top", "Anthrax", "Motörhead", "Slayer",
                    "Godsmack", "Kasabian", "Whitesnake", "Rob Zombie", "Audioslave", "The Cure", "Placebo",
                    "Apocalyptica", "Nightwish", "Blue Öyster Cult", "Twenty One Pilots", "Lacuna Coil", "Nickelback",
                    "Within Temptation", "Black Label Society", "Motley Crue", "Europe", "A-ha", "Manowar", "Nick Cave",
                    "Uriah Heep", "Meat Loaf", "Bathory", "Cradle of Filth", "Anathema", "Arch Enemy", "Opeth",
                    "The Animals", "Jim Morrison", "The Cranberries", "David Bowie", "Bob Dylan", "Elton John",
                    "John Lennon", "Ozzy Osburne", "Slash", "Janis Joplin");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }

        if (MainActivity.getCategorie().equals("Masini")) {
            List<String> listAuxiliara = Arrays.asList("Ford Focus",
                    "Ford Fiesta",
                    "Ford Escort",
                    "Ford Ka",
                    "Ford Mondeo",
                    "Ford Fusion",
                    "Ford Taurus",
                    "Focus Mustang",
                    "Ford GT",
                    "Ford B-Max",
                    "Ford C-Max",
                    "Ford Kuga",
                    "Ford Flex",
                    "Ford Edge",
                    "Ford Expedition",
                    "Ford Explorer",
                    "Ford Transit",
                    "Fiat Bravo",
                    "Fiat Punto",
                    "Fiat 500",
                    "Fiat Panda",
                    "Fiat Uno",
                    "Fiat Tipo",
                    "Fiat Freemont",
                    "Fiat Linea",
                    "Fiat Sedici",
                    "Fiat Albea",
                    "Fiat Idea",
                    "Dacia Duster",
                    "Dacia Sandero",
                    "Dacia 1300",
                    "Dacia Solenza",
                    "Dacia Lodgy",
                    "Dacia Logan",
                    "Dacia Dokker",
                    "Dacia Nova",
                    "Hyundai i10",
                    "Hyundai i20",
                    "Hyundai i30",
                    "Hyundai i40",
                    "Hyundai Elantra",
                    "Hyundai Tucson",
                    "Hyundai ix35",
                    "Hyundai Sonata",
                    "Hyundai Genesis",
                    "Hyundai Santa Fe",
                    "Honda Accord",
                    "Honda Civic",
                    "Honda Jazz",
                    "Honda NSX",
                    "Mazda 2",
                    "Mazda 3",
                    "Mazda 6",
                    "Mazda MX-5",
                    "Mazda MX-3",
                    "Mazda CX-3",
                    "Mazda CX-5",
                    "Mazda CX-9",
                    "Mazda RX-8",
                    "Mitsubishi Lancer",
                    "Mitsubishi ASX",
                    "Mitsubishi Pajero",
                    "Mitsubishi L200",
                    "Skoda Yeti",
                    "Skoda Roomster",
                    "Skoda Superb",
                    "Skoda Rapid",
                    "Skoda Fabia",
                    "Skoda Mission",
                    "Range Rover Evoque",
                    "Range Rover Sport",
                    "Land Rover Discovery",
                    "Land Rover Freelander",
                    "Land Rover Defender",
                    "Land Rover Series",
                    "Volkswagen Beetle",
                    "Volkswagen Amarok",
                    "Volkswagen Caddy",
                    "Volkswagen Passat CC",
                    "Volkswagen Passat",
                    "Volkswagen Golf",
                    "Volkswagen Jetta",
                    "Volkswagen Scirocco",
                    "Volkswagen Sharan",
                    "Volkswagen Tiguan",
                    "Volkswagen Touareg",
                    "Volkswagen Touran",
                    "Volkswagen Eos",
                    "Volkswagen Lupo",
                    "Volkswagen Fox",
                    "Volkswagen Up!",
                    "BMW Seria 1",
                    "BMW Seria 2",
                    "BMW Seria 3",
                    "BMW Seria 4",
                    "BMW Seria 5",
                    "BMW Seria 6",
                    "BMW Seria 7",
                    "BMW X1",
                    "BMW X3",
                    "BMW X5",
                    "BMW X6",
                    "Audi Quattro",
                    "Audi 80",
                    "Audi Coupe",
                    "Audi A3",
                    "Audi A4",
                    "Audi A2",
                    "Audi A6",
                    "Audi A7",
                    "Audi Q3",
                    "Audi Q5",
                    "Audi Q7",
                    "Audi TT",
                    "Audi A5",
                    "Mercedes Clasa A",
                    "Mercedes Clasa B",
                    "Mercedes Clasa C",
                    "Mercedes Clasa E",
                    "Mercedes Clasa G",
                    "Mercedes GLA",
                    "Mercedes GLE",
                    "Mercedes CLK",
                    "Mercedes SLK",
                    "Mercedes Citaro",
                    "Mercedes Vito",
                    "Citroën C1",
                    "Citroën C2",
                    "Citroën C3",
                    "Citroën C4",
                    "Citroën C5",
                    "Citroën C6",
                    "Citroën DS",
                    "Citroën Saxo",
                    "Citroën Xsara",
                    "Citroën C4 Cactus",
                    "Peugeot 206",
                    "Peugeot 207",
                    "Peugeot 208",
                    "Peugeot 307",
                    "Peugeot 308",
                    "Peugeot 407",
                    "Peugeot 508",
                    "Peugeot 2008",
                    "Peugeot 3008",
                    "Peugeot Partner",
                    "Renault Captur",
                    "Renault Fluence",
                    "Renault Kadjar",
                    "Renault Kangoo",
                    "Renault Megane",
                    "Renault Scenic",
                    "Renault Symbol",
                    "Renault Twingo",
                    "Renault Twizy",
                    "Renault Zoe",
                    "Renault Talisman",
                    "Renault Laguna",
                    "Opel Adam",
                    "Opel Ampera",
                    "Opel Astra",
                    "Opel Corsa",
                    "Opel Insignia",
                    "Opel Karl",
                    "Opel Meriva",
                    "Opel Mokka",
                    "Opel Zafira",
                    "Opel Astra OPC",
                    "Opel Kadett",
                    "Opel Vectra",
                    "Opel Signum",
                    "Ferrari Enzo",
                    "Ferrari LaFerrari",
                    "Ferrari F40",
                    "Ferrari F50",
                    "Lamborghini Gallardo",
                    "Lamborghini Miura",
                    "Lamborghini Murciélago",
                    "Lamborghini Diablo",
                    "Lamborghini Reventón",
                    "Lamborghini Aventador",
                    "Nissan Altima",
                    "Nissan Juke",
                    "Nissan Qashqai",
                    "Nissan X-Trail",
                    "Nissan Pathfinder",
                    "Nissan Murano",
                    "Nissan Note",
                    "Infiniti I35",
                    "Infiniti M56",
                    "Infiniti JX35",
                    "Infiniti Q50 S Hybrid",
                    "Lada Niva",
                    "Lada Kalina",
                    "Lada Vesta",
                    "Kia Cee'd",
                    "Kia Rio",
                    "Kia Picanto",
                    "Kia Forte",
                    "Kia Sportage",
                    "Kia Sorento",
                    "Seat Ibiza",
                    "Seat Cordoba",
                    "Seat Leon",
                    "Seat Altea",
                    "Seat Toledo",
                    "Seat Exeo",
                    "Aston Martin DB9",
                    "Aston Martin One-77",
                    "Aston Martin Cygnet",
                    "Aston Martin DB11",
                    "Rolls-Royce Ghost",
                    "Rolls-Royce Wraith",
                    "Rolls-Royce Phantom",
                    "Cadillac Deville",
                    "Cadillac Escalade",
                    "Chevrolet Spark",
                    "Chevrolet Bolt",
                    "Chevrolet Cruze",
                    "Chevrolet Malibu",
                    "Chevrolet Camaro",
                    "Chevrolet Equinox",
                    "Jeep Grand Cherokee",
                    "Jeep Wrangler",
                    "Jeep Patriot",
                    "Jeep Liberty",
                    "Alfa Romeo MiTo",
                    "Alfa Romeo Giulietta",
                    "Alfa Romeo Giulia",
                    "Alfa Romeo 4C",
                    "Alfa Romeo Spider"
            );
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }

        if (MainActivity.getCategorie().equals("Mortal")) {
            List<String> listAuxiliara = Arrays.asList("Liu Kang",
                    "Johnny Cage",
                    "Kano",
                    "Raiden",
                    "Scorpion",
                    "Sonya Blade",
                    "Sub-Zero",
                    "Goro",
                    "Shang Tsung",
                    "Reptile",
                    "Baraka",
                    "Jax",
                    "Kitana",
                    "Kung Lao",
                    "Mileena",
                    "Kintaro",
                    "Shao Kahn",
                    "Jade",
                    "Noob Saibot",
                    "Smoke",
                    "Cyrax",
                    "Sektor",
                    "Kabal",
                    "Nightwolf",
                    "Sindel",
                    "Sheeva",
                    "Stryker",
                    "Motaro",
                    "Ermac",
                    "Rain",
                    "Chameleon",
                    "Khameleon",
                    "Fujin",
                    "Jarek",
                    "Kai",
                    "Reiko",
                    "Tanya",
                    "Quan Chi",
                    "Shinnok",
                    "Meat",
                    "Blaze",
                    "Bo' Rai Cho",
                    "Drahmin",
                    "Frost",
                    "Hsu Hao",
                    "Kenshi",
                    "Li Mei",
                    "Mavado",
                    "Mokap",
                    "Moloch",
                    "Nitara",
                    "Sareena",
                    "Ashrah",
                    "Dairou",
                    "Darrius",
                    "Havik",
                    "Hotaru",
                    "Kira",
                    "Kobra",
                    "Onaga",
                    "Shujinko",
                    "Daegon",
                    "Taven",
                    "Skarlet",
                    "Cyber Sub-Zero",
                    "Cassie Cage",
                    "D'Vorah",
                    "Erron Black",
                    "Ferra/Torr",
                    "Jacqui Briggs",
                    "Kotal Kahn",
                    "Kung Jin",
                    "Takeda",
                    "Tremor",
                    "Tri-Borg"
            );
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }

        if (MainActivity.getCategorie().equals("Dota")) {
            List<String> listAuxiliara = Arrays.asList("Anti-Mage", "Axe", "Crystal Maiden", "Dazzle", "Drow Ranger",
                    "Earthshaker", "Lich", "Lina", "Lion", "Mirana", "Morphling", "Necrophos", "Puck", "Pudge", "Razor",
                    "Sand King", "Shadow Shaman", "Storm Spirit", "Sven", "Tidehunter", "Vengeful Spirit", "Windranger",
                    "Witch Doctor", "Zeus", "Slardar", "Enigma", "Faceless Void", "Tiny", "Viper", "Venomancer",
                    "Clockwerk", "Nature's Prophet", "Dark Seer", "Sniper", "Pugna", "Beastmaster", "Enchantress",
                    "Leshrac", "Shadow Fiend", "Tinker", "Night Stalker", "Ancient Apparition", "Spectre", "Doom",
                    "Chen", "Juggernaut", "Bloodseeker", "Kunkka", "Riki", "Queen of Pain", "Wraith King",
                    "Broodmother", "Huskar", "Jakiro", "Batrider", "Omniknight", "Dragon Knight", "Warlock",
                    "Alchemist", "Lifestealer", "Death Prophet", "Ursa", "Bounty Hunter", "Silencer", "Spirit Breaker",
                    "Invoker", "Clinkz", "Outworld Devourer", "Bane", "Shadow Demon", "Lycan", "Lone Druid",
                    "Brewmaster", "Phantom Lancer", "Treant Protector", "Ogre Magi", "Gyrocopter", "Chaos Knight",
                    "Phantom Assassin", "Rubick", "Luna", "Io", "Undying", "Disruptor", "Templar Assassin",
                    "Naga Siren", "Nyx Assassin", "Keeper of the Light", "Visage", "Meepo", "Magnus",
                    "Centaur Warrunner", "Slark ", "Timbersaw", "Medusa", "Troll Warlord", "Tusk", "Bristleback",
                    "Skywrath Mage", "Elder Titan", "Abaddon", "Ember Spirit", "Earth Spirit", "Legion Commander",
                    "Phoenix", "Terrorblade", "Techies", "Oracle", "Winter Wyvern", "Arc Warden", "Underlord",
                    "Monkey King", "Tango", "Dust of Appearance", "Flying Courier", "Animal Courier", "Sentry Ward",
                    "Observer Ward", "Bottle", "Gem of True Sight", "Morbid Mask", "Blink Dagger", "Arcane Boots",
                    "Mekansm", "Vladmir's Offering", "Dagon", "Aghanim's Scepter", "Refresher Orb", "Shadow Blade",
                    "Blade Mail", "Manta Style", "Heart of Tarrasque", "Black King Bar", "Satanic", "Hyperstone",
                    "Aegis of the Immortal", "Cheese", "Eul's Scepter of Divinity", "Desolator", "Roshan");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }


        if (MainActivity.getCategorie().equals("Lol")) {
            List<String> listAuxiliara = Arrays.asList("Aatrox",
                    "Ahri",
                    "Akali",
                    "Alistar",
                    "Amumu",
                    "Anivia",
                    "Annie",
                    "Ashe",
                    "Aurelion Sol",
                    "Azir",
                    "Bard",
                    "Blitzcrank",
                    "Brand",
                    "Braum",
                    "Caitlyn",
                    "Camille",
                    "Cassiopeia",
                    "Cho'Gath",
                    "Corki",
                    "Darius",
                    "Diana",
                    "Dr. Mundo",
                    "Draven",
                    "Ekko",
                    "Elise",
                    "Evelynn",
                    "Ezreal",
                    "Fiddlesticks",
                    "Fiora",
                    "Fizz",
                    "Galio",
                    "Gangplank",
                    "Garen",
                    "Gnar",
                    "Gragas",
                    "Graves",
                    "Hecarim",
                    "Heimerdinger",
                    "Illaoi",
                    "Irelia",
                    "Ivern",
                    "Janna",
                    "Jarvan IV",
                    "Jax",
                    "Jayce",
                    "Jhin",
                    "Jinx",
                    "Kalista",
                    "Karma",
                    "Karthus",
                    "Kassadin",
                    "Katarina",
                    "Kayle",
                    "Kennen",
                    "Kha'Zix",
                    "Kindred",
                    "Kled",
                    "Kog'Maw",
                    "LeBlanc",
                    "Lee Sin",
                    "Leona",
                    "Lissandra",
                    "Lucian",
                    "Lulu",
                    "Lux",
                    "Malphite",
                    "Malzahar",
                    "Maokai",
                    "Master Yi",
                    "Miss Fortune",
                    "Mordekaiser",
                    "Morgana",
                    "Nami",
                    "Nasus",
                    "Nautilus",
                    "Nidalee",
                    "Nocturne",
                    "Nunu",
                    "Olaf",
                    "Orianna",
                    "Pantheon",
                    "Poppy",
                    "Quinn",
                    "Rammus",
                    "Rek'Sai",
                    "Renekton",
                    "Rengar",
                    "Riven",
                    "Rumble",
                    "Ryze",
                    "Sejuani",
                    "Shaco",
                    "Shen",
                    "Shyvana",
                    "Singed",
                    "Sion",
                    "Sivir",
                    "Skarner",
                    "Sona",
                    "Soraka",
                    "Swain",
                    "Syndra",
                    "Tahm Kench",
                    "Taliyah",
                    "Talon",
                    "Taric",
                    "Teemo",
                    "Thresh",
                    "Tristana",
                    "Trundle",
                    "Tryndamere",
                    "Twisted Fate",
                    "Twitch",
                    "Udyr",
                    "Urgot",
                    "Varus",
                    "Vayne",
                    "Veigar",
                    "Vel'Koz",
                    "Vi",
                    "Viktor",
                    "Vladimir",
                    "Volibear",
                    "Warwick",
                    "Wukong",
                    "Xerath",
                    "Xin Zhao",
                    "Yasuo",
                    "Yorick",
                    "Zac",
                    "Zed",
                    "Ziggs",
                    "Zilean",
                    "Zyra",
                    "Abyssal Scepter",
                    "Aegis of the Legion",
                    "Aether Wisp",
                    "Amplifying Tome",
                    "Ancient Coin",
                    "Arcane Sweeper",
                    "Arcane Sweeper",
                    "Archangel's Staff",
                    "Archangel's Staff (Quick Charge)",
                    "Ardent Censer",
                    "Athene's Unholy Grail",
                    "B. F. Sword",
                    "Bami's Cinder",
                    "Banner of Command",
                    "Banshee's Veil",
                    "Berserker's Greaves",
                    "Bilgewater Cutlass",
                    "Blade of the Ruined King",
                    "Blasting Wand",
                    "Boots of Mobility",
                    "Boots of Speed",
                    "Boots of Swiftness",
                    "Brawler's Gloves",
                    "Catalyst of Aeons",
                    "Caulfield's Warhammer",
                    "Chain Vest",
                    "Chalice of Harmony",
                    "Cloak of Agility",
                    "Cloth Armor",
                    "Control Ward",
                    "Corrupting Potion",
                    "Crystalline Bracer",
                    "Cull",
                    "Dagger",
                    "Dead Man's Plate",
                    "Death's Dance",
                    "Death's Daughter",
                    "Dervish Blade",
                    "Diet Poro-Snax",
                    "Doran's Blade",
                    "Doran's Ring",
                    "Doran's Shield",
                    "Duskblade of Draktharr",
                    "Edge of Night",
                    "Elixir of Iron",
                    "Elixir of Sorcery",
                    "Elixir of Wrath",
                    "Entropy Field",
                    "Espresso Snax",
                    "Essence Reaver",
                    "Executioner's Calling",
                    "Explorer's Ward",
                    "Eye of the Equinox",
                    "Eye of the Oasis",
                    "Eye of the Watchers",
                    "Face of the Mountain",
                    "Faerie Charm",
                    "Farsight Alteration",
                    "Fiendish Codex",
                    "Fire at Will",
                    "Flash Zone",
                    "Forbidden Idol",
                    "Frost Queen's Claim",
                    "Frosted Snax",
                    "Frostfang",
                    "Frozen Heart",
                    "Frozen Mallet",
                    "Giant Slayer",
                    "Giant's Belt",
                    "Glacial Shroud",
                    "Golden Transcendence",
                    "Golden Transcendence (Disabled)",
                    "Greater Stealth Totem (Trinket)",
                    "Greater Vision Totem (Trinket)",
                    "Guardian Angel",
                    "Guardian's Hammer",
                    "Guardian's Horn",
                    "Guardian's Orb",
                    "Guinsoo's Rageblade",
                    "Haunting Guise",
                    "Health Potion",
                    "Hexdrinker",
                    "Hextech GLP-800",
                    "Hextech Gunblade",
                    "Hextech Protobelt-01",
                    "Hextech Revolver",
                    "Hunter's Machete",
                    "Hunter's Potion",
                    "Hunter's Talisman",
                    "Iceborn Gauntlet",
                    "Infinity Edge",
                    "Ionian Boots of Lucidity",
                    "Jaurim's Fist",
                    "Kindlegem",
                    "Kircheis Shard",
                    "Knight's Vow",
                    "Last Whisper",
                    "Liandry's Torment",
                    "Lich Bane",
                    "Locket of the Iron Solari",
                    "Long Sword",
                    "Lord Dominik's Regards",
                    "Lord Van Damm's Pillager",
                    "Lost Chapter",
                    "Luden's Echo",
                    "Manamune",
                    "Manamune (Quick Charge)",
                    "Maw of Malmortius",
                    "Mejai's Soulstealer",
                    "Mercurial Scimitar",
                    "Mercury's Treads",
                    "Mikael's Crucible",
                    "Moonflair Spellblade",
                    "Morellonomicon",
                    "Mortal Reminder",
                    "Muramana",
                    "Muramana",
                    "Nashor's Tooth",
                    "Needlessly Large Rod",
                    "Negatron Cloak",
                    "Nexus Siege: Siege Weapon Slot",
                    "Ninja Tabi",
                    "Nomad's Medallion",
                    "Null-Magic Mantle",
                    "Ohmwrecker",
                    "Oracle Alteration",
                    "Oracle's Extract",
                    "Overlord's Bloodmail",
                    "Perfect Hex Core",
                    "Phage",
                    "Phantom Dancer",
                    "Pickaxe",
                    "Poacher's Dirk",
                    "Poro-Snax",
                    "Port Pad",
                    "Prototype Hex Core",
                    "Quicksilver Sash",
                    "Rabadon's Deathcap",
                    "Rainbow Snax Party Pack!",
                    "Raise Morale",
                    "Randuin's Omen",
                    "Rapid Firecannon",
                    "Raptor Cloak",
                    "Ravenous Hydra",
                    "Recurve Bow",
                    "Redemption",
                    "Refillable Potion",
                    "Rejuvenation Bead",
                    "Relic Shield",
                    "Righteous Glory",
                    "Rod of Ages",
                    "Rod of Ages (Quick Charge)",
                    "Ruby Crystal",
                    "Ruby Sightstone",
                    "Runaan's Hurricane",
                    "Rylai's Crystal Scepter",
                    "Sanguine Blade",
                    "Sapphire Crystal",
                    "Seeker's Armguard",
                    "Seer Stone (Trinket)",
                    "Seraph's Embrace",
                    "Serrated Dirk",
                    "Sheen",
                    "Shield Totem",
                    "Siege Ballista",
                    "Siege Refund",
                    "Siege Sight Warder",
                    "Siege Teleport",
                    "Siege Teleport (Inactive)",
                    "Sightstone",
                    "Skirmisher's Sabre",
                    "Sorcerer's Shoes",
                    "Soul Anchor (Trinket)",
                    "Spectre's Cowl",
                    "Spellthief's Edge",
                    "Spirit Visage",
                    "Stalker's Blade",
                    "Statikk Shiv",
                    "Sterak's Gage",
                    "Stinger",
                    "Sunfire Cape",
                    "Super Spicy Snax",
                    "Sweeping Lens (Trinket)",
                    "Talisman of Ascension",
                    "Targon's Brace",
                    "Tear of the Goddess",
                    "Tear of the Goddess (Quick Charge)",
                    "The Black Cleaver",
                    "The Black Spear",
                    "The Bloodthirster",
                    "The Dark Seal",
                    "The Hex Core mk-1",
                    "The Hex Core mk-2",
                    "The Lightbringer",
                    "Thornmail",
                    "Tiamat",
                    "Titanic Hydra",
                    "Total Biscuit of Rejuvenation",
                    "Total Biscuit of Rejuvenation",
                    "Tower: Beam of Ruination",
                    "Tower: Storm Bulwark",
                    "Tracker's Knife",
                    "Trinity Force",
                    "Vampiric Scepter",
                    "Vanguard Banner",
                    "Void Staff",
                    "Warden's Mail",
                    "Warding Totem (Trinket)",
                    "Warmog's Armor",
                    "Wicked Hatchet",
                    "Wit's End",
                    "Wooglet's Witchcap",
                    "Youmuu's Ghostblade",
                    "Zeal",
                    "Zeke's Harbinger",
                    "Zhonya's Hourglass",
                    "Zz'Rot Portal");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }


        if (MainActivity.getCategorie().equals("Chimie")) {
            List<String> listAuxiliara = Arrays.asList("Actiniu\n (Ac)", "Aluminiu\n (Al)", "Americiu\n (Am)",
                    "Argint\n (Ag)", "Argon\n (Ar)", "Arsen\n (As)", "Astatiniu\n (At)", "Aur\n (Au)",
                    "Azot, Nitrogen\n (N)", "Bariu\n (Ba)", "Beriliu\n (Be)", "Berkeliu\n (Bk)", "Bismut\n (Bi)",
                    "Bor\n (B)", "Bohriu\n (Bh)", "Brom\n (Br)", "Cadmiu\n (Cd)", "Calciu\n (Ca)", "Californiu\n (Cf)",
                    "Carbon\n (C)", "Ceriu\n (Ce)", "Cesiu\n (Cs)", "Clor\n (Cl)", "Cobalt\n (Co)", "Crom\n (Cr)",
                    "Cupru\n (Cu)", "Curiu\n (Cm)", "Darmstadtiu\n (Ds)", "Disprosiu\n (Dy)", "Dubniu\n (Db)",
                    "Einsteiniu\n (Es)", "Erbiu\n (Er)", "Europiu\n (Eu)", "Fermiu\n (Fm)", "Fier\n (Fe)",
                    "Fluor\n (F)", "Fosfor\n (P)", "Franciu\n (Fr)", "Gadoliniu\n (Gd)", "Galiu\n (Ga)",
                    "Germaniu\n (Ge)", "Hafniu\n (Hf)", "Hassiu\n (Hs)", "Heliu\n (He)", "Hidrogen\n (H)",
                    "Holmiu\n (Ho)", "Indiu\n (In)", "Iod\n (I)", "Iridiu\n (Ir)", "Kripton\n (Kr)", "Lantan\n (La)",
                    "Lawrențiu\n (Lr)", "Litiu\n (Li)", "Lutețiu\n (Lu)", "Magneziu\n (Mg)", "Mangan\n (Mn)",
                    "Meitneriu\n (Mt)", "Mendeleeviu\n (Md)", "Mercur\n (Hg)", "Molibden\n (Mo)", "Neodim\n (Nd)",
                    "Neon\n (Ne)", "Neptuniu\n (Np)", "Nichel\n (Ni)", "Niobiu\n (Nb)", "Nobeliu\n (No)",
                    "Osmiu\n (Os)", "Oxigen\n (O)", "Paladiu\n (Pd)", "Platină\n (Pt)", "Plumb\n (Pb)",
                    "Plutoniu\n (Pu)", "Poloniu\n (Po)", "Potasiu, Kaliu\n (K)", "Praseodim\n (Pr)", "Promețiu\n (Pm)",
                    "Protactiniu\n (Pa)", "Radiu\n (Ra)", "Radon\n (Rn)", "Reniu\n (Re)", "Rodiu\n (Rh)",
                    "Roentgeniu\n (Rg)", "Rubidiu\n (Rb)", "Ruteniu\n (Ru)", "Rutherfordiu\n (Rf)", "Samariu\n (Sm)",
                    "Scandiu\n (Sc)", "Seaborgiu\n (Sg)", "Seleniu\n (Se)", "Siliciu\n (Si)", "Sodiu, Natriu\n (Na)",
                    "Staniu\n (Sn)", "Stibiu, Antimoniu\n (Sb)", "Stronțiu\n (Sr)", "Sulf\n (S)", "Taliu\n (Tl)",
                    "Tantal\n (Ta)", "Tehnețiu\n (Tc)", "Telur\n (Te)", "Terbiu\n (Tb)", "Titan\n (Ti)", "Toriu\n (Th)",
                    "Tuliu\n (Tm)", "Unbiniliu\n (Ubn)", "Coperniciu\n (Cn)", "Ununeniu\n (Uue)", "Ununhexiu\n (Uuh)",
                    "Ununoctiu\n (Uuo)", "Ununpentiu\n (Uup)", "Ununquadiu\n (Uuq)", "Ununseptiu\n (Uus)",
                    "Ununtriu\n (Uut)", "Uraniu\n (U)", "Vanadiu\n (V)", "Wolfram, Tungsten\n (W)", "Xenon\n (Xe)",
                    "Yterbiu\n (Yb)", "Ytriu\n (Y)", "Zinc\n (Zn)", "Zirconiu\n (Zr)");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }

        if (MainActivity.getCategorie().equals("Fizica")) {
            List<String> listAuxiliara = Arrays.asList("Accelerator de particule", "Accelerație",
                    "Accelerație gravitațională", "Amper", "Amplitudine", "An sideral", "Analiză dimensională",
                    "Antimaterie", "Antiparticulă", "Antiproton", "Apă grea", "Atom", "Boson", "Cameră cu bule",
                    "Cameră cu ceață", "Captor", "Cation", "Cauză", "Căldură", "Câmp de forțe", "Câmp de presiuni",
                    "Câmp de temperaturi", "Câmp de viteze", "Câmp electric", "Câmp electromagnetic", "Câmp magnetic",
                    "Câmp vectorial", "Ceas", "Celulă solară", "Ciclul Carnot", "Circuit integrat",
                    "Condensator electric", "Conductivitate electrică", "Conductivitate termică", "Constanta Planck",
                    "Constantă fizică", "Coulomb (unitate)", "Criptare cuantică", "Cronograf", "Cronometru",
                    "Curent electric", "Densitate", "Dezintegrare alfa", "Difracția electronilor", "Dinamometrie",
                    "Diodă", "Diodă semiconductoare", "Diodă tunel", "Distilare", "Echivalență masă-energie",
                    "Ecuația de continuitate", "Efectul Casimir", "Efectul Cerenkov", "Efectul Coandă",
                    "Efectul Compton", "Efectul fotoelectric", "Efectul Josephson", "Efectul tunel", "Electricitate",
                    "Electromagnet", "Electromagnetism", "Electron", "Electronvolt", "Electrostatică", "Emițător",
                    "Energie", "Energie alternativă", "Energie cinetică", "Energie electrică", "Energie eoliană",
                    "Energie hidraulică", "Energie internă", "Energie luminoasă", "Energie mecanică",
                    "Energie nucleară", "Energie solară", "Energie sonoră", "Entalpie", "Entropie", "Eter luminifer",
                    "Experiment", "Fenomen fizic", "Fermion", "Fierbere", "Fisiune nucleară", "Fizica particulelor",
                    "Fizica stării solide", "Fizică atomică", "Fizică clasică", "Forță", "Foton", "Fulger",
                    "Funcție de undă", "Fuziune nucleară", "Gaz perfect", "Gravitație", "Greutate", "Higrometru",
                    "Histerezis", "Impuls", "Inductanță", "Inerție", "Intensitatea curentului electric",
                    "Intensitate luminoasă", "Ion", "Izotop", "Joncțiune p-n", "Kilogram", "Laborator", "Laser",
                    "Legea atracției universale", "Legile lui Newton", "Linie spectrală", "Lucru mecanic", "Lumină",
                    "Luminozitate", "Lungime", "Lungime de undă", "Magnet", "Magnetism", "Masă", "Materie",
                    "Mecanică cuantică", "Metru", "Microfon", "Mișcare browniană", "Motor cu reacție", "Motor diesel",
                    "Motorul Stirling", "Nanocristal", "Nanotehnologie", "Neutrino", "Newton", "Nucleu atomic",
                    "Număr atomic", "Oscilație", "Osciloscop", "Particulă alfa", "Particulă elementară",
                    "Pascal (unitate)", "Pendul fizic", "Pendul Foucault", "Pendul gravitațional", "Perpetuum mobile",
                    "Pilă de combustie", "Piroelectricitate", "Pirometru", "Plasmă", "Portanță", "Potențial electric",
                    "Presiune", "Principiul de excluziune", "Principiul incertitudinii", "Proton", "Punct de fierbere",
                    "Punct de topire", "Putere", "Quark", "Radiație", "Radioactivitate", "Radiocomunicație",
                    "Randament", "Reactor nuclear", "Reflexie optică", "Rezistență electrică", "Sarcină electrică",
                    "Secundă", "Sens orar", "Singularitate gravitațională", "Sistem de referință", "Sistem fizic",
                    "Sistem termodinamic", "Sistemul Internațional de Unități", "Sonar", "Spațiu", "Spațiu-timp",
                    "Spectre de emisie", "Sunet", "Teleportare", "Temperatură", "Tensiune electrică",
                    "Teoria relativității", "Teorie", "Termometru", "Timp", "Timp civil", "Timpul atomic", "Topire",
                    "Transformare termodinamică", "Turbiditate", "Turbină cu gaze", "Umiditate", "Undă staționară",
                    "Unitate de măsură", "Vapori", "Vid", "Viscozitate", "Viteza luminii", "Viteza sunetului", "Viteză",
                    "Volt", "Volum", "Zero absolut");
            lista.addAll(listAuxiliara);
            int index = (int) (Math.random() * (lista.size() - 1));
            cuvant.setText(lista.get(index));
            listaRezultateString.add(lista.get(index));
            lista.remove(lista.get(index));
        }

        cT = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String v = String.format("%02d", millisUntilFinished / 60000);
                int va = (int) ((millisUntilFinished % 60000) / 1000);
                ceas.setText(v + ":" + String.format("%02d", va));
                if (ceas.getText().equals("00:04")) {
                    mp.start();
                }
            }

            @Override
            public void onFinish() {
                if (interAd.isLoaded()) {
                    interAd.show();
                } else {
                    Intent myIntent = new Intent(Play.this, Rezultate.class);
                    startActivity(myIntent);
                }
            }
        };
        cT.start();

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        interAd.loadAd(adRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        cT.cancel();
        mp.stop();
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
        cT.cancel();
        mp.stop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // cuvant.setText(x + "\n" + y + "\n" + z + "\n");

        if ((z <= -6.5) && (ev)) {
            sCorect.start();
            int index = (int) (Math.random() * (lista.size() - 1));
            rLayout.setBackgroundColor(Color.parseColor("#6FB66F"));
            linLayout.setBackgroundColor(Color.parseColor("#6FB66F"));
            ctCuloare.start();

            cuvant.setText(lista.get(index));

            if ((index <= lista2.size()) && (MainActivity.getCategorie().equals("Celebritati"))) {
                tip.setVisibility(View.VISIBLE);
                tip.setText(lista2.get(index));
            } else {
                tip.setText("");
            }
            sCorect.start();
            ev = false;

            ctWait.start();

            listaRezultateString.add(lista.get(index));
            lista.remove(index);
            if (MainActivity.getCategorie().equals("Celebritati")) {
                lista2.remove(index);
            }
            listaRezultateInt.add(1);
            scor = scor + 2;
        }
        if ((z >= 8) && (ev)) {
            sPass.start();
            int index = (int) (Math.random() * (lista.size() - 1));

            rLayout.setBackgroundColor(Color.parseColor("#C15A5A"));
            linLayout.setBackgroundColor(Color.parseColor("#C15A5A"));
            ctCuloare.start();

            cuvant.setText(lista.get(index));
            if ((index <= lista2.size()) && (MainActivity.getCategorie().equals("Celebritati"))) {
                tip.setVisibility(View.VISIBLE);
                tip.setText(lista2.get(index));
            } else {
                tip.setText("");
            }
            ev = false;
            ctWait.start();

            listaRezultateString.add(lista.get(index));
            lista.remove(index);
            if (MainActivity.getCategorie().equals("Celebritati")) {
                lista2.remove(index);
            }
            listaRezultateInt.add(0);
            scor--;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorMan.unregisterListener(this);
    }

}
