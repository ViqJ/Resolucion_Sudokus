package com.example;

import java.io.File;

public class Main {
    
    public static void main(String[] str) {

        int correctas = 0;
        int erroneas = 0;
            PaginaWeb paginaWeb = new PaginaWeb();
            OCR lector = new OCR();
            SudokuSolver sudokuSolver = new SudokuSolver();
    
            paginaWeb.cargarPagina("https://sudoku.com/es/evil/");
    
            SudokuPage paginaSudoku = new SudokuPage(paginaWeb.driver);
            try{
                File[] imagenesSudoku = paginaSudoku.getSudokuImage();
                String[] textoValores = new String[81]; 
                for(int i=0 ;i<imagenesSudoku.length;i++){
                    String Dato = lector.getOCR(imagenesSudoku[i]);
                    textoValores[i] = (Dato=="")?"0":Dato;
                }
                int[][] tableroSudoku = new int[9][9];
                int contador = 0;
                for(int i = 0; i < 9; i++) {
                    for(int j = 0; j < 9; j++) {
                        String valor = textoValores[contador].trim(); // Eliminar espacios en blanco al principio y al final
                        if(!valor.isEmpty()) {
                            tableroSudoku[i][j] = Integer.parseInt(valor);
                        } else {
                            tableroSudoku[i][j] = 0; // O cualquier otro valor predeterminado
                        }
                        contador++;
                    }
                }

                    printSudoku(tableroSudoku);
                    int[][] sudokuResuelto = sudokuSolver.solveSudoku(tableroSudoku);
                    if(sudokuResuelto!=null){
                        printSudoku(sudokuResuelto);
                        for(int i=0;i<sudokuResuelto.length;i++){
                            for(int j=0;j<sudokuResuelto[i].length;j++){
                                paginaSudoku.ingresarValor(String.valueOf(sudokuResuelto[i][j]));
                                if(sudokuResuelto[i].length-1!=j) paginaSudoku.moverDerecha();
                            }
                            if(i!=sudokuResuelto.length-1){
                                paginaSudoku.moverAbajo();
                                for(int z=0;z<9;z++){
                                    paginaSudoku.moverIzquierda();
                                }
                            }
                        }
                        correctas++;
                    }else{
                        erroneas++;
                    }
            }catch(Exception e){
                System.out.println(e.toString());
                erroneas++;
                paginaWeb.driver.navigate().refresh();
            }
            paginaWeb.cerrarNavegador();
        System.out.println("Cantidad correctas:" + correctas);
        System.out.println("Cantidad erroneas:" + erroneas);

    } 
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
