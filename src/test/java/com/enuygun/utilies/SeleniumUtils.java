package com.enuygun.utilies;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import com.enuygun.base.BaseDriver;
import com.enuygun.model.ElementInfo;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumUtils {
    WebDriver driver;
    FluentWait<WebDriver> wait;
    long waitElementTimeout = 20;
    long pollingEveryValue = 300;

    public SeleniumUtils() {
        driver = BaseDriver.driver;
        wait = setFluentWait(waitElementTimeout);
    }

    public FluentWait<WebDriver> setFluentWait(long timeout) {

        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEveryValue))
                .ignoring(NoSuchElementException.class);
        return fluentWait;
    }

    public WebElement findElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        return element;
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public void sendKeys(String locator, String text) {
        findElement(getBy(locator)).sendKeys(text);

    }

    public void scrollToElement(String locator) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView({block: \"center\", inline: \"nearest\"});", findElement(getBy(locator)));
    }

    public void clickElement(String locator) {
        findElement(getBy(locator)).click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public ElementInfo getElementInfo(String locator) {
        LocatorUtility storeHelper = new LocatorUtility();

        return storeHelper.findElementInfoByKey(locator);
    }

    public By getBy(String locator) {
        ElementInfo elementInfo = getElementInfo(locator);
        By by = ElementHelper.getElementInfoToBy(elementInfo);

        return by;
    }

    public String getText(String locator) {
        return findElement(getBy(locator)).getText();
    }

    public void clearText(String locator) {
        findElement(getBy(locator)).clear();
    }

    public void setWait(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectDateFromCalendar(String locator, String date) {
        String[] dateArray = date.split("-");
        Integer year = Integer.parseInt(dateArray[0]);
        Integer month = Integer.valueOf(dateArray[1]);
        String day = dateArray[2];
        String[] monthNames = {"", "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"};
        String dayXpathCalendar = "//h3[contains(text(),'" + monthNames[month] + "')]/parent::div/following-sibling::div[2]//button[@data-testid=\"datepicker-active-day\" and @data-day=\"" + day + "\"]";
        String monthXpath = "//h3[contains(@data-testid,'month-name-and-year')][contains(text(),'" + monthNames[month] + " " + year + "')]";


        Assertions.assertFalse(dateIsPast(date), "Seçilen tarih geçmiş bir tarih olamaz");

        while (true) {
            if (isElementVisible(By.xpath(monthXpath))) {
                driver.findElement(By.xpath(dayXpathCalendar)).click();
                break;
            } else {
                clickElement(locator);
            }
        }

    }

    public boolean dateIsPast(String date) {
        String[] dateArray = date.split("-");
        Integer year = Integer.parseInt(dateArray[0]);
        Integer month = Integer.valueOf(dateArray[1]);
        String day = dateArray[2];

        if (LocalDate.now().getYear() > year) {
            return true;
        } else if (LocalDate.now().getYear() == year && LocalDate.now().getMonthValue() > month) {
            return true;
        } else if (LocalDate.now().getYear() == year && LocalDate.now().getMonthValue() == month && LocalDate.now().getDayOfMonth() > Integer.parseInt(day)) {
            return true;
        }
        return false;
    }

    public boolean isElementVisible(By locator) {
        try {
            Thread.sleep(500);
            driver.findElement(locator).isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setSlider(String locator1, String locator2, int hour) {
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(findElement(getBy(locator2)), countSliderValue(locator1, hour), 0).perform();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int countSliderValue(String locator, int hour) {
        int sliderValue = 0;
        int sliderHour = 24;
        int sliderWidth = findElement(getBy(locator)).getSize().getWidth();
        sliderValue = (sliderWidth / sliderHour) * hour;


        if (sliderValue < 0) {
            return -(sliderWidth + sliderValue);
        } else
            return sliderValue;
    }

    public List<WebElement> getElementList(String locator) {
        List<WebElement> elements = driver.findElements(getBy(locator));
        return elements;
    }

    public void verifyElementListContainsText(String locator, String text) {
        List<WebElement> elements = getElementList(locator);
        for (WebElement element : elements) {
            Assertions.assertTrue(element.getText().contains(text), element.getText() + " elementi " + text + " text değerini içermiyor");
        }
    }

    public String dateConvert(String text, String format){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String newDate = simpleDateFormat.format(date);

        return newDate;

    }
}

