package ru.karamoff;

public class HashMap<K, V> implements Map<K, V> {

    private Node<K, V>[] storage;
    private final int STORAGE_SIZE = 15; // должна быть 2^n-1

    private class Node<NK, NV> {
        NK key;
        NV value;
        Node<NK, NV> next;

        public Node(NK key, NV value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashMap() {
        storage = new Node[STORAGE_SIZE];
    }

    public void put(K key, V value) {
        Node<K, V> node = storage[key.hashCode() & (STORAGE_SIZE-1)];

        if (node == null) {
            storage[key.hashCode() & (STORAGE_SIZE-1)] = new Node<>(key, value);
        } else {
            boolean exists = node.key.equals(key);

            while (!exists && node.next != null) {
                node = node.next;
                exists = node.key.equals(key);
            }

            if (exists) {
                node.value = value;
            } else {
                node.next = new Node<>(key, value);
            }
        }
    }

    public V get(K key) {
        Node<K, V> node = storage[key.hashCode() & (STORAGE_SIZE-1)];

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
