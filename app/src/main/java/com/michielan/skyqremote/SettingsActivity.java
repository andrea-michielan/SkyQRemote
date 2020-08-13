package com.michielan.skyqremote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    public void showHelp(View view) {
        Intent myIntent = new Intent(SettingsActivity.this, HelpActivity.class);
        SettingsActivity.this.startActivity(myIntent);
    }

    class HelpListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // Code to show the help page
            showHelp(v);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        setTheme(R.style.PreferenceScreen);
        setTitle("Settings");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.help_toast), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.help_toast_action), new HelpListener()).show();
            }
        });
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            Preference button = getPreferenceManager().findPreference("discover");
            if (button != null) {
                button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {

                        Fragment newFragment = new DiscoverFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        // replace the FrameLayout with new Fragment
                        transaction.replace(R.id.settings, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();

                        return false;
                    }
                });
            }
        }
    }

}