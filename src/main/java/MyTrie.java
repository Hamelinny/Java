import com.sun.xml.internal.txw2.output.StreamSerializer;

/**
 * Created by daphne on 04.10.16.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class MyTrie implements Trie, StreamSerializable {

    private Node root;

    public MyTrie() {
        root = new Node();
    }

    public boolean add(String element) {
        Node cur = root;
        for (int i = 0; i < element.length(); i++) {
            if (!cur.isEdge(element.charAt(i))) {
                cur.edges.put(element.charAt(i), new Node());
            }
            cur = cur.next(element.charAt(i));
        }
        if (cur.IsTerminalFor) {
            return false;
        }
        cur.IsTerminalFor = true;
        cur.IsTerminalAll++;
        cur = root;
        for (int i = 0; i < element.length(); i++) {
            cur.IsTerminalAll++;
            cur = cur.next(element.charAt(i));
        }
        return true;
    };

    public boolean contains(String element) {
        Node cur = root;
        for (int i = 0; i < element.length(); i++) {
            if (!cur.isEdge(element.charAt(i))) {
                return false;
            }
            cur = cur.next(element.charAt(i));
        }
        return cur.IsTerminalFor;
    };


    public boolean remove(String element) {
        Node cur = root;
        for (int i = 0; i < element.length(); i++) {
            if (!cur.isEdge(element.charAt(i))) {
                return false;
            }
            cur = cur.next(element.charAt(i));
        }
        if (cur.IsTerminalFor) {
            cur.IsTerminalFor = false;
            cur = root;
            cur.IsTerminalAll--;
            for (int i = 0; i < element.length(); i++) {
                cur.next(element.charAt(i)).IsTerminalAll--;
                if (cur.next(element.charAt(i)).IsTerminalAll == 0) {
                    cur.edges.remove(element.charAt(i));
                    break;
                }
                cur = cur.next(element.charAt(i));
            }
            return true;
        }
        return false;
    };


    public int size() {
        return root.IsTerminalAll;
    };


    public int howManyStartsWithPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!cur.isEdge(prefix.charAt(i))) {
                return 0;
            }
            cur = cur.next(prefix.charAt(i));
        }
        return cur.IsTerminalAll;
    };


    public void serialize(OutputStream out) throws IOException {
        dfsOut(root, out);
    }


    public void deserialize(InputStream in) throws IOException {
        root = new Node();
        dfsIn(root, in);
    }


    private void dfsOut(Node cur, OutputStream out) throws IOException {
        if (cur.IsTerminalFor)
            out.write(1);
        for (char c = 'a'; c <= 'z'; c++) {
            if (cur.isEdge(c)) {
                out.write(0);
                out.write(c - 'a');
                dfsOut(cur.next(c), out);
                out.write(2);
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (cur.isEdge(c)) {
                out.write(0);
                out.write(c - 'A' + 26);
                dfsOut(cur.next(c), out);
                out.write(2);
            }
        }
    }

    private void dfsIn(Node cur, InputStream in) throws IOException {
        int bt = in.read();
        while (bt != -1 && bt != 2) {
            if (bt == 0) {
                bt = in.read();
                char c = (char)bt;
                if (c >= 26)
                    c = (char)(c - 26 + 'A');
                else
                    c = (char)(c + 'a');
                Node nxt = new Node();
                cur.edges.put(c, nxt);
                dfsIn(nxt, in);
                cur.IsTerminalAll += nxt.IsTerminalAll;
            }
            else if (bt == 1) {
                cur.IsTerminalFor = true;
                cur.IsTerminalAll++;
            }
            bt = in.read();
        }
    }

    private class Node {
        private HashMap<Character, Node> edges;
        private boolean IsTerminalFor;
        private int IsTerminalAll;

        private Node() {
            edges = new HashMap();
            IsTerminalFor = false;
            IsTerminalAll = 0;
        }

        private boolean isEdge(Character c) {
            if (edges.get(c) != null)
                return true;
            return false;
        }


        private Node next(Character c) {
            return edges.get(c);
        }

    }

}
