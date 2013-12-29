package medicwebapplication;

import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import consumingrestobjects.Jednostka;
import consumingrestservice.SearchPhrase;

import java.util.ArrayList;

/**
 * @author Rafal Zawadzki
 */
public class UnitDataTableCreate {

    private static Table currentResultsTable = null;

    public static boolean deleteTable(VerticalLayout layout) {
        layout.removeComponent(currentResultsTable);
        currentResultsTable = null;
        MainVaadinUI.setFullLayout(layout);
        return true;
    }

    public static boolean checkIfExistsTable() {
        if (currentResultsTable != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void makeTable(ArrayList<Jednostka> queryResult, VerticalLayout layout) {
        if (checkIfExistsTable()) {
            deleteTable(layout);
        }
        if (SearchPhrase.checkIfNoResultExists()) {
            SearchPhrase.deleteNoResult(layout);
        }
        Table table = new Table("Wyniki wyszukiwania");
        table.setSelectable(true);
        table.setImmediate(true);
        //todo trivial: height of table's row
        table.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                //todo induce window
                System.out.println("Okno jednostki");
            }
        });
        table.addContainerProperty("Nazwa", String.class, null);
        for (Integer i = 0; i < queryResult.size(); i++) {
            table.addItem(new Object[]{queryResult.get(i).getNazwa()}, i);  //todo minor: nazwa jednostki nadrzednej
        }
        layout.addComponent(table);
        layout.setComponentAlignment(table, Alignment.MIDDLE_CENTER);
        currentResultsTable = table;
    }

}
