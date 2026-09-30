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
    }
}
