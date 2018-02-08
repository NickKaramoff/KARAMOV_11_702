package ru.karamoff;

public class Main {

    public static void main(String[] args) {
	    MatrixImpl matrix = new MatrixImpl();

	    matrix.set(1000,5000,1);
	    matrix.set(2000,6000,2);
	    matrix.set(1500,4500,3);
	    matrix.set(2000,2000,4);

		System.out.println(matrix.get(1500, 4500));
	}
}
