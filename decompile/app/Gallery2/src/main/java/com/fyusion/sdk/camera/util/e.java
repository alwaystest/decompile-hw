package com.fyusion.sdk.camera.util;

/* compiled from: Unknown */
public enum e {
    FY_APP_STARTED("FY_APP_STARTED"),
    FY_APP_CLOSED("FY_APP_CLOSED"),
    FY_APP_FOREGROUND("FY_APP_FOREGROUND"),
    FY_APP_BACKGROUND("FY_APP_BACKGROUND"),
    FY_APP_CRASH("FY_APP_CRASH"),
    FY_FYUSE_RECORDED("FY_FYUSE_RECORDED"),
    FY_FYUSE_RECORD_NOT_ENOUGH_DATA("FY_FYUSE_RECORD_NOT_ENOUGH_DATA"),
    FY_FYUSE_PROCESSED("FY_FYUSE_PROCESSED"),
    FY_FYUSE_SHARED_FACEBOOK("FY_FYUSE_SHARED_FACEBOOK"),
    FY_FYUSE_SHARED_FACEBOOK_SUCCESS("FY_FYUSE_SHARED_FACEBOOK_SUCCESS"),
    FY_FYUSE_GRIDVIEW("FY_FYUSE_GRIDVIEW"),
    FY_FYUSE_VIEWED_IN_FEED("FY_FYUSE_VIEWED_IN_FEED"),
    FY_FYUSE_VIEWED_IN_FULLSCREEN("FY_FYUSE_VIEWED_IN_FULLSCREEN"),
    FY_TAB_SELECTED_FEED("FY_TAB_SELECTED_FEED"),
    FY_TAB_SELECTED_EXPLORE("FY_TAB_SELECTED_EXPLORE"),
    FY_TAB_SELECTED_CAMERA("FY_TAB_SELECTED_CAMERA"),
    FY_TAB_SELECTED_ACTIVITY("FY_TAB_SELECTED_ACTIVITY"),
    FY_TAB_SELECTED_PROFILE("FY_TAB_SELECTED_PROFILE"),
    FY_HASHTAG_CLICKED("FY_HASHTAG_CLICKED"),
    FY_VISUALTAG_CREATED("FY_VISUALTAG_CREATED"),
    FY_VISUALTAG_ENABLED("FY_VISUALTAG_ENABLED"),
    FY_VISUALTAG_CLICKED("FY_VISUALTAG_CLICKED"),
    FY_SIGN_UP_IN_FACEBOOK_TRY("FY_SIGN_UP_IN_FACEBOOK_TRY"),
    FY_SIGN_UP_FACEBOOK_SUCCESS("FY_SIGN_UP_FACEBOOK_SUCCESS"),
    FY_SIGN_UP_IN_FACEBOOK_FAILED_UNKNOWN("FY_SIGN_UP_IN_FACEBOOK_FAILED_UNKNOWN"),
    FY_SIGN_IN_FACEBOOK_SUCCESS("FY_SIGN_IN_FACEBOOK_SUCCESS"),
    FY_SIGN_IN_FACEBOOK_FAILED_NOT_VALIDATED("FY_SIGN_IN_FACEBOOK_FAILED_NOT_VALIDATED"),
    FY_SIGN_IN_FACEBOOK_FAILED_INVALID_TOKEN("FY_SIGN_IN_FACEBOOK_FAILED_INVALID_TOKEN"),
    FY_SIGN_IN_FACEBOOK_FAILED_WRONG_PASSWORD("FY_SIGN_IN_FACEBOOK_FAILED_WRONG_PASSWORD"),
    FY_SIGN_UP_EMAIL_INITIATED("FY_SIGN_UP_EMAIL_INITIATED"),
    FY_SIGN_IN_EMAIL_SUCCESS("FY_SIGN_IN_EMAIL_SUCCESS"),
    FY_SIGN_IN_EMAIL_FAILED_NOT_VALIDATED("FY_SIGN_IN_EMAIL_FAILED_NOT_VALIDATED"),
    FY_SIGN_IN_EMAIL_FAILED_WRONG_PASSWORD("FY_SIGN_IN_EMAIL_FAILED_WRONG_PASSWORD"),
    FY_SIGN_IN_EMAIL_TRY("FY_SIGN_IN_EMAIL_TRY"),
    FY_FULLSCREEN_INITIALIZE_ONLINE("FY_FULLSCREEN_INITIALIZE_ONLINE"),
    FY_FULLSCREEN_INITIALIZE_LOCAL("FY_FULLSCREEN_INITIALIZE_LOCAL"),
    FY_FULLSCREEN_EXIT("FY_FULLSCREEN_EXIT"),
    FY_SEARCH_BAR("FY_SEARCH_BAR"),
    FY_FEED_FETCH_ENTRIES_TRY("FY_FEED_FETCH_ENTRIES_TRY"),
    FY_FEED_FETCH_ENTRIES_ABORT_TOO_MANY_CELLS("FY_FEED_FETCH_ENTRIES_ABORT_TOO_MANY_CELLS"),
    FY_FEED_FETCH_ENTRIES_FAILED_NO_CONNECTION("FY_FEED_FETCH_ENTRIES_FAILED_NO_CONNECTION"),
    FY_FEED_FETCH_ENTRIES_SUCCESS("FY_FEED_FETCH_ENTRIES_SUCCESS"),
    FY_MESSAGING_NEW_CREATED("FY_MESSAGING_NEW_CREATED"),
    FY_MESSAGING_OPENED("FY_MESSAGING_OPENED"),
    FY_TUTORIAL_VIDEO_NAMED_("FY_TUTORIAL_VIDEO_NAMED_");
    
    private String T;

    private e(String str) {
        a(str);
    }

    public String a() {
        return this.T;
    }

    public void a(String str) {
        this.T = str;
    }
}
