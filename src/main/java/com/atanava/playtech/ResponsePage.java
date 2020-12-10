package com.atanava.playtech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ResponsePage {

    public WebDriver driver;

    public ResponsePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getHeaderText(String selector) {
        return driver.findElement(By.cssSelector(selector)).getText();
    }
}
