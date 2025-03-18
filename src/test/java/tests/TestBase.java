package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.LocalDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.id;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        String deviceHost = System.getProperty("deviceHost", "local"); // Значение по умолчанию "local"

        switch (deviceHost) {
            case "browserstack" -> {
                Configuration.browser = BrowserstackDriver.class.getName();
            }
            case "local" -> {
                Configuration.browser = LocalDriver.class.getName();
            }
            default -> {
                throw new IllegalArgumentException("Unsupported deviceHost: " + deviceHost);
            }
        }
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
//        if (System.getProperty("deviceHost").equals("local")) {
//            $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
//            $(id("org.wikipedia.alpha:id/closeButton")).click();
//
//        }
    }


    @AfterEach
    void addAttachments() {
        if (System.getProperty("deviceHost").equals("browserstack")) {
            String sessionId = Selenide.sessionId().toString();
            System.out.println(sessionId);
            Attach.pageSource();
            Attach.addVideo(sessionId);
        }
        closeWebDriver();
    }
}
