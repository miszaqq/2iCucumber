package uk.co.edgewords;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import uk.co.edgewords.POMPages.HomePagePOM;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
//import static uk.co.edgewords.Utils.Hooks.driver;

public class StepDefinitions {

    private WebDriver driver;
    private final SharedDictionary sharedDict; //Field for shared dictonary to use in this class

    public StepDefinitions(SharedDictionary sharedDict) { //PicoContainer will instantiate sharedDict for us
        this.sharedDict = sharedDict; //Put the passed instance of sharedDict in the field.
        driver = (WebDriver)sharedDict.readDict("webdriver");

    }

    //@Then("^(?i)i am on the (?-i)Google homepage$") //Case insesitivity using RegEx
    @Then("I am on Google") //Multiple annotations for alternative phrasings
    public void i_am_on_the_google_homepage() throws InterruptedException {

        driver.get("https://www.google.co.uk");
        var iAgrees = driver.findElements(By.cssSelector("#L2AGLb > div"));
        if(!iAgrees.isEmpty()){
            iAgrees.get(0).click();
        }




        Thread.sleep(1000);
        String bodyText = driver.findElement(By.tagName("body")).getText();
        sharedDict.addDict("capturedText",bodyText);
        Thread.sleep(1000);
    }


    @Then("I see in the results")
    public void i_see_in_the_results(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        //throw new io.cucumber.java.PendingException();
        List<Map<String,String>> rows = dataTable.asMaps(String.class, String.class);
        String searchResults = driver.findElement(By.cssSelector("#rso")).getText();

        for(var row : rows){
            MatcherAssert.assertThat(searchResults, containsString(row.get("title")));
            MatcherAssert.assertThat(searchResults, containsString(row.get("url")));
        }
    }


    @Then("{string} appears in the results")
    public void edgewords_appears_in_the_results(String searchResult) {
        String searchResults = driver.findElement(By.cssSelector("#rso")).getText();
        MatcherAssert.assertThat(searchResults, containsString(searchResult));
    }
}
