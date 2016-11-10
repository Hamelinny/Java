import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by daphne on 19.10.16.
 */
public class Function2Test {

    @Test
    public void compose2() {
        Function2 <Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x * y;
            }
        };
        Function1 <Integer, Integer> g = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x;
            }
        };
        assertEquals((long)f.apply(2, 3), 6);
        assertEquals((long)g.apply(2), 2);
        Function2 <Integer, Integer, Integer> h = f.compose(g);
        assertEquals((long)h.apply(4, 5), 20);
    }

    @Test
    public void bind1() {
        Function2 <Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x + y;
            };
        };
        Function1 <Integer, Integer> g = f.bind1(3);
        assertEquals((long)g.apply(0), 3);
        assertEquals((long)g.apply(5), 8);

    }

    @Test
    public void bind2() {
        Function2 <Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x - y;
            };
        };
        Function1 <Integer, Integer> g = f.bind2(5);
        assertEquals((long)g.apply(0), -5);
        assertEquals((long)g.apply(12), 7);
    }

    @Test
    public void curry() {
        Function2 <Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x + y;
            }
        };
        Function1 <Integer, Function1 <Integer, Integer> > g = f.curry();
        assertEquals((long)g.apply(3).apply(4), 7);
    }

}
