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

    /*
     *  Метод удаляет объект из списка
     */
    @Override
    public void remove(Object element) {
        if (head.value.equals(element)) {
            head = head.next; // если искомый объект в "голове" -> делаем новую "голову"
        } else {
            Node found = findInNext(element);   // узел-указатель ищет узел, что находится
                                                // перед узлом с искомым объектом
            if (found != null) {
                if (tail == found.next) {
                    tail = found; // если найденный элемент был "хвостом", мы перестраиваем хвост
                }
                found.next = found.next.next; // так или иначе мы просто переподключаем узел
            } else {
                System.err.println("Element not found"); // если элемента нет, выводим ошибку
            }
        }
    }

    /*
     *  Метод возвращает true, если объект есть в списке и false, если его нет
     */
    @Override
    public boolean contains(Object element) {
        // true <- если объект лежит в "голове" или он есть в чьём-нибудь следующем узле
        // если ни то, ни то не выполняется -> false
        return head.value.equals(element) || findInNext(element) != null;
    }

    /*
     *  Метод ищет определённый объект и возвращает предыдущий узел
     */
    private Node findInNext(Object element) {
        Node pointer = head; // начинаем поиск с "головы"

        // ищем, пока не дойдём до конца или не вернёмся к началу
        while (pointer.next != null && !pointer.next.equals(head)) {
            if (pointer.next.value.equals(element)) {
                return pointer; // если объект лежит в следующем узле, возвращаем текущий
            } else {
                pointer = pointer.next; // иначе - ищем дальше
            }
        }
        return null; // возвращаем null, если объекта нет
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node pointer = head;
        do {
            builder.append(pointer.value);
            if (pointer.next != null && !pointer.next.equals(head)) {
                builder.append(", ");
            }
            pointer = pointer.next;
        } while (pointer != null && !pointer.equals(head));
        builder.append("]");
        return builder.toString();
    }
}