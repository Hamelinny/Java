import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by daphne on 19.10.16.
 */
public class MyTest {
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
        assertEquals(f.apply(2), new Integer(4));
        assertEquals(g.apply(2), new Integer(6));
        Function1 <Integer, Integer> h = g.compose(f);
        assertEquals(h.apply(1), new Integer(6));
    }

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
        assertEquals(f.apply(2, 3), new Integer(6));
        assertEquals(g.apply(2), new Integer(2));
        Function2 <Integer, Integer, Integer> h = f.compose(g);
        assertEquals(h.apply(4, 5), new Integer(20));
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
        assertEquals(g.apply(0), new Integer(3));
        assertEquals(g.apply(5), new Integer(8));

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
        assertEquals(g.apply(0), new Integer(-5));
        assertEquals(g.apply(12), new Integer(7));
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
        assertEquals(g.apply(3).apply(4), new Integer(7));
    }

    @Test
    public void orAndNot() {
        Predicate <Integer> p1 = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x > 1;
            }
        };
        Predicate <Integer> p2 = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x < -1;
            }
        };

        Predicate <Integer> p3 = p1.or(p2);
        assertTrue(p3.apply(new Integer(4)));
        assertTrue(p3.apply(new Integer(-2)));
        assertFalse(p3.apply(new Integer(-1)));
        Predicate <Integer> p4 = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x % 2 == 1;
            }
        };
        Predicate <Integer> p5 = p4.and(p1);
        assertFalse(p5.apply(4));
        assertTrue(p5.apply(3));
        assertFalse(p5.apply(-3));

        Predicate <Integer> p6 = p5.not();
        assertTrue(p6.apply(4));
        assertFalse(p6.apply(3));
        Predicate <Object> p7 = Predicate.ALWAYS_FALSE;
        assertFalse(p7.apply(new Integer(0)));
        p7 = Predicate.ALWAYS_TRUE;
        assertTrue(p7.apply(new Integer(0)));
    }

    @Test
    public void map() {
        ArrayList <Integer> a = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        Function1 <Integer, Integer> f = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x * 2;
            }
        };
        ArrayList <Integer> b = (ArrayList <Integer>) Collections.map(f, a);
        for (int i = 0; i < 5; i++) {
            assertEquals(b.get(i), new Integer((i + 1) * 2));
        }
    }

    @Test
    public void filter() {
        ArrayList <Integer> a = new ArrayList<Integer>(Arrays.asList(1, 2, 0, 8, 11));
        Predicate <Integer> f = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x % 2 == 0;
            }
        };
        ArrayList <Integer> b = Collections.filter(f, a);
        assertEquals(b.size(), 3);
        assertEquals((long)b.get(0), 2);
        assertEquals((long)b.get(1), 0);
        assertEquals((long)b.get(2), 8);
    }

    @Test
    public void take() {
        ArrayList <Integer> a = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        Predicate <Integer> f = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x < 3;
            }
        };
        Predicate <Integer> g = f.not();
        ArrayList <Integer> ans1 = Collections.takeWhile(f, a);
        ArrayList <Integer> ans2 = Collections.takeUnless(g, a);
        assertEquals(ans1, ans2);
    }

    @Test
    public void foldl() {
        ArrayList <Integer> a = new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2));
        Function2 <Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x / y;
            }
        };
        Integer ans = Collections.foldl(f, new Integer(32), a);
        assertEquals(ans, new Integer(2));
    }

    @Test
    public void foldr() {
        ArrayList <Integer> a = new ArrayList<Integer>(Arrays.asList(4, 4, 4, 2));
        Function2 <Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x / y;
            }
        };
        Integer ans = Collections.foldr(f, new Integer(1), a);
        assertEquals(ans, new Integer(2));
    }
}
