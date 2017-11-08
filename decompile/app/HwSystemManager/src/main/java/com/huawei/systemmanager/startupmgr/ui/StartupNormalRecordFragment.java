package com.huawei.systemmanager.startupmgr.ui;

import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.systemmanager.R;
import com.huawei.systemmanager.comm.component.BaseListFragment;
import com.huawei.systemmanager.comm.component.ContentLoader;
import com.huawei.systemmanager.comm.widget.CommonAdapter;
import com.huawei.systemmanager.comm.widget.ViewUtil;
import com.huawei.systemmanager.startupmgr.comm.AbsRecordInfo.Cmp;
import com.huawei.systemmanager.startupmgr.comm.NormalRecordInfo;
import com.huawei.systemmanager.startupmgr.db.StartupDataMgrHelper;
import com.huawei.systemmanager.util.HwLog;
import com.huawei.systemmanager.util.app.HsmPackageManager;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StartupNormalRecordFragment extends BaseListFragment<NormalRecordInfo> {
    private static final int DATA_LOADER_ID = 1;
    public static final String TAG = "NormalRecordFragment";
    private LayoutInflater mLayoutInflater;

    private static class NormalRecordAdapter extends CommonAdapter<NormalRecordInfo> {
        NormalRecordAdapter(Context ctx, LayoutInflater inflater) {
            super(ctx, inflater);
        }

        protected View newView(int position, ViewGroup parent, NormalRecordInfo item) {
            View innerView = getInflater().inflate(R.layout.common_list_item_twolines_image, null);
            CommonRecordViewHolder holder = new CommonRecordViewHolder();
            holder.icon = (ImageView) innerView.findViewById(R.id.image);
            holder.title = (TextView) innerView.findViewById(ViewUtil.HWID_TEXT_1);
            holder.description = (TextView) innerView.findViewById(ViewUtil.HWID_TEXT_2);
            holder.description.setSingleLine(false);
            innerView.setTag(holder);
            return innerView;
        }

        protected void bindView(int position, View view, NormalRecordInfo item) {
            Context ctx = getContext();
            CommonRecordViewHolder holder = (CommonRecordViewHolder) view.getTag();
            holder.icon.setImageDrawable(item.getAppIcon());
            holder.title.setText(item.getTitleString(ctx));
            holder.description.setText(item.getDescriptionString(ctx));
        }

        public boolean swapData(List<? extends NormalRecordInfo> list) {
            HwLog.i(StartupNormalRecordFragment.TAG, "swap data, list size:" + (list == null ? 0 : list.size()));
            return super.swapData(list);
        }
    }

    private static class NormalRecordLoader extends ContentLoader<List<NormalRecordInfo>> {
        public NormalRecordLoader(Context ctx) {
            super(ctx);
        }

        public List<NormalRecordInfo> loadInBackground() {
            List<NormalRecordInfo> result = StartupDataMgrHelper.queryNormalRecordInfoList(getContext());
            Iterator<NormalRecordInfo> iterator = result.iterator();
            while (iterator.hasNext()) {
                NormalRecordInfo tmp = (NormalRecordInfo) iterator.next();
                if (HsmPackageManager.getInstance().packageExists(tmp.getPackageName(), 0)) {
                    tmp.loadExtUIMembers(getContext());
                    if (!tmp.uiMemberValid()) {
                        iterator.remove();
                    }
                } else {
                    iterator.remove();
                }
            }
            Collections.sort(result, new Cmp());
            return result;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.startupmgr_record_list, container, false);
        this.mLayoutInflater = inflater;
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setEmptyTextAndImage(R.string.startupmgr_no_records, R.drawable.ic_no_apps);
        setListAdapter(new NormalRecordAdapter(getApplicationContext(), this.mLayoutInflater));
        initLoader(1);
    }

    public Loader<List<NormalRecordInfo>> onCreateLoader(int id, Bundle args) {
        return new NormalRecordLoader(getApplicationContext());
    }

    public void onResume() {
        super.onResume();
        loadNormalRecordData();
    }

    protected void onListItemClick(AdapterView<?> adapterView, View v, int position, long id) {
    }

    public void resetListPosition() {
        HwLog.i(TAG, "resetListPosition");
        AdapterView list = getListView();
        if (list != null) {
            list.setSelection(0);
        }
    }

    private void loadNormalRecordData() {
        notifyLoader(1);
    }
}
