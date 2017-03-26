package com.example.ingle.nipm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    private EditText fname;
    private EditText lname;
    private EditText emailaddress;
    private EditText phonenumber;
    private EditText nsheid;
    private EditText age;
    private EditText dateofbirth;
    private EditText gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("users");
        //myRef.setValue("list of users");

    }

    public void addData(View view){

        Person x = new Person();

        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        emailaddress = (EditText) findViewById(R.id.eaddress);
        nsheid = (EditText) findViewById(R.id.nsheid);
        String fname_string = fname.getText().toString().trim();
        String lname_string = lname.getText().toString().trim();
        String emailaddress_string = emailaddress.getText().toString().trim();
        String id_string = nsheid.getText().toString().trim();

        Map<String, Object> genericInfo = new HashMap<String, Object>();
        genericInfo.put("First Name", fname_string);
        genericInfo.put("Last Name", lname_string);
        genericInfo.put("Email Address", emailaddress_string);

        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        //root.child("users").child(id_string).updateChildren(genericInfo);

        phonenumber = (EditText) findViewById(R.id.pnumber);
        age = (EditText) findViewById(R.id.age);
        dateofbirth = (EditText) findViewById(R.id.dob);
        gender = (EditText) findViewById(R.id.sex);
        String phonenumber_string = phonenumber.getText().toString().trim();
        String age_string = age.getText().toString().trim();
        String dob_string = dateofbirth.getText().toString().trim();
        String gender_string = gender.getText().toString().trim();

        Map<String, Object> personalInfo = new HashMap<String, Object>();
        personalInfo.put("Phone Number", phonenumber_string);
        personalInfo.put("Age", age_string);
        personalInfo.put("Date of Birth", dob_string);
        personalInfo.put("Gender", gender_string);
        //root.child("users").child(id_string).child("Personal Information").updateChildren(personalInfo);

        x.f = fname_string;
        x.l = lname_string;
        x.e = emailaddress_string;
        x.pi.a = age_string;
        x.pi.bd = dob_string;
        x.pi.g = gender_string;
        x.pi.ph = phonenumber_string;

        Map<String, Object> info = new HashMap<String, Object>();
        info.put(id_string, x);
        //root.child("users").child(id_string).setValue(x);
        root.child("users").updateChildren(info);

        Log.v("FIRST NAME", x.f);
        Log.v("LAST NAME", x.l);

    }

    public void getData(View view){

        //hold our child nodes
        final List<Person> theUsers = new ArrayList<Person>();
        final DatabaseReference userList = FirebaseDatabase.getInstance().getReference().child("users");

        userList.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //dataSnapshot.getRef().setValue(null)
                //get all of the children at this level
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                //shake hands with each of them
                for (DataSnapshot c : children) {
                    //Person childValue = child.getValue(Person.class);
                    //theUsers.add(childValue);
                    //Log.v("FIRST NAME", childValue.f);
                    //Log.v("LAST NAME", childValue.l);
                    Log.v("id", c.getKey());
                    Log.v("email", c.child("e").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*userList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataSnapshot.getRef().setValue(null);
                //get all of the children at this level
                //Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                //shake hands with each of them
                /*for (DataSnapshot child : children) {
                    Person childValue = child.getValue(Person.class);
                    theUsers.add(childValue);
                    Log.v("FIRST NAME", childValue.f);
                    Log.v("LAST NAME", childValue.l);
                    Log.v("id", child.getKey());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        //Query mee = userList.equalTo("1234");
        //mee.toString();
        //Log.v("data retrieval",mee);


    }



}
