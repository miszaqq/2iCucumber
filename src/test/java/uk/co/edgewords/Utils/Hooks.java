package uk.co.edgewords.Utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import uk.co.edgewords.SharedDictionary;

public class Hooks {
    private WebDriver driver; //Field to share driver between methods in this class
    private final SharedDictionary sharedDict; //field to use sharedDict in this class

    public Hooks(SharedDictionary sharedDict) {
        this.sharedDict = sharedDict;
    }

//    public static WebDriver getDriver(){
//        //ToDO:implement access checks etc/whatever
//        return driver;
//    }
    @Before(order = 1)
    public void AnotherSetup(){
        System.out.println("Another before");
    }

    @Before(value = "@Gui", order=2)
    public void SetUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        sharedDict.addDict("webdriver",driver);
        Thread.sleep(1000);
    }

    @After("@Gui")
    public void TearDown(){
        WebDriver shareddriver = (WebDriver)sharedDict.readDict("webdriver");
        shareddriver.quit();
    }
}
