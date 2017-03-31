package linked.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;
import android.content.Intent;

public class BusinessActivities extends AppCompatActivity implements View.OnClickListener {

    Button art_button, food_button, sports_button, submit_button;
    TextView select_act, skip;
    CheckBox box1, box2, box3, box4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_activities);

        select_act = (TextView) findViewById(R.id.select_act);
        select_act.setVisibility(View.INVISIBLE);

        box1 = (CheckBox) findViewById(R.id.checkBox1);
        box1.setVisibility(View.INVISIBLE);
        box2 = (CheckBox) findViewById(R.id.checkBox2);
        box2.setVisibility(View.INVISIBLE);
        box3 = (CheckBox) findViewById(R.id.checkBox3);
        box3.setVisibility(View.INVISIBLE);
        box4 = (CheckBox) findViewById(R.id.checkBox4);
        box4.setVisibility(View.INVISIBLE);

        art_button = (Button) findViewById(R.id.artButton);
        art_button.setOnClickListener(this);

        food_button = (Button) findViewById(R.id.foodButton);
        food_button.setOnClickListener(this);

        sports_button = (Button) findViewById(R.id.sportsButton);
        sports_button.setOnClickListener(this);

        submit_button = (Button) findViewById(R.id.submitButton5);
        submit_button.setOnClickListener(this);

        skip = (TextView) findViewById(R.id.skip_step);
        skip.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == skip)
            startActivity(new Intent(BusinessActivities.this, BusinessMenu.class));
        else if (v == submit_button)
            startActivity(new Intent(BusinessActivities.this, BusinessMenu.class));
        else if (v == art_button){
            select_act.setVisibility(View.VISIBLE);
            box1.setText("Film");
            box1.setVisibility(View.VISIBLE);
            box2.setText("Music");
            box2.setVisibility(View.VISIBLE);
            box3.setText("Pottery");
            box3.setVisibility(View.VISIBLE);
            box4.setText("Theatre");
            box4.setVisibility(View.VISIBLE);
        }
        else if (v == food_button){
            select_act.setVisibility(View.VISIBLE);
            box1.setText("Bars");
            box1.setVisibility(View.VISIBLE);
            box2.setText("Dinner");
            box2.setVisibility(View.VISIBLE);
            box3.setText("Eating Contests");
            box3.setVisibility(View.VISIBLE);
            box4.setText("Wine Tasting");
            box4.setVisibility(View.VISIBLE);
        }
        else if (v == sports_button) {
            select_act.setVisibility(View.VISIBLE);
            box1.setText("Basketball");
            box1.setVisibility(View.VISIBLE);
            box2.setText("Golf");
            box2.setVisibility(View.VISIBLE);
            box3.setText("Soccer");
            box3.setVisibility(View.VISIBLE);
            box4.setText("Yoga");
            box4.setVisibility(View.VISIBLE);
        }
    }
}
