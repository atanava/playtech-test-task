package com.atanava.playtech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class FormPage {

    private final WebDriver driver;

    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String fillField(String selector, String input) {
        WebElement nameInput = driver.findElement(By.cssSelector(selector));
        nameInput.sendKeys(input);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return nameInput.getAttribute("data-initial-value");
    }

    public boolean isRadioBtnSelected(String selector) {
        String isChecked = driver.findElement(By.cssSelector(selector)).getAttribute("aria-checked");
        return Boolean.parseBoolean(isChecked);
    }

    public void clickElement(String selector) {
        driver.findElement(By.cssSelector(selector)).click();
    }

    public boolean isErrorPresent(String errorIcon) {
        try {
            driver.findElement(By.cssSelector(errorIcon));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
