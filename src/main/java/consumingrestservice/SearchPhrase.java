package consumingrestservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import consumingrestobjects.Jednostka;
import medicwebapplication.DataUnitCreate;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Rafal Zawadzki
 */
public class SearchPhrase {
    private ArrayList<Jednostka> currentResult = null;

    public static ArrayList<Jednostka> getUnitsByPhrase(String phrase) {
        ArrayList<Jednostka> parsingResponse = null;
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
    public static void search(String phrase, VerticalLayout layout) {
        ArrayList<Jednostka> parsingResponse = SearchPhrase.getUnitsByPhrase(phrase);
        if(parsingResponse.isEmpty()){
            //todo MAJOR there are better places, move to view
            Label noRes = new Label("Brak wyników");
            noRes.setWidth("10em");
            layout.addComponent(noRes);
            layout.setComponentAlignment(noRes, Alignment.MIDDLE_CENTER);
            return;
        }
        DataUnitCreate.makeTable(parsingResponse, layout);
    }
}