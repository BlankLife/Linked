package linked.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Kyle on 3/27/2017.
 */

public class BusinessMenu extends AppCompatActivity implements View.OnClickListener{

    /* We need to get a reference to the User who logged in, in order to get the correct information
       from the Database.
      ******* Requires interactions with the Database.
     */

    /*
    In reply above,
        //An example to access current user's information
        FirebaseUser current_user;
        current_user = FirebaseAuth.getInstance().getCurrentUser();
        if (current_user != null){
            String name = current_user.getDisplayName();
            String email = current_user.getEmail();
            Uri photoUrl = current_user.getPhotoUrl;
            String uid = current_user.getUid();           // The user's ID, unique to the Firebase project.
     }
     */

    // We will also need a signout button from this menu for authentication to not mess up.

    protected Button selectActivitiesBtn;
    protected Button editBusinessBtn;
    protected TextView businessName;
    protected TextView businessAddress;
    protected String[] activity = {                            //******Retrieved Activities from DataBase in String Array GOES HERE
            "Activity1", "Activity2", "Activity3"};
    protected ArrayList<String> selectedActivities = new ArrayList<>();
    private String nameText = "";                              //******Variable to hold Business Name
    private String addressText = "";                           //******Variable to hold Address Name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_menu);
        nameText = "Some Business";                             //******Retrieve Business Name from Database HERE
        addressText = "Some Address";                           //******Retrieve Business Address from Database HERE
        businessName = (TextView) findViewById(R.id.businessName);
        businessAddress = (TextView) findViewById(R.id.businessAddress);

        businessName.setText(nameText);
        businessAddress.setText(addressText);

        editBusinessBtn = (Button) findViewById(R.id.editBusiness);
        editBusinessBtn.setOnClickListener(this);

        selectActivitiesBtn = (Button) findViewById(R.id.viewEditActs);
        selectActivitiesBtn.setOnClickListener(this);

        for (int i = 0; i < activity.length; i++) {
            selectedActivities.add(activity[i]);
        }
    }


    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.viewEditActs:
                showSelectActivitiesDialog();
                break;
            case R.id.editBusiness:
                showEditBusinessDialog();
            default:
                break;
        }
    }

    //Popup Window for Editing Business Name and Address
    protected void showEditBusinessDialog() {
        AlertDialog.Builder editBusiness = new AlertDialog.Builder(this);

        final EditText nameInput = new EditText(this);
        final EditText addressInput = new EditText(this);
        nameInput.setHint("Enter new Business Name here...");
        addressInput.setHint("Enter new Address here...");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(nameInput);
        layout.addView(addressInput);
        editBusiness
                .setView(layout)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tName="", tAddress="";
                        tName = nameInput.getText().toString().trim();
                        tAddress = addressInput.getText().toString().trim();
                        if(!tName.isEmpty()){
                            nameText = tName;                   //******Update Business Name in Database HERE
                            businessName.setText(nameText);
                        }
                        if(!tAddress.isEmpty()){
                            addressText = tAddress;             //******Update Business Address in Database HERE
                            businessAddress.setText(addressText);
                        }
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = editBusiness.create();
        dialog.show();
    }

    //Shows Updated Activities on the View/Edit Activity Button: This is a temporary function
    protected void onChangeSelectedActivities() {
        StringBuilder stringBuilder = new StringBuilder();

        for(String activity : selectedActivities)
            stringBuilder.append(activity + ",");
        selectActivitiesBtn.setText(stringBuilder.toString());
    }

    //Popup Window for View/Edit Activities
    protected void showSelectActivitiesDialog() {
        boolean[] checkedActivities = new boolean[activity.length];
        int count = activity.length;

        for(int i = 0; i < count; i++)
            checkedActivities[i] = selectedActivities.contains(activity[i]);

        DialogInterface.OnMultiChoiceClickListener activityDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if (isChecked)
                    selectedActivities.add(activity[which]);
                else
                    selectedActivities.remove(activity[which]);

                onChangeSelectedActivities();                               //Temp
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Select Activities for Deletion");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Enter new Activity here...");
        builder
                .setMultiChoiceItems(activity, checkedActivities, activityDialogListener)
                .setView(input)
                .setNeutralButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String tAct = input.getText().toString().trim();
                        if(!tAct.isEmpty()) {
                            selectedActivities.add(tAct);
                            activity = new String[selectedActivities.size()];
                            activity = selectedActivities.toArray(activity);
                            //******Store Updated Array of Activities to go to Database GOES HERE
                        }
                        onChangeSelectedActivities();                       //Temp
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        activity = new String[selectedActivities.size()];
                        activity = selectedActivities.toArray(activity);
                        //******Store Updated Array of Activities into Database GOES HERE
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}