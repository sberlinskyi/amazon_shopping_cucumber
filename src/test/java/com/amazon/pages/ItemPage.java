package com.amazon.pages;

import com.amazon.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage {

    // Constructor to initialize WebDriver object with this page
    public ItemPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//select[@id='quantity']")
    public WebElement itemQuantityDropDown;

    @FindBy(id = "add-to-cart-button")
    public WebElement addToCartButton;

    @FindBy(id = "nav-cart-count-container")
    public WebElement cartButton;

    @FindBy(xpath = "//span[@id='priceblock_ourprice']")
    public WebElement itemPrice;

    public double getSingleItemPrice() {
        // Save the item price to later confirm the total price in cart
        String price = itemPrice.getText();

        String temp = "";
        for (int i = 0; i < price.length(); i++) {
            if(Character.isDigit(price.charAt(i))){
                temp+= ""+price.charAt(i);
            }

            if (price.charAt(i) == '.'){
                temp+=price.charAt(i);
            }
        }

        return Double.parseDouble(temp);
    }
}
