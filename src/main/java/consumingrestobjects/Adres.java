package consumingrestobjects;

/**
 * @author Rafal Zawadzki
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Adres {
    Integer ID;
    String Ulica;
    String NrDomu;
    String KodPocztowy;
    String Miasto;
    String Dopisek;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUlica() {
        return Ulica;
    }

    public void setUlica(String ulica) {
        Ulica = ulica;
    }

    public String getNrDomu() {
        return NrDomu;
    }

    public void setNrDomu(String nrDomu) {
        NrDomu = nrDomu;
    }

    public String getKodPocztowy() {
        return KodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        KodPocztowy = kodPocztowy;
    }

    public String getMiasto() {
        return Miasto;
    }

    public void setMiasto(String miasto) {
        Miasto = miasto;
    }

    public String getDopisek() {
        return Dopisek;
    }

    public void setDopisek(String dopisek) {
        Dopisek = dopisek;
    }
}

