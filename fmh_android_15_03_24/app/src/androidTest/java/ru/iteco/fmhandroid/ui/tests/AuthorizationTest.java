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
import ru.iteco.fmhandroid.ui.pageObjects.AuthorizationPage;
import ru.iteco.fmhandroid.ui.utils.ScreenshotWatcher;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest extends AuthorizationPage {

    @Rule
    public RuleChain screenshotRule = RuleChain
            .outerRule(new ActivityScenarioRule<>(AppActivity.class))
            .around(new ScreenshotWatcher());

    @Before
    public void goToLoginPage() {
        if (checkIfLoggedIn()) {
            logOut();
        }
    }

    @Test
    @DisplayName("1. Авторизация с корректными данными.")
    @Description("Вход в приложение с использованием корректных логина и пароля.")
    public void testLogin() {
        addCorrectLogin();
        addCorrectPassword();
        signIn();
        checkLogin();
    }

    @Test
    @DisplayName("2. Вход с пустой формой.")
    @Description("Показ ошибки при попытке войти без заполнения полей логина и пароля.")
    public void testEmptyLoginForm() {
        signIn();
        checkEmptyFieldMessage();
    }

    @Test
    @DisplayName("3. Вход с неверным логином.")
    @Description("Показ ошибки при попытке войти с неверным логином и верным паролем.")
    public void testIncorrectLogin() {
        addIncorrectLogin();
        addCorrectPassword();
        signIn();
        checkIncorrectCredsMessage();
    }

    @Test
    @DisplayName("4. Вход с неверным паролем.")
    @Description("Показ ошибки при попытке войти с неверным паролем и верным логином.")
    public void testIncorrectPassword() {
        addCorrectLogin();
        addIncorrectPassword();
        signIn();
        checkIncorrectCredsMessage();
    }

    @Test
    @DisplayName("5. Log out при клике завершает сессию аккаунта.")
    @Description("Выход из аккаунта с помощью кнопки Log out.")
    public void testLogOut() {
        logIn();
        logOut();
    }
}

