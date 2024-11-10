package acetoys.pageobjects;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Category {

    private static final String NEXT_PAGE_PRODUCTS_SELECTOR = ".page-item.active";
    private static final String PRODUCTS_CATEGORY_SELECTOR = "#CategoryName";

    private static final String ALL_PRODUCTS_PAGE_CATEGORY = "Load the Products List Page - Category: All Products";
    private static final String ALL_PRODUCTS_ENDPOINT = "/category/all";
    private static final String ALL_PRODUCTS_SELECTOR_VALUE = "All Products";

    private static final String BABIES_TOYS_PAGE_CATEGORY = "Load the Products List Page - Category: Babies Toys";
    private static final String BABIES_TOYS_ENDPOINT = "/category/babies-toys";
    private static final String BABIES_TOYS_SELECTOR_VALUE = "Babies Toys";

    private static final String SECOND_PAGE_PRODUCTS_CATEGORY = "Load second page of products";
    private static final String SECOND_PAGE_PRODUCTS_ENDPOINT = "/category/all?page=1";
    private static final String SECOND_PAGE_PRODUCTS_VALUE = "2";

    private static final String THIRD_PAGE_PRODUCTS_CATEGORY = "Load third page of products";
    private static final String THIRD_PAGE_PRODUCTS_ENDPOINT = "/category/all?page=2";
    private static final String THIRD_PAGE_PRODUCTS_VALUE = "3";


    public static ChainBuilder getBuilder(String categoryName, String endpoint, String selector, String value) {
        return exec(
                http(categoryName)
                        .get(endpoint)
                        .check(css(selector).shouldBe(value))
        );
    }

    public static ChainBuilder productListByCategoryAllProducts = getBuilder(
            ALL_PRODUCTS_PAGE_CATEGORY,
            ALL_PRODUCTS_ENDPOINT,
            PRODUCTS_CATEGORY_SELECTOR,
            ALL_PRODUCTS_SELECTOR_VALUE
    );

    public static ChainBuilder productListByCategoryBabiesToys = getBuilder(
            BABIES_TOYS_PAGE_CATEGORY,
            BABIES_TOYS_ENDPOINT,
            PRODUCTS_CATEGORY_SELECTOR,
            BABIES_TOYS_SELECTOR_VALUE
    );

    public static ChainBuilder loadSecondPageOfProducts = getBuilder(
            SECOND_PAGE_PRODUCTS_CATEGORY,
            SECOND_PAGE_PRODUCTS_ENDPOINT,
            NEXT_PAGE_PRODUCTS_SELECTOR,
            SECOND_PAGE_PRODUCTS_VALUE
    );

    public static ChainBuilder loadThirdPageOfProducts = getBuilder(
            THIRD_PAGE_PRODUCTS_CATEGORY,
            THIRD_PAGE_PRODUCTS_ENDPOINT,
            NEXT_PAGE_PRODUCTS_SELECTOR,
            THIRD_PAGE_PRODUCTS_VALUE
    );

}
