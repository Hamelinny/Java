/**
 * Created by daphne on 19.09.16.
 */
public class MyHashMap {

    private int capacity, szkey;
    private Node map[];

    public MyHashMap(int cap) {
        capacity = cap;
        szkey = 0;
        map = new Node[capacity];
    }



    public MyHashMap() {
        MyHashMap cur = new MyHashMap((int)1e5);
        copy(cur);
    }



    public int size() {
        return szkey;
    }

    public boolean contains(String key) {
        int hashOf = getHash(key);
        Node cur = map[hashOf];
        while (cur != null) {
            if (cur.key.equals(key)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public String get(String key) {
        int hashOf = getHash(key);
        Node cur = map[hashOf];
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur.val;
            }
            cur = cur.next;
        }
        return null;
    }

    public String put(String key, String value) {
        if (szkey * 2 >= capacity) {
            rebuild();
        }
        int hashOf = getHash(key);
        Node cur = map[hashOf];
        if (cur == null) {
            map[hashOf] = new Node(key, value);
            szkey++;
            return null;
        }
        while (cur != null) {
            if (cur.key.equals(key)) {
                String prev = cur.val;
                cur.val = value;
                return prev;
            }
            cur = cur.next;
        }
        cur = map[hashOf].getLast();
        cur.addElem(new Node(key, value));
        szkey++;
        return null;
    }

    public String remove(String key) {
        int hashOf = getHash(key);
        Node cur = map[hashOf];
        if (cur == null) {
            return null;
        }
        if (cur.key.equals(key)) {
            map[hashOf] = map[hashOf].next;
            szkey--;
            return cur.val;
        }
        while (cur.next != null) {
            if (cur.next.key.equals(key)) {
                String prev = cur.next.val;
                cur.next = cur.next.next;
                szkey--;
                return prev;
            }
            cur = cur.next;
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            map[i] = null;
        }
        szkey = 0;
    }

    private void copy(MyHashMap x) {
        capacity = x.capacity;
        szkey = x.szkey;
        map = x.map;
    }

    private int getHash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }


    private void rebuild() {
        MyHashMap cur = new MyHashMap(2 * capacity);
        for (int i = 0; i < capacity; i++) {
            Node c = map[i];
            while (c != null) {
                cur.put(c.key, c.val);
                c = c.next;
            }
        }
        copy(cur);
    }


    private class Node {
        Node next;
        String key, val;
        private Node(String k, String v) {
            next = null;
            val = v;
            key = k;
        }

        private void addElem(Node x) {
            next = x;
        }
        private Node getLast() {
            Node cur = this;
            while (cur.next != null) {
                cur = cur.next;
            }
            return cur;
        }

    }


}