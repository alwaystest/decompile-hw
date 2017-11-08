package com.huawei.systemmanager.spacecleanner.ui.secondaryui.uiitem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.huawei.systemmanager.R;
import com.huawei.systemmanager.comm.misc.FileUtil;
import com.huawei.systemmanager.comm.misc.GlobalContext;
import com.huawei.systemmanager.spacecleanner.engine.tencentadapter.TecentWeChatTrashFile;
import com.huawei.systemmanager.spacecleanner.engine.trash.Trash;
import com.huawei.systemmanager.spacecleanner.ui.commonitem.CommonTrashItem.TrashTransFunc;
import com.huawei.systemmanager.util.HwLog;

public class WeChatExpandListItem extends FileTrashItem<TecentWeChatTrashFile> {
    public static final TrashTransFunc<WeChatExpandListItem> sTransFunc = new TrashTransFunc<WeChatExpandListItem>() {
        public WeChatExpandListItem apply(Trash input) {
            if (input == null) {
                HwLog.e(TrashTransFunc.TAG, "ApkDataItem trans, input is null!");
                return null;
            } else if (input instanceof TecentWeChatTrashFile) {
                return new WeChatExpandListItem((TecentWeChatTrashFile) input);
            } else {
                HwLog.e(TrashTransFunc.TAG, "ApkDataItem trans, trans error, origin type:" + input.getType());
                return null;
            }
        }

        public int getTrashType() {
            return 1048576;
        }
    };

    public WeChatExpandListItem(TecentWeChatTrashFile trash) {
        super(trash);
    }

    public String getName() {
        return ((TecentWeChatTrashFile) getTrash()).getName();
    }

    public String getDescription(Context ctx) {
        return FileUtil.getFileSize(getTrashSize());
    }

    public String getPreMessage() {
        return GlobalContext.getString(R.string.wechat_audio_alert_msg);
    }

    public Drawable getIcon(Context context) {
        return context.getResources().getDrawable(R.drawable.ic_storagecleaner_cache);
    }

    public Drawable getItemIcon() {
        return GlobalContext.getContext().getResources().getDrawable(R.drawable.ic_storagecleaner_cache);
    }

    public int getIconWidth(Context ctx) {
        return ctx.getResources().getDimensionPixelSize(R.dimen.spaceclean_load_apk_icon_width);
    }

    public int getIconHeight(Context ctx) {
        return ctx.getResources().getDimensionPixelSize(R.dimen.spaceclean_load_apk_icon_height);
    }

    public boolean isUseIconAlways() {
        return true;
    }

    public int doClickAction() {
        return 6;
    }

    public boolean shouldLoadPic() {
        return false;
    }
}
