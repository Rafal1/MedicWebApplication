package medicwebapplication;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import consumingrestobjects.Adres;
import consumingrestobjects.Jednostka;
import consumingrestservice.SearchPhrase;

import java.util.ArrayList;

/**
 * @author Rafal Zawadzki
 */
@Theme("mytheme")
public class UnitSubWindow extends Window {
    public UnitSubWindow(Object nr, ArrayList<Jednostka> queryResponse) {
        //todo if subWindow initialized - don't do it again (for the smae Unit)
        super("Widok Jednostki"); // Set window caption
        center();
        //todo test na numer Junit
        if (nr == null) {
            return;
        }
        Integer nrCorrect = prepareNumberOfUnit(nr);
        composeUnitElements(nrCorrect, queryResponse);
        UI.getCurrent().addWindow(this);         //todo trivial: what's the name of UI.getCurrent?
    }

    private void composeUnitElements(Integer nr, ArrayList<Jednostka> queryResponse) {
        Jednostka unit = queryResponse.get(nr); //nr - like array's subscript
        Adres addr = SearchPhrase.getAdresByID(unit.getId());
        Jednostka overUnit = SearchPhrase.getJednostkaByID(unit.getId());
        String overUnitName = "Brak jednostki nadrzędnej";
        if (overUnit.getNadrzednaJednostka() != 0) { //there is no row in database's table with 0 index
            overUnitName = overUnit.getNazwa();
        }
        setWidth("640px");

        VerticalLayout content = new VerticalLayout();
        content.setWidth("600px");
        content.setMargin(true);
        setContent(content);

        //todo bind CSS style
        Label unitName = new Label(unit.getNazwa());
        unitName.setStyleName("unitDesc");
        unitName.setId("unitDesc");
        unitName.setHeight("2em");
        content.addComponent(unitName);
        Label breakSpHeader = new Label();
        breakSpHeader.setHeight("5px");
        content.addComponent(breakSpHeader);

        HorizontalLayout contactInfo = new HorizontalLayout();
        content.addComponent(contactInfo);

        VerticalLayout leftContactInfo = new VerticalLayout();
        contactInfo.addComponent(leftContactInfo);

        VerticalLayout rightContactInfo = new VerticalLayout();
        contactInfo.addComponent(rightContactInfo);

        Panel leftPanel = new Panel();
        leftPanel.setSizeUndefined();
        leftContactInfo.addComponent(leftPanel);
        FormLayout leftForm = new FormLayout();
        leftForm.setSizeUndefined();
        leftForm.setMargin(true);

        //todo PL/EN nazewnictwo
        Label unitUl = new Label("Ul: " + addr.getUlica());
        leftForm.addComponent(unitUl);
        Label unitNrDomu = new Label("Nr domu: " + addr.getNrDomu());
        leftForm.addComponent(unitNrDomu);
        Label unitMiasto = new Label("Miasto: " + addr.getMiasto());
        leftForm.addComponent(unitMiasto);
        Label unitKodPocztowy = new Label("Kod: " + addr.getKodPocztowy());
        leftForm.addComponent(unitKodPocztowy);
        Label unitDopisekInfo = new Label("Dopisek: ");
        leftForm.addComponent(unitDopisekInfo);
        Label unitDopisek = new Label(addr.getDopisek());
        leftForm.addComponent(unitDopisek);

        Label unitUpdateDate = new Label("Data aktualizacji: " + unit.getDataAktualizacji().toString());
        leftForm.addComponent(unitUpdateDate);
        Label unitJednostkaNadrzednaInfo = new Label("Jednostka nadrzędna: ");
        leftForm.addComponent(unitJednostkaNadrzednaInfo);
        Label unitJednostkaNadrzedna = new Label(overUnitName); //todo link to see datails, AND TEST
        leftForm.addComponent(unitJednostkaNadrzedna);
        leftPanel.setContent(leftForm);

        Panel rightPanel = new Panel();
        rightPanel.setSizeUndefined(); // Shrink to fit content
        rightContactInfo.addComponent(rightPanel);
        FormLayout rightForm = new FormLayout();
        rightForm.setSizeUndefined();
        rightForm.setMargin(true);

        Label unitTel = new Label("Telefon: " + unit.getTelefon());
        rightForm.addComponent(unitTel);
        Label unitTelOpisInfo = new Label("Opis telefonu: ");
        rightForm.addComponent(unitTelOpisInfo);
        Label unitTelOpis = new Label(unit.getTelOpis());
        rightForm.addComponent(unitTelOpis);
        Label unitWWW = new Label("Strona: " + unit.getWww());
        rightForm.addComponent(unitWWW);
        Label unitEmail = new Label("E-mail: " + unit.getEmail());
        rightForm.addComponent(unitEmail);
        Label unitGodzinyPracyInfo = new Label("Godziny pracy: ");
        rightForm.addComponent(unitGodzinyPracyInfo);
        Label unitGodzinyPracy = new Label(unit.getGodzinyPracy());
        rightForm.addComponent(unitGodzinyPracy);
        rightPanel.setContent(rightForm);

        Label breakSpPanels = new Label();
        breakSpPanels.setHeight("5px");
        content.addComponent(breakSpPanels);
        Panel centerPanel = new Panel();
        centerPanel.setSizeUndefined();
        content.addComponent(centerPanel);
        FormLayout centerForm = new FormLayout();
        centerForm.setSizeUndefined();
        centerForm.setMargin(true);

        TextArea areaOpis = new TextArea("Opis: ");
        areaOpis.setValue(unit.getOpis());
        areaOpis.setReadOnly(true);
        areaOpis.setWidth("430px");
        areaOpis.setRows(6);
        centerForm.addComponent(areaOpis);
        Label breakSp = new Label();
        breakSp.setHeight("5px");
        centerForm.addComponent(breakSp);

        HorizontalLayout bottom = new HorizontalLayout();
        centerForm.addComponent(bottom);
        TextArea areaDodOpis = new TextArea("Dodatkowe: ");
        areaDodOpis.setValue(unit.getInfoDodatkowe());
        areaDodOpis.setReadOnly(true);
        areaDodOpis.setWidth("300px");
        areaDodOpis.setRows(3);
        bottom.addComponent(areaDodOpis);
        TextArea areaSpec = new TextArea("Specjalizacje: ");
        areaSpec.setValue(unit.getSpecjalizacja1()+ "\n" + unit.getSpecjalizacja2() + "\n" + unit.getSpecjalizacja3());
        areaSpec.setReadOnly(true);
        areaSpec.setWidth("100px");
        areaSpec.setRows(3);
        bottom.addComponent(areaSpec);
        centerPanel.setContent(centerForm);

    }

    public static Integer prepareNumberOfUnit(Object nr) {
        if (nr instanceof Integer && nr != null) {
            return (Integer) nr;
        }
        if (nr instanceof String) {
            Integer tmp = Integer.parseInt((String) nr);
            if (tmp != null) {
                return tmp;
            }
        }
        return null;
    }

}