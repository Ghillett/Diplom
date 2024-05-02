package ru.iteco.fmhandroid.ui.pageObjects;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.AllureStepsHelper;

public class AboutPage extends AllureStepsHelper {
    public void clickPrivacyPolicyLink() {
        clickButton(R.id.about_privacy_policy_value_text_view);
    }

    public void clickTermsOfUseLink() {
        clickButton(R.id.about_terms_of_use_value_text_view);
    }
}
