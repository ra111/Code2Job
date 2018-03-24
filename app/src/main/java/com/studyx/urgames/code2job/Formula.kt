package com.studyx.urgames.code2job

import android.app.ActionBar
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewStub
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by rahula on 10/01/18.
 */

class Formula : AppCompatActivity() {
    internal lateinit var importPanel: View
    internal lateinit var tv: TextView
    internal  lateinit var Formula_layout: ConstraintLayout
    internal var i = 0
    internal var isShowing = false
    internal lateinit var  formula_two: katex.hourglass.`in`.mathlib.MathView
    internal  lateinit var formula3: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula4: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula5: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula6: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula7: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula8: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula9: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula10: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula11: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula12: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula13: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula14: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula15: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula16: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula17: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula18: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula19: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula20: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula21: katex.hourglass.`in`.mathlib.MathView
    internal lateinit var formula22: katex.hourglass.`in`.mathlib.MathView
    internal  var formula23: katex.hourglass.`in`.mathlib.MathView? = null
    internal  var tex = "This come from string. You can insert inline formula:" +
            " \\({ax^2 + bx + c = 0}\\) " +
            "or displayed formula: $$\\(\\color{red}{sum_{i=0}^n i^2 }= \\frac{(n^2+n)(2n+1)}{6})\\)$$"
    internal var form1 = "<div style=\"text-align:center;margin:10px;\" >\\(a^3+b^3+c^3-3abc=(a+b+c)(a^2+b^2+c^2-ab-bc-ca)\\)</div>"
    internal var form2 = "<div style=\"text-align:center;margin:10px;\" >The product of n consecutive integers is always divisible by n!</div>"
    internal var form3 = "<div style=\"text-align:center;margin:10px;\" >The sum of any number of even numbers is always even</div>"
    internal var form4 = "<div style=\"text-align:center;margin:10px;\" >The sum of even number of odd numbers is always even</div>"
    internal var form5 = "<div style=\"text-align:center;margin:10px;\" >If N is a composite number such that" + "\\(N = a^p b^q c^r\\)</div> "
    internal var form6 = "<div style=\"text-align:center;margin:10px;\" >The number of factors of N= " + "\\((p+1)(q+1)(r+1)\\)</div>"
    internal var form7 = " <div style=\"text-align:center;margin:10px;\" >It can be expressed as the product of two factors in " + "\\(\\frac{(p+1)(q+1)(r+1)}{2}\\)</div>"
    internal var form8 = "<div style=\"text-align:center;margin:10px;\" >If N is a perfect square, it can be expressed</div>"
    internal var form9 = "<div style=\"text-align:center;margin:10px;\" >As a product of two DIFFERENT factors in</div>" + "<div style=\"text-align:center;margin:10px;\" >\\(\\frac{(p+1)(q+1)(r+1)-1}{2}\\)</div>" + "<div style=\"text-align:center;margin:10px;\" > ways</div>"
    internal var form10 = "<div style=\"text-align:center;margin:10px;\" >As a product of two factors in </div>" + "<div style=\"text-align:center;margin:10px;\" >\\(\\frac{(p+1)(q+1)(r+1)+1}{2}\\)</div>" + " <div style=\"text-align:center;margin:10px;\" >ways</div>"
    internal var form11 = "<div style=\"text-align:center;margin:10px;\" >Sum of all factors of N =</div>" + "<div style=\"text-align:center;margin:10px;\" >\\((\\frac{a^{p+1}-1}{a-1})(\\frac{b^{q+1}-1}{b-1})(\\frac{c^{r+1}-1}{c-1})\\)</div>"
    internal var form12 = "<div style=\"text-align:center;margin:10px;\">the number of co-primes of N =</div>" + "<div style=\"text-align:center;margin:10px;\" >\\(N(1-\\frac{1}{a})(1-\\frac{1}{b})(1-\\frac{1}{c})...\\)</div>"
    internal var form13 = "<div style=\"text-align:center;margin:10px;\">It can be expressed as a product of two factors in " + "\\(2^{n-1}\\)</div>"
    //String tex="\\(\\color{white}{ax^2 + 7}\\)";
    internal var form14 = "<div style=\"text-align:center;margin:10px;\">In a triangle ABC,if AD is the median" + "\\(AB^2+AC^2=2(AD^2+BD^2)\\)</div>"
    internal var form15 = "<div style=\"text-align:center;margin:10px;\">Sum of all the angles in a polygon is" + "\\((2n-4)90\\)</div>"
    internal var form16 = "<div style=\"text-align:center;margin:10px;\">Exterior angle of a polygon is" + "\\(\\frac{360}{n}\\)</div>"
    internal var form17 = "<div style=\"text-align:center;margin:10px;\">Interior angle of a polygon is" + "\\((180 -{\\frac{360}{n}})\\)</div>"
    internal var form18 = "<div style=\"text-align:center;margin:10px;\">Number of diagonals of a polygon is " + "\\(\\frac{n(n-3)}{2}\\)</div>"
    internal var form19 = "<div style=\"text-align:center;margin:10px;\"><div style=\"text-align:center;margin:10px;\">The angle subtended by the diameter of the circle is 90°</div>"
    internal var form20 = "<div style=\"text-align:center;margin:10px;\">The number of ways of dividing (p + q) items into two groups containing p and q items respectively is</div>" + "<div style=\"text-align:center;margin:10px;\">\\(\\frac{(p+q)!}{p!q!}\\)</div>"
    internal var form21 = "<div style=\"text-align:center;margin:10px;\">The number of ways of dividing 2p items into two equal groups of p each is</div>" + "\\(\\frac{(2p)!}{(p!)^2}\\)" + ",when the two groups have distinct identity and" + "<div style=\"text-align:center;margin:10px;\">\\(\\frac{(2p)!}{2!(p!)^2}\\)</div>" +
            "when the two groups do not have distinct identity"
    internal var form22 = "<div style=\"text-align:center;margin:10px;\">nCr = nCn– r</div>"
    internal var form23 = "<div style=\"text-align:center;margin:10px;\">Sum of squares of first n natural number =</div>" + "<div style=\"text-align:center;margin:10px;\">\\(\\frac{n(n+1)(2n+1)}{6}\\)</div>"
    internal var form24 = " <div style=\"text-align:center;margin:10px;\">Sum of cubes of first n natural numbers =</div>" + "<div style=\"text-align:center;margin:10px;\">\\(\\frac{n(n+1)}{2}^2\\)</div>"
    internal var form25 = "<div style=\"text-align:center;margin:10px;\">Sum of first n natural numbers =</div>" + "<div style=\"text-align:center;margin:10px;\">\\(\\frac{n(n+1)}{2}\\)</div>"
    internal var form26 = "  <div style=\"text-align:center;margin:10px;\">\\((G.M)^2 = (A.M) (H.M)\\)</div>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formula)

