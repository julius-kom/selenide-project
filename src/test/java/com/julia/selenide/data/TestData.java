package com.julia.selenide.data;

import com.julia.selenide.models.Product;

import java.util.List;

public class TestData  {

    public static final String STANDARD_USER = "standard_user";
    public static final String STANDARD_PASSWORD = "secret_sauce";
    public static final String WRONG_PASSWORD = "wrong_password";

    public static final List<Product> EXPECTED_PRODUCTS = List.of(
            new Product("Sauce Labs Backpack",
                    "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising " +
                            "style with unequaled laptop and tablet protection.",
                    29.99),
            new Product("Sauce Labs Bike Light",
                    "A red light isn't the desired state in testing but it sure helps when riding your " +
                            "bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
                    9.99),
            new Product("Sauce Labs Bolt T-Shirt",
                    "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American" +
                            " Apparel, 100% ringspun combed cotton, heather gray with red bolt.",
                    15.99),
            new Product("Sauce Labs Fleece Jacket",
                    "It's not every day that you come across a midweight quarter-zip fleece jacket " +
                            "capable of handling everything from a relaxing day outdoors to a busy day at the office.",
                    49.99),
            new Product("Sauce Labs Onesie",
                    "Rib snap infant onesie for the junior automation engineer in development." +
                            " Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.",
                    7.99),
            new Product("Test.allTheThings() T-Shirt (Red)",
                    "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard " +
                            "to automate a few tests. Super-soft and comfy ringspun combed cotton.",
                    15.99)
    );


}
