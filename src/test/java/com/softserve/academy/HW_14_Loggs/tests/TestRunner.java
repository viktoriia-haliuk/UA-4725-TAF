package com.softserve.academy.HW_14_Loggs.tests;

import com.softserve.academy.HW_14_Loggs.pages.SignInPage;
import com.softserve.academy.HW_14_Loggs.utils.EnvUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@ExtendWith(RunnerExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class TestRunner {
    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    private final String LOCALSTORAGE_REMOVE = "window.localStorage.removeItem('%s');";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected SignInPage signInPage;

    private JavascriptExecutor javascriptExecutor;

    @BeforeAll
    public static void setUpBeforeAll() {
        String browser = EnvUtils.getBrowser();

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
        } else {
            WebDriverManager.firefoxdriver().setup();
        }

    }

    private WebDriver createDriver() {
        String browser = EnvUtils.getBrowser();
        return browser.equalsIgnoreCase("chrome")
                ? new ChromeDriver()
                : new FirefoxDriver();
    }

    @BeforeEach
    public void setUpBeforeEach(TestInfo testInfo) {
        logger.info("=== Starting test: {} ===", testInfo.getDisplayName());

        driver = createDriver();
        driver.manage().window().setSize(new Dimension(1264, 798));

        driver.get(EnvUtils.getBaseUrl());

        javascriptExecutor = (JavascriptExecutor) driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        signInPage = new SignInPage(driver, wait); // ✅ сторінка готова для тестів
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) {
        if (!RunnerExtension.isTestSuccessful) {

            logger.error("❌ Test FAILED: {}", testInfo.getDisplayName());

            takeScreenShot();
        }
        else {
            logger.info("✅ Test PASSED: {}", testInfo.getDisplayName());
        }

        driver.manage().deleteAllCookies();
        javascriptExecutor.executeScript(String.format(LOCALSTORAGE_REMOVE, "accessToken"));
        javascriptExecutor.executeScript(String.format(LOCALSTORAGE_REMOVE, "refreshToken"));

        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));

        if (driver != null) {
            driver.quit();
        }

    }

    private void takeScreenShot() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_TEMPLATE);
        String currentTime = localDate.format(formatter);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

}
