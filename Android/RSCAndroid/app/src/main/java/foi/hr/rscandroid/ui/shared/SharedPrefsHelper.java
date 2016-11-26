package foi.hr.rscandroid.ui.shared;


import android.preference.PreferenceManager;

import foi.hr.rscandroid.RSCApplication;

public class SharedPrefsHelper {

    public static String getSharedPrefsString(String key) {
        return PreferenceManager.getDefaultSharedPreferences(RSCApplication.getInstance()).getString(key, "");
    }

    public static void setSharedPrefsString(String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(RSCApplication.getInstance()).edit().putString(key, value).apply();
    }

    public static void setSharedPrefsBoolean(String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(RSCApplication.getInstance()).edit().putBoolean(key, value).apply();
    }

    public static boolean getSharedPrefsBoolean(String key) {
        return PreferenceManager.getDefaultSharedPreferences(RSCApplication.getInstance()).getBoolean(key, false);
    }

    public static void clearEntireSharedPrefs() {
        PreferenceManager.getDefaultSharedPreferences(RSCApplication.getInstance()).edit().clear().apply();
    }

    public static void clearSingleSharedPrefsItem(String key) {
        PreferenceManager.getDefaultSharedPreferences(RSCApplication.getInstance()).edit().remove(key).apply();
    }

    public static void setSharedPrefsLong(String key, long value) {
        PreferenceManager.getDefaultSharedPreferences(RSCApplication.getInstance()).edit().putLong(key, value).apply();
    }

    public static long getSharedPrefsLong(String key) {
        return PreferenceManager.getDefaultSharedPreferences(RSCApplication.getInstance()).getLong(key, 0);
    }
}
