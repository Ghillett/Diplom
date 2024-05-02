package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageObjects.QuotationPage;
import ru.iteco.fmhandroid.ui.utils.ScreenshotWatcher;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class QuotationTest extends QuotationPage {

    @Rule
    public RuleChain screenshotRule = RuleChain
            .outerRule(new ActivityScenarioRule<>(AppActivity.class))
            .around(new ScreenshotWatcher());

    @Before
    public void openApp() {
        if (!checkIfLoggedIn()) {
            logIn();
        }
    }

    @Test
    @DisplayName("24. Страница цитат открывается кликом на иконку.")
    @Description("При клике на иконку цитат в топбаре приложения должна открываться страница цитат.")
    public void testPageOpen() {
        goToQuotationPage();
        checkTitle();
    }
}
