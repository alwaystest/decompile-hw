package com.amap.api.services.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.security.MessageDigest;
import java.util.Locale;

/* compiled from: AppInfo */
public class w {
    private static String a = "";
    private static String b = null;
    private static String c = "";
    private static String d;
    private static String e = null;

    public static String a(Context context) {
        try {
            if (!"".equals(a)) {
                return a;
            }
            PackageManager packageManager = context.getPackageManager();
            a = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
            return a;
        } catch (Throwable e) {
            ay.a(e, "AppInfo", "getApplicationName");
            e.printStackTrace();
        } catch (Throwable e2) {
            ay.a(e2, "AppInfo", "getApplicationName");
            e2.printStackTrace();
        }
    }

    public static String b(Context context) {
        try {
            if (b != null) {
                if (!"".equals(b)) {
                    return b;
                }
            }
            b = context.getApplicationContext().getPackageName();
        } catch (Throwable th) {
            ay.a(th, "AppInfo", "getPackageName");
            th.printStackTrace();
        }
        return b;
    }

    public static String c(Context context) {
        try {
            if (!"".equals(c)) {
                return c;
            }
            c = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return c;
        } catch (Throwable e) {
            ay.a(e, "AppInfo", "getApplicationVersion");
            e.printStackTrace();
        } catch (Throwable e2) {
            ay.a(e2, "AppInfo", "getApplicationVersion");
            e2.printStackTrace();
        }
    }

    public static String d(Context context) {
        try {
            if (e != null && !"".equals(e)) {
                return e;
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            byte[] digest = MessageDigest.getInstance("SHA1").digest(packageInfo.signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String toUpperCase = Integer.toHexString(b & 255).toUpperCase(Locale.US);
                if (toUpperCase.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(toUpperCase);
                stringBuffer.append(":");
            }
            stringBuffer.append(packageInfo.packageName);
            e = stringBuffer.toString();
            return e;
        } catch (Throwable e) {
            ay.a(e, "AppInfo", "getSHA1AndPackage");
            e.printStackTrace();
            return e;
        } catch (Throwable e2) {
            ay.a(e2, "AppInfo", "getSHA1AndPackage");
            e2.printStackTrace();
            return e;
        } catch (Throwable e22) {
            ay.a(e22, "AppInfo", "getSHA1AndPackage");
            e22.printStackTrace();
            return e;
        }
    }

    static void a(String str) {
        d = str;
    }

    private static String g(Context context) throws NameNotFoundException {
        if (d == null || d.equals("")) {
            d = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.amap.api.v2.apikey");
        }
        return d;
    }

    public static String e(Context context) {
        try {
            return g(context);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return d;
        } catch (Throwable th) {
            th.printStackTrace();
            return d;
        }
    }

    public static String f(Context context) {
        try {
            return g(context);
        } catch (Throwable e) {
            ay.a(e, "AppInfo", "getKey");
            e.printStackTrace();
            return d;
        } catch (Throwable e2) {
            ay.a(e2, "AppInfo", "getKey");
            e2.printStackTrace();
            return d;
        }
    }
}
