package com.michielan.skyqremote;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        setTitle(R.string.info_title);

        TextView links = findViewById(R.id.about_text1);
        links.setText(Html.fromHtml(getString(R.string.about_text1)));
        links.setMovementMethod(LinkMovementMethod.getInstance());

        TextView email_us = findViewById(R.id.about_text3);
        email_us.setText(Html.fromHtml(getString(R.string.about_text3)));
        email_us.setMovementMethod(LinkMovementMethod.getInstance());

        String versionText;
        try {
            versionText = getString(R.string.about_text4, getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
            TextView version = findViewById(R.id.about_text4);
            version.setText(versionText);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
