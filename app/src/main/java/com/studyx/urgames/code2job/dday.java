package com.studyx.urgames.code2job;

import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by rahula on 20/01/18.
 */

public class dday extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {


    private static final int REQUEST_WRITE_PERMISSION = 786;

    PdfRenderer mPdfRenderer = null;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dday);


    }

}