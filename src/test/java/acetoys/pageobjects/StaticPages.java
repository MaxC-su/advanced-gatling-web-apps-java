package acetoys.pageobjects;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class StaticPages {

    public static ChainBuilder homePage =
            exec(
                    http("Load home page")
                            .get("/")
                            .check(status().shouldBe(200))
                            .check(status().not(404), status().not(405), status().not(500))
                            .check(css("title").shouldBe("Ace Toys Online Shop"))
                            .check(css("#_csrf", "content").saveAs("csrfToken"))
            );

    public static ChainBuilder ourStoryPage =
            exec(
                    http("Load our story page")
                            .get("/our-story")
            );


    public static ChainBuilder getInTouchPage =
            exec(
                    http("Load Get In Touch page")
                            .get("/get-in-touch")
                            .check(substring("as we are not actually a real store").exists())
            );

}
