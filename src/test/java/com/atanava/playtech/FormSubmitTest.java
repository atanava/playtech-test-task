package com.atanava.playtech;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.atanava.playtech.GoogleFormConfig.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static com.atanava.playtech.FormTestData.*;

public class FormSubmitTest {
    public WebDriver driver;
    public FormPage formPage;
    public ResponsePage responsePage;

    private static final long DELAY_SECONDS = 5;
    private static final long DELAY_MILLIS = 100;

    private final String NO_ERROR_ICON = "An error icon should appear, but it isn't";

    @ClassRule
    public static ExternalResource summary = TestLogRules.SUMMARY;

    @Rule
    public TestWatcher watcher = TestLogRules.watchman;


    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        formPage = new FormPage(driver);
        responsePage = new ResponsePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(DELAY_SECONDS, TimeUnit.SECONDS);
        driver.get(FORM_PAGE);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void submitFullForm() {
        formPage.fillField(OTHER_OPT_FIELD, COMMENT);
        formPage.fillField(NAME_FIELD, NAME);
        formPage.fillField(EMAIL_FIELD, EMAIL);
        formPage.fillField(ADDRESS_FIELD, ADDRESS);
        formPage.fillField(PHONE_FIELD, PHONE);
        formPage.fillField(COMMENTS_FIELD, COMMENT);

        formPage.clickElement(SUBMIT_BTN);
        driver.manage().timeouts().pageLoadTimeout(DELAY_SECONDS, TimeUnit.SECONDS);

        String actualUrl = driver.getCurrentUrl();
        assertEquals(RESPONSE_PAGE, actualUrl);
    }

    @Test
    public void submitRequiredFields() {
        formPage.fillField(NAME_FIELD, NAME);
        formPage.fillField(EMAIL_FIELD, EMAIL);
        formPage.fillField(ADDRESS_FIELD, ADDRESS);

        formPage.clickElement(SUBMIT_BTN);
        driver.manage().timeouts().pageLoadTimeout(DELAY_SECONDS, TimeUnit.SECONDS);

        String actualUrl = driver.getCurrentUrl();
        assertEquals(RESPONSE_PAGE, actualUrl);
    }

    @Test
    public void submitBlancForm() {
        formPage.clickElement(SUBMIT_BTN);
        driver.manage().timeouts().implicitlyWait(DELAY_MILLIS, TimeUnit.MILLISECONDS);
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> responsePage.getHeaderText(RESP_PAGE_HEADER));
    }

    @Test
    public void submitLostName() {
        formPage.fillField(EMAIL_FIELD, EMAIL);
        formPage.fillField(ADDRESS_FIELD, ADDRESS);

        formPage.clickElement(SUBMIT_BTN);
        driver.manage().timeouts().implicitlyWait(DELAY_MILLIS, TimeUnit.MILLISECONDS);
        assertTrue(NO_ERROR_ICON, formPage.isErrorPresent(NAME_ERROR_ICON));

    }

    @Test
    public void submitLostEmail() {
        formPage.fillField(NAME_FIELD, NAME);
        formPage.fillField(ADDRESS_FIELD, ADDRESS);

        formPage.clickElement(SUBMIT_BTN);
        driver.manage().timeouts().implicitlyWait(DELAY_MILLIS, TimeUnit.MILLISECONDS);
        assertTrue(NO_ERROR_ICON, formPage.isErrorPresent(EMAIL_ERROR_ICON));
    }

    @Test
    public void submitInvalidEmail() {
        formPage.fillField(NAME_FIELD, NAME);
        formPage.fillField(EMAIL_FIELD, INVALID_EMAIL);
        formPage.fillField(ADDRESS_FIELD, ADDRESS);

        formPage.clickElement(SUBMIT_BTN);
        driver.manage().timeouts().implicitlyWait(DELAY_MILLIS, TimeUnit.MILLISECONDS);
        assertTrue(NO_ERROR_ICON, formPage.isErrorPresent(EMAIL_ERROR_ICON));
    }

    @Test
    public void submitLostAddress() {
        formPage.fillField(NAME_FIELD, NAME);
        formPage.fillField(EMAIL_FIELD, EMAIL);

        formPage.clickElement(SUBMIT_BTN);
        driver.manage().timeouts().implicitlyWait(DELAY_MILLIS, TimeUnit.MILLISECONDS);
        assertTrue(NO_ERROR_ICON, formPage.isErrorPresent(ADDRESS_ERROR_ICON));
    }

}
