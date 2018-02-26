package com.studyx.urgames.code2job;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import io.github.kexanie.library.MathView;

/**
 * Created by rahula on 10/01/18.
 */

public class Formula extends Activity {
    View importPanel;
    MathView formula_two;
    String tex = "This come from string. You can insert inline formula:" +
            " \\(\\color{white}{ax^2 + bx + c = 0}\\) " +
                    "or displayed formula: $$\\(\\color{white}{sum_{i=0}^n i^2 }= \\frac{(n^2+n)(2n+1)}{6})\\)$$";

//String tex="\\(\\color{white}{ax^2 + 7}\\)";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formula );
         importPanel = ((ViewStub) findViewById(R.id.stub_import)).inflate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        importPanel.setVisibility(View.GONE);
        formula_two = (MathView) findViewById(R.id.formula_two);
        formula_two.setText(tex);

    }
}
