/**
 * Created by daphne on 19.09.16.
 */
import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashMapTest {

    @Test
    public void simple() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put("hello", "world");
        assertEquals("world", hashMap.get("hello"));
    }

    @Test
    public void withRebuild() {
        MyHashMap hashMap = new MyHashMap(2);
        hashMap.put("hello", "world");
        hashMap.put("hello", "world");
        assertEquals(1, hashMap.size());
        hashMap.clear();
        assertEquals(0, hashMap.size());
        hashMap.put("a", "a");
        hashMap.put("b", "b");
        hashMap.put("c", "c");
        hashMap.put("d", "d");
        assertEquals(4, hashMap.size());
        assertEquals("c", hashMap.get("c"));
    }

    @Test
    public void testContains() {
        MyHashMap hashMap = new MyHashMap(100);
        hashMap.put("", "");
        assertTrue(hashMap.contains(""));
        hashMap.remove("");
        assertFalse(hashMap.contains(""));
    }

    @Test
    public void test1() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.clear();
        hashMap.put("", "");
        hashMap.put("", "");
        hashMap.put("a", "b");
        assertEquals(2, hashMap.size());
        hashMap.remove("");
        assertEquals(1, hashMap.size());
        hashMap.remove("a");
        assertEquals(0, hashMap.size());
    }

    @Test
    public void test2() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put("a", "c");
        hashMap.put("a", "d");
        hashMap.put("a", "b");
        assertEquals(1, hashMap.size());
        hashMap.remove("");
        assertEquals(1, hashMap.size());
        hashMap.remove("a");
        assertEquals(0, hashMap.size());
    }

    @Test
    public void test3() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put("a", "c");
        hashMap.put("a", "d");
        hashMap.put("a", "b");
        assertEquals(1, hashMap.size());
        hashMap.remove("");
        assertNull(hashMap.remove("hello"));
        assertEquals("b", hashMap.remove("a"));
    }

}