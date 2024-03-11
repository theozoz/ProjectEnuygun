package com.enuygun.utilies;


import org.openqa.selenium.By;
import com.enuygun.model.ElementInfo;

public class ElementHelper {
    public static By getElementInfoToBy(ElementInfo elementInfo) {
        By by = null;
        String elementInfoValue = elementInfo.getValue();
        switch (elementInfo.getType()) {
            case "css":
                by = By.cssSelector(elementInfoValue);
                break;
            case "id":
                by = By.id(elementInfoValue);
                break;
            case "xpath":
                by = By.xpath(elementInfoValue);
                break;
            case "class":
                by = By.className(elementInfoValue);
                break;
            case "tagName":
                by = By.tagName(elementInfoValue);
                break;
            case "name":
                by = By.name(elementInfoValue);
                break;
            default:
                throw new NullPointerException("Element type is not true");
        }
        return by;
    }
}
