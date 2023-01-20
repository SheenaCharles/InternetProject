package com.heroku;
import dataProvider.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.net.MalformedURLException;
import java.net.URL;

public class BasicSetupPageTest extends ConfigReader {

    protected static WebDriver driver;
//    String platform = System.getenv("platform");
//    String browser= System.getenv("browser");
//    String osOption = System.getenv("os");
//    String version = System.getenv("version");
    String platform = System.getProperty("platform");
    String browser = System.getProperty("browser");
    String osOption = System.getProperty("os");
    String version = System.getProperty("version");
    protected URL url;

    @BeforeSuite
    public void browswerSetup() {
         if(platform.equalsIgnoreCase("local")) {
             try {
                 if (browser.equalsIgnoreCase("chrome")) {
                     WebDriverManager.chromedriver().setup();
                     driver = new ChromeDriver();
                 } else if (browser.equalsIgnoreCase("firefox")) {
                     WebDriverManager.firefoxdriver().setup();
                     driver = new FirefoxDriver();
                 } else if (browser.equalsIgnoreCase("edge")) {
                     WebDriverManager.edgedriver().setup();
                     driver = new EdgeDriver();
                 } else {
                    System.out.println("The Browser type is Undefined");
                }
             driver.manage().window().maximize();
             driver.get("https://the-internet.herokuapp.com/");
         } catch (NullPointerException e) {
                 throw new RuntimeException(e);
             } catch (Exception e) {
                 System.out.println(e.getMessage());
             }
        } else if (platform.equalsIgnoreCase("sauceLab")) {
            cloudConfiguration( "https://" + getSauceLabUserName() +":"+ getSauceLabAccessKey() + getSauceLabUrl());
        } else if (platform.equalsIgnoreCase("lambdaTest")) {
            cloudConfiguration( "https://" + getLambdaTestUserName() +":"+ getLambdaTestAccessKey() + getLambdaURL());
        }
    }

