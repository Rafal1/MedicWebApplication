package medicwebapplication;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import consumingrestobjects.Jednostka;

import java.util.ArrayList;

/**
 * @author Rafal Zawadzki
 */
public class UnitSubWindow {
    public void initSubWindow(ArrayList<Jednostka> reponseRequest, VerticalLayout layout){
        Window subWindow = new Window("Sub-window");
        VerticalLayout subContent = new VerticalLayout();
        subContent.setMargin(true);
        subWindow.setContent(subContent);
        // Put some components in it
        subContent.addComponent(new Label("Meatball sub"));
        subContent.addComponent(new Button("Awlright"));
        // Center it in the browser window
        subWindow.center();
        layout.addComponent(subWindow);
    }
}
