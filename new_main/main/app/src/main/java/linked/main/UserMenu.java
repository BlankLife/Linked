package linked.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class UserMenu extends AppCompatActivity implements View.OnClickListener{

    TextView signout_button;
    ImageButton link_button, link_button3;
    ImageView find_act, view_act, chat, settings;

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
        }

        signout_button = (TextView) findViewById(R.id.signoutButton);
        signout_button.setOnClickListener(this);

        link_button = (ImageButton)findViewById(R.id.imageButton1);
        link_button.setOnClickListener(this);

        link_button3 = (ImageButton)findViewById(R.id.imageButton3);
        link_button3.setOnClickListener(this);*/

        find_act = (ImageView) findViewById(R.id.findImageView);
        find_act.setOnClickListener(this);
        view_act = (ImageView) findViewById(R.id.viewImageView);
        view_act.setOnClickListener(this);
        chat =  (ImageView) findViewById(R.id.chatImageView);
        chat.setOnClickListener(this);
        settings = (ImageView) findViewById(R.id.settingsImageView);
        settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v == settings){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(UserMenu.this, Start.class));
        }
        //...
        if (v == find_act)
            startActivity(new Intent(this, LocationActivity.class));
        else if (v == view_act)
            startActivity(new Intent(this, BusinessActivities.class));
    }
}
