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
import org.springframework.web.client.RestTemplate;

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


//        ObjectMapper xmlMapper = new ObjectMapper();
//        xmlMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        LinkedHashMap getVariables = new LinkedHashMap();
//        getVariables.put("phrase", "entralny");
        RestTemplate restTemplate = new RestTemplate();
        Greeting gret = restTemplate.getForObject("http://localhost:8080/search?phrase=entralny", Greeting.class);
        final String buduje = gret.getContent();
//  ResponseEntity<ArrayList> pojUnit = restTemplate.getForEntity("http://localhost:8080/search?phrase=entralny", ArrayList.class);
//        final ArrayList<Jednostka> temp = pojUnit.getBody();
//        final String nameUnit = pojUnit.getBody().getId().toString();

        // temp.get(0).getId().toString()

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                layout.addComponent(new Label(buduje));
            }
        });
        layout.addComponent(button);
    }

}
