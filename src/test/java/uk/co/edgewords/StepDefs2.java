package uk.co.edgewords;

import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class StepDefs2 {

    private WebDriver driver;
    private final SharedDictionary sharedDict;

    public StepDefs2(SharedDictionary sharedDict) {

        this.sharedDict = sharedDict;
        driver = (WebDriver)sharedDict.readDict("webdriver");
    }

    @When("I search for {string}")
    public void i_search_for(String searchText) throws InterruptedException {
        String capturedText = (String)sharedDict.readDict("capturedText");
        System.out.println(capturedText);
        driver.findElement(By.cssSelector("input[name=q]")).sendKeys(searchText + Keys.ENTER);
        Thread.sleep(1000);
    }
}
