package linked.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static linked.main.R.id.submitButton;

public class UserCreate extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    //Define Spinner & Adapter objects
    Spinner month_spinner, year_spinner, gender_spinner;
    ArrayAdapter adapter;
    ImageButton img_button;
    Button submit_button;
    EditText username, fullname, email, password, confirm;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener  mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("EmailPassword", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("EmailPassword", "onAuthStateChanged:signed_out");
                }
            }
        };

        adapter = ArrayAdapter.createFromResource(this, R.array.month_array, R.layout.spinner_layout);
        month_spinner = (Spinner) findViewById(R.id.monthSpinner);
        month_spinner.setAdapter(adapter);
        month_spinner.setOnItemSelectedListener(UserCreate.this);

        adapter = ArrayAdapter.createFromResource(this, R.array.year_array, R.layout.spinner_layout);
        year_spinner = (Spinner) findViewById(R.id.yearSpinner);
        year_spinner.setAdapter(adapter);
        year_spinner.setOnItemSelectedListener(UserCreate.this);

        adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, R.layout.spinner_layout);
        gender_spinner = (Spinner) findViewById(R.id.genderSpinner);
        gender_spinner.setAdapter(adapter);
        gender_spinner.setOnItemSelectedListener(UserCreate.this);

        img_button = (ImageButton) findViewById(R.id.imageButton);
        img_button.setOnClickListener(this);

        submit_button = (Button) findViewById(submitButton);
        submit_button.setOnClickListener(this);

        username = (EditText) findViewById(R.id.userText);
        fullname = (EditText) findViewById(R.id.nameText2);
        email = (EditText) findViewById(R.id.emailText2);
        password = (EditText) findViewById(R.id.passText2);
        confirm = (EditText) findViewById(R.id.cPassText2);
    }

    @Override
    public void onClick(View v) {
        if (v == img_button) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
        }
        else if (v == submit_button)
            createUserAuth();
    }

    private void createUserAuth(){
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(UserCreate.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if (!task.isSuccessful()) {
                            Log.d("EmailPassword", "createUserWithEmail:failed:" + task.getException());
                            Toast.makeText(UserCreate.this, R.string.create_user_failed, Toast.LENGTH_SHORT).show();
                        }

                        else {
                            Log.d("EmailPassword", "createUserWithEmail:onComplete:" + task.isSuccessful());
                            createUserDB();
                        }

                    }
                });
    }

    private void createUserDB(){
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        User_CLASS newUser = new User_CLASS();


        /* An example to access current user's information
        FirebaseUser current_user;
        current_user = FirebaseAuth.getInstance().getCurrentUser();
        if (current_user != null){
            String name = current_user.getDisplayName();
            String email = current_user.getEmail();
            Uri photoUrl = current_user.getPhotoUrl;

           // The user's ID, unique to the Firebase project. Do NOT use this value to
           // authenticate with your backend server, if you have one. Use
           // FirebaseUser.getToken() instead.
            String uid = current_user.getUid();
        }*/


        // newUser.username = username.getText().toString().trim();
        //newUser.fullname = fullname.getText().toString().trim();
        newUser.emailaddress = email.getText().toString().trim();
        newUser.password = password.getText().toString().trim();
       // newUser.month = month_spinner.getSelectedItem().toString();
        //newUser.year = year_spinner.getSelectedItem().toString();
        //newUser.gender = gender_spinner.getSelectedItem().toString();


        Map<String, Object> user_info = new HashMap<String, Object>();
        user_info.put(email.getText().toString().trim(), newUser);

        //root.child("User_Accounts").setValue(user_info);
        //root.child("All_Accounts").setValue(user_info);
        root.child("mytest").setValue("success");

        startActivity(new Intent(UserCreate.this, UserMenu.class));
    }


    // These methods are needed to enable spinners
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {    }

}
