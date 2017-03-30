package linked.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    Button user_account, business_account, business_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // User Account button from Create Account screen to User Create screen
        user_account = (Button) findViewById(R.id.userButton);
        user_account.setOnClickListener(this);

        business_account = (Button) findViewById(R.id.businessButton);
        business_account.setOnClickListener(this);

        business_menu = (Button) findViewById(R.id.menuButton);
        business_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == user_account)
            startActivity(new Intent(CreateAccount.this, UserCreate.class));
        else if (v == business_account)
            startActivity(new Intent(CreateAccount.this, BusinessCreate.class));
        else if (v == business_menu)                        //this else if statement is for testing only
            startActivity(new Intent(CreateAccount.this, BusinessMenu.class));
        }
}
