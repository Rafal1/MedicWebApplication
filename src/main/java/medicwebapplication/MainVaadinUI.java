package medicwebapplication;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import consumingrestservice.SearchPhrase;

import javax.servlet.annotation.WebServlet;

@Theme("mytheme") //mytheme by default
@SuppressWarnings("serial")
public class MainVaadinUI extends UI {
    private static VerticalLayout fullLayout = null;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MainVaadinUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setWidth("1366px");
        setContent(layout);

        Label label = new Label("Wyszukiwarka Medic");
        label.setWidth(null);
        label.setStyleName("h1");
        label.setHeight("1em");
        label.setId("opisLabel");
        layout.addComponent(label);
        layout.setComponentAlignment(label, Alignment.TOP_CENTER);

        Label spaceUnderLogo = new Label("");
        spaceUnderLogo.setWidth(null);
        spaceUnderLogo.setHeight("20px");
        layout.addComponent(spaceUnderLogo);

        final HorizontalLayout searchTools = new HorizontalLayout();
        layout.addComponent(searchTools);
        layout.setComponentAlignment(searchTools, Alignment.MIDDLE_CENTER);

        String phrase = "";
        final ObjectProperty<String> property = new ObjectProperty<String>(phrase, String.class);
        TextField searchFor = new TextField();
        searchFor.setPropertyDataSource(property);
        searchFor.setMaxLength(100);
        searchFor.focus();
        searchFor.setWidth("40em");
        searchTools.addComponent(searchFor);
        searchTools.setComponentAlignment(searchFor, Alignment.TOP_CENTER);

        Button button = new Button("Szukaj");
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if(property.getValue() != null && !property.getValue().isEmpty() ){ //todo is it a proper behaviour for empty string?
                    SearchPhrase.search(property.getValue(), layout);
                }
            }
        });
        searchTools.addComponent(button);
        searchTools.setComponentAlignment(button, Alignment.TOP_CENTER);
        //todo checkbox for "all word" mode

        Label spaceUnderSearch = new Label("");
        spaceUnderSearch.setWidth(null);
        spaceUnderSearch.setHeight("40px");
        layout.addComponent(spaceUnderSearch);

    }

    public static VerticalLayout getFullLayout() {
        return fullLayout;
    }

    public static void setFullLayout(VerticalLayout fullLayout) {
        MainVaadinUI.fullLayout = fullLayout;
    }

}