package crittercism.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.crittercism.app.CrittercismConfig;
import spacemadness.com.lunarconsole.BuildConfig;

public final class at {
    public String a;
    public int b;

    public at(Context context, CrittercismConfig crittercismConfig) {
        this.a = BuildConfig.VERSION_NAME;
        this.b = 0;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.a = packageInfo.versionName;
            this.b = packageInfo.versionCode;
        } catch (NameNotFoundException e) {
        }
        String customVersionName = crittercismConfig.getCustomVersionName();
        if (customVersionName != null && customVersionName.length() > 0) {
            this.a = customVersionName;
        }
        if (crittercismConfig.isVersionCodeToBeIncludedInVersionString()) {
            this.a += "-" + Integer.toString(this.b);
        }
    }
}
