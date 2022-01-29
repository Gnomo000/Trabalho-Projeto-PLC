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

    private static SharedPreferences sharedpreferences;

    private static SharedPreferences getInstance(Context context) {
        if (sharedpreferences == null) {
            sharedpreferences = context.getApplicationContext().getSharedPreferences("Preferences", 0);
        }
        return sharedpreferences;
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

    public static void saveSessionStrings(Context context, int id, String name, String date, String email, String phone, String username,String password, String image) {
        getInstance(context).edit().putInt(KEY_ID, id).apply();
        getInstance(context).edit().putString(KEY_NAME, name).apply();
        getInstance(context).edit().putString(KEY_DATE, date).apply();
        getInstance(context).edit().putString(KEY_EMAIL, email).apply();
        getInstance(context).edit().putString(KEY_PHONE, phone).apply();
        getInstance(context).edit().putString(KEY_USERNAME, username).apply();
        getInstance(context).edit().putString(KEY_PASSWORD, password).apply();
        getInstance(context).edit().putString(KEY_IMAGE, image).apply();
    }

    public static void clearSession(Context context) {
        getInstance(context).edit().clear().apply();
    }
}
