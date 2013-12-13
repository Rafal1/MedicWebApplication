package medicwebapplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import consumingrestobjects.Jednostka;
import org.springframework.web.client.RestTemplate;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        ArrayList<Jednostka> parsingResponse = null;
        String nameUnitT = "";

        LinkedHashMap getVariables = new LinkedHashMap();
        getVariables.put("phrase", "iniczny");

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        String unitsString = restTemplate.getForObject("http://localhost:8080/search?phrase={phrase}", String.class, getVariables);
        try {
            parsingResponse = mapper.readValue(unitsString, new TypeReference<ArrayList<Jednostka>>() {
            });
        } catch (IOException e) {
            System.out.print("Parsing array error");
            e.printStackTrace();
        }

        String tmp = null;
        if(parsingResponse != null){
             tmp = parsingResponse.get(0).getNazwa().toString();
        }
        final String x = tmp;

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                layout.addComponent(new Label(x));
            }
        });
        layout.addComponent(button);
    }

}