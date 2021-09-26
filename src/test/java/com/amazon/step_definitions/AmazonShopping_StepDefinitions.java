package com.amazon.step_definitions;

import com.amazon.pages.CartPage;
import com.amazon.pages.ItemPage;
import com.amazon.pages.LandingPage;
import com.amazon.pages.ResultsPage;
import com.amazon.utilities.BrowserUtils;
import com.amazon.utilities.ConfigReader;
import com.amazon.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonShopping_StepDefinitions {

    LandingPage landingPage;
    ItemPage itemPage;
    ResultsPage resultsPage;
    CartPage cartPage;
    double singleItemPrice;
    Select select;
    Select cartQuantity;
    String cartItemQuantityAfterChange;
    WebDriverWait wait;

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
        // Select the first result
        resultsPage = new ResultsPage();
        wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(resultsPage.firstResult));
        resultsPage.firstResult.click();

        // Save the single item price while here
        itemPage = new ItemPage();
        singleItemPrice = itemPage.getSingleItemPrice();

        // Add it to cart in quantity of 2
        // Create an object of Select class - for select drop-down
        select = new Select(itemPage.itemQuantityDropDown);
        select.selectByVisibleText(ConfigReader.getProperty("amazonHatQuantity"));

        // Click Add to Cart button
        itemPage.addToCartButton.click();
    }
    @Then("The total price and quantity should be correct")
    public void the_total_price_and_quantity_should_be_correct() {
        // Go to cart and confirm
        // Go to cart
        wait.until(ExpectedConditions.elementToBeClickable(itemPage.cartButton));
        itemPage.cartButton.click();

        cartPage = new CartPage();

        // Confirm quantity is correct
        Assert.assertEquals(cartPage.getActualItemQuantity(), cartPage.getExpectedItemQuantityInCartFirstTest());

        // Compare it with item price form item page * actual item quality
        double expectedTotalPriceForItems = singleItemPrice * cartPage.getActualItemQuantity();

        Assert.assertEquals(expectedTotalPriceForItems, cartPage.getTotalItemsPrice(), 0);
    }
    @When("User reduces the quantity from two to one")
    public void user_reduces_the_quantity_from_two_to_one() {
        // Write code here that turns the phrase above into concrete actions
        cartQuantity = new Select(cartPage.itemQuantity);
        // Change the quantity in the drop-down to 1
        cartQuantity.selectByVisibleText("1");

        BrowserUtils.sleep(2);

        // Retrieve how many items are in cart after the change
        cartItemQuantityAfterChange = cartQuantity.getFirstSelectedOption().getText();
    }
    @Then("Total price and quantity has been correctly changed")
    public void total_price_and_quantity_has_been_correctly_changed() {
        // Confirm that only one item is selected in the cart
        Assert.assertEquals(cartItemQuantityAfterChange, "1");

        // Calculate what a price should be: item quantity * item price
        double expectedTotalPrice = cartPage.getActualItemQuantity() * singleItemPrice;

        // Retrieve the actual total price via calling getTotalItemsPrice() method

        // Assert results
        Assert.assertEquals(expectedTotalPrice, cartPage.getTotalItemsPrice(), 0);
    }
}
