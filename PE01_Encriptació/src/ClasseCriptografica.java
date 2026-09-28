import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ClasseCriptografica {

    // encripta un missatge amb una clau i retorna el criptograma en Base64
    public static String encripta(String missatge, String clau) {
        comprovaParametres(missatge, clau);

        byte[] bytesMissatge = missatge.getBytes(StandardCharsets.UTF_8);
        byte[] bytesClau = clau.getBytes(StandardCharsets.UTF_8);
        int longitudClau = bytesClau.length;
        int sumaClau = sumaDeLaClau(bytesClau);

        byte[] xifrat = new byte[bytesMissatge.length];

        for (int i = 0; i < bytesMissatge.length; i++) {
            // capa 1: desplaçament. sumem el caracter de la clau que toca, la posicio i la suma de tota la clau
            int valor = valorSenseSigne(bytesMissatge[i])
                      + valorSenseSigne(bytesClau[i % longitudClau])
                      + i
                      + sumaClau;
            valor = valor % 256;

            // capa 2: XOR amb el SEGUENT caracter de la clau, per tal que cada posicio del missatge depengui de dos caracters diferents de la clau
            int caracterXor = valorSenseSigne(bytesClau[(i + 1) % longitudClau]);
            xifrat[i] = (byte) (valor ^ caracterXor);
        }

        // capa 3: els bytes resultants no son caracters escribibles, els passem a Base64
        return Base64.getEncoder().encodeToString(xifrat);
    }

    // desencripta un criptograma en Base64 amb una clau i retorna el missatge original
    public static String desencripta(String missatgeXifrat, String clau) {
        comprovaParametres(missatgeXifrat, clau);

        // desfem capa 3
        byte[] xifrat = Base64.getDecoder().decode(missatgeXifrat);

        byte[] bytesClau = clau.getBytes(StandardCharsets.UTF_8);
        int longitudClau = bytesClau.length;
        int sumaClau = sumaDeLaClau(bytesClau);

        byte[] bytesMissatge = new byte[xifrat.length];

        for (int i = 0; i < xifrat.length; i++) {
            // desfem  capa 2. el XOR es la seva propia inversa: aplicar-lo dues vegades amb el mateix valor retorna el numero de partida
            int caracterXor = valorSenseSigne(bytesClau[(i + 1) % longitudClau]);
            int valor = valorSenseSigne(xifrat[i]) ^ caracterXor;

            // desfem la capa 1, restant el mateix que haviem sumat
            valor = valor
                  - valorSenseSigne(bytesClau[i % longitudClau])
                  - i
                  - sumaClau;

            // el resultat pot ser negatiu, el tornem al rang 0..255.
            valor = ((valor % 256) + 256) % 256;

            bytesMissatge[i] = (byte) valor;
        }

        return new String(bytesMissatge, StandardCharsets.UTF_8);
    }

    // suma tots els bytes de la clau. gracies a aquest valor, canviar un sol caracter de la clau afecta totes les posicions del missatge
    private static int sumaDeLaClau(byte[] clau) {
        int suma = 0;
        for (byte b : clau) {
            suma += valorSenseSigne(b);
        }
        return suma % 256;
    }

    // conversio ja q a java el tipus byte va de -128 a 127, pero nosaltres treballem 0..255
    private static int valorSenseSigne(byte b) {
        return b & 0xFF;
    }

    // la clau ha de participar en el proces aixi q no pot estar buida
    private static void comprovaParametres(String missatge, String clau) {
        if (missatge == null) {
            throw new IllegalArgumentException("El missatge no pot ser null.");
        }
        if (clau == null || clau.isEmpty()) {
            throw new IllegalArgumentException("La clau no pot estar buida.");
        }
    }
}
