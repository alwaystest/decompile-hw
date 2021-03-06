package com.huawei.harassmentinterception.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CallLog.Calls;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.android.app.ActionBarEx;
import com.huawei.harassmentinterception.blackwhitelist.DataShareManager;
import com.huawei.harassmentinterception.common.CommonObject.CallLogInfo;
import com.huawei.harassmentinterception.common.CommonObject.ParcelableBlacklistItem;
import com.huawei.harassmentinterception.db.DBAdapter;
import com.huawei.harassmentinterception.ui.IDataLoadingWidget.DataLoadingBaseActivity;
import com.huawei.harassmentinterception.util.CommonObjectHelper;
import com.huawei.systemmanager.R;
import com.huawei.systemmanager.comm.misc.GlobalContext;
import com.huawei.systemmanager.comm.misc.TimeUtil;
import com.huawei.systemmanager.comm.misc.Utility;
import com.huawei.systemmanager.comm.widget.ViewUtil;
import com.huawei.systemmanager.util.HwLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class CalllogListActivity extends DataLoadingBaseActivity {
    private static final String TAG = "CalllogListActivity";
    private MenuItem mAddBtn;
    private RelativeLayout mCallLogListLayout = null;
    private List<CallLogInfo> mDataList = new ArrayList();
    private CallLogListAdapter mDataListAdapter;
    private boolean mIsAllChecked;
    private boolean mIsNewlyLoad = false;
    private Menu mMenu;
    private View mNoDataLayout = null;
    private Set<String> mNumberSet = new HashSet();
    private TextView mOperatorView = null;
    private ProgressBar mProgressBar = null;
    private MenuItem mSelAllBtn;
    private TextView mSelectedCountView = null;
    private HashMap<String, CallLogInfo> mSelectedMap = new HashMap();
    private long mTodayStartTime = TimeUtil.getTodayStartTime();
    private ViewGroup mViewGroup = null;

    class CallLogListAdapter extends BaseAdapter {
        CallLogListAdapter() {
        }

        public int getCount() {
            return CalllogListActivity.this.mDataList.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup viewgroup) {
            if (convertView == null) {
                return CalllogListActivity.this.createListViewWithTimeAxis(position, viewgroup);
            }
            CalllogListActivity.this.fillViewHolderWithData((ViewHolder) convertView.getTag(), (CallLogInfo) CalllogListActivity.this.mDataList.get(position), position);
            return convertView;
        }
    }

    private static class ClickClass implements OnClickListener {
        private ClickClass() {
        }

        public void onClick(View view) {
            ((ViewHolder) view.getTag())._checkBox.toggle();
        }
    }

    private static class ViewHolder {
        CheckBox _checkBox;
        TextView _contactInfo;
        TextView _location;
        TextView _time;

        private ViewHolder() {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interception_calllog_list);
        this.mViewGroup = (ViewGroup) ((RelativeLayout) findViewById(R.id.rl_calllog_list)).getParent();
        initActionBar();
        this.mNoDataLayout = findViewById(R.id.no_calllog_view);
        ViewUtil.initEmptyViewMargin(GlobalContext.getContext(), this.mNoDataLayout);
        this.mCallLogListLayout = (RelativeLayout) findViewById(R.id.calllog_layout);
        this.mProgressBar = (ProgressBar) findViewById(R.id.loading_progressbar);
        ListView calllogListView = (ListView) findViewById(R.id.calllog_list_view);
        this.mDataListAdapter = new CallLogListAdapter();
        calllogListView.setAdapter(this.mDataListAdapter);
        setDataSourceUri(Calls.CONTENT_URI);
        setTitle(R.string.ActionBar_DoNotDisturb_Unselect);
    }

    private void initActionBar() {
        View titleBarView = getLayoutInflater().inflate(R.layout.custom_actionbar_selecting, this.mViewGroup, false);
        this.mOperatorView = (TextView) titleBarView.findViewById(R.id.view_title);
        this.mOperatorView.setText(getResources().getString(R.string.ActionBar_DoNotDisturb_Unselect));
        this.mSelectedCountView = (TextView) titleBarView.findViewById(R.id.view_selected_count);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        ActionBarEx.setCustomTitle(actionBar, titleBarView);
        ActionBarEx.setStartIcon(actionBar, true, null, new OnClickListener() {
            public void onClick(View arg0) {
                CalllogListActivity.this.abortLoading();
                CalllogListActivity.this.finish();
            }
        });
    }

    private void finishActivityWithResult() {
        if (this.mSelectedMap.isEmpty()) {
            finish();
            return;
        }
        ArrayList<ParcelableBlacklistItem> selectedList = new ArrayList();
        for (Entry<String, CallLogInfo> entry : this.mSelectedMap.entrySet()) {
            CallLogInfo callLog = (CallLogInfo) entry.getValue();
            ParcelableBlacklistItem item = new ParcelableBlacklistItem();
            item.setPhone(callLog.getPhone());
            item.setName(callLog.getName());
            selectedList.add(item);
        }
        Intent intent = new Intent();
        DataShareManager.getInstance().setBlacklistBuff(selectedList);
        setResult(-1, intent);
        finish();
    }

    protected void onResume() {
        if (isDataChanged()) {
            this.mProgressBar.setVisibility(0);
            loadDataWithDelay(50);
        }
        super.onResume();
    }

    private void updateSelectingStatus() {
        boolean z = true;
        if (this.mMenu != null) {
            if (this.mDataList.isEmpty()) {
                this.mAddBtn.setVisible(false);
                this.mSelAllBtn.setVisible(false);
                this.mOperatorView.setText(R.string.ActionBar_DoNotDisturb_Unselect);
                this.mSelectedCountView.setVisibility(8);
                return;
            }
            this.mAddBtn.setVisible(true);
            this.mSelAllBtn.setVisible(true);
            int number = CommonObjectHelper.getCheckedItemNum(this.mDataList);
            this.mIsAllChecked = CommonObjectHelper.isAllItemChecked(this.mDataList, number);
            if (number > 0) {
                this.mOperatorView.setText(R.string.ActionBar_DoNotDisturb_Select);
                this.mSelectedCountView.setVisibility(0);
                this.mSelectedCountView.setText("" + Utility.getLocaleNumber(number));
                setTitle(getString(R.string.ActionBar_DoNotDisturb_Select) + Utility.getLocaleNumber(number));
                this.mAddBtn.setEnabled(true);
            } else {
                setTitle(R.string.ActionBar_DoNotDisturb_Unselect);
                this.mOperatorView.setText(R.string.ActionBar_DoNotDisturb_Unselect);
                this.mSelectedCountView.setVisibility(8);
                this.mAddBtn.setEnabled(false);
            }
            if (number == 0 || !this.mIsAllChecked) {
                this.mSelAllBtn.setIcon(R.drawable.menu_check_status);
                this.mSelAllBtn.setTitle(R.string.select_all);
                this.mSelAllBtn.setChecked(false);
            } else {
                this.mSelAllBtn.setIcon(R.drawable.menu_check_pressed);
                this.mSelAllBtn.setTitle(R.string.unselect_all);
                this.mSelAllBtn.setChecked(true);
            }
            MenuItem menuItem = this.mSelAllBtn;
            if (this.mDataList.size() == 0) {
                z = false;
            }
            menuItem.setEnabled(z);
        }
    }

    private void resetData() {
        this.mDataList.clear();
        this.mNumberSet.clear();
    }

    public void onLoadingStart() {
        this.mIsNewlyLoad = true;
    }

    public void onLoadDataInBackground() {
        int nLoadedCount = 0;
        HwLog.d(TAG, "onLoadDataInBackground: Batch size = 10");
        try {
            List<CallLogInfo> callLogList = DBAdapter.getCallLogListInBatches(this, 10, 0);
            while (!isAbortLoading() && !Utility.isNullOrEmptyList(callLogList)) {
                postAppendMsg(callLogList);
                nLoadedCount += callLogList.size();
                callLogList = DBAdapter.getCallLogListInBatches(this, 10, ((CallLogInfo) callLogList.get(callLogList.size() - 1)).getDate());
            }
        } catch (Exception e) {
            HwLog.e(TAG, "onLoadDataInBackground: Exceptiong", e);
        }
        HwLog.d(TAG, "onLoadDataInBackground: nLoadedCount = " + nLoadedCount);
    }

    public void onAppendData(Object obj) {
        if (obj != null && obj.getClass() == ArrayList.class) {
            ArrayList<CallLogInfo> callLogListCarrier = (ArrayList) obj;
            if (callLogListCarrier.size() > 0) {
                if (this.mIsNewlyLoad) {
                    resetData();
                    this.mIsNewlyLoad = false;
                }
                for (CallLogInfo cLogInfo : callLogListCarrier) {
                    String matchedNumber = cLogInfo.getMatchedNumber();
                    if (!(TextUtils.isEmpty(matchedNumber) || this.mNumberSet.contains(matchedNumber))) {
                        if (this.mSelectedMap.containsKey(matchedNumber)) {
                            cLogInfo.setSelected(true);
                        }
                        this.mDataList.add(cLogInfo);
                        this.mNumberSet.add(matchedNumber);
                    }
                }
                refreshListView();
            }
        }
    }

    public void onLoadingComplete() {
        if (this.mIsNewlyLoad) {
            resetData();
            this.mIsNewlyLoad = false;
        }
        restoreSeletcedItems();
        refreshListView();
        super.onLoadingComplete();
    }

    private void restoreSeletcedItems() {
        if (this.mSelectedMap.isEmpty()) {
            this.mNumberSet.clear();
        } else if (this.mNumberSet.isEmpty()) {
            this.mSelectedMap.clear();
        } else {
            Iterator<String> iter = this.mSelectedMap.keySet().iterator();
            while (iter.hasNext()) {
                if (!this.mNumberSet.contains(iter.next())) {
                    iter.remove();
                }
            }
            this.mNumberSet.clear();
        }
    }

    private void refreshListView() {
        if (Utility.isNullOrEmptyList(this.mDataList)) {
            this.mCallLogListLayout.setVisibility(8);
            this.mNoDataLayout.setVisibility(0);
        } else {
            this.mCallLogListLayout.setVisibility(0);
            this.mNoDataLayout.setVisibility(8);
        }
        this.mProgressBar.setVisibility(8);
        this.mDataListAdapter.notifyDataSetChanged();
        updateSelectingStatus();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.interception_add_menu, menu);
        this.mMenu = menu;
        this.mAddBtn = menu.findItem(R.id.add_contacts);
        this.mSelAllBtn = menu.findItem(R.id.select_all_contacts);
        updateSelectingStatus();
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                finish();
                break;
            case R.id.add_contacts:
                abortLoading();
                finishActivityWithResult();
                break;
            case R.id.select_all_contacts:
                selectAllContacts();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectAllContacts() {
        this.mIsAllChecked = !this.mIsAllChecked;
        CommonObjectHelper.doSelect(this.mDataList, this.mIsAllChecked);
        this.mSelectedMap.clear();
        if (this.mIsAllChecked) {
            for (CallLogInfo callLogInfo : this.mDataList) {
                this.mSelectedMap.put(callLogInfo.getMatchedNumber(), callLogInfo);
            }
        }
        refreshListView();
    }

    private View createListViewWithTimeAxis(int position, ViewGroup viewgroup) {
        View interView = getLayoutInflater().inflate(R.layout.interception_calllog_list_item, viewgroup, false);
        ViewHolder holder = new ViewHolder();
        holder._contactInfo = (TextView) interView.findViewById(R.id.calllog_contactInfo);
        holder._location = (TextView) interView.findViewById(R.id.calllog_location);
        holder._checkBox = (CheckBox) interView.findViewById(R.id.calllog_checkbox);
        holder._time = (TextView) interView.findViewById(R.id.time);
        fillViewHolderWithData(holder, (CallLogInfo) this.mDataList.get(position), position);
        interView.setClickable(true);
        interView.setTag(holder);
        interView.setOnClickListener(new ClickClass());
        interView.setTag(holder);
        return interView;
    }

    private void fillViewHolderWithData(ViewHolder holder, CallLogInfo callLogInfo, final int position) {
        holder._contactInfo.setText(callLogInfo.getContactInfo(this));
        String location = callLogInfo.getGeoLocation();
        if (TextUtils.isEmpty(location)) {
            holder._location.setText("");
            holder._location.setVisibility(8);
        } else {
            holder._location.setText(location);
            holder._location.setVisibility(0);
        }
        int index = position;
        holder._checkBox.setOnCheckedChangeListener(null);
        holder._checkBox.setChecked(callLogInfo.isSelected());
        holder._checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundbutton, boolean isChecked) {
                CallLogInfo cInfo;
                if (isChecked) {
                    cInfo = (CallLogInfo) CalllogListActivity.this.mDataList.get(position);
                    cInfo.setSelected(true);
                    CalllogListActivity.this.mSelectedMap.put(cInfo.getMatchedNumber(), cInfo);
                } else {
                    cInfo = (CallLogInfo) CalllogListActivity.this.mDataList.get(position);
                    cInfo.setSelected(false);
                    CalllogListActivity.this.mSelectedMap.remove(cInfo.getMatchedNumber());
                }
                CalllogListActivity.this.updateSelectingStatus();
            }
        });
        holder._checkBox.setFocusable(false);
        String timeDes = "";
        long recordTime = callLogInfo.getDate();
        if (recordTime >= this.mTodayStartTime) {
            timeDes = DateUtils.formatDateTime(getContext(), recordTime, 1);
        } else {
            timeDes = DateUtils.formatDateTime(getContext(), recordTime, 16);
        }
        holder._time.setText(timeDes);
    }
}
