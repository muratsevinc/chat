package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
public class ChatStepDefinitions {

    @Given("Deniz and Murat are valid users")
    public void denizAndMuratAreValidUsers() {
        System.out.println("asd");
    }

    @When("Deniz wants to send a message {string} to Murat")
    public void denizWantsToSendAMessageToMurat(String arg0) {
        System.out.println("asdd");
    }

    @Then("Murat can display the message")
    public void muratCanDisplayTheMessage() {
        System.out.println("asdddd");
    }
}
