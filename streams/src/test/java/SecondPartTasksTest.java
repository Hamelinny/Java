
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static sp.SecondPartTasks.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        List<String> paths = Arrays.asList("src/test/resources/01.txt",
                "src/test/resources/02.txt",
                "src/test/resources/03.txt"
        );
        List <String> quotes = Arrays.asList("TEST", "O Romeo, Romeo, wherefore art thou Romeo? TEST");
        assertEquals(quotes, findQuotes(paths, "TEST"));
    }

    @Test
    public void testPiDividedBy4() {

        assertEquals(Math.PI / 4, piDividedBy4(), 1e-3);
    }

    @Test
    public void testFindPrinter() {

        Map<String, List <String> > mp = new HashMap<>();
        mp.put("a", Arrays.asList("a"));
        mp.put("b", Arrays.asList("b", "b"));
        mp.put("c", Arrays.asList("cc"));
        mp.put("d", Arrays.asList("d", "dd", "d"));
        assertEquals("d", findPrinter(mp));

    }

    @Test
    public void testCalculateGlobalOrder() {
        Map<String, Integer> mp1 = new HashMap<>();
        mp1.put("a", 0);
        mp1.put("b", 1);
        Map<String, Integer> mp2 = new HashMap<>();
        mp2.put("a", 1);
        mp2.put("b", 1);
        mp2.put("c", 1);
        Map<String, Integer> mp3 = new HashMap<>();
        mp3.put("d", 5);
        mp3.put("b", 1);
        Map<String, Integer> ans = new HashMap<>();
        ans.put("a", 1);
        ans.put("b", 3);
        ans.put("c", 1);
        ans.put("d", 5);
        assertEquals(ans, calculateGlobalOrder(Arrays.asList(mp1, mp2, mp3)));
    }
}