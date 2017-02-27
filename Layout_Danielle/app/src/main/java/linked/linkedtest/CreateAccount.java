package linked.linkedtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //        User button from Create Account screen
        Button button2 = (Button) findViewById(R.id.userButton);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(CreateAccount.this, UserCreate.class);
                startActivity(i);
            }
        });

        //        Business button from Create Account screen
        Button button3 = (Button) findViewById(R.id.businessButton);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(CreateAccount.this, BusinessCreate.class);
                startActivity(i);
            }
        });
    }
}
