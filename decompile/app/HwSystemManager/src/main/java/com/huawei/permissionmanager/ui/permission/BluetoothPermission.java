package com.huawei.permissionmanager.ui.permission;

import android.content.Context;
import com.huawei.permissionmanager.ui.CheckPackagePermissionInterface;
import com.huawei.permissionmanager.ui.Permission;
import com.huawei.systemmanager.R;

public class BluetoothPermission extends Permission {
    public BluetoothPermission(Context context, int permName, int permDescriptions, int permissionType, int permissionPopupInfoCode, int permissionCategoryId, int permissionNoneAppTipsCode, CheckPackagePermissionInterface interfaceCheckPackagePermission) {
        super(context, permName, permDescriptions, permissionType, permissionPopupInfoCode, permissionCategoryId, permissionNoneAppTipsCode, interfaceCheckPackagePermission);
    }

    public int getHistoryStringId() {
        return R.string.permission_history_use_bluetooth;
    }

    public int getPermissionCode() {
        return 8388608;
    }

    public String getName(Context ctx) {
        return ctx.getString(R.string.Open_BT_Permission);
    }

    public boolean donotAskAgain() {
        return false;
    }
}
