package myPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by ntran on 11/10/15.
 */
public class StationsPage {
    WebDriver webDriver;

    @FindBy(className="searchInput")
    WebElement stationSearchInput;
    @FindBy(className="tophit")
    WebElement topHitLink;


    public StationsPage(WebDriver driver) {
        this.webDriver = driver;

        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("searchInput")));
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    public void createTaylorSwiftStation(){
        stationSearchInput.click();
//        stationSearchInput.sendKeys("Taylor Swift");
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.elementToBeClickable(topHitLink));
    }


}
