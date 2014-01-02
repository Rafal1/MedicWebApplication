package consumingrestservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import consumingrestobjects.Adres;
import consumingrestobjects.Jednostka;
import medicwebapplication.MainVaadinUI;
import medicwebapplication.UnitDataTableCreate;
import medicwebapplication.Validation;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Rafal Zawadzki
 */
public class SearchPhrase {
    private static Label noResults = null;
    private static boolean wholeWord = false;

    public static boolean isWholeWord() {
        return wholeWord;
    }

    public static void setWholeWord(boolean wholeWord) {
        SearchPhrase.wholeWord = wholeWord;
    }

    public static boolean checkIfNoResultExists() {
        if (noResults != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteNoResult(VerticalLayout layout) {
        layout.removeComponent(noResults);
        noResults = null;
        MainVaadinUI.setFullLayout(layout);
        return true;
    }

    public static ArrayList<Jednostka> getUnitsByPhrase(String phrase) {
        ArrayList<Jednostka> parsingResponse = new ArrayList<Jednostka>();
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        String unitsString;
        if (wholeWord) {
            unitsString = restTemplate.getForObject("http://localhost:8080/search?phrase={phrase}&wholeWord={wholeWord}", String.class, phrase, wholeWord);
        } else {
            unitsString = restTemplate.getForObject("http://localhost:8080/search?phrase={phrase}", String.class, phrase);
            System.out.println();
        }
        try {
            parsingResponse = mapper.readValue(unitsString, new TypeReference<ArrayList<Jednostka>>() {
            });
        } catch (IOException e) {
            System.out.print("Parsing array error");
            e.printStackTrace();
        }
        return parsingResponse;
    }

    public static Adres getAdresByID(Integer adresID) {
        RestTemplate restTemplate = new RestTemplate();
        Adres stream = restTemplate.getForObject("http://localhost:8080/address?adresID={adresID}", Adres.class, adresID);
        return stream;
    }

    public static Jednostka getJednostkaByID(Integer unitID) {
        RestTemplate restTemplate = new RestTemplate();
        Jednostka stream = restTemplate.getForObject("http://localhost:8080/overunit?unitID={unitID}", Jednostka.class, unitID);
        return stream;
    }

    //method done because there is no way to give return value from listener (inner class) in MainVaadinUI
    //todo trivial: try to generalize VerticalLayout
    //todo minor: there are better places, move to view
    public static void search(String phrase, VerticalLayout layout) {
        if (UnitDataTableCreate.checkIfExistsTable()) {
            UnitDataTableCreate.deleteTable(layout);
        }
        Boolean val = Validation.validate(phrase);
        if (!val) {
            if (!checkIfNoResultExists()) {
                labelResultMessage(layout);
                //todo maybe special message when validation goes wrong
            }
            return;
        }
        ArrayList<Jednostka> parsingResponse = SearchPhrase.getUnitsByPhrase(phrase);
        if (parsingResponse.size() == 1 &&
                parsingResponse.get(0).getId() == null &&
                parsingResponse.get(0).getNazwa().equals("Brak Wynikow")) {
            if (!checkIfNoResultExists()) {
                labelResultMessage(layout);
            }
            return;
        }
        if (checkIfNoResultExists()) {
            deleteNoResult(layout);
        }
        UnitDataTableCreate.makeTable(parsingResponse, layout);
    }

    private static void labelResultMessage(VerticalLayout layout) {
        Label noRes = new Label("Brak wyników");
        noRes.setWidth("120px");
        layout.addComponent(noRes);
        layout.setComponentAlignment(noRes, Alignment.TOP_CENTER);
        noResults = noRes;
    }

}