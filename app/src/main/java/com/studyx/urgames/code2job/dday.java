package com.studyx.urgames.code2job;

import android.app.Activity;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;

/**
 * Created by rahula on 20/01/18.
 */

public class dday extends Activity {


    private static final int REQUEST_WRITE_PERMISSION = 786;

    PdfRenderer mPdfRenderer = null;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dday);


    }

}