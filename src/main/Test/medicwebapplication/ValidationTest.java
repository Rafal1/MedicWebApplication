package medicwebapplication;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Rafal Zawadzki
 */
public class ValidationTest {
    @Test
    public void testIsSpecialChar() throws Exception {
        ArrayList<String> examples = new ArrayList<String>();
        examples.add("h!j");
        examples.add("po#");
        examples.add("38y vn3hy3hnvy3i");

        Boolean sp1 = Validation.isSpecialChar(examples.get(0));
        Boolean sp2 = Validation.isSpecialChar(examples.get(1));
        Boolean corr = Validation.isSpecialChar(examples.get(2));

        assertEquals(false, sp1);
        assertEquals(false, sp2);
        assertEquals(true, corr);
    }

    @Test
    public void testIsminimalLenght() throws Exception {
        ArrayList<String> examples = new ArrayList<String>();
        examples.add("x");
        examples.add("xxx");
        Boolean shortS = Validation.isminimalLenght(examples.get(0));
        Boolean shortS1 = Validation.isminimalLenght(examples.get(1));

        assertEquals(false, shortS);
        assertEquals(true, shortS1);

    }

    @Test
    public void testIsmaximalLenght() throws Exception {
        ArrayList<String> examples = new ArrayList<String>();
        examples.add("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");//115
        examples.add("xxx");
        Boolean longS = Validation.ismaximalLenght(examples.get(0));
        Boolean longS1 = Validation.ismaximalLenght(examples.get(1));

        assertEquals(false, longS);
        assertEquals(true, longS1);
    }

}
