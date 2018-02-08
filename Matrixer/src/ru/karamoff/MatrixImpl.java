package ru.karamoff;

import java.util.ArrayList;
import java.util.Collections;

public class MatrixImpl implements Matrix {
	private ArrayList<Cell> cells = new ArrayList<>();

	@Override
	public void set(int row, int column, int value) {
		cells.add(new Cell(row, column, value));
		Collections.sort(cells);
	}

	@Override
	public int get(int row, int column) {
		return search(row, column, 0, cells.size() - 1);
	}

	private int search(int searchedRow, int searchedColumn, int start, int end) {
		int middle = start + (end - start) / 2;

		if (end - start > 0) {
			if (cells.get(middle).getRow() != searchedRow) {
				if (cells.get(middle).getRow() > searchedRow) {
					return search(searchedRow, searchedColumn, start, middle);
				} else {
					return search(searchedRow, searchedColumn, middle, end);
				}
			} else {
				if (cells.get(middle).getColumn() > searchedColumn) {
					return search(searchedRow, searchedColumn, start, middle);
				} else {
					return search(searchedRow, searchedColumn, middle, end);
				}
			}
		} else {
			if (cells.get(middle).getRow() == searchedRow && cells.get(middle).getColumn() == searchedColumn) {
				return middle;
			} else {
				return -1;
			}
		}
	}
}
