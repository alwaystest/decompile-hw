package com.android.settings.fingerprint;

import android.content.Intent;
import android.content.res.Resources.Theme;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.settings.SetupWizardUtils;
import com.android.setupwizardlib.view.NavigationBar;
import com.android.setupwizardlib.view.NavigationBar.NavigationBarListener;

public class SetupFingerprintEnrollFinish extends FingerprintEnrollFinish implements NavigationBarListener {
    protected Intent getEnrollingIntent() {
        Intent intent = new Intent(this, SetupFingerprintEnrollEnrolling.class);
        intent.putExtra("hw_auth_token", this.mToken);
        if (this.mUserId != -10000) {
            intent.putExtra("android.intent.extra.USER_ID", this.mUserId);
        }
        SetupWizardUtils.copySetupExtras(getIntent(), intent);
        return intent;
    }

    protected void onApplyThemeResource(Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, SetupWizardUtils.getTheme(getIntent()), first);
    }

    protected void initViews() {
        SetupWizardUtils.setImmersiveMode(this);
        View nextButton = findViewById(2131886371);
        if (nextButton != null) {
            nextButton.setVisibility(8);
        }
        NavigationBar navigationBar = getNavigationBar();
        navigationBar.setNavigationBarListener(this);
        navigationBar.getBackButton().setVisibility(8);
        ((TextView) findViewById(2131886296)).setText(2131624670);
        ((TextView) findViewById(2131886624)).setVisibility(0);
    }

    protected Button getNextButton() {
        return getNavigationBar().getNextButton();
    }

    public void onNavigateBack() {
        onBackPressed();
    }

    public void onNavigateNext() {
        onNextButtonClick();
    }

    protected int getMetricsCategory() {
        return 248;
    }
}
