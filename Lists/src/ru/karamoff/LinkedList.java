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

    private int length = 0;

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
        length++;
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    /*
     *  Метод удаляет объект из списка
     */
    @Override
    public void remove(Object element) {
        if (head.value.equals(element)) {
            if (tail.value.equals(element)) {
                head = null;
                tail = null;
            } else {
                head = head.next;
            }   // если искомый объект в "голове" -> делаем новую "голову"
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
        length--;
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

    public static LinkedList sort(LinkedList toSort) {
        LinkedList[] stack = new LinkedList[32];
        int count = 0;

        while (toSort.head != null) {
            stack[count] = new LinkedList();
            stack[count].add(toSort.head.value);
            toSort.remove(toSort.head.value);
            count++;

            if (count >= 2) {
                if (stack[count - 1].length == stack[count - 2].length) {
                    stack[count-2] = merge(stack[count-2], stack[count-1]);
                    count--;
                }
            }
        }

        while (count != 1) {
            stack[count-2] = merge(stack[count-2], stack[count-1]);
            count--;
        }

        return stack[0];
    }

    public static LinkedList merge(LinkedList sorted1, LinkedList sorted2) {
        LinkedList merged = new LinkedList();
        while (sorted1.head != null && sorted2.head != null) {
            if ((int) sorted1.head.value < (int) sorted2.head.value) {
                merged.add(sorted1.head.value);
                sorted1.remove(sorted1.head.value);
            } else {
                merged.add(sorted2.head.value);
                sorted2.remove(sorted2.head.value);
            }
        }
        if (sorted1.head == null) {
            while (sorted2.head != null) {
                merged.add(sorted2.head.value);
                sorted2.remove(sorted2.head.value);
            }
        } else if (sorted2.head == null) {
            while (sorted1.head != null) {
                merged.add(sorted1.head.value);
                sorted1.remove(sorted1.head.value);
            }
        }
        return merged;
    }

    public int getLength() {
        return length;
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