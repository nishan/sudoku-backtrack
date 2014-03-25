sudoku-backtrack
================

Attempt at writing a sudoku solver using backtracking and recursion.

Usage
------


	public static void main(String[] a) throws Exception {

		int[][] problem = { { 0, 0, 7, 0, 3, 0, 8, 0, 0 },
				{ 0, 0, 0, 2, 0, 5, 0, 0, 0 }, { 4, 0, 0, 9, 0, 6, 0, 0, 1 },
				{ 0, 4, 3, 0, 0, 0, 2, 1, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 5 },
				{ 0, 5, 8, 0, 0, 0, 6, 7, 0 }, { 5, 0, 0, 1, 0, 8, 0, 0, 9 },
				{ 0, 0, 0, 5, 0, 3, 0, 0, 0 }, { 0, 0, 2, 0, 9, 0, 5, 0, 0 } };

		if (SudokuBacktrack.solve(problem))
			SudokuPrinter.print(problem);
		else
			System.out.println("Can not solve the puzzle!");

		// You can read sudoku from a file.
		int[][] sudoku = SudokuPrinter.read(new File("sudoku.txt"));

		if (SudokuBacktrack.solve(sudoku))
			SudokuPrinter.print(sudoku);
		else
			System.out.println("Can not solve the puzzle!");

	}
