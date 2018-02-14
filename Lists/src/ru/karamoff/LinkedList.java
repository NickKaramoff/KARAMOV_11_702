package ru.karamoff;

public class LinkedList implements List {

    private static class Node {
        Object value;
        Node next;

        Node(Object value) {
            this.value = value;
            this.next = null;
        }
    }
    // ссылка на первый элемент списка
    private Node head;
    // ссылка на последний элемент списка
    private Node tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public Object get(int index) {
        Node pointer = this.head;
        for (int i = 1; i <= index; i++) {
            pointer = pointer.next;
        }
        if (pointer != null) {
            return pointer.value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void addToBegin(Object object) {
        Node newNode = new Node(object);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        tail.next = newNode;
        tail = newNode;
    }

    @Override
    public void remove(Object element) {
        if (contains(element)){
            if (head.value.equals(element)) {
                head = head.next;
                return;
            }
            Node pointer = head;
            while (pointer.next != null) {
                if (pointer.next.value.equals(element)) {
                    pointer.next = pointer.next.next;
                    return;
                }
            }
        } else {
            System.out.println("Элемент не найден");
        }
    }

    @Override
    public boolean contains(Object element) {
        if (head.value.equals(element)) {
            return true;
        }
        Node pointer = head;
        while (pointer.next != null) {
            if (pointer.next.value.equals(element)) {
                return true;
            }
            pointer.next = pointer.next.next;
        }
        return false;
    }
}