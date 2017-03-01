package linked.linkedtest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static linked.linkedtest.R.string.user;
import static linked.linkedtest.R.string.username;


public class UserCreate extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //Define Spinner & Adapter objects
    Spinner month_spinner, year_spinner, gender_spinner;
    ArrayAdapter adapter;

    //Constants
    private static final int PICK_IMAGE = 1;
    private static final String TAG = "EmailPassword";
    //Firebase Objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);

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


        adapter = ArrayAdapter.createFromResource(this, R.array.month_array, android.R.layout.simple_spinner_item);
        month_spinner = (Spinner) findViewById(R.id.monthSpinner);
        month_spinner.setAdapter(adapter);
        month_spinner.setOnItemSelectedListener(UserCreate.this);

        adapter = ArrayAdapter.createFromResource(this, R.array.year_array, android.R.layout.simple_spinner_item);
        year_spinner = (Spinner) findViewById(R.id.yearSpinner);
        year_spinner.setAdapter(adapter);
        year_spinner.setOnItemSelectedListener(UserCreate.this);

        adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
        gender_spinner = (Spinner) findViewById(R.id.genderSpinner);
        gender_spinner.setAdapter(adapter);
        gender_spinner.setOnItemSelectedListener(UserCreate.this);

        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        //Submit button from User create screen
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                userCreate();
            }
        });
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView spinnerDialogText = (TextView) view;
        Toast.makeText(this, spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {    }

    private void userCreate(){
        // Initialize view objects
        final EditText username = (EditText) findViewById(R.id.userText);
        final EditText fullname = (EditText) findViewById(R.id.nameText2);
        final EditText email = (EditText) findViewById(R.id.emailText2);
        final EditText password = (EditText) findViewById(R.id.passText2);
        final EditText confirm = (EditText) findViewById(R.id.cPassText2);

        // Error Check if all fields are complete
        if (password != confirm) {
            // Throw error
        }

        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        // Create user with FirebaseAuth here
        mAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(UserCreate.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Toast.makeText(UserCreate.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
