package consumingrestobjects;

/**
 * @author Rafal Zawadzki
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Jednostka {
    Integer id;
//   Integer adres;
//    String nazwa;
//    String www;
//    String telefon;
//    String telOpis;
//    String email;
//    String godzinyPracy;
//    String opis;
//    String infoDodatkowe;
//    String specjalizacja1;
//    String specjalizacja2;
//    String specjalizacja3;
//    Integer nadrzednaJednostka;
//    String podrzedneJednostki;
//    Date dataAktualizacji;
//    String lokalizacja;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

