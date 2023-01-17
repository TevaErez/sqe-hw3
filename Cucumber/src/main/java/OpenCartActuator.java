import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OpenCartActuator {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor executor;
    static private String randomProductPicketed;

    public void initSession(String webDriver, String path, String url_path){
//        webDriver = "webdriver.chrome.driver";
//        path = "C:\\Users\\User\\Desktop\\ass3sqe\\sqe-hw3\\Selenium\\chromedriver.exe";
        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        // launch website -> localhost
        driver.get(url_path);

        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();

        executor = (JavascriptExecutor)driver;

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

    private void input_text(String xamppPath, String text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xamppPath))).sendKeys(text);
    }
    private void WebDriverWaitClick(String xamppPath){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xamppPath))).click();
        waitMilliseconds(500);
    }
    private void JavascriptExecutorClick(String xamppPath) {
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(xamppPath)));
        waitMilliseconds(500);
    }

    private void JavascriptExecutorRollDown(String xamppPath) {
        executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(xamppPath)));
        waitMilliseconds(500);
    }

    private String JavascriptExecutorGetText(String xamppPath) {
        WebElement element_product = driver.findElement(By.xpath(xamppPath));
        return element_product.getText();
    }

    public void waitMilliseconds(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (Exception ignored) {
        }
    }

    public void addComment()
    {
        JavascriptExecutorRollDown("/html/body/main/div[2]/div/div/div[2]/div[1]/form/div/div[1]/a/img");
        JavascriptExecutorClick("/html/body/main/div[2]/div/div/div[2]/div[1]/form/div/div[1]/a/img");
        JavascriptExecutorRollDown("/html/body/main/div[2]/div/div/ul/li[2]/a");
        JavascriptExecutorClick("/html/body/main/div[2]/div/div/ul/li[2]/a");
        input_text("/html/body/main/div[2]/div/div/div[2]/div["+2+"]/form/div[2]/input","a reviewer");
        input_text("/html/body/main/div[2]/div/div/div[2]/div["+2+"]/form/div[3]/textarea",
                "Just A random review ny a random user to demonstrate");
        randomProductPicketed = JavascriptExecutorGetText("/html/body/main/div[2]/div/div/div[1]/div[2]/h1");
        JavascriptExecutorClick("/html/body/main/div[2]/div/div/div[2]/div["+2+"]/form/div[4]/div[1]/input[5]");

    }

    public void deleteProduct(){
        input_text("/html/body/div/div[2]/div/div/div/div/div[2]/form/div[1]/div/input", "admin");
        input_text("/html/body/div/div[2]/div/div/div/div/div[2]/form/div[2]/div[1]/input", "3322");
        WebDriverWaitClick("/html/body/div/div[2]/div/div/div/div/div[2]/form/div[3]/button");
        input_text("/html/body/div/div[2]/div[2]/div/div[1]/div/div[2]/div[1]/input", randomProductPicketed);
        WebDriverWaitClick("/html/body/div/div[2]/div[2]/div/div[1]/div/div[2]/div[6]/button");
        WebDriverWaitClick("/html/body/div/div[2]/div[2]/div/div[2]/div/div[2]/form/div[1]/table/tbody/tr[1]/td[1]/input");
        WebDriverWaitClick("/html/body/div/div[2]/div[1]/div/div/button[3]");
        driver.switchTo().alert().accept();
        driver.close();

    }

    public void submitComment(){
        waitMilliseconds(1000);
        JavascriptExecutorClick("/html/body/main/div[2]/div/div/div[2]/div[2]/form/div[5]/div/button");
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
