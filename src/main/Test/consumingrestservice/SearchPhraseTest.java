package consumingrestservice;

import consumingrestobjects.Adres;
import consumingrestobjects.Jednostka;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Rafal Zawadzki
 */
public class SearchPhraseTest {
    @Test
    public void testGetUnitsByPhrase() {
        ArrayList<Jednostka> res = SearchPhrase.getUnitsByPhrase("Centralny Szpital Kliniczny MSW w Warszawie");
        String resName = res.get(0).getNazwa();
        String exp = "Centralny Szpital Kliniczny MSW w Warszawie";
        assertEquals(exp, resName);
    }

    @Test
    public void testGetAdresByID() {
        Adres res = SearchPhrase.getAdresByID(1);
        String resSt = res.getUlica();
        String exp = "Wołoska";
        assertEquals(exp, resSt);
    }

    @Test
    public void testGetJednostkaByID() {
        Jednostka res = SearchPhrase.getJednostkaByID(1); //brak nadrzednej
        String resName = res.getNazwa();
        String exp = "null";
        assertEquals(exp, resName);
    }
    //todo z nadrzedna jednostka
}
