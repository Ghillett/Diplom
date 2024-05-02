package ru.iteco.fmhandroid.ui.pageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.ViewInteraction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Consumer;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.helpers.TestData;
import ru.iteco.fmhandroid.ui.utils.AllureStepsHelper;

public class NewsPage extends AllureStepsHelper {

    public String createNews(boolean isActive) {
        String newsID = generateNewsID();

        goToNewsCreationPanel();
        inputText(R.id.news_item_category_text_auto_complete_text_view, TestData.baseCategory);
        addTitle(newsID);
        addCurrentDate();
        addDescription();

        if (!isActive) {
            addLatestTime();
            clickSaveButton();
            waitForElementLoading(R.id.edit_news_item_image_view, 3000);
            editNews(newsID);
            clickButton(onView(allOf(withId(R.id.switcher), withText("Active"))));
        }

        addCurrentTime();
        clickSaveButton();

        waitForElementLoading(R.id.main_menu_image_button, 3000);

        return newsID;
    }

    public void changeNewsStatus(String newsTitle) {
        goToNewsAdminPanel();
        editNews(newsTitle);
        clickButton(R.id.switcher);
        clickSaveButton();

        waitForElementLoading(R.id.main_menu_image_button, 3000);
    }

    public String generateNewsID() {
        int id = (int)(Math.random() * 100_000);
        return "test" + id;
    }

    public void addTitle(String id) {
        inputText(R.id.news_item_title_text_input_edit_text, id);
    }

    public void addCurrentDate() {
        clickButton(R.id.news_item_publish_date_text_input_edit_text);
        clickOkButton();
    }

    public void addCurrentTime() {
        clickButton(R.id.news_item_publish_time_text_input_edit_text);
        clickOkButton();
    }

