package linked.linkedtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Start extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Initialize Firebase Object
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...

            }
        };

        //Sign up button from main screen
        Button registerButton = (Button) findViewById(R.id.signupButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Start.this, CreateAccount.class));
            }
        });

        //Log in button from main screen
        Button loginButton = (Button) findViewById(R.id.signinButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    userLogin();
            }
        });
    }

    public void userLogin() {
        final EditText username = (EditText) findViewById(R.id.usernameField);
        final EditText password = (EditText) findViewById(R.id.passwordField);

        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(Start.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        startActivity(new Intent(Start.this, Menu.class));

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(Start.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                            /*  If sign in fails, stay in the same page
                                For testing purposes (since we don't have a DB yet) it goes to Menu page*/
                            startActivity(new Intent(Start.this, BusinessActivities.class));
                        }
                    }
                });
    }
}

