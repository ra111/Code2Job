package com.studyx.urgames.code2job;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by rahula on 10/01/18.
 */

public class Formula extends AppCompatActivity {
    View importPanel;
    TextView tv;
    ConstraintLayout Formula_layout;
    int i = 0;
    boolean isShowing=false;
    katex.hourglass.in.mathlib.MathView formula_two;
    katex.hourglass.in.mathlib.MathView  formula3, formula4, formula5, formula6, formula7, formula8, formula9, formula10, formula11, formula12, formula13, formula14,
            formula15, formula16, formula17, formula18, formula19, formula20, formula21, formula22, formula23;
    String tex = "This come from string. You can insert inline formula:" +
            " \\({ax^2 + bx + c = 0}\\) " +
            "or displayed formula: $$\\(\\color{red}{sum_{i=0}^n i^2 }= \\frac{(n^2+n)(2n+1)}{6})\\)$$";
    String form1 = "<div style=\"text-align:center;margin:10px;\" >\\(a^3+b^3+c^3-3abc=(a+b+c)(a^2+b^2+c^2-ab-bc-ca)\\)</div>";
    String form2 = "<div style=\"text-align:center;margin:10px;\" >The product of n consecutive integers is always divisible by n!</div>";
    String form3 = "<div style=\"text-align:center;margin:10px;\" >The sum of any number of even numbers is always even</div>";
    String form4 = "<div style=\"text-align:center;margin:10px;\" >The sum of even number of odd numbers is always even</div>";
    String form5 = "<div style=\"text-align:center;margin:10px;\" >If N is a composite number such that" + "\\(N = a^p b^q c^r\\)</div> ";
    String form6 = "<div style=\"text-align:center;margin:10px;\" >The number of factors of N= " + "\\((p+1)(q+1)(r+1)\\)</div>";
    String form7 = " <div style=\"text-align:center;margin:10px;\" >It can be expressed as the product of two factors in " + "\\(\\frac{(p+1)(q+1)(r+1)}{2}\\)</div>";
    String form8 = "<div style=\"text-align:center;margin:10px;\" >If N is a perfect square, it can be expressed</div>";
    String form9 = "<div style=\"text-align:center;margin:10px;\" >As a product of two DIFFERENT factors in</div>" + "<div style=\"text-align:center;margin:10px;\" >\\(\\frac{(p+1)(q+1)(r+1)-1}{2}\\)</div>" + "<div style=\"text-align:center;margin:10px;\" > ways</div>";
    String form10 = "<div style=\"text-align:center;margin:10px;\" >As a product of two factors in </div>" + "<div style=\"text-align:center;margin:10px;\" >\\(\\frac{(p+1)(q+1)(r+1)+1}{2}\\)</div>" + " <div style=\"text-align:center;margin:10px;\" >ways</div>";
    String form11 = "<div style=\"text-align:center;margin:10px;\" >Sum of all factors of N =</div>" + "<div style=\"text-align:center;margin:10px;\" >\\((\\frac{a^{p+1}-1}{a-1})(\\frac{b^{q+1}-1}{b-1})(\\frac{c^{r+1}-1}{c-1})\\)</div>";
    String form12="<div style=\"text-align:center;margin:10px;\">the number of co-primes of N =</div>"+ "<div style=\"text-align:center;margin:10px;\" >\\(N(1-\\frac{1}{a})(1-\\frac{1}{b})(1-\\frac{1}{c})...\\)</div>";
    String form13 = "<div style=\"text-align:center;margin:10px;\">It can be expressed as a product of two factors in " + "\\(2^{n-1}\\)</div>";
    //String tex="\\(\\color{white}{ax^2 + 7}\\)";
    String form14 = "<div style=\"text-align:center;margin:10px;\">In a triangle ABC,if AD is the median" + "\\(AB^2+AC^2=2(AD^2+BD^2)\\)</div>";
    String form15 = "<div style=\"text-align:center;margin:10px;\">Sum of all the angles in a polygon is" + "\\((2n-4)90\\)</div>";
    String form16 = "<div style=\"text-align:center;margin:10px;\">Exterior angle of a polygon is" + "\\(\\frac{360}{n}\\)</div>";
    String form17 = "<div style=\"text-align:center;margin:10px;\">Interior angle of a polygon is" + "\\((180 -{\\frac{360}{n}})\\)</div>";
    String form18 = "<div style=\"text-align:center;margin:10px;\">Number of diagonals of a polygon is " + "\\(\\frac{n(n-3)}{2}\\)</div>";
    String form19 = "<div style=\"text-align:center;margin:10px;\"><div style=\"text-align:center;margin:10px;\">The angle subtended by the diameter of the circle is 90°</div>";
    String form20 = "<div style=\"text-align:center;margin:10px;\">The number of ways of dividing (p + q) items into two groups containing p and q items respectively is</div>" + "<div style=\"text-align:center;margin:10px;\">\\(\\frac{(p+q)!}{p!q!}\\)</div>";
    String form21 = "<div style=\"text-align:center;margin:10px;\">The number of ways of dividing 2p items into two equal groups of p each is</div>" + "\\(\\frac{(2p)!}{(p!)^2}\\)" + ",when the two groups have distinct identity and" + "<div style=\"text-align:center;margin:10px;\">\\(\\frac{(2p)!}{2!(p!)^2}\\)</div>" +
            "when the two groups do not have distinct identity";
    String form22 = "<div style=\"text-align:center;margin:10px;\">nCr = nCn– r</div>";
    String form23 = "<div style=\"text-align:center;margin:10px;\">Sum of squares of first n natural number =</div>" + "<div style=\"text-align:center;margin:10px;\">\\(\\frac{n(n+1)(2n+1)}{6}\\)</div>";
    String form24 = " <div style=\"text-align:center;margin:10px;\">Sum of cubes of first n natural numbers =</div>" + "<div style=\"text-align:center;margin:10px;\">\\(\\frac{n(n+1)}{2}^2\\)</div>";
    String form25 = "<div style=\"text-align:center;margin:10px;\">Sum of first n natural numbers =</div>" + "<div style=\"text-align:center;margin:10px;\">\\(\\frac{n(n+1)}{2}\\)</div>";
    String form26 = "  <div style=\"text-align:center;margin:10px;\">\\((G.M)^2 = (A.M) (H.M)\\)</div>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formula);
        
