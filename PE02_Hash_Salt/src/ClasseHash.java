import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;

public class ClasseHash {

    // calcula el hash SHA-256 d'un text i el retorna en hexadecimal
    public static String calculaHash(String text) {
        try {
            byte[] bytesText = text.getBytes(StandardCharsets.UTF_8);
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(bytesText);
            return HexFormat.of().withUpperCase().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            // no hauria de passar mai, java sempre te SHA-256
            throw new RuntimeException("No s'ha trobat l'algoritme SHA-256");
        }
    }

    // genera una salt aleatoria de 16 bytes i la retorna en hexadecimal
    public static String generaSalt() {
        // SecureRandom es per seguretat, Random normal es pot predir
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return HexFormat.of().withUpperCase().formatHex(salt);
    }

    // hash de la contrasenya amb la salt davant
    public static String hashAmbSalt(String contrasenya, String salt) {
        return calculaHash(salt + contrasenya);
    }
}
