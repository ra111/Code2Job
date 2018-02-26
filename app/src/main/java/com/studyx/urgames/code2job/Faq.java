package com.studyx.urgames.code2job;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.studyx.urgames.code2job.ques.answers;
import static com.studyx.urgames.code2job.ques.questions;

/**
 * Created by rahula on 15/01/18.
 */
public class Faq  extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));

            window.setBackgroundDrawableResource(R.drawable.animationlist);
        }
        ArrayAdapter adapter =new ArrayAdapter<String>(this,R.layout.list,questions);
        ListView listView=(ListView)findViewById(R.id.question_list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                LayoutInflater inflater =(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView =inflater.inflate(R.layout.popup,null);

                RelativeLayout faq=(RelativeLayout) findViewById(R.id.faq);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;

                boolean focusable = false;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.setOutsideTouchable(false);
                final TextView ques =(TextView) popupView.findViewById(R.id.popupTextQues);
                final TextView ans=(TextView)popupView.findViewById(R.id.popupTextAns);
                final Button close=(Button) popupView.findViewById(R.id.close_button);
                close.bringToFront();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                        popupWindow.setOutsideTouchable(true);
                    }
                });

                ques.setText(questions[i]);
                ans.setText(answers[i]);
                popupWindow.showAtLocation(faq, Gravity.CENTER,0,0);


            }

        });
    }

}