        importPanel = findViewById<View>(R.id.stub_import) as ViewStub
        importPanel.visibility = View.VISIBLE

        Formula_layout = findViewById<View>(R.id.formula_layout) as ConstraintLayout
        Formula_layout.visibility = View.GONE
        supportActionBar!!.setBackgroundDrawable(
                ColorDrawable(Color.parseColor("#505050")))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(android.R.color.transparent)

            window.setBackgroundDrawableResource(R.drawable.animationlist)
        }

        val ab = supportActionBar

        // Create a TextView programmatically.
        tv = TextView(applicationContext)

        // Create a LayoutParams for TextView
        val lp = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT) // Height of TextView

        // Apply the layout parameters to TextView widget
        tv.layoutParams = lp

        // Set text to display in TextView
        tv.text = "FORMULA"

        // Set the text color of TextView
        tv.setTextColor(Color.WHITE)
        tv.textSize = 20.0f
        val boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD)

        tv.typeface = boldTypeface

        // Set TextView text alignment to center
        tv.gravity = Gravity.CENTER

        // Set the ActionBar display option
        ab!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        // Finally, set the newly created TextView as ActionBar custom view
        ab.customView = tv

    }


    override fun onResume() {
        super.onResume()

        importPanel.visibility = View.GONE
        formula_two = findViewById<View>(R.id.formula_two) as katex.hourglass.`in`.mathlib.MathView
        formula4 = findViewById<View>(R.id.formula4) as katex.hourglass.`in`.mathlib.MathView
        formula5 = findViewById<View>(R.id.formula5) as katex.hourglass.`in`.mathlib.MathView
        formula3 = findViewById<View>(R.id.formula3) as katex.hourglass.`in`.mathlib.MathView
        formula6 = findViewById<View>(R.id.formula6) as katex.hourglass.`in`.mathlib.MathView
        formula7 = findViewById<View>(R.id.formula7) as katex.hourglass.`in`.mathlib.MathView
        formula8 = findViewById<View>(R.id.formula8) as katex.hourglass.`in`.mathlib.MathView
        formula9 = findViewById<View>(R.id.formula9) as katex.hourglass.`in`.mathlib.MathView
        formula10 = findViewById<View>(R.id.formula10) as katex.hourglass.`in`.mathlib.MathView
        formula11 = findViewById<View>(R.id.formula11) as katex.hourglass.`in`.mathlib.MathView
        formula12 = findViewById<View>(R.id.formula12) as katex.hourglass.`in`.mathlib.MathView
        formula13 = findViewById<View>(R.id.formula13) as katex.hourglass.`in`.mathlib.MathView
        formula14 = findViewById<View>(R.id.formula14) as katex.hourglass.`in`.mathlib.MathView
        formula15 = findViewById<View>(R.id.formula15) as katex.hourglass.`in`.mathlib.MathView
        formula16 = findViewById<View>(R.id.formula16) as katex.hourglass.`in`.mathlib.MathView
        formula17 = findViewById<View>(R.id.formula17) as katex.hourglass.`in`.mathlib.MathView
        formula18 = findViewById<View>(R.id.formula18) as katex.hourglass.`in`.mathlib.MathView
        formula19 = findViewById<View>(R.id.formula19) as katex.hourglass.`in`.mathlib.MathView
        formula20 = findViewById<View>(R.id.formula20) as katex.hourglass.`in`.mathlib.MathView
        formula21 = findViewById<View>(R.id.formula21) as katex.hourglass.`in`.mathlib.MathView
        formula22 = findViewById<View>(R.id.formula22) as katex.hourglass.`in`.mathlib.MathView
        //   formula23 = (katex.hourglass.in.mathlib.MathView) findViewById(R.id.formula23);
        formula3.setDisplayText(form24)
        formula_two.setDisplayText(form22)
        formula4.setDisplayText(form2)
        formula5.setDisplayText(form3)
        formula6.setDisplayText(form4)
        formula7.setDisplayText(form5)
        formula8.setDisplayText(form6)
        formula9.setDisplayText(form7)
        formula10.setDisplayText(form8)
        formula11.setDisplayText(form9)
        formula12.setDisplayText(form10)
        formula13.setDisplayText(form11)
        formula14.setDisplayText(form12)
        formula15.setDisplayText(form13)
        formula16.setDisplayText(form14)
        formula17.setDisplayText(form15)
        formula18.setDisplayText(form16)
        formula19.setDisplayText(form17)
        formula20.setDisplayText(form18)
        formula21.setDisplayText(form19)
        formula22.setDisplayText(form20)
        //    formula23.setDisplayText(form21);
        Formula_layout.visibility = View.VISIBLE
        importPanel.visibility = View.GONE

    }
}
