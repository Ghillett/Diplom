package ru.iteco.fmhandroid.ui.pageObjects;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.AllureStepsHelper;
import ru.iteco.fmhandroid.ui.helpers.TestData;

public class AuthorizationPage extends AllureStepsHelper {
    private final int mainMenu = R.id.container_list_news_include_on_fragment_main;

    public void addCorrectLogin(){
        inputText("Login", TestData.login);
    }
    public void addCorrectPassword() {
        inputText("Password", TestData.password);
    }

    public void addIncorrectLogin() {
        inputText("Login", TestData.incorrectLogin);
    }

    public void addIncorrectPassword() {
        inputText("Password", TestData.incorrectPassword);
    }

    public void signIn() {
        clickSignInButton();
    }

    public void checkLogin() {
        checkElementDisplayed(mainMenu, true);
    }

    public void checkEmptyFieldMessage() {
        checkToastMessage(R.string.empty_login_or_password, TestData.EmptyFieldMessage);
    }

    public void checkIncorrectCredsMessage() {
        checkToastMessage(R.string.empty_login_or_password, TestData.IncorrectCredsMessage);
    }
}
