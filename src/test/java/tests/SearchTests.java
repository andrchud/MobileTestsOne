package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase{
    @Test
    @Tag("android")
    @DisplayName("Successful search")
    void successfulSearchTest() {
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_container"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("android")
    @DisplayName("Open article from Main page")
    void openArticleFromMainPageTest() {
        step("Click on article header ", () ->
                $(id("org.wikipedia.alpha:id/horizontal_scroll_list_item_text")).click());
        step("Check opening", () ->
                $$(id("org.wikipedia.alpha:id/view_news_fullscreen_link_card_list"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("android")
    @DisplayName("Open article from Search")
    void openArticleFromSearchTest() {
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Open first article", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_container")).first().click());
        step("Check opening", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text"))
                        .shouldHave(text("An error occurred")));
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
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Send anonymous data"));
        });
        step("Click \"Accept\"", () -> {
            $(id("org.wikipedia.alpha:id/acceptButton")).click();
        });
        step("The Explore tab is open", () -> {
            $(id("org.wikipedia.alpha:id/navigation_bar_item_large_label_view")).shouldHave(text("Explore"));
        });
    }
}
