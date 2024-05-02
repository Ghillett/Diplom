package ru.iteco.fmhandroid.ui.pageObjects;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.AllureStepsHelper;

public class MainPage extends AllureStepsHelper {

    public void checkIfNewsPageOpen(){
        checkElementDisplayed(R.id.news_list_swipe_refresh, true);
    }

    public void checkIfMainPageOpen() {
        checkElementDisplayed(R.id.container_custom_app_bar_include_on_fragment_main, true);
    }

    public void checkIfAboutPageOpen() {
        checkElementDisplayed(R.id.about_version_title_text_view, true);
    }
}
