package medicwebapplication;

import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import consumingrestobjects.Jednostka;

import java.util.ArrayList;

/**
 * @author Rafal Zawadzki
 */
public class DataUnitCreate {

    public static boolean deleteTable() {
        return false;
    }

    public static boolean checkIfExistsTable() {
        return false;
    }

    public static boolean checkNumberOfPages() {
        return false;
    }

    public static boolean displayPages() {
        return false;
    }

    public static void makeTable(ArrayList<Jednostka> queryResult, VerticalLayout layout) {
        //todo check old table remove if necessary

        Table table = new Table("Wyniki wyszukiwania");
        table.setSelectable(true);
        table.setImmediate(true);
        table.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                //todo induce window
                System.out.println("Okno jednostki");
            }
        });
        table.addContainerProperty("Nazwa", String.class, null);
        //todo return no result service
        for (Integer i = 0; i < queryResult.size(); i++) {
            table.addItem(new Object[]{queryResult.get(i).getNazwa()}, i);  //todo nazwa jednostki nadrzednej
        }
        layout.addComponent(table);
        layout.setComponentAlignment(table, Alignment.MIDDLE_CENTER);
    }
}
