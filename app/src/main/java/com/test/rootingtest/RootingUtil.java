package com.test.rootingtest;

import android.content.pm.PackageManager;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class RootingUtil {
    public static boolean isRooted(PackageManager packageManager) {
        return checkTestKeys() || checkCommandSuperUser() || checkRootingApkAndSuFile() || checkRootingApk(packageManager);
    }

    private static boolean checkTestKeys() {
        String buildTags = android.os.Build.TAGS;
        boolean isRooted = buildTags != null && buildTags.contains("test-keys");
        Log.e("isRooted", "test-keys " + isRooted);
        return isRooted;
    }

    public static boolean checkCommandSuperUser() {
        try {
            Runtime.getRuntime().exec("su");
            Log.e("isRooted", "su Command " + true);
            return true;
        } catch (IOException localIOException) {
            Log.e("isRooted", "su Command " + false);
            return false;
        }
    }

    public static boolean checkRootingApkAndSuFile() {
        String[] paths = {
                "/system/bin/.ext/",
                "/system/xbin/su",
                "/system/xbin/mu",
                "/system/sd/xbin/su",
                "/sbin/su",
                "/system/su",
                "/system/bin/su",
                "/system/bin/.ext/.su",
                "/system/app/Superuser.apk",
                "/system/app/su.apk",
                "/system/app/Spapasu.apk"};

        for (String path : paths) {
            if (new File(path).exists()) {
                Log.e("isRooted", path + ", " + true);
                return true;
            } else {
                Log.e("isRooted", path + ", " + false);
            }

        }
        return false;
    }

    public static boolean checkRootingApk(PackageManager packageManager) {
        String[] rootingPackage = {"com.devadvance.rootcloak", "com.devadvance.rootcloakplus", "com.jrummy.root.browserfree", "com.geohot.towelroot"};

        for (String packageName : rootingPackage) {

            if (isPackageInstalled(packageManager, packageName)) {
                Log.e("isRooted", packageName + ", " + true);
                return true;
            } else {
                Log.e("isRooted", packageName + ", " + false);
            }

        }

        return false;
    }

    private static boolean isPackageInstalled(PackageManager packageManager, String packageName) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
