import graphql.Assert;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class OpenCartActuator {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor executor;

    private String randomProductPicketed;


    private  static final int  WAITTIME = 500;

    public void initSession(String webDriver, String path, String url_path){
        System.setProperty(webDriver, path);

        this.driver = new ChromeDriver();

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        driver.get(url_path);

        driver.manage().window().maximize();

        executor = (JavascriptExecutor)driver;

        System.out.println("Driver setup finished for - " + driver.getTitle());
    }

    private void input_text(String xamppPath, String text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xamppPath))).sendKeys(text);
    }
    private void WebDriverWaitClick(String xamppPath){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xamppPath))).click();
        waitMilliseconds(WAITTIME);
    }
    private void JavascriptExecutorClick(String xamppPath) {
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(xamppPath)));
        waitMilliseconds(WAITTIME);
    }

    private void JavascriptExecutorRollDown(String xamppPath) {
        executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(xamppPath)));
        waitMilliseconds(WAITTIME);
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
        WebDriverWaitClick("/html/body/div/div[2]/div[2]/div/div[2]/div/div[2]/form/div[1]/table/tbody/tr[1]/td[1]/input");
        WebDriverWaitClick("/html/body/div/div[2]/div[1]/div/div/button[3]");
        driver.switchTo().alert().accept();
        driver.close();
    }

    public void submitComment(){
        JavascriptExecutorRollDown("/html/body/main/div[2]/div/div/ul/li[2]/a");
        JavascriptExecutorClick("/html/body/main/div[2]/div/div/ul/li[2]/a");
    }

    public void makeSureThereIsAProduct(String username, String password, String productName) {
        driver.get("http://localhost/opencartpro/admin/index.php?route=catalog/product");
        waitMilliseconds(WAITTIME);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-username"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-password"))).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form-login\"]/div[3]/button"))).click();
        waitMilliseconds(WAITTIME);
        try {
            int i = 1;
            String firstProductInList;
            while (true) {
                firstProductInList =
                        JavascriptExecutorGetText("//*[@id=\"form-product\"]/div[1]/table/tbody/tr["+i+"]/td[3]");
                firstProductInList = firstProductInList.split("\nEnabled")[0];
                if (firstProductInList.equals(productName))
                    return;
                i++;
            }
        }
        catch (Exception noProductsInDB){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div[1]/div/div/a"))).click();
            waitMilliseconds(WAITTIME);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-name-1"))).sendKeys(productName);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-meta-title-1"))).sendKeys("Product meta tag");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form-product\"]/ul/li[2]/a"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-model"))).sendKeys("Product model");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form-product\"]/ul/li[11]/a"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-keyword-0-1"))).sendKeys(productName+"seo");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div[1]/div/div/button"))).click();
            waitMilliseconds(WAITTIME);
            makeSureThereIsAProduct(username,password, productName);
        }
    }

    public void makeSureThereAProductIsFeaturedInHomePage(String username, String password, String productName) {
        driver.get("http://localhost/opencartpro/admin/index.php?route=marketplace/extension");
        waitMilliseconds(WAITTIME);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-username"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-password"))).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form-login\"]/div[3]/button"))).click();
        waitMilliseconds(WAITTIME);
        Select drpCountry = new Select(driver.findElement(By.id("input-type")));
        drpCountry.selectByVisibleText("Modules (11)");
        waitMilliseconds(WAITTIME);
        JavascriptExecutorClick("/html/body/div/div[2]/div[2]/div/div[2]/div/fieldset[2]/div[2]/table/tbody/tr[9]/td[3]/a[1]");
        input_text("/html/body/div/div[2]/div[2]/div/div[2]/form/div[2]/div/input", productName);
        input_text("/html/body/div/div[2]/div[2]/div/div[2]/form/div[2]/div/input", " ");
        waitMilliseconds(WAITTIME);
        input_text("/html/body/div/div[2]/div[2]/div/div[2]/form/div[2]/div/input", String.valueOf(Keys.BACK_SPACE));
        WebDriverWaitClick("/html/body/div/div[2]/div[1]/div/div/button");
    }

    public void navigateToReviewSection(String productName) {
        driver.get("http://localhost/opencartpro");
        JavascriptExecutorRollDown("//*[@id=\"content\"]/div[2]/div[1]/form/div/div[2]/div[1]/h4/a");
        int i = 1;
        String firstProductInList;
        while (true) {
            firstProductInList =
                    JavascriptExecutorGetText("//*[@id=\"content\"]/div[2]/div["+i+"]/form/div/div[2]/div[1]/h4/a");
            firstProductInList = firstProductInList.split("\nEnabled")[0];
            if (firstProductInList.equals(productName))
                break;
            i++;
        }
        JavascriptExecutorClick("//*[@id=\"content\"]/div[2]/div["+i+"]/form/div/div[1]/a/img");
        JavascriptExecutorClick("/html/body/main/div[2]/div/div/ul/li[2]/a");
        JavascriptExecutorRollDown("/html/body/main/div[2]/div/div/ul/li[2]");
    }

    public void fillAReview(List<String> reviewDetails) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-name"))).sendKeys(reviewDetails.get(0));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-text"))).sendKeys(reviewDetails.get(1));
        randomProductPicketed = JavascriptExecutorGetText("//*[@id=\"content\"]/div[1]/div[2]/h1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-rating\"]/input["+
                reviewDetails.get(2)+"]"))).click();
        waitMilliseconds(WAITTIME);
    }

    public void checkAdded() {
        try {
            JavascriptExecutorClick("/html/body/main/div[2]/div/div/div[2]/div[2]/form/div[5]/div/button");
            driver.findElement(By.id("alert"));
        } catch (Exception exception) {
            Assert.assertTrue(false);
        }
    }

    public void close() {
        driver.close();
    }

    public void navigateToProduct(String productName) {
//        input_text("/html/body/div/div[2]/div/div/div/div/div[2]/form/div[1]/div/input", "admin");
//        input_text("/html/body/div/div[2]/div/div/div/div/div[2]/form/div[2]/div[1]/input", "3322");
//        WebDriverWaitClick("/html/body/div/div[2]/div/div/div/div/div[2]/form/div[3]/button");
        JavascriptExecutorClick("//*[@id=\"input-name\"]");
        input_text("//*[@id=\"input-name\"]", productName);
        WebDriverWaitClick("//*[@id=\"button-filter\"]");
    }

    public void deleteProduct(String productName){
        WebDriverWaitClick("//*[@id=\"form-product\"]/div[1]/table/tbody/tr/td[1]/input");
        WebDriverWaitClick("/html/body/div/div[2]/div[1]/div/div/button[3]");
        driver.switchTo().alert().accept();
    }

    public void checkDeleted() {
        try {
            driver.findElement(By.id("alert"));

        } catch (Exception exception) {
            Assert.assertTrue(false);
        }
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