        importPanel = ((ViewStub) findViewById(R.id.stub_import));
        importPanel.setVisibility(View.VISIBLE);

        Formula_layout=(ConstraintLayout)findViewById(R.id.formula_layout);
        Formula_layout.setVisibility(View.GONE);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#505050")));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));

            window.setBackgroundDrawableResource(R.drawable.animationlist);
        }

        android.support.v7.app.ActionBar ab = getSupportActionBar();

        // Create a TextView programmatically.
        tv = new TextView(getApplicationContext());

        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView

        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);

        // Set text to display in TextView
        tv.setText("FORMULA");

        // Set the text color of TextView
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(20.0f);
        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

        tv.setTypeface(boldTypeface);

        // Set TextView text alignment to center
        tv.setGravity(Gravity.CENTER);

        // Set the ActionBar display option
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        // Finally, set the newly created TextView as ActionBar custom view
        ab.setCustomView(tv);

    }


    @Override
    protected void onResume() {
        super.onResume();

        importPanel.setVisibility(View.GONE);
        formula_two = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula_two);
        formula4 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula4);
        formula5 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula5);
        formula3 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula3);
        formula6 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula6);
        formula7 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula7);
        formula8 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula8);
        formula9 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula9);
        formula10 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula10);
        formula11 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula11);
        formula12 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula12);
        formula13 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula13);
        formula14 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula14);
        formula15 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula15);
        formula16 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula16);
        formula17 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula17);
        formula18 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula18);
        formula19 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula19);
        formula20 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula20);
        formula21 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula21);
        formula22 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula22);
     //   formula23 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula23);
formula3.setDisplayText(form24);
formula_two.setDisplayText(form22);
        formula4.setDisplayText(form2);
        formula5.setDisplayText(form3);
        formula6.setDisplayText(form4);
        formula7.setDisplayText(form5);
        formula8.setDisplayText(form6);
        formula9.setDisplayText(form7);
        formula10.setDisplayText(form8);
        formula11.setDisplayText(form9);
        formula12.setDisplayText(form10);
        formula13.setDisplayText(form11);
        formula14.setDisplayText(form12);
        formula15.setDisplayText(form13);
        formula16.setDisplayText(form14);
        formula17.setDisplayText(form15);
        formula18.setDisplayText(form16);
        formula19.setDisplayText(form17);
        formula20.setDisplayText(form18);
        formula21.setDisplayText(form19);
        formula22.setDisplayText(form20);
    //    formula23.setDisplayText(form21);
        Formula_layout.setVisibility(View.VISIBLE);
        importPanel.setVisibility(View.GONE);

    }
}
