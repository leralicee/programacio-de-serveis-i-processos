import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class ProgramaPrincipal {

    // el scanner i els usuaris son de tota la classe pq els fan servir diversos metodes
    private static Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

    // usuaris registrats en memoria: nom d'usuari -> dades (salt i hash)
    private static HashMap<String, Usuari> usuaris = new HashMap<>();

    public static void main(String[] args) {
        int opcio = -1;

        while (opcio != 0) {
            System.out.println();
            System.out.println("=== MENU ===");
            System.out.println("1. Calcul de resums");
            System.out.println("2. Verificacio de la integritat");
            System.out.println("3. Registre d'usuari");
            System.out.println("4. Inici de sessio");
            System.out.println("5. Proves de la practica");
            System.out.println("0. Sortir");
            System.out.print("Opcio: ");

            // si no escriu un numero, l'opcio queda a -1 i torna a sortir el menu
            try {
                opcio = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcio = -1;
            }

            switch (opcio) {
                case 1 -> calculResums();
                case 2 -> verificacioIntegritat();
                case 3 -> registre();
                case 4 -> iniciSessio();
                case 5 -> proves();
                case 0 -> System.out.println("Adeu!");
                default -> System.out.println("Opcio incorrecta.");
            }
        }
        sc.close();
    }

    // ---------- PART 1 ----------

    private static void calculResums() {
        System.out.println();
        System.out.println("=== PART 1: CALCUL DE RESUMS ===");

        // 1. un missatge i el seu hash
        System.out.print("Missatge: ");
        String missatge = sc.nextLine();
        System.out.println("Hash: " + ClasseHash.calculaHash(missatge));
        System.out.println();

        // 2. dos missatges, mirem si els hashes coincideixen
        System.out.print("Primer missatge: ");
        String primer = sc.nextLine();
        System.out.print("Segon missatge: ");
        String segon = sc.nextLine();
        String hashPrimer = ClasseHash.calculaHash(primer);
        String hashSegon = ClasseHash.calculaHash(segon);
        System.out.println("Hash 1: " + hashPrimer);
        System.out.println("Hash 2: " + hashSegon);
        System.out.println("Coincideixen? " + hashPrimer.equals(hashSegon));
        System.out.println();

        // 3. calculem el hash, modifiquem el missatge i el tornem a calcular
        System.out.print("Missatge original: ");
        String original = sc.nextLine();
        System.out.print("Missatge modificat: ");
        String modificat = sc.nextLine();
        String hashOriginal = ClasseHash.calculaHash(original);
        String hashModificat = ClasseHash.calculaHash(modificat);
        System.out.println("Hash original:  " + hashOriginal);
        System.out.println("Hash modificat: " + hashModificat);
        System.out.println("Caracters diferents: " + caractersDiferents(hashOriginal, hashModificat) + " de " + hashOriginal.length());
        System.out.println();

        // 4. el mateix missatge diverses vegades, ha de donar sempre el mateix
        System.out.print("Missatge per repetir: ");
        String repetit = sc.nextLine();
        String hashPrimeraVegada = ClasseHash.calculaHash(repetit);
        boolean sempreIgual = true;
        for (int i = 1; i <= 3; i++) {
            String hash = ClasseHash.calculaHash(repetit);
            System.out.println("Vegada " + i + ": " + hash);
            if (!hash.equals(hashPrimeraVegada)) {
                sempreIgual = false;
            }
        }
        System.out.println("Es mante el resultat? " + sempreIgual);
    }

    // compta quantes posicions son diferents entre dos hashes de la mateixa mida
    private static int caractersDiferents(String a, String b) {
        int diferents = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diferents++;
            }
        }
        return diferents;
    }

    // ---------- PART 2 ----------

    private static void verificacioIntegritat() {
        System.out.println();
        System.out.println("=== PART 2: VERIFICACIO DE LA INTEGRITAT ===");

        try {
            // 1. l'emissor escriu el missatge i el desem a missatge.txt
            System.out.print("[EMISSOR] Missatge a enviar: ");
            String missatge = sc.nextLine();
            Files.writeString(Path.of("missatge.txt"), missatge);

            // 2. calculem el hash i el desem a missatge.hash
            String hashOriginal = ClasseHash.calculaHash(missatge);
            Files.writeString(Path.of("missatge.hash"), hashOriginal);
            System.out.println("Desat missatge.txt i missatge.hash");

            // creem tambe una versio modificada per poder provar-ho (un caracter afegit al final)
            Files.writeString(Path.of("missatgeCanviat.txt"), missatge + ".");
            System.out.println("Creat missatgeCanviat.txt (versio modificada)");
            System.out.println();

            // 3 i 4. el receptor diu quin fitxer ha rebut
            System.out.print("[RECEPTOR] Nom del fitxer rebut (missatge.txt o missatgeCanviat.txt): ");
            String nomFitxer = sc.nextLine();
            String missatgeRebut = Files.readString(Path.of(nomFitxer));

            // 5. calculem el hash del rebut i el comparem amb el guardat
            String hashRebut = ClasseHash.calculaHash(missatgeRebut);
            String hashGuardat = Files.readString(Path.of("missatge.hash")).trim();
            System.out.println("Missatge rebut: " + missatgeRebut);
            System.out.println("Hash guardat:   " + hashGuardat);
            System.out.println("Hash rebut:     " + hashRebut);

            // 6. diem si coincideixen
            if (hashRebut.equals(hashGuardat)) {
                System.out.println("Els hashes coincideixen: el missatge NO s'ha modificat.");
            } else {
                System.out.println("Els hashes NO coincideixen: s'ha detectat una modificacio!");
            }
        } catch (NoSuchFileException e) {
            System.out.println("Error: el fitxer " + e.getMessage() + " no existeix.");
        } catch (IOException e) {
            System.out.println("Error amb el fitxer: " + e.getMessage());
        }
    }

    // ---------- PART 3 ----------

    private static void registre() {
        System.out.println();
        System.out.println("=== PART 3: REGISTRE D'USUARI ===");

        System.out.print("Nom d'usuari: ");
        String nom = sc.nextLine();
        if (nom.isEmpty() || usuaris.containsKey(nom)) {
            System.out.println("Error: el nom esta buit o l'usuari ja existeix.");
            return;
        }
        System.out.print("Contrasenya: ");
        String contrasenya = sc.nextLine();

        registraUsuari(nom, contrasenya);
        Usuari usuari = usuaris.get(nom);
        System.out.println("Usuari registrat!");
        System.out.println("  Salt: " + usuari.getSalt());
        System.out.println("  Hash: " + usuari.getHash());
        // la contrasenya no es mostra ni es guarda enlloc
    }

    // genera la salt, calcula el hash i guarda l'usuari. la contrasenya es perd al acabar el metode
    private static void registraUsuari(String nom, String contrasenya) {
        String salt = ClasseHash.generaSalt();
        String hash = ClasseHash.hashAmbSalt(contrasenya, salt);
        usuaris.put(nom, new Usuari(nom, salt, hash));
    }

    // ---------- PART 4 ----------

    private static void iniciSessio() {
        System.out.println();
        System.out.println("=== PART 4: INICI DE SESSIO ===");

        System.out.print("Nom d'usuari: ");
        String nom = sc.nextLine();
        System.out.print("Contrasenya: ");
        String contrasenya = sc.nextLine();

        if (comprovaLogin(nom, contrasenya)) {
            System.out.println("Acces permes. Benvingut/da, " + nom + "!");
        } else {
            System.out.println("Error: usuari o contrasenya incorrectes.");
        }
    }

    // retorna true nomes si l'usuari existeix i el hash de la contrasenya amb la seva salt coincideix
    private static boolean comprovaLogin(String nom, String contrasenya) {
        // 2. mirem si l'usuari esta registrat
        if (!usuaris.containsKey(nom)) {
            return false;
        }

        // 3. recuperem la salt i el hash guardats
        Usuari usuari = usuaris.get(nom);

        // 4. calculem el hash de la contrasenya escrita amb la salt guardada
        String hashCalculat = ClasseHash.hashAmbSalt(contrasenya, usuari.getSalt());

        // 5 i 6. nomes deixem entrar si coincideix
        return hashCalculat.equals(usuari.getHash());
    }

    // ---------- PART 5 ----------

    private static void proves() {
        System.out.println();
        System.out.println("=== PART 5: PROVES ===");

        // prova 1: dos usuaris amb la mateixa contrasenya
        registraUsuari("prova_anna", "1234");
        registraUsuari("prova_marc", "1234");
        Usuari anna = usuaris.get("prova_anna");
        Usuari marc = usuaris.get("prova_marc");
        System.out.println("Prova 1 - dos usuaris amb la contrasenya 1234");
        System.out.println("  anna -> salt " + anna.getSalt() + "  hash " + anna.getHash());
        System.out.println("  marc -> salt " + marc.getSalt() + "  hash " + marc.getHash());
        System.out.println("  Hashes diferents? " + !anna.getHash().equals(marc.getHash()));

        // prova 2: contrasenya correcta
        System.out.println("Prova 2 - login anna amb 1234");
        System.out.println("  Acces permes? " + comprovaLogin("prova_anna", "1234"));

        // prova 3: contrasenya incorrecta
        System.out.println("Prova 3 - login anna amb 4321");
        System.out.println("  Acces permes? " + comprovaLogin("prova_anna", "4321"));

        // prova 4: contrasenya bona pero amb una salt diferent de la guardada
        String altraSalt = ClasseHash.generaSalt();
        String hashAltraSalt = ClasseHash.hashAmbSalt("1234", altraSalt);
        System.out.println("Prova 4 - contrasenya 1234 amb una salt diferent");
        System.out.println("  Hash guardat:          " + anna.getHash());
        System.out.println("  Hash amb altra salt:   " + hashAltraSalt);
        System.out.println("  Coincideixen? " + hashAltraSalt.equals(anna.getHash()));

        // esborrem els usuaris de prova perque no es quedin registrats
        usuaris.remove("prova_anna");
        usuaris.remove("prova_marc");
    }
}
