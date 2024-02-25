package com.example;


public class SudokuSolver {
    // Método para resolver un Sudoku
    public int[][] solveSudoku(int[][] sudoku) {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if (sudoku[i][j] == 0) {
                    for (int z = 1; z <= 9; z++) {
                        if (valida(sudoku, i, j, z)) {
                            sudoku[i][j] = z;
                            int[][] resultado = solveSudoku(sudoku);
                            if (resultado != null) {
                                return resultado;
                            }
                            sudoku[i][j] = 0; // Revertir la asignación si no lleva a una solución válida
                        }
                    }
                    return null; // No se puede resolver con este número, retornar null
                }
            }
        }
        return sudoku; // El Sudoku está completamente resuelto
    }

    // Método para verificar si un número es válido en una posición dada del Sudoku
    private boolean valida(int[][] sudoku, int fila, int columna, int numero) {
        // Verificar la fila
        for (int i = 0; i < 9; i++) {
            if (sudoku[fila][i] == numero) {
                return false;
            }
        }

        // Verificar la columna
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][columna] == numero) {
                return false;
            }
        }

        // Verificar el cuadro de 3x3
        int inicioFila = fila - fila % 3;
        int inicioColumna = columna - columna % 3;
        for (int i = inicioFila; i < inicioFila + 3; i++) {
            for (int j = inicioColumna; j < inicioColumna + 3; j++) {
                if (sudoku[i][j] == numero) {
                    return false;
                }
            }
        }

        return true; // El número es válido en esta posición
    }
}
