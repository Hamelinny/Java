import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by daphne on 19.10.16.
 */
public class CollectionsTest {

    @Test
    public void map() {
        List <Integer> a = Arrays.asList(1, 2, 3, 4, 5);
        Function1 <Integer, Integer> f = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x * 2;
            }
        };
        List <Integer> b = Collections.map(f, a);
        for (int i = 0; i < 5; i++) {
            assertEquals((long)b.get(i), (i + 1) * 2);
        }
    }

    @Test
    public void filter() {
        List <Integer> a = Arrays.asList(1, 2, 0, 8, 11);
        Predicate <Integer> f = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x % 2 == 0;
            }
        };
        List <Integer> b = Collections.filter(f, a);
        assertEquals(Arrays.asList(2, 0, 8), b);
    }

    @Test
    public void take() {
        List <Integer> a = Arrays.asList(1, 2, 3, 4, 5);
        Predicate <Integer> f = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x < 3;
            }
        };
        Predicate <Integer> g = f.not();
        List <Integer> ans1 = Collections.takeWhile(f, a);
        List <Integer> ans2 = Collections.takeUnless(g, a);
        assertEquals(ans1, ans2);
    }

    @Test
    public void foldl() {
        List <Integer> a = Arrays.asList(2, 2, 2, 2);
        Function2 <Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x / y;
            }
        };
        long ans = Collections.foldl(f, 32, a);
        assertEquals(ans, 2);
    }

    @Test
    public void foldr() {
        List <Integer> a = Arrays.asList(4, 4, 4, 2);
        Function2 <Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x / y;
            }
        };
        long ans = Collections.foldr(f, 1, a);
        assertEquals(ans, 2);
    }

    @Test
    public void fold() {
        List <Integer> a = Arrays.asList(1, 2, 3, 4);
        Function2 <Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return y;
            }
        };
        Integer ans1 = Collections.foldr(f, 1, a);
        Integer ans2 = Collections.foldl(f, 1, a);
        assertNotEquals(ans1, ans2);
    }
}
