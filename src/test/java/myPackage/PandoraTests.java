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

    private void createUser() {
        String baseUrl = "http://radio.stage.savagebeast.com";
        webDriver.get(baseUrl);
        RegistrationPage registrationPage = new RegistrationPage(webDriver);

        System.out.println("Clicking on register");
        registrationPage.clickRegisterLink();

        //Wait for field forms to load up.
        getWaiter(5).until(ExpectedConditions.elementToBeClickable(registrationPage.getEmailField()));

        System.out.println("Filling in form fields!");
        registrationPage.fillInUserRegistrationDetails(RandomTextFieldGenerator.randomStringUserName(), LOGIN_PASSWORD,
                BIRTH_YEAR, ZIP_CODE);

        //Wait for congrats form to show up.
        getWaiter(5).until(ExpectedConditions.elementToBeClickable(registrationPage.getContinueLink()));
        String expectedText = "Congratulations! You're now registered with Pandora";
        String actualText = registrationPage.getCongratsText();

        assertEquals(expectedText, actualText);
        System.out.println("Clicking on continue");
        registrationPage.clickContinueLink();

        getWaiter(5).until(ExpectedConditions.presenceOfElementLocated(By.id("searchPopupWelcomePosition")));
    }

    @Test
    public void registerPandoraAccountTest() {
        createUser();
    }

    @Test
    public void automateStationPlayWithControls() throws InterruptedException {
        createUser();
        webDriver.findElements(By.className("searchInput")).get(1).click();
        webDriver.findElements(By.className("searchInput")).get(1).sendKeys("Taylor Swift");
        webDriver.findElement(By.className("tophit")).click();
        Thread.sleep(10000);

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
