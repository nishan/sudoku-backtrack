import java.io.File;

public class SudokuBacktrack {

	public static boolean solve(int[][] sudoku) {
		return guess(sudoku, 0, 0);
	}

	/**
	 * This is how this works -- We find the first digit which will work for the
	 * cell(i.j). Assign that digit to the cell and try to solve rest of the
	 * sudoku. If sudoku is solvable, we are fine. Else, we assign next possible
	 * digit and solve again. We do this till we find that one digit. And then,
	 * we recurse.
	 */
	public static boolean guess(int[][] sudoku, int i, int j) {

		if (i == 8 && j == 9) {
			return true;
		}

		if (j > 8) { // We are at last cell of the row. Go to first cell of
						// next row.
			j = 0;
			i++;
		}

		if (sudoku[i][j] > 0)
			return guess(sudoku, i, j + 1); // Solved already, move to next cell

		for (int k = 1; k <= 9; k++)
			if (isPossible(sudoku, i, j, k)) { // See if value is possible
				sudoku[i][j] = k; // Assign that value
				if (guess(sudoku, i, j + 1)) // solve rest of the cells
					return true; // sudoku solved. So 'k' is good.
				else
					continue; // It did not work. try next value.
			}

		// We did not find a solution. Mark the cell unsolved.
		sudoku[i][j] = 0;
		return false;
	}

	// Check if 'k' is a valid digit at cell(i, j)
	private static boolean isPossible(int[][] sudoku, int i, int j, int k) {

		for (int n = 0; n < 9; n++) {
			if (sudoku[n][j] == k || sudoku[i][n] == k) // Check the row & col
				return false;
		}

		// Check the 3x3 square
		int boxRowStart = ((i / 3)) * 3;
		int boxColStart = ((j / 3)) * 3;
		for (int n = boxColStart; n < boxColStart + 3; n++)
			for (int m = boxRowStart; m < boxRowStart + 3; m++)
				if (sudoku[m][n] == k)
					return false;

		return true;

	}

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

}
