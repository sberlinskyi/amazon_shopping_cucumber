package com.amazon.step_definitions;

import com.amazon.pages.LandingPage;
import com.amazon.utilities.ConfigReader;
import com.amazon.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;

public class AmazonShopping_StepDefinitions {

    LandingPage landingPage;

    @Given("User is on the Amazon home page")
    public void user_is_on_the_amazon_home_page() {
        // Opening page
        Driver.getDriver().get(ConfigReader.getProperty("amazonMainURL"));
    }
    @When("User searches for hats for men")
    public void user_searches_for_hats_for_men() {
        // Enter text into a search bar
        landingPage = new LandingPage();
        landingPage.searchBar.sendKeys("hats for men", Keys.ENTER);
    }
    @When("User adds the first hat from results to cart with quantity two")
    public void user_adds_the_first_hat_from_results_to_cart_with_quantity_two() {

    }
    @Then("The total price and quantity should be correct")
    public void the_total_price_and_quantity_should_be_correct() {

    }
    @When("User reduces the quantity from two to one")
    public void user_reduces_the_quantity_from_two_to_one() {

    }
    @Then("Total price and quantity has been correctly changed")
    public void total_price_and_quantity_has_been_correctly_changed() {

    }
}
