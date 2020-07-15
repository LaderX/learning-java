import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class PhoneBookTests extends TestCase {

    private String actual;
    private long expected;

    public PhoneBookTests(String actual, long expected) {
        this.actual = actual;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: testString({0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"+7 909 123-45-67", 79091234567L},
                {"+7 (909) 1234567", 79091234567L},
                {"+8-905-1234567", 79051234567L},
                {"9-453-1234567", -1},
                {"8-905-123", -1},
                {"905-1234567", 79051234567L},
                {"8-905-12345672342", -1},
                {"Вася", -1}
        });
    }

    @Test
    public void testPhoneAnalyzer() {
        assertEquals(PhoneBook.phoneAnalyzer(actual), expected);
    }
}





