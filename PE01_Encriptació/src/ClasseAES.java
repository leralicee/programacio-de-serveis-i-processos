import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ClasseAES {

    // ECB no fa servir IV, PKCS5Padding omple ultim bloc
    private static final String ALGORITME = "AES/ECB/PKCS5Padding";

    // encripta el missatge amb AES i el retorna en Base64
    public static String encripta(String missatge, String clau) throws Exception {
        SecretKeySpec clauAES = preparaClau(clau);

        Cipher cipher = Cipher.getInstance(ALGORITME);
        cipher.init(Cipher.ENCRYPT_MODE, clauAES);

        // AES treballa amb bytes
        byte[] bytesMissatge = missatge.getBytes(StandardCharsets.UTF_8);
        byte[] xifrat = cipher.doFinal(bytesMissatge);

        // passem a Base64 per poder retornar un String
        return Base64.getEncoder().encodeToString(xifrat);
    }

    // desencripta un missatge en Base64 i retorna l'original
    public static String desencripta(String missatgeXifrat, String clau) throws Exception {
        SecretKeySpec clauAES = preparaClau(clau);

        Cipher cipher = Cipher.getInstance(ALGORITME);
        cipher.init(Cipher.DECRYPT_MODE, clauAES);

        // desfem el Base64
        byte[] xifrat = Base64.getDecoder().decode(missatgeXifrat);
        byte[] bytesMissatge = cipher.doFinal(xifrat);

        return new String(bytesMissatge, StandardCharsets.UTF_8);
    }

    // la clau ha de tenir 16, 24 o 32 bytes, si no java dona error
    private static SecretKeySpec preparaClau(String clau) {
        byte[] bytesClau = clau.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(bytesClau, "AES");
    }
}
