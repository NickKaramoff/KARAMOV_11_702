package ru.karamoff;

public class Cell implements Comparable<Cell>{
	private int row, column, value;

	public Cell(int row, int column, int value) {
		this.row = row;
		this.column = column;
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public int getValue() {
		return value;
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
