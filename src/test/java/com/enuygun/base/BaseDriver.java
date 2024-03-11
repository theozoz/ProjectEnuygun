package com.enuygun.base;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeSpec;
import com.thoughtworks.gauge.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseDriver {

    public static WebDriver driver;
    public static final String BASE_URL="https://www.enuygun.com/";

    @BeforeSuite
    public void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeScenario
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterScenario
    public void teardown() {
        driver.quit();
    }

}
