# PreferencesScreenDemoApp
demo App for Preferences/Settings screen in android

## Introduction
- Preferences are pieces of information that Apps store persistently(in a file or storage) and then use them to configure itself. 
- Apps often expose preferences to users using "Prefrence Screen" so that they can customize the appearance and behavior of the app. 
- So "Preferences Screen" is screen that exposes the preferences to user and also let them alter the value of these preferences.
- Preference Screen is backed by a Shared preference file that stores preference values persistently.
- Android system creates a default shared preference file for each app.
- Android also provide developer with option to create a new shared preference file and use that instead of default shared preference file.

## Steps for Implementing Preferences/Settings Screen in Andorid
- include dependency for "preferences from support library" in build.gradle
- create preference file ,its an xml file inside xml folder that contains preference elements in the root element "Preference Screen".
- create Settings Fragment by Subclassing PreferenceFragmentCompat class and implement its methods.
- Add preference xml file to Settings Fragment inside onCreatePreferences() method of Settings Fragment.
- create a settings Activity(its a normal Activity) and in its layout file define a container for PrefrenceFragment.
- Add Settings Fragment to the fragment containerin in settings Activity .

### Include dependency
     compile 'com.android.support:preference-v7:26.1.0'

### Create preference xml file
- create xml folder in res directory.
- create xml file inside this xml folder.
- define preference Catalog by putting preference elements inside root of xml file which is "PreferenceScreen".
   
      <?xml version="1.0" encoding="utf-8"?>
      <PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
      <PreferenceCategory android:title="General">
        <EditTextPreference
            android:defaultValue="@string/store_by_name_default_value"
            android:key="@string/store_by_name_key"
            android:summary="My videos"
            android:title="Default Storage" />

        <CheckBoxPreference
            android:defaultValue="@bool/auto_upload_default_value"
            android:key="@string/auto_upload_key"
            android:summaryOn="Auto Upload Over Wifi"
            android:summaryOff="Do Not Auto Upload Over Wifi"
            android:title="Auto Upload" />

        <ListPreference
            android:defaultValue="@string/upload_quality_default_value"
            android:dialogTitle="Upload Quality"
            android:entries="@array/pref_upload_quality_entries"
            android:entryValues="@array/pref_upload_quality_values"
            android:key="@string/upload_quality_key"
            android:summary="@string/upload_quality_default_value"
            android:title="Upload Quality" />

      </PreferenceCategory>
      </PreferenceScreen>

### Create Settings Fragment and attach preference xml to it.

- create settings Fragment by subclassing PreferenceFragmentCompat class and implementing its method onCreatePreferences().
- settings Fragment is a Fragment, so like fragments we attach a layout file to it in onCreate method.
- but unlike normal fragments, in PreferenceFragmentCompat class we do it using method addPreferencesFromResource(R.xml.your_pref).
- also in PreferenceFragmentCompat we do not attach layout file instead we attach "Pref xml file".
- PreferenceFragmentCompat is a specialize Fragment that is designed to be able to inflate Preference file using its method 
  addPreferencefromResources().
  
  #### OnSharedPreferenceChangeListener  
- Preference Elements in PreferenceFragment are backed by shared preference file which means any input/change to preferences will
  be Persistant.
- we can attach onChangeListener to the shared preference file because as we just mentioned PreferenceFragment is backed by shared         Preference file ,so any change to Preferences in PreferenceFragment will also change the shared preference file of the app ,so when
  this happens we want to be able to know that ,so that we can read the changes and Alter the app behaviour accordingly.
- also bydefault PreferenceFragment uses default shared preference file of the app.

### Add setting Fragment to settings Activity
- simple create a settings activity and add out settings Fragment to it.
- settings activity is a normal activity.
- in onStart() of activity check for SharedPreferenceChange_Flag ,if its altered that would mean changes were made to shared pref
  file,so read from values from shared pref file and change behaviour of the app accordingly.

## Things to look for
- if our prefScreen has category than the getPreferenceScreen().getSharedPreferences() will get us only categories pref ,so than we     would   have to traverse through them to get the individual preferences. same is the case in this app.


