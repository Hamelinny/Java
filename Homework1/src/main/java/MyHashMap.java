/**
 * Created by daphne on 19.09.16.
 */
public class MyHashMap {

    protected class Node {
        Node r;
        String key, val;
        int len;
        Node(String k, String v) {
            r = null;
            val = v;
            key = k;
            len = 1;
        }
        protected void setR(Node x) {
            r = x;
            x.len = len + 1;
        }
        protected Node getR() {
            Node cur = this;
            while (cur.r != null) {
                cur = cur.r;
            }
            return cur;
        }

    }


    private int capacity, sz, szkey;
    private Node map[];

    MyHashMap(int cap) {
        capacity = cap + 1;
        sz = 0;
        szkey = 0;
        map = new Node[capacity];
    }

    MyHashMap() {
        capacity = (int)1e5;
        sz = 0;
        szkey = 0;
        map = new Node[capacity];
    }

    public void copy(MyHashMap x) {
        capacity = x.capacity;
        sz = x.sz;
        szkey = x.szkey;
        map = x.map;
    }

    public void rebuild() {
        MyHashMap cur = new MyHashMap(2 * capacity);
        for (int i = 0; i < capacity; i++) {
            Node c = map[i];
            while (c != null) {
                cur.put(c.key, c.val);
                c = c.r;
            }
        }
        copy(cur);
    }


    public int size() {
        return szkey;
    }

    public boolean contains(String key) {
        int hashOf = key.hashCode() % capacity;
        Node cur = map[hashOf];
        if (cur != null) {
            if (cur.val == key)
                return true;
        }
        return false;
    }

    public String get(String key) {
        int hashOf = key.hashCode() % capacity;
        Node cur = map[hashOf];
        if (cur != null) {
            return cur.val;
        }
        return null;
    }

    public String put(String key, String value) {
        if (sz * 2 >= capacity) {
            rebuild();
        }
        int hashOf = key.hashCode() % capacity;
        sz++;
        if (map[hashOf] == null) {
            szkey++;
            map[hashOf] = new Node(key, value);
            return null;
        }
        else {
            Node cur = map[hashOf].getR();
            cur.r = new Node(key, value);
            return cur.val;
        }

    }

    public String remove(String key) {
        int hashOf = key.hashCode() % capacity;
        if (map[hashOf] == null)
            return null;
        else {
            int len = map[hashOf].getR().len;
            sz -= len;
            szkey--;
            String cur = map[hashOf].getR().val;
            map[hashOf] = null;
            return cur;
        }
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            map[i] = null;
        }
        sz = 0;
        szkey = 0;
    }
}