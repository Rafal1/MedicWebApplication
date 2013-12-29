package consumingrestservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import consumingrestobjects.Jednostka;
import medicwebapplication.MainVaadinUI;
import medicwebapplication.UnitDataTableCreate;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Rafal Zawadzki
 */
public class SearchPhrase {
    private static Label noResults = null;

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
        String unitsString = restTemplate.getForObject("http://localhost:8080/search?phrase={phrase}", String.class, phrase);
        try {
            parsingResponse = mapper.readValue(unitsString, new TypeReference<ArrayList<Jednostka>>() {
            });
        } catch (IOException e) {
            System.out.print("Parsing array error");
            e.printStackTrace();
        }
        return parsingResponse;
    }

    //method done because there is no way to give return value from listener (inner class) in MainVaadinUI
    //todo trivial: try to generalize VerticalLayout
    //todo minor: there are better places, move to view
    public static void search(String phrase, VerticalLayout layout) {
        ArrayList<Jednostka> parsingResponse = SearchPhrase.getUnitsByPhrase(phrase);
        if (parsingResponse.size() == 1 &&
                parsingResponse.get(0).getId() == null &&
                parsingResponse.get(0).getNazwa().equals("Brak Wynikow")) {
            if (checkIfNoResultExists()) {
                deleteNoResult(layout);
            }
            if (UnitDataTableCreate.checkIfExistsTable()) {
                UnitDataTableCreate.deleteTable(layout);
            }
            Label noRes = new Label("Brak wyników");
            noRes.setWidth("80px");
            noRes.getId();
            layout.addComponent(noRes);
            layout.setComponentAlignment(noRes, Alignment.TOP_CENTER);
            noResults = noRes;
            return;
        }
        UnitDataTableCreate.makeTable(parsingResponse, layout);
    }
}