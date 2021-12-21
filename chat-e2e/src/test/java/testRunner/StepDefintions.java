package testRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class StepDefintions {

    RemoteWebDriver driver;
    private String theMessage;

    @Given("Deniz and Murat are valid users")
    public void denizAndMuratAreValidUsers() throws InterruptedException {
        this.initDriver();
        this.registerUser("Murat");
        this.registerUser("Deniz");
        this.logout();
    }

    @When("Deniz wants to send a message {string} to Murat")
    public void denizWantsToSendAMessageToMurat(String messageContent) {
        theMessage= messageContent;
        loginAs("Deniz");
        pickUser("Murat");
        sendMessage(messageContent);
    }

    @Then("Murat can display the message")
    public void muratCanDisplayTheMessage() {
        loginAs("Murat");
        pickUser("Deniz");
        List<String> currentMessages = getMessagesFromUI();

        Assert.assertTrue(currentMessages.get(currentMessages.size()-1).contains(theMessage));
    }



    private void initDriver() {
        String driverFile = "C:\\Users\\murat\\Downloads\\chromedriver_win322\\chromedriver.exe";
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(driverFile))
                .build();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
        options.addArguments("--headless");
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        this.driver = new ChromeDriver(service, options);

    }


    private void registerUser(String user) throws InterruptedException {
        driver.get("http://localhost/chatPhp/login.php");

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(user);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }


    private void logout() {
        driver.findElement(By.xpath("//a")).click();
    }

    private void loginAs(String userName) {
        driver.get("http://localhost/chatPhp/login.php");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }


    private void pickUser(String userName) {

        driver.findElement(By.xpath("//select")).click();
        driver.findElement(By.xpath("//option[@value='" + userName + "']")).click();

        driver.findElement(By.xpath("//input[@name='show']")).click();
    }

    private void sendMessage(String messageContent) {

        driver.findElement(By.xpath("//textarea")).sendKeys(messageContent);
        driver.findElement(By.xpath("//input[@name='send']")).click();
    }


    private List<String> getMessagesFromUI() {

        return driver.findElements(By.xpath("//div"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
