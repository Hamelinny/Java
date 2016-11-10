import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by daphne on 19.10.16.
 */
public class PredicateTest {

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
        assertTrue(p3.apply(4));
        assertTrue(p3.apply(-2));
        assertFalse(p3.apply(-1));
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
        assertFalse(p7.apply(0));
        p7 = Predicate.ALWAYS_TRUE;
        assertTrue(p7.apply(0));
    }

    @Test
    public void wildcardAndLazy() throws IllegalArgumentException {
        Predicate <Object> p1 = new Predicate<Object>() {
            @Override
            public Boolean apply(Object x) {
                throw new IllegalArgumentException();
            }
        };
        Predicate <Integer> p2 = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x > 0;
            }
        };
        assertTrue(p2.or(p1).apply(1));
        assertFalse(p2.and(p1).apply(0));
    }

}
