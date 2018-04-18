package ru.karamoff;

public class HashMap<K, V> implements Map<K, V> {

    private Node[] storage;
    private final int STORAGE_SIZE = 15; // должна быть 2^n-1

    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashMap() {
        storage = (Node[]) new Object[STORAGE_SIZE];
    }

    public void put(K key, V value) {
        Node node = storage[key.hashCode() & STORAGE_SIZE];

        if (node == null) {
            storage[key.hashCode() & STORAGE_SIZE] = new Node(key, value);
        } else {
            boolean exists = node.key.equals(key);

            while (!exists && node.next != null) {
                node = node.next;
                exists = node.key.equals(key);
            }

            if (exists) {
                node.value = value;
            } else {
                node.next = new Node(key, value);
            }
        }
    }

    public V get(K key) {
        Node node = storage[key.hashCode() & STORAGE_SIZE];

        if (node != null) {
            boolean found = node.key.equals(key);

            while (!found && node.next != null) {
                node = node.next;
                found = node.key.equals(key);
            }

            if (found) {
                return node.value;
            }
        }

        return null;
    }

}
