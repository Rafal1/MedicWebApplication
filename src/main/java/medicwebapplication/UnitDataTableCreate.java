package medicwebapplication;

import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.*;
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

    public static void makeTable(final ArrayList<Jednostka> queryResult, VerticalLayout layout) { //todo veryfing if final right choice
        if (checkIfExistsTable()) {
            deleteTable(layout);
        }
        if (SearchPhrase.checkIfNoResultExists()) {
            SearchPhrase.deleteNoResult(layout);
        }
        //todo info koniec wyszukiwania
        Panel shortOut = new Panel();
        final Table table = new Table("Wyniki wyszukiwania");
        table.setSelectable(true);
        table.setImmediate(true);
        table.removeAllActionHandlers();

        //todo trivial: height of table's row
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                new UnitSubWindow(table.getValue(), queryResult);
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
