package pt.ipbeja.catlogoeletrnico;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_DATE = "DATE";
    private static final String KEY_EMAIL = "EMAIL";
    private static final String KEY_PHONE = "PHONE";
    private static final String KEY_USERNAME= "USERNAME";
    private static final String KEY_PASSWORD = "PASSWORD";
    private static final String KEY_IMAGE = "IMAGE";
    private static final String KEY_DARKTHEME = "isDarkModeOn";

    private static SharedPreferences sharedpreferences;
    private static SharedPreferences sharedpreferencesTheme;

    private static SharedPreferences getInstance(Context context) {
        if (sharedpreferences == null) {
            sharedpreferences = context.getApplicationContext().getSharedPreferences("Preferences", 0);
        }
        return sharedpreferences;
    }

    private static SharedPreferences getInstanceTheme(Context context) {
        if (sharedpreferencesTheme == null) {
            sharedpreferencesTheme = context.getApplicationContext().getSharedPreferences("Theme", 0);
        }
        return sharedpreferencesTheme;
    }

    public static User getActiveSession(Context context) {
        if (getInstance(context).contains(KEY_EMAIL) && getInstance(context).contains(KEY_PASSWORD)) {
            return new User(getInstance(context).getInt(KEY_ID, 0),
                    getInstance(context).getString(KEY_NAME, ""),
                    getInstance(context).getString(KEY_DATE, ""),
                    getInstance(context).getString(KEY_EMAIL, ""),
                    getInstance(context).getString(KEY_PHONE, ""),
                    getInstance(context).getString(KEY_USERNAME, ""),
                    getInstance(context).getString(KEY_PASSWORD, ""),
                    getInstance(context).getString(KEY_IMAGE, ""));
        }
        return null;
    }

    public static boolean getTheme(Context context) {
        if (getInstanceTheme(context).contains(KEY_DARKTHEME)) {
            return getInstanceTheme(context).getBoolean(KEY_DARKTHEME, true);
        }
        return false;
    }

    public static void saveSession(Context context, User user) {
        getInstance(context).edit().putInt(KEY_ID, user.getId()).apply();
        getInstance(context).edit().putString(KEY_NAME, user.getName()).apply();
        getInstance(context).edit().putString(KEY_DATE, user.getDate()).apply();
        getInstance(context).edit().putString(KEY_EMAIL, user.getEmail()).apply();
        getInstance(context).edit().putString(KEY_PHONE, user.getPhone()).apply();
        getInstance(context).edit().putString(KEY_USERNAME, user.getUsername()).apply();
        getInstance(context).edit().putString(KEY_PASSWORD, user.getPassword()).apply();
        getInstance(context).edit().putString(KEY_IMAGE, user.getImage()).apply();
    }

    public static void saveTheme(Context context, boolean b) {
        getInstanceTheme(context).edit().putBoolean(KEY_DARKTHEME, b).apply();
    }

    public static void clearSession(Context context) {
        getInstance(context).edit().clear().apply();
    }

    public static void clearTheme(Context context) {
        getInstanceTheme(context).edit().clear().apply();
    }
}
