package linked.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Start extends AppCompatActivity implements View.OnClickListener {

    //Layout Declarations
    EditText username, password;
    Button register_button, login_button;

    //Firebase Declarations
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                //Used for Firebase Analytics
                if (user != null)
                    Log.d("EmailPassword", "onAuthStateChanged:signed_in:" + user.getUid());
                else
                    Log.d("EmailPassword", "onAuthStateChanged:signed_out");
            }
        };

        //Layout
        username = (EditText) findViewById(R.id.usernameField);
        password = (EditText) findViewById(R.id.passwordField);
        register_button = (Button) findViewById(R.id.signupButton);
        login_button = (Button) findViewById(R.id.signinButton);

        // When a click event happens, onClick method is called (implements the interface)
        register_button.setOnClickListener(this);
        login_button.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == register_button)
            startActivity(new Intent(Start.this, CreateAccount.class));
        else if (v == login_button)
            userLogin();
    }

    private void userLogin() {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        //Error check for empty boxes
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
            Toast.makeText(Start.this, R.string.empty_field, Toast.LENGTH_SHORT).show();
        else {
            // Create user with FirebaseAuth here
            mAuth.signInWithEmailAndPassword(user, pass)
                    .addOnCompleteListener(Start.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Log.w("EmailPassword", "signInWithEmail:failed", task.getException());
                                Toast.makeText(Start.this, R.string.auth_failed,
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("EmailPassword", "signInWithEmail:onComplete:" + task.isSuccessful());

                                //Retrieve "account_type" from database and choose which activity to go to.
                                startActivity(new Intent(Start.this, UserMenu.class));
                                //startActivity(new Intent(Start.this, BusinessMenu.class));
                            }
                        }
                    });
        }
    }

}


