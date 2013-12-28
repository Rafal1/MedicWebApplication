package consumingrestservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import consumingrestobjects.Jednostka;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author Rafal Zawadzki
 */
public class SearchPhrase {
    private ArrayList<Jednostka> currentResult = null;

    public static ArrayList<Jednostka> searchUnitsByPhrase(String phrase) {
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

    public static void search(String phrase){
        //                ArrayList<Jednostka> parsingResponse = SearchPhrase.getUnitsByPhrase(property.getValue());
        //                layout.addComponent(new Label(parsingResponse.get(0).getNazwa().toString()));
    }
}
