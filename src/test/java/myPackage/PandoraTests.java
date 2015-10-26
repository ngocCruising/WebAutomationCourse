package myPackage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigInteger;
import java.security.SecureRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by ntran on 10/26/15.
 */
public class PandoraTests {
    public static final String LOGIN_PASSWORD = "myFailPassword";
    public static final String BIRTH_YEAR = "1993";
    public static final String ZIP_CODE = "92704";

    private WebDriver webDriver;
    @Before
    public void startUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/ntran/Desktop/WebAutomationCourse/chromedriver");
        webDriver = new ChromeDriver();
    }
    @After
    public void tearDown() {
        webDriver.close();
    }

    @Test
    public void checkTitle() {
        String baseUrl = "http://newtours.demoaut.com";
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = "";

        // launch Chrome and direct it to the BASE URL
        webDriver.get(baseUrl);

        // get the actual value of the title
        actualTitle = webDriver.getTitle();


        /**
         * Compare the actual title of the page with the expected one and print the result as "Passed" or "Failed"
         */
        if (!actualTitle.contentEquals(expectedTitle)) {
            fail("Actual title=" + actualTitle +  "did not match expectedTitle=" + expectedTitle);
        }

    }

    @Test
    public void UITest() {
        String baseUrl = "http://www.facebook.com";
        String tagName = "";

        webDriver.get(baseUrl);
        tagName = webDriver.findElement(By.id("email")).getTagName();
        assert(tagName.equals("input"));
    }

    @Test
    public void registerPandoraAccountTest() throws InterruptedException {
        String baseUrl = "http://radio.stage.savagebeast.com";
        webDriver.get(baseUrl);

        webDriver.findElement(By.linkText("register")).click();
        System.out.println("Clicking on register");

        //Wait for field forms to load up.
        getWaiter(5).until(ExpectedConditions.elementToBeClickable(By.name("email")));


        System.out.println("Filling in form fields!");
        webDriver.findElement(By.name("email")).sendKeys(RandomTextFieldGenerator.randomStringUserName());
        webDriver.findElement(By.name("password")).sendKeys(LOGIN_PASSWORD);
        webDriver.findElement(By.name("birthYear")).sendKeys(BIRTH_YEAR);
        webDriver.findElement(By.name("zipCode")).sendKeys(ZIP_CODE);
        webDriver.findElement(By.cssSelector("input[value='MALE']")).click();
        webDriver.findElement(By.name("agreeTermsOfUse")).click();
        webDriver.findElement(By.cssSelector("input[value='Register']")).click();

        //Wait for congrats form to show up.
        getWaiter(5).until(ExpectedConditions.elementToBeClickable(By.linkText("Continue")));
        String expectedText = "Congratulations! You're now registered with Pandora";
        String actualText = webDriver.findElement(By.className("lightbox_header")).getText();

        assertEquals(expectedText, actualText);
        webDriver.findElement(By.linkText("Continue")).click();
        System.out.println("Clicking on continue");

        getWaiter(5).until(ExpectedConditions.presenceOfElementLocated(By.id("searchPopupWelcomePosition")));
    }

    // Used to generate random inputs for fields for testing.
    public static class RandomTextFieldGenerator{

        private static SecureRandom random = new SecureRandom();

        public static String randomStringUserName() {
            return (new BigInteger(14, random).toString(32)) + "@test.com";
        }
    }

    // Gets a driver that will wait for an expected condition.
    private WebDriverWait getWaiter(int waitTime) {
        return new WebDriverWait(webDriver, waitTime);
    }
}
