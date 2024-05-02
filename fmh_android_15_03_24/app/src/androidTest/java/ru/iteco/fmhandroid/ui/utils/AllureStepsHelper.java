package ru.iteco.fmhandroid.ui.utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AllureStepsHelper extends BaseTest {

    final int delay = 5000;
    public void inputText(String placeholderText, String text) {
        Allure.step("Ввод текста " + text + " в элемент найденный по тексту: " + placeholderText);
        ViewInteraction element = onView(withHint(placeholderText));
        element.perform(typeText(text), closeSoftKeyboard());
    }

    public void inputText(int elementID, String text) {
        Allure.step("Ввод текста " + text + " в элемент найденный по id: " + elementID);
        ViewInteraction element = onView(withId(elementID));
        element.perform(replaceText(text), closeSoftKeyboard());
    }

    public void inputText(ViewInteraction element, String text) {
        Allure.step("Ввод текста " + text + " в элемент найденный по view: " + element);
        element.perform(replaceText(text), closeSoftKeyboard());
    }

    public void clickButton(int elementID) {
        Allure.step("Клик по кнопке с id: " + elementID);
        ViewInteraction element = onView(withId(elementID));
        element.perform(click());
    }

    public void clickButton(String elementText) {
        Allure.step("Клик по кнопке с текстом: " + elementText);
        ViewInteraction element = onView(withText(elementText));
        element.perform(click());
    }

    public void clickButton(ViewInteraction view) {
        Allure.step("Клик по кнопке: " + view);
        view.perform(click());
    }

    public void clickSignInButton() {
        Allure.step("Клик по кнопке Sign In");
        onView(withId(R.id.enter_button)).perform(click());
    }

    public void clickSaveButton() {
        Allure.step("Клик по кнопке Save");
        onView(withId(R.id.save_button)).perform(click());
    }

    public void clickOkButton() {
        Allure.step("Клик по кнопке OK");
        onView(withId(android.R.id.button1)).perform(click());
    }

    public void checkElementDisplayed(int elementID, boolean isDelayed) {
        if (isDelayed) {
            Allure.step("Проверка отображения элемента с id: " + elementID + " c ожиданием " + delay + " миллисекунд");
            waitForElementLoading(elementID, delay);
        } else {
            Allure.step("Проверка отображения элемента с id: " + elementID + " без задержки");
        }
        ViewInteraction element = onView(withId(elementID));
        element.check(matches(isDisplayed()));
    }

    public void checkElementHasText(int elementID, String text) {
        Allure.step("Проверка наличия текста " + text + " в элементе с id: " + elementID);
        onView(withId(elementID)).check(matches(withText(text)));
    }

    public void checkDisplayedText(int elementID, String text) {
        Allure.step("Проверка соответствия текста элемента с id: " + elementID + " тексту " + text);
        ViewInteraction element = onView(withId(elementID));
        element.check(matches(isDisplayed()));
        element.check(matches(withText(text)));
    }

    public void checkToastMessage(int resourceString, String text) {
        Allure.step("Проверка отображения элемента: " + resourceString + "с текстом: " + text);
        onView(withText(resourceString)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        onView(withText(resourceString)).inRoot(new ToastMatcher())
                .check(matches(withText(text)));
    }

    public void checkIntent() {
        Allure.step("Проверка соответствия интента типу ACTION_VIEW");
        intended(Matchers.allOf(hasAction(Intent.ACTION_VIEW)));
    }

    public void goToMainPage() {
        Allure.step("Переход на страницу Main");
        waitForElementLoading(R.id.main_menu_image_button, 2000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("Main")).perform(click());
    }

    public void goToNewsPage() {
        Allure.step("Переход на страницу News");
        waitForElementLoading(R.id.main_menu_image_button, 2000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("News")).perform(click());
    }

    public void goToNewsAdminPanel() {
        Allure.step("Переход в панель администрирования новостей");
        waitForElementLoading(R.id.main_menu_image_button, 2000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("News")).perform(click());
        onView(withId(R.id.edit_news_material_button)).perform(click());
    }

    public void goToNewsCreationPanel() {
        Allure.step("Переход в меню создания новости");
        waitForElementLoading(R.id.main_menu_image_button, 2000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("News")).perform(click());
        onView(withId(R.id.edit_news_material_button)).perform(click());
        onView(withId(R.id.add_news_image_view)).perform(click());
    }

    public void goToAboutPage() {
        Allure.step("Переход на страницу About");
        waitForElementLoading(R.id.main_menu_image_button, 2000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("About")).perform(click());
    }

    public void goToQuotationPage() {
        Allure.step("Переход на страницу Цитат");
        onView(withId(R.id.our_mission_image_button)).perform(click());
    }
    
}
