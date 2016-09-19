/**
 * Created by daphne on 19.09.16.
 */
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyHashMapTest {

    @Test
    public void simple() {
        MyHashMap HashMap = new MyHashMap();
        HashMap.put("hello", "world");
        assertTrue(HashMap.get("hello") == "world");

    }

    @Test
    public void withRebuild() {
        MyHashMap HashMap = new MyHashMap(2);
        HashMap.put("hello", "world");
        HashMap.put("hello", "world");
        assertTrue(HashMap.size() == 1);
        HashMap.clear();
        assertTrue(HashMap.size() == 0);
        HashMap.put("a", "a");
        HashMap.put("b", "b");
        HashMap.put("c", "c");
        HashMap.put("d", "d");
        assertTrue(HashMap.size() == 4);
        assertTrue(HashMap.get("c") == "c");
    }

    @Test
    public void testContains() {
        MyHashMap HashMap = new MyHashMap(100);
        HashMap.put("", "");
        assertTrue(HashMap.contains(""));
        HashMap.remove("");
        assertFalse(HashMap.contains(""));
    }

    @Test
    public void test1() {
        MyHashMap HashMap = new MyHashMap();
        HashMap.clear();
        HashMap.put("", "");
        HashMap.put("", "");
        HashMap.put("a", "b");
        assertTrue(HashMap.size() == 2);
        HashMap.remove("");
        assertTrue(HashMap.size() == 1);
        HashMap.remove("a");
        assertTrue(HashMap.size() == 0);
    }

    @Test
    public void test2() {
        MyHashMap HashMap = new MyHashMap();
        HashMap.put("a", "c");
        HashMap.put("a", "d");
        HashMap.put("a", "b");
        assertTrue(HashMap.size() == 1);
        HashMap.remove("");
        assertTrue(HashMap.size() == 1);
        HashMap.remove("a");
        assertTrue(HashMap.size() == 0);
    }

    @Test
    public void test3() {
        MyHashMap HashMap = new MyHashMap();
        HashMap.put("a", "c");
        HashMap.put("a", "d");
        HashMap.put("a", "b");
        assertTrue(HashMap.size() == 1);
        HashMap.remove("");
        assert(HashMap.remove("hello") == null);
        assert(HashMap.remove("a") == "b");
    }

}