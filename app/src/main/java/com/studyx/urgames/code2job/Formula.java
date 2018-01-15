package com.studyx.urgames.code2job;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.kexanie.library.MathView;

/**
 * Created by rahula on 10/01/18.
 */

public class Formula extends AppCompatActivity{
    MathView formula_two;
    String tex = "This come from string. You can insert inline formula:" +
            " \\(ax^2 + bx + c = 0\\) " +
            "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formula );
    }

    @Override
    protected void onResume() {
        super.onResume();

        formula_two = (MathView) findViewById(R.id.formula_two);
        formula_two.setText(tex);
    }
}
