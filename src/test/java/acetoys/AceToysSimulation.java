package acetoys;

import java.time.Duration;
import java.util.*;

import acetoys.pageobjects.Category;
import acetoys.pageobjects.StaticPages;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class AceToysSimulation extends Simulation {

    private static final String DOMAIN = "https://acetoys.uk";

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl(DOMAIN)
            .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*"))
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("bg,en;q=0.9");


    private ScenarioBuilder scn = scenario("AceToysSimulation")
            .exec(
                    StaticPages.homePage
            )
            .pause(2)
            .exec(
                    StaticPages.ourStoryPage
            )
            .pause(2)
            .exec(
                    StaticPages.getInTouchPage
            )
            .pause(2)
            .exec(
                    Category.productListByCategoryAllProducts
            )
            .pause(2)
            .exec(
                    Category.loadSecondPageOfProducts
            )
            .pause(2)
            .exec(
                    Category.loadThirdPageOfProducts
            )
            .pause(2)
            .exec(
                    http("Load Products Details Page - Product: Darts Board")
                            .get("/product/darts-board")
            )
            .pause(2)
            .exec(
                    http("Add Product to Cart: Product Id: 19")
                            .get("/cart/add/19")
            )
            .pause(2)
            .exec(
                   Category.productListByCategoryBabiesToys
            )
            .pause(2)
            .exec(
                    http("Add Product to Cart: Product Id: 4")
                            .get("/cart/add/4")
            )
            .pause(2)
            .exec(
                    http("Add Product to Cart: Product Id: 4")
                            .get("/cart/add/4")
            )
            .pause(2)
            .exec(
                    http("View Cart")
                            .get("/cart/view")
            )
            .pause(2)
            .exec(
                    http("Login User")
                            .post("/login")
                            .formParam("_csrf", "#{csrfToken}")
                            .formParam("username", "user1")
                            .formParam("password", "pass")
                            .check(css("#_csrf", "content").saveAs("csrfTokenLogged"))
            )
            .exec(session -> {
                        System.out.println(session);
                        System.out.println(session.getString("csrfTokenLogged"));
                        return session;
                    }
            )
            .pause(2)
            .exec(
                    http("Increase Product Quantity in Cart - Product Id: 19")
                            .get("/cart/add/19?cartPage=true")
            )
            .pause(2)
            .exec(
                    http("Increase Product Quantity in Cart - Product Id: 19")
                            .get("/cart/add/19?cartPage=true")
            )
            .pause(2)
            .exec(
                    http("Subtract Product Quantity in Cart - Product Id: 19")
                            .get("/cart/subtract/19")
            )
            .pause(2)
            .exec(
                    http("Checkout")
                            .get("/cart/checkout")
            )
            .pause(2)
            .exec(
                    http("Logout")
                            .post("/logout")
                            .formParam("_csrf", "#{csrfTokenLogged}")
            );

    {
        setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
    }
}
