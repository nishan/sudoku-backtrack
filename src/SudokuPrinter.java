import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class SudokuPrinter {

	public static void print(int[][] sudoku) {
		print(sudoku, System.out, true);
	}

	public static void print(int[][] sudoku, File f)
			throws FileNotFoundException {
		PrintStream fout = new PrintStream(new FileOutputStream(f));
		try {
			print(sudoku, fout, false);
		} finally {
			fout.close();
		}
	}

	public static void print(int[][] sudoku, PrintStream out,
			boolean prettyPrint) {
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0 && prettyPrint)
				out.println(" -----------------------");
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0 && prettyPrint)
					out.print("| ");
				if (sudoku[i][j] == 0)
					out.print("x ");
				else
					out.print(sudoku[i][j] + " ");
			}
			if (prettyPrint)
				out.print("|");
			out.println();
		}
		if (prettyPrint)
			out.println(" -----------------------");
	}

	public static int[][] read(File f) throws IOException {

		int[][] sudoku = new int[9][9];
		int i = 0, j = 0;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(f));
			String s;
			while ((s = reader.readLine()) != null) {

				String tokens[] = s.trim().split(" ");
				j = 0;
				for (int k = 0; k < tokens.length && j < 9; k++) {
					try {
						sudoku[i][j++] = Integer.parseInt(tokens[k]);
					} catch (NumberFormatException ne) {}
				}
				i++;
			}
		} finally {
			if (reader != null) 
				reader.close();
		}

		return sudoku;
	}

}
