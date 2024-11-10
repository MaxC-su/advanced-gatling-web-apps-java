package acetoys.pageobjects;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Product {

    public static ChainBuilder loadProductDetailsPageDartsBoard = exec(
            http("Load product details page - Darts Board")
                    .get("/product/darts-board")
                    .check(css(".display-3.mb-5").shouldBe("Darts Board"))
    );

    public static ChainBuilder addProductToCartId19 = exec(
            http("Add Product to Cart: Product Id: 19")
                    .get("/cart/add/19")
                    .check(substring("You have <span>1</span> products in your Basket").exists())
    );

    public static ChainBuilder addProductToCartId4 = exec(
            http("Add Product to Cart: Product Id: 4")
                    .get("/cart/add/4")
                    .check(substring("You have <span>2</span> products in your Basket").exists())
    );

}
