package com.huawei.gallery.photoshare.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.android.gallery3d.R;
import com.huawei.gallery.app.AbstractGalleryActivity;
import com.huawei.gallery.photoshare.utils.PhotoShareUtils;

public class PhotoShareLoginActivity extends AbstractGalleryActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gallery_activity);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        this.mContent = new PhotoShareLoginFragment();
        this.mContent.setArguments(getIntent().getExtras());
        ft.add(R.id.fragment_container, this.mContent, getClass().getSimpleName());
        ft.commit();
    }

    protected void onResume() {
        super.onResume();
        if (PhotoShareUtils.isHiCloudLogin() && PhotoShareUtils.isShareSwitchOpen()) {
            setResult(-1);
            finish();
        }
    }

    protected boolean needToRequestPermissions() {
        return false;
    }
}
