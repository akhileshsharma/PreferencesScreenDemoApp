package com.easyapps.preferencesdemoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "flow";
    private static boolean PREFERENCES_HAVE_BEEN_UPDATED = false;
    SharedPreferences sharedPreferences;
    TextView defaultVideos, autoUpload, uploadQuality, messageNotification, vibrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null)
            Toast.makeText(this,"change Preferences from setting Activity"
                    ,Toast.LENGTH_LONG).show();
        setUpUI();
        sharedPreferences = PreferenceManager.
                getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        readValuesFromSharedPrefFile();
    }
    //----------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //---------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //----------------------------------------------
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        PREFERENCES_HAVE_BEEN_UPDATED = true;
        Log.i(TAG, "onSharedPreferenceChanged: " + PREFERENCES_HAVE_BEEN_UPDATED);
    }

    //------------------------------------------------------
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");

        if (PREFERENCES_HAVE_BEEN_UPDATED) {
            Log.i(TAG, "onStart: preferences were updated");
            readValuesFromSharedPrefFile();
            PREFERENCES_HAVE_BEEN_UPDATED = false;
        }
    }//--------------------

    //-------------------------------------------------------------------
    private void readValuesFromSharedPrefFile() {

        defaultVideos.setText(sharedPreferences.getString(getString(R.string.store_by_name_key),
                getString(R.string.store_by_name_default_value)));

        autoUpload.setText("" + sharedPreferences.getBoolean(getString(R.string.auto_upload_key),
                getResources().getBoolean(R.bool.auto_upload_default_value)));

        uploadQuality.setText(sharedPreferences.getString(getString(R.string.upload_quality_key),
                getString(R.string.upload_quality_default_value)));

        messageNotification.setText("" + sharedPreferences.getBoolean(getString(R.string.message_notification_key),
                getResources().getBoolean(R.bool.message_notification_default_value)));

        vibrate.setText("" + sharedPreferences.getBoolean(getString(R.string.vibrate_key),
                getResources().getBoolean(R.bool.vibrate_default_value)));
    }
//-----------------------------------------------------------------------------

    private void setUpUI() {
        defaultVideos = findViewById(R.id.store_by_name_value);
        autoUpload = findViewById(R.id.auto_upload_checkbox_value);
        uploadQuality = findViewById(R.id.upload_quality_value);
        messageNotification = findViewById(R.id.message_notification_value);
        vibrate = findViewById(R.id.vibrate_value);
    }
//-----------------------------------------------------


}