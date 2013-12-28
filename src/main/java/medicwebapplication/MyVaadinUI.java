package medicwebapplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import consumingrestobjects.Jednostka;
import consumingrestservice.SearchPhrase;
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

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        Label label = new Label("Wyszukiwarka Medic");
        label.setWidth(null);
        label.setStyleName("h1");
        label.setHeight("1em");
        label.setId("opisLabel");
        layout.addComponent(label);
        layout.setComponentAlignment(label, Alignment.TOP_CENTER);

        Label spaceUnderLogo = new Label("");
        spaceUnderLogo.setWidth( null );
        spaceUnderLogo.setHeight ( "20px" );
        layout.addComponent(spaceUnderLogo);

        final HorizontalLayout searchTools = new HorizontalLayout();
        layout.addComponent(searchTools);
        layout.setComponentAlignment(searchTools, Alignment.MIDDLE_CENTER);

        String phrase = "";
        final ObjectProperty<String> property = new ObjectProperty<String>(phrase, String.class);
        TextField searchFor = new TextField();
        searchFor.setPropertyDataSource(property);
        searchFor.setMaxLength(30);
        searchFor.setWidth("35em");
        searchTools.addComponent(searchFor);
        searchTools.setComponentAlignment(searchFor, Alignment.TOP_CENTER);

        Button button = new Button("Szukaj");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                //todo new view (table with results
            }
        });
        searchTools.addComponent(button);
        searchTools.setComponentAlignment(button, Alignment.TOP_CENTER);
    }

}