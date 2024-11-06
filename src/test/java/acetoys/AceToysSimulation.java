package acetoys;

import java.time.Duration;
import java.util.*;

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
      http("Load home page")
        .get("/")
    )
    .pause(2)
    .exec(
      http("Load our story page")
        .get("/our-story")
    )
    .pause(2)
    .exec(
      http("Load Get In Touch page")
        .get("/get-in-touch")
    )
    .pause(2)
    .exec(
      http("Load the Products List Page - Category: All Products")
        .get("/category/all")
    )
    .pause(2)
    .exec(
      http("Load Next Page Of Products - Page 1")
        .get("/category/all?page=1")
    )
    .pause(2)
    .exec(
      http("Load Next Page Of Products - Page 2")
        .get("/category/all?page=2")
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
      http("Load the Products List Page - Category: Babies Toys")
        .get("/category/babies-toys")
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
        .formParam("_csrf", "55f7a969-cab9-4641-bb03-7d0c59bbd3a3")
        .formParam("username", "user1")
        .formParam("password", "pass")
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
        .formParam("_csrf", "9a971d68-84a9-445c-bab9-c5f125f8016c")
    );

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
