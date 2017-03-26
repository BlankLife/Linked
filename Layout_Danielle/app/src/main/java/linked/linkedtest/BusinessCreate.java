package linked.linkedtest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
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


public class BusinessCreate extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner business_spinner;
    ArrayAdapter adapter;
    private static final int PICK_IMAGE = 1;
    private static final String TAG = "EmailPassword";

    //Firebase Objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_create);

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

        adapter = ArrayAdapter.createFromResource(this, R.array.business_type_array, R.layout.spinner_layout);
        business_spinner = (Spinner) findViewById(R.id.businessSpinner);
        business_spinner.setAdapter(adapter);
        business_spinner.setOnItemSelectedListener(BusinessCreate.this);

        ImageButton button = (ImageButton) findViewById(R.id.imageButton2);
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

        //        Submit button from Create Business Account screen
        Button submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                businessCreate();
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
/*        TextView spinnerDialogText = (TextView) view;
        Toast.makeText(this, spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {    }

    private void businessCreate(){
        // Initialize view objects
        //final EditText username = (EditText) findViewById(R.id.userText);
        final EditText fullname = (EditText) findViewById(R.id.emailText2);
        final EditText email = (EditText) findViewById(R.id.cPassText2);
        final EditText business = (EditText) findViewById(R.id.nameText2);
        final EditText address = (EditText) findViewById(R.id.passText2);
        final EditText password = (EditText) findViewById(R.id.passText);
        final EditText confirm = (EditText) findViewById(R.id.cPassText);

        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        Business_CLASS newBusiness = new Business_CLASS();

        newBusiness.owner = fullname.getText().toString().trim();
        newBusiness.emailaddress = email.getText().toString().trim();
        newBusiness.business_name = business.getText().toString().trim();
        newBusiness.business_address = address.getText().toString().trim();
        newBusiness.password = password.getText().toString().trim();

        Map<String, Object> business_info = new HashMap<String, Object>();
        business_info.put(email.getText().toString().trim(), newBusiness);
        root.child("Business_Accounts").updateChildren(business_info);
        root.child("All_Accounts").updateChildren(business_info);

        if (password != confirm) {
            //Throw error for this if all fields are filled
        }

        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        // Create user with FirebaseAuth here
        /*mAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(BusinessCreate.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        startActivity(new Intent(BusinessCreate.this, BusinessActivities.class));
                        if (!task.isSuccessful()) {
                            Toast.makeText(BusinessCreate.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

    }
}
