package com.julia.selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.julia.selenide.models.Product;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProductsPage {

    private SelenideElement header = $x("//div[@class='app_logo']");
    private ElementsCollection productCards = $$x("//div[@data-test='inventory-item']");
    private ElementsCollection productNames =  $$x("//div[@data-test='inventory-item-name']");
    private ElementsCollection productPrices =  $$x("//div[@data-test='inventory-item-price']");
    private SelenideElement dropdown = $x("//select[@data-test='product-sort-container']");
    private SelenideElement cart = $x("//a[@data-test='shopping-cart-link']");
    private SelenideElement burgerMenuButton = $x("//div[@class='bm-burger-button']");
    private SelenideElement logoutButton = $x("//a[@data-test='logout-sidebar-link']");

    @Step("Check that Product page is opened")
    public ProductsPage checkHeaderIsVisible(){
        header.should(visible);
        return this;
    }

    @Step("Get products names")
    public List<String> getProductNames(){
        return productNames.texts();
    }

    @Step("Get products prices")
    public List<Double> getProductPrices(){
        return productPrices
                .texts()
                .stream()
                .map(price ->
                        Double.parseDouble(price.replace("$", "")))
                .toList();
    }

    @Step("Get product cards")
    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        for (SelenideElement card : productCards){
            String name = card.$x(".//div[@data-test='inventory-item-name']").getText();
            String description = card.$x(".//div[@data-test='inventory-item-desc']").getText();
            Double price = Double.parseDouble(card.$x(".//div[@data-test='inventory-item-price']")
                    .getText().replace("$", ""));
            Product product = new Product(name, description, price);
            products.add(product);
        }
        return products;
    }

    @Step("Select from dropdown {sorting}")
    public void selectFromDropdown(String sorting){
        dropdown.selectOption(sorting);
    }

    private SelenideElement getProductCardByName(String name) {
        for(SelenideElement card : productCards){
            String productName = card
                    .$x(".//div[@data-test='inventory-item-name']")
                    .getText();

            if (productName.equalsIgnoreCase(name)) {
                return card;
            }
        }
       throw new RuntimeException("Product not found: " + name);
    }

    @Step("Click on Add to cart button for product: {name}")
    public void clickOnBtnAddToCartByName(String name) {
        SelenideElement card = getProductCardByName(name);
        card.$x(".//button").click();
    }

    public String getProductButtonText(String name) {
        SelenideElement card = getProductCardByName(name);
        return card.$x(".//button").getText();
    }

    @Step("Open cart")
    public CartPage openCart() {
        cart.click();
        return new CartPage();
    }

    @Step("Click on burger button")
    public void clickOnBurgerButton() {
        burgerMenuButton.click();
    }

    @Step("Click on logout button")
    public void clickOnLogoutButton() {
        logoutButton.click();
    }
}