package ru.karamoff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class SparseMatrix implements Matrix {

    public class Cell implements Comparable<Cell> {

        private int row, column;

        private Number value;

        public Cell(int row, int column, Number value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }

        @Override
        public int compareTo(Cell another) {
            int compare = Integer.compare(this.row, another.row);
            if (compare == 0) {
                return Integer.compare(this.column, another.column);
            } else {
                return compare;
            }
        }

    }

    private LinkedList<Cell> cells;

    public SparseMatrix() {
        cells = new LinkedList<>();
    }

    @Override
    public void put(int row, int column, Number value) {
        if (value.doubleValue() != 0.0) {
            if (cells.size() > 0) {
                Iterator<Cell> iterator = cells.iterator();
                Cell pointer = cells.element();
                int index = 0;
                while (pointer.row < row && iterator.hasNext()) {
                    pointer = iterator.next();
                    index++;
                }
                while (pointer.column < column && iterator.hasNext()) {
                    pointer = iterator.next();
                    index++;
                }
                if (pointer.row == row && pointer.column == column) {
                    pointer.value = value;
                } else {
                    cells.add(index, new Cell(row, column, value));
                }
            } else {
                cells.add(new Cell(row, column, value));
            }
        } else {
            System.out.println("Нулевое значение не будет сохранено");
        }
    }

    @Override
    public Number get(int row, int column) {
        return search(row, column, 0, cells.size() - 1);
    }

    private int search(int searchedRow, int searchedColumn, int start, int end) {
        int middle = start + (end - start) / 2;

        if (end - start > 0) {
            if (cells.get(middle).row != searchedRow) {
                if (cells.get(middle).row > searchedRow) {
                    return search(searchedRow, searchedColumn, start, middle);
                } else {
                    return search(searchedRow, searchedColumn, middle, end);
                }
            } else {
                if (cells.get(middle).column > searchedColumn) {
                    return search(searchedRow, searchedColumn, start, middle);
                } else {
                    return search(searchedRow, searchedColumn, middle, end);
                }
            }
        } else {
            if (cells.get(middle).row == searchedRow && cells.get(middle).column == searchedColumn) {
                return middle;
            } else {
                return -1;
            }
        }
    }


}
