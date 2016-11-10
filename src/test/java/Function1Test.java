import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by daphne on 19.10.16.
 */
public class Function1Test {
    @Test
    public void compose1() {
        Function1 <Integer, Integer> f = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return 2 * x;
            }
        };
        Function1 <Integer, Integer> g = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return 3 * x;
            }
        };
        assertEquals((long)f.apply(2), 4);
        assertEquals((long)g.apply(2), 6);
        Function1 <Integer, Integer> h = g.compose(f);
        assertEquals((long)h.apply(1), 6);
    }

}
