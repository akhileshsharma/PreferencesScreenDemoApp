package com.easyapps.preferencesdemoapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.util.Log;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String TAG = "flow";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_main);

        Log.i(TAG, "onCreatePreferences: get default shared pref file and register " +
                "onSharePreferenceChange listener on it");
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        PreferenceScreen prefScreen = getPreferenceScreen();
        int count = prefScreen.getPreferenceCount();
        Log.i(TAG, "onCreatePreferences: app has categories ,we don't want to change summary " +
                "of prefrences in third categories calle about, so we decrement the count ,so we don't have " +
                "to traverse through 3rd category");
        count--;

        Log.i(TAG, "onCreatePreferences: if our prefScreen has category than the getPreferenceScreen().getSharedPreferences() " +
                "will get us only categories pref ,so than we would have to traverse through them to get the individual " +
                "preferences. same is the case in this app.");
        for (int i = 0; i < count; i++) {
            Preference preference = prefScreen.getPreference(i);

            if (preference instanceof PreferenceCategory) {
                PreferenceCategory category = (PreferenceCategory) preference;

                for (int j = 0; j < category.getPreferenceCount(); j++) {

                    if (!(category.getPreference(j) instanceof CheckBoxPreference)
                            &&
                            !(category.getPreference(j) instanceof SwitchPreferenceCompat))
                        category.getPreference(j).setSummary(sharedPreferences.
                                getString(category.getPreference(j).getKey(), ""));

                }

            } else {
                Log.i(TAG, "onCreatePreferences: if prefrence was not a category");
                preference.setSummary(sharedPreferences.
                        getString(preference.getKey(), ""));
            }
        }//for ends
    } //onCreatePreference ends

    public void onStart() {
        Log.i(TAG, "onStart: ");
        super.onStart();

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        setPreferenceSummary(sharedPreferences, key);
    }

    private void setPreferenceSummary(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);

        if (preference instanceof EditTextPreference) {
            preference.setSummary(sharedPreferences.getString(key, "some value"));
        }
        if (preference instanceof ListPreference) {
            preference.setSummary(sharedPreferences.getString(key, "some value"));
        }

    } //setPreferenceSummary closed


}
