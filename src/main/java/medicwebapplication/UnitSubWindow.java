package medicwebapplication;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import consumingrestobjects.Jednostka;

import java.util.ArrayList;

/**
 * @author Rafal Zawadzki
 */
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
        Jednostka unit = queryResponse.get(nr);
        setWidth("680px");

        VerticalLayout content = new VerticalLayout();
        content.setWidth("600px");
        content.setMargin(true);
        setContent(content);

        TextArea areaNazwa = new TextArea();
        areaNazwa.setValue(unit.getNazwa());
        areaNazwa.setWidth("600px");
        areaNazwa.setId("unitHeader");
        areaNazwa.setRows(2);
        areaNazwa.isReadOnly();
        content.addComponent(areaNazwa);
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

        Label unitAdres = new Label("Brak obsługi adresu");
        leftForm.addComponent(unitAdres);
        Label unitUpdateDate = new Label("Data aktualizacji: " + unit.getDataAktualizacji().toString());
        leftForm.addComponent(unitUpdateDate);
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
        breakSpPanels.setHeight("1em");
        content.addComponent(breakSpPanels);
        Panel centerPanel = new Panel();
        centerPanel.setSizeUndefined();
        content.addComponent(centerPanel);
        FormLayout centerForm = new FormLayout();
        centerForm.setSizeUndefined();
        centerForm.setMargin(true);

        TextArea areaOpis = new TextArea("Opis: ");
        areaNazwa.isReadOnly();
        areaOpis.setValue(unit.getOpis());
        areaOpis.setWidth("430px");
        areaOpis.setRows(6);
        centerForm.addComponent(areaOpis);
        Label breakSp = new Label();
        breakSp.setHeight("1em");
        centerForm.addComponent(breakSp);
        TextArea areaDodOpis = new TextArea("Dodatkowe: ");
        areaDodOpis.isReadOnly();
        areaDodOpis.setValue(unit.getInfoDodatkowe());
        areaDodOpis.setWidth("200px");
        areaDodOpis.setRows(3);
        centerForm.addComponent(areaDodOpis);
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