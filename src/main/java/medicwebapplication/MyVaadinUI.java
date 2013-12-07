package medicwebapplication;

import javax.servlet.annotation.WebServlet;

import consumingrestobjects.Greeting;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
    //, widgetset = "medicwebapplication.AppWidgetSet" problem w configuration
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        LinkedHashMap getVariables = new LinkedHashMap();
        getVariables.put("phrase", "iniczny");

        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<ArrayList> pojUnit = restTemplate.getForEntity("http://localhost:8080/search?phrase={phrase}", ArrayList.class, getVariables);
        ArrayList pojUnit = restTemplate.getForObject("http://localhost:8080/search?phrase={phrase}", ArrayList.class, getVariables);
        final Jednostka temp = (Jednostka) pojUnit.get(0);
                final String nameUnit =  temp. toString();

//        final ArrayList<Jednostka> temp = pojUnit.getBody();
//        final String nameUnit =  temp.toString();

        // temp.get(0).getId().toString()

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                layout.addComponent(new Label(nameUnit));
            }
        });
        layout.addComponent(button);
    }

}