import org.junit.Test;
import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by daphne on 04.10.16.
 */
public class TestMyTrie {
    @Test
    public void contains() {
        MyTrie trie = new MyTrie();
        trie.add("hello");
        trie.add("world");
        assertTrue(trie.contains("hello"));
        assertTrue(trie.contains("world"));
        trie.remove("hello");
        trie.remove("world");
        assertFalse(trie.contains("hello"));
        assertFalse(trie.contains("world"));
    }

    @Test
    public void size() {
        MyTrie trie = new MyTrie();
        trie.add("");
        trie.add("");
        assertEquals(1, trie.size());
        assertTrue(trie.contains(""));
        trie.remove("");
        assertEquals(0, trie.size());
        trie.remove("hello");
        trie.remove("world");
        assertFalse(trie.contains("hello"));
        assertFalse(trie.contains("world"));
    }

    @Test
    public void howMany() {
        MyTrie trie = new MyTrie();
        trie.add("a");
        trie.add("aa");
        trie.add("aaa");
        trie.remove("aa");
        assertEquals(2, trie.howManyStartsWithPrefix("a"));
        trie.remove("a");
        assertEquals(1, trie.howManyStartsWithPrefix("a"));
    }

    @Test
    public void inOut() throws IOException {
        MyTrie trie = new MyTrie();
        trie.add("hello");
        trie.add("World");
        trie.add("Java");
        trie.add("hell");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        trie.serialize((OutputStream)out);
        trie.remove("hello");
        trie.remove("World");
        trie.remove("Java");
        trie.remove("hell");
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        trie.deserialize(in);
        assertEquals(4, trie.size());
        assertTrue(trie.contains("hell"));
        assertEquals(2, trie.howManyStartsWithPrefix("he"));
        assertTrue(trie.contains("hello"));
        assertTrue(trie.contains("World"));
        assertTrue(trie.contains("Java"));
    }

    @Test
    public void simple() {
        MyTrie trie = new MyTrie();
        assertEquals(0, trie.howManyStartsWithPrefix(""));
        assertEquals(0, trie.howManyStartsWithPrefix("a"));
        trie.add("aaaa");
        assertFalse(trie.contains("aaa"));
        assertFalse(trie.remove("a"));
        assertEquals(1, trie.size());
    }

    @Test
    public void alphabet() throws IOException {
        MyTrie trie = new MyTrie();
        trie.add("qwertyuiopasdfghjklzxcvbnm");
        trie.add("QWERTYUIOPASDFGHJKLZXCVBNM");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        trie.serialize((OutputStream)out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        trie.deserialize(in);
        assertEquals(2, trie.size());
        assertTrue(trie.remove("qwertyuiopasdfghjklzxcvbnm"));
        assertFalse(trie.add("QWERTYUIOPASDFGHJKLZXCVBNM"));
    }


}
