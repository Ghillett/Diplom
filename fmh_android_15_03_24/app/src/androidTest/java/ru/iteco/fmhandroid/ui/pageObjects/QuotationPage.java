package ru.iteco.fmhandroid.ui.pageObjects;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.helpers.TestData;
import ru.iteco.fmhandroid.ui.utils.AllureStepsHelper;

public class QuotationPage extends AllureStepsHelper {
    public void checkTitle() {
        checkElementHasText(R.id.our_mission_title_text_view, TestData.ourMissionText);
    }
}
