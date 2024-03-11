package com.enuygun.steps;

import com.enuygun.utilies.SeleniumUtils;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepImplementation {
    SeleniumUtils seleniumUtils;

    public StepImplementation() {
        seleniumUtils = new SeleniumUtils();
    }


    @Step("<key> Elementin görünür olduğu kontrol edilir")
    public void elementIsVisible(String key) {
        seleniumUtils.findElement(seleniumUtils.getBy(key));
    }

    @Step("Sayfa <url> adresine gidilir")
    public void goToUrl(String url) {
        seleniumUtils.goToUrl(url);
    }
    @Step("Element <key> üzerine scroll edilir")
    public void scroll(String key) {
        seleniumUtils.scrollToElement(key);
    }

    @Step("Takvimden <key> belirtilen tarih <text> seçilir")
    public void selectDateFromCalendar(String key, String text) {
        seleniumUtils.selectDateFromCalendar(key, text);
    }

    @Step("<key> elementine <text> değeri yazılır")
    public void sendKeys(String key, String text) {
        seleniumUtils.sendKeys(key, text);
    }

    @Step("<key> elementine tıklanır")
    public void clickElement(String key) {
        seleniumUtils.clickElement(key);
    }

    @Step("<key> elementinin <text> değerini içerdiği kontrol edilir")
    public void verifyElementContainsText(String key, String text) {
        Assertions.assertTrue(seleniumUtils.getText(key).contains(text), seleniumUtils.getText(key) + " elementi " + text + " text değerini içermiyor");
    }

    @Step("<key> elementin text değeri temizlenir")
    public void clearElement(String key) {
        seleniumUtils.clearText(key);
    }

    @Step("<saniye> saniye beklenir")
    public void sleep(int time) {
        seleniumUtils.setWait(time);
    }

    @Step("<key1> Kalkış saati barının <key2> çubuğunu saat <hour> olarak ayarlanır")
    public void setSlider(String key1, String key2, int hour) {
        seleniumUtils.setSlider(key1, key2, hour);
    }

    @Step("Element listesinin <key> text değeri artan şekilde sıralandığı kontrol edilir")
    public void verifyElementTextShortOrder(String key) {
        List<WebElement> elements = seleniumUtils.getElementList(key);

        List<Float> sortList = new ArrayList();
        List<Float> sortedList = new ArrayList<>(sortList);

        for (WebElement element : elements) {
            sortList.add(Float.valueOf(element.getText()));
        }
        sortedList.addAll(sortList);
        sortedList.stream().sorted(Comparator.naturalOrder());

        Assertions.assertTrue( sortList.equals(sortedList),  sortList+ " Element text değerleri artan şekilde sıralanmıyor"+ sortedList);
    }

    @Step("Element listesinin <key> text değeri <text> değerini içerdiği kontrol edilir")
    public void verifyElementTextShortOrder(String key, String text) {
        seleniumUtils.verifyElementListContainsText(key, text);
    }

    @Step("Element <key1> listesi elementin <key2> sayısı ile eşleştiği kontrol edilir")
    public void getElementListSize(String key1, String key2) {
        List<WebElement> elements = seleniumUtils.getElementList(key1);
        String text= seleniumUtils.getText(key2);
        String sayi = text.substring(1, text.length() - 1);

        Assertions.assertTrue(elements.size() == Integer.parseInt(sayi),
                elements.size() + " Element listesi ile liste sayısı "+sayi+" eşleşmiyor");
    }

    @Step("Element <key> text değeri <storeKey> StoreKey değerini içerdiği kontrol edilir")
    public void verifyElementTextContainsDataStoreValue(String key, String storeKey) {
        String storeValue = (String) ScenarioDataStore.get(storeKey);
        String elementsText = seleniumUtils.getText(key);
        assertTrue(elementsText.contains(storeValue), "element text " + elementsText + "  StoreValue" + storeValue + " değerini içermiyor");
    }

    @Step("Text <text> değeri <storeKey> StoreKey değeri olarak kaydedilir")
    public void saveTextInStoreKey(String text, String storeKey) {
        ScenarioDataStore.put(storeKey, seleniumUtils.dateConvert(text, "dd MMM yyyy, E"));
    }
}