    public void  cloudConfiguration(String testUrl) {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", browser);
            caps.setCapability("platform", osOption);
            caps.setCapability("version", version);
            url = new URL(testUrl);
            driver = new RemoteWebDriver(url, caps);
            driver.get("https://the-internet.herokuapp.com/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterSuite
    public void closeBrowser() {
        driver.quit();
    }
}


//    @BeforeMethod
//    public void browswerSetup() {
//        if (runEnvironmentOption.equalsIgnoreCase("local")) {
//            if (browserOption.equalsIgnoreCase("chrome")) {
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//            } else if (browserOption.equalsIgnoreCase("firefox")) {
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//            } else if (browserOption.equalsIgnoreCase("edge")) {
//                WebDriverManager.firefoxdriver().setup();
//                driver = new EdgeDriver();
//            } else {
//                System.out.println("The Browser type is Undefined");
//            }
//
//        } else if (runEnvironmentOption.equalsIgnoreCase("saucelab")) {
//            if (browserOption.equalsIgnoreCase("chrome")) {
//                chromeOptionForSauceLab();
//            } else if (browserOption.equalsIgnoreCase("firefox")) {
//                fireFoxOptionForSauceLab();
//            } else if (browserOption.equalsIgnoreCase("edge")) {
//                edgeOptionForSauceLab();
//            } else {
//                System.out.println("The Browser type is Undefined");
//            }
//
//        } else if (runEnvironmentOption.equalsIgnoreCase("lambdatest")) {
//            if (browserOption.equalsIgnoreCase("chrome")) {
//                chromeOptionForLambdaTest();
//            } else if (browserOption.equalsIgnoreCase("firefox")) {
//                firefoxOptionForLambdaTest();
//            } else if (browserOption.equalsIgnoreCase("edge")) {
//                edgeOptionForLambdaTest();
//            } else {
//                System.out.println("The Browser type is Undefined");
//            }
//        }
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//        driver.get(baseUrl);
//    }
//
//    public void chromeOptionForSauceLab() {
//        ChromeOptions browserOptions = new ChromeOptions();
//        browserOptions.setPlatformName("Windows 11");
//        browserOptions.setBrowserVersion("latest");
//        Map<String, Object> sauceOptions = new HashMap<>();
//        sauceOptions.put("build", "selenium-build-8BQTC");
//        browserOptions.setCapability("sauce:options", sauceOptions);
//        URL url = null;
//        try {
//            url = new URL("https://oauth-saranyas.sridhar-b0c63:f3b84b6b-5aac-49b2-ae85-02746a3b15bd@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        driver = new RemoteWebDriver(url, browserOptions);
//    }
//
//    public void fireFoxOptionForSauceLab() {
//        FirefoxOptions browserOptions = new FirefoxOptions();
//        browserOptions.setPlatformName("Windows 11");
//        browserOptions.setBrowserVersion("latest");
//        Map<String, Object> sauceOptions = new HashMap<>();
//        sauceOptions.put("build", "selenium-build-8BQTC");
//        browserOptions.setCapability("sauce:options", sauceOptions);
//        URL url = null;
//        try {
//            url = new URL("https://oauth-saranyas.sridhar-b0c63:f3b84b6b-5aac-49b2-ae85-02746a3b15bd@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        driver = new RemoteWebDriver(url, browserOptions);
//    }
//
//    public void edgeOptionForSauceLab() {
//        EdgeOptions browserOptions = new EdgeOptions();
//        browserOptions.setPlatformName("Windows 11");
//        browserOptions.setBrowserVersion("latest");
//        Map<String, Object> sauceOptions = new HashMap<>();
//        sauceOptions.put("build", "selenium-build-8BQTC");
//        browserOptions.setCapability("sauce:options", sauceOptions);
//        URL url = null;
//        try {
//            url = new URL("https://oauth-saranyas.sridhar-b0c63:f3b84b6b-5aac-49b2-ae85-02746a3b15bd@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        driver = new RemoteWebDriver(url, browserOptions);
//    }
//
//    public void chromeOptionForLambdaTest() {
//        ChromeOptions browserOptions = new ChromeOptions();
//        browserOptions.setPlatformName("Windows 10");
//        browserOptions.setBrowserVersion("108.0");
//        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
//        ltOptions.put("username", "saranyas.sridhar");
//        ltOptions.put("accessKey", "9L68fxvF1p8BuHoe5MLUPizqWGrr5bvlv55o70xrszgA06XPCp");
//        ltOptions.put("project", "Untitled");
//        ltOptions.put("w3c", true);
//        ltOptions.put("plugin", "java-java");
//        browserOptions.setCapability("LT:Options", ltOptions);
//        URL url = null;
//        try {
//            url = new URL("https://saranyas.sridhar:9L68fxvF1p8BuHoe5MLUPizqWGrr5bvlv55o70xrszgA06XPCp@hub.lambdatest.com/wd/hub");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        driver = new RemoteWebDriver(url, browserOptions);
//    }
//
//    public void firefoxOptionForLambdaTest() {
//        FirefoxOptions browserOptions = new FirefoxOptions();
//        browserOptions.setPlatformName("Windows 10");
//        browserOptions.setBrowserVersion("108.0");
//        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
//        ltOptions.put("username", "saranyas.sridhar");
//        ltOptions.put("accessKey", "9L68fxvF1p8BuHoe5MLUPizqWGrr5bvlv55o70xrszgA06XPCp");
//        ltOptions.put("project", "Untitled");
//        ltOptions.put("w3c", true);
//        ltOptions.put("plugin", "java-java");
//        browserOptions.setCapability("LT:Options", ltOptions);
//        URL url = null;
//        try {
//            url = new URL("https://saranyas.sridhar:9L68fxvF1p8BuHoe5MLUPizqWGrr5bvlv55o70xrszgA06XPCp@hub.lambdatest.com/wd/hub");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        driver = new RemoteWebDriver(url, browserOptions);
//    }
//
//    public void edgeOptionForLambdaTest() {
//        EdgeOptions browserOptions = new EdgeOptions();
//        browserOptions.setPlatformName("Windows 10");
//        browserOptions.setBrowserVersion("108.0");
//        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
//        ltOptions.put("username", "saranyas.sridhar");
//        ltOptions.put("accessKey", "9L68fxvF1p8BuHoe5MLUPizqWGrr5bvlv55o70xrszgA06XPCp");
//        ltOptions.put("project", "Untitled");
//        ltOptions.put("w3c", true);
//        ltOptions.put("plugin", "java-java");
//        browserOptions.setCapability("LT:Options", ltOptions);
//        URL url = null;
//        try {
//            url = new URL("https://saranyas.sridhar:9L68fxvF1p8BuHoe5MLUPizqWGrr5bvlv55o70xrszgA06XPCp@hub.lambdatest.com/wd/hub");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        driver = new RemoteWebDriver(url, browserOptions);
//    }



