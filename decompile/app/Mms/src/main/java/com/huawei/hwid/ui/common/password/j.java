package com.huawei.hwid.ui.common.password;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.hwid.core.c.b.a;
import com.huawei.hwid.core.c.m;
import com.huawei.hwid.core.helper.handler.ErrorStatus;
import com.huawei.hwid.ui.common.c;
import com.huawei.hwid.ui.common.login.RegisterResetVerifyEmailActivity;

/* compiled from: FindpwdTypeActivity */
class j extends c {
    final /* synthetic */ FindpwdTypeActivity b;
    private String d;

    public j(FindpwdTypeActivity findpwdTypeActivity, Context context, String str) {
        this.b = findpwdTypeActivity;
        super(findpwdTypeActivity, context);
        this.d = str;
    }

    public void onSuccess(Bundle bundle) {
        super.onSuccess(bundle);
        Intent intent = new Intent(this.b, RegisterResetVerifyEmailActivity.class);
        intent.putExtra("isFromRegister", false);
        intent.putExtra("accountName", this.b.f);
        intent.putExtra("emailName", this.d);
        intent.putExtra("siteId", this.b.g);
        this.b.startActivityForResult(intent, 3);
    }

    public void onFail(Bundle bundle) {
        if (bundle.getBoolean("isRequestSuccess", false)) {
            ErrorStatus errorStatus = (ErrorStatus) bundle.getParcelable("requestError");
            if (errorStatus != null) {
                int i;
                a.b("FindpwdTypeActivity", "sendEmailProcess ==> HttpStatusCode =" + errorStatus.getErrorCode());
                int a = m.a(this.a, "CS_notification");
                int i2;
                if (errorStatus.getErrorCode() == 70001104) {
                    i2 = a;
                    a = m.a(this.a, "CS_verification_email_overload_24h");
                    i = i2;
                } else if (70001102 != errorStatus.getErrorCode()) {
                    a = m.a(this.a, "CS_security_email_error");
                    i = m.a(this.a, "CS_prompt_dialog_title");
                } else {
                    i2 = a;
                    a = m.a(this.a, "CS_verification_email_overload_1h");
                    i = i2;
                }
                Dialog create = com.huawei.hwid.ui.common.j.a(this.a, a, i).create();
                this.b.a(create);
                create.show();
            }
        }
        super.onFail(bundle);
    }
}
