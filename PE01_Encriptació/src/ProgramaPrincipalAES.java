import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ProgramaPrincipalAES {

    public static void main(String[] args) throws Exception {

        // dades de l'enunciat
        String missatge = "Aquest és un missatge secret.";
        String clau = "1234567890123456";

        System.out.println("=== AES ===");
        System.out.println("Missatge original:  " + missatge);

        String xifrat = ClasseAES.encripta(missatge, clau);
        System.out.println("Missatge xifrat:    " + xifrat);

        String recuperat = ClasseAES.desencripta(xifrat, clau);
        System.out.println("Missatge recuperat: " + recuperat);
        System.out.println("Son iguals? " + missatge.equals(recuperat));
        System.out.println();

        System.out.println("=== PROVES AES ===");

        // prova 1: clau correcta
        System.out.println("Prova 1 - clau correcta");
        System.out.println("  Recuperat: " + ClasseAES.desencripta(xifrat, clau));

        // prova 2: desencriptem amb una altra clau de 16
        System.out.println("Prova 2 - clau diferent");
        try {
            System.out.println("  Recuperat: " + ClasseAES.desencripta(xifrat, "6543210987654321"));
        } catch (Exception e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // prova 3: un altre missatge
        System.out.println("Prova 3 - missatge diferent");
        String altre = "Demà quedem a les 5 a la biblioteca";
        String altreXifrat = ClasseAES.encripta(altre, clau);
        System.out.println("  Original:  " + altre);
        System.out.println("  Xifrat:    " + altreXifrat);
        System.out.println("  Recuperat: " + ClasseAES.desencripta(altreXifrat, clau));

        // prova 4: clau massa curta
        System.out.println("Prova 4 - clau de longitud incorrecta");
        try {
            System.out.println("  Xifrat: " + ClasseAES.encripta(missatge, "1234"));
        } catch (Exception e) {
            System.out.println("  Error: " + e.getMessage());
        }
        System.out.println();

        provaManual();
    }

    // l'usuari escriu el missatge i la clau. es repeteix fins que deixa el missatge buit
    private static void provaManual() {
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("=== PROVA EL TEU MISSATGE AMB AES ===");

        while (true) {
            System.out.print("Missatge (enter per sortir): ");
            String missatge = sc.nextLine();
            if (missatge.isEmpty()) {
                break;
            }
            System.out.print("Clau (16, 24 o 32 caracters): ");
            String clau = sc.nextLine();

            // si la clau no te la mida bona, encripta dona error i no seguim
            String xifrat;
            try {
                xifrat = ClasseAES.encripta(missatge, clau);
            } catch (Exception e) {
                System.out.println("  Error: " + e.getMessage());
                System.out.println();
                continue;
            }
            System.out.println("  Xifrat:    " + xifrat);

            try {
                System.out.println("  Recuperat: " + ClasseAES.desencripta(xifrat, clau));
            } catch (Exception e) {
                System.out.println("  Error: " + e.getMessage());
            }

            // per provar la clau incorrecta en directe
            System.out.print("Vols provar una clau incorrecta? Escriu-la (enter per saltar): ");
            String altraClau = sc.nextLine();
            if (!altraClau.isEmpty()) {
                try {
                    System.out.println("  Amb " + altraClau + ": " + ClasseAES.desencripta(xifrat, altraClau));
                } catch (Exception e) {
                    System.out.println("  Error: " + e.getMessage());
                }
            }
            System.out.println();
        }
        sc.close();
    }
}
