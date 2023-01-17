import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class OpenCartActuator {
    private WebDriver driver;
    private WebDriverWait wait;


    public void initSession(String webDriver, String path){
//        webDriver = "webdriver.chrome.driver";
//        path = "C:\\Users\\User\\Desktop\\ass3sqe\\sqe-hw3\\Selenium\\chromedriver.exe";
        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        // launch website -> localhost
        driver.get("http://localhost/opencartpro/");

        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();


        /*
            If we wanted to test the web application on different devices -
                1. Open the web app
                2. Right click -> click inspect
                3. Click on the phone icon at the top left corner of the inspect window (the app changes preview format at this point)
                4. Locate the dimensions drop-down list at the top of the web app and choose device
                5. Copy dimensions size (on the right side of the drop-down list)
                   -> driver.manage().window().setSize(new Dimension(width, height));
         */

        System.out.println("Driver setup finished for - " + driver.getTitle());
    }
//
//    public void goToLogin(){
//        // locate and click on web element -> login
//        driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[2]/div/ul/li[2]")).click();
//    }
//
////    public void enterLoginInfo(String username, String password) {
////        // locate the username input box and enter username
////        // $x("//*[@id='username']")
////        // driver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
////        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']"))).sendKeys(username);
////
////        // locate the password input box and enter password
////        // $x("//*[@name='password' and @type='password']")
////        driver.findElement(By.xpath("//*[@name='password' and @type='password']")).sendKeys(password);
////
////        // locate Log in button and press
////        // $x("//*[@id='loginbtn']")
////        driver.findElement(By.id("loginbtn")).click();
////    }
//
//    public void teacherWelcomeMessage(){
//        // now to check if login process succeeded -> find the element which indicates it succeeded
//        // $x("//*[contains(text(),'Welcome, Teacher!')]")
//        driver.findElement(By.xpath("//*[contains(text(),'Welcome back, Teacher!')]"));
//    }

//    public void loginSequence(String username, String password){
//        // locate and click on web element -> login
//        goToLogin();
//
//        // enter username and password -> press login
//        enterLoginInfo(username, password);
//
//        // check for welcome message
//        teacherWelcomeMessage();
//
//    }

    public void addComment()
    {
        String product_example_path = "/html/body/main/div[2]/div/div/div[2]/div[3]/form/div/div[1]/a/img";
        String review_tab_path = "/html/body/main/div[2]/div/div/div[1]/ul/li[3]/a";
        String review_insert_name_path = "/html/body/main/div[2]/div/div/div[1]/div[3]/div[3]/form/div[2]/input";
        String review_insert_review_path = "/html/body/main/div[2]/div/div/div[1]/div[3]/div[3]/form/div[3]/textarea";
        String review_insert_ranking_path = "/html/body/main/div[2]/div/div/div[1]/div[3]/div[3]/form/div[4]/div[1]/input[5]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(product_example_path))).click();
        WebElement element_review = driver.findElement(By.xpath(review_tab_path));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(review_insert_name_path))).sendKeys("reviewer_1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(review_insert_review_path))).sendKeys("reviewer_1 review");
        WebElement element2 = driver.findElement(By.xpath(review_insert_ranking_path));
        executor.executeScript("arguments[0].click();", element2);
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void deleteProduct() {

    }

}



// to clear users input from text box: driver.findElement(By.name("q")).clear();
// to navigate backward in browser history: driver.navigate().back();
// to navigate forward in browser history: driver.navigate().forward();
// to refresh a web page: driver.navigate().refresh();
// to close a browser: driver.close();
// to close a browser and all other windows associated with the driver: driver.quit();
// to move between windows: driver.switchTo().window("windowName");
// to move between frames: driver.switchTo().frame("frameName");
// to drag and drop elements:
//     WebElement element = driver.findElement(By.name("source"));
//     WebElement target = driver.findElement(By.name("target"));
//     (new Actions(driver)).dragAndDrop(element, target).perform();
