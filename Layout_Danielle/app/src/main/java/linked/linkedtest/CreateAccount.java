package linked.linkedtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import static android.R.id.button2;
import static android.R.id.button3;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);



        //        User Account button from Create Account screen to User Create screen
        /*Button userAcc = (Button) findViewById(R.id.userButton);
        userAcc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(CreateAccount.this, UserCreate.class));
            }
        });*/

        //        Business Account button from Create Account screen to Business Create screen
        Button businessAcc = (Button) findViewById(R.id.businessButton);
        businessAcc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startActivity(new Intent(CreateAccount.this, BusinessCreate.class));
            }
        });
    }

    public void visitMenu(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    public void user_ButtonClick(View view){
        startActivity(new Intent(CreateAccount.this, UserCreate.class));
    }
}
