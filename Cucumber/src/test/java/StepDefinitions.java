import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StepDefinitions {
    private String ADMIN_USERNAME = "admin";
    private String ADMIN_PASSWORD = "3322";

    //    // $$*TODO* explain what this step does$$
//    @Given("an example scenario")
//    public void anExampleScenario() {
//    }
//
//    // $$*TODO* explain what this step does$$
//    @When("all step definitions are implemented")
//    public void allStepDefinitionsAreImplemented() {
//    }
//
//    // $$*TODO* explain what this step does$$
//    @Then("the scenario passes")
//    public void theScenarioPasses() {
//    }
    private static List<OpenCartActuator> allOpenCarts;
    private static OpenCartActuator openCart;

    @Given("user in home page")
    public void userInHomePage() {
        System.out.println("--------------- INITIALIZING OPENCART TEST - OPENING WEBPAGE ---------------");
        if (allOpenCarts == null) {
            allOpenCarts = new ArrayList<>();
        }
        openCart = new OpenCartActuator();
        allOpenCarts.add(openCart);
        openCart.initSession("webdriver.chrome.driver",
                "C:\\Users\\User\\Desktop\\ass3sqe\\sqe-hw3\\Selenium\\chromedriver.exe",
                "http://localhost/opencartpro/");

    }

    @And("at least one product")
    public void atLeastOneProduct() {
        openCart.makeSureThereIsAProduct(ADMIN_USERNAME, ADMIN_PASSWORD);
    }

    @And("product featured in home page")
    public void productFeaturedInHomePage() {
        openCart.makeSureThereAProductIsFeaturedInHomePage(ADMIN_USERNAME, ADMIN_PASSWORD);
    }

    @When("navigate to review section of first product featured")
    public void navigateToReviewSectionOfFirstProductFeatured() {
        openCart.navigateToReviewSection();
    }

    @And("submit review with {string} details")
    public void submitReviewWithReviewDetailsDetails(String ReviewDetails) {
        openCart.fillAReview(Arrays.asList(ReviewDetails.split(",")));
    }

    @Then("new comment added")
    public void newCommentAdded() {
        openCart.checkAdded();
    }


    @And("close")
    public void close() {
        openCart.close();
    }

}
