package linked.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class UserMenu extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    TextView signout_button;
    ImageButton link_button, link_button3;
    ImageView find_act, view_act, chat, settings;

    public static ArrayList<String> businesses = new ArrayList<>();

    private GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    private double latitude;
    private double longitude;
    private static final String TAG = "CardFragment";
    DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Business_Accounts").child("User_ID");
    StorageReference mStorage = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);


        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

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
        else if (v == chat)
            startActivity(new Intent(this, MessagingActivity.class));
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(100000);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }


    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {

        //update current user location
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.d(TAG,String.valueOf(latitude));
        Log.d(TAG,String.valueOf(longitude));
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                businesses.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //if within, save name of the business
                    if(distanceBetween(latitude,longitude,Double.valueOf((String)snapshot.child("latitude").getValue()),
                            Double.valueOf((String) snapshot.child("longitude").getValue())))
                    {
                        businesses.add((String) snapshot.child("business_name").getValue());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    boolean distanceBetween(double lat1, double lon1, double lat2, double lon2){
        float [] result = new float[1];     //distance between return in meter

        Location.distanceBetween(lat1,lon1,lat2, lon2,result);
        //within 2 mile, return true
        if(result[0] < 3218.74){
            return true;
        }

        return false;
    }
/*
    Bitmap getImage(String path){


    }
*/
}
