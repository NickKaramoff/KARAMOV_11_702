package ru.karamoff;

/** Расширяемый массив целых чисел.
 * Поддерживает добавление элемента в конец, удаление элемента, вставку элемента в определённую позицию и вывод в консоль.
 * @author Nick Karamoff
 * @version 1.0
 */

class ExpandableArray {
    private int array[] = new int[10];
    int count = 0;

    void appendItem(int element) {
        if (count < array.length) {
            array[count] = element;
            count++;
        } else {
            expand();
            appendItem(element);
        }
    }

    void removeItem(int index) {
        if (count > 0) {
            for (int i = index; i < array.length - 1; i++) {
                array[i] = array[i + 1];
            }
            count -= 1;
        } else {
            System.err.println("Массив пуст!");
        }
    }

    void insertItem(int index, int element) {
        if (count < array.length) {
            for (int i = count - 1; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = element;
            System.out.println("Элемент добавлен в позицию " + index + ".");
        } else {
            expand();
            insertItem(index, element);
        }
    }

    void printArray() {
        if (count > 0) {
            System.out.println("Введённый массив:");
            for (int i = 0; i < count; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        } else {
            System.err.println("Массив пуст!");
        }
    }

    private void expand() {
        int temporaryArray[] = new int[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            temporaryArray[i] = array[i];
        }
        for (int i = array.length; i < temporaryArray.length; i++) {
            temporaryArray[i] = 0;
        }

        array = temporaryArray;
    }
}
