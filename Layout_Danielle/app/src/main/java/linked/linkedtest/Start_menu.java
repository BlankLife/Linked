package linked.linkedtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import static linked.linkedtest.R.id.signupButton;

public class Start_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

//        Sign up button from main screen
        Button button1 = (Button) findViewById(R.id.signupButton);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Start_menu.this, CreateAccount.class);
                startActivity(i);
            }
        });

    }

    public void checkLogin(View view) {

    }

    public void gotoCreate(View view) {

    }
}
