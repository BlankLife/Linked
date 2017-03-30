package linked.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserMenu extends AppCompatActivity implements View.OnClickListener{

    TextView signout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);


        /* An example to access current user's information
        FirebaseUser current_user;
        current_user = FirebaseAuth.getInstance().getCurrentUser();
        if (current_user != null){
            String name = current_user.getDisplayName();
            String email = current_user.getEmail();
            Uri photoUrl = current_user.getPhotoUrl;
            String uid = current_user.getUid();           // The user's ID, unique to the Firebase project.
        }*/

        signout_button = (TextView) findViewById(R.id.signoutButton);
        signout_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v == signout_button){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(UserMenu.this, Start.class));
        }
        //...

    }


}
