package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

/* compiled from: Unknown */
public class zzh implements OnClickListener {
    private final Activity mActivity;
    private final Intent mIntent;
    private final Fragment zzadi;
    private final int zzadj;

    public zzh(Activity activity, Intent intent, int i) {
        this.mActivity = activity;
        this.zzadi = null;
        this.mIntent = intent;
        this.zzadj = i;
    }

    public zzh(Fragment fragment, Intent intent, int i) {
        this.mActivity = null;
        this.zzadi = fragment;
        this.mIntent = intent;
        this.zzadj = i;
    }

    public void onClick(DialogInterface dialog, int which) {
        try {
            if (this.mIntent != null && this.zzadi != null) {
                this.zzadi.startActivityForResult(this.mIntent, this.zzadj);
            } else if (this.mIntent != null) {
                this.mActivity.startActivityForResult(this.mIntent, this.zzadj);
            }
            dialog.dismiss();
        } catch (ActivityNotFoundException e) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
