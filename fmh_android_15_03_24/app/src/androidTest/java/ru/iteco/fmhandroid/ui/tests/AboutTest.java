package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.release;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageObjects.AboutPage;
import ru.iteco.fmhandroid.ui.utils.ScreenshotWatcher;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutTest extends AboutPage {

    @Rule
    public RuleChain screenshotRule = RuleChain
            .outerRule(new ActivityScenarioRule<>(AppActivity.class))
            .around(new ScreenshotWatcher());

    @Before
    public void setupIntents() {
        init();
        if (!checkIfLoggedIn()) {
            logIn();
        }
    }

    @After
    public void teardownIntents() {
        release();
    }

    @Test
    @DisplayName("25. При клике на ссылку Privacy Policy открывается документ.")
    @Description("Открытие ссылки Privacy Policy на странице About.")
    public void testOpenPrivacyPolicy() {
        goToAboutPage();
        clickPrivacyPolicyLink();
        checkIntent();
    }

    @Test
    @DisplayName("26. При клике на ссылку Terms of use открывается документ.")
    @Description("Открытие ссылки Terms of use на странице About.")
    public void testOpenTermsOfUse() {
        goToAboutPage();
        clickTermsOfUseLink();
        checkIntent();
    }
}
