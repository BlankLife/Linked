package linked.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BusinessCreate extends AppCompatActivity implements View.OnClickListener{

    // Layout Declarations
    EditText fullname, email, business, address, password, confirm;
    ImageButton img_button;
    Button submit_button;

    // Firebase Declarations
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener  mAuthListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_create);

        // Firebase
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null)
                    Log.d("EmailPassword", "onAuthStateChanged:signed_in:" + user.getUid());
                else
                    Log.d("EmailPassword", "onAuthStateChanged:signed_out");
            }
        };

        // Layout
        fullname = (EditText) findViewById(R.id.emailText2);
        email = (EditText) findViewById(R.id.cPassText2);
        business = (EditText) findViewById(R.id.nameText2);
        address = (EditText) findViewById(R.id.passText2);
        password = (EditText) findViewById(R.id.passText);
        confirm = (EditText) findViewById(R.id.cPassText);
        img_button = (ImageButton) findViewById(R.id.imageButton2);
        submit_button = (Button) findViewById(R.id.submitButton);

        // When a click event happens, onClick method is called (implements the interface)
        img_button.setOnClickListener(this);
        submit_button.setOnClickListener(this);
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
        if (v == img_button) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
        }
        else if (v == submit_button) {
            businessCreateAuth();
        }
    }

    private void businessCreateAuth(){
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(BusinessCreate.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if (!task.isSuccessful()) {
                            Log.d("EmailPassword", "createUserWithEmail:failed:" + task.getException());
                            Toast.makeText(BusinessCreate.this, R.string.create_user_failed, Toast.LENGTH_SHORT).show();
                        }

                        else {
                            Log.d("EmailPassword", "createUserWithEmail:onComplete:" + task.isSuccessful());
                            businessCreateDB();
                        }

                    }
                });
    }

    private void businessCreateDB(){
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
        }

        // Database
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        Business_CLASS newBusiness = new Business_CLASS();

        //root.push().getKey().

        newBusiness.owner = fullname.getText().toString().trim();
        newBusiness.emailaddress = email.getText().toString().trim();
        newBusiness.business_name = business.getText().toString().trim();
        newBusiness.business_address = address.getText().toString().trim();
        newBusiness.password = password.getText().toString().trim();

        Map<String, Object> business_info = new HashMap<String, Object>();
        business_info.put(email.getText().toString().trim(), newBusiness);
        business_info.put(password.getText().toString().trim(), newBusiness);

        root.child("All_Accounts").child("Business_Accounts").setValue(business_info);
        */
        startActivity(new Intent(BusinessCreate.this, Start.class));
    }

}





