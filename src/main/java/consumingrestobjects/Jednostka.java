package consumingrestobjects;

/**
 * @author Rafal Zawadzki
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Jednostka implements Serializable {
    Integer id;
   Integer adres;
    String nazwa;
    String www;
    String telefon;
    String telOpis;
    String email;
    String godzinyPracy;
    String opis;
    String infoDodatkowe;
    String specjalizacja1;
    String specjalizacja2;
    String specjalizacja3;
    Integer nadrzednaJednostka;
    String podrzedneJednostki;
    String dataAktualizacji;
    String lokalizacja;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdres() {
        return adres;
    }

    public void setAdres(Integer adres) {
        this.adres = adres;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTelOpis() {
        return telOpis;
    }

    public void setTelOpis(String telOpis) {
        this.telOpis = telOpis;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGodzinyPracy() {
        return godzinyPracy;
    }

    public void setGodzinyPracy(String godzinyPracy) {
        this.godzinyPracy = godzinyPracy;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getInfoDodatkowe() {
        return infoDodatkowe;
    }

    public void setInfoDodatkowe(String infoDodatkowe) {
        this.infoDodatkowe = infoDodatkowe;
    }

    public String getSpecjalizacja1() {
        return specjalizacja1;
    }

    public void setSpecjalizacja1(String specjalizacja1) {
        this.specjalizacja1 = specjalizacja1;
    }

    public String getSpecjalizacja2() {
        return specjalizacja2;
    }

    public void setSpecjalizacja2(String specjalizacja2) {
        this.specjalizacja2 = specjalizacja2;
    }

    public String getSpecjalizacja3() {
        return specjalizacja3;
    }

    public void setSpecjalizacja3(String specjalizacja3) {
        this.specjalizacja3 = specjalizacja3;
    }

    public Integer getNadrzednaJednostka() {
        return nadrzednaJednostka;
    }

    public void setNadrzednaJednostka(Integer nadrzednaJednostka) {
        this.nadrzednaJednostka = nadrzednaJednostka;
    }

    public String getPodrzedneJednostki() {
        return podrzedneJednostki;
    }

    public void setPodrzedneJednostki(String podrzedneJednostki) {
        this.podrzedneJednostki = podrzedneJednostki;
    }

    public String getDataAktualizacji() {
        return dataAktualizacji;
    }

    public void setDataAktualizacji(String dataAktualizacji) {
        this.dataAktualizacji = dataAktualizacji;
    }

    public String getLokalizacja() {
        return lokalizacja;
    }

    public void setLokalizacja(String lokalizacja) {
        this.lokalizacja = lokalizacja;
    }
}

