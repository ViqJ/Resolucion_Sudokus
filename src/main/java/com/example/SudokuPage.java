package com.example;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SudokuPage{
    private WebDriver driver;

    public SudokuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//*[@id=\"game\"]/canvas")
    private WebElement sudokuImage;

    

    @FindBy(xpath = "//*[@id=\"game-controls\"]")
    private WebElement panelControl;

    @FindBy(xpath = "//*[@id=\"numpad\"]/div[1]")
    private WebElement numerouno;
    
    @FindBy(xpath = "//*[@id=\"sdk_google_analytics\"]")
    private WebElement checkboxgoogle;

    public File[] getSudokuImage() throws IOException {
        File [] sudokuImages = new File[81];
        // Crear una instancia de Actions
        Actions actions = new Actions(driver);
        // Presionar la flecha hacia arriba en el elemento
        for(int i=0;i<2;i++){
            actions.sendKeys(panelControl, Keys.ARROW_UP).perform();
            actions.sendKeys(panelControl, Keys.ARROW_LEFT).perform();
        }
        int contador = 0;
        // Capturar la imagen del captcha

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Obtener las coordenadas y dimensiones del elemento captcha
        int width = sudokuImage.getSize().getWidth()/9;
        int height = sudokuImage.getSize().getHeight()/9;
        int posX = sudokuImage.getLocation().getX();
        int posY = sudokuImage.getLocation().getY();
        // Recortar la imagen del captcha
        BufferedImage captchaImage = ImageIO.read(screenshot);


        BufferedImage captchaCropped2 = captchaImage.getSubimage(posX, posY, width*9, height*9);
        // Guardar la imagen recortada
        File imagenCompleta = new File("imagenesSudoku\\sudokuCompleto.png");
        ImageIO.write(captchaCropped2, "png", imagenCompleta);

        float espacioX = (int) (width*0.2);
        float espacioY = (int) (height*0.2);
        //Dejar
        for(int row = 0; row<9;row++){
            for(int col = 0; col < 9;col++){
                int x = posX+(col*width);
                int y = posY+(row*height);
                BufferedImage captchaCropped = captchaImage.getSubimage((int) (x + espacioX), (int) (y + espacioY), (int) (width*0.7 ), (int) (height*0.7 ));
                // Guardar la imagen recortada
                sudokuImages[contador] = new File("imagenesSudoku\\sudoku"+(contador+1)+".png");
                ImageIO.write(captchaCropped, "png", sudokuImages[contador]);
                contador++;
            }
        }
        
        return sudokuImages;
    }
    public void moverDerecha(){
        Actions actions = new Actions(driver);
        actions.sendKeys(panelControl, Keys.ARROW_RIGHT).perform();
    }
    public void moverIzquierda(){
        Actions actions = new Actions(driver);
        actions.sendKeys(panelControl, Keys.ARROW_LEFT).perform();
    }
    public void moverAbajo(){
        Actions actions = new Actions(driver);
        actions.sendKeys(panelControl, Keys.ARROW_DOWN).perform();
    }
    public void ingresarValor(String valor){
        Actions actions = new Actions(driver);
        actions.sendKeys(panelControl, Keys.valueOf("NUMPAD"+valor)).perform();
    }
    

    public void wait (int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
