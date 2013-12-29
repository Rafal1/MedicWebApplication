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

        VerticalLayout content = new VerticalLayout();
        content.setMargin(true);
        setContent(content);
        Label unitName = new Label(unit.getNazwa());
        content.addComponent(unitName);

        HorizontalLayout contactInfo = new HorizontalLayout();
        content.addComponent(contactInfo);

        VerticalLayout leftContactInfo = new VerticalLayout();
        contactInfo.addComponent(leftContactInfo);

        VerticalLayout rightContactInfo = new VerticalLayout();
        contactInfo.addComponent(rightContactInfo);

        Label unitAdres = new Label("Brak obsługi adresu");
        leftContactInfo.addComponent(unitAdres);
        Label unitUpdateDate = new Label(unit.getDataAktualizacji().toString());
        leftContactInfo.addComponent(unitUpdateDate);
        Label unitTel = new Label(unit.getTelefon());
        rightContactInfo.addComponent(unitTel);
        Label unitTelOpis = new Label(unit.getTelOpis());
        rightContactInfo.addComponent(unitTelOpis);
        Label unitWWW = new Label(unit.getWww());
        rightContactInfo.addComponent(unitWWW);
        Label unitEmail = new Label(unit.getEmail());
        rightContactInfo.addComponent(unitEmail);
        Label unitGodzinyPracy = new Label(unit.getGodzinyPracy());
        rightContactInfo.addComponent(unitGodzinyPracy);

        //todo panele do wyglądu, odstepy
        Label unitOpis = new Label(unit.getOpis());
        content.addComponent(unitOpis);
        Label unitInfoDodatkowe = new Label(unit.getInfoDodatkowe());
        content.addComponent(unitInfoDodatkowe);

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
