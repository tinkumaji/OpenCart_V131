package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseCase {
    public static WebDriver driver;
    public Logger logger;
    public Properties properties;

    @BeforeClass(groups = {"sanity", "regression", "master"})
    @Parameters({"os", "browser"})
    public void setUp(String os, String browser) throws IOException {
        logger = LogManager.getLogger(this.getClass());
        FileReader file = new FileReader("./src//test//resources//config.properties");
        properties = new Properties();
        properties.load(file);

        String executionEnv = properties.getProperty("execution_env");
        if (executionEnv.equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome": driver = new ChromeDriver();
                break;
                case "edge": driver = new EdgeDriver();
                break;
                case "firefox": driver = new FirefoxDriver();
                break;
                default:
                    System.out.println("Invalid Browser...!");
            }
        }

        if (executionEnv.equalsIgnoreCase("remote")) {
            String hubUrl = "http://localhost:4444/";
            DesiredCapabilities capabilities = new DesiredCapabilities();

            if (os.equalsIgnoreCase("Windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("Mac")) {
                capabilities.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("Linux")) {
                capabilities.setPlatform(Platform.LINUX);
            } else {
                System.out.println("Invalid OS...!");
            }

            switch (browser.toLowerCase()) {
                case "chrome": capabilities.setBrowserName("chrome");
                    break;
                case "edge": capabilities.setBrowserName("edge");
                    break;
                case "firefox": capabilities.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("Invalid Browser...!");
            }

            driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("AppUrl"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"sanity", "regression", "master"})
    public void tearDown() {
        driver.quit();
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomEmail() {
        return RandomStringUtils.randomAlphabetic(5) + "" + RandomStringUtils.randomNumeric(3) + "@gmail.com";
    }

    public String randomPassword() {
        return RandomStringUtils.randomAlphabetic(5) + "@" +RandomStringUtils.randomNumeric(3);
    }

    public String captureScreenshot(String tName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tName + "_" + timeStamp + ".jpg";
        File target = new File(targetFilePath);
        source.renameTo(target);

        return targetFilePath;
    }
}
