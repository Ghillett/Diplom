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
import ru.iteco.fmhandroid.ui.pageObjects.MainPage;
import ru.iteco.fmhandroid.ui.utils.ScreenshotWatcher;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainTest extends MainPage {
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
    @DisplayName("6. Переход на страницу News с Main.")
    @Description("Переход на страницу News со страницы Main через навигацию бургер-меню.")
    public void goToNewsFromMain() {
        goToNewsPage();
        checkIfNewsPageOpen();
    }

    @Test
    @DisplayName("7. Переход на страницу About с Main.")
    @Description("Переход на страницу About с Main через навигация бургер-меню.")
    public void goToAboutFromMain() {
        goToAboutPage();
        checkIfAboutPageOpen();
    }

    @Test
    @DisplayName("8. Переход на страницу Main с News.")
    @Description("Переход на страницу Main с News через навигацию бургер-меню.")
    public void goToMainFromNews() {
        goToNewsPage();
        goToMainPage();
        checkIfMainPageOpen();
    }

    @Test
    @DisplayName("9. Переход на страницу About с News.")
    @Description("Переход на страницу About с News через навигацию бургер-меню.")
    public void goToAboutFromNews() {
        goToNewsPage();
        goToAboutPage();
        checkIfAboutPageOpen();
    }
}
