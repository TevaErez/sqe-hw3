import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("hellocucumber")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class RunCucumberTest {

    private String ADMIN_USERNAME = "admin";
    private String ADMIN_PASSWORD = "3322";
//    private String COURSE_NAME = "Demo course";
//    private String ADMIN_USERNAME = "admin";
//    private String ADMIN_PASSWORD = "3322";
//    private int DEFAULT_LENGTH = 5;
    private OpenCartActuator opencart;
    private String webDriver = "webdriver.chrome.driver";
    private String seleniumPath = "C:\\Users\\User\\Desktop\\ass3sqe\\sqe-hw3\\Selenium\\chromedriver.exe";

    public RunCucumberTest(String url_path) {
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
        opencart = new OpenCartActuator();
        opencart.initSession(webDriver, seleniumPath, url_path);
    }

    public void addCommentTest() {
        System.out.println("--------------- STARTING MOODLE MULTIPLE CHOICE QUIZ TEST ---------------");
        System.out.println("--------------- MULTIPLE CHOICE QUIZ TEST - TEACHER LOGIN ---------------");
        opencart.addComment();
    }

    public void deleteProductTest() {
        System.out.println("--------------- STARTING MOODLE MULTIPLE CHOICE QUIZ TEST ---------------");
        System.out.println("--------------- MULTIPLE CHOICE QUIZ TEST - TEACHER LOGIN ---------------");
        opencart.deleteProduct();
    }

    public void submitComment(){
        System.out.println("--------------- STARTING MOODLE MULTIPLE CHOICE QUIZ TEST ---------------");
        System.out.println("--------------- MULTIPLE CHOICE QUIZ TEST - TEACHER LOGIN ---------------");
        opencart.submitComment();
    }
}
