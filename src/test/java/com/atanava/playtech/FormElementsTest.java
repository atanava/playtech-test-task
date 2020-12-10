package com.atanava.playtech;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static com.atanava.playtech.GoogleFormConfig.*;
import static com.atanava.playtech.FormTestData.*;

public class FormElementsTest {
    public static WebDriver driver;
    public static FormPage formPage;

    private final String[] buttons = {OPTION_1_BTN, OPTION_2_BTN, OPTION_3_BTN, OPTION_4_BTN, OPTION_5_BTN, OTHER_OPT_BTN};

    @ClassRule
    public static ExternalResource summary = TestLogRules.SUMMARY;

    @Rule
    public TestWatcher watcher = TestLogRules.watchman;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        formPage = new FormPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(FORM_PAGE);
    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkRadioButtons() {
        for (int i = 0; i < buttons.length; i++) {
            String actualBtn = buttons[i];
            formPage.clickElement(actualBtn);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

            for (int j = 0; j < buttons.length; j++) {
                if (j == i) {
                    assertTrue("Radio Button \"Option " + (i+1) + "\" should be selected, but wasn't",
                            formPage.isRadioBtnSelected(actualBtn));
                } else {
                    assertFalse("Radio Button \"Option " + (j+1) + "\" should not be selected, but was selected",
                            formPage.isRadioBtnSelected(buttons[j]));
                }
            }
        }
    }

    @Test
    public void checkCancelButton() {
        for (int i = 0; i < buttons.length; i++) {
            formPage.clickElement(buttons[i]);
            assertTrue("Radio Button \"Option " + (i+1) + "\" should be selected, but wasn't",
                    formPage.isRadioBtnSelected(buttons[i]));
            formPage.clickElement(OPTION_CANCEL_BTN);

            for (int j = 0; j < buttons.length; j++) {
                assertFalse("Radio Button \"Option " + (j+1) + "\" should not be selected, but was selected",
                        formPage.isRadioBtnSelected(buttons[j]));
            }
        }
    }

    @Test
    public void fillOtherOption() {
        String actual = formPage.fillField(OTHER_OPT_FIELD, COMMENT);
        assertEquals(COMMENT, actual);
        assertTrue("Radio Button \"Other option\" should be selected, but wasn't",
                formPage.isRadioBtnSelected(OTHER_OPT_BTN));
    }

    @Test
    public void fillName() {
        String actual = formPage.fillField(NAME_FIELD, NAME);
        assertEquals(NAME, actual);
    }

    @Test
    public void fillEmail() {
        String actual = formPage.fillField(EMAIL_FIELD, EMAIL);
        assertEquals(EMAIL, actual);
    }

    @Test
    public void fillAddress() {
        String actual = formPage.fillField(ADDRESS_FIELD, ADDRESS);
        assertEquals(ADDRESS, actual);
    }

    @Test
    public void fillPhone() {
        String actual = formPage.fillField(PHONE_FIELD, PHONE);
        assertEquals(PHONE, actual);
    }

    @Test
    public void fillComments() {
        String actual = formPage.fillField(COMMENTS_FIELD, COMMENT);
        assertEquals(COMMENT, actual);
    }

}