    public void addLatestTime() {
        clickButton(R.id.news_item_publish_time_text_input_edit_text);
        ViewInteraction switchViewButton = onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Switch to text input mode for the time input.")));
        switchViewButton.perform(click());
        ViewInteraction hoursInput = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.RelativeLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.TextInputTimePickerView")),
                                                1)),
                                0)));
        inputText(hoursInput, "23");
        ViewInteraction minutesInput = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.RelativeLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.TextInputTimePickerView")),
                                                1)),
                                3)));
        inputText(minutesInput, "59");
        clickOkButton();
    }

    public void addDescription() {
        inputText(R.id.news_item_description_text_input_edit_text, TestData.baseDescription);
    }

    public void applyFilter() {
        clickButton(R.id.filter_news_material_button);
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(replaceText(TestData.baseCategory));
        clickButton(R.id.news_item_publish_date_start_text_input_edit_text);
        clickOkButton();
        clickButton(R.id.news_item_publish_date_end_text_input_edit_text);
        clickOkButton();
        clickButton(R.id.filter_button);
    }

    public String getNextDay() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate nextDay = currentDate.plusDays(1);
        String finalDate = nextDay.format(formatter);
        return finalDate;
    }

    public String getNextHour() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime nextHour = currentTime.plusHours(1);
        String finalTime = nextHour.getHour() + ":" + nextHour.getMinute();
        return finalTime;
    }

    public ViewInteraction findNewsCardElByTitle(String newsTitle, int elementID) {
        ViewInteraction element = onView(allOf(withId(elementID), withParent(withParent(withChild(withChild(withText(newsTitle)))))));
        return element;
    };

    public ViewInteraction findNewsCardElByTitle(String newsTitle, String elementText) {
        ViewInteraction element = onView(allOf(withText(elementText), withParent(withParent(withChild(withChild(withText(newsTitle)))))));
        return element;
    }

    public void editNews(String newsTitle) {
        ViewInteraction editButton = findNewsCardElByTitle(newsTitle, R.id.edit_news_item_image_view);
        editButton.perform(click());
    }

    public String changeNewsPublicationDate(String newsTitle) {
        String changedDate = getNextDay();
        editNews(newsTitle);
        inputText(R.id.news_item_publish_date_text_input_edit_text, changedDate);
        clickButton(R.id.save_button);

        waitForElementLoading(R.id.main_menu_image_button, 3000);

        return changedDate;
    }

    public String changeNewsPublicationTime(String newsTitle) {
        String nextHour = getNextHour();
        editNews(newsTitle);
        inputText(R.id.news_item_publish_time_text_input_edit_text, nextHour);
        clickSaveButton();

        waitForElementLoading(R.id.main_menu_image_button, 3000);

        return nextHour;
    }

    public String changeNewsTitle(String newsTitle) {
        editNews(newsTitle);
        String newTitle = generateNewsID();
        addTitle(newTitle);
        clickSaveButton();

        waitForElementLoading(R.id.main_menu_image_button, 3000);

        return newTitle;
    }

    public void changeNewsCategory(String newsTitle) {
        editNews(newsTitle);
        ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
        inputText(categoryField, TestData.secondaryCategory);
        clickSaveButton();

        waitForElementLoading(R.id.main_menu_image_button, 3000);
    }

    public void deleteNews(String newsTitle) {
        ViewInteraction deleteButton = findNewsCardElByTitle(newsTitle, R.id.delete_news_item_image_view);
        deleteButton.perform(click());
        clickOkButton();

        goToNewsAdminPanel();
    }

    public void checkFiltering(String newsTitle) {
        onView(withText(newsTitle)).check(matches(isDisplayed()));
    }

    public void checkNewsStatus(String newsTitle, boolean shouldBeActive) {
        if (shouldBeActive) {
            ViewInteraction status = findNewsCardElByTitle(newsTitle, TestData.activeNewsStatus);
            status.check(matches(isDisplayed()));
        } else {
            ViewInteraction status = findNewsCardElByTitle(newsTitle, TestData.inactiveNewsStatus);
            status.check(matches(isDisplayed()));
        }
    }

    public void checkNewsCategory(String newsTitle, boolean isCategoryChanged) {
        editNews(newsTitle);
        ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
        if (isCategoryChanged) {
            categoryField.check(matches(withText(TestData.secondaryCategory)));
        } else {
            categoryField.check(matches(withText(TestData.baseCategory)));
        }
    }

    public void checkNewsPublicationDate(String newsTitle, String expectedDate) {
        ViewInteraction publicationDate = findNewsCardElByTitle(newsTitle, R.id.news_item_publication_date_text_view);
        publicationDate.check(matches(withText(expectedDate)));
    }

    public void checkNewsPublicationTime(String newsTitle, String expectedTime) {
        editNews(newsTitle);
        ViewInteraction publishTime = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
        publishTime.check(matches(withText(expectedTime)));
    }

    public void checkSorting(String newsTitle) {
        ViewInteraction firstElementNewToOld = onView(allOf(withText(newsTitle), withParent(withParent(childAtPosition(
                withId(R.id.news_list_recycler_view), 0)))));
        firstElementNewToOld.check(matches(isDisplayed()));

        clickButton(R.id.sort_news_material_button);

        onView(allOf(withText(newsTitle), withParent(withParent(childAtPosition(
                withId(R.id.news_list_recycler_view), 0))))).check(doesNotExist());
    }

    public void checkIfNewsExists(String newsID) {
        ViewInteraction title = onView(allOf(withId(R.id.news_item_title_text_view), withText(newsID)));
        title.check(matches(isDisplayed()));
    }

    public void checkIfNewsDoesNotExist(String newsID) {
        ViewInteraction title = onView(allOf(withId(R.id.news_item_title_text_view), withText(newsID)));
        title.check(doesNotExist());
    }

    public void checkIfNewsPublished(String newsID) {
        clickButton(R.id.main_menu_image_button);
        clickButton("News");
        ViewInteraction fragment = onView(allOf(withId(R.id.container_custom_app_bar_include_on_fragment_news_list), isDisplayed()));
        ViewInteraction title = onView(allOf(withId(R.id.news_item_title_text_view), withText(newsID)));
        title.check(matches(isDisplayed()));
    }

    public void deleteCreatedNews(ArrayList<String> newsList) {
        try {
            waitForElementLoading(R.id.save_button, 1000);
            clickSaveButton();
        } catch(Exception e) {}
        goToNewsAdminPanel();
        waitForElementLoading(R.id.delete_news_item_image_view, 3000);
        newsList.forEach(new Consumer<String>() {
            @Override
            public void accept(String newsTitle) {
                try {
                    deleteNews(newsTitle);
                } catch (Exception e) {}
            }
        });
        newsList.clear();
    }
}
