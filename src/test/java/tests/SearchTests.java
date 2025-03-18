package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase{
    @Test
    @Tag("android")
    @DisplayName("Successful search")
    void successfulSearchTest() {
        step("close onboarding ", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(id("org.wikipedia.alpha:id/closeButton")).click();
        });
        step("Type search", () -> {
            $(id("org.wikipedia.alpha:id/search_container")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/search_results_list"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("android")
    @DisplayName("Open article from Main page")
    void openArticleFromMainPageTest() {
        step("close onboarding ", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(id("org.wikipedia.alpha:id/closeButton")).click();
        });
        step("Click on article header ", () ->
                $(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"org.wikipedia.alpha:id/view_featured_article_card_content_container\"))")).click());
        step("Check opening", () -> {
            $(id("org.wikipedia.alpha:id/view_page_header_image"))
                    .shouldBe(visible);
        });
    }

    @Test
    @Tag("android")
    @DisplayName("Open article from Search")
    void openArticleFromSearchTest() {
        step("close onboarding ", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(id("org.wikipedia.alpha:id/closeButton")).click();
        });
        step("Type search", () -> {
            $(id("org.wikipedia.alpha:id/search_container")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Java");
        });
        step("Open first article", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click());
        step("Check opening", () ->
                $(id("org.wikipedia.alpha:id/view_page_header_image")).shouldBe(visible));
    }

    @Test
    @Tag("android")
    @DisplayName("Onboarding experience")
    void onboardingTest() {
        step("The 1st onboarding page is open", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        });
        step("Click \"continue\"", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("The 2nd onboarding page is open", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("New ways to explore"));
        });
        step("Click \"continue\"", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("The 3rd onboarding page is open", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Reading lists with sync"));
        });
        step("Click \"continue\"", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("The 4th onboarding page is open", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Data & Privacy"));
        });
        step("Click \"done\"", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();
        });
        step("Click \"close\" game window", () -> {
            $(id("org.wikipedia.alpha:id/closeButton")).click();
        });
        step("The Explore tab is open", () -> {
            $(id("org.wikipedia.alpha:id/navigation_bar_item_large_label_view")).shouldHave(text("Explore"));
        });
    }
}
