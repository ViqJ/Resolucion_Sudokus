# Resolucion_Sudokus

Saludos,

Este programa es un solucionador simple de Sudokus que utiliza las librerías Selenium y Tesseract. Selenium se encarga de la interacción con la página web "https://sudoku.com/es/evil/", mientras que Tesseract se utiliza para analizar los números en la imagen del Sudoku.

La lógica principal se encuentra en la clase SudokuSolver, que resuelve el Sudoku de forma bruta, probando todas las posibilidades lógicas excepto en los casos donde se incumplen las condiciones de resolución del Sudoku.

Para probar el programa, asegúrate de tener los controladores de Chrome descomprimidos en el directorio. Puedes descargar los controladores desde el siguiente enlace: <a scr="https://drive.google.com/file/d/1IuSYkols6tV98GuSQnPin3O9utQPBusU/view?usp=drive_link">Descargar controlador de Chrome<a>

Es importante tener en cuenta que la lectura OCR solo considera números del 1 al 9. Sin embargo, debido a posibles errores de reconocimiento, es posible que el programa no resuelva el Sudoku en todos los casos.

