// guarda les dades d'un usuari registrat. la contrasenya NO es guarda, nomes la salt i el hash
public class Usuari {

    private String nom;
    private String salt;
    private String hash;

    public Usuari(String nom, String salt, String hash) {
        this.nom = nom;
        this.salt = salt;
        this.hash = hash;
    }

    public String getNom() {
        return nom;
    }

    public String getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }
}
