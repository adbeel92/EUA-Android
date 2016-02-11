package com.eua.SalesTrackingApp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rubymobile on 2/2/16.
 */
public class PreferencesManager {
    private static PreferencesManager self;
    private final SharedPreferences pref;
    private static final String PREF_NAME = "EUA";

    /**
     * Sets SharedPreferences' value
     * @param context   Environment data where manager was called
     *
     * @see Context#getSharedPreferences(String, int)
     */
    private PreferencesManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Gets instance of the manager for better performance
     *
     * @param context   Environment data where manager was called
     * @return instance of shared
     */
    public static PreferencesManager getInstance(Context context) {
        if (self == null) {
            self = new PreferencesManager(context);
        }
        return self;
    }

    /**
     *
     * @param state state of switch for automatic tracking. If true, automatic trip is on; if not, false
     */
}
