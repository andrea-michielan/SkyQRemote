package com.michielan.skyqremote;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MyTag";

    public void showHelp(View view) {
        Intent myIntent = new Intent(MainActivity.this, HelpActivity.class);
        MainActivity.this.startActivity(myIntent);
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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.help_toast), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.help_toast_action), new HelpListener()).show();
            }
        });

        // Check if the app has been launched for the first time or if the ip has not been set yet
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String ip = sharedPreferences.getString("ip", "_");
        // Check if the ip is in the correct form
        assert ip != null;
        if (!ip.matches("([0-9]{1,3})[.]{1}([0-9]{1,3})[.]{1}([0-9]{1,3})[.]{1}([0-9]{1,3})"))
            // Show a dialog when the app is installed for the first time
            showStartDialog();

    }

    private void showStartDialog() {
        new AlertDialog.Builder(this, R.style.AlertDialogDark)
                .setTitle(R.string.first_time_dialog_title)
                .setMessage(R.string.first_time_dialog_message)
                .setPositiveButton(R.string.first_time_dialog_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                        MainActivity.this.startActivity(settingsIntent);
                    }
                }).setNegativeButton(R.string.first_time_dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        })
                .create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}