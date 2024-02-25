package com.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCR {

    public String getOCR(File imageToRead) {
        // Ruta de la imagen con texto a leer
        File imagen = imageToRead;
        //Obtener nombre de archivo actual
        String nombreArchivo = imageToRead.getName();

        String textoExtraido = "";

        // Convertir imagen a blanco y negro
        BufferedImage imagenBW = convertirABlancoYNegro(imagen);

        // Guardar la imagen en disco (opcional)
        File imagenBWNueva = new File("imagenesByN\\imagenBW"+nombreArchivo+".png");
        try {
            ImageIO.write(imagenBW, "png", imagenBWNueva);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Instanciar el objeto Tesseract
        ITesseract tesseract = new Tesseract();
        tesseract.setVariable("tessedit_char_whitelist", "123456789");
        tesseract.setVariable("tessedit_char_blacklist", "0ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()_+-=[]{}|;:'\",.<>/?`~\\");
        tesseract.setPageSegMode(10); // Modo de segmentación de página: Tratar como un solo carácter
    
        try {
            // Configurar la ruta del directorio de datos de entrenamiento
            tesseract.setDatapath("dataOCR\\");

            // Realizar OCR en la imagen y obtener el texto
            textoExtraido = tesseract.doOCR(imagenBWNueva);

        } catch (TesseractException e) {
            System.err.println("Error al realizar OCR: " + e.getMessage());
        }

        return textoExtraido;
    }

    private BufferedImage convertirABlancoYNegro(File imagen) {
        try {
            BufferedImage imagenOriginal = ImageIO.read(imagen);
            BufferedImage imagenBW = new BufferedImage(imagenOriginal.getWidth(), imagenOriginal.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g = imagenBW.createGraphics();
            g.drawImage(imagenOriginal, 0, 0, Color.WHITE, null);
            g.dispose();
            return imagenBW;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
