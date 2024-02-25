package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class PaginaWeb {

    protected WebDriver driver;

    public PaginaWeb(){
        // Establecer la propiedad del sistema para indicar la ubicación del controlador de Chrome
        System.setProperty("webdriver.chrome.driver", "chrome\\version 121.0.6167.85\\chromedriver-win64\\chromedriver.exe");

        // Crear opciones para el navegador Chrome
        ChromeOptions cop = new ChromeOptions();
        cop.setBinary("chrome\\version 121.0.6167.85\\chrome-win64\\chrome.exe");

        // Inicializar el controlador de Chrome
        this.driver = new ChromeDriver(cop);
    }

    public void cargarPagina(String url) {
        this.driver.get(url);
    }

    public void cerrarNavegador() {
        // Cerrar el navegador
        this.driver.quit();
    }
}