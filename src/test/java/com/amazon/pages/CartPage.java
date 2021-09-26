package com.amazon.pages;

import com.amazon.utilities.ConfigReader;
import com.amazon.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CartPage {

    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//select[@name='quantity']")
    public WebElement itemQuantity;

    @FindBy(xpath = "//span[@id='sc-subtotal-amount-activecart']")
    public WebElement totalPrice;

    // Get the currently selected quantity on the cart page
    // Create a new Select class object for this drop-down
    public int getActualItemQuantity() {
        Select selectCartQuantity = new Select(this.itemQuantity);
        return Integer.parseInt(selectCartQuantity.getFirstSelectedOption().getText());
    }

    // Get the total items price in cart
    public double getTotalItemsPrice() {
        return Double.parseDouble(this.totalPrice.getText().replace("$", ""));
    }

    public int getExpectedItemQuantityInCartFirstTest() {
        return Integer.parseInt(ConfigReader.getProperty("amazonHatQuantity"));
    }

}
