package com.android.contacts.hap.list;

import android.content.Context;
import com.android.contacts.util.HwCustContactFeatureUtils;
import com.huawei.android.provider.SettingsEx.Systemex;
import java.util.List;

public class HwCustContactDataMultiselectAdapterImpl extends HwCustContactDataMultiselectAdapter {
    protected static final String TAG = "HwCustContactDataMultiselectAdapterImpl";

    public HwCustContactDataMultiselectAdapterImpl(Context context) {
        super(context);
    }

    public boolean getEnableEmailContactInMms() {
        if ("true".equals(Systemex.getString(this.mContext.getContentResolver(), "enable_email_contact_in_mms"))) {
            return HwCustContactFeatureUtils.isBindOnlyNumberSwitch(this.mContext);
        }
        return false;
    }

    public void setSelectionQueryArgs(StringBuilder selectionBuilder, List<String> selectionArgs) {
        selectionBuilder.append("mimetype IN (?,?)");
        selectionArgs.add("vnd.android.cursor.item/phone_v2");
        selectionArgs.add("vnd.android.cursor.item/email_v2");
    }
}
