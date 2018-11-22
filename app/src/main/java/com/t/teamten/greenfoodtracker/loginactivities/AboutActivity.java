package com.t.teamten.greenfoodtracker.loginactivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.t.teamten.greenfoodtracker.R;

public class AboutActivity extends AppCompatActivity {

    PDFView about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutactivity);
        about = findViewById(R.id.pdfaboutpage);
        about.fromAsset("aboutpage.pdf").load();

    }
}
