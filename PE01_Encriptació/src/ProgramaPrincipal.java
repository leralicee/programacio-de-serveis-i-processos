public class ProgramaPrincipal {

    public static void main(String[] args) {

        // exemple de l'enunciat: Desencripta(Encripta("HOLA", "123"), "123") = "HOLA"
        System.out.println("=== EXEMPLE BASIC ===");
        String missatge = "HOLA";
        String clau = "123";
        String xifrat = ClasseCriptografica.encripta(missatge, clau);
        String desxifrat = ClasseCriptografica.desencripta(xifrat, clau);
        System.out.println("Missatge original: " + missatge);
        System.out.println("Clau:              " + clau);
        System.out.println("Missatge xifrat:   " + xifrat);
        System.out.println("Missatge recuperat: " + desxifrat);
        System.out.println();

        // apartat 1: investiga el funcionament
        System.out.println("=== INVESTIGA EL FUNCIONAMENT ===");

        // mateix missatge i mateixa clau dos cops, ha de donar el mateix
        String primer = ClasseCriptografica.encripta("HOLA", "3");
        String segon = ClasseCriptografica.encripta("HOLA", "3");
        System.out.println("HOLA + clau 3 (1r cop): " + primer);
        System.out.println("HOLA + clau 3 (2n cop): " + segon);
        System.out.println("Son iguals? " + primer.equals(segon));

        // canviem la clau, el resultat ha de canviar
        String ambClau7 = ClasseCriptografica.encripta("HOLA", "7");
        System.out.println("HOLA + clau 7:          " + ambClau7);
        System.out.println("Canvia el resultat? " + !primer.equals(ambClau7));

        // desencriptem amb una clau q no toca
        System.out.println("Desencriptat amb clau 7: " + ClasseCriptografica.desencripta(primer, "7"));
        System.out.println();

        // apartat 5: proves del sistema
        System.out.println("=== PROVES DEL SISTEMA ===");
        prova("Test 1 - missatge curt", "HOLA", "123");
        prova("Test 2 - missatge amb espais", "HOLA MÓN", "123");
        prova("Test 3 - missatge llarg", "Aquest missatge te mes de trenta caracters seguro", "123");

        // test 4: mateix missatge amb dues claus diferents
        String ambClauA = ClasseCriptografica.encripta("HOLA", "123");
        String ambClauB = ClasseCriptografica.encripta("HOLA", "124");
        System.out.println("Test 4 - clau diferent");
        System.out.println("  HOLA + 123: " + ambClauA);
        System.out.println("  HOLA + 124: " + ambClauB);
        System.out.println("  Resultat: " + (!ambClauA.equals(ambClauB) ? "CORRECTE" : "INCORRECTE"));

        // test 5: anada i tornada amb un missatge qualsevol
        prova("Test 5 - encriptar i desencriptar", "Missatge de prova 2026!", "provaclau");

        // test 6: xifrem amb una clau i desxifrem amb una altra, no ha de sortir el missatge original
        String xifratTest6 = ClasseCriptografica.encripta("HOLA", "123");
        String resultatTest6 = ClasseCriptografica.desencripta(xifratTest6, "124");
        System.out.println("Test 6 - clau incorrecta");
        System.out.println("  Xifrat amb 123:       " + xifratTest6);
        System.out.println("  Desxifrat amb 124:    " + resultatTest6);
        System.out.println("  Resultat: " + (!resultatTest6.equals("HOLA") ? "CORRECTE" : "INCORRECTE"));
    }

    // fa l'anada i tornada d'un missatge i diu si s'ha recuperat igual
    private static void prova(String nomTest, String missatge, String clau) {
        String xifrat = ClasseCriptografica.encripta(missatge, clau);
        String desxifrat = ClasseCriptografica.desencripta(xifrat, clau);
        System.out.println(nomTest);
        System.out.println("  Original:  " + missatge);
        System.out.println("  Xifrat:    " + xifrat);
        System.out.println("  Recuperat: " + desxifrat);
        System.out.println("  Resultat: " + (missatge.equals(desxifrat) ? "CORRECTE" : "INCORRECTE"));
    }
}
