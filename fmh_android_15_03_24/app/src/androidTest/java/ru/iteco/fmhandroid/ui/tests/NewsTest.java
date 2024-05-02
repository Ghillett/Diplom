package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageObjects.NewsPage;
import ru.iteco.fmhandroid.ui.utils.ScreenshotWatcher;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest extends NewsPage {

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

    ArrayList<String> newsList = new ArrayList<>();

    @After
    public void clearNewsAfterTest() {
        deleteCreatedNews(newsList);
    }

    @Test
    @DisplayName("22. Создание новости с заполнением всех полей корректными данными.")
    @Description("Создание новости и проверка того, что она отображается в панели администратора.")
    public void testNewsCreation() {
        String newsTitle = createNews(true);
        checkIfNewsExists(newsTitle);

        newsList.add(newsTitle);
    }

    @Test
    @DisplayName("23. Созданная новость появляется в списке опубликованных.")
    @Description("Создание новости и проверка того, что она отображается как опубликованная с списке новостей.")
    public void testNewsPublication() {
        String newsTitle = createNews(true);
        checkIfNewsPublished(newsTitle);

        newsList.add(newsTitle);
    }

    @Test
    @DisplayName("10. Сортировка от старых новостей к новым и наоборот.")
    @Description("Сортировка новостей на странице новостей.")
    public void testNewsListSorting() {
        String newsTitle = createNews(true);
        goToNewsPage();
        checkSorting(newsTitle);

        newsList.add(newsTitle);
    }

    @Test
    @DisplayName("11. Фильтрация с полями заполненными корректными данными.")
    @Description("Фильтрация новости на странице новостей.")
    public void testNewsListFiltering() {
        String newsTitle = createNews(true);
        goToNewsPage();
        applyFilter();
        checkFiltering(newsTitle);

        newsList.add(newsTitle);
    }

    @Test
    @DisplayName("12. Отображаются все активные новости.")
    @Description("В списке новостей отображаются опубликованные активные новости, и не отображаются неактивные.")
    public void testOnlyActiveNewsDisplayedInList() {
        String hiddenNewsTitle = createNews(false);
        String activeNewsTitle = createNews(true);
        goToNewsPage();
        checkIfNewsExists(activeNewsTitle);
        checkIfNewsDoesNotExist(hiddenNewsTitle);

        newsList.add(activeNewsTitle);
        newsList.add(hiddenNewsTitle);
    }

    @Test
    @DisplayName("13. Отображаются все созданные новости.")
    @Description("В панели администратора отображаются и активные, и неактивные новости.")
    public void testAllNewsDisplayedInAdminPanel() {
        String activeNewsTitle = createNews(true);
        String hiddenNewsTitle = createNews(false);
        checkIfNewsExists(activeNewsTitle);
        checkIfNewsExists(hiddenNewsTitle);

        newsList.add(activeNewsTitle);
        newsList.add(hiddenNewsTitle);
    }

    @Test
    @DisplayName("14. При нажатии OK в диалоговом окне удаления новость удаляется.")
    @Description("Новость перестает отображаться в списке новостей после удаления.")
    public void testNewsDeletion() {
        String newsTitle = createNews(true);
        deleteNews(newsTitle);
        checkIfNewsDoesNotExist(newsTitle);

        newsList.add(newsTitle);
    }

    @Test
    @DisplayName("15. При изменении категории изменения отражаются в новости.")
    @Description("После изменения категории, изменения сохраняются в информации о новости.")
    public void testCategoryChange() {
        String newsTitle = createNews(true);
        changeNewsCategory(newsTitle);
        checkNewsCategory(newsTitle, true);

        newsList.add(newsTitle);
    }

    @Test
    @DisplayName("16. При изменении названия новости изменения отражаются в новости.")
    @Description("После изменения названия новости, изменения можно увидеть в панели администратора.")
    public void testTitleChange() {
        String newsTitle = createNews(true);
        String changedTitle = changeNewsTitle(newsTitle);
        checkIfNewsDoesNotExist(newsTitle);
        checkIfNewsExists(changedTitle);

        newsList.add(changedTitle);
    }

    @Test
    @DisplayName("17. При изменении даты публикации оно меняется в информации о новости.")
    @Description("После изменения даты публикации, изменения можно увидеть на карточке новости в панели администратора.")
    public void testDateChange() {
        String newsTitle = createNews(true);
        String changedDate = changeNewsPublicationDate(newsTitle);
        checkNewsPublicationDate(newsTitle, changedDate);

        newsList.add(newsTitle);
    }

    @Test
    @DisplayName("18. Изменение времени публикации сохраняется в информации о новости.")
    @Description("Изменение времени публикации можно увидеть в информации о новости.")
    public void testTimeChange() {
        String newsTitle = createNews(true);
        String changedTime = changeNewsPublicationTime(newsTitle);
        checkNewsPublicationTime(newsTitle, changedTime);

        newsList.add(newsTitle);
    }

    @Test
    @DisplayName("19. Изменение статуса новости отображается в информации о новости.")
    @Description("Изменение статуса новости можно увидеть на карточке новости в панели администратора.")
    public void testStatusChange() {
        String newsTitle = createNews(true);
        changeNewsStatus(newsTitle);
        checkNewsStatus(newsTitle, false);

        newsList.add(newsTitle);
    }

    @Test
    @DisplayName("20. Новости сортируются в порядке от новых к старым и наоборот.")
    @Description("Сортировка новостей в панели администратора.")
    public void testNewsAdminPanelSorting() {
        String newsTitle = createNews(true);
        checkSorting(newsTitle);

        newsList.add(newsTitle);
    }

    @Test
    @DisplayName("21. Новости можно отфильтровать с заполнением всех полей.")
    @Description("Фильтрация новостей в панели администратора.")
    public void testNewsAdminPanelFiltering() {
        String newsTitle = createNews(true);
        applyFilter();
        checkFiltering(newsTitle);

        newsList.add(newsTitle);
    }
}
