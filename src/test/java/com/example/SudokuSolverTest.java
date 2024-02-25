package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SudokuSolverTest {

    @Test
    public void testSolveSudoku() {
        SudokuSolver solver = new SudokuSolver();
        
        // Lee los Sudokus y las soluciones esperadas del archivo
        try {
            Scanner scanner = new Scanner(new File("src\\test\\java\\recourses\\sudokus.txt"));
            while (scanner.hasNextLine()) {
                int[][] sudoku = readSudoku(scanner);
                printSudoku(sudoku);
                System.out.println();
                int[][] expectedSolution = readSudoku(scanner);
                printSudoku(expectedSolution);
                System.out.println();
                int[][] actualSolution = solver.solveSudoku(sudoku);
                printSudoku(actualSolution);
                System.out.println();
                assertArrayEquals(expectedSolution, actualSolution);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para leer un Sudoku de un archivo
    private int[][] readSudoku(Scanner scanner) {
        int[][] sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String[] line = scanner.nextLine().trim().split("\\s+");
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(line[j]);
            }
        }
        return sudoku;
    }
    // Método para imprimir un Sudoku

    public static void printSudoku(int[][] sudoku) {
        for (int i = 0; i < sudoku.length; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < sudoku[i].length; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                if (j == 8) {
                    System.out.println(sudoku[i][j]);
                } else {
                    System.out.print(sudoku[i][j] + " ");
                }
            }
        }
    }
}
